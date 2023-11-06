package com.ksvsofttech.product.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksvsofttech.product.dao.TaskDao;
import com.ksvsofttech.product.entities.Task;
import com.ksvsofttech.product.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
	private static final Logger LOGGER = LogManager.getLogger(TaskServiceImpl.class);

	@Autowired
	private TaskDao taskDao;

	/* All Employee Weekly Task Display to Task Assigner */
	@Override
	public List<Task> weeklyTask(String empId) throws Exception {
		List<Task> listTask = new ArrayList<>();
		try {
			listTask = taskDao.weeklyTask(empId);
		} catch (Exception e) {
			LOGGER.error("------Error occur while display Weekly Data------" + ExceptionUtils.getStackTrace(e));
		}
		return listTask;
	}

	/* List Of Active Task */
	public List<Task> taskAssign(String empId) throws Exception {
		List<Task> listTask = new ArrayList<>();
		try {
			listTask = taskDao.taskAssign(empId);
		} catch (Exception e) {
			LOGGER.error("------Error occur while display isactiveTask list------" + ExceptionUtils.getStackTrace(e));
		}
		return listTask;
	}

	
	
	/* Cancel Registered Task */
	@Override
	public Task cancelTask(Task task) throws Exception {
		try {
			return taskDao.cancelTask(task);
		} catch (Exception e) {
			LOGGER.error("------Error occur while cancel registered task------" + ExceptionUtils.getStackTrace(e));
		}
		return task;
	}

	/* List of Cancel Task .... */
	@Override
	public List<Task> cancelTaskList(String empId) throws Exception {
		List<Task> listTask = new ArrayList<>();
		try {
			listTask = taskDao.cancelTaskList(empId);
		} catch (Exception e) {
			LOGGER.error("------Error occur while display inactiveTask list------" + ExceptionUtils.getStackTrace(e));
		}

		return listTask;
	}

	/* Save Task.... */
	@Override
	public Task saveTask(Task task) throws Exception {
		try {
			this.taskDao.saveTask(task);
		} catch (Exception e) {
			LOGGER.error("------Error occur while save Task list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No task save and update ......" + task);
		}

		return task;
	}

	/* Get Task By Manager */
	@Override
	public Task getTaskById(Long id) throws Exception {
		try {
			return taskDao.getTaskById(id);

		} catch (Exception e) {
			LOGGER.error("------ Error occur while get Task activity by Id -----" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist ...... ");
		}
	}

	/* Complete Task */
	@Override
	public Task completeTask(Task task) throws Exception {
		try {
			return taskDao.completeTask(task);
		} catch (Exception e) {
			LOGGER.error("------Error occur while complete task------" + ExceptionUtils.getStackTrace(e));
		}
		return task;
	}
	
	/* List for Complete Task... */
	@Override
	public List<Task> completedTask(String empId) throws Exception {
		List<Task> listTask = new ArrayList<>();
		try {
			listTask = taskDao.completedTask(empId);

		} catch (Exception e) {
			LOGGER.error("------ Error occur while get Task activity by Id -----" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist ...... ");
		}
		return listTask;
	}

	/* List for DashBoard Task.... */
	@Override
	public List<Task> dashboardTask(String empId) throws Exception {
		List<Task> listTask = new ArrayList<>();
		try {
			listTask = taskDao.dashboardTask(empId);

		} catch (Exception e) {
			LOGGER.error(
					"------ Error occur while display dashboardTask by Id -----" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist ...... ");
		}
		return listTask;
	}

}