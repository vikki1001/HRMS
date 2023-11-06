package com.ksvsofttech.product.service;

public interface EmailAndOTPService {

	public void emailSend(String from, String to, String subject, String body ,String cc) throws Exception;
	
	public void emailsend(String from, String to, String subject, String body) throws Exception;
	
	
}
