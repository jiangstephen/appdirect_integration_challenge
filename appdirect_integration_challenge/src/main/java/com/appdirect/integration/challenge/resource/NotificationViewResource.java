package com.appdirect.integration.challenge.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.appdirect.integration.challenge.service.IEntityViewService;


@Path("/ui")
public class NotificationViewResource {
	
	private static Logger LOG = Logger.getLogger(NotificationViewResource.class);
	
	private IEntityViewService entityViewService;
	
	public void setEntityViewService(IEntityViewService entityViewService) {
		this.entityViewService = entityViewService;
	}

	@GET
	@Path("showevent")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showAllEvent()  {
		
		LOG.debug(entityViewService.getAllNotificationEvent());
		return Response.ok(entityViewService.getAllNotificationEvent()).build();
	}
	
	@GET
	@Path("showsubscriber")	
	@Produces(MediaType.APPLICATION_JSON)
	public Response showAllSubscriber()  {
		return Response.ok(entityViewService.getAllSubScriber()).build();
	}
	
	@GET
	@Path("showaccountmanager/{accountIdentifier}")	
	@Produces(MediaType.APPLICATION_JSON)
	public Response showAccountManager(@PathParam("accountIdentifier") String accountIdentifier)  {
		return Response.ok(entityViewService.getSubscriptionManager(accountIdentifier)).build();
	}
	
	
	
}
