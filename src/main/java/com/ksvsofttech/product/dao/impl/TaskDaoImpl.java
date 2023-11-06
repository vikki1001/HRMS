package com.ksvsofttech.product.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.dao.TaskDao;
import com.ksvsofttech.product.entities.Task;
import com.ksvsofttech.product.repository.TaskRepository;

@Repository
public class TaskDaoImpl implements TaskDao {
	private static final Logger LOGGER = LogManager.getLogger(TaskDaoImpl.class);

	@Autowired
	private TaskRepository taskRepository;

	/* All Emp Weekly Task Display to Task Assigner */
	@Override
	public List<Task> weeklyTask(String empId) throws Exception {
		List<Task> listTask = new ArrayList<>();
		try {
			listTask = taskRepository.weeklyTask(empId);
		} catch (Exception e) {
			LOGGER.error("------Error occur while display Weekly Data------" + ExceptionUtils.getStackTrace(e));
		}
		return listTask;
	}

	/* List Of IsActive Task */
	@Override
	public List<Task> taskAssign(String empId) throws Exception {
		List<Task> listTask = taskRepository.taskAssign(empId);
		try {
			if (!listTask.isEmpty()) {
				return listTask;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while display isactive task list------" + ExceptionUtils.getStackTrace(e));
		}
		return new ArrayList<>();
	}


	/*Complete Task*/
	@Override
	public Task completeTask(Task task) throws Exception {
		try {
			Optional<Task> task1 = taskRepository.findById(task.getId());
			if (task1.isPresent()) {
				Task newTask = task1.get();
				newTask.setStatus(task.getStatus());
				newTask.setStatus("Completed");

				taskRepository.save(newTask);
			} else {
				return task;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while Complete------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No Complete Task......");
		}
		return task;
	}
	
	/* Cancel Registered Task */
	@Override
	public Task cancelTask(Task task) throws Exception {
		try {
			Optional<Task> task1 = taskRepository.findById(task.getId());
			if (task1.isPresent()) {
				Task newTask = task1.get();
				newTask.setIsActive(task.getIsActive());
				newTask.setIsActive("0");

				taskRepository.save(newTask);
			} else {
				return task;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while deactive user------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No User deactivate......");
		}
		return task;
	}

	/* List of cancel Task */
	@Override
	public List<Task> cancelTaskList(String empId) throws Exception {
		List<Task> listTask = taskRepository.cancelTaskList(empId);
		try {
			if (!listTask.isEmpty()) {
				return listTask;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while display inactive exit list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No exit record exist ......");
		}
		return new ArrayList<>();
	}

	/* List of Save and Update Task */
	@Override
	public Task saveTask(Task task) throws Exception {
		String loginId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			loginId = authentication.getName();
			if (Objects.isNull(task.getId())) {
				task.setIsActive("1");
				task.setCreatedDate(new Date());
				task.setStatus("Pending");
				task.setManagerId(loginId);
				task.setCreatedBy(loginId);
				task.setLastModifiedDate(new Date());
				task.setLastModifiedBy(loginId);
				taskRepository.save(task);
			} else {
				Optional<Task> task1 = taskRepository.findById(task.getId());
				if (task1.isPresent()) {
					Task newTask = task1.get();
					newTask.setEmpId(task.getEmpId());
					newTask.setProjects(task.getProjects());
					newTask.setHours(task.getHours());
					newTask.setDescription(task.getDescription());
					newTask.setDate(task.getDate());
					taskRepository.save(newTask);
				} else {
					return task;
				}
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while get save and update list------" + ExceptionUtils.getStackTrace(e));
		}
		return task;
	}

	/* Get Task By Manager */
	@Override
	public Task getTaskById(Long id) throws Exception {
		Optional<Task> optional = taskRepository.findById(id);
		Task task = null;
		try {
			if (optional.isPresent()) {
				task = optional.get();
			} else {
				return task;
			}

		} catch (Exception e) {
			LOGGER.error("------Error occur while get Task by empId -----" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist ...... " + id);
		}
		return task;
	}

	/* List for Complete Task... */
	@Override
	public List<Task> completedTask(String empId) throws Exception {
		List<Task> listTask = new ArrayList<>();
		try {
			listTask = taskRepository.completedTask(empId);

		} catch (Exception e) {
			LOGGER.error(
					"------ Error occur while complete Task activity by Id -----" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist ...... ");
		}
		return listTask;
	}

	/* List for DashBoard Task.... */
	@Override
	public List<Task> dashboardTask(String empId) throws Exception {
		List<Task> listTask = new ArrayList<>();
		try {
			listTask = taskRepository.dashboardTask(empId);

		} catch (Exception e) {
			LOGGER.error(
					"------ Error occur while display dashboardTask by empId -----" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist ...... ");
		}
		return listTask;

	}
}