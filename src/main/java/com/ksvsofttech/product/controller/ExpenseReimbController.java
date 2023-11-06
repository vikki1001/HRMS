package com.ksvsofttech.product.controller;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.ksvsofttech.product.entities.AuditRecord;
import com.ksvsofttech.product.entities.EmailTemplate;
import com.ksvsofttech.product.entities.EmpBasicDetails;
import com.ksvsofttech.product.entities.ExpenseReimb;
import com.ksvsofttech.product.service.AuditRecordService;
import com.ksvsofttech.product.service.EmailAndOTPService;
import com.ksvsofttech.product.service.EmailService;
import com.ksvsofttech.product.service.EmpRegistartionService;
import com.ksvsofttech.product.service.ExpenseReimbService;

@Controller
public class ExpenseReimbController {
	private static final Logger LOGGER = LogManager.getLogger(ExpenseReimbController.class);

	@Autowired
	private TemplateEngine templateEngine;

	public ExpenseReimbController(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private EmailAndOTPService emailAndOTPService;

	@Autowired
	private ExpenseReimbService expenseReimbService;

	@Autowired
	private AuditRecordService auditRecordService;

	@Autowired
	private EmpRegistartionService empRegistartionService;

	/* List of Active Expense Reimbursement */
	@GetMapping(value = "/activeExpenseReimbList")
	public String activeExpenseReimbList(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empId = null;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			LOGGER.info(authentication.getName());
			List<ExpenseReimb> userDetails = null;
			if (empId != null) {
				userDetails = expenseReimbService.activeExpenseReimb(empId);
				if (userDetails != null) {
					model.addAttribute("expenseList", userDetails);

					return "expenseReimb/activeExpenseList";
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error occur to display active expense list... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed active expense list"));
			auditRecord.setMenuCode("Employee Management");
			auditRecord.setSubMenuCode("Expense Reimb");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "expenseReimb/activeExpenseList";
	}

	/* List of Cancel Expense Reimbursement */
	@GetMapping(value = "/cancelExpenseReimbList")
	public String cancelExpenseReimbList(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empId = null;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			LOGGER.info(authentication.getName());
			List<ExpenseReimb> userDetails = null;
			if (empId != null) {
				userDetails = expenseReimbService.cancelExpenseReimb(empId);
				if (userDetails != null) {
					model.addAttribute("expenseList", userDetails);
					return "expenseReimb/cancelExpenseList";
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error occur to display inactive expense list... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed active expense list"));
			auditRecord.setMenuCode("Employee Management");
			auditRecord.setSubMenuCode("Expense Reimb");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "expenseReimb/cancelExpenseList";
	}

	/* Expense Reimb Apply Mail Send Project Manager & HR */
	public void sendMailExpenseReimb() throws Exception {
		String empId;
		EmailTemplate emailTemplate = emailService.getAppliedExpenseReimb();
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			List<ExpenseReimb> expenseReimb = null;
			if (empId != null) {
				expenseReimb = expenseReimbService.getExpenseReimbByEmpId(empId);
				if (expenseReimb != null) {
					if (emailTemplate != null) {
						Context context = new Context();
						context.setVariable("expenseReimbList", expenseReimb);
						String body = templateEngine.process("expenseReimb/expenseReimbMail", context);

						String from = emailTemplate.getEmailFrom();
						String to = emailTemplate.getEmailTo();
						String subject = emailTemplate.getEmailSub();
						String cc = emailTemplate.getEmailCc();

						System.out.println(" Your Expense reimb Mail Sent ");
						emailAndOTPService.emailSend(from ,to, subject, body,cc);
					}
				} else {
					System.out.println("Nothing Happen ::::::");
				}
			} else {
				System.out.println("Nothing Happen ::::::");
			}
		} catch (Exception e) {
			LOGGER.error(
					"Error occurring while sending emails to expense reimb ..... " + ExceptionUtils.getStackTrace(e));
		}
	}

	/* Expense Reimbursement Registration Page */
	@GetMapping(value = "/expenseReimbHome")
	public String expenseReimbHome(@ModelAttribute ExpenseReimb expenseReimb, BindingResult result, Model model,
			Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			EmpBasicDetails userDetails = null;
			if (empId != null) {
				userDetails = empRegistartionService.getCurrentUser(empId);
				if (userDetails != null) {
					model.addAttribute("expenseList", userDetails);
				} else {
					LOGGER.info("Nothing Happen/Error ::::::");
					return "expenseReimb/activeExpenseList";
				}
			} else {
				LOGGER.info("Nothing Happen/Error ::::::");
				return "expenseReimb/activeExpenseList";
			}
		} catch (Exception e) {
			LOGGER.error("Error occur to get expense page... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed active expense reimb list"));
			auditRecord.setMenuCode("Employee Management");
			auditRecord.setSubMenuCode("Expense Reimb");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "expenseReimb/expenseReimb";
	}

	/* Save Expense Reimbursement Data */
	@PostMapping(value = "/saveExpenseReimb")
	public String saveExpenseReimb(@ModelAttribute ExpenseReimb expenseReimb, BindingResult result, Model model,
			@RequestParam("attachment") MultipartFile file, RedirectAttributes redirAttrs, Device device)
			throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			EmpBasicDetails userDetails = null;
			if (empId != null) {
				userDetails = empRegistartionService.getCurrentUser(empId);
				if (userDetails != null) {
					model.addAttribute("expenseList", userDetails);
				} else {
					LOGGER.info("Nothing Happen/Error ::::::");
					return "redirect:/activeExpenseReimbList";
				}
			} else {
				LOGGER.info("Nothing Happen/Error ::::::");
				return "redirect:/activeExpenseReimbList";
			}
			if (Objects.nonNull(expenseReimb)) {
				expenseReimb.setAttachment(file.getBytes());
				expenseReimbService.saveExpenseReimb(expenseReimb);
				sendMailExpenseReimb();
				redirAttrs.addFlashAttribute("success", "Your ExpenseReimb mail Sent Successfully");
				return "redirect:/activeExpenseReimbList";
			} else {
				System.out.println("expenseReimb object is null :::::::::::::::::: " + expenseReimb);
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while save expense Reimbursement successfully... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - create expense successfully"));
			auditRecord.setMenuCode("Employee Management");
			auditRecord.setSubMenuCode("Expense Reimb");
			auditRecord.setActivityCode("CREATE");
			auditRecordService.save(auditRecord, device);
		}
		return "expenseReimb/expenseReimb";

	}

	/* Cancel Registered Expense Reimbursement by Employee */
	@GetMapping(value = "/cancelExpenseReimb/{id}")
	public String deactiveExpense(ExpenseReimb expenseReimb, @PathVariable(name = "id") String empId, Device device)
			throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			expenseReimbService.getCancelById(expenseReimb);
		} catch (Exception e) {
			LOGGER.error("rror occur while deactivate expense.. " + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("No emp deactivate......" + empId);
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - cancel expense by id " + empId));
			auditRecord.setMenuCode("Employee Management");
			auditRecord.setSubMenuCode("Expense Reimb");
			auditRecord.setActivityCode("CANCEL");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/activeExpenseReimbList";
	}

	/* Display List of Expense Reimbursement to Manager */
	@GetMapping(value = "/appliedExpenseReimbList")
	public String appliedExpenseReimbList(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			model.addAttribute("expenseReimbList", expenseReimbService.getEmpWithManger());
		} catch (Exception e) {
			LOGGER.error("Error occur while display applied expense reimb list ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed apply emp list"));
			auditRecord.setMenuCode("Employee Management");
			auditRecord.setSubMenuCode("Expense Reimb");
			auditRecord.setActivityCode("Applied Expense Reimb List");
			auditRecordService.save(auditRecord, device);
		}
		return "expenseReimb/appliedExpenseList";
	}

