package com.appdirect.integration.challenge.datastore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.appdirect.integration.challenge.model.Entity;

public abstract class AbstractDataStore<T extends Entity> implements IDataStore<T>{
	

	private static final long serialVersionUID = -3365825896495404170L;
	protected Map<String, T> storeRepository = new HashMap<String, T>();
	
	protected abstract void afterCreate(T data);
	

	@Override
	public T findOne(String uuid) {
		return storeRepository.get(uuid);
	}

	@Override
	public T create(T data) {
		data.setCreatedDate(new Date());
		data.setUpdatedDate(new Date());
		storeRepository.put(data.getUuid(), data);
		afterCreate(data);
		return data;
	}

	@Override
	public T merge(T data) {
		data.setUpdatedDate(new Date());
		storeRepository.put(data.getUuid(), data);
		return data;
	}

	@Override
	public void delete(String uuid) {
		storeRepository.remove(uuid);
	}

}
