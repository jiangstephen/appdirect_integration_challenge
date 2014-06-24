package com.appdirect.integration.challenge.datastore;

import com.appdirect.integration.challenge.model.NotificationEvent;

public class NotificationEventStore extends AbstractDataStore<NotificationEvent, Integer> {


	private static final long serialVersionUID = 1L;

	@Override
	protected void afterCreate(NotificationEvent data) {
	}

	@Override
	public Integer getIdentifer(NotificationEvent data) {
		return data.getId();
	}
	
}
