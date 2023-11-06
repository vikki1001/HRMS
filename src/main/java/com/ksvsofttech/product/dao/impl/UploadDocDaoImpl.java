package com.ksvsofttech.product.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.dao.UploadDocDao;
import com.ksvsofttech.product.entities.UploadDocument;
import com.ksvsofttech.product.repository.UploadDocRepository;

@Repository
public class UploadDocDaoImpl implements UploadDocDao {
	private static final Logger LOGGER = LogManager.getLogger(UploadDocDaoImpl.class);

	@Autowired
	private UploadDocRepository uploadDocRepository;

	/* Save & Update Upload Document */
	@Override
	public UploadDocument saveUploadDoc(UploadDocument uploadDoc) throws Exception {
		String loginId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			loginId = authentication.getName();
			if (uploadDoc.getId() == null) {
				uploadDoc.setIsActive("1");
				uploadDoc.setCreatedDate(new Date());
				uploadDoc.setCreatedBy(loginId);
				uploadDocRepository.save(uploadDoc);
			} else {
				Optional<UploadDocument> uploadDoc2 = uploadDocRepository.findById(uploadDoc.getId());
				if (uploadDoc2.isPresent()) {
					UploadDocument newUploadDoc = uploadDoc2.get();
					newUploadDoc.setTemplateName(uploadDoc.getTemplateName());
					newUploadDoc.setOriginalName(uploadDoc.getOriginalName());
					newUploadDoc.setFile(uploadDoc.getFile());
					newUploadDoc.setFileType(uploadDoc.getFileType());
					newUploadDoc.setSize(uploadDoc.getSize());
					newUploadDoc.setExtension(uploadDoc.getExtension());
					newUploadDoc.setFileDepartment(uploadDoc.getFileDepartment());
					newUploadDoc.setLastModifiedDate(new Date());
					newUploadDoc.setLastModifiedBy(loginId);
					uploadDocRepository.save(newUploadDoc);
				} else {
					return uploadDoc;
				}
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while save & update upload doc------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No document save & update......" + uploadDoc.getId());
		}
		return uploadDoc;
	}

	/* Display Upload Document List */
	@Override
	public List<UploadDocument> findAllDoc() throws Exception {
		List<UploadDocument> uploadDocList = uploadDocRepository.findAll();
		try {
			if (!uploadDocList.isEmpty()) {
				return uploadDocList;
			}
		} catch (Exception e) {
			LOGGER.error(
					"------Error occur while display upload documnet list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist......");
		}
		return new ArrayList<>();
	}

	/* Find Doc By Id */
	@Override
	public UploadDocument findDocById(Long id) throws Exception {
		try {
			return uploadDocRepository.getById(id);
		} catch (Exception e) {
			LOGGER.error("------Error occur while find document by id------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist......");
		}
	}

	/* For Activate Document */
	@Override
	public UploadDocument deactiveDoc(UploadDocument uploadDocument) throws Exception {
		try {
			Optional<UploadDocument> uploadDocument2 = uploadDocRepository.findById(uploadDocument.getId());
			if (uploadDocument2.isPresent()) {
				UploadDocument newuploadDocument = uploadDocument2.get();
				newuploadDocument.setIsActive(uploadDocument.getIsActive());
				newuploadDocument.setIsActive("0");

				uploadDocRepository.save(newuploadDocument);

			} else {
				uploadDocRepository.save(uploadDocument);
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while deactive document------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No document deactivate......");
		}
		return uploadDocument;
	}

	/* List Of Active Document */
	@Override
	public List<UploadDocument> getIsActive() throws Exception {
		List<UploadDocument> uploadDocList = uploadDocRepository.getIsActive();
		try {
			if (!uploadDocList.isEmpty()) {
				return uploadDocList;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while display isactive upload document list------"
					+ ExceptionUtils.getStackTrace(e));
			throw new Exception("No document record exist ......");
		}
		return new ArrayList<>();
	}

	/* List Of Deactive Document */
	@Override
	public List<UploadDocument> getDeactive() throws Exception {
		List<UploadDocument> uploadDocList = uploadDocRepository.getDeactive();
		try {
			if (!uploadDocList.isEmpty()) {
				return uploadDocList;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while display deactive upload document list------"
					+ ExceptionUtils.getStackTrace(e));
			throw new Exception("No document record exist ......");
		}
		return new ArrayList<>();
	}

	/* For Activate Document */
	@Override
	public UploadDocument activeDoc(UploadDocument uploadDocument) throws Exception {
		try {
			Optional<UploadDocument> uploadDocument2 = uploadDocRepository.findById(uploadDocument.getId());
			if (uploadDocument2.isPresent()) {
				UploadDocument newuploadDocument = uploadDocument2.get();
				newuploadDocument.setIsActive(uploadDocument.getIsActive());
				newuploadDocument.setIsActive("1");

				uploadDocRepository.save(newuploadDocument);

			} else {
				uploadDocRepository.save(uploadDocument);
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while active document------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No document activate......");
		}
		return uploadDocument;
	}

	/* List Of Employee Department Document */
	@Override
	public List<UploadDocument> getEmpDepartment() throws Exception {
		List<UploadDocument> uploadDocList = uploadDocRepository.getEmpDepartment();
		try {
			if (!uploadDocList.isEmpty()) {
				return uploadDocList;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while display employee department upload document list------"
					+ ExceptionUtils.getStackTrace(e));
			throw new Exception("No document record exist ......");
		}
		return new ArrayList<>();
	}

	/* List Of Organization Department Document */
	@Override
	public List<UploadDocument> getOrganizationDepart() throws Exception {
		List<UploadDocument> uploadDocList = uploadDocRepository.getOrganizationDepart();
		try {
			if (!uploadDocList.isEmpty()) {
				return uploadDocList;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while display organization department upload document list------"
					+ ExceptionUtils.getStackTrace(e));
			throw new Exception("No document record exist ......");
		}
		return new ArrayList<>();
	}
}
