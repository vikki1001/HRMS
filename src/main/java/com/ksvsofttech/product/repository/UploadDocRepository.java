package com.ksvsofttech.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.entities.UploadDocument;

@Repository
public interface UploadDocRepository extends JpaRepository<UploadDocument, Long> {

	@Query("SELECT u FROM UploadDocument u WHERE u.id = :id")
	public UploadDocument getId(String id);

	@Query("SELECT u FROM UploadDocument u WHERE u.isActive = 1")
	public List<UploadDocument> getIsActive();

	@Query("SELECT u FROM UploadDocument u WHERE u.isActive = 0")
	public List<UploadDocument> getDeactive();

	@Query("SELECT u FROM UploadDocument u WHERE u.fileDepartment = 'Employee' AND u.isActive = 1 ORDER BY createdDate DESC")
	public List<UploadDocument> getEmpDepartment();

	@Query("SELECT u FROM UploadDocument u WHERE u.fileDepartment = 'Organization' AND u.isActive = 1 ORDER BY createdDate DESC")
	public List<UploadDocument> getOrganizationDepart();

}
