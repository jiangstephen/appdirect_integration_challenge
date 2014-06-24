package com.appdirect.integration.challenge.datastore;

import java.io.Serializable;

import com.appdirect.integration.challenge.model.Entity;

public interface IDataStore<T extends Entity> extends Serializable {
	
	T findOne(String uuid);
	T create(T data);
	T merge(T data);
	void delete(String uuid);

}
