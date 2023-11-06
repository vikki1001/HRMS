package com.ksvsofttech.product.service;

import java.util.List;

import com.ksvsofttech.product.entities.SMSTemplate;

public interface SMSService {

	
	  public SMSTemplate deactiveSMS(SMSTemplate smsTemplate) throws Exception;
	 
	  public SMSTemplate activateSMS(SMSTemplate smsTemplate) throws Exception;
	  
	  public List<SMSTemplate> getIsActive() throws Exception;
	  
	  public SMSTemplate getById(long id)throws Exception;
	  
	  public List<SMSTemplate> getInActive() throws Exception;
	 
	  public void save(SMSTemplate smsTemplate) throws Exception;
}
