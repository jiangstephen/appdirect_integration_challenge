package com.appdirect.integration.challenge.datastore;

import java.util.Date;

import org.springframework.beans.factory.InitializingBean;

import com.appdirect.integration.challenge.data.EditionType;
import com.appdirect.integration.challenge.model.Subscriber;

public class SubscriberStore extends AbstractDataStore<Subscriber, String> implements InitializingBean {


	private static final long serialVersionUID = 1L;
	
	public Subscriber findByAccountIdentifier(String accountIdentifer){
		for(Subscriber subscriber: this.storeRepository.values()){
			if(subscriber.getAccountIdentifier().equals(accountIdentifer)){
				return subscriber;
			}
		}
		return null;
	}

	@Override
	protected void beforeCreate(Subscriber data) {
		data.setAccountIdentifier(data.getFirstName()+"_"+data.getLastName());
	}

	@Override
	public String getIdentifer(Subscriber data) {
		return data.getAccountIdentifier();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Subscriber subscriber = new Subscriber();
		subscriber.setAccountIdentifier("jiangstephen");
		subscriber.setCompanyUuid("d15bb36e-5fb5-11e0-8c3c-00262d2cda03");
		subscriber.setCreatedBy("jiangstephen@hotmail.com");
		subscriber.setCreatedDate(new Date());
		subscriber.setEditionCode(EditionType.BASIC);
		subscriber.setFirstName("Stephen");
		subscriber.setId(atomicInteger.addAndGet(1));
		subscriber.setLastName("Jiang");
		subscriber.setUpdatedBy("jiangstephen@hotmail.com");
		this.storeRepository.put(subscriber.getAccountIdentifier(), subscriber);
		subscriber.setUpdatedDate(new Date());
		subscriber = new Subscriber();
		subscriber.setAccountIdentifier("dummy-account");
		subscriber.setCompanyUuid("d15bb36e-5fb5-11e0-8c3c-00262d2cda03");
		subscriber.setCreatedBy("jiangstephen@hotmail.com");
		subscriber.setCreatedDate(new Date());
		subscriber.setEditionCode(EditionType.BASIC);
		subscriber.setFirstName("Stephen");
		subscriber.setId(atomicInteger.addAndGet(1));
		subscriber.setLastName("Jiang");
		subscriber.setUpdatedBy("jiangstephen@hotmail.com");
		subscriber.setUpdatedDate(new Date());
		
		this.storeRepository.put(subscriber.getAccountIdentifier(), subscriber);
		
	}
}
