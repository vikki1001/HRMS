package com.ksvsofttech.product.controller;

import java.security.Principal;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ksvsofttech.product.entities.AuditRecord;
import com.ksvsofttech.product.entities.UserMst;
import com.ksvsofttech.product.service.AuditRecordService;
import com.ksvsofttech.product.service.ChangePwdService;
import com.ksvsofttech.product.service.UserService;

@Controller
public class ChangePwdController {
	private static final Logger LOGGER = LogManager.getLogger(ChangePwdController.class);

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserService userService;
	@Autowired
	private ChangePwdService changePwdService;
	@Autowired
	private AuditRecordService auditRecordService;

	@GetMapping(value = "/changePassword")
	public String showEditProductPage(Model model, @ModelAttribute UserMst userMst, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String loginId = null;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			loginId = authentication.getName();
			UserMst userDetails = null;
			if (loginId != null) {
				userDetails = userService.getUserDetails(loginId);
				if (userDetails != null) {
					model.addAttribute("userMst", userDetails);
				} else {
					return "dashboard/userDashboard";
				}
			} else {
				return "dashboard/userDashboard";
			}

		} catch (Exception e) {
			LOGGER.error("Error occur while display change password page ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed change password form"));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Change Password");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "passwordpolicy/changepassword";
	}

	@PostMapping(value = "/changePassword")
	public String changePassword(@ModelAttribute UserMst userMst, @RequestParam("password") String password,
			@RequestParam("newPassword") String newPassword, @RequestParam("confirmPassword") String confirmPassword, Principal principal, BindingResult result, Device device,
			RedirectAttributes redirAttrs) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String loginId = principal.getName();
		UserMst currentUser = changePwdService.getUserByLoginId(loginId);
		try {
			if (this.bCryptPasswordEncoder.matches(password, currentUser.getPassword())) {
				currentUser.setPassword(this.bCryptPasswordEncoder.encode(newPassword));
				currentUser.setPasswordToken(UUID.randomUUID().toString());
				this.changePwdService.changePwd(currentUser);
				redirAttrs.addFlashAttribute("notMatch", "Old Password not Match..");
				System.err.println("Old Password not match form db ::::::::::::");
				
				return "redirect:/changePassword";
			} else if (!Objects.equals(newPassword, confirmPassword)) {
				redirAttrs.addFlashAttribute("pwdNotMatch", "new Password & Confirm New Password not match");
				System.err.println("new Password & Confirm New Password not match:::::::::: ");
				return "redirect:/changePassword";
			} else {
				Optional<UserMst> optional = userService.findLoginByLoginId(loginId);
				if(optional.isPresent()) {
					UserMst newUserMst = optional.get();
					newUserMst.setPassword(this.bCryptPasswordEncoder.encode(newPassword));
					newUserMst.setPasswordToken(UUID.randomUUID().toString());
					userService.saveOrUpdateUser(newUserMst);
					redirAttrs.addFlashAttribute("success", "Change Password Successfully");
					return "redirect:/changePassword";
				} else {
					System.err.println("Password not Change :::::::::::::::::");
					//do nothing
				}
				
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while change password  ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - change password successfully "));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Change Password");
			auditRecord.setActivityCode("UPDATE");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/changePassword";

	}
}