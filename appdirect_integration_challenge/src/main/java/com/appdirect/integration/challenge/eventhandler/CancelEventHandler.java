package com.appdirect.integration.challenge.eventhandler;

import com.appdirect.integration.challenge.data.Event;
import com.appdirect.integration.challenge.data.EventResult;
import com.appdirect.integration.challenge.model.Subscriber;

public class CancelEventHandler extends AbstractEventHandler<EventResult>{


	@Override
	public EventResult handleEvent(Event event) {
		String accountIdentifer = event.getPayload().getAccount().getAccountIdentifier();
		Subscriber subscriber = subscriberStore.findByAccountIdentifier(accountIdentifer);
		if(subscriber==null){
			return new EventResult(String.format("Account Identifier %s doesn't exist.", accountIdentifer), ErrorCode.ACCOUNT_NOT_FOUND);
		}
		subscriberStore.delete(subscriber.getUuid());
		return new EventResult("Subscription is cancelled successfully.", accountIdentifer);
	}

}
