package com.appdirect.integration.challenge.service;

import com.sun.jersey.oauth.server.OAuthServerRequest;
import com.sun.jersey.oauth.signature.OAuthParameters;
import com.sun.jersey.oauth.signature.OAuthSecrets;

public interface ISignatureService {

	public abstract boolean verifySignature(OAuthServerRequest osr,
			OAuthParameters params, OAuthSecrets secrets);

	public abstract String getSignatureBaseString(String method, String url,
			String data);

	public abstract String getSignatureData(String oauth_consumer_key,
			String oauth_nonce, String oauth_signature_method,
			String oauth_timestamp, String oauth_version);

	public abstract String encrypt(String data, String key)
			throws java.security.SignatureException;

	public abstract void verifySignature(String signature, String baseString,
			String customerKey);

}