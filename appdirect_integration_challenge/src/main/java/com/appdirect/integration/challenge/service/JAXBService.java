package com.appdirect.integration.challenge.service;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JAXBService implements IJAXBService {
	
	/* (non-Javadoc)
	 * @see com.appdirect.integration.challenge.service.IJAXBService#getBeanFromString(java.lang.String, java.lang.Class)
	 */
	@Override
	public <T> T getBeanFromString(String xml, Class<T> clazz) throws JAXBException{
	    JAXBContext jc = JAXBContext.newInstance(clazz);
	    StringReader reader = new StringReader(xml);
	    Unmarshaller unmarshaller = jc.createUnmarshaller();
	    @SuppressWarnings("unchecked")
		T result =(T)unmarshaller.unmarshal(reader);
	    return result;
	}
	
	/* (non-Javadoc)
	 * @see com.appdirect.integration.challenge.service.IJAXBService#convertBeanToString(T)
	 */
	@Override
	public <T> String convertBeanToString(T xmlElement) throws JAXBException{
		JAXBContext jc = JAXBContext.newInstance(xmlElement.getClass());
		Marshaller marshaller = jc.createMarshaller();
		StringWriter writer = new StringWriter();
		marshaller.marshal(xmlElement, writer);
		return writer.getBuffer().toString();
	}

}
