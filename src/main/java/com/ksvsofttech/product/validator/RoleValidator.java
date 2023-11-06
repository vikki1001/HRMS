package com.ksvsofttech.product.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ksvsofttech.product.entities.RoleMst;

@Component
public class RoleValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return RoleMst.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors err) {

		ValidationUtils.rejectIfEmpty(err, "roleCode", "role.rolecode.empty");
		ValidationUtils.rejectIfEmpty(err, "roleName", "role.rolename.empty");
		ValidationUtils.rejectIfEmpty(err, "level", "role.level.empty");

		/*
		 * ValidationUtils.rejectIfEmptyOrWhitespace(err, "roleCode",
		 * "role.rolecode.empty"); try { if
		 * (roleService.findByRoleCode(rolemst.getRoleCode()) != null) {
		 * err.rejectValue("roleCode", "Duplicate.roleForm.rolecode"); } } catch
		 * (Exception e) { e.printStackTrace(); }
		 */
	}

}
