package com.ksvsofttech.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.entities.AuditRecord;

@Repository
public interface AuditRecordRepository extends JpaRepository<AuditRecord, Long> {

	@Query(value = "SELECT * FROM AuditRecord  WHERE createdDate between :from and :to and roleCode = :roleCode ORDER BY createdDate DESC", nativeQuery = true)
	public List<AuditRecord> findByfromDateBetweenorroleCode(@Param("from") String from, @Param("to") String to,
			@Param("roleCode") String roleCode);

	@Query(value = "SELECT * FROM AuditRecord  WHERE createdDate between :from and :to and loginId = :loginId ORDER BY createdDate DESC", nativeQuery = true)
	public List<AuditRecord> findByfromDateBetweenorloginId(@Param("from") String from, @Param("to") String to,
			@Param("loginId") String loginId);

	@Query(value = "SELECT * FROM AuditRecord  WHERE createdDate between :from and :to and branchCode = :branchName ORDER BY createdDate DESC", nativeQuery = true)
	public List<AuditRecord> findByfromDateBetweenorbranchName(@Param("from") String from, @Param("to") String to,
			@Param("branchName") String branchName);

	@Query(value = "SELECT * FROM AuditRecord  WHERE createdDate between :from and :to and loginId = :loginId and branchCode =:branchName ORDER BY createdDate DESC", nativeQuery = true)
	public List<AuditRecord> findByfromDateBetweenorloginIdorbranchName(@Param("from") String from,
			@Param("to") String to, @Param("loginId") String loginId, @Param("branchName") String branchName);

	@Query(value = "SELECT * FROM AuditRecord  WHERE createdDate between :from and :to ORDER BY createdDate DESC", nativeQuery = true)
	public List<AuditRecord> findAllUser(@Param("from") String from, @Param("to") String to);
}