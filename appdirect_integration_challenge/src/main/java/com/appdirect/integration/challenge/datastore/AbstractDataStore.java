package com.appdirect.integration.challenge.datastore;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.appdirect.integration.challenge.data.Event;
import com.appdirect.integration.challenge.model.Entity;

public abstract class AbstractDataStore<T extends Entity, ID> implements IDataStore<T, ID>{
	

	private static final long serialVersionUID = -3365825896495404170L;
	protected Map<ID, T> storeRepository = new HashMap<ID, T>();
	protected AtomicInteger atomicInteger = new AtomicInteger(0);
	
	protected void beforeCreate(T data){
	}
	protected void afterCreate(T data){
	}

	@Override
	public T findOne(ID id) {
		return storeRepository.get(id);
	}

	@Override
	public T create(T data, Event event) {
		data.setCreatedDate(new Date());
		data.setUpdatedDate(new Date());
		data.setCreatedBy(getUserName(event));
		data.setUpdatedBy(getUserName(event));
		data.setId(atomicInteger.addAndGet(1));
		beforeCreate(data);
		storeRepository.put(getIdentifer(data), data);
		afterCreate(data);
		return data;
	}

	@Override
	public T merge(T data, Event event) {
		data.setUpdatedDate(new Date());
		data.setCreatedBy(getUserName(event));
		storeRepository.put(getIdentifer(data), data);
		return data;
	}
	
	@Override
	public void delete(ID id) {
		storeRepository.remove(id);
	}
	
	@Override
	public Collection<T> findAll(){
		return storeRepository.values();
	}
	
	private String getUserName(Event event){
		return event.getCreator().getEmail();
	}

}
