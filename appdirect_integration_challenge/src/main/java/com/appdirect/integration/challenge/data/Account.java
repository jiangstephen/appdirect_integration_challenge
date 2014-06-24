package com.appdirect.integration.challenge.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder={ "accountIdentifier","status"})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Account {
	
	@XmlElement
	private String accountIdentifier;
	
	@XmlElement
	private AccountStatus status;

	public String getAccountIdentifier() {
		return accountIdentifier;
	}

	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	
}
