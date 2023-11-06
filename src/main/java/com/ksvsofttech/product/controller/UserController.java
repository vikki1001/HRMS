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
import org.springframework.web.bind.annotation.PostMapping;import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ksvsofttech.product.entities.AuditRecord;
import com.ksvsofttech.product.entities.EmpBasicDetails;
import com.ksvsofttech.product.entities.UserMst;
import com.ksvsofttech.product.service.AuditRecordService;
import com.ksvsofttech.product.service.BranchService;
import com.ksvsofttech.product.service.DepartmentService;
import com.ksvsofttech.product.service.RoleService;
import com.ksvsofttech.product.service.SequenceMstService;
import com.ksvsofttech.product.service.UserService;
import com.ksvsofttech.product.validator.UserValidator;

@Controller
public class UserController {
	private static final Logger LOGGER = LogManager.getLogger(UserController.class);

	@Autowired
	private UserValidator userMstValidator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(userMstValidator);
	}

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private BranchService branchService;
	@Autowired
	private AuditRecordService auditRecordService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private SequenceMstService sequenceMstService;
		
	/* Display List of IsActive Users */
	@GetMapping(value = "/activeUserList")
	public String getAllUsers(@ModelAttribute UserMst userMst, Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			model.addAttribute("listUser", userService.getIsActiveUsers());
		} catch (Exception e) {
			LOGGER.error("Error occur while display  actice user list" + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed active user list"));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("User Management");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "user/activeuserlist";
	}

	/* Display List of InActive Users */
	@GetMapping(value = "/inActiveUserList")
	public String inactiveUser(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			model.addAttribute("listUser", userService.getInActiveUsers());
		} catch (Exception e) {
			LOGGER.error("Error occur to display inactive user list... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed inactive user list"));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("User Management");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "user/inactiveuserlist";
	}

	@GetMapping(value = "/userForm")
	public String saveUser(@ModelAttribute UserMst userMst, Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			model.addAttribute("listRole", roleService.getIsActive());
			model.addAttribute("listBranch", branchService.getIsActiveBranchs());
			model.addAttribute("departmentList", departmentService.getIsActive());
			model.addAttribute("tenantId", sequenceMstService.getTenantId());

		} catch (Exception e) {
			LOGGER.error("Error occur to display user registration page... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed user management"));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("User Management");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "user/newuser";
	}

	@PostMapping(value = "/saveUser")
	public String successUser(@Valid @ModelAttribute UserMst userMst, BindingResult result, Model model, Device device,
			RedirectAttributes redirAttrs) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			model.addAttribute("listRole", roleService.getIsActive());
			model.addAttribute("listBranch", branchService.getIsActiveBranchs());
			model.addAttribute("departmentList", departmentService.getIsActive());
			model.addAttribute("tenantId", sequenceMstService.getTenantId());

			if (userService.loginIdExists(userMst.getLoginId())) {
				result.addError(new FieldError("userMst", "loginId", "LoginId already in use"));
			} else if (userService.userExists(userMst.getPassword())) {
				result.addError(new FieldError("userMst", "password", "Password must be required"));
			} else if (result.hasErrors()) {
				List<ObjectError> allErrors = result.getAllErrors();
				for (ObjectError temp : allErrors) {
					System.out.println(temp);
				}
				return "user/newuser";
			} else {
				/* Save Users to Database */
				userService.saveOrUpdateUser(userMst);
				redirAttrs.addFlashAttribute("success", "Registration Completed Successfully");
				return "redirect:/activeUserList";
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while user registration successfully... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - create user in user management"));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("User Management");
			auditRecord.setActivityCode("CREATE");
			auditRecordService.save(auditRecord, device);
		}
		return "user/newuser";
	}

	/* Update of Registered Users */
	@GetMapping(value = "/showFormForUpdate/{loginId}")
	public String showEditProductPage(@ModelAttribute UserMst userMst, @PathVariable(name = "loginId") String loginId,
			Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			model.addAttribute("listRole", roleService.getIsActive());
			model.addAttribute("listBranch", branchService.getIsActiveBranchs());
			model.addAttribute("departmentList", departmentService.getIsActive());
			model.addAttribute("userMst", userService.getUserById(loginId));
			model.addAttribute("tenantId", sequenceMstService.getTenantId());
		} catch (Exception e) {
			LOGGER.error("Error occur while display edit registered page ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed user id - " + loginId));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("User Management");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "user/updateuser";
	}

	@PostMapping(value = "/saveUpdate")
	public String updateUser(@Valid @ModelAttribute UserMst userMst, BindingResult result, Model model, Device device,
			RedirectAttributes redirAttrs) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			model.addAttribute("listRole", roleService.getIsActive());
			model.addAttribute("listBranch", branchService.getIsActiveBranchs());
			model.addAttribute("departmentList", departmentService.getIsActive());
			model.addAttribute("tenantId", sequenceMstService.getTenantId());

			if (result.hasErrors()) {
				List<ObjectError> allErrors = result.getAllErrors();
				for (ObjectError temp : allErrors) {
					System.out.println(temp);
				}
				return "user/updateuser";
			} else {
				/* Save Users to Database */
				userService.saveOrUpdateUser(userMst);
				redirAttrs.addFlashAttribute("success", "Update Registration Successfully");
				return "redirect:/activeUserList";
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while user registration successfully... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - update user in user management"));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("User Management");
			auditRecord.setActivityCode("UPDATE");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/activeUserList";
	}

	/* Deactivate of Registered User */
	@GetMapping(value = "/deactiveUser/{loginId}")
	public String deactiveUser(UserMst userMst, @PathVariable(name = "loginId") String loginId, Device device)
			throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			userService.deactiveUser(userMst);
		} catch (Exception e) {
			LOGGER.error("------Error occur while deactivate user------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No User deactivate......" + loginId);
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - deactivate user login id - " + loginId));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("User Management");
			auditRecord.setActivityCode("DEACTIVATE");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/activeUserList";
	}

	/* Activate of Registered User */
	@GetMapping(value = "/activeUser/{loginId}")
	public String activeUser(UserMst userMst, @PathVariable(name = "loginId") String loginId, Device device)
			throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			userService.activateUser(userMst);
		} catch (Exception e) {
			LOGGER.error("------Error occur while activate user------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No User activate......" + loginId);
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - activate user login id - " + loginId));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("User Management");
			auditRecord.setActivityCode("ACTIVATE");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/inActiveUserList";
	}

	@GetMapping(value = "/escalationSearch")
	public @ResponseBody ModelAndView search(@RequestParam("value") String value, Device device)
			throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		ModelAndView mv = new ModelAndView();
		try {
			mv.setViewName("fragments/searchEscalation");
			List<EmpBasicDetails> empBasicDetails = userService.getSearchEmpByIdAndFullName(value);
			mv.addObject("empBasicDetails", empBasicDetails); 
			return mv;
		} catch (Exception e) {
			LOGGER.error("------Error occur while search employee details ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No employee found......");
		}  finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - escalation search"));
			auditRecord.setMenuCode("User Administrator");
			auditRecord.setSubMenuCode("User Management");
			auditRecord.setActivityCode("SEARCH");
			auditRecordService.save(auditRecord, device);
		}
	}
}
