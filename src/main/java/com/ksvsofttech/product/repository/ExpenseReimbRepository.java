package com.ksvsofttech.product.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.entities.ExpenseReimb;

@Repository
public interface ExpenseReimbRepository extends JpaRepository<ExpenseReimb, Long> {

	@Query(value = "SELECT * FROM ExpenseReimb WHERE empId = :empId AND isActive=1 ORDER BY date DESC", nativeQuery = true)
	public List<ExpenseReimb> activeExpenseReimb(String empId);

	@Query(value = "SELECT * FROM ExpenseReimb WHERE empId = :empId AND isActive=0 ORDER BY date DESC", nativeQuery = true)
	public List<ExpenseReimb> cancelExpenseReimb(String empId);

	@Query(value = "SELECT * FROM ExpenseReimb WHERE id = :id AND isActive=1", nativeQuery = true)
	public Optional<ExpenseReimb> getCancelById(Long id);

	@Query(value = "SELECT * FROM ExpenseReimb WHERE empId = :empId AND isActive = 1", nativeQuery = true)
	public List<ExpenseReimb> getExpenseReimbByEmpId(String empId);

	@Query(value = "SELECT * FROM ExpenseReimb WHERE isActive = 1 ORDER BY date DESC", nativeQuery = true)
	public List<ExpenseReimb> getEmpWithManger();
	
	@Query(value = "SELECT * FROM ExpenseReimb WHERE isActive = 1 AND status = 'Pending' AND managerId = :empId ORDER BY date DESC", nativeQuery = true)
	public List<ExpenseReimb> getEmpWithMangerWithPending(String empId);

	@Transactional
	@Modifying
	@Query("update ExpenseReimb u set u.status = ?1, u.flag = ?2  where u.id = ?3")
	public void acceptStatus(String status, String flag, Long id);

	@Query(value = "SELECT * FROM ExpenseReimb WHERE id = :id AND isActive = 1", nativeQuery = true)
	public List<ExpenseReimb> acceptExpenseReimbById(Long id);

	@Query(value = "SELECT * FROM ExpenseReimb WHERE id = :id", nativeQuery = true)
	public ExpenseReimb getAttachment(Long id);

	@Query(value = "SELECT * FROM ExpenseReimb WHERE empId = :empId", nativeQuery = true)
	public ExpenseReimb getCurrentUser(String empId);
	
	@Query(value = "SELECT * FROM ExpenseReimb WHERE notification = 'Unread' AND isActive = '1' AND managerId = :userId", nativeQuery = true)
	public List<ExpenseReimb> unreadNotification(String userId);

	@Query(value = "SELECT * FROM ExpenseReimb WHERE managerId = :userId AND isActive = '1' ORDER BY createdDate DESC", nativeQuery = true)
	public List<ExpenseReimb> expenseReimbNotificationBell(String userId);
}