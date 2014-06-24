package com.appdirect.integration.challenge.datastore;

import java.io.Serializable;
import java.util.Collection;

import com.appdirect.integration.challenge.data.Event;
import com.appdirect.integration.challenge.model.Entity;

public interface IDataStore<T extends Entity, ID> extends Serializable {
	
	ID getIdentifer(T data);
	T findOne(ID id);
	T create(T data, Event event);
	T merge(T data, Event event);
	Collection<T>  findAll();
	void delete(ID id);

}
