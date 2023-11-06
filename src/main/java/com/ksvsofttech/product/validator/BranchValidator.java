package com.ksvsofttech.product.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ksvsofttech.product.entities.BranchMst;

@Component
public class BranchValidator implements Validator {

	private static final Pattern EMAIL_REGEX = Pattern
			.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
	private static final Pattern PHONE_REGEX = Pattern.compile("^[0-9]{10}$");

	@Override
	public boolean supports(Class<?> clazz) {
		return BranchMst.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		BranchMst branchMst = (BranchMst) target;

		if (branchMst.getEmail() != null && !EMAIL_REGEX.matcher(branchMst.getEmail()).matches()) {
			errors.rejectValue("email", "branchMst.email.invalid");
		}

		if (branchMst.getMobile() != null && !PHONE_REGEX.matcher(branchMst.getMobile()).matches()) {
			errors.rejectValue("mobile", "branchMst.mobile.invalid");
		}

		ValidationUtils.rejectIfEmpty(errors, "branchCode", "branchMst.branchCode.empty");
		ValidationUtils.rejectIfEmpty(errors, "branchRefCode", "branchMst.branchRefCode.empty");
		ValidationUtils.rejectIfEmpty(errors, "branchName", "branchMst.branchName.empty");
		ValidationUtils.rejectIfEmpty(errors, "branchType", "branchMst.branchType.empty");
		ValidationUtils.rejectIfEmpty(errors, "postalCode", "branchMst.postalCode.empty");
		ValidationUtils.rejectIfEmpty(errors, "address1", "branchMst.address1.empty");
		ValidationUtils.rejectIfEmpty(errors, "address2", "branchMst.address2.empty");
		ValidationUtils.rejectIfEmpty(errors, "address3", "branchMst.address3.empty");
		ValidationUtils.rejectIfEmpty(errors, "country", "branchMst.country.empty");
		ValidationUtils.rejectIfEmpty(errors, "state", "branchMst.state.empty");
		ValidationUtils.rejectIfEmpty(errors, "city", "branchMst.city.empty");
		ValidationUtils.rejectIfEmpty(errors, "contactPerson", "branchMst.contactPerson.empty");
		ValidationUtils.rejectIfEmpty(errors, "telephoneno", "branchMst.telephoneno.empty");
		ValidationUtils.rejectIfEmpty(errors, "fax", "branchMst.fax.empty");

	}

}
