package com.ksvsofttech.product.controller;

import java.util.Date;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.ksvsofttech.product.entities.AuditRecord;
import com.ksvsofttech.product.entities.EmailTemplate;
import com.ksvsofttech.product.entities.EmpBasicDetails;
import com.ksvsofttech.product.entities.ExitActivity;
import com.ksvsofttech.product.service.AuditRecordService;
import com.ksvsofttech.product.service.EmailAndOTPService;
import com.ksvsofttech.product.service.EmailService;
import com.ksvsofttech.product.service.EmpRegistartionService;
import com.ksvsofttech.product.service.ExitActivityService;

@Controller
public class ExitActivityController {
	private static final Logger LOGGER = LogManager.getLogger(ExitActivityController.class);


	@Autowired
	private TemplateEngine templateEngine;

	public ExitActivityController(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private EmailAndOTPService emailAndOTPService;

	@Autowired
	private AuditRecordService auditRecordService;

	@Autowired
	private ExitActivityService exitActivityService;

	@Autowired
	private EmpRegistartionService empRegistartionService;

	/* List Of IsActive Exit Activity */
	@GetMapping(value = "/activeExitActivityList")
	public String activeExitActivityList(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empId = null;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			List<ExitActivity> userDetails = null;
			if (empId != null) {
				userDetails = exitActivityService.isActiveExitActivity(empId);
				if (userDetails != null) {
					model.addAttribute("exitActivityList", userDetails);
					return "exitActivity/activeExitList";
				} else {
					LOGGER.info("Nothing Happen / Error  ::::::");
					return "exitActivity/activeExitList";
				}
			} else {
				LOGGER.info("Nothing Happen / Error  ::::::");
				return "exitActivity/activeExitList";
			}
		} catch (Exception e) {
			LOGGER.error("Error occur to display active Exit Activity list... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed active exit list"));
			auditRecord.setMenuCode("EMPLOYEE MANAGEMENT");
			auditRecord.setSubMenuCode("Exit Activity");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "exitActivity/activeExitList";
	}

