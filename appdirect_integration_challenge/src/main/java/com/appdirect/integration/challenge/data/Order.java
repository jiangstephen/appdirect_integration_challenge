package com.appdirect.integration.challenge.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder={"editionCode", "item"})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Order{
	@XmlElement
	private EditionType editionCode;
	@XmlElement
	private List<Item> item = new ArrayList<Item>();
	
	public EditionType getEditionCode() {
		return editionCode;
	}
	public void setEditionCode(EditionType editionCode) {
		this.editionCode = editionCode;
	}
	public List<Item> getItem() {
		return item;
	}
	public void setItem(List<Item> item) {
		this.item = item;
	}
	
	
}