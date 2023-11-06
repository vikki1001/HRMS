package com.ksvsofttech.product.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ksvsofttech.product.dao.EmailDao;
import com.ksvsofttech.product.entities.EmailTemplate;
import com.ksvsofttech.product.service.EmailService;

@Service
@Transactional
public class EmailServiceImpl implements EmailService {
	private static final Logger LOGGER = LogManager.getLogger(EmailServiceImpl.class);

	@Autowired
	private EmailDao emailDao;

	@Override
	public void save(EmailTemplate emailTemplate) throws Exception {
		try {
			this.emailDao.save(emailTemplate);
		} catch (Exception e) {
			LOGGER.error("------Error occur while save & update role------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No role save & update......" + emailTemplate);
		}
	}

	@Override
	public List<EmailTemplate> getIsActive() throws Exception {
		List<EmailTemplate> emailTemplate = new ArrayList<>();
		try {
			emailTemplate = emailDao.getIsActive();
		} catch (Exception e) {
			LOGGER.error("------Error occur while display isactive role list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No isactive role record exist......" + emailTemplate);
		}
		return emailTemplate;
	}
	
	@Override
	public List<EmailTemplate> getInActive() throws Exception {
		List<EmailTemplate> emailTemplate = new ArrayList<>();
		try {
			emailTemplate = emailDao.getInActive();
		} catch (Exception e) {
			LOGGER.error("------Error occur while deactive role-----" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("No Role deactivate" + emailTemplate);
		}
		return emailTemplate;
	}

	@Override
	public EmailTemplate getById(long id) throws Exception {
		EmailTemplate emailTemplate = new EmailTemplate();
		try {
			emailTemplate = emailDao.getById(id);
		} catch (Exception e) {
			LOGGER.error("------Error while role not found for id------" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("No role exist for given id......" + emailTemplate);
		}
		return emailTemplate;
	}

	@Override
	public EmailTemplate deactiveEmail(EmailTemplate emailTemplate) throws Exception {
		try {
		 emailDao.deactiveEmail(emailTemplate);
		} catch (Exception e) {
			LOGGER.error("------Error occur while deactive role-----" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("No Role deactivate" + emailTemplate);
		}
		return emailTemplate;
	}

	@Override
	public EmailTemplate activateEmail(EmailTemplate emailTemplate) throws Exception {
		try {
			 emailDao.activeEmail(emailTemplate);
		} catch (Exception e) {
			LOGGER.error("------Error occur while deactive role-----" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("No Role deactivate" + emailTemplate);
		}
		return emailTemplate;
	}

	@Override
	public EmailTemplate getAppliedLeave() throws Exception {
		EmailTemplate emailTemplate = new EmailTemplate();
		try {
			emailTemplate = emailDao.getAppliedLeave();
		} catch (Exception e) {
			LOGGER.error("------Error occur while get Applied Leave-----" + ExceptionUtils.getStackTrace(e));
		}
		return emailTemplate;
	}

	@Override
	public EmailTemplate getApprovedLeave() throws Exception {
		EmailTemplate emailTemplate = new EmailTemplate();
		try {
			emailTemplate = emailDao.getApprovedLeave();
		} catch (Exception e) {
			LOGGER.error("------Error occur while get Approved Leave-----" + ExceptionUtils.getStackTrace(e));
		}
		return emailTemplate;
	}

	@Override
	public EmailTemplate getRejectLeave() throws Exception {
		EmailTemplate emailTemplate = new EmailTemplate();
		try {
			emailTemplate = emailDao.getRejectLeave();
		} catch (Exception e) {
			LOGGER.error("------Error occur while get Reject Leave-----" + ExceptionUtils.getStackTrace(e));
		}
		return emailTemplate;
	}

	@Override
	public EmailTemplate getAppliedExitActivity() throws Exception {
		EmailTemplate emailTemplate = new EmailTemplate();
		try {
			emailTemplate = emailDao.getAppliedExitActivity();
		} catch (Exception e) {
			LOGGER.error("------Error occur while applied mail of exit activity to manager-----" + ExceptionUtils.getStackTrace(e));
		}
		return emailTemplate;
	}

	@Override
	public EmailTemplate getApprovedExitActivity() throws Exception {
		EmailTemplate emailTemplate = new EmailTemplate();
		try {
			emailTemplate = emailDao.getApprovedExitActivity();
		} catch (Exception e) {
			LOGGER.error("------Error occur while Approved exit activity to -----" + ExceptionUtils.getStackTrace(e));
		}
		return emailTemplate;
	}

	@Override
	public EmailTemplate getRejectExitActivity() throws Exception {
		EmailTemplate emailTemplate = new EmailTemplate();
		try {
			emailTemplate = emailDao.getRejectExitActivity();
		} catch (Exception e) {
			LOGGER.error("------Error occur while Reject exit activity  -----" + ExceptionUtils.getStackTrace(e));
		}
		return emailTemplate;
	}

	@Override
	public EmailTemplate getAppliedExpenseReimb() throws Exception {
		EmailTemplate emailTemplate = new EmailTemplate();
		try {
			emailTemplate = emailDao.getAppliedExpenseReimb();
		} catch (Exception e) {
			LOGGER.error("------Error occur while Applied for Expense Reimbursement -----" + ExceptionUtils.getStackTrace(e));
		}
		return emailTemplate;
	}

	@Override
	public EmailTemplate getApprovedExpenseReimb() throws Exception {
		EmailTemplate emailTemplate = new EmailTemplate();
		try {
			emailTemplate = emailDao.getApprovedExpenseReimb();
		} catch (Exception e) {
			LOGGER.error("------Error occur while Approved  Expense Reimbursement -----" + ExceptionUtils.getStackTrace(e));
		}
		return emailTemplate;
	}

	@Override
	public EmailTemplate getRejectExpenseReimb() throws Exception {
		EmailTemplate emailTemplate = new EmailTemplate();
		try {
			emailTemplate = emailDao.getRejectExpenseReimb();
		} catch (Exception e) {
			LOGGER.error("------Error occur while Reject  Expense Reimbursement -----" + ExceptionUtils.getStackTrace(e));
		}
		return emailTemplate;
	}

	@Override
	public EmailTemplate getAppliedAddNewReq() throws Exception {
		EmailTemplate emailTemplate = new EmailTemplate();
		try {
			emailTemplate = emailDao.getAppliedAddNewReq();
		} catch (Exception e) {
			LOGGER.error("------Error occur while Applied for Add New Request -----" + ExceptionUtils.getStackTrace(e));
		}
		return emailTemplate;
	}

	@Override
	public EmailTemplate getApprovedAddNewReq() throws Exception {
		EmailTemplate emailTemplate = new EmailTemplate();
		try {
			emailTemplate = emailDao.getApprovedAddNewReq();
		} catch (Exception e) {
			LOGGER.error("------Error occur while Approval of Add New Request -----" + ExceptionUtils.getStackTrace(e));
		}
		return emailTemplate;
	}

	@Override
	public EmailTemplate getRejectAddNewReq() throws Exception {
		EmailTemplate emailTemplate = new EmailTemplate();
		try {
			emailTemplate = emailDao.getRejectAddNewReq();
		} catch (Exception e) {
			LOGGER.error("------Error occur while Rejection of Add New Request -----" + ExceptionUtils.getStackTrace(e));
		}
		return emailTemplate;
	}

	@Override
	public EmailTemplate getTainingRequest() throws Exception {
		EmailTemplate emailTemplate = new EmailTemplate();
		try {
			emailTemplate = emailDao.getTainingRequest();
		} catch (Exception e) {
			LOGGER.error("------Error occur while Request for Training Form -----" + ExceptionUtils.getStackTrace(e));
		}
		return emailTemplate;
	}

	@Override
	public EmailTemplate getSelfAppraisal() throws Exception {
		EmailTemplate emailTemplate = new EmailTemplate();
		try {
			emailTemplate = emailDao.getSelfAppraisal();
		} catch (Exception e) {
			LOGGER.error("------Error occur while Request for Self Appraisal -----" + ExceptionUtils.getStackTrace(e));
		}
		return emailTemplate;
	}

	@Override
	public EmailTemplate getRatingByManager() throws Exception {
		EmailTemplate emailTemplate = new EmailTemplate();
		try {
			emailTemplate = emailDao.getRatingByManager();
		} catch (Exception e) {
			LOGGER.error("------Error occur while Request for Rating by manager -----" + ExceptionUtils.getStackTrace(e));
		}
		return emailTemplate;
	}

	@Override
	public EmailTemplate getRatingByRM() throws Exception {
		EmailTemplate emailTemplate = new EmailTemplate();
		try {
			emailTemplate = emailDao.getRatingByRM();
		} catch (Exception e) {
			LOGGER.error("------Error occur while Request for Rating by reporting manager -----" + ExceptionUtils.getStackTrace(e));
		}
		return emailTemplate;
	}
}
