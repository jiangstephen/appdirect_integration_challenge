package com.appdirect.integration.challenge.service;

import java.io.IOException;
import java.io.StringReader;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.appdirect.integration.challenge.data.Event;
import com.appdirect.integration.challenge.data.EventError;
import com.appdirect.integration.challenge.data.EventResult;
import com.appdirect.integration.challenge.datastore.IDataStore;
import com.appdirect.integration.challenge.eventhandler.ErrorCode;
import com.appdirect.integration.challenge.eventhandler.HandlerRepository;
import com.appdirect.integration.challenge.model.NotificationEvent;
import com.sun.jersey.api.core.HttpContext;

public class EventService implements IEventService {
	
	private static Logger LOG = Logger.getLogger(EventService.class);
	
	private IOAuthService oauthService;
	
	private JAXBService jaxbService;
	
	private HandlerRepository handlerRepoistory;
	
	private IDataStore<NotificationEvent, Integer> eventDataStore;

	
	public void setEventDataStore(IDataStore<NotificationEvent, Integer> eventDataStore) {
		this.eventDataStore = eventDataStore;
	}
	
	

	
	public void setOauthService(IOAuthService oauthService) {
		this.oauthService = oauthService;
	}

	public void setJaxbService(JAXBService jaxbService) {
		this.jaxbService = jaxbService;
	}

	public void setHandlerRepoistory(HandlerRepository handlerRepoistory) {
		this.handlerRepoistory = handlerRepoistory;
	}
	



	/* (non-Javadoc)
	 * @see com.appdirect.integration.challenge.service.IEventService#handleRequest(java.lang.String)
	 */
	@Override
	public Response handleRequest(String eventUrl, HttpServletRequest _currentRequest, HttpContext _currentContext) {
		String eventXml = null;
		EventResult eventResult = null;
		Event event =null;
		try{
			Validate.notNull(eventUrl, "eventUrl should not be null.");
			LOG.debug("event url is :"+eventUrl);
			printRequestInfo(_currentRequest);
			String signedEventUrl = oauthService.signUrl(eventUrl);
			eventXml=executeHttpRequest(new GetMethod(signedEventUrl), null);
			if(isEventXml(eventXml)){
				LOG.debug("eventXml:"+eventXml);
				event = jaxbService.getBeanFromString(eventXml, Event.class);
				oauthService.verifySignature(_currentContext);
				eventResult = handlerRepoistory.getHandler(event.getType()).handleEvent(event);
				registerEvent(event, eventResult);
				handlEventReturnUrl(event, eventResult);
				return Response.ok(eventResult).build();
			}
			EventError error = jaxbService.getBeanFromString(eventXml, EventError.class);
			return Response.ok(error).build();
		}
		catch(WebApplicationException e){
			LOG.error(e.toString(),e );
			eventResult = new EventResult("Unable to identify the OAuth", ErrorCode.UNAUTHORIZED);
			registerEvent(event, eventResult);
			return Response.ok(eventResult).build();
		}
		catch(Exception e){
			LOG.error(e.toString(),e );
			return Response.ok(new EventResult("Unknown server error", ErrorCode.UNKNOWN_ERROR)).build();
		}
	}

	@SuppressWarnings("deprecation")
	private String executeHttpRequest(HttpMethodBase method, String body) throws Exception {
		HttpClient client = new HttpClient();
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(3, false));
		if(body!=null && method instanceof PostMethod){
			PostMethod postMethod = (PostMethod)method;
			postMethod.setRequestEntity(new StringRequestEntity(body));
			postMethod.addRequestHeader("content-type","application-xml");
		}
		try {
			int statusCode = client.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
			}
			byte[] responseBody = method.getResponseBody();
			return new String(responseBody);
		} catch (Exception e) {
			System.err.println("Fatal transport error: " + e.getMessage());
			throw e;
		} finally {
			method.releaseConnection();
		}
	}	
	
	private void handlEventReturnUrl(Event event, EventResult eventResult) throws JAXBException, Exception{
		if(StringUtils.isNotEmpty(event.getReturnUrl())){ //no returnUrl is required.
			LOG.debug("returnUrl:"+event.getReturnUrl());
			String signedReturnUrl = oauthService.signUrl(event.getReturnUrl());
			String bodyContent = executeHttpRequest(new PostMethod(signedReturnUrl), jaxbService.convertBeanToString(eventResult));
			printString(bodyContent, 200, "no body content");
			LOG.debug("returnUrl:"+event.getReturnUrl());
		}
	}

	private void printRequestInfo(HttpServletRequest _currentRequest) throws IOException {
		LOG.debug("request body:"
				+ IOUtils.toString(_currentRequest.getInputStream()));
		IOUtils.closeQuietly(_currentRequest.getInputStream());
		@SuppressWarnings("unchecked")
		Enumeration<String> enumeration = _currentRequest.getHeaderNames();
		while (enumeration.hasMoreElements()) {
			String header = enumeration.nextElement();
			System.out
					.println(header + ":" + _currentRequest.getHeader(header));
		}
	}
	
	private void printString(String toPrint, int limit, String messageWhenEmpty){
		LOG.debug(!StringUtils.isEmpty(toPrint)?(toPrint.length()>limit?toPrint.substring(0,limit):toPrint):messageWhenEmpty);
	}

	private void registerEvent(Event event, EventResult eventResult) {
		NotificationEvent notificationEvent = new NotificationEvent();
		try{
			notificationEvent.setErrorCdoe(eventResult.getErrorCode());
			notificationEvent.setEventType(event.getType());
			notificationEvent.setMessage(eventResult.getMessage());
			notificationEvent.setSuccess(eventResult.isSuccess());
			notificationEvent.setAccountIdentifier(event.getPayload().getAccount().getAccountIdentifier());
		} catch(RuntimeException e){
			LOG.debug(e.toString(),e );
		} finally {
			eventDataStore.create(notificationEvent, event);
		}
	}
	
	private boolean isEventXml(String xml) throws ParserConfigurationException, SAXException, IOException{
	    DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	    InputSource is = new InputSource();
	    is.setCharacterStream(new StringReader(xml));
	    Document doc = db.parse(is);
	    String firstChildNodeName = doc.getFirstChild().getNodeName();
	    return "event".equals(firstChildNodeName);
	}



}
