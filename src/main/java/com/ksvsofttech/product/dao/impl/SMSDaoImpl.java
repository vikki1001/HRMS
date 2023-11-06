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

import com.ksvsofttech.product.dao.SMSDao;
import com.ksvsofttech.product.entities.SMSTemplate;
import com.ksvsofttech.product.repository.SMSRepository;

@Repository
public class SMSDaoImpl implements SMSDao {
	private static final Logger LOGGER = LogManager.getLogger(SMSDaoImpl.class);

	@Autowired
	public SMSRepository smsRepository;

	@Override
	public SMSTemplate deactiveSMS(SMSTemplate smsTemplate) throws Exception {
		try {
			Optional<SMSTemplate> smsTemplate2 = smsRepository.findById(smsTemplate.getId());
			if (smsTemplate2.isPresent()) {

				SMSTemplate newsmsTemplate = smsTemplate2.get();
				newsmsTemplate.setIsActive(smsTemplate.getIsActive());
				newsmsTemplate.setIsActive("0");

				smsRepository.save(newsmsTemplate);
			} else {
				return smsTemplate;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while activate role------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No Role activate......");
		}
		return smsTemplate;
	}

	@Override
	public SMSTemplate activateSMS(SMSTemplate smsTemplate) throws Exception {
		try {
			Optional<SMSTemplate> smsTemplate2 = smsRepository.findById(smsTemplate.getId());
			if (smsTemplate2.isPresent()) {

				SMSTemplate newsmsTemplate = smsTemplate2.get();
				newsmsTemplate.setIsActive(smsTemplate.getIsActive());
				newsmsTemplate.setIsActive("1");

				smsRepository.save(newsmsTemplate);
			} else {
				return smsTemplate;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while activate role------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No Role activate......");
		}
		return smsTemplate;
	}

	@Override
	public SMSTemplate getById(long id) throws Exception {
		Optional<SMSTemplate> optional = smsRepository.findByid(id);
		SMSTemplate smsTemplate = null;
		try {
			if (optional.isPresent()) {
				smsTemplate = optional.get();
			}
		} catch (Exception e) {
			LOGGER.error("Error Email Role not found for id " + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("Email not found for id :: " + smsTemplate);
		}
		return smsTemplate;
	}

	@Override
	public List<SMSTemplate> getIsActive() throws Exception {
		List<SMSTemplate> smsTemplates = smsRepository.getIsActive();
		try {
			if (!smsTemplates.isEmpty()) {
				return smsTemplates;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while display isactive role list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No role record exist ......");
		}
		return new ArrayList<>();
	}

	@Override
	public List<SMSTemplate> getInActive() throws Exception {
		List<SMSTemplate> smsTemplates = smsRepository.getInActive();
		try {
			if (!smsTemplates.isEmpty()) {
				return smsTemplates;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while display isactive role list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No role record exist ......");
		}
		return new ArrayList<>();
	}

	@Override
	public SMSTemplate save(SMSTemplate smsTemplate) throws Exception {
		String loginId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			loginId = authentication.getName();
			if (smsTemplate.getId() == null) {
				smsTemplate.setIsActive("1");
				smsTemplate.setTenantId("1");
				smsTemplate.setSmsFlag(0);
				smsTemplate.setVersion(0);
				smsTemplate.setCreatedDate(new Date());
				smsTemplate.setCreatedBy(loginId);
				this.smsRepository.save(smsTemplate);
			} else {				
				Optional<SMSTemplate> smsTemplates = smsRepository.findByid(smsTemplate.getId());
				if (smsTemplates.isPresent()) {
					SMSTemplate newsmsTemplate = smsTemplates.get();
					newsmsTemplate.setAdditionalNo(smsTemplate.getAdditionalNo());
					newsmsTemplate.setSmsMsg(smsTemplate.getSmsMsg());
					newsmsTemplate.setLastModifiedDate(new Date());
					newsmsTemplate.setLastModifiedBy(loginId);

					smsRepository.save(newsmsTemplate);
				} else {
					return smsTemplate;
				}
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while save & update role------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No role save or update......");
		}
		return smsTemplate;
	}

}
