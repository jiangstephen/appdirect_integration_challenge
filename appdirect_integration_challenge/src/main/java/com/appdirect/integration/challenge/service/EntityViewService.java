package com.appdirect.integration.challenge.service;

import java.util.Collection;

import com.appdirect.integration.challenge.datastore.IDataStore;
import com.appdirect.integration.challenge.datastore.SubscriptionManagerStore;
import com.appdirect.integration.challenge.model.NotificationEvent;
import com.appdirect.integration.challenge.model.Subscriber;
import com.appdirect.integration.challenge.model.SubscriptionManager;

public class EntityViewService implements IEntityViewService {
	
	private IDataStore<NotificationEvent, Integer> eventDataStore;
	
	private IDataStore<Subscriber, String> subscriberStore;
	
	private SubscriptionManagerStore  subscriptionManagerStore;
	
	public void setEventDataStore(IDataStore<NotificationEvent, Integer> eventDataStore) {
		this.eventDataStore = eventDataStore;
	}

	public void setSubscriberStore(IDataStore<Subscriber, String> subscriberStore) {
		this.subscriberStore = subscriberStore;
	}

	public void setSubscriptionManagerStore(
			SubscriptionManagerStore subscriptionManagerStore) {
		this.subscriptionManagerStore = subscriptionManagerStore;
	}
	

	/* (non-Javadoc)
	 * @see com.appdirect.integration.challenge.service.IEntityViewService#getAllNotificationEvent()
	 */
	@Override
	public Collection<NotificationEvent> getAllNotificationEvent() {
		return eventDataStore.findAll();
	}

	/* (non-Javadoc)
	 * @see com.appdirect.integration.challenge.service.IEntityViewService#getAllSubScriber()
	 */
	@Override
	public Collection<Subscriber> getAllSubScriber() {
		return subscriberStore.findAll();
	}

	/* (non-Javadoc)
	 * @see com.appdirect.integration.challenge.service.IEntityViewService#getSubscriptionManager(java.lang.String)
	 */
	@Override
	public Collection<SubscriptionManager> getSubscriptionManager(
			String accountIdentifier) {
		return subscriptionManagerStore.getAccountManagerByAccountIdentifier(accountIdentifier);
	}

}
