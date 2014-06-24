package com.appdirect.integration.challenge.service;

import java.security.SignatureException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.sun.jersey.core.util.Base64;
import com.sun.jersey.oauth.server.OAuthServerRequest;
import com.sun.jersey.oauth.signature.OAuthParameters;
import com.sun.jersey.oauth.signature.OAuthSecrets;
import com.sun.jersey.oauth.signature.OAuthSignature;
import com.sun.jersey.oauth.signature.OAuthSignatureException;
/**
 * This class defines common routines for generating authentication signatures
 * for AWS requests.
 */
public class SignatureService implements ISignatureService {
	
	private static Logger lOG = Logger.getLogger(SignatureService.class);
	
	private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
	
	
	/* (non-Javadoc)
	 * @see com.appdirect.integration.challenge.service.ISignatureService#verifySignature(com.sun.jersey.oauth.server.OAuthServerRequest, com.sun.jersey.oauth.signature.OAuthParameters, com.sun.jersey.oauth.signature.OAuthSecrets)
	 */
	@Override
	public boolean verifySignature(OAuthServerRequest osr,OAuthParameters params, OAuthSecrets secrets) {
		try {
			return OAuthSignature.verify(osr, params, secrets);
		} catch (OAuthSignatureException ose) {
			throw new WebApplicationException(Status.UNAUTHORIZED);
		}
	}
	
	/*
	private String getSignatureBaseString(String method, String url, String data){
		return String.format("%s&%s&%s", method.toUpperCase(), URLEncoder.encode(url), URLEncoder.encode(data));
	}
	

	private String getSignatureData(String oauth_consumer_key, String oauth_nonce, String oauth_signature_method, String oauth_timestamp, String oauth_version ){
		return String.format("oauth_consumer_key=%s&oauth_nonce=%s&oauth_signature_method=%s&oauth_timestamp=%s&oauth_version=%s",
				 oauth_consumer_key,  oauth_nonce,  oauth_signature_method,  oauth_timestamp,  oauth_version);
	}*/

	private String encrypt(String data, String key) throws java.security.SignatureException {
		String result;
		try {
			SecretKeySpec signingKey = new SecretKeySpec((key+"&").getBytes(),HMAC_SHA1_ALGORITHM);

			Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
			mac.init(signingKey);

			byte[] rawHmac = mac.doFinal(data.getBytes());

			result =new String(Base64.encode(rawHmac));
		} catch (Exception e) {
			throw new SignatureException("Failed to generate HMAC : "+ e.getMessage());
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.appdirect.integration.challenge.service.ISignatureService#verifySignature(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void verifySignature(String signature, String baseString, String customerKey){
		if(!OAuthService.APPDIRECT_CUSTOMER_KEY.equals(customerKey)){
			throw new WebApplicationException(Status.UNAUTHORIZED);
		}
		try{
			String expectedSignature = encrypt(baseString, OAuthService.APPDIRECT_CUSTOMER_SECRET);
			if(!expectedSignature.equals(signature)){
				throw new WebApplicationException(Status.UNAUTHORIZED);
			}
		}  catch (WebApplicationException e){
			throw e;
		}  catch(Exception e){
			lOG.debug(e.toString(),e );
			throw new WebApplicationException(Status.UNAUTHORIZED);
		}
	}
}