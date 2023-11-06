package com.ksvsofttech.product.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mobile.device.Device;
import org.springframework.scheduling.annotation.Scheduled;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.ksvsofttech.product.entities.AuditRecord;
import com.ksvsofttech.product.entities.CheckInCheckOut;
import com.ksvsofttech.product.entities.EmailTemplate;
import com.ksvsofttech.product.entities.UserMst;
import com.ksvsofttech.product.repository.CheckInCheckOutRepository;
import com.ksvsofttech.product.repository.EmpPersonalRepository;
import com.ksvsofttech.product.service.AuditRecordService;
import com.ksvsofttech.product.service.CheckInCheckOutService;
import com.ksvsofttech.product.service.DailyWorkReportService;
import com.ksvsofttech.product.service.EmailAndOTPService;
import com.ksvsofttech.product.service.EmailService;
import com.ksvsofttech.product.service.EmpRegistartionService;
import com.ksvsofttech.product.service.ExitActivityService;
import com.ksvsofttech.product.service.ExpenseReimbService;
import com.ksvsofttech.product.service.HolidayService;
import com.ksvsofttech.product.service.LeaveService;
import com.ksvsofttech.product.service.TrainingFormService;
import com.ksvsofttech.product.service.UploadDocService;
import com.ksvsofttech.product.service.UserService;

@Controller
public class CheckInCheckOutController {
	private static final Logger LOG = LogManager.getLogger(CheckInCheckOutController.class);

	@Autowired
	private EmailService emailService;
	@Autowired
	private EmailAndOTPService emailAndOTPService;
	@Autowired
	private TemplateEngine templateEngine;

	public CheckInCheckOutController(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}

	@Autowired
	private CheckInCheckOutService chkInChkOutService;
	@Autowired
	private AuditRecordService auditRecordService;
	@Autowired
	private HolidayService holidayService;
	@Autowired
	private EmpRegistartionService empRegistartionService;
	@Autowired
	private LeaveService leaveService;
	@Autowired
	private UploadDocService uploadDocService;
	@Autowired
	private ExitActivityService exitActivityService;
	@Autowired
	private ExpenseReimbService expenseReimbService;
	@Autowired
	private UserService userService;
	@Autowired
	private TrainingFormService trainingFormService;
	@Autowired
	private DailyWorkReportService dailyWorkReportService;
	@Autowired
	CheckInCheckOutRepository checkInCheckOutRepository;
	@Autowired
	EmpPersonalRepository empPersonalRepository;

	/* Total Working Days Calculate */
	public int getBusinessDays(LocalDate startInclusive, LocalDate endExclusive) {
		if (startInclusive.isAfter(endExclusive)) {
			String msg = "Start date " + startInclusive + " must be earlier than end date " + endExclusive;
			throw new IllegalArgumentException(msg);
		}
		int businessDays = 0;
		LocalDate d = startInclusive;
		while (d.isBefore(endExclusive)) {
			DayOfWeek dw = d.getDayOfWeek();
			if (dw != DayOfWeek.SUNDAY) {
				businessDays++;
			}
			d = d.plusDays(1);
		}
		return businessDays;
	}

	/* If employee not Check-In today then automatic save data */
	@Scheduled(cron = "${scheduling.job.cron3}")
	public void absentEmp() throws ParseException {
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String checkinDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String checkoutDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String timeDuration = "00:00 Hrs";
		String tenantId = "1";
		String isActive = "0";
		String status = "Absent";
		String dayOfTheWeek = new SimpleDateFormat("EEEE").format(new Date());
		try {
			List<String> userId = empRegistartionService.getEmpIdWithIsActive();
			List<String> checkInCheckOut = chkInChkOutService.getDateAndUserId();
			if (Objects.nonNull(userId) && Objects.nonNull(checkInCheckOut)) {
				userId.removeAll(checkInCheckOut);
				for (String id : userId) {
					if (!Objects.deepEquals(checkInCheckOut, userId)) {
						chkInChkOutService.insertEmployee(id, date, dayOfTheWeek, tenantId, isActive, checkinDateTime, checkoutDateTime, timeDuration, status);
					} else {
						System.out.println("Not Match ::: ");
					}
				}
			} else {
				System.out.println("userId & checkInCheckOut is Null ::::::::::::::::::");
			}
		} catch (Exception e) {
			LOG.error("Error occur while save absent employee data... " + ExceptionUtils.getStackTrace(e));
		}
	}

