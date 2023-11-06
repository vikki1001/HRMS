package com.ksvsofttech.product.service.Impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksvsofttech.product.dao.PasswordPolicyDao;
import com.ksvsofttech.product.entities.PasswordPolicy;
import com.ksvsofttech.product.service.PasswordPolicyService;

@Service
public class PasswordPolicyServiceImpl implements PasswordPolicyService {
	private static final Logger LOGGER = LogManager.getLogger(PasswordPolicyServiceImpl.class);

	@Autowired
	private PasswordPolicyDao passwordPolicyDao;

	@Override
	public List<PasswordPolicy> findAll() throws Exception {
		List<PasswordPolicy> passwordPolicies = passwordPolicyDao.findAll();
		try {
			if (!passwordPolicies.isEmpty()) {
				return passwordPolicies;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while display records ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist ......");
		}
		return passwordPolicies;
	}

	@Override
	public Optional<PasswordPolicy> findById(Long id) throws Exception {
		Optional<PasswordPolicy> passwordPolicies = passwordPolicyDao.findById(id);
		try {
			if (passwordPolicies.isPresent()) {
				return passwordPolicies;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while find record by id ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist ......" + id);
		}
		return passwordPolicies;
	}

	@Override
	public PasswordPolicy savePasswordPolicy(PasswordPolicy newPasswordPolicy) throws Exception {
		PasswordPolicy passwordPolicy = passwordPolicyDao.savePasswordPolicy(newPasswordPolicy);
		try {
			if (Objects.nonNull(passwordPolicy)) {
				return passwordPolicy;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while save password policy record ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist ......");
		}
		return passwordPolicy;
	}
}
