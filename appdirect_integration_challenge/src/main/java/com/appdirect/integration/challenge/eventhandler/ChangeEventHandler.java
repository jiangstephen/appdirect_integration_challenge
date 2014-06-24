package com.appdirect.integration.challenge.eventhandler;

import com.appdirect.integration.challenge.data.Event;
import com.appdirect.integration.challenge.data.EventResult;
import com.appdirect.integration.challenge.model.Subscriber;

public class ChangeEventHandler extends AbstractEventHandler<EventResult>{


	@Override
	public EventResult handleEvent(Event event) {
		String accountIdentifer = event.getPayload().getAccount().getAccountIdentifier();
		Subscriber subscriber = subscriberStore.findByAccountIdentifier(accountIdentifer);
		if(subscriber==null){
			return new EventResult(String.format("Account Identifier %s doesn't exist.", accountIdentifer), ErrorCode.ACCOUNT_NOT_FOUND);
		}
		subscriber.setEditionCode(event.getPayload().getOrder().getEditionCode());
		return new EventResult("Subscription is modified successfully", accountIdentifer);
		
	}

}
