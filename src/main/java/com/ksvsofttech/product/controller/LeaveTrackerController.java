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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ksvsofttech.product.entities.AuditRecord;
import com.ksvsofttech.product.entities.LeaveTracker;
import com.ksvsofttech.product.service.AuditRecordService;
import com.ksvsofttech.product.service.LeaveTrackerService;

@Controller
public class LeaveTrackerController {
	private static final Logger LOGGER = LogManager.getLogger(LeaveTrackerController.class);

	@Autowired
	private LeaveTrackerService leaveTrackerService;
	@Autowired
	private AuditRecordService auditRecordService;

	@GetMapping(value = "/activeAddLeaveList")
	public String activeAddLeaveList(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			List<LeaveTracker> userDetails = null;
			if (empId != null) {
				userDetails = leaveTrackerService.getActiveList();
				if (userDetails != null) {
					model.addAttribute("addLeaveList", userDetails);
					return "leaveTracker/activeAddLeaveList";
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error occur to get active add leave... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed active leave list"));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("Add Leave");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "leaveTracker/activeAddLeaveList";
	}

	@GetMapping(value = "/inActiveAddLeaveList")
	public String inActiveAddLeaveList(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			List<LeaveTracker> userDetails = null;
			if (empId != null) {
				userDetails = leaveTrackerService.getInActiveList();
				if (userDetails != null) {
					model.addAttribute("addLeaveList", userDetails);
					return "leaveTracker/inActiveAddLeaveList";
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error occur to get active add leave... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed inActive leave list"));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("Add Leave");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "leaveTracker/inActiveAddLeaveList";
	}

	@GetMapping(value = "/cancelAddLeaveList/{id}")
	public String getCancelAddLeave(LeaveTracker leaveTracker, @PathVariable(name = "id") Long id, Model model,
			Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empId = null;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			LeaveTracker userDetails = null;
			if (empId != null) {
				userDetails = leaveTrackerService.cancelAddLeave(leaveTracker);
				if (userDetails != null) {
					model.addAttribute("addLeaveList", userDetails);
					return "redirect:/activeAddLeaveList";
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while display cancel leave list" + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - cancel leave by id " + id));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("Add Leave");
			auditRecord.setActivityCode("CANCEL");
			auditRecordService.save(auditRecord, device);
		}
		return "leaveTracker/activeAddLeaveList";
	}

	@GetMapping(value = "/addLeaveForm")
	public String addLeaveForm(@ModelAttribute("leaveTracker") LeaveTracker leaveTracker, BindingResult bindingResult,
			Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			List<LeaveTracker> userDetails = null;
			if (empId != null) {
				userDetails = leaveTrackerService.getActiveList();
				if (userDetails != null) {
					model.addAttribute("addLeaveList", userDetails);
				} else {
					LOGGER.info("Nothing Happen/Error ::::::");
					return "leaveTracker/addLeave";
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error occur to get expense page... " + ExceptionUtils.getStackTrace(e));
		}  finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed add leave home page"));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("Add Leave");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "leaveTracker/addLeave";
	}

	@PostMapping(value = "/saveAddLeave")
	public String saveAddLeave(@ModelAttribute LeaveTracker leaveTracker, BindingResult result, Model model,
			Device device, RedirectAttributes redirAttrs) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			List<String> empId = leaveTrackerService.getAllEmp();
			float getLeave = leaveTracker.getAddLeave();

			if (Objects.nonNull(empId) && Objects.nonNull(getLeave)) {
				if (leaveTracker.getLeaveType().equals("Total Leave")) {
					System.out.println("INSIDE TOTAL LEAVE :::::::: ");
					List<Float> tl = leaveTrackerService.getAllEmpTotalLeave();
					for (int i = 0; i < tl.size(); i++) {
						float totalleave = tl.get(i) + getLeave;
						leaveTrackerService.updateTotalLeave(totalleave, empId.get(i));
					}
					redirAttrs.addFlashAttribute("success", "Save Data Successfully");
					return "redirect:/activeAddLeaveList";
				} else if (leaveTracker.getLeaveType().equals("Paid Leave")) {
					System.out.println("INSIDE PAID LEAVE :::::::: ");
					List<Float> pl = leaveTrackerService.getAllEmpPaidLeave();
					for (int i = 0; i < pl.size(); i++) {
						float paidleave = pl.get(i) + getLeave;
						leaveTrackerService.updatePaidLeave(paidleave, empId.get(i));
					}
					redirAttrs.addFlashAttribute("success", "Save Data Successfully");
					return "redirect:/activeAddLeaveList";
				} else {
					System.out.println("INSIDE MATERNITY LEAVE :::::::: ");
					List<String> marriedEmpOnly = leaveTrackerService.getMarriedEmployee();
					List<Float> ml = leaveTrackerService.getAllEmpMaternityLeave();
					for (int i = 0; i < ml.size(); i++) {
						float maternityLeave = ml.get(i) + getLeave;
						System.out.println("Maternity Leave  " + maternityLeave + " -- " + marriedEmpOnly.get(i));
						leaveTrackerService.updateMaternityLeave(maternityLeave, marriedEmpOnly.get(i));
					}
					redirAttrs.addFlashAttribute("success", "Save Data Successfully");
					return "redirect:/activeAddLeaveList";
				}
			} else {
				System.out.println("Employee Id & Get Leave null :::::: ");
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while save successfully data  ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - create new leave successfully"));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("Add Leave");
			auditRecord.setActivityCode("CREATE");
			auditRecordService.save(auditRecord, device);
		}
		//return "leaveTracker/addLeave";
		return "redirect:/activeAddLeaveList";
	}
}