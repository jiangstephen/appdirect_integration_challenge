package com.appdirect.integration.challenge.service;

import com.sun.jersey.oauth.server.OAuthServerRequest;
import com.sun.jersey.oauth.signature.OAuthParameters;
import com.sun.jersey.oauth.signature.OAuthSecrets;

public interface ISignatureService {

	public abstract boolean verifySignature(OAuthServerRequest osr,
			OAuthParameters params, OAuthSecrets secrets);

	public abstract void verifySignature(String signature, String baseString,
			String customerKey);

}