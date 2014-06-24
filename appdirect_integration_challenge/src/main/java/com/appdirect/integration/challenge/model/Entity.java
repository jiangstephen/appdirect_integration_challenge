package com.appdirect.integration.challenge.model;

import java.io.Serializable;
import java.util.Date;

public abstract class Entity implements Serializable{


	private static final long serialVersionUID = 1L;
	
	protected String uuid;
	
	protected String name;
	
	protected Date createdDate;
	
	protected Date updatedDate;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
	
	

}
