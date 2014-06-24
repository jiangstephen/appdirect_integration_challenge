package com.appdirect.integration.challenge.eventhandler;

import com.appdirect.integration.challenge.data.Event;
import com.appdirect.integration.challenge.data.EventResult;
import com.appdirect.integration.challenge.model.Subscriber;
import com.appdirect.integration.challenge.model.SubscriptionManager;

public class AssignEventHandler extends AccessEventHandler{

	@Override
	public EventResult handleEvent(Event event) {
		String accountIdentifier = event.getPayload().getAccount().getAccountIdentifier();
		Subscriber subscriber = subscriberStore.findByAccountIdentifier(accountIdentifier);
		if(subscriber==null){
			return new EventResult(String.format("Account Identifier %s doesn't exist.", accountIdentifier), ErrorCode.ACCOUNT_NOT_FOUND);
		}
		String email = event.getPayload().getUser().getEmail();
		SubscriptionManager subscriptionManager = this.toSubscriptionManager(event);
		
		if(this.isExist(subscriptionManager)){
			return new EventResult(String.format("user %s alerady exists", email), ErrorCode.USER_ALREADY_EXISTS);
		}
		subscriptionManagerStore.create(subscriptionManager, event);
		return new EventResult("User "+email+" added successfully", "");
	}

}
