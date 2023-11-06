package com.ksvsofttech.product.dao.impl;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.dao.AuditRecordDao;
import com.ksvsofttech.product.entities.AuditRecord;
import com.ksvsofttech.product.entities.UserMst;
import com.ksvsofttech.product.repository.AuditRecordRepository;
import com.ksvsofttech.product.repository.UserRepository;

@Repository
public class AuditRecordDaoImpl implements AuditRecordDao {
	private static final Logger LOGGER = LogManager.getLogger(AuditRecordDaoImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuditRecordRepository auditRecordRepository;

	public AuditRecord save(AuditRecord auditRecord, Device device) throws Exception {
		try {

			/* Save device ActionFrom to AuditRecord */
			String Devicetype = "";
			if (device.isNormal()) {
				Devicetype = "Browser";
			} else if (device.isMobile()) {
				Devicetype = "mobile";
			} else if (device.isTablet()) {
				Devicetype = "Tablet";
			}
			auditRecord.setActionFrom(auditRecord.getActionFrom());
			auditRecord.setActionFrom(Devicetype);

			/* Save current user details to AuditRecord */
			String userId = null;
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			userId = authentication.getName();
			UserMst userDetail = null;
			userDetail = userRepository.findByLoginId(userId);
			auditRecord.setLoginId(userDetail.getLoginId());
			auditRecord.setTenantId(userDetail.getTenantId());
			auditRecord.setBranchCode(userDetail.getBaseBranch());
			auditRecord.setRoleCode(userDetail.getMainRole());

			/* Save IPaddress and HostName to Database */
			InetAddress inetAddress = InetAddress.getLocalHost();
			auditRecord.setIpAddr(inetAddress.getHostAddress());
			auditRecord.setHostName(inetAddress.getHostName());

			/* Save AuditRecord to Database */
			auditRecordRepository.save(auditRecord);
		} catch (Exception e) {
			LOGGER.error("------Error occur while save & update AuditRecord------" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("Audit Record not save :: " + auditRecord);
		}
		return auditRecord;
	}

	/* Dropdown userlist in Audit */
	@Override
	public List<UserMst> getAllUser() throws Exception {
		List<UserMst> user = userRepository.findAll();
		try {
			if (!user.isEmpty()) {
				return user;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while display audit Record Users------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No Audit record exist......");
		}
		return new ArrayList<UserMst>();
	}

	/* Search data using fromDate, toDate, loginId & branchName */
	@Override
	public List<AuditRecord> findByfromDateBetweenorloginIdorbranchName(String from, String to, String loginId,
			String branchName) throws Exception {
		try {
			if ("All".equalsIgnoreCase(loginId) && branchName.isEmpty()) {
				return auditRecordRepository.findAllUser(from, to);
			} else if ("All".equalsIgnoreCase(loginId) && !branchName.isEmpty()) {
				return auditRecordRepository.findByfromDateBetweenorbranchName(from, to,branchName);
			} else if ("All".equalsIgnoreCase(branchName) && loginId.isEmpty()) {
				return auditRecordRepository.findAllUser(from, to);
			} else if ("All".equalsIgnoreCase(branchName) && !loginId.isEmpty()) {
				return auditRecordRepository.findByfromDateBetweenorloginId(from, to,loginId);
			} else if (!from.isEmpty() && !to.isEmpty() && !loginId.isEmpty() && !branchName.isEmpty()) {
				return auditRecordRepository.findByfromDateBetweenorloginIdorbranchName(from, to, loginId, branchName);
			} else if (!from.isEmpty() && !to.isEmpty() && !loginId.isEmpty()) {
				return auditRecordRepository.findByfromDateBetweenorloginId(from, to, loginId);
			} else if (!from.isEmpty() && !to.isEmpty() && !branchName.isEmpty()) {
				return auditRecordRepository.findByfromDateBetweenorbranchName(from, to, branchName);
			} else if (!from.isEmpty() && !to.isEmpty()) {
				return auditRecordRepository.findAllUser(from, to);
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while find audit record ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No Audit record exist......" + loginId);
		}
		return new ArrayList<>();
	}

	/* Search data using fromDate, toDate & loginId */
	@Override
	public List<AuditRecord> findByfromDateBetweenorloginId(String from, String to, String loginId) throws Exception {
		List<AuditRecord> auditRecord = auditRecordRepository.findByfromDateBetweenorloginId(from, to, loginId);
		try {
			if (!auditRecord.isEmpty()) {
				return auditRecord;
			}
			if ("All".equalsIgnoreCase(loginId)) {
				return auditRecord = auditRecordRepository.findAllUser(from, to);
			} else if (!from.isEmpty() && !to.isEmpty() && !loginId.isEmpty()) {
				return auditRecord = auditRecordRepository.findByfromDateBetweenorloginId(from, to, loginId);
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while find audit record ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No Audit record exist......" + loginId);
		}
		return auditRecord;
	}

	/* Search data using fromDate, toDate & roleCode */
	@Override
	public List<AuditRecord> findByfromDateBetweenorroleCode(String from, String to, String roleCode) throws Exception {
		List<AuditRecord> auditRecord = auditRecordRepository.findByfromDateBetweenorroleCode(from, to, roleCode);
		try {
			if (!auditRecord.isEmpty()) {
				return auditRecord;
			}
			if ("All".equalsIgnoreCase(roleCode)) {
				return auditRecord = auditRecordRepository.findAllUser(from, to);
			} else if (!from.isEmpty() && !to.isEmpty() && !roleCode.isEmpty()) {
				return auditRecord = auditRecordRepository.findByfromDateBetweenorroleCode(from, to, roleCode);
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while find audit record ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No Audit record exist......" + roleCode);
		}
		return auditRecord;
	}

	/* Search data using fromDate, toDate & branchName */
	@Override
	public List<AuditRecord> findByfromDateBetweenorbranchName(String from, String to, String branchName)
			throws Exception {
		List<AuditRecord> auditRecord = auditRecordRepository.findByfromDateBetweenorbranchName(from, to, branchName);
		try {
			if (auditRecord.isEmpty()) {
				return auditRecord;
			}
			if ("All".equalsIgnoreCase(branchName)) {
				auditRecord = auditRecordRepository.findAllUser(from, to);
			} else {
				auditRecord = auditRecordRepository.findByfromDateBetweenorbranchName(from, to, branchName);
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while find audit record ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No Audit record exist......" + branchName);
		}
		return auditRecord;
	}
}
