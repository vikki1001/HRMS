package com.ksvsofttech.product.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ksvsofttech.product.entities.AuditRecord;
import com.ksvsofttech.product.entities.RoleMst;
import com.ksvsofttech.product.service.AuditRecordService;
import com.ksvsofttech.product.service.RoleService;

@Controller
public class RoleHierarchyController {

private static final Logger LOGGER = LogManager.getLogger(RoleMenuActionAccessController.class);
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private AuditRecordService auditRecordService;

	/* For List Display */
	@RequestMapping(value = "/roleHierarchyList", method = RequestMethod.GET)
	public String getAllMenus(@ModelAttribute("roleMst") RoleMst roleMst, Model model, BindingResult result, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			List<RoleMst> role = roleService.getIsActive();
			model.addAttribute("listRole", role);
		} catch (Exception e) {
			LOGGER.error("Error occur while display role hierarcy list... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed role hierarchy list"));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Role Hierarcy Management");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "rolehierarchy/rolehierarchy";
	}
}
