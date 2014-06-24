package com.appdirect.integration.challenge.service;

import java.security.SignatureException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SignatureServiceTest {
	
	private ISignatureService testee;
	
	private String url = "http://eng-district-618.appspot.com/rest/subscribe";
	
	private static String expectedBaseString = "GET&http%3A%2F%2Feng-district-618.appspot.com%2Frest%2Fsubscribe&oauth_consumer_key%3Dstephens-product-10559%26oauth_nonce%3D3338530296809207081%26oauth_signature_method%3DHMAC-SHA1%26oauth_timestamp%3D1403402987%26oauth_version%3D1.0";
	
	private String oauth_consumer_key = "stephens-product-10559", 
		oauth_nonce = "3338530296809207081", 
		oauth_signature_method ="HMAC-SHA1", 
		oauth_timestamp="1403402987", 
		oauth_version="1.0";
	
	@Before
	public void setUp(){
		testee = new SignatureService();
	}
	
	/*http://eng-district-618.appspot.com/rest/subscribe?
	 * oauth_consumer_key=stephens-product-10559&
	 * oauth_nonce=3338530296809207081&
	 * oauth_signature=Lqi6bm3Qa8O4udUxfqGdn5lYvyI%3D&
	 * oauth_signature_method=HMAC-SHA1&
	 * oauth_timestamp=1403402987&
	 * oauth_version=1.0
	*/
	@Test
	public void shouldGenerateSignature() throws SignatureException{
		String expected = "Lqi6bm3Qa8O4udUxfqGdn5lYvyI=";
		
		String baseString = testee.getSignatureBaseString("GET", url, testee.getSignatureData(oauth_consumer_key, oauth_nonce, oauth_signature_method, oauth_timestamp, oauth_version));
		Assert.assertEquals(expectedBaseString, baseString);
		String actual = testee.encrypt(expectedBaseString, "TpOKRUHMTzVOtVxI");
		Assert.assertEquals(expected, actual);
		
	}

}
