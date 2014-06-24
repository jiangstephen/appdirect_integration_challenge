package com.appdirect.integration.challenge.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.appdirect.integration.challenge.eventhandler.ErrorCode;


@XmlRootElement(name="result")
@XmlType (propOrder={"success","errorCode","message", "accountIdentifier"})
@XmlAccessorType(XmlAccessType.FIELD)
public class EventResult {
	
	
	
	@XmlElement
	private boolean success;
	@XmlElement
	private ErrorCode errorCode;
	@XmlElement
	private String message;
	@XmlElement
	private String accountIdentifier;
	
	public EventResult(){
		
	}
	
	public EventResult(String message, ErrorCode errorCode){
		this.success = false;
		this.message = message;
		this.errorCode = errorCode;
	}
	
	public EventResult(String message, String accountIdentifier){
		this.success = true;
		this.message = message;
		this.accountIdentifier = accountIdentifier;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public ErrorCode getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAccountIdentifier() {
		return accountIdentifier;
	}
	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}
	
	

}
