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
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ksvsofttech.product.entities.AuditRecord;
import com.ksvsofttech.product.entities.TenantConfig;
import com.ksvsofttech.product.service.AuditRecordService;
import com.ksvsofttech.product.service.TenantConfigService;
import com.ksvsofttech.product.validator.TenantConfigValidator;

@Controller
public class TenantConfigController {
	private static final Logger LOGGER = LogManager.getLogger(TenantConfigController.class);

	@Autowired
	private TenantConfigValidator tenantConfigValidator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {	
	binder.setValidator(tenantConfigValidator);
	}
	
	@Autowired
	private TenantConfigService tenantConfigService;
	@Autowired
	private AuditRecordService auditRecordService;

	@GetMapping(value = "/tenantConfigForm")
	public String form(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			model.addAttribute("tenantConfigAttribute", new TenantConfig());
		} catch (Exception e) {
			LOGGER.error("Error occur while display tenant config page... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed tenant config form"));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Tenant Config Management");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "tenantconfig/tenantconfig";
	}

	@PostMapping(value = "/saveTenantConfig")
	public String Saveonfig(@Valid @ModelAttribute("tenantConfigAttribute") TenantConfig tenantConfig,
			BindingResult result, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			if (result.hasErrors()) {
				List<ObjectError> allErrors = result.getAllErrors();
				for (ObjectError temp : allErrors) {
					System.out.println(temp);
				}
				return "tenantconfig/tenantconfig";
			} else {
				/* Save Tenant Config to Database */
				tenantConfigService.saveTenantConfig(tenantConfig);
				return "success";
			}
		} catch (Exception e) {
			LOGGER.error("Error occur to save tenantconfig ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - create tenant config in tenant config management"));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Tenant Config Management");
			auditRecord.setActivityCode("CREATE");
			auditRecordService.save(auditRecord, device);
		}
		return "tenantconfig/tenantconfig";
	}
}
