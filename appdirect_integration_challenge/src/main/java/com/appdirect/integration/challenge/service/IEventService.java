package com.appdirect.integration.challenge.service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.core.HttpContext;

public interface IEventService {


	Response handleRequest(String eventUrl, HttpServletRequest _currentRequest,
			HttpContext _currentContext);

}