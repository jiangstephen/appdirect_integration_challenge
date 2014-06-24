package com.appdirect.integration.challenge.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder={"type","marketplace","creator","payload", "returnUrl"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Event {


	@XmlElement
	private EventType type;
	@XmlElement
	private MarketPlace marketplace;
	@XmlElement
	private Creator creator;
	@XmlElement
	private Payload payload;
	@XmlElement
	private String returnUrl;
	
	public EventType getType() {
		return type;
	}
	public void setType(EventType type) {
		this.type = type;
	}
	public MarketPlace getMarketplace() {
		return marketplace;
	}
	public void setMarketplace(MarketPlace marketplace) {
		this.marketplace = marketplace;
	}
	public Creator getCreator() {
		return creator;
	}
	public void setCreator(Creator creator) {
		this.creator = creator;
	}
	public Payload getPayload() {
		return payload;
	}
	public void setPayload(Payload payload) {
		this.payload = payload;
	}
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	
}