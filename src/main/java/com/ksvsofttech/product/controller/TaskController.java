package com.ksvsofttech.product.controller;

import java.util.Date;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ksvsofttech.product.entities.AuditRecord;
import com.ksvsofttech.product.entities.EmpBasicDetails;
import com.ksvsofttech.product.entities.Task;
import com.ksvsofttech.product.service.AuditRecordService;
import com.ksvsofttech.product.service.EmpRegistartionService;
import com.ksvsofttech.product.service.TaskService;

@Controller
public class TaskController {
	private static final Logger LOGGER = LogManager.getLogger(TaskController.class);

	@Autowired
	private TaskService taskService;

	@Autowired
	private EmpRegistartionService empRegistartionService;

	@Autowired
	private AuditRecordService auditRecordService;

	/* All Emp Weekly Task Display to Task Assigner */
	@GetMapping(value = "/weeklyDataList")
	public String weeklyDataList(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			model.addAttribute("weeklyTaskList", taskService.weeklyTask(empId));
		} catch (Exception e) {
			LOGGER.error("Error occur while display weekly data list ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed weekly data list"));
			auditRecord.setMenuCode("Employee Management");
			auditRecord.setSubMenuCode("Task");
			auditRecord.setActivityCode("VIEW");
		}
		return "task/weeklyAssinList";
	}
	
	@GetMapping(value = "/completeTask/{id}")
	public String completeTask(Task task, @PathVariable(name = "id") Long id, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			taskService.completeTask(task);
		} catch (Exception e) {
			LOGGER.error("------Error occur while complete task------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No Completed Task......" + id);
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - complete task login id - " + id));
			auditRecord.setMenuCode("Employee Management");
			auditRecord.setSubMenuCode("Task");
			auditRecord.setActivityCode("COMPLETE");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/taskAssignList";
	}
	
	/*For Complete Task List*/
	@GetMapping(value = "/completedTaskList")
	public String completeTaskList(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			model.addAttribute("completedTask", taskService.completedTask(empId));
			return "task/completedTaskList";

		} catch (Exception e) {
			LOGGER.error("Error occur to display task list... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed complete task list"));
			auditRecord.setMenuCode("Employe Management");
			auditRecord.setSubMenuCode("Task");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "task/completedTaskList";
	}

	/* Cancel Registered Task */
	@GetMapping(value = "/cancelTask/{id}")
	public String cancelTask(Task task, @PathVariable(name = "id") Long id, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			taskService.cancelTask(task);
		} catch (Exception e) {
			LOGGER.error("------Error occur while cancel registered task------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No User deactivate......" + id);
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - cancel task login id - " + id));
			auditRecord.setMenuCode("Employee Management");
			auditRecord.setSubMenuCode("Task");
			auditRecord.setActivityCode("CANCEL");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/cancelTaskList";
	}

	/* List of Cancel Task */
	@GetMapping(value = "/cancelTaskList")
	public String cancelTaskList(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empId = null;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			List<Task> userDetails = null;
			if (empId != null) {
				userDetails = taskService.cancelTaskList(empId);
				if (userDetails != null) {
					model.addAttribute("cancelTask", userDetails);
					return "task/cancelTaskList";
				} else {
					LOGGER.info("Nothing Happen / Error  ::::::");
					return "task/cancelTaskList";
				}
			} else {
				LOGGER.info("Nothing Happen / Error  ::::::");
				return "task/cancelTaskList";
			}
		} catch (Exception e) {
			LOGGER.error("Error occur to display cancel task list... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed cancel task list"));
			auditRecord.setMenuCode("Employee Management");
			auditRecord.setSubMenuCode("Task");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "task/cancelTaskList";

	}
	/* Task Home Page */
	@GetMapping(value = "/task")
	public String task(@ModelAttribute Task task, Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empId;
		try {
			model.addAttribute("empIdList", empRegistartionService.getIsActiveEmpRegList());
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			EmpBasicDetails userDetails = null;
			if (empId != null) {
				userDetails = empRegistartionService.getCurrentUser(empId);
				if (userDetails != null) {
					model.addAttribute("taskList", userDetails);
				} else {
					LOGGER.info("Nothing Happen ::::::");
					return "task/activeTaskList";
				}
			} else {
				LOGGER.info("Nothing Happen ::::::");
				return "task/activeTaskList";
			}
		} catch (Exception e) {
			LOGGER.error("Error occur to get task page... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed task form"));
			auditRecord.setMenuCode("Employee Management");
			auditRecord.setSubMenuCode("Task");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "task/task";
	}

	/* save task data */
	@PostMapping(value = "saveTask")
	public String successTask(@Validated @ModelAttribute Task task, BindingResult result, Model model, Device device,
			RedirectAttributes redirAttrs) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empId;
		try {
			model.addAttribute("empIdList", empRegistartionService.getIsActiveEmpRegList());
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			EmpBasicDetails userDetails = null;
			if (empId != null) {
				userDetails = empRegistartionService.getCurrentUser(empId);
				if (userDetails != null) {
					model.addAttribute("taskList", userDetails);
					model.addAttribute("currentDate", new Date());
				} else {
					LOGGER.info("Nothing Happen ::::::");
					return "redirect:/taskAssignList";
				}
			} else {
				LOGGER.info("Nothing Happen ::::::");
				return "redirect:/taskAssignList";
			}
			
				/* Save tasks to Database */
				taskService.saveTask(task);
				redirAttrs.addFlashAttribute("success", "Add New Task Successfully");
				return "redirect:/taskAssignList";
			
		} catch (Exception e) {
			LOGGER.error("Error occur while save task... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - create task in employee management"));
			auditRecord.setMenuCode("Employee Management");
			auditRecord.setSubMenuCode("Task");
			auditRecord.setActivityCode("CREATE");
		}
		return "redirect:/taskAssignList";

	}
	 /*Update Task Data*/
	@GetMapping(value = "/taskUpdate/{id}")
	public ModelAndView taskUpdate(@PathVariable(name = "id") Long id ,Device device) throws Exception {
		ModelAndView mav = new ModelAndView("task/updateTask");
		AuditRecord auditRecord = new AuditRecord();
		try {
			mav.addObject("empIdList", empRegistartionService.getIsActiveEmpRegList());
			Task task = taskService.getTaskById(id);
			mav.addObject("task", task);
		} catch (Exception e) {
			LOGGER.error("Error occur while display edit task  ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed update task - " + id ));
			auditRecord.setMenuCode("Employee management");
			auditRecord.setSubMenuCode("Task");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return mav;
	}	
	
	/* Update Task */
	@PostMapping(value = "/updateTask")
	public String updateTask(@Valid @ModelAttribute Task task, BindingResult result,
			Model model, Device device, RedirectAttributes redirAttrs) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empId;
		try {
			model.addAttribute("empIdList", empRegistartionService.getIsActiveEmpRegList());
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			EmpBasicDetails userDetails = null;
			if (empId != null) {
				userDetails = empRegistartionService.getCurrentUser(empId);
				if (userDetails != null) {
					model.addAttribute("taskList", userDetails);
					model.addAttribute("currentDate", new Date());
				} else {
					LOGGER.info("Nothing Happen ::::::");
					return "redirect:/taskAssignList";
				}
			} else {
				LOGGER.info("Nothing Happen ::::::");
				return "redirect:/taskAssignList";
			}
			
				/* Save tasks to Database */
				taskService.saveTask(task);
				redirAttrs.addFlashAttribute("success", "Update Task Successfully");
				return "redirect:/taskAssignList";
			
		} catch (Exception e) {
			LOGGER.error("Error occur while save task... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - update task in employee management"));
			auditRecord.setMenuCode("Employee Management");
			auditRecord.setSubMenuCode("Task");
			auditRecord.setActivityCode("UPDATE");
		}
		return "redirect:/taskAssignList";

	}

	
	/* Employee Task List Assign by manager */
	@GetMapping(value = "/taskAssignList")
	public String activeTaskList(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empId = null;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			List<Task> userDetails = null;
			if (empId != null) {
				userDetails = taskService.taskAssign(empId);
				if (userDetails != null) {
					model.addAttribute("taskList", userDetails);
					return "task/taskAssignList";
				} else {
					LOGGER.info("Nothing Happen / Error  ::::::");
					return "task/taskAssignList";
				}
			} else {
				LOGGER.info("Nothing Happen / Error  ::::::");
				return "task/taskAssignList";
			}
		} catch (Exception e) {
			LOGGER.error("Error occur to display task list... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed active task list"));
			auditRecord.setMenuCode("Employee Management");
			auditRecord.setSubMenuCode("Task");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "task/taskAssignList";
	}


	/*for dashBoard task...*/
	@GetMapping(value = "/dashboardTask")
	public String dashboardTask(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			List<Task> userDetails = null;
			if (empId != null) {
				userDetails = taskService.dashboardTask(empId);
				if (userDetails != null) {
					model.addAttribute("dashboardTaskList", userDetails);
				}
				 else {
						LOGGER.info("Nothing Happen ::::::");
						return "task/dashboardTask";
					}
				} else {
					LOGGER.info("Nothing Happen ::::::");
					return "task/dashboardTask";
				}
			System.out.println("dashboard data display::::::: " +taskService.dashboardTask(empId).toString());
			return "task/dashboardTask";
			
		} catch (Exception e) {
			LOGGER.error("Error occur to display dashboard task list... " + ExceptionUtils.getStackTrace(e));
		}finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed dashboard task"));
			auditRecord.setMenuCode("Employe Management");
			auditRecord.setSubMenuCode("Task");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "task/dashboardTask";
	
		}
}