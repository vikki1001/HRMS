package com.ksvsofttech.product.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ksvsofttech.product.entities.LeaveMst;

@Component
public class LeaveValidator implements Validator {
	private static final Pattern PHONE_REGEX = Pattern.compile("^[0-9]{10}$");
	
	@Override
	public boolean supports(Class<?> clazz) {
		return LeaveMst.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors err) {

		LeaveMst leaveMst = (LeaveMst) obj;

		if (leaveMst.getPhoneNo() != null && !PHONE_REGEX.matcher(leaveMst.getPhoneNo()).matches()) {
			err.rejectValue("phoneNo", "leave.phoneNo.invalid");
		}
		
		ValidationUtils.rejectIfEmpty(err, "leaveType", "leave.leaveType.empty");
		ValidationUtils.rejectIfEmpty(err, "fromDate", "leave.fromDate.empty");
		ValidationUtils.rejectIfEmpty(err, "toDate", "leave.toDate.empty");
		ValidationUtils.rejectIfEmpty(err, "leaveDetails", "leave.leaveDetails.empty");
		ValidationUtils.rejectIfEmpty(err, "projectCode", "leave.projectCode.empty");
		ValidationUtils.rejectIfEmpty(err, "addrDuringLeave", "leave.addrDuringLeave.empty");
		
	}
}
