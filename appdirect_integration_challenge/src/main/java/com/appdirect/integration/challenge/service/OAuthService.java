package com.appdirect.integration.challenge.service;

import java.io.IOException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.signature.QueryStringSigningStrategy;

import org.apache.log4j.Logger;

import com.sun.jersey.api.core.HttpContext;
import com.sun.jersey.oauth.server.OAuthServerRequest;
import com.sun.jersey.oauth.signature.OAuthParameters;
import com.sun.jersey.oauth.signature.OAuthSecrets;

public class OAuthService implements IOAuthService {
	
	private static Logger LOG = Logger.getLogger(OAuthService.class);
	
	public static String APPDIRECT_CUSTOMER_KEY="stephens-product-10559";
	public static String APPDIRECT_CUSTOMER_SECRET="TpOKRUHMTzVOtVxI";
	
	private SignatureService signatureService;
	
	
	
	public void setSignatureService(SignatureService signatureService) {
		this.signatureService = signatureService;
	}

	/* (non-Javadoc)
	 * @see com.appdirect.integration.challenge.service.IOAuthService#signUrl(java.lang.String)
	 */
	@Override
	public String signUrl(String url) throws IOException, OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException{
		
		OAuthConsumer consumer = new DefaultOAuthConsumer(APPDIRECT_CUSTOMER_KEY,APPDIRECT_CUSTOMER_SECRET);
		consumer.setSigningStrategy( new QueryStringSigningStrategy());
		String signedUrl = consumer.sign(url);
		return signedUrl;
	}
	
	@Override
	public void verifySignature(HttpContext _currentContext) {
		OAuthParameters params = new OAuthParameters();
		OAuthServerRequest oauthServerRequest = new OAuthServerRequest(
				_currentContext.getRequest());
		params.readRequest(oauthServerRequest);
		LOG.debug("timestamp:" + params.getTimestamp());
		OAuthSecrets oauthSecret = new OAuthSecrets();
		oauthSecret.setConsumerSecret(OAuthService.APPDIRECT_CUSTOMER_SECRET);
		if (!signatureService.verifySignature(oauthServerRequest, params,
				oauthSecret)) {
			throw new WebApplicationException(Status.UNAUTHORIZED);
		}
		LOG.debug("Authentication successfully");
	}

}
