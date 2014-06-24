package com.appdirect.integration.challenge.service;

import java.io.IOException;

import junit.framework.Assert;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import org.junit.Before;
import org.junit.Test;

import com.appdirect.integration.challenge.service.OAuthService;
import com.google.appengine.repackaged.org.apache.commons.httpclient.HttpStatus;

public class OAuthServiceTest {
	
	private IOAuthService testee;
	
	@Before
	public void setUp(){
		testee = new OAuthService();
	}
	
	@Test
	public void testShouldSignPost() throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, IOException{
		System.out.println(testee.signUrl("http://eng-district-618.appspot.com/rest/subscribe"));
	}

}
