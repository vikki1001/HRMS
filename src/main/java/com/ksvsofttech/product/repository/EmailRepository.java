package com.ksvsofttech.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.entities.EmailTemplate;

@Repository
public interface EmailRepository extends JpaRepository<EmailTemplate, Long> {
	
	@Query("SELECT e FROM EmailTemplate e WHERE e.id = :id")
	public Optional<EmailTemplate> findByid(Long id);

	@Query("SELECT u FROM EmailTemplate u WHERE u.isActive=1 ORDER BY createdDate DESC")
	public List<EmailTemplate> getIsActive();

	@Query("SELECT u FROM EmailTemplate u WHERE u.isActive=0")
	public List<EmailTemplate> getInActive();
	 
	@Query(value = "SELECT * FROM EmailTemplate  WHERE isActive = 1 AND templateName = 'Leave Request'" , nativeQuery = true)
	public EmailTemplate getAppliedLeave();
	
	@Query(value = "SELECT * FROM EmailTemplate  WHERE isActive = 1 AND templateName = 'Leave Approved'" , nativeQuery = true)
	public EmailTemplate getApprovedLeave();
	
	@Query(value = "SELECT * FROM EmailTemplate  WHERE isActive = 1 AND templateName = 'Leave Reject'" , nativeQuery = true)
	public EmailTemplate getRejectLeave();
	
	@Query(value = "SELECT * FROM EmailTemplate  WHERE isActive = 1 AND templateName = 'ExitActivity Request'" , nativeQuery = true)
	public EmailTemplate getAppliedExitActivity();

	@Query(value = "SELECT * FROM EmailTemplate  WHERE isActive = 1 AND templateName = 'ExitActivity Approved'" , nativeQuery = true)
	public EmailTemplate getApprovedExitActivity();

	@Query(value = "SELECT * FROM EmailTemplate  WHERE isActive = 1 AND templateName = 'ExitActivity Reject'" , nativeQuery = true)
	public EmailTemplate getRejectExitActivity();
	
	@Query(value = "SELECT * FROM EmailTemplate  WHERE isActive = 1 AND templateName = 'ExpenseReimb Request'" , nativeQuery = true)
	public EmailTemplate getAppliedExpenseReimb();
	
	@Query(value = "SELECT * FROM EmailTemplate  WHERE isActive = 1 AND templateName = 'ExpenseReimb Approved'" , nativeQuery = true)
	public EmailTemplate getApprovedExpenseReimb();
	
	@Query(value = "SELECT * FROM EmailTemplate  WHERE isActive = 1 AND templateName = 'ExpenseReimb Reject'" , nativeQuery = true)
	public EmailTemplate getRejectExpenseReimb();
	
	@Query(value = "SELECT * FROM EmailTemplate  WHERE isActive = 1 AND templateName = 'AddNewReq Request'" , nativeQuery = true)
	public EmailTemplate getAppliedAddNewReq();
	
	@Query(value = "SELECT * FROM EmailTemplate  WHERE isActive = 1 AND templateName = 'AddNewReq Approved'" , nativeQuery = true)
	public EmailTemplate getApproveddAddNewReq();

	@Query(value = "SELECT * FROM EmailTemplate  WHERE isActive = 1 AND templateName = 'AddNewReq Reject'" , nativeQuery = true)
	public EmailTemplate getRejectAddNewReq();
	
	@Query(value = "SELECT * FROM EmailTemplate  WHERE isActive = 1 AND templateName = 'Training Form Req'" , nativeQuery = true)
	public EmailTemplate getTainingRequest();

	@Query(value = "SELECT * FROM EmailTemplate  WHERE isActive = 1 AND templateName = 'Self Appraisal'" , nativeQuery = true)
	public EmailTemplate getSelfAppraisal();
	
	@Query(value = "SELECT * FROM EmailTemplate  WHERE isActive = 1 AND templateName = 'Rating by Reporting Manager'" , nativeQuery = true)
	public EmailTemplate getRatingByRM();
	
	@Query(value = "SELECT * FROM EmailTemplate  WHERE isActive = 1 AND templateName = 'Rating by Manager'" , nativeQuery = true)
	public EmailTemplate getRatingByManager();
	
}