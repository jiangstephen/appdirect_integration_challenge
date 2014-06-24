package com.appdirect.integration.challenge.eventhandler;

import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;

import com.appdirect.integration.challenge.data.Creator;
import com.appdirect.integration.challenge.data.Event;
import com.appdirect.integration.challenge.data.EventResult;
import com.appdirect.integration.challenge.model.Subscriber;

public class OrderEventHandler extends AbstractEventHandler<EventResult>{


	@Override
	public EventResult handleEvent(Event event) {
		EventResult eventResult = null;
		Creator creator = event.getCreator();
		if(subscriberStore.findOne(creator.getUuid())==null){
			Subscriber subscriber = new Subscriber();
			subscriber.setFirstName(creator.getFirstName());
			subscriber.setLastName(creator.getLastName());
			subscriber.setCompanyUuid(event.getPayload().getCompany().getUuid());
			subscriber.setEditionCode(event.getPayload().getOrder().getEditionCode());
			subscriberStore.create(subscriber, event);	
			eventResult = new EventResult("Account created for "+creator.getFirstName() + " "+creator.getLastName(),subscriber.getAccountIdentifier());
			
		} else {
			eventResult = new EventResult("Account already exists for "+creator.getFirstName()+" "+creator.getLastName(), ErrorCode.USER_ALREADY_EXISTS);
		}
		if(StringUtils.isNotBlank(event.getReturnUrl())){
			appendEventUrl(event, eventResult);
		}
		return eventResult;
	}
	
	@SuppressWarnings("deprecation")
	private void appendEventUrl(Event event, EventResult eventResult){
		StringBuffer sb = new StringBuffer();
		sb.append(event.getReturnUrl());
		sb.append(String.format("&%s=%s","success", eventResult.isSuccess()));
		sb.append(String.format("&%s=%s","message", URLEncoder.encode(eventResult.getMessage())));
		if(StringUtils.isNotBlank(eventResult.getAccountIdentifier())){
			sb.append(String.format("&%s=%s","accountIdentifier", eventResult.getAccountIdentifier()));
		}
		if(eventResult.getErrorCode()!=null){
			sb.append(String.format("&%s=%s","errorCode", eventResult.getErrorCode()));
		}
		event.setReturnUrl(sb.toString());
	}

}
