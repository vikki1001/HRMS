package com.ksvsofttech.product.controller;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ksvsofttech.product.entities.CheckInCheckOut;
import com.ksvsofttech.product.entities.ExitActivity;
import com.ksvsofttech.product.entities.ExpenseReimb;
import com.ksvsofttech.product.entities.LeaveMst;
import com.ksvsofttech.product.repository.CheckInCheckOutRepository;
import com.ksvsofttech.product.repository.ExitActivityRepository;
import com.ksvsofttech.product.repository.ExpenseReimbRepository;
import com.ksvsofttech.product.repository.LeaveRepository;
import com.ksvsofttech.product.service.CheckInCheckOutService;
import com.ksvsofttech.product.service.ExitActivityService;
import com.ksvsofttech.product.service.ExpenseReimbService;
import com.ksvsofttech.product.service.LeaveService;

@Controller
public class NotificationAlertController {
	private static final Logger LOG = LogManager.getLogger(NotificationAlertController.class);

	@Autowired
	CheckInCheckOutRepository checkInCheckOutRepository;
	@Autowired
	CheckInCheckOutService checkInCheckOutService;
	
	@Autowired
	ExitActivityRepository exitActivityRepository;
	@Autowired
	ExitActivityService exitActivityService;
	
	@Autowired
	ExpenseReimbRepository expenseReimbRepository;
	@Autowired
	ExpenseReimbService expenseReimbService;
	
	@Autowired
	LeaveRepository leaveRepository;
	@Autowired
	LeaveService leaveService;
	

	/* Add New Req Notification Alert */
	@GetMapping("/addNewReqNotificationAlert")
	public @ResponseBody String addNewReqNotificationAlert(@ModelAttribute CheckInCheckOut checkInCheckOut, Model model)
			throws Exception {
		String notification = "Read";
		String userId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			userId = authentication.getName();

			for (CheckInCheckOut checkInCheckOut2 : checkInCheckOutRepository.unreadNotification(userId)) {
				if ("Unread".equals(checkInCheckOut2.getNotification())) {
					notification = "Unread";
				} else {
					notification = "Read";
				}
			}
		} catch (Exception e) {
			LOG.error("Error occur while notification alert " + ExceptionUtils.getStackTrace(e));
		}
		return notification;
	}

	/* For Read Add New Req Notification Msg */
	@GetMapping(value = "/addNewReqNotification/{id}")
	public String getEmpIdAddNewReqNotification(CheckInCheckOut checkInCheckOut, @PathVariable(name = "id") Long id,
			Model model) throws Exception {
		try {
			System.out.println("Read Notification ::::::::::: ");
			checkInCheckOutService.notificationRead(checkInCheckOut);
		} catch (Exception e) {
			LOG.error("Error occur while read notification ... " + ExceptionUtils.getStackTrace(e));
		}
		return "redirect:/approvalNewReqList";
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	/* Exit Activity Notification Alert */
	@GetMapping("/exitActivityNotificationAlert")
	public @ResponseBody String exitActivityNotificationAlert(@ModelAttribute ExitActivity exitActivity, Model model)
			throws Exception {
		String notification = "Read";
		String userId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			userId = authentication.getName();

			for (ExitActivity exitActivity2 : exitActivityRepository.unreadNotification(userId)) {
				if ("Unread".equals(exitActivity2.getNotification())) {
					notification = "Unread";
				} else {
					notification = "Read";
				}
			}
		} catch (Exception e) {
			LOG.error("Error occur while notification alert " + ExceptionUtils.getStackTrace(e));
		}
		return notification;
	}

	/* For Read Exit Activity Notification Msg */
	@GetMapping(value = "/exitActivityNotification/{id}")
	public String getEmpIdexitActivityNotification(ExitActivity exitActivity, @PathVariable(name = "id") Long id,
			Model model) throws Exception {
		try {
			System.out.println("Read Notification ::::::::::: ");
			exitActivityService.notificationRead(exitActivity);
		} catch (Exception e) {
			LOG.error("Error occur while read notification ... " + ExceptionUtils.getStackTrace(e));
		}
		return "redirect:/appliedExitActivityList";
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	/* Expense Notification Alert */
	@GetMapping("/expenseNotificationAlert")
	public @ResponseBody String expenseNotificationAlert(@ModelAttribute ExpenseReimb expenseReimb, Model model)
			throws Exception {
		String notification = "Read";
		String userId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			userId = authentication.getName();

			for (ExpenseReimb expenseReimb2 : expenseReimbRepository.unreadNotification(userId)) {
				if ("Unread".equals(expenseReimb2.getNotification())) {
					notification = "Unread";
				} else {
					notification = "Read";
				}
			}
		} catch (Exception e) {
			LOG.error("Error occur while notification alert " + ExceptionUtils.getStackTrace(e));
		}
		return notification;
	}

	/* For Read Expense Notification Msg */
	@GetMapping(value = "/expenseNotification/{id}")
	public String getEmpIdexpenseNotification(ExpenseReimb expenseReimb, @PathVariable(name = "id") Long id,
			Model model) throws Exception {
		try {
			System.out.println("Read Notification ::::::::::: ");
			expenseReimbService.notificationRead(expenseReimb);
		} catch (Exception e) {
			LOG.error("Error occur while read notification ... " + ExceptionUtils.getStackTrace(e));
		}
		return "redirect:/appliedExpenseReimbList";
	}
	
/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	/* Leave Notification Alert */
	@GetMapping("/leaveNotificationAlert")
	public @ResponseBody String leaveNotificationAlert(@ModelAttribute LeaveMst leaveMst, Model model)
			throws Exception {
		String notification = "Read";
		String userId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			userId = authentication.getName();

			for (LeaveMst leaveMst2 : leaveRepository.unreadNotification(userId)) {
				if ("Unread".equals(leaveMst2.getNotification())) {
					notification = "Unread";
				} else {
					notification = "Read";
				}
			}
		} catch (Exception e) {
			LOG.error("Error occur while notification alert " + ExceptionUtils.getStackTrace(e));
		}
		return notification;
	}

	/* For Read Leave Notification Msg */
	@GetMapping(value = "/leaveNotification/{id}")
	public String getEmpIdleaveNotification(LeaveMst leaveMst, @PathVariable(name = "id") Long id,
			Model model) throws Exception {
		try {
			System.out.println("Read Notification ::::::::::: ");
			leaveService.notificationRead(leaveMst);
		} catch (Exception e) {
			LOG.error("Error occur while read notification ... " + ExceptionUtils.getStackTrace(e));
		}
		return "redirect:/appliedLeaveList";
	}
}
