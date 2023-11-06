package com.ksvsofttech.product.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ksvsofttech.product.entities.HolidayMaster;

@Component
public class HolidayValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return HolidayMaster.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors err) {

		ValidationUtils.rejectIfEmpty(err, "holidayCode", "holiday.holidaycode.empty");
		ValidationUtils.rejectIfEmpty(err, "holidayDate", "holiday.holidaydate.empty");
		ValidationUtils.rejectIfEmpty(err, "description", "holiday.description.empty");

	}
}
