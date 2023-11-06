package com.ksvsofttech.product.dao.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.dao.PasswordPolicyDao;
import com.ksvsofttech.product.entities.PasswordPolicy;
import com.ksvsofttech.product.repository.PasswordPolicyRepository;

@Repository
public class PasswordPolicyDaoImpl implements PasswordPolicyDao {
	private static final Logger LOGGER = LogManager.getLogger(PasswordPolicyDaoImpl.class);

	@Autowired
	private PasswordPolicyRepository passwordPolicyRepository;

	@Override
	public List<PasswordPolicy> findAll() throws Exception {
		List<PasswordPolicy> passwordPolicies = passwordPolicyRepository.findAll();
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
		Optional<PasswordPolicy> passwordPolicies = passwordPolicyRepository.findById(id);
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
		PasswordPolicy passwordPolicy = passwordPolicyRepository.save(newPasswordPolicy);
		try {
			if (Objects.nonNull(passwordPolicy)) {
				return passwordPolicy;
			}
		} catch (Exception e) {
			LOGGER.error(
					"------Error occur while save password policy record ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist ......");
		}
		return passwordPolicy;
	}
}
