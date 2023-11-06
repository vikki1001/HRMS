package com.ksvsofttech.product.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ksvsofttech.product.dao.SMSDao;
import com.ksvsofttech.product.entities.SMSTemplate;
import com.ksvsofttech.product.service.SMSService;

@Service
@Transactional
public class SMSServiceImpl implements SMSService {
	private static final Logger LOGGER = LogManager.getLogger(SMSServiceImpl.class);

	@Autowired
	private SMSDao smsDao;

	@Override
	public SMSTemplate deactiveSMS(SMSTemplate smsTemplate) throws Exception {
		try {
			 smsDao.deactiveSMS(smsTemplate);
			} catch (Exception e) {
				LOGGER.error("------Error occur while deactive role-----" + ExceptionUtils.getStackTrace(e));
				throw new RuntimeException("No Role deactivate" + smsTemplate);
			}
			return smsTemplate;
	}

	@Override
	public SMSTemplate activateSMS(SMSTemplate smsTemplate) throws Exception {
		try {
			 smsDao.activateSMS(smsTemplate);
			} catch (Exception e) {
				LOGGER.error("------Error occur while deactive role-----" + ExceptionUtils.getStackTrace(e));
				throw new RuntimeException("No Role deactivate" + smsTemplate);
			}
			return smsTemplate;
	}

	@Override
	public SMSTemplate getById(long id) throws Exception {
		SMSTemplate smsTemplate = new SMSTemplate();
		try {
			smsTemplate = smsDao.getById(id);
		} catch (Exception e) {
			LOGGER.error("------Error while role not found for id------" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("No role exist for given id......" + smsTemplate);
		}
		return smsTemplate;
	}

	@Override
	public void save(SMSTemplate smsTemplate) throws Exception {
		try {
			this.smsDao.save(smsTemplate);
		} catch (Exception e) {
			LOGGER.error("------Error occur while save & update role------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No role save & update......" + smsTemplate);
		}
	}

	@Override
	public List<SMSTemplate> getIsActive() throws Exception {
		List<SMSTemplate> smsTemplates = new ArrayList<>();
		try {
			smsTemplates = smsDao.getIsActive();
		} catch (Exception e) {
			LOGGER.error("------Error occur while display isactive role list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No isactive role record exist......" + smsTemplates);
		}
		return smsTemplates;
	}

	@Override
	public List<SMSTemplate> getInActive() throws Exception {
		List<SMSTemplate> smsTemplates = new ArrayList<>();
		try {
			smsTemplates = smsDao.getInActive();
		} catch (Exception e) {
			LOGGER.error("------Error occur while display isactive role list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No isactive role record exist......" + smsTemplates);
		}
		return smsTemplates;
	}
	
}
