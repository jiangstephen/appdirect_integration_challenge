package com.appdirect.integration.challenge.service;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import com.appdirect.integration.challenge.data.Event;
import com.appdirect.integration.challenge.data.EventResult;
import com.appdirect.integration.challenge.model.NotificationEvent;
import com.appdirect.integration.challenge.model.Subscriber;
import com.appdirect.integration.challenge.model.SubscriptionManager;
import com.sun.jersey.api.core.HttpContext;

public interface IEventService {


	Response handleRequest(String eventUrl, HttpServletRequest _currentRequest,
			HttpContext _currentContext);

	

}