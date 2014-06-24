package com.appdirect.integration.challenge.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder={"type"})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Notice {
	
	@XmlElement
	private NoticeType type;

	public NoticeType getType() {
		return type;
	}

	public void setType(NoticeType type) {
		this.type = type;
	}
	

}
