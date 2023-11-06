package com.ksvsofttech.product.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ksvsofttech.product.entities.AuditRecord;
import com.ksvsofttech.product.entities.PasswordPolicy;
import com.ksvsofttech.product.service.AuditRecordService;
import com.ksvsofttech.product.service.PasswordPolicyService;

@Controller
public class PasswordPolicyController {
	private static final Logger LOGGER = LogManager.getLogger(PasswordPolicyController.class);

	@Autowired
	private AuditRecordService auditRecordService;

	@Autowired
	private PasswordPolicyService passwordPolicyService;

	@GetMapping(value = "/passwordPolicy")
	public String form(@ModelAttribute("passwordPolicy") PasswordPolicy passwordPolicy, Model model, Device device)
			throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		List<PasswordPolicy> passwordPolicyList = passwordPolicyService.findAll();
		try {
			model.addAttribute("passwordPolicy", passwordPolicyList);
		} catch (Exception e) {
			LOGGER.error("Error occur while display password policy page..." + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed password policy management form"));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Password Policy Form");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "passwordpolicy/passwordpolicy";
	}

	@PostMapping(value = "/savePasswordPolicy")
	public String saveForm(@ModelAttribute("passwordPolicy") PasswordPolicy passwordPolicy, Model model, Device device,
			RedirectAttributes redirAttrs, Long id) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String loginId;
		id = (long) 1;		
		List<PasswordPolicy> passwordPolicyList = passwordPolicyService.findAll();
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			loginId = authentication.getName();
			
			model.addAttribute("passwordPolicy", passwordPolicyList);
			Optional<PasswordPolicy> optional = passwordPolicyService.findById(id);
			if (optional.isPresent()) {
				PasswordPolicy newPasswordPolicy = optional.get();
				newPasswordPolicy.setMinimumPasswordLength(passwordPolicy.getMinimumPasswordLength());
				newPasswordPolicy.setMaximumPasswordLength(passwordPolicy.getMaximumPasswordLength());
				newPasswordPolicy.setMinimumAlphabetsLength(passwordPolicy.getMinimumAlphabetsLength());
				newPasswordPolicy.setSameConsCharNotAllowedCount(passwordPolicy.getSameConsCharNotAllowedCount());
				newPasswordPolicy.setMinimumDigits(passwordPolicy.getMinimumDigits());
				newPasswordPolicy.setMinimumSpecialCharacter(passwordPolicy.getMinimumSpecialCharacter());
				newPasswordPolicy.setPasswordValidateDays(passwordPolicy.getPasswordValidateDays());
				newPasswordPolicy.setLastPasswordNotAllowedCount(passwordPolicy.getLastPasswordNotAllowedCount());
				newPasswordPolicy.setMaximumBadLoginCount(passwordPolicy.getMaximumBadLoginCount());
				newPasswordPolicy.setIsUserIdAllowed(passwordPolicy.getIsUserIdAllowed());
				newPasswordPolicy.setLastModifiedBy(loginId);
				newPasswordPolicy.setLastModifiedDate(new Date());
				newPasswordPolicy.setIsActive("1");
				passwordPolicyService.savePasswordPolicy(newPasswordPolicy);
			} else {
				System.out.println("Do Nothing ..............");
			}
			redirAttrs.addFlashAttribute("success", "Update Registration Successfully");
		} catch (Exception e) {
			LOGGER.error("Error occur while display save/update password policy..." + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - save/update password policy management"));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Password Policy Form");
			auditRecord.setActivityCode("SAVE/UPDATE");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/passwordPolicy";
	}
}