	/* Expense Reimbursement Request Approved by Manager, Mail Send */
	public void approvedExpenseReimb() throws Exception {
		String empId;
		EmailTemplate emailTemplate = emailService.getApprovedExpenseReimb();
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			List<ExpenseReimb> expenseReimb = null;
			if (empId != null) {
				expenseReimb = expenseReimbService.getExpenseReimbByEmpId(empId);
				if (emailTemplate != null) {
					Context context = new Context();
					context.setVariable("approvedExpenseReimbList", expenseReimb);
					String body = templateEngine.process("expenseReimb/approvedExpenseReimbMail", context);

					String from = emailTemplate.getEmailFrom();
					String to = emailTemplate.getEmailTo();
					String subject = emailTemplate.getEmailSub();
					String cc = emailTemplate.getEmailCc();

					System.out.println(" Expense Reimb Request Approved ");
					emailAndOTPService.emailSend(from ,to, subject, body, cc);
				} else {
					System.out.println("Nothing Happen ::::::");
				}
			} else {
				System.out.println("Nothing Happen ::::::");
			}
		} catch (Exception e) {
			LOGGER.error(
					"Error occurring while sending mail to employee who ExpenseReimb request approved by manager..... "
							+ ExceptionUtils.getStackTrace(e));
		}
	}

	/* Accept Expense Reimbursement by Manager */
	@GetMapping(value = "/acceptExpenseReimb/{id}")
	public String acceptExpenseReimb(@ModelAttribute ExpenseReimb expenseReimb, @PathVariable(name = "id") Long id,
			Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			if (Objects.nonNull(expenseReimb)) {
				String status = "Approved";
				String flag = "Y";
				expenseReimb.setStatus(status);
				expenseReimb.setFlag(flag);
				expenseReimbService.acceptStatus(status, flag, id);

				approvedExpenseReimb();
			} else {
				System.out.println("Nothing Happen ::::::");
				return "redirect:/appliedExitActivityList";
			}
			model.addAttribute("expenseReimbList", expenseReimbService.acceptExpenseReimbById(id));
		} catch (Exception e) {
			LOGGER.error("Error occur while accept Expense Reimb by manager ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - approved expense of id - " + id));
			auditRecord.setMenuCode("Employee Management");
			auditRecord.setSubMenuCode("Expense Reimb");
			auditRecord.setActivityCode("Approved Expense Reimb");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/appliedExpenseReimbList";
	}

	/* Expense Reimbursement Request Approved by Manager, Mail Send */
	public void rejectExpenseReimb() throws Exception {
		String empId;
		EmailTemplate emailTemplate = emailService.getRejectExpenseReimb();
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			List<ExpenseReimb> expenseReimb = null;
			if (empId != null) {
				expenseReimb = expenseReimbService.getExpenseReimbByEmpId(empId);
				if (expenseReimb != null) {
					if (emailTemplate != null) {
						Context context = new Context();
						context.setVariable("rejectExpenseReimbList", expenseReimb);
						String body = templateEngine.process("expenseReimb/rejectExpenseReimbMail", context);

						String from = emailTemplate.getEmailFrom();
						String to = emailTemplate.getEmailTo();
						String subject = emailTemplate.getEmailSub();
						String cc = emailTemplate.getEmailCc();

						System.out.println(" Expense Reimb Reject ");
						emailAndOTPService.emailSend(from ,to, subject, body,cc);
					}
				} else {
					System.out.println("Nothing Happen ::::::");
				}
			} else {
				System.out.println("Nothing Happen ::::::");
			}
		} catch (Exception e) {
			LOGGER.error(
					"Error occurring while sending mail to employee who ExpenseReimb request reject by manager..... "
							+ ExceptionUtils.getStackTrace(e));
		}
	}

	/* Reject Expense Reimbursement by Manager */
	@GetMapping(value = "/rejectExpenseReimb/{id}")
	public String rejectExpenseReimb(@ModelAttribute ExpenseReimb expenseReimb, @PathVariable(name = "id") Long id,
			Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			if (Objects.nonNull(expenseReimb)) {
				String status = "Reject";
				String flag = "Y";
				expenseReimb.setStatus(status);
				expenseReimb.setFlag(flag);
				expenseReimbService.acceptStatus(status, flag, id);

				rejectExpenseReimb();
			} else {
				System.out.println("Nothing Happen ::::::");
				return "redirect:/appliedExitActivityList";
			}
			model.addAttribute("expenseReimbList", expenseReimbService.acceptExpenseReimbById(id));
		} catch (Exception e) {
			LOGGER.error("Error occur while reject Expense Reimb by manager ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - reject expense of id - " + id));
			auditRecord.setMenuCode("Employee Management");
			auditRecord.setSubMenuCode("Expense Reimb");
			auditRecord.setActivityCode("Reject Expense Reimb");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/appliedExpenseReimbList";
	}

	/* Display Attachment(Image) */
	@GetMapping(value = "/attachment")
	public void showattachment(@RequestParam("id") Long id, HttpServletResponse response) throws Exception {
		try {
			ExpenseReimb expenseReimb = expenseReimbService.getAttachment(id);
			if (expenseReimb != null) {
				response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
				response.getOutputStream().write(expenseReimb.getAttachment());
				response.getOutputStream().flush();
				response.getOutputStream().close();
			} else {
				System.out.println("attachment not get....");
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while display attachment  " + ExceptionUtils.getStackTrace(e));
		}
	}
}