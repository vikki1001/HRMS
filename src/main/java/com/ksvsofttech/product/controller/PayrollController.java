package com.ksvsofttech.product.controller;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.compress.utils.IOUtils;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ksvsofttech.product.entities.AuditRecord;
import com.ksvsofttech.product.entities.EmpBasicDetails;
import com.ksvsofttech.product.entities.PayrollMst;
import com.ksvsofttech.product.entities.TenantMst;
import com.ksvsofttech.product.methodUtils.PayrollPDFGenerator;
import com.ksvsofttech.product.repository.TenantMstRepository;
import com.ksvsofttech.product.service.AuditRecordService;
import com.ksvsofttech.product.service.EmpRegistartionService;
import com.ksvsofttech.product.service.PayrollService;

@Controller
public class PayrollController {
	private static final Logger LOGGER = LogManager.getLogger(PayrollController.class);

	@Autowired
	private AuditRecordService auditRecordService;
	@Autowired
	private PayrollService payrollService;
	@Autowired
	private EmpRegistartionService empRegistartionService;
	@Autowired
	private TenantMstRepository tenantMstRepository;

	/* List of uploaded records */
	@GetMapping("/payrollSlipList")
	public String payrollSlipList(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			List<PayrollMst> payrollMstList = payrollService.findAll();
			if (Objects.nonNull(payrollMstList)) {
				model.addAttribute("payrollSlipList", payrollMstList);
			} else {
				System.out.println("List is null ::::::: ");
			}
		} catch (Exception e) {
			LOGGER.error("Error occuring to display salary slip page ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - payroll list"));
			auditRecord.setMenuCode("USER MANAGEMENT");
			auditRecord.setSubMenuCode("Salary Slip");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "payroll/payrollSlipList";
	}

	/* Payroll Home Page under in HR */
	@GetMapping("/payroll")
	public String payroll(PayrollMst payrollMst, Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			model.addAttribute("payrollMst", payrollMst);
		} catch (Exception e) {
			LOGGER.error("Error occuring to open payroll page... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed payroll home page"));
			auditRecord.setMenuCode("HR MANAGEMENT");
			auditRecord.setSubMenuCode("Payroll");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "payroll/payroll";
	}

	/* Save/Upload record into DB */
	@PostMapping("/payroll")
	public String importExcelFile(@RequestParam("file") MultipartFile files, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			payrollService.saveAll(files);
		} catch (Exception e) {
			LOGGER.error("Error occuring to upload excel file ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - create payroll successfully"));
			auditRecord.setMenuCode("HR MANAGEMENT");
			auditRecord.setSubMenuCode("Payroll");
			auditRecord.setActivityCode("CREATE");
			auditRecordService.save(auditRecord, device);
		}

		return "redirect:/payrollSlipList";
	}

	/* Download Payroll Excel Format */
	@GetMapping("/downloadPayroll")
	public void downloadPayrollFile(HttpServletResponse response, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			List<PayrollMst> payrollMst = payrollService.findAll();
			ByteArrayInputStream byteArrayInputStream = payrollService.exportPayroll(payrollMst);
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=payroll " + new Date() + ".xlsx");
			IOUtils.copy(byteArrayInputStream, response.getOutputStream());
		} catch (Exception e) {
			LOGGER.error("Error occuring while download payroll formate ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - download payroll format"));
			auditRecord.setMenuCode("HR MANAGEMENT");
			auditRecord.setSubMenuCode("Payroll");
			auditRecord.setActivityCode("PAYROLL FORMAT");
			auditRecordService.save(auditRecord, device);
		}
	}

	/* View Payroll Slip Home Page */
	@GetMapping("/payrollSlipHome")
	public String homePayrollSlip(PayrollMst payrollMst, Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			model.addAttribute("payrollMst", payrollMst);
		} catch (Exception e) {
			LOGGER.error("Error occuring while displaying payroll Slip Home Page ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed payroll slip home page"));
			auditRecord.setMenuCode("EMPLOYEE MANAGEMENT");
			auditRecord.setSubMenuCode("Payroll");
			auditRecord.setActivityCode("PAYROLLSlip");
			auditRecordService.save(auditRecord, device);
		}
		return "payroll/payrollSlip";
	}

	/* View Payroll */
	@GetMapping(value = "/payrollSlip", params = "view")
	public String viewPayrollSlip(@RequestParam String month, @RequestParam String year, Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empId = null;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			if (Objects.nonNull(empId)) {
				List<PayrollMst> payrolls = payrollService.getPayrollSlipByMonthAndYear(month, year, empId);
				System.out.println(payrolls.toString());				
				model.addAttribute("empPayroll", payrolls);
			} else {
				System.out.println("Error occur to get current user ::::::::::");
			}
		} catch (Exception e) {
			LOGGER.error("Error occuring while view payroll Slip  ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed payroll slip by employee - " + userDetails.getUsername()));
			auditRecord.setMenuCode("EMPLOYEE MANAGEMENT");
			auditRecord.setSubMenuCode("Payroll");
			auditRecord.setActivityCode("PAYROLLSlip");
			auditRecordService.save(auditRecord, device);
		}
		return "payroll/payrollSlipView";
	}
	
//	
//	/* View Payroll */
//	@GetMapping(value = "/payrollSlip", params = "view")
//	public List<PayrollMst> viewPayrollSlip(@RequestParam String month, @RequestParam String year, Model model, Device device) throws Exception {
//		String empId = null;
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		empId = authentication.getName();
//		System.out.println("MONTH - " + month + " :::::::::: " + "YAER - " + year + " :::::::::: " + "EMPID - " + empId);
//		return payrollService.getPayrollSlipByMonthAndYear(month, year, empId);
//	}

	/* Payroll PDF Download */
	@GetMapping(value = "/payrollSlip", params = "download")
	public void exportToPDF(@RequestParam String month, @RequestParam String year, HttpServletResponse response, Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empId = null;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			if (Objects.nonNull(empId)) {
				response.setContentType("application/pdf");

				String headerKey = "Content-Disposition";
				String headerValue = "attachment; filename=salarySlip " + new Date() + ".pdf";
				response.setHeader(headerKey, headerValue);

				List<PayrollMst> payrollList = payrollService.getPayrollSlipByMonthAndYear(month, year, empId);
				EmpBasicDetails basicDetailsList = empRegistartionService.getCurrentUser(empId);
				TenantMst tenantMstList = tenantMstRepository.getTenantDetails(basicDetailsList.getTenantId());
				PayrollPDFGenerator exporter = new PayrollPDFGenerator(payrollList, basicDetailsList, tenantMstList);
				exporter.export(response);
			} else {
				System.out.println("Error occur to get current user ::::::::::");
			}
		} catch (Exception e) {
			LOGGER.error("Error occuring while download payroll Slip ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - downlaod payroll slip by employee - " + userDetails.getUsername()));
			auditRecord.setMenuCode("EMPLOYEE MANAGEMENT");
			auditRecord.setSubMenuCode("Payroll");
			auditRecord.setActivityCode("PAYROLLSlip");
			auditRecordService.save(auditRecord, device);
		}
	}
}