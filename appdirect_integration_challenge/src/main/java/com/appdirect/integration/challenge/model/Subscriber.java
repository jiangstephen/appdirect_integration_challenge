package com.appdirect.integration.challenge.model;

import com.appdirect.integration.challenge.data.EditionType;

public class Subscriber extends Entity{
	

	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String accountIdentifier;
	private String companyUuid;
	private EditionType editionCode; 


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
	public String getAccountIdentifier() {
		return accountIdentifier;
	}
	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}
	public String getCompanyUuid() {
		return companyUuid;
	}
	public void setCompanyUuid(String companyUuid) {
		this.companyUuid = companyUuid;
	}
	public EditionType getEditionCode() {
		return editionCode;
	}
	public void setEditionCode(EditionType editionCode) {
		this.editionCode = editionCode;
	}

}
