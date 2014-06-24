package com.appdirect.integration.challenge.eventhandler;

import com.appdirect.integration.challenge.data.EventResult;
import com.appdirect.integration.challenge.datastore.SubscriberStore;

public abstract class AbstractEventHandler<T extends EventResult> implements IEventHandler<T>{

	
	protected SubscriberStore subscriberStore;
	
	
	public void setSubscriberStore(SubscriberStore subscriberStore) {
		this.subscriberStore = subscriberStore;
	}

}
