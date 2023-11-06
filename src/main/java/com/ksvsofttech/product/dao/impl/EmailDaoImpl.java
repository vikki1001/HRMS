package com.ksvsofttech.product.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.dao.EmailDao;
import com.ksvsofttech.product.entities.EmailTemplate;
import com.ksvsofttech.product.repository.EmailRepository;

@Repository
public class EmailDaoImpl implements EmailDao {
	private static final Logger LOGGER = LogManager.getLogger(EmailDaoImpl.class);

	@Autowired
	public EmailRepository emailRepository;

	@Override
	public EmailTemplate save(EmailTemplate emailTemplate) throws Exception {
		String loginId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			loginId = authentication.getName();
			if (emailTemplate.getId() == null) {
				emailTemplate.setIsActive("1");
				emailTemplate.setTenantId("1");
				emailTemplate.setEmailFlag(0);
				emailTemplate.setVersion(0);
				emailTemplate.setCreatedDate(new Date());
				emailTemplate.setCreatedBy(loginId);
				this.emailRepository.save(emailTemplate);
			} else {
				Optional<EmailTemplate> emailTemplates = emailRepository.findByid(emailTemplate.getId());
				if (emailTemplates.isPresent()) {
					EmailTemplate newEmailTemplate = emailTemplates.get();
					newEmailTemplate.setTemplateName(emailTemplate.getTemplateName());
					newEmailTemplate.setTemplateDesc(emailTemplate.getTemplateDesc());
					newEmailTemplate.setEmailTo(emailTemplate.getEmailTo());
					newEmailTemplate.setEmailCc(emailTemplate.getEmailCc());
					newEmailTemplate.setEmailBcc(emailTemplate.getEmailBcc());
					newEmailTemplate.setEmailSub(emailTemplate.getEmailSub());
					newEmailTemplate.setEmailMsg(emailTemplate.getEmailMsg());
					newEmailTemplate.setLastModifiedDate(new Date());
					newEmailTemplate.setLastModifiedBy(loginId);
					emailRepository.save(newEmailTemplate);
				} else {
					return emailTemplate;
				}
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while save & update role------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No role save or update......");
		}
		return emailTemplate;
	}

