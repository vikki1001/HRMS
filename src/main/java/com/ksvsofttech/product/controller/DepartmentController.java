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
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ksvsofttech.product.entities.AuditRecord;
import com.ksvsofttech.product.entities.DepartmentMst;
import com.ksvsofttech.product.service.AuditRecordService;
import com.ksvsofttech.product.service.DepartmentService;
import com.ksvsofttech.product.service.RoleService;
import com.ksvsofttech.product.validator.DepartmentValidator;

@Controller
public class DepartmentController {
	private static final Logger LOGGER = LogManager.getLogger(DepartmentController.class);

	@Autowired
	private DepartmentValidator departmentValidator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(departmentValidator);
	}

	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private AuditRecordService auditRecordService;

	@GetMapping(value = "/activeDepartment")
	public String getIsActiveDepartment(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			model.addAttribute("listdepartment", departmentService.getIsActive());
		} catch (Exception e) {
			LOGGER.error("Error occur to display active department list... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed active department list"));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Department Management");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "department/activedepartment";
	}

	@GetMapping(value = "/inActiveDepartment")
	public String getInActiveDepartment(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			model.addAttribute("listdepartment", departmentService.getInActive());
		} catch (Exception e) {
			LOGGER.error("Error occur to display inactive department list... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed inActive department list"));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Department Management");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "department/inactivedepartment";
	}

	@GetMapping(value = "/department")
	public String department(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		DepartmentMst departmentMst = new DepartmentMst();
		try {
			model.addAttribute("listRole", roleService.getIsActive());
			model.addAttribute("departmentMst", departmentMst);
		} catch (Exception e) {
			LOGGER.error("Error occur to open department registration page... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed department management form"));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Department Management");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "department/newdepartment";
	}

	@PostMapping(value = "/saveDepartment")
	public String successDepartment(@Valid @ModelAttribute DepartmentMst departmentMst, BindingResult result,
			Model model, Device device, RedirectAttributes redirAttrs) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			if (departmentService.departmentExists(departmentMst.getDepartmentName())) {
				result.addError(new FieldError("departmentMst", "departmentName", "Departmenet Name already in use"));
			}
			model.addAttribute("listRole", roleService.getIsActive());
			if (result.hasErrors()) {
				List<ObjectError> allErrors = result.getAllErrors();
				for (ObjectError temp : allErrors) {
					System.out.println(temp);
				}
				return "department/newdepartment";
			} else {
				/* Save Roles to Database */
				departmentService.save(departmentMst);
				redirAttrs.addFlashAttribute("success", "Registration Completed Successfully");
				return "redirect:/activeDepartment";
			}
		} catch (Exception e) {
			LOGGER.error(
					"Error occur while department registration successfully... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - create new department"));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Department Management");
			auditRecord.setActivityCode("CREATE");
			auditRecordService.save(auditRecord, device);
		}

		return "department/newdepartment";
	}

	@GetMapping(value = "/departmentUpdate/{departmentId}")
	public ModelAndView DepartmentUpdate(@PathVariable(name = "departmentId") Long departmentId, Model model,
			Device device) throws Exception {
		ModelAndView mav = new ModelAndView("department/updatedepartment");
		AuditRecord auditRecord = new AuditRecord();
		try {
			// model.addAttribute("listRole", roleService.getIsActive());
			DepartmentMst departmentMst = departmentService.getByDepartmentId(departmentId);
			mav.addObject("listRole", roleService.getIsActive());
			mav.addObject("departmentMst", departmentMst);
		} catch (Exception e) {
			LOGGER.error("Error occur while display edit registered department page ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed department id - " + departmentId));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Department Management");
			auditRecordService.save(auditRecord, device);
		}
		return mav;
	}

	@PostMapping(value = "/updateDepartment")
	public String updateDepartment(@Valid @ModelAttribute DepartmentMst departmentMst, BindingResult result,
			Model model, Device device, RedirectAttributes redirAttrs) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			model.addAttribute("listRole", roleService.getIsActive());
			if (result.hasErrors()) {
				List<ObjectError> allErrors = result.getAllErrors();
				for (ObjectError temp : allErrors) {
					System.out.println(temp);
				}
				return "department/updatedepartment";
			} else {
				/* Save Roles to Database */
				departmentService.save(departmentMst);
				redirAttrs.addFlashAttribute("success", "Update Registration Successfully");
				return "redirect:/activeDepartment";
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while update registration successfully... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - update registered department"));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Department Management");
			auditRecord.setActivityCode("UPDATE");
			auditRecordService.save(auditRecord, device);
		}

		return "department/updatedepartment";
	}

	@GetMapping(value = "/deactivedepartment/{departmentId}")
	public String deactiveDepartment(DepartmentMst departmentMst,
			@PathVariable(name = "departmentId") Long departmentId, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			departmentService.deactiveDepartment(departmentMst);
		} catch (Exception e) {
			LOGGER.error("------Error occur while activate department-----" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("No Role inactivate" + departmentId);
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - deactivate department id - " + departmentId));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Department Management");
			auditRecord.setActivityCode("DEACTIVATE");
			auditRecordService.save(auditRecord, device);
		}

		return "redirect:/activeDepartment";
	}

	/* Activate of Registered Role */
	@GetMapping(value = "/activedepartment/{departmentId}")
	public String activeDepartment(DepartmentMst departmentMst, @PathVariable(name = "departmentId") Long departmentId,
			Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			departmentService.activateDepartment(departmentMst);
		} catch (Exception e) {
			LOGGER.error("------Error occur while inactivate department -----" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("No Role inactivate" + departmentId);
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - activate department id - " + departmentId));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Department Management");
			auditRecord.setActivityCode("ACTIVATE");
			auditRecordService.save(auditRecord, device);
		}

		return "redirect:/inActiveDepartment";
	}
}
