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
import com.ksvsofttech.product.entities.BranchMst;
import com.ksvsofttech.product.service.AuditRecordService;
import com.ksvsofttech.product.service.BranchService;
import com.ksvsofttech.product.validator.BranchValidator;

@Controller
public class BranchController {
	private static final Logger LOGGER = LogManager.getLogger(BranchController.class);

	@Autowired
	private BranchValidator branchMstValidator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(branchMstValidator);
	}

	@Autowired
	private AuditRecordService auditRecordService;
	@Autowired
	private BranchService branchService;

	/* Display List of IsActive Branch */
	@GetMapping(value = "/activeBranchList")
	public String getIsActiveBranchs(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			model.addAttribute("listBranch", branchService.getIsActiveBranchs());
		} catch (Exception e) {
			LOGGER.error("Error occur while display active branch list " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed active branch list"));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Branch Management");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "branch/activebranchlist";
	}

	/* Display List of InActive Branch */
	@GetMapping(value = "/inActiveBranchList")
	public String getInActiveBranchs(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			model.addAttribute("listBranch", branchService.getInActiveBranchs());
		} catch (Exception e) {
			LOGGER.error("Error occur while display inactive branch list " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed inActive branch list"));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Branch Management");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "branch/inactivebranchlist";
	}

	@GetMapping(value = "/branchForm")
	public String saveBranch(Model model, Device device) throws Exception {
		BranchMst branchMst = new BranchMst();
		AuditRecord auditRecord = new AuditRecord();
		try {
			model.addAttribute("branchAttribute", branchMst);
		} catch (Exception e) {
			LOGGER.error("Error occur to open branch registration page... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed branch management form"));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Branch Management");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "branch/newbranch";
	}

	@PostMapping(value = "/saveBranch")
	public String successBranch(@Valid @ModelAttribute("branchAttribute") BranchMst mst, BindingResult result,
			Model model, Device device, RedirectAttributes redirAttrs) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			if (branchService.branchExists(mst.getBranchCode())) {
				result.addError(new FieldError("mst", "branchCode", "Branch Code already in use"));
			}
			if (result.hasErrors()) {
				List<ObjectError> allErrors = result.getAllErrors();
				for (ObjectError temp : allErrors) {
					System.out.println(temp);
				}
				return "branch/newbranch";
			} else {
				/* Save Branch to Database */
				branchService.save(mst);
				redirAttrs.addFlashAttribute("success", "Registration Completed Successfully");
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while branch registration successfully... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - create/update branch "));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Branch Management");
			auditRecord.setActivityCode("CREATE-UPDATE");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/activeBranchList";
	}

	/* Update of Registered Branch */
	@GetMapping(value = "/branchUpdate/{branchId}")
	public ModelAndView branchUpdate(@PathVariable(name = "branchId") long branchId, Device device) throws Exception {
		ModelAndView mav = new ModelAndView("branch/updatebranch");
		AuditRecord auditRecord = new AuditRecord();
		try {
			BranchMst branchMST = branchService.getBranchById(branchId);
			mav.addObject("branchAttribute", branchMST);
		} catch (Exception e) {
			LOGGER.error("Error occur while edit branch registartion ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed branch by id " + branchId));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Branch Management");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return mav;
	}

	/* Deactivate of Registered Branch */
	@GetMapping(value = "/deactiveBranch/{branchId}")
	public String deactiveBranch(BranchMst branchMst, @PathVariable(name = "branchId") long branchId, Device device)
			throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			branchService.deactiveBranch(branchMst);
		} catch (Exception e) {
			LOGGER.error("------Error occur while deactivate branch------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No branch deactivate......" + branchId);
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - deactivate branch by branch id - " + branchId));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Branch Management");
			auditRecord.setActivityCode("DEACTIVATE");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/activeBranchList";
	}

	/* Activate of Registered Branch */
	@GetMapping(value = "/activeBranch/{branchId}")
	public String activeBranch(BranchMst branchMst, @PathVariable(name = "branchId") long branchId, Device device)
			throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			branchService.activateBranch(branchMst);
		} catch (Exception e) {
			LOGGER.error("------Error occur while activate branch ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No branch activate......" + branchId);
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - activate branch by branch id - " + branchId));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("Branch Management");
			auditRecord.setActivityCode("ACTIVATE");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/inActiveBranchList";
	}
}