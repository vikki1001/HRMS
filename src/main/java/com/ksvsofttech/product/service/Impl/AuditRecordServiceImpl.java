package com.ksvsofttech.product.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Service;

import com.ksvsofttech.product.dao.AuditRecordDao;
import com.ksvsofttech.product.entities.AuditRecord;
import com.ksvsofttech.product.entities.UserMst;
import com.ksvsofttech.product.service.AuditRecordService;

@Service
public class AuditRecordServiceImpl implements AuditRecordService {
	private static final Logger LOGGER = LogManager.getLogger(AuditRecordServiceImpl.class);

	@Autowired
	private AuditRecordDao auditRecordDao;

	/* Save & Update Branch */
	public AuditRecord save(AuditRecord auditRecord, Device device) throws Exception {
		try {
			this.auditRecordDao.save(auditRecord, device);
		} catch (Exception e) {
			LOGGER.error("------Error occur while save & update audit record ------" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("Audit record not save :: " + auditRecord);
		}
		return auditRecord;
	}

	@Override
	public List<UserMst> getAllUser() throws Exception {
		List<UserMst> user = auditRecordDao.getAllUser();
		try {
			if (!user.isEmpty()) {
				return user;
			}

		} catch (Exception e) {
			LOGGER.error("------Error occur while display Audit Record list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No audit record exist......");
		}
		return new ArrayList<UserMst>();
	}

	@Override
	public List<AuditRecord> findByfromDateBetweenorloginIdorbranchName(String from, String to, String loginId,
			String branchName) throws Exception {
		List<AuditRecord> auditRecord = auditRecordDao.findByfromDateBetweenorloginIdorbranchName(from, to, loginId,
				branchName);
		try {
			if (auditRecord.isEmpty()) {
				return auditRecord;
			}
		} catch (Exception e) {
			LOGGER.error(
					"------Error occur while find audit record for given id------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No audit record exist for given id....." + loginId);
		}
		return auditRecord;
	}

	@Override
	public List<AuditRecord> findByfromDateBetweenorloginId(String from, String to, String loginId) throws Exception {
		List<AuditRecord> auditRecord = auditRecordDao.findByfromDateBetweenorloginId(from, to, loginId);
		try {

			if (auditRecord.isEmpty()) {
				return auditRecord;
			}
		} catch (Exception e) {
			LOGGER.error(
					"------Error occur while find audit record for given id------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No audit record exist for given id....." + loginId);
		}
		return auditRecord;
	}

	@Override
	public List<AuditRecord> findByfromDateBetweenorroleCode(String from, String to, String roleCode) throws Exception {
		List<AuditRecord> auditRecord = auditRecordDao.findByfromDateBetweenorroleCode(from, to, roleCode);
		try {

			if (auditRecord.isEmpty()) {
				return auditRecord;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while find audit record for given roleCode------"
					+ ExceptionUtils.getStackTrace(e));
			throw new Exception("No audit record exist for given roleCode....." + roleCode);
		}
		return auditRecord;
	}

	@Override
	public List<AuditRecord> findByfromDateBetweenorbranchName(String from, String to, String branchName)
			throws Exception {
		List<AuditRecord> auditRecord = auditRecordDao.findByfromDateBetweenorbranchName(from, to, branchName);
		try {

			if (auditRecord.isEmpty()) {
				return auditRecord;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while find audit record for given roleCode------"
					+ ExceptionUtils.getStackTrace(e));
			throw new Exception("No audit record exist for given roleCode....." + branchName);
		}
		return auditRecord;
	}
}
