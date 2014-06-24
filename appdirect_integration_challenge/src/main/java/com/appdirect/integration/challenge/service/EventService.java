package com.appdirect.integration.challenge.service;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.UnmarshalException;

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

import com.appdirect.integration.challenge.data.Event;
import com.appdirect.integration.challenge.data.EventError;
import com.appdirect.integration.challenge.data.EventResult;
import com.appdirect.integration.challenge.eventhandler.ErrorCode;
import com.appdirect.integration.challenge.eventhandler.HandlerRepository;
import com.sun.jersey.api.core.HttpContext;

public class EventService implements IEventService {
	
	private IOAuthService oauthService;
	
	private JAXBService jaxbService;
	
	private HandlerRepository handlerRepoistory;
	
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
		try{
			Validate.notNull(eventUrl, "eventUrl should not be null.");
			System.out.println("event url is :"+eventUrl);
			printRequestInfo(_currentRequest);
			oauthService.verifySignature(_currentContext);
			String signedEventUrl = oauthService.signUrl(eventUrl);
			eventXml=executeHttpRequest(new GetMethod(signedEventUrl), null);
			System.out.println("eventXml:"+eventXml);
			Event event = jaxbService.getBeanFromString(eventXml, Event.class);
			EventResult eventResult = handlerRepoistory.getHandler(event.getType()).handleEvent(event);
			if(StringUtils.isNotEmpty(event.getReturnUrl())){ //no returnUrl is required.
				System.out.println("returnUrl:"+event.getReturnUrl());
				String signedReturnUrl = oauthService.signUrl(event.getReturnUrl());
				String bodyContent = executeHttpRequest(new PostMethod(signedReturnUrl), jaxbService.convertBeanToString(eventResult));
				printString(bodyContent, 200, "no body content");
				System.out.println("returnUrl:"+event.getReturnUrl());
			}
			return Response.ok(eventResult).build();
		}
		catch(UnmarshalException e){
			try{
				EventError error = jaxbService.getBeanFromString(eventXml, EventError.class);
				return Response.ok(error).build();
			} catch(Exception ex){
				ex.printStackTrace();
				return Response.status(Status.INTERNAL_SERVER_ERROR).tag(e.toString()).build();
			}
		}
		catch(WebApplicationException e){
			e.printStackTrace();
			return Response.ok(new EventResult("Unable to identify the OAuth", ErrorCode.UNAUTHORIZED)).build();
		}
		catch(Exception e){
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).tag(e.toString()).build();
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

	private void printRequestInfo(HttpServletRequest _currentRequest) throws IOException {
		System.out.println("request body:"
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
		System.out.println(!StringUtils.isEmpty(toPrint)?(toPrint.length()>limit?toPrint.substring(0,limit):toPrint):messageWhenEmpty);
	}

}
