package com.ksvsofttech.product.controller;

import java.util.Collections;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ksvsofttech.product.entities.AuditRecord;
import com.ksvsofttech.product.entities.BranchMst;
import com.ksvsofttech.product.entities.RoleMst;
import com.ksvsofttech.product.entities.UserMst;
import com.ksvsofttech.product.service.AuditRecordService;
import com.ksvsofttech.product.service.BranchService;
import com.ksvsofttech.product.service.RoleService;

@Controller
public class AuditRecordController {
	private static final Logger LOGGER = LogManager.getLogger(AuditRecordController.class);

	@Autowired
	private BranchService branchService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private AuditRecordService auditRecordService;

	/* Audit Record List */
	@GetMapping(value = "/auditRecordList")
	public String homeAllRecord(AuditRecord auditRecord, Model model, String from, String to, String loginId,
			String branchName, Device device) throws Exception {
		try {
			UserMst userMst = new UserMst();
			userMst.setLoginId("All");
			List<UserMst> userMst2 = auditRecordService.getAllUser();
			userMst2.add(userMst);
			Collections.sort(userMst2, UserMst.Comparators.LOGINID);
			model.addAttribute("allUsers", userMst2);

			BranchMst branchMst = new BranchMst();
			branchMst.setBranchName("All");
			List<BranchMst> branchMst2 = branchService.getIsActiveBranchs();
			branchMst2.add(branchMst);
			Collections.sort(branchMst2, BranchMst.Comparators.BRANCH);
			model.addAttribute("listBranch", branchMst2);

		} catch (Exception e) {
			LOGGER.error("Error occur while display audit record list " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed audit record list"));
			auditRecord.setMenuCode("Audit Record");
			auditRecord.setSubMenuCode("Audit Listing Report");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "auditrecord/auditrecordlist";
	}

	/* Search Data using fromDate, toDate, loginId & branchName */
	@GetMapping(value = "/between/fromortoorloginIdorbranchName")
	public String getAllRecord(@RequestParam("from") String from, @RequestParam("to") String to,
			@RequestParam String loginId, @RequestParam String branchName, Model model, Device device,
			RedirectAttributes redirAttrs) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			List<AuditRecord> auditRecords = auditRecordService.findByfromDateBetweenorloginIdorbranchName(from, to,
					loginId, branchName);

			UserMst userMst = new UserMst();
			userMst.setLoginId("All");
			List<UserMst> userMst2 = auditRecordService.getAllUser();
			userMst2.add(userMst);
			Collections.sort(userMst2, UserMst.Comparators.LOGINID);
			model.addAttribute("allUsers", userMst2);

			BranchMst branchMst = new BranchMst();
			branchMst.setBranchName("All");
			List<BranchMst> branchMst2 = branchService.getIsActiveBranchs();
			branchMst2.add(branchMst);
			Collections.sort(branchMst2, BranchMst.Comparators.BRANCH);
			model.addAttribute("listBranch", branchMst2);
			model.addAttribute("listAuditRecord", auditRecords);
		} catch (Exception e) {
			LOGGER.error("Error occur while search audit record... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - search viewed audit record list"));
			auditRecord.setMenuCode("Audit Record");
			auditRecord.setActivityCode("SEARCH-VIEW");
			auditRecordService.save(auditRecord, device);
		}
		redirAttrs.addFlashAttribute("success", "  Also select Username/Branch.... ");
		return "auditrecord/auditrecordlist";
	}

	/* User Audit Record List */
	@GetMapping(value = "/userAudit")
	public String homeAllUserRecord(AuditRecord auditRecord, Model model, String from, String to, String loginId,
			Device device) throws Exception {
		try {
			UserMst userMst = new UserMst();
			userMst.setLoginId("All");			
			List<UserMst> userMst2 = auditRecordService.getAllUser();
			userMst2.add(userMst);
			Collections.sort(userMst2, UserMst.Comparators.LOGINID);
			model.addAttribute("allUsers", userMst2);

		} catch (Exception e) {
			LOGGER.error("Error occur while display user audit list " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed user audit record list"));
			auditRecord.setMenuCode("Audit Record");
			auditRecord.setSubMenuCode("User Audit");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "auditrecord/userauditrecordlist";
	}

	/* Search Data using fromDate, toDate & loginId */
	@GetMapping(value = "/between/fromortoorloginId")
	public String getAllUserRecord(@RequestParam("from") String from, @RequestParam("to") String to,
			@RequestParam String loginId, Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			List<AuditRecord> auditRecordList = auditRecordService.findByfromDateBetweenorloginId(from, to, loginId);

			UserMst userMst = new UserMst();
			userMst.setLoginId("All");
			List<UserMst> userMst2 = auditRecordService.getAllUser();
			userMst2.add(userMst);
			Collections.sort(userMst2, UserMst.Comparators.LOGINID);
			model.addAttribute("allUsers", userMst2);

			model.addAttribute("listAuditRecord", auditRecordList);

		} catch (Exception e) {
			LOGGER.error("Error occur while search user audit record... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - search viewed user audit record list"));
			auditRecord.setMenuCode("Audit Record");
			auditRecord.setSubMenuCode("User Audit");
			auditRecord.setActivityCode("SEARCH-VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "auditrecord/userauditrecordlist";
	}

	/* Role Audit Record List */
	@GetMapping(value = "/roleAudit")
	public String homeAllRoleRecord(AuditRecord auditRecord, Model model, String from, String to, String roleCode,
			Device device) throws Exception {
		try {
			RoleMst roleMst = new RoleMst();
			roleMst.setRoleCode("All");
			List<RoleMst> listRoleMst = roleService.getIsActive();
			listRoleMst.add(roleMst);
			Collections.sort(listRoleMst, RoleMst.Comparators.ROLECODE);
			model.addAttribute("listRole", listRoleMst);

		} catch (Exception e) {
			LOGGER.error("Error occur while display role audit list " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed role audit record list"));
			auditRecord.setMenuCode("Audit Record");
			auditRecord.setSubMenuCode("Role Audit");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "auditrecord/rolebasedauditrecord";
	}

	/* Search Data using fromDate, toDate & roleCode */
	@GetMapping(value = "/between/fromortoorroleCode")
	public String getAllRoleRecord(@RequestParam("from") String from, @RequestParam("to") String to,
			@RequestParam String roleCode, Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			List<AuditRecord> auditRecordList = auditRecordService.findByfromDateBetweenorroleCode(from, to, roleCode);
			RoleMst roleMst = new RoleMst();
			roleMst.setRoleCode("All");
			List<RoleMst> listRoleMst = roleService.getIsActive();
			listRoleMst.add(roleMst);
			Collections.sort(listRoleMst, RoleMst.Comparators.ROLECODE);
			model.addAttribute("listRole", listRoleMst);

			model.addAttribute("listAuditRecord", auditRecordList);
		} catch (Exception e) {
			LOGGER.error("Error occur while search role audit record... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - search viewed role audit record list"));
			auditRecord.setMenuCode("Audit Record");
			auditRecord.setSubMenuCode("Role Audit");
			auditRecord.setActivityCode("SEARCH-VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "auditrecord/rolebasedauditrecord";
	}
}
