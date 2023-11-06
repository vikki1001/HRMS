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
import com.ksvsofttech.product.entities.RoleMst;
import com.ksvsofttech.product.service.AuditRecordService;
import com.ksvsofttech.product.service.RoleService;
import com.ksvsofttech.product.validator.RoleValidator;

@Controller
public class RoleController {
	private static final Logger LOGGER = LogManager.getLogger(RoleController.class);

	@Autowired
	private RoleValidator roleMstValidator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(roleMstValidator);
	}
	@Autowired
	private RoleService roleService;
	@Autowired
	private AuditRecordService auditRecordService;

	/* Display List of Registered Role */
	@GetMapping(value = "/activeRoleList")
	public String getIsActiveRoles(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			model.addAttribute("listRole", roleService.getIsActive());
		} catch (Exception e) {
			LOGGER.error("Error occur to display active role list... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed active role list"));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Role Management");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "role/activerolelist";
	}

	/* For Inactive List */
	@GetMapping(value = "/inActiveRoleList")
	public String inactiveRole(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			model.addAttribute("listRole", roleService.getInActive());
		} catch (Exception e) {
			LOGGER.error("Error occur to display inactive role list... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed inactive role list"));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Role Management");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "role/inactiverolelist";
	}

	@GetMapping(value = "/roleForm")
	public String saveRole(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		/* Create Model attribute to bind form data */
		RoleMst mst = new RoleMst();
		try {
			model.addAttribute("roleAttribute", mst);
		} catch (Exception e) {
			LOGGER.error("Error occur to open role registration page... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed role management"));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Role Management");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "role/newrole";
	}

	@PostMapping(value = "/saveRole")
	public String successRole(@Valid @ModelAttribute("roleAttribute") RoleMst roleMst, BindingResult result,
			Model model, Device device, RedirectAttributes redirAttrs) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			if (roleService.roleExists(roleMst.getRoleCode())) {
				result.addError(new FieldError("roleMst", "roleCode", "Role Code already in use"));
			}
			if (result.hasErrors()) {
				List<ObjectError> allErrors = result.getAllErrors();
				for (ObjectError temp : allErrors) {
					System.out.println(temp);
				}
				return "role/newrole";
			} else {
				/* Save Roles to Database */
				roleService.save(roleMst);
				redirAttrs.addFlashAttribute("success", "Registration Completed Successfully");
				return "redirect:/activeRoleList";
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while role registration successfully... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - create role successfully"));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Role Management");
			auditRecord.setActivityCode("CREATE");
			auditRecordService.save(auditRecord, device);
		}
		return "role/newrole";
	}

	/* Update of Registered Role */
	@GetMapping(value = "/roleUpdate/{roleId}")
	public ModelAndView roleUpdate(@PathVariable(name = "roleId") long roleId, Device device) throws Exception {
		ModelAndView mav = new ModelAndView("role/updaterole");
		AuditRecord auditRecord = new AuditRecord();
		try {
			RoleMst roleMST = roleService.getRoleById(roleId);
			mav.addObject("updateRoleAttribute", roleMST);
		} catch (Exception e) {
			LOGGER.error("Error occur while display edit registration page ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed role id - " + roleId));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Role Management");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return mav;
	}

	@PostMapping(value = "/updateRole")
	public String updateRole(@Valid @ModelAttribute("updateRoleAttribute") RoleMst roleMst, BindingResult result,
			Model model, Device device, RedirectAttributes redirAttrs) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			if (result.hasErrors()) {
				List<ObjectError> allErrors = result.getAllErrors();
				for (ObjectError temp : allErrors) {
					System.out.println(temp);
				}
				return "role/updaterole";
			} else {
				/* Save Roles to Database */
				roleService.save(roleMst);
				redirAttrs.addFlashAttribute("success", "Update Registration Successfully");
				return "redirect:/activeRoleList";
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while update role registration successfully... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - update registered role "));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Role Management");
			auditRecord.setActivityCode("UPDATE");
			auditRecordService.save(auditRecord, device);
		}
		return "role/updaterole";
	}

	/* Deactivate of Registered Role */
	@GetMapping(value = "/deactiveRole/{roleId}")
	public String deactiveRole(RoleMst roleMst, @PathVariable(name = "roleId") long roleId, Device device)
			throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			roleService.deactiveRole(roleMst);
		} catch (Exception e) {
			LOGGER.error("------Error occur while inactivate role-----" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("No Role inactivate" + roleId);
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - deactivate role role id - " + roleId));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Role Management");
			auditRecord.setActivityCode("DEACTIVATE");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/activeRoleList";
	}

	/* Activate of Registered Role */
	@GetMapping(value = "/activeRole/{roleId}")
	public String activeRole(RoleMst roleMst, @PathVariable(name = "roleId") long roleId, Device device)
			throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			roleService.activateRole(roleMst);
		} catch (Exception e) {
			LOGGER.error("------Error occur while activate role-----" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("No Role activate" + roleId);
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - activate role by role id - " + roleId));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Role Management");
			auditRecord.setActivityCode("ACTIVATE");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/inActiveRoleList";
	}
}