	/* Display attendance(check in & out) page */
	@GetMapping(value = "/attendance")
	public String getcheckInOut(@ModelAttribute CheckInCheckOut checkInCheckOut, Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String userId = null;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			userId = authentication.getName();

			CheckInCheckOut checkInCheckOut1 = chkInChkOutService.findByEmpId(userId);
			/* Check-In & Check-out Button in Attendance */
			if (checkInCheckOut1 != null) {
				model.addAttribute("flag", checkInCheckOut1);
			} else {
				model.addAttribute("flag", new CheckInCheckOut());
			}

			/* Weekly Data Display in Attendance */
			LocalDate today = LocalDate.now();
			// Go backward to get Monday
			LocalDate monday = today;
			while (monday.getDayOfWeek() != DayOfWeek.MONDAY) {
				monday = monday.minusDays(1);
			}
			// Go forward to get Sunday
			LocalDate sunday = today;
			while (sunday.getDayOfWeek() != DayOfWeek.SUNDAY) {
				sunday = sunday.plusDays(1);
			}

			String from = monday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			String to = sunday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			/* Current user */
			List<CheckInCheckOut> userDetails = null;
			if (Objects.nonNull(userId)) {
				userDetails = chkInChkOutService.findByDayOfTheWeek(from, to, userId);
				if (Objects.nonNull(userDetails)) {
					model.addAttribute("attendance", userDetails);
				}
			}

			/* DAYS OVERVIEW THIS MONTH */
			LocalDate firstDay = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
			LocalDate lastDay = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());

			/* Working Days */
			int days = getBusinessDays(firstDay, lastDay);
			model.addAttribute("workingDays", days);

			/* Present Days (Employee Check-In) */
			long presentDays = chkInChkOutService.getPresentDays(userId);
			model.addAttribute("presentDays", presentDays);

			/* Holidays */
			long holidayDays = holidayService.getHolidayDays();
			model.addAttribute("holidayDays", holidayDays);

			/* Late Days (Late Login) */
			long lateDays = chkInChkOutService.getLateDays(userId);
			model.addAttribute("lateDays", lateDays);

			/* Half Days (Half Day Leave) */
			long halfDays = chkInChkOutService.getHalfDays(userId);
			model.addAttribute("halfDays", halfDays);

			/* Previous Day for Absent Day */
			long absentDays = chkInChkOutService.getAbsentDays(userId);
			model.addAttribute("absentDays", absentDays);
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			
			String dateStart = checkInCheckOut1.getCheckinDateTime();
			String dateStop = dateFormat.format(new Date()); 
			
			Date d1 = dateFormat.parse(dateStart);
			Date d2 = dateFormat.parse(dateStop);  

			// Get msec from each, and subtract.
			long diff = d2.getTime() - d1.getTime();
			
			long diffHours = (long) Math.floor((diff % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
			long diffMinutes = (long) Math.floor((diff % (1000 * 60 * 60)) / (1000 * 60));
			long diffSeconds = (long) Math.floor((diff % (1000 * 60)) / 1000);
			
			String todayCheckin = diffHours + ":" + diffMinutes + ":" + diffSeconds;
			System.out.println("TODAYCHECKIN ::::::: " + todayCheckin);
			model.addAttribute("todayCheckin", todayCheckin);

			return "checkincheckout/attendance";
		} catch (Exception e) {
			LOG.error("Error occur to open checkInOut page... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed attendance"));
			auditRecord.setMenuCode("Employee Administrator");
			auditRecord.setSubMenuCode("Attendance");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "checkincheckout/attendance";
	}

