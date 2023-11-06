package com.ksvsofttech.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
	
	@Query(value = "SELECT * FROM Task WHERE WEEKOFYEAR(date) = WEEKOFYEAR(CURRENT_DATE()) AND empId = :empId  AND status = 'Pending' AND isActive ='1'  ORDER BY date ASC", nativeQuery = true)
	public List<Task> weeklyTask(String empId);

	@Query(value = "SELECT * FROM Task WHERE WEEKOFYEAR(date) = WEEKOFYEAR(CURRENT_DATE()) AND managerId = :empId AND isActive ='1' ORDER BY date ASC", nativeQuery = true)
	public List<Task> taskAssign(String empId);

	@Query(value = "SELECT * FROM Task WHERE  WEEKOFYEAR(date) = WEEKOFYEAR(CURRENT_DATE()) AND managerId = :empId AND isActive = '0'", nativeQuery = true)
	public List<Task> cancelTaskList(String empId);
	
	@Query(value = "SELECT * FROM Task WHERE  WEEKOFYEAR(date) = WEEKOFYEAR(CURRENT_DATE()) AND empId = :empId AND isActive = '1' AND status = 'Completed' ", nativeQuery = true)
	public List<Task> completedTask(String empId);
	
	@Query(value = "SELECT * FROM TASK WHERE isActive = '1' AND WEEKOFYEAR(date) = WEEKOFYEAR(CURRENT_DATE()) AND empId = :empId ORDER BY date ASC", nativeQuery = true)
	public List<Task> dashboardTask(String empId);
	
}
