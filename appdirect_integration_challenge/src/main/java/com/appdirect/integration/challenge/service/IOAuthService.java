package com.appdirect.integration.challenge.service;

import java.io.IOException;

import com.sun.jersey.api.core.HttpContext;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

public interface IOAuthService {

	public abstract String signUrl(String url) throws IOException,
			OAuthMessageSignerException, OAuthExpectationFailedException,
			OAuthCommunicationException;

	void verifySignature(HttpContext _currentContext);

}