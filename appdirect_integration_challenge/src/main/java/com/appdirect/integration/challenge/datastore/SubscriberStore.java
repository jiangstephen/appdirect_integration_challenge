package com.appdirect.integration.challenge.datastore;

import com.appdirect.integration.challenge.model.Subscriber;

public class SubscriberStore extends AbstractDataStore<Subscriber> {


	private static final long serialVersionUID = 1L;
	
	public Subscriber findByAccountIdentifier(String accountIdentifer){
		for(Subscriber subscriber: this.storeRepository.values()){
			if(subscriber.getAccountIdentifier().equals(accountIdentifer)){
				return subscriber;
			}
		}
		return null;
	}

	@Override
	protected void afterCreate(Subscriber data) {
		data.setAccountIdentifier(data.getFirstName()+"_"+data.getLastName());
	}
}
