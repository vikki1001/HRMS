package com.ksvsofttech.product.controller;

import java.util.List;
import java.util.Objects;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.ksvsofttech.product.entities.AuditRecord;
import com.ksvsofttech.product.entities.EmailTemplate;
import com.ksvsofttech.product.entities.TrainingForm;
import com.ksvsofttech.product.service.AuditRecordService;
import com.ksvsofttech.product.service.EmailAndOTPService;
import com.ksvsofttech.product.service.EmailService;
import com.ksvsofttech.product.service.TrainingFormService;

@Controller
public class TrainingFormController {
	private static final Logger LOGGER = LogManager.getLogger(TrainingFormController.class);

	@Autowired
	private TemplateEngine templateEngine;

	public TrainingFormController(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}

	@Autowired
	private EmailAndOTPService emailAndOTPService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private TrainingFormService trainingFormService;
	@Autowired
	private AuditRecordService auditRecordService;

	@GetMapping("/activeTrainingList")
	private String activeList(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String userId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			userId = authentication.getName();
			if (Objects.nonNull(userId)) {
				List<TrainingForm> trainingList = trainingFormService.getActiveList(userId);
				model.addAttribute("trainingList", trainingList);
			} else {
				System.out.println("Current UserId not Get :::::::: " + userId);
			}
		} catch (Exception e) {
			LOGGER.error("Error Occuring While Display Active Training List ::: " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed active training list"));
			auditRecord.setMenuCode("Employee Management");
			auditRecord.setSubMenuCode("Training Form");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "training/activeTrainingList";
	}

	@GetMapping("/inActiveTrainingList")
	private String inActiveList(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String userId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			userId = authentication.getName();
			if (Objects.nonNull(userId)) {
				List<TrainingForm> trainingList = trainingFormService.getInActiveList(userId);
				model.addAttribute("trainingList", trainingList);
			} else {
				System.out.println("Current UserId not Get :::::::: " + userId);
			}
		} catch (Exception e) {
			LOGGER.error("Error Occuring While Display Isactive Training List ::: " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed inactive training list"));
			auditRecord.setMenuCode("Employee Management");
			auditRecord.setSubMenuCode("Training Form");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "training/inActiveTrainingList";
	}

	@GetMapping("/trainingForm")
	private String trainingPage(TrainingForm trainingForm, Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			model.addAttribute("trainingForm", trainingForm);
		} catch (Exception e) {
			LOGGER.error("Error Occuring While Opening Training Home Page ::: " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed training form"));
			auditRecord.setMenuCode("Employee Management");
			auditRecord.setSubMenuCode("Training Form");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "training/trainingForm";
	}

	@PostMapping("/saveTrainingForm")
	private String saveTrainingForm(@ModelAttribute TrainingForm trainingForm, Model model, Device device)
			throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String userId;
		EmailTemplate emailTemplate = emailService.getTainingRequest();
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			userId = authentication.getName();
			if (Objects.nonNull(trainingForm)) {
				trainingFormService.saveUpdate(trainingForm);
				
				/* Training Request Mail Send Project Manager & HR */
				if (emailTemplate != null) {
					Context context = new Context();
					context.setVariable("trainingFormList", trainingFormService.getTrainingReq(userId, trainingForm.getSubject()));
					String body = templateEngine.process("training/trainingFormMail", context);
					String from = emailTemplate.getEmailFrom();
					String to = emailTemplate.getEmailTo();
					String subject = emailTemplate.getEmailSub();
					String cc = emailTemplate.getEmailCc();
					emailAndOTPService.emailSend(from, to, subject, body, cc);
				} else {
					System.out.println("Error occur while sending mail ::::::::::::: ");
				}
			} else {
				System.out.println("Data not Save Successfully");
			}
		} catch (Exception e) {
			LOGGER.error("Error Occuring While Opening Training Home Page ::: " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - create training form successfully"));
			auditRecord.setMenuCode("Employee Management");
			auditRecord.setSubMenuCode("Training Form");
			auditRecord.setActivityCode("CREATE");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/activeTrainingList";
	}

	@GetMapping("/trainingForm/{id}")
	public ModelAndView roleUpdate(@PathVariable("id") Long id, Device device) throws Exception {
		ModelAndView mav = new ModelAndView("training/updateTrainingForm");
		AuditRecord auditRecord = new AuditRecord();
		try {
			TrainingForm trainingForm = trainingFormService.getById(id);
			mav.addObject("trainingForm", trainingForm);
		} catch (Exception e) {
			LOGGER.error("Error occured while Opening Edit Trainig Form Page ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed by id - " + id));
			auditRecord.setMenuCode("Organization");
			auditRecord.setSubMenuCode("Training Request");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return mav;
	}

	@GetMapping("/trainingListForHR")
	private String trainingListForHR(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		List<TrainingForm> trainingList = trainingFormService.getListForHr();
		try {
			model.addAttribute("trainingList", trainingList);
		} catch (Exception e) {
			LOGGER.error("Error Occuring While Display Training List for HR ::: " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed training list for HR"));
			auditRecord.setMenuCode("Organization");
			auditRecord.setSubMenuCode("Training Request");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "training/trainingList";
	}

	@GetMapping("/trainingListForManager")
	private String trainingListForManager(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String userId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			userId = authentication.getName();
			if (Objects.nonNull(userId)) {
				model.addAttribute("trainingList", trainingFormService.getListForManger(userId));
			} else {
				System.out.println("Error occur while display training list for manager");
			}
		} catch (Exception e) {
			LOGGER.error("Error Occuring While Display Training List for Manager ::: " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed training list for Manger"));
			auditRecord.setMenuCode("Organization");
			auditRecord.setSubMenuCode("Training Request");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "training/trainingList";
	}

	/* Deactivate of Training */
	@GetMapping(value = "/deactivetraining/{id}")
	public String deactiveTraining(TrainingForm trainingForm, @PathVariable(name = "id") Long id, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			trainingFormService.getDeactiveTraining(trainingForm);
		} catch (Exception e) {
			LOGGER.error("------Error occur while deactivate training------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("Training not deactivate for this id ...... " + id);
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - deactivate training id - " + id));
			auditRecord.setMenuCode("Organization");
			auditRecord.setSubMenuCode("Training Request");
			auditRecord.setActivityCode("DEACTIVATE");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/activeTrainingList";
	}

	/* Activate of Training User */
	@GetMapping(value = "/activetraining/{id}")
	public String activeTraining(TrainingForm trainingForm, @PathVariable(name = "id") Long id, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			trainingFormService.getActiveTraining(trainingForm);
		} catch (Exception e) {
			LOGGER.error("------Error occur while activate training------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("Training not activate for this id ...... " + id);
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - activate training id - " + id));
			auditRecord.setMenuCode("Organization");
			auditRecord.setSubMenuCode("Training Request");
			auditRecord.setActivityCode("ACTIVATE");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/inActiveTrainingList";
	}

	@GetMapping("/totalTraining")
	public String totalTraining(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		List<TrainingForm> trainingForms = trainingFormService.getTotalTraining();
		try {
			if (Objects.nonNull(trainingForms)) {
				model.addAttribute("totalTrainingForm", trainingForms);				
			} else {
				System.out.println("Error occuring while get total training form");
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while get list of total training form" + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed training list for HR dashboard"));
			auditRecord.setMenuCode("Organization");
			auditRecord.setSubMenuCode("Training Request");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "training/totalTraining";
	}
}