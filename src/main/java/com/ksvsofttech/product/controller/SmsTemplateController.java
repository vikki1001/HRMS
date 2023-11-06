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
import com.ksvsofttech.product.entities.SMSTemplate;
import com.ksvsofttech.product.service.AuditRecordService;
import com.ksvsofttech.product.service.SMSService;

@Controller
public class SmsTemplateController {
	private static final Logger LOGGER = LogManager.getLogger(SmsTemplateController.class);

	@Autowired
	private SMSService smsService;
	@Autowired
	private AuditRecordService auditRecordService;

	@GetMapping(value = "/activeSMSList")
	public String getIsActiveSMS(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			model.addAttribute("listSMS", smsService.getIsActive());
		} catch (Exception e) {
			LOGGER.error("Error occur to display active sms list ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed active SMS list"));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Sms Management");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}

		return "sms/activesmslist";
	}

	@GetMapping(value = "/inActiveSMSList")
	public String getInActiveSMS(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			model.addAttribute("listSMS", smsService.getInActive());
		} catch (Exception e) {
			LOGGER.error("Error occur to display inActive sms list ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed inActive SMS list"));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Sms Management");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "sms/inactivesmslist";
	}

	@GetMapping(value = "/SMSTemplate")
	public String smsTemplate(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		SMSTemplate smsTemplate = new SMSTemplate();
		try {
			model.addAttribute("smsAttribute", smsTemplate);
		} catch (Exception e) {
			LOGGER.error("Error occur to open sms registration page... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed sms management form"));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Sms Management");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "sms/newsmstemplate";
	}

	@PostMapping(value = "/saveSMS")
	public String successSMS(@Valid @ModelAttribute("smsAttribute") SMSTemplate smsTemplate, BindingResult result,
			Model model, Device device, RedirectAttributes redirAttrs) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			if (result.hasErrors()) {
				List<ObjectError> allErrors = result.getAllErrors();
				for (ObjectError temp : allErrors) {
					System.out.println(temp);
				}
				return "email/newsmstemplate";
			} else {
				/* Save Sms to Database */
				smsService.save(smsTemplate);
				redirAttrs.addFlashAttribute("success", "Registration Completed Successfully");
				return "redirect:/activeSMSList";
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while sms registration successfully... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - create sms successfully"));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Sms Management");
			auditRecord.setActivityCode("CREATE");
			auditRecordService.save(auditRecord, device);
		}
		return "sms/newsmstemplate";
	}

	@GetMapping(value = "/SMSUpdate/{id}")
	public ModelAndView emailUpdate(@PathVariable(name = "id") long id, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		ModelAndView mav = new ModelAndView("sms/updatesmstemplate");
		try {
			SMSTemplate smsTemplate = smsService.getById(id);
			mav.addObject("updateSMSAttribute", smsTemplate);
		} catch (Exception e) {
			LOGGER.error("Error occur while display edit registered page ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed sms id " + id));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Sms Management");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return mav;
	}

	@PostMapping(value = "/updateSMS")
	public String updateEmail(@Valid @ModelAttribute("updateSMSAttribute") SMSTemplate smsTemplate,
			BindingResult result, Model model, Device device, RedirectAttributes redirAttrs) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			if (result.hasErrors()) {
				List<ObjectError> allErrors = result.getAllErrors();
				for (ObjectError temp : allErrors) {
					System.out.println(temp);
				}
				return "sms/updatesmstemplate";
			} else {
				/* Save Sms to Database */
				smsService.save(smsTemplate);
				redirAttrs.addFlashAttribute("success", "Update Registration Successfully");
				return "redirect:/activeSMSList";
			}
		} catch (Exception e) {
			LOGGER.error(
					"Error occur while update sms registration successfully... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - update registered sms successfully"));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Sms Management");
			auditRecord.setActivityCode("UPDATE");
			auditRecordService.save(auditRecord, device);
		}
		return "sms/updatesmstemplate";
	}

	@GetMapping(value = "/deactiveSMS/{id}")
	public String deactiveSMS(SMSTemplate smsTemplate, @PathVariable(name = "id") long id, Device device)
			throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			smsService.deactiveSMS(smsTemplate);
		} catch (Exception e) {
			LOGGER.error("------Error occur while inactivate sms -----" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("No Sms inactivate" + id);
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - deactivate sms by sms id - " + id));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Sms Management");
			auditRecord.setActivityCode("DEACTIVATE");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/activeSMSList";
	}

	@GetMapping(value = "/activeSMS/{id}")
	public String activeSMS(SMSTemplate smsTemplate, @PathVariable(name = "id") long id, Device device)
			throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			smsService.activateSMS(smsTemplate);
		} catch (Exception e) {
			LOGGER.error("------Error occur while activate sms -----" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("No Sms activate" + id);
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - activate sms by sms id - " + id));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Sms Management");
			auditRecord.setActivityCode("ACTIVATE");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/inActiveSMSList";
	}

}