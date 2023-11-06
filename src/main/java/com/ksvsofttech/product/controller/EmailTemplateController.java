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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ksvsofttech.product.entities.AuditRecord;
import com.ksvsofttech.product.entities.EmailTemplate;
import com.ksvsofttech.product.service.AuditRecordService;
import com.ksvsofttech.product.service.EmailService;

@Controller
public class EmailTemplateController {
	private static final Logger LOGGER = LogManager.getLogger(EmailTemplateController.class);

	@Autowired
	private EmailService emailService;
	@Autowired
	private AuditRecordService auditRecordService;
	
	/* Display List of IsActive Email */
	@GetMapping(value = "/activeEmailList")
	public String getIsActiveEmail(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			model.addAttribute("listEmail", emailService.getIsActive());
		} catch (Exception e) {
			LOGGER.error("Error occur to display active email list... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed active email list"));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Email Management");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "email/activeemaillist";
	}

	/* Display List of Active Email */
	@GetMapping(value = "/inActiveEmailList")
	public String getInActiveEmail(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			model.addAttribute("listEmail", emailService.getInActive());
		} catch (Exception e) {
			LOGGER.error("Error occur to display inActive email list... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed inActive email list"));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Email Management");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "email/inactiveemaillist";
	}

	@GetMapping(value = "/emailTemplate")
	public String emailTemplate(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		EmailTemplate emailTemplate = new EmailTemplate();
		try {
			model.addAttribute("emailAttribute", emailTemplate);
		} catch (Exception e) {
			LOGGER.error("Error occur to display email registration page... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed email management form"));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Email Management");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "email/newemailtemplate";
	}

	@PostMapping(value = "/saveEmail")
	public String successEmail(@Valid @ModelAttribute("emailAttribute") EmailTemplate emailTemplate,
			BindingResult result, Model model, Device device, RedirectAttributes redirAttrs) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			if (result.hasErrors()) {
				List<ObjectError> allErrors = result.getAllErrors();
				for (ObjectError temp : allErrors) {
					System.out.println(temp);
				}
				return "email/newemailtemplate";
			} else {
				/* Save Email to Database */
				emailService.save(emailTemplate);
				redirAttrs.addFlashAttribute("success", "Registration Completed Successfully");
				return "redirect:/activeEmailList";
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while email registration successfully... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - create new email"));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Email Management");
			auditRecord.setActivityCode("CREATE");
			auditRecordService.save(auditRecord, device);
		}

		return "email/newemailtemplate";
	}
	
	/* Update of Registered Email */
	@GetMapping(value = "/emailUpdate/{id}")
	public ModelAndView emailUpdate(@PathVariable(name = "id") long id, Device device) throws Exception {
		ModelAndView mav = new ModelAndView("email/updateemailtemplate");
		AuditRecord auditRecord = new AuditRecord();
		try {
			EmailTemplate emailTemplate = emailService.getById(id);
			mav.addObject("updateEmailAttribute", emailTemplate);
		} catch (Exception e) {
			LOGGER.error("Error occur while display email edit registered page... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed email id - " + id));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Email Management");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}

		return mav;
	}

	@PostMapping(value = "/updateEmail")
	public String updateEmail(@Valid @ModelAttribute("updateEmailAttribute") EmailTemplate emailTemplate,
			BindingResult result, Model model, Device device, RedirectAttributes redirAttrs) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			if (result.hasErrors()) {
				List<ObjectError> allErrors = result.getAllErrors();
				for (ObjectError temp : allErrors) {
					System.out.println(temp);
				}
				return "email/updateemailtemplate";
			} else {
				/* Save Email to Database */
				emailService.save(emailTemplate);
				redirAttrs.addFlashAttribute("success", "Update Registration Successfully");
				return "redirect:/activeEmailList";
			}
		} catch (Exception e) {
			LOGGER.error(
					"Error occur while update email registration successfully... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - update registered email"));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Email Management");
			auditRecord.setActivityCode("UPDATE");
			auditRecordService.save(auditRecord, device);
		}

		return "email/updateemailtemplate";
	}

	/* Deactivate of Registered Email */
	@GetMapping(value = "/deactiveEmail/{id}")
	public String deactiveEmail(EmailTemplate emailTemplate, @PathVariable(name = "id") long id, Device device)
			throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			emailService.deactiveEmail(emailTemplate);
		} catch (Exception e) {
			LOGGER.error("------Error occur while inactivate email-----" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("No email inactivate" + id);
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - deactivate email id - " + id));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Email Management");
			auditRecord.setActivityCode("DEACTIVATE");
			auditRecordService.save(auditRecord, device);
		}

		return "redirect:/activeEmailList";
	}

	/* Activate of Registered Email */
	@GetMapping(value = "/activeEmail/{id}")
	public String activeEmail(EmailTemplate emailTemplate, @PathVariable(name = "id") long id, Device device)
			throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			emailService.activateEmail(emailTemplate);
		} catch (Exception e) {
			LOGGER.error("------Error occur while activate email-----" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("No email activate" + id);
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - activate email id - " + id));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Email Management");
			auditRecord.setActivityCode("ACTIVATE");
			auditRecordService.save(auditRecord, device);
		}

		return "redirect:/inActiveEmailList";
	}
}
