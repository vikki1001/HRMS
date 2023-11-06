package com.ksvsofttech.product.controller;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

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
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ksvsofttech.product.entities.AuditRecord;
import com.ksvsofttech.product.entities.UserMst;
import com.ksvsofttech.product.service.AuditRecordService;
import com.ksvsofttech.product.service.EmailAndOTPService;
import com.ksvsofttech.product.service.UserService;

@Controller
public class ForgotPasswordController {
	private static final Logger LOGGER = LogManager.getLogger(ForgotPasswordController.class);

	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private EmailAndOTPService emailAndOTPService;
	@Autowired
	private UserService userService;
	@Autowired
	private AuditRecordService auditRecordService;

	@GetMapping("/forgot")
	public String forgotPwdHomePage(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			System.out.println("Forgot Password Home Page :::::::::::::::::");
		} catch (Exception e) {
			LOGGER.error("Error occur while display forgot password page" + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed forgot password home page"));
			auditRecord.setMenuCode("Forgot Password");
			auditRecord.setSubMenuCode("Forgot Password");
			auditRecord.setActivityCode("FORGOT PASSWORD");
			auditRecordService.save(auditRecord, device);
		}
		return "forgotPassword/forgot";
	}

	@PostMapping("/sendOtp")
	public String sendOtp(@RequestParam("emailId") String emailId, Model model, RedirectAttributes redirAttrs,
			HttpSession session, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			/* Generating OTP of 6 Digit */
			Random random = new Random();
			int randomOTP = random.nextInt(999999);

			List<UserMst> getEmailId = entityManager
					.createQuery("SELECT e FROM UserMst e WHERE e.emailId = ?1 AND e.isActive = 1", UserMst.class)
					.setParameter(1, emailId).getResultList();

			userService.setRandomOTP(randomOTP, emailId);
			session.setAttribute("email", emailId);
			for (UserMst user : getEmailId) {
				if (user.getEmailId().equals(emailId)) {
					/* Write Code for Send OTP to Email */
					String body = "<h1> OTP = " + randomOTP + " </h1>";
					String subject = "OTP From KSVSoft-Tech";
					String to = emailId;
					String from = "springbootmail.2022@gmail.com";
					emailAndOTPService.emailsend(from, to, subject, body);
					redirAttrs.addFlashAttribute("otpSend", "We have send OTP to your email...");
					return "forgotPassword/otp";
				} else {
					return "redirect:/forgot";
				}
			}
			redirAttrs.addFlashAttribute("failed", "This mail id is not found..");
		} catch (Exception e) {
			LOGGER.error("Error occur while display forgot password page" + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - send otp to email id - " + emailId));
			auditRecord.setMenuCode("Forgot Password");
			auditRecord.setSubMenuCode("Forgot Password");
			auditRecord.setActivityCode("SEND OTP");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/forgot";
	}

	@GetMapping("/otp")
	public String otpPage(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
		} catch (Exception e) {
			LOGGER.error("Error occur while display reset password page" + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed otp home page"));
			auditRecord.setMenuCode("Forgot Password");
			auditRecord.setSubMenuCode("Forgot Password");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "forgotPassword/otp";
	}

	@PostMapping("/verifyOtp")
	public String otpPage(@RequestParam("otp") Integer otp, Model model, RedirectAttributes redirAttrs,
			HttpSession session, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			String emailId = (String) session.getAttribute("email");
			List<UserMst> getRandomOTP = entityManager
					.createQuery("SELECT e FROM UserMst e WHERE e.emailId = ?1 AND e.isActive = 1", UserMst.class)
					.setParameter(1, emailId).getResultList();
			for (UserMst userMst : getRandomOTP) {
				if (userMst.getRandomOTP().equals(otp)) {
					redirAttrs.addFlashAttribute("otpSuccess", " OTP Verified Success ...");
					return "forgotPassword/reset";
				} else {
					redirAttrs.addFlashAttribute("otp", "Wrong OTP...");
					return "redirect:/otp";
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while display otp verify page" + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - verified otp successfully"));
			auditRecord.setMenuCode("Forgot Password");
			auditRecord.setSubMenuCode("Forgot Password");
			auditRecord.setActivityCode("OTP");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/otp";
	}

	@GetMapping("/reset")
	public String resetPage(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
		} catch (Exception e) {
			LOGGER.error("Error occur while display reset password page" + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed reset password home page"));
			auditRecord.setMenuCode("Forgot Password");
			auditRecord.setSubMenuCode("Forgot Password");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "forgotPassword/reset";
	}

	@PostMapping("/resetPassword")
	public String resetPassword(@ModelAttribute UserMst userMst, @RequestParam("password") String password,
			@RequestParam("confirmPassword") String confirmPassword, HttpSession session, Model model,
			BindingResult result, RedirectAttributes redirAttrs, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String emailId = (String) session.getAttribute("email");
		try {
			if (result.hasErrors()) {
				List<ObjectError> allErrors = result.getAllErrors();
				for (ObjectError temp : allErrors) {
					System.out.println(temp);
				}
				return "forgotPassword/reset";
			} else if (!Objects.equals(password, confirmPassword)) {
				redirAttrs.addFlashAttribute("pwdNotMatch", "password & confirm password not match");
				return "redirect:/reset";
			} else {
				password = bCryptPasswordEncoder.encode(password);
				String passwordToken = UUID.randomUUID().toString();
				userService.resetPassword(password, passwordToken, emailId);
				redirAttrs.addFlashAttribute("password", "Reset Password Success... Now try to login with new password");
				return "redirect:/login";
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while display reset password" + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - reset password successfully"));
			auditRecord.setMenuCode("Forgot Password");
			auditRecord.setSubMenuCode("Forgot Password");
			auditRecord.setActivityCode("RESET PASSWORD");
			auditRecordService.save(auditRecord, device);
		}
		return "forgotPassword/reset";
	}
}