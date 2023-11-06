package com.ksvsofttech.product.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.ksvsofttech.product.constant.ProdConstant;
import com.ksvsofttech.product.dto.WebSessionObject;
import com.ksvsofttech.product.entities.AuditRecord;
import com.ksvsofttech.product.entities.CheckInCheckOut;
import com.ksvsofttech.product.entities.EmpBasicDetails;
import com.ksvsofttech.product.entities.ExitActivity;
import com.ksvsofttech.product.entities.ExpenseReimb;
import com.ksvsofttech.product.entities.LeaveMst;
import com.ksvsofttech.product.entities.MenuMst;
import com.ksvsofttech.product.entities.RoleMenuActionAccess;
import com.ksvsofttech.product.entities.TenantMst;
import com.ksvsofttech.product.entities.UserMst;
import com.ksvsofttech.product.repository.CheckInCheckOutRepository;
import com.ksvsofttech.product.repository.ExitActivityRepository;
import com.ksvsofttech.product.repository.ExpenseReimbRepository;
import com.ksvsofttech.product.repository.LeaveRepository;
import com.ksvsofttech.product.repository.TenantMstRepository;
import com.ksvsofttech.product.repository.UserRepository;
import com.ksvsofttech.product.service.AuditRecordService;
import com.ksvsofttech.product.service.EmpRegistartionService;
import com.ksvsofttech.product.service.MenuService;
import com.ksvsofttech.product.service.RoleMenuActionAccessService;
import com.ksvsofttech.product.service.UserService;

