package com.appdirect.integration.challenge.eventhandler;

import com.appdirect.integration.challenge.data.Event;
import com.appdirect.integration.challenge.data.EventResult;

public interface IEventHandler<T extends EventResult> {
	
	T handleEvent(Event event);

}
