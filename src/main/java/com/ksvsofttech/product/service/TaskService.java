package com.ksvsofttech.product.service;

import java.util.List;

import com.ksvsofttech.product.entities.Task;

public interface TaskService {
	
	public List<Task> weeklyTask(String empId) throws Exception;

	public List<Task> taskAssign(String empId) throws Exception;

	public Task cancelTask(Task task) throws Exception;

	public List<Task> cancelTaskList(String empId) throws Exception;

	public Task saveTask(Task task) throws Exception;

	public Task getTaskById(Long id) throws Exception;

	public List<Task> completedTask(String empId) throws Exception;

	public List<Task> dashboardTask(String empId) throws Exception;

	public Task completeTask(Task task) throws Exception;

}
