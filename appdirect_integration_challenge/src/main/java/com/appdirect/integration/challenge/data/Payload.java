package com.appdirect.integration.challenge.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlType(propOrder={"company", "account", "user", "order", "notice"})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Payload {
	
	@XmlElement
	private Company company;
	
	@XmlElement 
	private Account account;
	
	@XmlElement 
	private User user;
	
	
	@XmlElement 
	private Notice notice;
	
	@XmlElement
	private Order order;
	
	
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Notice getNotice() {
		return notice;
	}
	public void setNotice(Notice notice) {
		this.notice = notice;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	
}