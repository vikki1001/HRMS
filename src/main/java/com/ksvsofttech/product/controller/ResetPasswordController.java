package com.ksvsofttech.product.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ksvsofttech.product.entities.AuditRecord;
import com.ksvsofttech.product.entities.UserMst;
import com.ksvsofttech.product.service.AuditRecordService;
import com.ksvsofttech.product.service.ResetPasswordService;

@Controller
public class ResetPasswordController {
	private static final Logger LOGGER = LogManager.getLogger(ResetPasswordController.class);

	@Autowired
	private ResetPasswordService resetPasswordService;
	@Autowired
	private AuditRecordService auditRecordService;

	@GetMapping(value = "/resetPasswordList")
	public String pwdList(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			model.addAttribute("listUser", resetPasswordService.getAllUsers());
		} catch (Exception e) {
			LOGGER.error("Error occur while display reset password list..." + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed reset password list"));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Reset Password Management");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "passwordpolicy/resetpasswordlist";
	}

	@GetMapping(value = "/resetPassword/{passwordToken}")
	public String showEditProductPage(@PathVariable(name = "passwordToken") String passwordToken, Model model,
			Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			model.addAttribute("userAttribute", resetPasswordService.getUserByToken(passwordToken));
		} catch (Exception e) {
			LOGGER.error("Error occur while display reset password page ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed password token - " + passwordToken));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Reset Password Management");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "passwordpolicy/resetpassword";
	}

	@PostMapping(value = "/resetSuccess")
	public String resetPwdSave(@Valid @ModelAttribute("userAttribute") UserMst user, BindingResult result, Model model,
			Device device, RedirectAttributes redirAttrs) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			if (result.hasErrors()) {
				List<ObjectError> allErrors = result.getAllErrors();
				for (ObjectError temp : allErrors) {
					System.out.println(temp);
				}
				return "passwordpolicy/resetpassword";
			} else {
				/* Save Users to Database */
				resetPasswordService.saveUser(user);
				redirAttrs.addFlashAttribute("success", " Reset Password Successfully.....");
				return "redirect:/resetPasswordList";
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while reset password...." + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - password reset successfully"));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Reset Password Management");
			auditRecord.setActivityCode("RESET PASSWORD");
			auditRecordService.save(auditRecord, device);
		}
		return "passwordpolicy/resetpassword";
	}
}
