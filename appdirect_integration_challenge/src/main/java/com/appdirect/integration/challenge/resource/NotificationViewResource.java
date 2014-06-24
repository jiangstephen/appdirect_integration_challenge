package com.appdirect.integration.challenge.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.appdirect.integration.challenge.service.IEventService;


@Path("/ui")
public class NotificationViewResource {
	
	private static Logger LOG = Logger.getLogger(NotificationViewResource.class);
	
	private IEventService eventService;
	
	public void setEventService(IEventService eventService) {
		this.eventService = eventService;
	}

	@GET
	@Path("showevent")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showAllEvent()  {
		
		LOG.debug(eventService.getAllNotificationEvent());
		return Response.ok(eventService.getAllNotificationEvent()).build();
	}
	
	@GET
	@Path("showsubscriber")	
	@Produces(MediaType.APPLICATION_JSON)
	public Response showAllSubscriber()  {
		return Response.ok(eventService.getAllSubScriber()).build();
	}
	
	@GET
	@Path("showaccountmanager/{accountIdentifier}")	
	@Produces(MediaType.APPLICATION_JSON)
	public Response showAccountManager(@PathParam("accountIdentifier") String accountIdentifier)  {
		return Response.ok(eventService.getSubscriptionManager(accountIdentifier)).build();
	}
	
	
	
}
