package com.appdirect.integration.challenge.eventhandler;

import com.appdirect.integration.challenge.data.Event;
import com.appdirect.integration.challenge.data.EventResult;
import com.appdirect.integration.challenge.model.Subscriber;
import com.appdirect.integration.challenge.model.SubscriptionManager;

public class UnassignEventHandler extends AccessEventHandler{

	@Override
	public EventResult handleEvent(Event event) {
		String accountIdentifier = event.getPayload().getAccount().getAccountIdentifier();
		Subscriber subscriber = subscriberStore.findByAccountIdentifier(accountIdentifier);
		if(subscriber==null){
			return new EventResult(String.format("Account Identifier %s doesn't exist.", accountIdentifier), ErrorCode.ACCOUNT_NOT_FOUND);
		}
		SubscriptionManager subscriptionManager = this.toSubscriptionManager(event);
		String email = subscriptionManager.getEmail();
		if(this.isExist(subscriptionManager)){
			subscriptionManagerStore.delete(subscriptionManagerStore.getIdentifer(subscriptionManager));
			return new EventResult("User "+email+" removed successfully", "");
		}
		return new EventResult("User "+email+" not found ", ErrorCode.USER_NOT_FOUND);

	}

}