	/* Display attendance(check in & out) page */
	@GetMapping(value = "/empDashboard")
	public String empDashboard(@ModelAttribute CheckInCheckOut checkInCheckOut, @Param("id") Long id, Model model, Device device, HttpServletResponse response) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String userId = null;
		try {
			/* Current user */
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			userId = authentication.getName();
			UserMst roleCode = userService.getUserDetails(userId);			
			
			if(roleCode.getMainRole().equals("HR")) {
				LOG.info("HR DASHBOARD :::::::::::::::");
				
				model.addAttribute("date", new Date());
				model.addAttribute("upcomingBirthDay", chkInChkOutService.getUpcomingEmpBirthday());
				model.addAttribute("birthDayToday", empPersonalRepository.getEmpBirthdayToday());
				model.addAttribute("holidayList", holidayService.getHolidayUpcoming());
				model.addAttribute("organizationList", uploadDocService.getOrganizationDepart());
				model.addAttribute("employeeList", uploadDocService.getEmpDepartment());

				/* List of Upcoming BirthDay, Holiday List */
				if (userId != null) {
					model.addAttribute("leaveDashboard", leaveService.getLeavePending(userId));
					model.addAttribute("attendanceDashboard", chkInChkOutService.getAttendancePending(userId));
					model.addAttribute("image", empRegistartionService.getCurrentUser(userId));
					model.addAttribute("exitActivityDashboard", exitActivityService.getEmpWithMangerWithPending(userId));
					model.addAttribute("expenseReimbDashboard", expenseReimbService.getEmpWithMangerWithPending(userId));
					model.addAttribute("onLeaveToday", leaveService.onLeaveToday());
				}
				
					/* DashBoard Count */
					model.addAttribute("allEmp", empRegistartionService.getAllEmployees());
					model.addAttribute("allLeave", leaveService.getAllLeaves());
					model.addAttribute("allAttendance", chkInChkOutService.getAllAttendance());
					model.addAttribute("allAbsent", chkInChkOutService.getAllEmpAbsent());
					model.addAttribute("allPendingLeaves", leaveService.getAllPendingLeaves());
					model.addAttribute("allExitActivity", exitActivityService.getAllExitActivity());
					model.addAttribute("allNewReq", chkInChkOutService.getAllNewReq());
					model.addAttribute("allTraining", trainingFormService.getAllTraining());
					model.addAttribute("allWorkReport", dailyWorkReportService.getAllWorkReport());

				return "dashboard/hrDashboard";
			} else {
				LOG.info("EMPLOYEE DASHBOARD :::::::::::::::");
				
				CheckInCheckOut checkInCheckOut1 = chkInChkOutService.findByEmpId(userId);
				/* Check-In & Check-out Button in Attendance */
				if (checkInCheckOut1 != null) {
					model.addAttribute("flag", checkInCheckOut1);
				} else {
					model.addAttribute("flag", new CheckInCheckOut());
				}

				/* Weekly Data Display in Attendance */
				LocalDate today = LocalDate.now();
				// Go backward to get Monday
				LocalDate monday = today;
				while (monday.getDayOfWeek() != DayOfWeek.MONDAY) {
					monday = monday.minusDays(1);
				}
				// Go forward to get Sunday
				LocalDate sunday = today;
				while (sunday.getDayOfWeek() != DayOfWeek.SUNDAY) {
					sunday = sunday.plusDays(1);
				}
				String from = monday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				String to = sunday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

				/* List of Weekly Data, Upcoming BirthDay, Holiday List */
				List<CheckInCheckOut> userDetails = null;
				if (userId != null) {
					userDetails = chkInChkOutService.findByDayOfTheWeek(from, to, userId);
					model.addAttribute("leaveDashboard", leaveService.getLeavePending(userId));
					model.addAttribute("attendanceDashboard", chkInChkOutService.getAttendancePending(userId));
					model.addAttribute("image", empRegistartionService.getCurrentUser(userId));
					model.addAttribute("exitActivityDashboard", exitActivityService.getEmpWithMangerWithPending(userId));
					model.addAttribute("expenseReimbDashboard", expenseReimbService.getEmpWithMangerWithPending(userId));
					model.addAttribute("onLeaveToday", leaveService.onLeaveToday());
					model.addAttribute("checkin", chkInChkOutService.findByDateAndCurrentUser2(userId));

					/* DashBoard Count */
					model.addAttribute("allEmp", empRegistartionService.getAllEmployees());

					if (userDetails != null) {
						model.addAttribute("weeklyList", userDetails);
						model.addAttribute("date", new Date());
						model.addAttribute("upcomingBirthDay", chkInChkOutService.getUpcomingEmpBirthday());
						model.addAttribute("birthDayToday", empPersonalRepository.getEmpBirthdayToday());
						model.addAttribute("holidayList", holidayService.getHolidayUpcoming());
						model.addAttribute("organizationList", uploadDocService.getOrganizationDepart());
						model.addAttribute("employeeList", uploadDocService.getEmpDepartment());
					}
				}
				/* Present Days in Current Month (Employee Check-In) */
				long presentDays = chkInChkOutService.getPresentDays(userId);
				model.addAttribute("presentDays", presentDays);
				
				/* Leave Count in Current Month */
				long totallLeave = leaveService.getLeaveDays(userId);
				model.addAttribute("totallLeave", totallLeave);

				/* Previous Day for Absent Day */
				long absentDays = chkInChkOutService.getAbsentDays(userId);
				model.addAttribute("absentDays", absentDays);
				
//				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
//				
//				String dateStart = checkInCheckOut1.getCheckinDateTime();
//				String dateStop = dateFormat.format(new Date()); 
//				
//				Date d1 = dateFormat.parse(dateStart);
//				Date d2 = dateFormat.parse(dateStop);  
//
//				// Get msec from each, and subtract.
//				long diff = d2.getTime() - d1.getTime();
//				
//				long diffHours = (long) Math.floor((diff % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
//				long diffMinutes = (long) Math.floor((diff % (1000 * 60 * 60)) / (1000 * 60));
//				long diffSeconds = (long) Math.floor((diff % (1000 * 60)) / 1000);
//				
//				String todayCheckin = diffHours + ":" + diffMinutes + ":" + diffSeconds;
//				System.out.println("TODAYCHECKIN ::::::: " + todayCheckin);
//				model.addAttribute("todayCheckin", todayCheckin);
				
				return "dashboard/userDashboard";
			}
		} catch (Exception e) {
			LOG.error("Error occur to open checkInOut page... " + ExceptionUtils.getStackTrace(e));
			throw new Exception("ROLE NOT FOUND ::::::::::");
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed HR/employee dashboard "));
			auditRecord.setMenuCode("Employee Administrator");
			auditRecord.setSubMenuCode("Dashboard ");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
	}

