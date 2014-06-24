package com.appdirect.integration.challenge.service;

import java.util.Collection;

import com.appdirect.integration.challenge.model.NotificationEvent;
import com.appdirect.integration.challenge.model.Subscriber;
import com.appdirect.integration.challenge.model.SubscriptionManager;

public interface IEntityViewService {

	public abstract Collection<NotificationEvent> getAllNotificationEvent();

	public abstract Collection<Subscriber> getAllSubScriber();

	public abstract Collection<SubscriptionManager> getSubscriptionManager(
			String accountIdentifier);

}