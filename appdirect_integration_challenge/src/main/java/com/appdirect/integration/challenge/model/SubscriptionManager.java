package com.appdirect.integration.challenge.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SubscriptionManager extends Entity{

	private static final long serialVersionUID = 1L;
	
	private String accountIdentifier;
	
	private String email;
	
	private String firstName;
	
	private String lastName;

	public String getAccountIdentifier() {
		return accountIdentifier;
	}

	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	

}