	@Override
	public List<EmailTemplate> getIsActive() throws Exception {
		List<EmailTemplate> emailTemplate = emailRepository.getIsActive();
		try {
			if (!emailTemplate.isEmpty()) {
				return emailTemplate;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while display isactive role list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No role record exist ......");
		}
		return new ArrayList<>();
	}

	@Override
	public List<EmailTemplate> getInActive() throws Exception {
		List<EmailTemplate> emailTemplate = emailRepository.getInActive();
		try {
			if (!emailTemplate.isEmpty()) {
				return emailTemplate;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while display isactive role list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No role record exist ......");
		}
		return new ArrayList<>();
	}

	@Override
	public EmailTemplate getById(long id) throws Exception {
		Optional<EmailTemplate> optional = emailRepository.findByid(id);
		EmailTemplate emailTemplate = null;
		try {
			if (optional.isPresent()) {
				emailTemplate = optional.get();
			}
		} catch (Exception e) {
			LOGGER.error("Error Email Role not found for id " + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("Email not found for id :: " + emailTemplate);
		}
		return emailTemplate;
	}

	@Override
	public EmailTemplate deactiveEmail(EmailTemplate emailTemplate) throws Exception {
		try {
			Optional<EmailTemplate> emailtemplate2 = emailRepository.findById(emailTemplate.getId());
			if (emailtemplate2.isPresent()) {

				EmailTemplate newEmailTemplate = emailtemplate2.get();
				newEmailTemplate.setIsActive(emailTemplate.getIsActive());
				newEmailTemplate.setIsActive("0");

				emailRepository.save(newEmailTemplate);
			} else {
				return emailTemplate;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while activate role------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No Role activate......");
		}
		return emailTemplate;
	}

	@Override
	public EmailTemplate activeEmail(EmailTemplate emailTemplate) throws Exception {
		try {
			Optional<EmailTemplate> emailtemplate2 = emailRepository.findById(emailTemplate.getId());
			if (emailtemplate2.isPresent()) {

				EmailTemplate newEmailTemplate = emailtemplate2.get();
				newEmailTemplate.setIsActive(emailTemplate.getIsActive());
				newEmailTemplate.setIsActive("1");

				emailRepository.save(newEmailTemplate);
			} else {
				return emailTemplate;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while activate role------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No Role activate......");
		}
		return emailTemplate;
	}

	@Override
	public EmailTemplate getAppliedLeave() throws Exception {
		EmailTemplate emailTemplate = new EmailTemplate();
		try {
			emailTemplate = emailRepository.getAppliedLeave();
		} catch (Exception e) {
			LOGGER.error("------Error occur while get Applied Leave-----" + ExceptionUtils.getStackTrace(e));
		}
		return emailTemplate;
	}

	@Override
	public EmailTemplate getApprovedLeave() throws Exception {
		EmailTemplate emailTemplate = new EmailTemplate();
		try {
			emailTemplate = emailRepository.getApprovedLeave();
		} catch (Exception e) {
			LOGGER.error("------Error occur while get Approved Leave-----" + ExceptionUtils.getStackTrace(e));
		}
		return emailTemplate;
	}

	@Override
	public EmailTemplate getRejectLeave() throws Exception {
		EmailTemplate emailTemplate = new EmailTemplate();
		try {
			emailTemplate = emailRepository.getRejectLeave();
		} catch (Exception e) {
			LOGGER.error("------Error occur while get Reject Leave-----" + ExceptionUtils.getStackTrace(e));
		}
		return emailTemplate;
	}

	@Override
	public EmailTemplate getAppliedExitActivity() throws Exception {
		EmailTemplate emailTemplate = new EmailTemplate();
		try {
			emailTemplate = emailRepository.getAppliedExitActivity();
		} catch (Exception e) {
			LOGGER.error("------Error occur while Applied for exit activity-----" + ExceptionUtils.getStackTrace(e));
		}
		return emailTemplate;
	}

	@Override
	public EmailTemplate getApprovedExitActivity() throws Exception {
		EmailTemplate emailTemplate = new EmailTemplate();
		try {
			emailTemplate = emailRepository.getApprovedExitActivity();
		} catch (Exception e) {
			LOGGER.error("------Error occur while Approved for exit activity-----" + ExceptionUtils.getStackTrace(e));
		}
		return emailTemplate;
	}

	@Override
	public EmailTemplate getRejectExitActivity() throws Exception {
		EmailTemplate emailTemplate = new EmailTemplate();
		try {
			emailTemplate = emailRepository.getRejectExitActivity();
		} catch (Exception e) {
			LOGGER.error("------Error occur while Reject for exit activity-----" + ExceptionUtils.getStackTrace(e));
		}
		return emailTemplate;
	}

	@Override
	public EmailTemplate getAppliedExpenseReimb() throws Exception {
		EmailTemplate emailTemplate = new EmailTemplate();
		try {
			emailTemplate = emailRepository.getAppliedExpenseReimb();
		} catch (Exception e) {
			LOGGER.error("------Error occur while Applied for Expense Reimbursement-----" + ExceptionUtils.getStackTrace(e));
		}
		return emailTemplate;
	}

	@Override
	public EmailTemplate getApprovedExpenseReimb() throws Exception {
		EmailTemplate emailTemplate = new EmailTemplate();
		try {
			emailTemplate = emailRepository.getApprovedExpenseReimb();
		} catch (Exception e) {
			LOGGER.error("------Error occur while Approved  Expense Reimbursement-----" + ExceptionUtils.getStackTrace(e));
		}
		return emailTemplate;
	}

	@Override
	public EmailTemplate getRejectExpenseReimb() throws Exception {
		EmailTemplate emailTemplate = new EmailTemplate();
		try {
			emailTemplate = emailRepository.getRejectExpenseReimb();
		} catch (Exception e) {
			LOGGER.error("------Error occur while Reject  Expense Reimbursement-----" + ExceptionUtils.getStackTrace(e));
		}
		return emailTemplate;
	}

	@Override
	public EmailTemplate getAppliedAddNewReq() throws Exception {
		EmailTemplate emailTemplate = new EmailTemplate();
		try {
			emailTemplate = emailRepository.getAppliedAddNewReq();
		} catch (Exception e) {
			LOGGER.error("------Error occur while  Applied for Add New Request-----" + ExceptionUtils.getStackTrace(e));
		}
		return emailTemplate;
	}

	@Override
	public EmailTemplate getApprovedAddNewReq() throws Exception {
		EmailTemplate emailTemplate = new EmailTemplate();
		try {
			emailTemplate = emailRepository.getApproveddAddNewReq();
		} catch (Exception e) {
			LOGGER.error("------Error occur while  Approval of Add New Request-----" + ExceptionUtils.getStackTrace(e));
		}
		return emailTemplate;
	}

	@Override
	public EmailTemplate getRejectAddNewReq() throws Exception {
		EmailTemplate emailTemplate = new EmailTemplate();
		try {
			emailTemplate = emailRepository.getRejectAddNewReq();
		} catch (Exception e) {
			LOGGER.error("------Error occur while  Rejection of Add New Request-----" + ExceptionUtils.getStackTrace(e));
		}
		return emailTemplate;
	}

	@Override
	public EmailTemplate getTainingRequest() throws Exception {
		EmailTemplate emailTemplate = new EmailTemplate();
		try {
			emailTemplate = emailRepository.getTainingRequest();
		} catch (Exception e) {
			LOGGER.error("------Error occur while Request for Training Form -----" + ExceptionUtils.getStackTrace(e));
		}
		return emailTemplate;
	}

	@Override
	public EmailTemplate getSelfAppraisal() throws Exception {
		EmailTemplate emailTemplate = new EmailTemplate();
		try {
			emailTemplate = emailRepository.getSelfAppraisal();
		} catch (Exception e) {
			LOGGER.error("------Error occur while Request for Self Appraisal -----" + ExceptionUtils.getStackTrace(e));
		}
		return emailTemplate;
	}
	
	@Override
	public EmailTemplate getRatingByManager() throws Exception {
		EmailTemplate emailTemplate = new EmailTemplate();
		try {
			emailTemplate = emailRepository.getRatingByManager();
		} catch (Exception e) {
			LOGGER.error("------Error occur while Request for Rating by manager -----" + ExceptionUtils.getStackTrace(e));
		}
		return emailTemplate;
	}
	@Override
	public EmailTemplate getRatingByRM() throws Exception {
		EmailTemplate emailTemplate = new EmailTemplate();
		try {
			emailTemplate = emailRepository.getRatingByRM();
		} catch (Exception e) {
			LOGGER.error("------Error occur while Request for Rating by reporting manager -----" + ExceptionUtils.getStackTrace(e));
		}
		return emailTemplate;
	}
}

