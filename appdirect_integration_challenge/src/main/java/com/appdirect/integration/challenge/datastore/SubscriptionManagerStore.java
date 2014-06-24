package com.appdirect.integration.challenge.datastore;

import java.util.ArrayList;
import java.util.Collection;

import com.appdirect.integration.challenge.model.SubscriptionManager;

public class SubscriptionManagerStore extends AbstractDataStore<SubscriptionManager, String> {


	private static final long serialVersionUID = 1L;

	@Override
	public String getIdentifer(SubscriptionManager data) {
		return data.getAccountIdentifier()+"-"+data.getEmail();
	}
	
	public Collection<SubscriptionManager> getAccountManagerByAccountIdentifier(String accountIdentifer){
		Collection<SubscriptionManager> subManagers = new ArrayList<SubscriptionManager>();
		for(SubscriptionManager subscriptionManager: storeRepository.values()){
			if(accountIdentifer.equals(subscriptionManager.getAccountIdentifier())){
				subManagers.add(subscriptionManager);
			}
		}
		return subManagers;
		
	}
	
}
