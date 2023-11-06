package com.ksvsofttech.product.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.ksvsofttech.product.entities.AuditRecord;
import com.ksvsofttech.product.entities.EmailTemplate;
import com.ksvsofttech.product.entities.EmpBasicDetails;
import com.ksvsofttech.product.entities.LeaveMst;
import com.ksvsofttech.product.entities.LeaveTracker;
import com.ksvsofttech.product.service.AuditRecordService;
import com.ksvsofttech.product.service.EmailAndOTPService;
import com.ksvsofttech.product.service.EmailService;
import com.ksvsofttech.product.service.EmpRegistartionService;
import com.ksvsofttech.product.service.HolidayService;
import com.ksvsofttech.product.service.LeaveService;
import com.ksvsofttech.product.service.LeaveTrackerService;

@Controller
public class LeaveController {
	private static final Logger LOGGER = LogManager.getLogger(LeaveController.class);

	@Autowired
	private LeaveTrackerService leaveTrackerService;
	@Autowired
	private LeaveService leaveService;
	@Autowired
	private AuditRecordService auditRecordService;
	@Autowired
	private EmpRegistartionService empRegistartionService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private HolidayService holidayService;
	@Autowired
	private TemplateEngine templateEngine;

	public LeaveController(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}

	@Autowired
	private EmailAndOTPService emailAndOTPService;

	/* Display List of Active Holidays */
	@GetMapping(value = "/leaveTracker")
	public String getActiveHolidays(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empId = null;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			List<LeaveTracker> userDetails = null;
			EmpBasicDetails userDetails2 = null;
			if (empId != null) {
				userDetails = leaveTrackerService.getEmpDetails(empId);
				userDetails2 = empRegistartionService.getCurrentUser(empId);
				if (userDetails != null) {
					model.addAttribute("leaveTrackerList", userDetails);
				} else {
					System.out.println(" UserDetails Is Null :::::::::::: ");
				}
			} else {
				System.out.println(" Employee Id is Null ::::::::::::::::");
			}
			model.addAttribute("empBasicDetails", userDetails2);
		} catch (Exception e) {
			LOGGER.error("Error occur while active display holiday list" + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed holidayList"));
			auditRecord.setMenuCode("HOLIDAYMASTER");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "leave/leavetracker";
	}

	/* Display Active Leave List */
	@GetMapping(value = "/activeLeaveList")
	public String getActiveLeave(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empId = null;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			List<LeaveMst> userDetails = null;
			if (empId != null) {
				userDetails = leaveService.getActiveLeave(empId);
				if (userDetails != null) {
					model.addAttribute("leaveList", userDetails);
					return "leave/activeLeaveList";
				} else {
					System.out.println("Nothing Happen ::::::");
					return "leave/activeLeaveList";
				}
			} else {
				System.out.println("Nothing Happen ::::::");
				return "leave/activeLeaveList";
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while display active leave list" + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed active leave list"));
			auditRecord.setMenuCode("Employee Management");
			auditRecord.setSubMenuCode("Leave Management");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "leave/activeLeaveList";
	}

