package com.appdirect.integration.challenge.eventhandler;

import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.InitializingBean;

import com.appdirect.integration.challenge.data.EventType;

public class HandlerRepository implements InitializingBean{
	
	private IEventHandler<?>  eventDefaultHandler;
	
	private Map<EventType, IEventHandler<?>>  eventHandlers;
	
	
	
	public void setEventDefaultHandler(IEventHandler<?> eventDefaultHandler) {
		this.eventDefaultHandler = eventDefaultHandler;
	}

	public void setEventHandlers(Map<EventType, IEventHandler<?>> eventHandlers) {
		this.eventHandlers = eventHandlers;
	}

	public IEventHandler<?> getHandler(EventType eventType){
		if(eventHandlers.containsKey(eventType)){
			return eventHandlers.get(eventType);
		}
		return eventDefaultHandler;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Validate.notNull(eventHandlers, "eventhandlers should not be null");
		Validate.notNull(eventDefaultHandler,"eventDefaultHandler should not be null");
		
	}
}
