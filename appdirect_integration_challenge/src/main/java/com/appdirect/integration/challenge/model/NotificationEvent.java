package com.appdirect.integration.challenge.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.appdirect.integration.challenge.data.EventType;
import com.appdirect.integration.challenge.eventhandler.ErrorCode;

@XmlRootElement
public class NotificationEvent  extends Entity{

	private static final long serialVersionUID = 1L;
	private EventType eventType;
	private String accountIdentifier;
	private boolean isSuccess;
	private String message;
	private ErrorCode errorCdoe;
	public EventType getEventType() {
		return eventType;
	}
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
	public String getAccountIdentifier() {
		return accountIdentifier;
	}
	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ErrorCode getErrorCdoe() {
		return errorCdoe;
	}
	public void setErrorCdoe(ErrorCode errorCdoe) {
		this.errorCdoe = errorCdoe;
	}


}
