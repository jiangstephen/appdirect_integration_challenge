package com.appdirect.integration.challenge.data;

public enum EventType {
	
	SUBSCRIPTION_ORDER,  // fire by AppDirect when a user buys an app from AppDirect.
	SUBSCRIPTION_CHANGE, // fired by AppDirect when a user upgrades/downgrades/modifies an existing subscription.
	SUBSCRIPTION_CANCEL, // fired by AppDirect when a user cancels a subscription.
	SUBSCRIPTION_NOTICE, // fired by AppDirect when a subscription goes overdue or delinquent.
	USER_ASSIGNMENT,     // fired by AppDirect when a user assigns a user to an app.
	USER_UNASSIGNMENT;

}
