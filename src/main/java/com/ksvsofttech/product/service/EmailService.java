package com.ksvsofttech.product.service;

import java.util.List;

import com.ksvsofttech.product.entities.EmailTemplate;

public interface EmailService {

	public EmailTemplate deactiveEmail(EmailTemplate emailTemplate) throws Exception;

	public EmailTemplate activateEmail(EmailTemplate emailTemplate) throws Exception;

	public List<EmailTemplate> getIsActive() throws Exception;

	public EmailTemplate getById(long id) throws Exception;

	public List<EmailTemplate> getInActive() throws Exception;

	public void save(EmailTemplate emailTemplate) throws Exception;

	public EmailTemplate getAppliedLeave() throws Exception;

	public EmailTemplate getApprovedLeave() throws Exception;

	public EmailTemplate getRejectLeave() throws Exception;

	public EmailTemplate getAppliedExitActivity() throws Exception;

	public EmailTemplate getApprovedExitActivity() throws Exception;

	public EmailTemplate getRejectExitActivity() throws Exception;

	public EmailTemplate getAppliedExpenseReimb() throws Exception;

	public EmailTemplate getApprovedExpenseReimb() throws Exception;

	public EmailTemplate getRejectExpenseReimb() throws Exception;

	public EmailTemplate getAppliedAddNewReq() throws Exception;

	public EmailTemplate getApprovedAddNewReq() throws Exception;

	public EmailTemplate getRejectAddNewReq() throws Exception;

	public EmailTemplate getTainingRequest() throws Exception;

	public EmailTemplate getSelfAppraisal() throws Exception;

	public EmailTemplate getRatingByManager() throws Exception;

	public EmailTemplate getRatingByRM()  throws Exception;

	

	
}
