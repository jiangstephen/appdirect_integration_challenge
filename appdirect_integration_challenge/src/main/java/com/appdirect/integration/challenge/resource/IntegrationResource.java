package com.appdirect.integration.challenge.resource;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.appdirect.integration.challenge.service.IEventService;
import com.sun.jersey.api.core.HttpContext;

@Path("/")
@Produces(MediaType.APPLICATION_XML)
public class IntegrationResource {
	
	private IEventService eventService;
	
	@Context
	private HttpServletRequest _currentRequest;

	@Context
	private HttpContext _currentContext;


	public IEventService getEventService() {
		return eventService;
	}

	public void setEventService(IEventService eventService) {
		this.eventService = eventService;
	}

	@Path("subscribe")
	@GET
	public Response subscribe(@QueryParam("url") String eventUrl) throws Exception {
		return eventService.handleRequest(eventUrl, _currentRequest, _currentContext);
	}

	@GET
	@Path("cancel")
	public Response cancel(@QueryParam("url") String eventUrl)  {
		return eventService.handleRequest(eventUrl, _currentRequest, _currentContext);
	}

	@GET
	@Path("change")
	public Response change(@QueryParam("url") String eventUrl)  {
		return eventService.handleRequest(eventUrl, _currentRequest, _currentContext);
	}

	@GET
	@Path("notice")
	public Response notice(@QueryParam("url") String eventUrl)  {
		return eventService.handleRequest(eventUrl, _currentRequest, _currentContext);
	}
	
	
	@GET
	@Path("status")
	public Response status(@QueryParam("url") String eventUrl)  {
		return eventService.handleRequest(eventUrl, _currentRequest, _currentContext);
	}

	@GET
	@Path("addon")
	public Response addon(@QueryParam("url") String eventUrl)  {
		return eventService.handleRequest(eventUrl, _currentRequest, _currentContext);
	}

	@GET
	@Path("assign")
	public Response assign(@QueryParam("url") String eventUrl)  {
		return eventService.handleRequest(eventUrl, _currentRequest, _currentContext);
	}

	@GET
	@Path("unassign")  
	public Response unassign(@QueryParam("url") String eventUrl)  {
		return eventService.handleRequest(eventUrl, _currentRequest, _currentContext);
	}

}
