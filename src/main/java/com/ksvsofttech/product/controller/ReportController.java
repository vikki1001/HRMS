package com.ksvsofttech.product.controller;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ksvsofttech.product.entities.AuditRecord;
import com.ksvsofttech.product.entities.CheckInCheckOut;
import com.ksvsofttech.product.entities.EmpBasicDetails;
import com.ksvsofttech.product.entities.LeaveMst;
import com.ksvsofttech.product.service.AuditRecordService;
import com.ksvsofttech.product.service.CheckInCheckOutService;
import com.ksvsofttech.product.service.EmpRegistartionService;
import com.ksvsofttech.product.service.LeaveService;
import com.ksvsofttech.product.service.ReportService;

@Controller
public class ReportController {
	private static final Logger LOG = LogManager.getLogger(ReportController.class);

	@Autowired
	private ReportService reportService;
	@Autowired
	private EmpRegistartionService empRegistartionService;
	@Autowired
	private CheckInCheckOutService chkInChkOutService;
	@Autowired
	private AuditRecordService auditRecordService;
	@Autowired
	private LeaveService leaveService;
	
	/* Display List of CheckInOut */
	@GetMapping(value = "/empTotalSummaryReport")
	public String checkInCheckOutList(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			EmpBasicDetails basicDetails = new EmpBasicDetails();
			basicDetails.setEmpId("All");
			List<EmpBasicDetails> empBasicDetails = empRegistartionService.getFindAllEmpRegList();
			empBasicDetails.add(basicDetails);
			Collections.sort(empBasicDetails, EmpBasicDetails.Comparators.EMPLOYEEID);			
			model.addAttribute("empIdList", empBasicDetails);
			
//			EmpBasicDetails basicDetails2 = new EmpBasicDetails();
//			basicDetails2.setTenantId("All");
			
			List<String> empBasicDetails2 = empRegistartionService.getUniqueTenantId();
			empBasicDetails2.add("All");
			Collections.sort(empBasicDetails2, Collections.reverseOrder());
			//Collections.sort(empBasicDetails2, EmpBasicDetails.Comparators.TENANTID);
			model.addAttribute("tenantIdList", empBasicDetails2);

		} catch (Exception e) {
			LOG.error("Error occur while display checkInOut list " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed emp time summary report"));
			auditRecord.setMenuCode("Report");
			auditRecord.setSubMenuCode("Employee FTime Summary Report");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "report/empsummarylist";
	}

	/* Search data using from date, to date, userId & tenantId */
	@GetMapping(value = "/between/fromortooruserIdortenantId")
	public String getfromortooruserId(Model model, @RequestParam String from, @RequestParam String to,
			@RequestParam String userId, @RequestParam String tenantId, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			List<CheckInCheckOut> inCheckOuts = chkInChkOutService.findByDateorUserIdorTenantId(from, to, userId, tenantId);

			EmpBasicDetails basicDetails = new EmpBasicDetails();
			basicDetails.setEmpId("All");
			List<EmpBasicDetails> empBasicDetails = empRegistartionService.getFindAllEmpRegList();
			empBasicDetails.add(basicDetails);
			Collections.sort(empBasicDetails, EmpBasicDetails.Comparators.EMPLOYEEID);			
			model.addAttribute("empIdList", empBasicDetails);
			
//			EmpBasicDetails basicDetails2 = new EmpBasicDetails();
//			basicDetails2.setTenantId("All");
			
			List<String> empBasicDetails2 = empRegistartionService.getUniqueTenantId();
			empBasicDetails2.add("All");
			Collections.sort(empBasicDetails2, Collections.reverseOrder());
			//Collections.sort(empBasicDetails2, EmpBasicDetails.Comparators.TENANTID);
			model.addAttribute("tenantIdList", empBasicDetails2);

			model.addAttribute("checkInCheckOutList", inCheckOuts);
		} catch (Exception e) {
			LOG.error("Error occur while display checkInOut list " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - search-view emp time summary report"));
			auditRecord.setMenuCode("Report");
			auditRecord.setSubMenuCode("Employee Time Summary Report");
			auditRecord.setActivityCode("SEARCH-VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "report/empsummarylist";
	}

	/* Display List of CheckInOut */
	@GetMapping(value = "/empSummaryReport")
	public String empSummaryReport(String empId, String fullName, String departName, String grade, Model model,
			Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			if (empId != null || fullName != null || departName != null || grade != null) {
				System.out.println("If :::::::::::::");
				model.addAttribute("empBasicList", reportService.getBySearch(empId, fullName, departName, grade));
			} else {
				System.out.println("else :::::::::::::");
				model.addAttribute("empBasicList", empRegistartionService.getIsActiveEmpRegList());
			}
		} catch (Exception e) {
			LOG.error("Error occur while display employee summary report list " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - search-view emp summary report"));
			auditRecord.setMenuCode("Report");
			auditRecord.setSubMenuCode("Employee Summary Report");
			auditRecord.setActivityCode("SEARCH-VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "report/empsummaryreport";
	}

	/* Display List of Leave Summary Report */
	@GetMapping(value = "/leaveSummaryReport")
	public String leaveSummaryReport(Model model, @ModelAttribute LeaveMst leaveMst, BindingResult result,
			Device device, @Param("leaveType") String leaveType) throws Exception {
	AuditRecord auditRecord = new AuditRecord();
		try {
			//for display all active leave 
			model.addAttribute("leaveSummaryList", reportService.activeLeave());
		} catch (Exception e) {
			LOG.error("Error occur while display leave summary page " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - view emp leave summary home page"));
			auditRecord.setMenuCode("Report");
			auditRecord.setSubMenuCode("Leave Summary Report");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "report/leaveSummary";
	}

	/* Display List of Leave Summary Report */
	@PostMapping(value = "/leaveSummary")
	public String getLeaveSummaryReport(Model model, @ModelAttribute LeaveMst leaveMst, BindingResult result,
			Device device, @Param("leaveType") String leaveType) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			//search then display all active & type of leaves
			if (leaveType != null) {
				model.addAttribute("leaveSummaryList", leaveService.getLeaveByLeaveType(leaveType));
			} else {
				// Do Nothing
			}
		} catch (Exception e) {
			LOG.error("Error occur while display leave summary list " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - search-view emp leave summary report"));
			auditRecord.setMenuCode("Report");
			auditRecord.setSubMenuCode("Leave Summary Report");
			auditRecord.setActivityCode("SEARCH-VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "report/leaveSummary";
	}

	/* Display Leave Approval Report Page */
	@GetMapping(value = "/leaveApprovalReport")
	public String leaveApprovalReport(@ModelAttribute LeaveMst leaveMst, BindingResult result, Model model,
			Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			//for display all approved & active leave 
			model.addAttribute("leaveApprovalList", reportService.getApprovedLeave());
		} catch (Exception e) {
			LOG.error("Error occur while display leave summary page " + ExceptionUtils.getStackTrace(e));
		}  finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - view emp leave approval home page"));
			auditRecord.setMenuCode("Report");
			auditRecord.setSubMenuCode("Leave Approval Report");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "report/leaveApproval";
	}

	/* Display List of Leave Approval Report */
	@PostMapping(value = "/leaveApproval")
	public String getLeaveApprovalReport(@ModelAttribute LeaveMst leaveMst, BindingResult result, Model model,
			Device device, @Param("leaveType") String leaveType) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			//search then display all approved, active & type of leave
			if (leaveType != null) {
				model.addAttribute("leaveApprovalList", reportService.getApprovedAndLeaveType(leaveType));
			} else {
				// Do Nothing
			}
		} catch (Exception e) {
			LOG.error("Error occur while display leave summary list " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - search-view emp leave approval report"));
			auditRecord.setMenuCode("Report");
			auditRecord.setSubMenuCode("Leave Approval Report");
			auditRecord.setActivityCode("SEARCH-VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "report/leaveApproval";
	}
}
