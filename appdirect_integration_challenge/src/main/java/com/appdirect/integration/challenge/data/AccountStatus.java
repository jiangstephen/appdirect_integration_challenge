package com.appdirect.integration.challenge.data;

public enum AccountStatus {
	
	FREE_TRIAL,// the subscription is in free trial, the customer is not being charged.
	FREE_TRIAL_EXPIRED,// the free trial has expired, the customer cannot use his subscription unless he converts to a paid subscription.
	ACTIVE,// regular subscription in "good standing". If the application is not free, the customer is being changed the appropriate amount.
	SUSPENDED,// an invoice for this subscription is overdue. The subscription cannot be used unless the customers pays the invoice.
	CANCELLED;// the subscription is not active anymore and does not show up in the customer 

}