	/* Click to check in for Attendance */
	@GetMapping(value = "/saveCheckInAttendance")
	public String saveCheckInAttendance(@ModelAttribute CheckInCheckOut checkInCheckOut, Device device, Model model) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			checkInCheckOut.setUserId(userDetails.getUsername());
			chkInChkOutService.saveCheckIn(checkInCheckOut, device);
		} catch (Exception e) {
			LOG.error("Error occur while save checkIn data in Attendance /n" + ExceptionUtils.getStackTrace(e));
			throw new Exception("error occur in checkIn for Attendance page");
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - checkin attendance"));
			auditRecord.setMenuCode("Employee Administrator");
			auditRecord.setSubMenuCode("Attendance");
			auditRecord.setActivityCode("CREATE");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/attendance";
	}

	/* Click to click out for Attendance */
	@GetMapping(value = "/saveCheckOutAttendance")
	public String saveCheckOutAttendance(CheckInCheckOut checkInCheckOut, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			checkInCheckOut.setUserId(userDetails.getUsername());
			chkInChkOutService.saveCheckOut(checkInCheckOut, device);
		} catch (Exception e) {
			LOG.error("Error occur while save checkOut data /n" + ExceptionUtils.getStackTrace(e));
			throw new Exception("error occur in checkOut ");
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - checkout Attendance"));
			auditRecord.setMenuCode("Employee Administrator");
			auditRecord.setSubMenuCode("Attendance");
			auditRecord.setActivityCode("CREATE");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/attendance";
	}

	/* Click to check in for DashBoard */
	@GetMapping(value = "/saveCheckIn")
	public @ResponseBody String saveCheckIn(@ModelAttribute CheckInCheckOut checkInCheckOut, Device device, Model model) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String flag = "N";
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			checkInCheckOut.setUserId(userDetails.getUsername());
			CheckInCheckOut checkInCheckOut1 = chkInChkOutService.saveCheckIn(checkInCheckOut, device);
			if (checkInCheckOut1 != null) {
				flag = "Y";
			}
		} catch (Exception e) {
			LOG.error("Error occur while save checkIn data /n" + ExceptionUtils.getStackTrace(e));
			throw new Exception("error occur in checkIn ");
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - checkin attendance in dashboard"));
			auditRecord.setMenuCode("Employee Administrator");
			auditRecord.setSubMenuCode("Employee Dashboard");
			auditRecord.setActivityCode("CREATE");
			auditRecordService.save(auditRecord, device);
		}
		return flag;
	}

	/* Click to click out for DashBoard */
	@GetMapping(value = "/saveCheckOut")
	public @ResponseBody String saveCheckOut(@ModelAttribute CheckInCheckOut checkInCheckOut, Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String flag = "N";
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			checkInCheckOut.setUserId(userDetails.getUsername());
			checkInCheckOut.setRemark(checkInCheckOut.getRemark());
			CheckInCheckOut checkInCheckOut2 = chkInChkOutService.saveCheckOut(checkInCheckOut, device);
			if (checkInCheckOut2 != null) {
				flag = "Y";
			}
		} catch (Exception e) {
			LOG.error("Error occur while save checkOut data /n" + ExceptionUtils.getStackTrace(e));
			throw new Exception("error occur in checkOut ");
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - checkout attendance in dashboard"));
			auditRecord.setMenuCode("Employee Administrator");
			auditRecord.setSubMenuCode("Employee Dashboard");
			auditRecord.setActivityCode("CREATE");
			auditRecordService.save(auditRecord, device);
		}
		return flag;
	}

	/* Display Info for Employee about timeSummary */
	@GetMapping(value = "/totalTime")
	public String getEmpProfile(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String userId = null;
		try {
			/* Current user */
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			userId = authentication.getName();
			List<CheckInCheckOut> userDetails = null;
			if (userId != null) {
				userDetails = chkInChkOutService.getTotalTime(userId);
				if (userDetails != null) {
					model.addAttribute("totalTime", userDetails);
				}
			}
		} catch (Exception e) {
			LOG.error("Error occur while display time summary to employee " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed attendance in employee total time"));
			auditRecord.setMenuCode("Employee Administrator");
			auditRecord.setSubMenuCode("Employee Total Time");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "checkincheckout/emptotaltimelist";
	}

	/* Display Data for Employee Weekly */
	@GetMapping(value = "/dayOfWeek")
	public String getDataWeekly(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String userId = null;
		try {
			LocalDate today = LocalDate.now();
			// Go backward to get Monday
			LocalDate monday = today;
			while (monday.getDayOfWeek() != DayOfWeek.MONDAY) {
				monday = monday.minusDays(1);
			}
			// Go forward to get Sunday
			LocalDate sunday = today;
			while (sunday.getDayOfWeek() != DayOfWeek.SUNDAY) {
				sunday = sunday.plusDays(1);
			}

			String from = monday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			String to = sunday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			/* Current user */
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			userId = authentication.getName();
			List<CheckInCheckOut> userDetails = null;
			if (userId != null) {
				userDetails = chkInChkOutService.findByDayOfTheWeek(from, to, userId);
				if (userDetails != null) {
					model.addAttribute("weeklyList", userDetails);
				} else {
					return "checkincheckout/weeklyEmpData";
				}
			} else {
				return "checkincheckout/weeklyEmpData";
			}
		} catch (Exception e) {
			LOG.error("Error occur while display weekly list " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed attendance in employee weekly data"));
			auditRecord.setMenuCode("Employee Administrator");
			auditRecord.setSubMenuCode("Employee Weekly Data");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "checkincheckout/weeklyEmpData";
	}

	/* For New Request List */
	@GetMapping(value = "/newReqList")
	public String getNewReqList(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			if (empId != null) {
				List<CheckInCheckOut> getNewReqList = chkInChkOutService.getNewReqList(empId);
				model.addAttribute("addReq", getNewReqList);
			} else {
				System.out.println("No Employee Found ::::::::::");
			}
		} catch (Exception e) {
			LOG.error("Error occur while approval new request list... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed new request list"));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("New Request List");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "checkincheckout/newRequestList";
	}

	/* For Cancel Request List */
	@GetMapping(value = "/cancelReqList")
	public String cancelReqList(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			if (empId != null) {
				List<CheckInCheckOut> getCancelReqList = chkInChkOutService.cancelReqList(empId);
				model.addAttribute("addReq", getCancelReqList);
			} else {
				System.out.println("No Employee Found ::::::::::");
			}
		} catch (Exception e) {
			LOG.error("Error occur while cacnel new request list... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed cancel request list"));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("Cancel Request List");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "checkincheckout/cancelRequestList";
	}

	/* Add Request Home Page */
	@GetMapping("/getAddRequest")
	public String getAddRequest(@ModelAttribute CheckInCheckOut checkInCheckOut, Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String userId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			userId = authentication.getName();
			model.addAttribute("user", empRegistartionService.getCurrentUser(userId));
		} catch (Exception e) {
			LOG.error("Error occur while display add request form " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed add request in check-in check-out"));
			auditRecord.setMenuCode("Employee Administrator");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "checkincheckout/addRequest-PopUp";
	}

	/* Save Add Request Data */
	@PostMapping("/addRequest")
	public String addRequest(@ModelAttribute CheckInCheckOut checkInCheckOut, BindingResult result, Model model, Device device, RedirectAttributes redirectAttributes) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String userId;
		EmailTemplate emailTemplate = emailService.getAppliedAddNewReq();
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			userId = authentication.getName();
			model.addAttribute("user", empRegistartionService.getCurrentUser(userId));

			if (Objects.nonNull(checkInCheckOut)) {
				model.addAttribute("addReq", checkInCheckOut);
				chkInChkOutService.updateNewRequest(checkInCheckOut, device);
				if (emailTemplate != null) {
					/* Mail Send */
					CheckInCheckOut checkInCheckOuts = chkInChkOutService.sendMail(userId);
					
					Context context = new Context();
					context.setVariable("getEmp", checkInCheckOuts);
					String body = templateEngine.process("checkincheckout/chkInOutRequest", context);
					String from = emailTemplate.getEmailFrom();
					String to = emailTemplate.getEmailTo();
					String subject = emailTemplate.getEmailSub();
					String cc = emailTemplate.getEmailCc();
					emailAndOTPService.emailSend(from, to, subject, body, cc);
				}
			}
		} catch (Exception e) {
			LOG.error("Error occur while send request" + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - create add request in check-in check-out"));
			auditRecord.setMenuCode("Employee Administrator");
			auditRecord.setSubMenuCode("Add Request");
			auditRecord.setActivityCode("UPDATE");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/newReqList";
	}

	/* For Approved/Reject List */
	@GetMapping(value = "/approvalNewReqList")
	public String getEmpLeave(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			if (empId != null) {
				List<CheckInCheckOut> checkInCheckOuts = chkInChkOutService.getEmpWithManger(empId);
				model.addAttribute("addReq", checkInCheckOuts);
			} else {
				System.out.println("No Employee Found ::::::::::");
			}
		} catch (Exception e) {
			LOG.error("Error occur while approval new request by manager... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed approved/reject list"));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("New Request");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "checkincheckout/approvalRequest";
	}
	
	/* Reject New Request By Manager */
	@GetMapping(value = "/cancelAddReq/{id}")
	public String cancelAddReq(CheckInCheckOut checkInCheckOut, @PathVariable(name = "id") Long id, Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			chkInChkOutService.cancelAddReq(checkInCheckOut);
		} catch (Exception e) {
			LOG.error("Error occur while cancel new request by employee ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - cancel viewed id - " + id));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("Add New Request");
			auditRecord.setActivityCode("Cancel Request");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/newReqList";
	}

	/* Leave Request Approved by Manager */
	public void approvedNewReqMail() throws Exception {
		String userId;
		EmailTemplate emailTemplate = emailService.getApprovedAddNewReq();
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			userId = authentication.getName();
			List<CheckInCheckOut> checkInCheckOut = null;
			if (userId != null) {
				checkInCheckOut = chkInChkOutService.getCurrentUser(userId);
				if (checkInCheckOut != null) {
					Context context = new Context();
					context.setVariable("approvedNewReqList", checkInCheckOut);
					String body = templateEngine.process("checkincheckout/approvedNewReqMail", context);
					String from = emailTemplate.getEmailFrom();
					String to = emailTemplate.getEmailTo();
					String subject = emailTemplate.getEmailSub();
					String cc = emailTemplate.getEmailCc();

					emailAndOTPService.emailSend(from, to, subject, body, cc);
				} else {
					System.out.println("Nothing Happen ::::::");
				}
			} else {
				System.out.println("Nothing Happen ::::::");
			}
		} catch (Exception e) {
			LOG.error("Error occurring while sending emails to employee who add new request rejected by manager..... " + ExceptionUtils.getStackTrace(e));
		}
	}

	/* Approval New Request By Manager */
	@GetMapping(value = "/acceptNew/{id}")
	public String acceptNewRequest(@ModelAttribute CheckInCheckOut checkInCheckOut, @PathVariable(name = "id") Long id, Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			model.addAttribute("approvedNewReqList", chkInChkOutService.getAcceptLeaveById(id)); 
			if (checkInCheckOut != null) {
				String approvalReq = "Approved";
				String status = "Present";
				String isActive = "1";
				String flag = "N";
				checkInCheckOut.setApprovalReq(approvalReq);
				checkInCheckOut.setStatus(status);
				checkInCheckOut.setIsActive(isActive);
				checkInCheckOut.setFlag(flag);
				chkInChkOutService.acceptStatus(approvalReq, status, isActive, flag, id);

				approvedNewReqMail();
			} else {
				System.out.println("Nothing Happen ::::::");
				return "redirect:/approvalNewReqList";
			}
		} catch (Exception e) {
			LOG.error("Error occur while accept new request by manager ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - accept new request viewed id - " + id));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("New Request");
			auditRecord.setActivityCode("Accept Request");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/approvalNewReqList";
	}

	/* Leave Request Rejected by Manager */
	public void rejectNewReqMail() throws Exception {
		String userId;
		EmailTemplate emailTemplate = emailService.getRejectAddNewReq();
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			userId = authentication.getName();
			List<CheckInCheckOut> checkInCheckOut = null;
			if (userId != null) {
				checkInCheckOut = chkInChkOutService.getCurrentUser(userId);
				if (checkInCheckOut != null) {
					Context context = new Context();
					context.setVariable("rejectNewReqList", checkInCheckOut);
					String body = templateEngine.process("checkincheckout/rejectNewReqMail", context);
					String from = emailTemplate.getEmailFrom();
					String to = emailTemplate.getEmailTo();
					String subject = emailTemplate.getEmailSub();
					String cc = emailTemplate.getEmailCc();

					emailAndOTPService.emailSend(from, to, subject, body, cc);
				} else {
					System.out.println("Nothing Happen ::::::");
				}
			} else {
				System.out.println("Nothing Happen ::::::");
			}
		} catch (Exception e) {
			LOG.error("Error occurring while sending emails to employee who add new request rejected by manager..... " + ExceptionUtils.getStackTrace(e));
		}
	}

	/* Reject New Request By Manager */
	@GetMapping(value = "/rejectNew/{id}")
	public String rejectNewRequest(@ModelAttribute CheckInCheckOut checkInCheckOut, @PathVariable(name = "id") Long id, Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			model.addAttribute("addReq", chkInChkOutService.getAcceptLeaveById(id));
			if (checkInCheckOut != null) {
				String approvalReq = "Reject";
				String status = "Absent";
				String isActive = "1";
				String flag = "N";
				checkInCheckOut.setApprovalReq(approvalReq);
				checkInCheckOut.setStatus(status);
				chkInChkOutService.acceptStatus(approvalReq, status, isActive, flag, id);

				rejectNewReqMail();
			} else {
				System.out.println("Nothing Happen ::::::");
				return "redirect:/approvalNewReqList";
			}
		} catch (Exception e) {
			LOG.error("Error occur while reject new request by manager ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - rejet request viewed id - " + id));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("New Request");
			auditRecord.setActivityCode("Reject Request");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/approvalNewReqList";
	}

	@GetMapping("/totalAbsentEmp")
	public String totalAbsentEmp(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		List<CheckInCheckOut> checkInCheckOuts = chkInChkOutService.getTotalAbsentEmp();
		try {
			if (Objects.nonNull(checkInCheckOuts)) {
				model.addAttribute("totalAbsentEmp", checkInCheckOuts);
			} else {
				System.out.println("Error occuring while get total absent employee");
			}
		} catch (Exception e) {
			LOG.error("Error occur while get list of total absent emp " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - view total absent employee"));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("Dashboard");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "checkincheckout/totalAbsentEmp";
	}

	@GetMapping("/totalAttendance")
	public String totalAttendance(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		List<CheckInCheckOut> checkInCheckOuts = chkInChkOutService.getTotalAttendance();
		try {
			if (Objects.nonNull(checkInCheckOuts)) {
				model.addAttribute("totalAttendance", checkInCheckOuts);
			} else {
				System.out.println("Error occuring while get total attendance employee");
			}
		} catch (Exception e) {
			LOG.error("Error occur while get list of total attendance emp " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - view total attendance of employee"));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("Dashboard");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "checkincheckout/totalAttendance";
	}

	@GetMapping("/totalNewReq")
	public String totalNewReq(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		List<CheckInCheckOut> checkInCheckOuts = chkInChkOutService.getTotalNewReq();
		try {
			if (Objects.nonNull(checkInCheckOuts)) {
				model.addAttribute("totalNewReq", checkInCheckOuts);
			} else {
				System.out.println("Error occuring while get total new req");
			}
		} catch (Exception e) {
			LOG.error("Error occur while get list of total new req " + ExceptionUtils.getStackTrace(e));
		}  finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - view new request of employee"));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("Dashboard");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "checkincheckout/totalNewReq";
	}
	
	
	/* Display Data for Employee Weekly */
	@GetMapping(value = "/nextWeekButton")
	public String getNextWeekButton(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String userId = null;
		try {
			LocalDate today = LocalDate.now();
			// Go backward to get Monday
			LocalDate monday = today;
			while (monday.getDayOfWeek() != DayOfWeek.MONDAY) {
				monday = monday.minusDays(1);
			}
			// Go forward to get Sunday
			LocalDate sunday = today;
			while (sunday.getDayOfWeek() != DayOfWeek.SUNDAY) {
				sunday = sunday.plusDays(1);
			}
			LocalDate startingDate = monday.minusDays(7);

			String startDate = startingDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			String endDate = monday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));			
			
			
			System.out.println("Starting Date of Previous Week :::::::::::: " + startDate);
			
			/* Current user */
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			userId = authentication.getName();
			List<CheckInCheckOut> userDetails = null;
			if (userId != null) {
				userDetails = checkInCheckOutRepository.nextButton(startDate, endDate, userId);
				if (userDetails != null) {
					model.addAttribute("weeklyList", userDetails);
				} else {
					return "checkincheckout/weeklyEmpData";
				}
			} else {
				return "checkincheckout/weeklyEmpData";
			}
		} catch (Exception e) {
			LOG.error("Error occur while display weekly list " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed attendance in employee weekly data"));
			auditRecord.setMenuCode("Employee Administrator");
			auditRecord.setSubMenuCode("Employee Weekly Data");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "checkincheckout/weeklyEmpData";
	}
}