	/* Display Cancel Leave List */
	@GetMapping(value = "/cancelLeaveList")
	public String getCancelLeave(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empId = null;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			List<LeaveMst> userDetails = null;
			if (empId != null) {
				userDetails = leaveService.getInactiveLeave(empId);
				if (userDetails != null) {
					model.addAttribute("leaveList", userDetails);
					return "leave/cancelLeaveList";
				} else {
					System.out.println("Nothing Happen ::::::");
					return "leave/cancelLeaveList";
				}
			} else {
				System.out.println("Nothing Happen ::::::");
				return "leave/cancelLeaveList";
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while display cancel leave list" + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed cancel leave list"));
			auditRecord.setMenuCode("Employee Management");
			auditRecord.setSubMenuCode("Leave Management");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "leave/cancelLeaveList";
	}

	/* Leave Apply Mail Send Project Manager & HR */
	public void sendMail() throws Exception {
		String empId;
		EmailTemplate emailTemplate = emailService.getAppliedLeave();
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			List<LeaveMst> leaveMst2 = null;
			if (empId != null) {
				leaveMst2 = leaveService.getLeaveApply(empId);
				if (leaveMst2 != null) {
					if (emailTemplate != null) {
						Context context = new Context();
						context.setVariable("leaveList", leaveMst2);
						String body = templateEngine.process("leave/leaveRequestMail", context);

						String from = emailTemplate.getEmailFrom();
						String to = emailTemplate.getEmailTo();
						String subject = emailTemplate.getEmailSub();
						String cc = emailTemplate.getEmailCc();

						System.out.println(" Leave Request  Send");
						emailAndOTPService.emailSend(from, to, subject, body, cc);
					}
				} else {
					System.out.println("Nothing Happen ::::::");
				}
			} else {
				System.out.println("Nothing Happen ::::::");
			}
		} catch (Exception e) {
			LOGGER.error("Error occurring while sending emails to employee leave request ..... "
					+ ExceptionUtils.getStackTrace(e));
		}
	}

	/* Display Apply Leave Home Page */
	@GetMapping(value = "/leaveForm")
	public String leaveApplyPage(@ModelAttribute LeaveMst leaveMst, Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			List<EmpBasicDetails> userDetails = null;
			if (empId != null) {
				userDetails = empRegistartionService.listOfCurrentUser(empId);
				if (userDetails != null) {
					model.addAttribute("leaveList", userDetails);
				} else {
					System.out.println("Nothing Happen ::::::");
					return "leave/activeLeaveList";
				}
			} else {
				System.out.println("Nothing Happen ::::::");
				return "leave/activeLeaveList";
			}
		} catch (Exception e) {
			LOGGER.error("Error occur to display leave apply page... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed leave apply"));
			auditRecord.setMenuCode("Employee Management");
			auditRecord.setSubMenuCode("Leave Management");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "leave/newleave";
	}

	/* Apply Leave */
	@PostMapping(value = "/applyLeave")
	public String leaveApply(@ModelAttribute LeaveMst leaveMst, BindingResult result, Model model, Device device,
			RedirectAttributes redirAttrs) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empId;
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH);
		String fromDate = leaveMst.getFromDate();
		String toDate = leaveMst.getToDate();
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			List<EmpBasicDetails> userDetails = null;
			if (empId != null) {
				userDetails = empRegistartionService.listOfCurrentUser(empId);
				if (userDetails != null) {
					model.addAttribute("leaveList", userDetails);
				} else {
					System.out.println("Nothing Happen ::::::");
					return "leave/activeLeaveList";
				}
			} else {
				System.out.println("Nothing Happen ::::::");
				return "leave/activeLeaveList";
			}

			Date from = myFormat.parse(fromDate);
			Date to = myFormat.parse(toDate);
			long diff = to.getTime() - from.getTime();
			System.out.println("TIME :::: " + diff);

			/* Day Calculate */
			// float daysBetween = TimeUnit.MILLISECONDS.toDays(diff) % 365;

			/* Hour & Minute Calculate */
			long hourBetween = TimeUnit.MILLISECONDS.toHours(diff) % 24;
			long minitBetween = TimeUnit.MILLISECONDS.toMinutes(diff) % 60;

			String time = hourBetween + "." + minitBetween;
			double convertTime = Double.parseDouble(time);
			String totalTime = "9";
			String calHourMinute = String.valueOf(Double.valueOf(time) / Double.valueOf(totalTime));
			float hourMinute = Float.parseFloat(calHourMinute);

			Calendar cal1 = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			cal1.setTime(from);
			cal2.setTime(to);

			float numberOfDays = 0;
			while (cal1.before(cal2)) {
				if ((Calendar.SUNDAY != cal1.get(Calendar.DAY_OF_WEEK))) {
					numberOfDays++;
					cal1.add(Calendar.DATE, 1);
				} else {
					cal1.add(Calendar.DATE, 1);
				}
			}

			float holidays = holidayService.getHolidayDaysForLeave(from, to);
			float totalDay = numberOfDays - holidays;

			if (totalDay > hourMinute) {
				leaveMst.setDayLeave(totalDay);
				System.out.println("FULL DAY & MORE THEN ONE DAY :::::::::::::::");
			} else {
				if (convertTime <= 4.30) {
					leaveMst.setDayLeave((float) 0.5);
					System.out.println("Half Day (<= 4.3) ::::::::::::::");
				} else {
					leaveMst.setDayLeave((float) 1.0);
					System.out.println("Full Day (> 4.30) :::::::::::::::");
				}

			}
			/* Save */
			leaveMst.setStatus("Pending");
			leaveService.saveOrUpdateLeave(leaveMst);
			redirAttrs.addFlashAttribute("success", "Your leave is Applied Successfully");
			/*-------------*/

			Optional<LeaveTracker> optional = leaveTrackerService.findByEmpId(empId);
			if (optional.isPresent()) {
				LeaveTracker leaveTracker = optional.get();

				if (leaveMst.getLeaveType().equals("Total Leave")) {
					System.out.println("TOTAL LEAVE ::::::");
					Float totalLeave = leaveTracker.getTotalLeave() - leaveMst.getDayLeave();

					if (leaveTracker.getBookedTotalLeave() == null) {
						Float bookedTotalLeave = leaveMst.getDayLeave();
						leaveTracker.setTotalLeave(totalLeave);
						leaveTracker.setBookedTotalLeave(bookedTotalLeave);
						leaveTrackerService.updateLeave(leaveTracker);
					} else {
						Float bookedTotalLeave = leaveTracker.getBookedTotalLeave() + leaveMst.getDayLeave();
						leaveTracker.setTotalLeave(totalLeave);
						leaveTracker.setBookedTotalLeave(bookedTotalLeave);
						leaveTrackerService.updateLeave(leaveTracker);
					}
				} else if (leaveMst.getLeaveType().equals("Paid Leave")) {
					System.out.println("PAID LEAVE ::::::");
					Float paidLeave = leaveTracker.getPaidLeave() - leaveMst.getDayLeave();

					if (leaveTracker.getBookedPaidLeave() == null) {
						Float bookedPaidLeave = leaveMst.getDayLeave();
						leaveTracker.setPaidLeave(paidLeave);
						leaveTracker.setBookedPaidLeave(bookedPaidLeave);
						leaveTrackerService.updateLeave(leaveTracker);
					} else {
						Float bookedPaidLeave = leaveTracker.getBookedPaidLeave() + leaveMst.getDayLeave();
						leaveTracker.setPaidLeave(paidLeave);
						leaveTracker.setBookedPaidLeave(bookedPaidLeave);
						leaveTrackerService.updateLeave(leaveTracker);
					}
				} else {
					System.out.println("MATERNITY LEAVE ::::::");
					Float maternityLeave = leaveTracker.getMaternityLeave() - leaveMst.getDayLeave();

					if (leaveTracker.getBookedMaternityLeave() == null) {
						Float bookedMaternityLeave = leaveMst.getDayLeave();
						leaveTracker.setMaternityLeave(maternityLeave);
						leaveTracker.setBookedMaternityLeave(bookedMaternityLeave);
						leaveTrackerService.updateLeave(leaveTracker);
					} else {
						Float bookedMaternityLeave = leaveTracker.getBookedMaternityLeave() + leaveMst.getDayLeave();
						leaveTracker.setMaternityLeave(maternityLeave);
						leaveTracker.setBookedMaternityLeave(bookedMaternityLeave);
						leaveTrackerService.updateLeave(leaveTracker);
					}
				}
			}
			/*-------------*/
			sendMail();
			return "redirect:/activeLeaveList";

		} catch (Exception e) {
			LOGGER.error("Error occur while leave apply successfully... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - create leave successfully"));
			auditRecord.setMenuCode("Employee Management");
			auditRecord.setSubMenuCode("Leave Management");
			auditRecord.setActivityCode("CREATE");
			auditRecordService.save(auditRecord, device);
		}
		return "leave/newleave";
	}

	/* Remove Leave */
	@GetMapping(value = "/cancelLeave/{id}")
	public String removeLeave(LeaveMst leaveMst, @PathVariable(name = "id") Long id, Model model, Device device)
			throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			if (leaveMst != null) {
				/*-------------*/
				Optional<LeaveTracker> optional = leaveTrackerService.findByEmpId(empId);
				Optional<LeaveMst> optional2 = leaveService.findLeaveById(id);
				if (optional.isPresent()) {
					LeaveTracker leaveTracker = optional.get();
					if (optional2.isPresent()) {
						LeaveMst leaveMsts = optional2.get();

						if (leaveMsts.getLeaveType().equals("Total Leave")) {
							System.out.println("TOTAL LEAVE ::::::");
							Float totalLeave = leaveTracker.getTotalLeave() + leaveMsts.getDayLeave();
							Float totalBookLeave = leaveTracker.getBookedTotalLeave() - leaveMsts.getDayLeave();
							leaveTracker.setTotalLeave(totalLeave);
							leaveTracker.setBookedTotalLeave(totalBookLeave);
							leaveTrackerService.updateLeave(leaveTracker);
						} else if (leaveMsts.getLeaveType().equals("Paid Leave")) {
							System.out.println("PAID LEAVE ::::::");
							Float paidLeave = leaveTracker.getPaidLeave() + leaveMsts.getDayLeave();
							Float totalPaidLeave = leaveTracker.getBookedPaidLeave() - leaveMsts.getDayLeave();
							leaveTracker.setPaidLeave(paidLeave);
							leaveTracker.setBookedPaidLeave(totalPaidLeave);
							leaveTrackerService.updateLeave(leaveTracker);
						} else {
							System.out.println("Maternity LEAVE ::::::");
							Float maternityLeave = leaveTracker.getMaternityLeave() + leaveMsts.getDayLeave();
							Float totalMaternityLeave = leaveTracker.getBookedMaternityLeave()
									- leaveMsts.getDayLeave();
							leaveTracker.setMaternityLeave(maternityLeave);
							leaveTracker.setBookedMaternityLeave(totalMaternityLeave);
							leaveTrackerService.updateLeave(leaveTracker);
						}
					}
				}
				leaveService.cancelLeave(leaveMst);
				return "redirect:/activeLeaveList";
			} else {
				System.out.println("Nothing Happen ::::::");
				return "redirect:/activeLeaveList";
			}
			/*-------------*/

		} catch (Exception e) {
			LOGGER.error("Error occur while update leave apply page... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - cancel leave by emp id - " + id));
			auditRecord.setMenuCode("Employee Management");
			auditRecord.setSubMenuCode("Leave Management");
			auditRecord.setActivityCode("CANCEL LEAVE");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/activeLeaveList";
	}

	/* Display List of emp apply leave to Manager */
	@GetMapping(value = "/appliedLeaveList")
	public String getEmpLeave(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			if (empId != null) {
				model.addAttribute("leaveList", leaveService.getEmpWithManger(empId));
				return "leave/appliedLeaveList";
			} else {
				System.out.println("No Employee Found ::::::::::");
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while accept leave of employees... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed apply emp list"));
			auditRecord.setMenuCode("Employee Management");
			auditRecord.setSubMenuCode("Leave Tracker");
			auditRecord.setActivityCode("ACCEPT LEAVE");
			auditRecordService.save(auditRecord, device);
		}
		return "leave/appliedLeaveList";
	}

	/* Leave Request Approved by Manager */
	public void approvedMailReq() throws Exception {
		String empId;
		EmailTemplate emailTemplate = emailService.getApprovedLeave();
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			List<LeaveMst> leaveMst2 = null;
			if (empId != null) {
				leaveMst2 = leaveService.getLeaveApply(empId);
				if (leaveMst2 != null) {
					Context context = new Context();

					System.out.println("leaveMst" + leaveMst2.toString());

					context.setVariable("leaveList", leaveMst2);
					String body = templateEngine.process("leave/approvedLeaveMail", context);

					String from = emailTemplate.getEmailFrom();
					String to = emailTemplate.getEmailTo();
					String subject = emailTemplate.getEmailSub();
					String cc = emailTemplate.getEmailCc();
					System.out.println("Your leave is Approved");

					emailAndOTPService.emailSend(from, to, subject, body, cc);
				} else {
					System.out.println("Nothing Happen ::::::");
				}
			} else {
				System.out.println("Nothing Happen ::::::");
			}
		} catch (Exception e) {
			LOGGER.error("Error occurring while sending emails to employee who leave request approved by manager..... "
					+ ExceptionUtils.getStackTrace(e));
		}
	}

	/* Accept Leave of Employees Manager */
	@GetMapping(value = "/acceptLeave/{id}")
	public String acceptLeave(@ModelAttribute LeaveMst leaveMst, @PathVariable(name = "id") Long id, Model model,
			Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			if (leaveMst != null) {
				String status = "Approved";
				String flag = "Y";
				leaveMst.setStatus(status);
				leaveMst.setFlag("Y");
				leaveService.acceptStatus(status, flag, id);

				approvedMailReq();
			} else {
				System.out.println("Nothing Happen ::::::");
				return "redirect:/appliedLeaveList";
			}
			model.addAttribute("leaveList", leaveService.getAcceptLeaveById(id));
		} catch (Exception e) {
			LOGGER.error("Error occur while accept leave of employees... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - approved leave of id - " + id));
			auditRecord.setMenuCode("Employee Management");
			auditRecord.setSubMenuCode("Leave Tracker");
			auditRecord.setActivityCode("Approved LEAVE");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/appliedLeaveList";
	}

	/* Leave Request Approved by Manager */
	public void rejectMailReq() throws Exception {
		String empId;
		EmailTemplate emailTemplate = emailService.getRejectLeave();
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			List<LeaveMst> leaveMst2 = null;
			if (empId != null) {
				leaveMst2 = leaveService.getLeaveApply(empId);
				if (leaveMst2 != null) {
					Context context = new Context();
					context.setVariable("leaveList", leaveMst2);
					String body = templateEngine.process("leave/rejectLeaveMail", context);

					String from = emailTemplate.getEmailFrom();
					String to = emailTemplate.getEmailTo();
					String subject = emailTemplate.getEmailSub();
					String cc = emailTemplate.getEmailCc();
					System.out.println("Your leave is Reject");

					emailAndOTPService.emailSend(from, to, subject, body, cc);

				} else {
					System.out.println("Nothing Happen ::::::");
				}
			} else {
				System.out.println("Nothing Happen ::::::");
			}
		} catch (Exception e) {
			LOGGER.error("Error occurring while sending emails to employee who leave request rejected by manager..... "
					+ ExceptionUtils.getStackTrace(e));
		}
	}

	/* Reject Leave of Employees Manager */
	@GetMapping(value = "/rejectLeave/{id}")
	public String rejectLeave(@ModelAttribute LeaveMst leaveMst, @PathVariable(name = "id") Long id, Model model,
			Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			Optional<LeaveMst> optional2 = leaveService.findLeaveById(id);
			if (optional2.isPresent()) {
				LeaveMst leaveMsts = optional2.get();

				Optional<LeaveTracker> optional = leaveTrackerService.findByEmpId(leaveMsts.getEmpId().getEmpId());
				if (optional.isPresent()) {
					LeaveTracker leaveTracker = optional.get();

					if (leaveMsts.getLeaveType().equals("Total Leave")) {
						System.out.println("TOTAL LEAVE ::::::");
						Float totalLeave = leaveTracker.getTotalLeave() + leaveMsts.getDayLeave();
						Float totalBookedLeave = leaveTracker.getBookedTotalLeave() - leaveMsts.getDayLeave();
						leaveTracker.setTotalLeave(totalLeave);
						leaveTracker.setBookedTotalLeave(totalBookedLeave);
						leaveTrackerService.updateLeave(leaveTracker);
						rejectMailReq();
					} else if (leaveMsts.getLeaveType().equals("Paid Leave")) {
						System.out.println("PAID LEAVE ::::::");
						Float paidLeave = leaveTracker.getPaidLeave() + leaveMsts.getDayLeave();
						Float paidBookedLeave = leaveTracker.getBookedPaidLeave() - leaveMsts.getDayLeave();
						leaveTracker.setPaidLeave(paidLeave);
						leaveTracker.setBookedPaidLeave(paidBookedLeave);
						leaveTrackerService.updateLeave(leaveTracker);
						rejectMailReq();
					} else {
						System.out.println("MATERNITY LEAVE ::::::");
						Float maternityLeave = leaveTracker.getMaternityLeave() + leaveMsts.getDayLeave();
						Float maternityBookedLeave = leaveTracker.getBookedMaternityLeave() - leaveMsts.getDayLeave();
						leaveTracker.setMaternityLeave(maternityLeave);
						leaveTracker.setBookedMaternityLeave(maternityBookedLeave);
						leaveTrackerService.updateLeave(leaveTracker);
						rejectMailReq();
					}
				}

				String status = "Rejected";
				String flag = "Y";
				leaveMst.setStatus(status);
				leaveMst.setFlag("Y");
				leaveService.acceptStatus(status, flag, id);
				model.addAttribute("leaveList", leaveService.getRejectLeave(leaveMsts.getEmpId().getEmpId()));
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while reject leave of employees... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - reject leave of id - " + id));
			auditRecord.setMenuCode("Employee Management");
			auditRecord.setSubMenuCode("Leave Tracker");
			auditRecord.setActivityCode("REJECT LEAVE");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/appliedLeaveList";
	}

	@GetMapping("/totalLeave")
	public String totalLeave(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		List<LeaveMst> leaveMsts = leaveService.getTotalLeave();
		try {
			if (Objects.nonNull(leaveMsts)) {
				System.out.println("TOTAL Leave EMPLOYEE ::::::::::::: " + leaveMsts.toString());
				model.addAttribute("totalLeave", leaveMsts);

			} else {
				System.out.println("Error occuring while get total leave employee");
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while get list of total leave emp " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed total leave"));
			auditRecord.setMenuCode("Employee Management");
			auditRecord.setSubMenuCode("Leave Tracker");
			auditRecord.setActivityCode("TOTAL LEAVE");
			auditRecordService.save(auditRecord, device);
		}

		return "leave/totalLeave";
	}

	@GetMapping("/totalPendingLeave")
	public String totalPendingLeave(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		List<LeaveMst> leaveMsts = leaveService.getTotalPendingLeave();
		try {
			if (Objects.nonNull(leaveMsts)) {
				System.out.println("TOTAL Pending Leave EMPLOYEE ::::::::::::: " + leaveMsts.toString());
				model.addAttribute("totalPendingLeave", leaveMsts);

			} else {
				System.out.println("Error occuring while get total pending leave employee");
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while get list of total leave emp " + ExceptionUtils.getStackTrace(e));
		}  finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed total pending leave"));
			auditRecord.setMenuCode("Employee Management");
			auditRecord.setSubMenuCode("Leave Tracker");
			auditRecord.setActivityCode("PENDING LEAVE");
			auditRecordService.save(auditRecord, device);
		}

		return "leave/totalPendingLeave";
	}
}