@Controller
public class LoginController {
	private static final Logger LOGGER = LogManager.getLogger(LoginController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private RoleMenuActionAccessService roleMenuActionAccessService;
	@Autowired
	private MenuService menuMstService;
	@Autowired
	private AuditRecordService auditRecordService;
	@Autowired
	private EmpRegistartionService empRegistartionService;
	@Autowired
	private TenantMstRepository tenantMstRepository;
	
	@Autowired
	CheckInCheckOutRepository checkInCheckOutRepository;
	@Autowired
	ExitActivityRepository exitActivityRepository;
	@Autowired
	ExpenseReimbRepository expenseReimbRepository;
	@Autowired
	LeaveRepository leaveRepository;
	@Autowired
	UserRepository userRepository;


	@GetMapping(value = "/success")
	private String success() {
		return "success";
	}

	@GetMapping(value = "/error")
	private String errorPage() throws Exception {
		try {

		} catch (Exception e) {
			LOGGER.error("Error while open error page... " + ExceptionUtils.getStackTrace(e));
		}
		return "error";
	}

	@GetMapping("/login")
	private String viewLogin(@ModelAttribute UserMst userMst, Model model, Device device) throws Exception {
		try {
			model.addAttribute("userMst", userMst);
		} catch (Exception e) {
			LOGGER.error("Error while open login page... " + ExceptionUtils.getStackTrace(e));
		}
		return "login/login";
	}

	@GetMapping("/loginSuccess")
	private String loginSuccess(@ModelAttribute UserMst userMst, Authentication auth, HttpServletRequest request, HttpSession session,
			BindingResult result, Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		WebSessionObject wso = new WebSessionObject();
		String userId = null;
		try {
			// For Authentication of the user details created by Owner Date 01-11-2021:23:52
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			userId = authentication.getName();
			UserMst userDetail = null;
			EmpBasicDetails empBasicDetails = null;
			TenantMst tenantMst = null;
			if (userId != null) {
				
				// Get Active user details created by Owner Date 01-11-2021:23:52
				userDetail = userService.getUserDetails(userId);
				empBasicDetails = empRegistartionService.getCurrentUser(userId);
				tenantMst = tenantMstRepository.getTenantDetails(userDetail.getTenantId());
				
				LOGGER.info("TENANT ID :::::::::: " + userMst.getTenantId());
				
				List<CheckInCheckOut> addNewReqNotificationBell = checkInCheckOutRepository.addNewReqNotificationBell(userId);
				List<ExitActivity> exitActivityNotificationBell =  exitActivityRepository.exitActivityNotificationBell(userId);
				List<ExpenseReimb> expenseReimbNotificationBell =  expenseReimbRepository.expenseReimbNotificationBell(userId);
				List<LeaveMst> leaveMstsNotificationBell = leaveRepository.leaveMstsNotificationBell(userId);
				
				
				if (userDetail != null && empBasicDetails != null && tenantMst != null) {
					wso.setLoginId(userDetail.getLoginId());
					wso.setFirstName(userDetail.getFirstName());
					wso.setTenantId(userDetail.getTenantId());
					wso.setBranch(userDetail.getBranch());
					wso.setMainRole(userDetail.getMainRole());
					wso.setLastSuccessfullLoginDateTime(userDetail.getLastSuccessfullLoginDateTime());
					wso.setLastName(userDetail.getLastName());

					/* Display Current Employee Image in Header */
					wso.setEmpId(empBasicDetails.getEmpId());
					wso.setFile(empBasicDetails.getFile());

					/* Get Logo From DataBase */
					wso.setTenantLogoPath(tenantMst.getTenantLogoPath());
					wso.setTenantFaviconPath(tenantMst.getTenantFaviconPath());
					
					/* Notification Alert */
					wso.setAddNewReqNotification(addNewReqNotificationBell);
					wso.setExitActivityNotification(exitActivityNotificationBell);
					wso.setExpenseReimbNotification(expenseReimbNotificationBell);
					wso.setLeaveMstNotification(leaveMstsNotificationBell);
					
					// Check user Bad Login Count created By Owner Date 02-11-2021:00:14
					if (userDetail.getConsequetiveBadLoginCount() > 0) {

					} else if (userDetail.getMainRole() != null) {

						Set<String> userMenuCodeSet = new HashSet<>();
						List<RoleMenuActionAccess> rmaaList = roleMenuActionAccessService.getMenuListByModuleRoleMap(
								userDetail.getTenantId().trim(), userDetail.getMainRole().trim(), ProdConstant.TRUE);
						for (RoleMenuActionAccess rm : rmaaList) {
							LOGGER.info("Value is empty from rm:::::" + rm.getMenuCode());
						}

						if (null != rmaaList && !rmaaList.isEmpty()) {
							Set<String> menuCodeSet = rmaaList.stream().map(x -> {
								return x.getMenuCode().trim();
							}).collect(Collectors.toSet());

							for (String str : menuCodeSet) {
								LOGGER.info("STRING::::" + str);
							}

							List<MenuMst> menuCodeList = menuMstService.getMenuMstIn(userDetail.getTenantId().trim(),
									menuCodeSet);
							for (MenuMst menu : menuCodeList) {
								LOGGER.info("STRING::::" + menu.getMenuName());
							}
							if (menuCodeList != null && !menuCodeList.isEmpty()) {
								// this is where user accessible menus are decided
								for (RoleMenuActionAccess rmaa : rmaaList) {
									userMenuCodeSet.addAll(menuCodeList.stream().filter(
											x -> x.getMenuCode().trim().equalsIgnoreCase(rmaa.getMenuCode().trim()))
											.map(x -> {
												return x.getMenuCode().trim();
											}).collect(Collectors.toSet()));
									// wso.setMenuMst(menuCodeList.get(menuCodeList.size() - 1));
								}
							}
						} else {
							System.out.println(" Nothing Happened..... ");
						}

						Map<String, List<MenuMst>> accessibleMenuMap1 = new HashMap<String, List<MenuMst>>();
						accessibleMenuMap1 = menuMstService.getAccessibleMenu(userDetail.getTenantId().trim(),
								wso.getLanguage(), userMenuCodeSet);
						Map<String, List<MenuMst>> menuLHMap1 = new LinkedHashMap<String, List<MenuMst>>();
						// Sorting the accessibleMenuMap by key
						/************************************************************************************************************/
						Set<Map.Entry<String, List<MenuMst>>> menuSet1 = accessibleMenuMap1.entrySet();
						List<Map.Entry<String, List<MenuMst>>> menuList1 = new ArrayList<Map.Entry<String, List<MenuMst>>>(
								menuSet1);
						Collections.sort(menuList1, new Comparator<Map.Entry<String, List<MenuMst>>>() {
							public int compare(Entry<String, List<MenuMst>> arg0, Entry<String, List<MenuMst>> arg1) {
								return arg0.getKey().compareTo(arg1.getKey());
							}
						});

						for (Map.Entry<String, List<MenuMst>> localMap : menuList1) {
							menuLHMap1.put(localMap.getKey(), localMap.getValue());
						}
						wso.setMenuLHMap(menuLHMap1);
					}
				} else {
					System.out.println("Else 1 ::::::::::::::::::::::::::::::::");
					return "login/login";
				}
			} else {
				System.out.println("Else 2 ::::::::::::::::::::::::::::::::");
				return "login/login";
			}
			session.setAttribute("webSession", wso);
			
			/* To set login date time */
			Optional<UserMst> optional = userRepository.findLoginByLoginId(userId);
			if (optional.isPresent()) {
				UserMst newUserMst = optional.get();
				
				newUserMst.setLastSuccessfullLoginDateTime(new Date());
				userRepository.save(newUserMst);
			}
		} catch (Exception e) {
			LOGGER.error("Error occur in login success... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - login employee"));
			auditRecord.setMenuCode("Login");
			auditRecord.setSubMenuCode("Login");
			auditRecord.setActivityCode("LOGIN");
			auditRecordService.save(auditRecord, device);
		}
		 //return "dashboard/userDashboard";
		return "redirect:/empDashboard";
	}
}
