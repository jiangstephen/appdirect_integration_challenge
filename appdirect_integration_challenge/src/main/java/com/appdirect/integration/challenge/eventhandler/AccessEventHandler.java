package com.appdirect.integration.challenge.eventhandler;

import com.appdirect.integration.challenge.data.Event;
import com.appdirect.integration.challenge.data.EventResult;
import com.appdirect.integration.challenge.data.User;
import com.appdirect.integration.challenge.datastore.SubscriptionManagerStore;
import com.appdirect.integration.challenge.model.SubscriptionManager;

public abstract class AccessEventHandler extends AbstractEventHandler<EventResult>{

	protected SubscriptionManagerStore subscriptionManagerStore;
	
	public void setSubscriptionManagerStore(
			SubscriptionManagerStore subscriptionManagerStore) {
		this.subscriptionManagerStore = subscriptionManagerStore;
	}
	
	protected SubscriptionManager toSubscriptionManager(Event event){
		SubscriptionManager subscriptionManager = new SubscriptionManager();
		subscriptionManager.setAccountIdentifier(event.getPayload().getAccount().getAccountIdentifier());
		User user = event.getPayload().getUser();
		subscriptionManager.setEmail(user.getEmail());
		subscriptionManager.setFirstName(user.getFirstName());
		subscriptionManager.setLastName(user.getLastName());
		return subscriptionManager;
	}
	
	protected boolean isExist(SubscriptionManager subscriptionManager){
		return subscriptionManagerStore.findOne(subscriptionManagerStore.getIdentifer(subscriptionManager))!=null;
	}

}
