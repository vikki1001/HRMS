package com.ksvsofttech.product.service;

import java.util.List;

import com.ksvsofttech.product.entities.UploadDocument;

public interface UploadDocService {

	public UploadDocument saveUploadDoc(UploadDocument uploadDoc) throws Exception;

	public List<UploadDocument> findAllDoc() throws Exception;
	
	public UploadDocument findDocById(Long id) throws Exception;

	public UploadDocument deactiveDoc(UploadDocument uploadDocument) throws Exception;

	public List<UploadDocument> getIsActive() throws Exception;

	public List<UploadDocument> getDeactive() throws Exception;

	public UploadDocument activeDoc(UploadDocument uploadDocument) throws Exception;

	public List<UploadDocument> getEmpDepartment() throws Exception;
	
	public List<UploadDocument> getOrganizationDepart() throws Exception;
}
