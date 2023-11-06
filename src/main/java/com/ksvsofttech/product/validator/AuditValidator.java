package com.ksvsofttech.product.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ksvsofttech.product.entities.AuditRecord;

@Component
public class AuditValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return AuditRecord.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		/*
		 * ValidationUtils.rejectIfEmpty(errors, "end","audit.createdDate.empty");
		 * ValidationUtils.rejectIfEmpty(errors, "start","audit.createdDate.empty");
		 * ValidationUtils.rejectIfEmpty(errors,
		 * "createdDate","audit.createdDate.empty");
		 * ValidationUtils.rejectIfEmpty(errors, "loginId" ,"audit.loginId.empty");
		 */
	}
}
