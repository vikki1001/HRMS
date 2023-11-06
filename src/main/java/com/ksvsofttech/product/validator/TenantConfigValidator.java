package com.ksvsofttech.product.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ksvsofttech.product.entities.TenantConfig;

@Component
public class TenantConfigValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return TenantConfig.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {

		ValidationUtils.rejectIfEmpty(errors, "country", "tenantConfig.country.empty");
		ValidationUtils.rejectIfEmpty(errors, "currency", "tenantConfig.currency.empty");
		ValidationUtils.rejectIfEmpty(errors, "dateFormat", "tenantConfig.dateFormat.empty");
		ValidationUtils.rejectIfEmpty(errors, "timeFormat", "tenantConfig.timeFormat.empty");
		ValidationUtils.rejectIfEmpty(errors, "sessionTimeOutInMin", "tenantConfig.sessionTimeOutInMin.empty");
		ValidationUtils.rejectIfEmpty(errors, "newCustomerDays", "tenantConfig.newCustomerDays.empty");
		ValidationUtils.rejectIfEmpty(errors, "numberDecimalCount", "tenantConfig.numberDecimalCount.empty");
		ValidationUtils.rejectIfEmpty(errors, "amountLength", "tenantConfig.amountLength.empty");
		ValidationUtils.rejectIfEmpty(errors, "isActive", "tenantConfig.isActive.empty");
		ValidationUtils.rejectIfEmpty(errors, "reportBaseUrl", "tenantConfig.reportBaseUrl.empty");

	}

}
