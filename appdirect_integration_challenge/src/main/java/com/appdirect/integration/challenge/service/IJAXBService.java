package com.appdirect.integration.challenge.service;

import javax.xml.bind.JAXBException;

public interface IJAXBService {

	public abstract <T> T getBeanFromString(String xml, Class<T> clazz)
			throws JAXBException;

	public abstract <T> String convertBeanToString(T xmlElement)
			throws JAXBException;

}