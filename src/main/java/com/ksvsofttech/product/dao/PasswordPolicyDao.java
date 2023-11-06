package com.ksvsofttech.product.dao;

import java.util.List;
import java.util.Optional;

import com.ksvsofttech.product.entities.PasswordPolicy;

public interface PasswordPolicyDao {

	public List<PasswordPolicy> findAll() throws Exception;

	public Optional<PasswordPolicy> findById(Long id) throws Exception;

	public PasswordPolicy savePasswordPolicy(PasswordPolicy newPasswordPolicy) throws Exception;
}
