package com.ksvsofttech.product.service;

import java.util.List;
import java.util.Optional;

import com.ksvsofttech.product.entities.PasswordPolicy;

public interface PasswordPolicyService {

	public List<PasswordPolicy> findAll() throws Exception;

	public Optional<PasswordPolicy> findById(Long id) throws Exception;

	public PasswordPolicy savePasswordPolicy(PasswordPolicy newPasswordPolicy) throws Exception;

}