	/* List Of InActive Exit Activity */
	@GetMapping(value = "/cancelExitActivityList")
	public String inactiveExitActivityList(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empId = null;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			List<ExitActivity> userDetails = null;
			if (empId != null) {
				userDetails = exitActivityService.cancelExitActivity(empId);
				if (userDetails != null) {
					model.addAttribute("cancelExitActivityList", userDetails);
					return "exitActivity/cancelExitList";
				} else {
					LOGGER.info("Nothing Happen / Error  ::::::");
					return "exitActivity/cancelExitList";
				}
			} else {
				LOGGER.info("Nothing Happen / Error  ::::::");
				return "exitActivity/cancelExitList";
			}
		} catch (Exception e) {
			LOGGER.error("Error occur to display cancel Exit Activity list... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed cancel exit activity list"));
			auditRecord.setMenuCode("EMPLOYEE MANAGEMENT");
			auditRecord.setSubMenuCode("Exit Activity");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "exitActivity/cancelExitList";
	}

	/* Exit Activity Apply Mail Send Project Manager & HR */
	public void sendMailExitActivity() throws Exception {
		String empId;
		EmailTemplate emailTemplate = emailService.getAppliedExitActivity();
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			List<ExitActivity> exitActivity = null;
			if (empId != null) {
				if (emailTemplate != null) {
				exitActivity = exitActivityService.getExitActivityById(empId);
				if (exitActivity != null) {
					Context context = new Context();
					context.setVariable("exitActivityList", exitActivity);
					String body = templateEngine.process("exitActivity/exitActivityMail", context);
					
					
					System.out.println("Sender Mail Id :: :::::::::::" + emailTemplate.getEmailTo());
					String from = emailTemplate.getEmailFrom();
					String to = emailTemplate.getEmailTo();
					String subject = emailTemplate.getEmailSub();
					String cc = emailTemplate.getEmailCc();
					
					System.out.println(" ExitActivity Request Sent ");
					emailAndOTPService.emailSend(from ,to,subject, body, cc);
				}	
				} else {
					System.out.println("Nothing Happen ::::::");
				}
			} else {
				System.out.println("Nothing Happen ::::::");
			}
		} catch (Exception e) {
			LOGGER.error("Error occurring while sending emails to exit activity ..... " + ExceptionUtils.getStackTrace(e));
		}
	}

	/* Exit Activity Home Page */
	@GetMapping(value = "/exitActivity")
	public String exitActivity(@ModelAttribute ExitActivity exitActivity, Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			EmpBasicDetails userDetails = null;
			if (empId != null) {
				userDetails = empRegistartionService.getCurrentUser(empId);
				if (userDetails != null) {
					model.addAttribute("exitList", userDetails);
					model.addAttribute("currentDate", new Date());
				} else {
					LOGGER.info("Nothing Happen / Error  ::::::");
					return "exitActivity/activeExitList";
				}
			} else {
				LOGGER.info("Nothing Happen / Error  ::::::");
				return "exitActivity/activeExitList";
			}
		} catch (Exception e) {
			LOGGER.error("Error occur to get exit page... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed exit activity home page"));
			auditRecord.setMenuCode("EMPLOYEE MANAGEMENT");
			auditRecord.setSubMenuCode("Exit Activity");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "exitActivity/exitActivity";
	}

	/* Save Exit Activity Form Data */
	@PostMapping(value = "/saveExitActivity")
	public String saveExitActivity(@Validated @ModelAttribute ExitActivity exitActivity, BindingResult result,
			Model model, RedirectAttributes redirAttrs, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			EmpBasicDetails userDetails = null;
			if (empId != null) {
				userDetails = empRegistartionService.getCurrentUser(empId);
				if (userDetails != null) {
					model.addAttribute("exitList", userDetails);
					model.addAttribute("currentDate", new Date());

				} else {
					LOGGER.info("Nothing Happen ::::::");
					return "exitActivity/activeExitList";
				}
			} else {
				LOGGER.info("Nothing Happen ::::::");
				return "exitActivity/activeExitList";
			}

			if (result.hasErrors()) {
				List<ObjectError> allErrors = result.getAllErrors();
				for (ObjectError temp : allErrors) {
					System.out.println(temp);
				}
				return "exitActivity/exitActivity";
			} else {
				exitActivityService.saveExitActivity(exitActivity);
				sendMailExitActivity();
				redirAttrs.addFlashAttribute("success", "Data Save Successfully");
				return "redirect:/activeExitActivityList";
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while save exit Activity successfully... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - create exit activity successfully "));
			auditRecord.setMenuCode("EMPLOYEE MANAGEMENT");
			auditRecord.setSubMenuCode("Exit Activity");
			auditRecord.setActivityCode("CREATE");
			auditRecordService.save(auditRecord, device);
		}
		return "exitActivity/exitActivity";
	}

	/* Deactive exit list */
	@GetMapping(value = "/deactivateActivity/{id}")
	public String deactivatActivity(ExitActivity exitActivity, @PathVariable(name = "id") Long id, Device device)
			throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			exitActivityService.cancelById(exitActivity);
		} catch (Exception e) {
			LOGGER.error("Error occur while deactivate registered exit activity... " + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("No exit activity inactivate......" + id);
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - deactivate activity of emp id -" + id));
			auditRecord.setMenuCode("EMPLOYEE MANAGEMENT");
			auditRecord.setSubMenuCode("Exit Activity");
			auditRecord.setActivityCode("DEACTIVATE");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/activeExitActivityList";
	}
	
	/* Display List of Exit Activity to Manager */
	@GetMapping(value = "/appliedExitActivityList")
	public String appliedExitActivity(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			if (empId != null) {
				model.addAttribute("exitActivityList", exitActivityService.getEmpWithManger(empId));
				return "exitActivity/appliedExitActivityList";
			} else {
				System.out.println("No user Found ::::::::::");
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while display applied exit activity list ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed apply emp list"));
			auditRecord.setMenuCode("EMPLOYEE MANAGEMENT");
			auditRecord.setSubMenuCode("Exit Activity");
			auditRecord.setActivityCode("ACCEPT EXIT ACTIVITY LIST");
			auditRecordService.save(auditRecord, device);
		}
		return "exitActivity/appliedExitActivityList";
	}
	
	/* Exit Activity Request Approved by Manager, Mail Send */
	public void approvedExitReq() throws Exception {
		String empId;
		EmailTemplate emailTemplate = emailService.getApprovedExitActivity();
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			List<ExitActivity> exitActivity = null;
			if (empId != null) {
				if (emailTemplate != null) {
				exitActivity = exitActivityService.getExitActivityById(empId);
				if (exitActivity != null) {
					Context context = new Context();
					context.setVariable("approvedExitReqList", exitActivity);
					String body = templateEngine.process("exitActivity/approvedExitActivityMail", context);
					
					String from = emailTemplate.getEmailFrom();
					String to = emailTemplate.getEmailTo();
					String subject = emailTemplate.getEmailSub();
					String cc = emailTemplate.getEmailCc();
					
					System.out.println(" Your Exit Activity Request is Approved ");
					emailAndOTPService.emailSend(from ,to, subject, body,cc);
				}
				} else {
					System.out.println("Nothing Happen ::::::");
				}
			} else {
				System.out.println("Nothing Happen ::::::");
			}
		} catch (Exception e) {
			LOGGER.error("Error occurring while sending mail to employee who exit activity request approved by manager..... " + ExceptionUtils.getStackTrace(e));
		}
	}

	/* Accept Exit Activity by Manager */
	@GetMapping(value = "/acceptExitActivity/{id}")
	public String acceptExitActivity(@ModelAttribute ExitActivity exitActivity, @PathVariable(name = "id") Long id, Model model,
			Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			if (Objects.nonNull(exitActivity)) {
				String status = "Approved";
				String flag = "Y";
				exitActivity.setStatus(status);
				exitActivity.setFlag(flag);
				exitActivityService.acceptStatus(status, flag, id);
				
				approvedExitReq();
			} else {
				System.out.println("Nothing Happen ::::::");
				return "redirect:/appliedExitActivityList";
			}
			model.addAttribute("exitActivityList", exitActivityService.acceptExitActivityById(id));
		} catch (Exception e) {
			LOGGER.error("Error occur while accept exit activity by manager ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - approved exit actvity of id - " + id));
			auditRecord.setMenuCode("EMPLOYEE MANAGEMENT");
			auditRecord.setSubMenuCode("Exit Activity");
			auditRecord.setActivityCode("APPROVED EXIT ACTIVITY");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/appliedExitActivityList";
	}
	
	/* Exit Activity Request Approved by Manager, Mail Send */
	public void rejectExitReq() throws Exception {
		String empId;
		EmailTemplate emailTemplate = emailService.getRejectExitActivity();
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			List<ExitActivity> exitActivity = null;
			if (empId != null) {
				if (emailTemplate != null) {
				exitActivity = exitActivityService.getExitActivityById(empId);
				if (exitActivity != null) {
					Context context = new Context();
					context.setVariable("rejectExitReqList", exitActivity);
					String body = templateEngine.process("exitActivity/rejectExitActivityMail", context);
					
					String from = emailTemplate.getEmailFrom();
					String to = emailTemplate.getEmailTo();
					String subject = emailTemplate.getEmailSub();
					String cc = emailTemplate.getEmailCc();;
					
					System.out.println(" Your ExitActivity Request is Reject ");
					emailAndOTPService.emailSend(from ,to,subject, body,cc);
				}
				} else {
					System.out.println("Nothing Happen ::::::");
				}
			} else {
				System.out.println("Nothing Happen ::::::");
			}
		} catch (Exception e) {
			LOGGER.error("Error occurring while sending mail to employee who exit activity request reject by manager..... " + ExceptionUtils.getStackTrace(e));
		}
	}

	/* Accept Exit Activity by Manager */
	@GetMapping(value = "/rejectExitActivity/{id}")
	public String rejectExitActivity(@ModelAttribute ExitActivity exitActivity, @PathVariable(name = "id") Long id, Model model,
			Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			if (Objects.nonNull(exitActivity)) {
				String status = "Reject";
				String flag = "Y";
				exitActivity.setStatus(status);
				exitActivity.setFlag(flag);
				exitActivityService.acceptStatus(status, flag, id);
				
				rejectExitReq();
			} else {
				System.out.println("Nothing Happen ::::::");
				return "redirect:/appliedExitActivityList";
			}
			model.addAttribute("exitActivityList", exitActivityService.acceptExitActivityById(id));
		} catch (Exception e) {
			LOGGER.error("Error occur while reject exit activity by manager ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - reject exit actvity of id - " + id));
			auditRecord.setMenuCode("EMPLOYEE MANAGEMENT");
			auditRecord.setSubMenuCode("Exit Activity");
			auditRecord.setActivityCode("REJECT EXIT ACTIVITY");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/appliedExitActivityList";
	}
	
	@GetMapping("/totalExitActivity")
	public String totalExitActivity(Model model, Device device) throws Exception {
		List<ExitActivity> exitActivities = exitActivityService.getTotalExitActivity();
		AuditRecord auditRecord = new AuditRecord();
		try {
			if (Objects.nonNull(exitActivities)) {
				System.out.println("TOTAL Exit Activity ::::::::::::: " + exitActivities.toString());
				model.addAttribute("totalExitActivity", exitActivities);
				
			} else {
				System.out.println("Error occuring while get total exit activity employee");
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while get list of total exit activity emp " + ExceptionUtils.getStackTrace(e));
		}  finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed total exit activity"));
			auditRecord.setMenuCode("EMPLOYEE MANAGEMENT");
			auditRecord.setSubMenuCode("Exit Activity");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}

		return "exitActivity/totalExitActivity";
	}
}
