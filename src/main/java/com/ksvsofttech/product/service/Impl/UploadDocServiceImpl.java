package com.ksvsofttech.product.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksvsofttech.product.dao.UploadDocDao;
import com.ksvsofttech.product.entities.UploadDocument;
import com.ksvsofttech.product.service.UploadDocService;

@Service
public class UploadDocServiceImpl implements UploadDocService {
	private static final Logger LOGGER = LogManager.getLogger(UploadDocServiceImpl.class);

	@Autowired
	private UploadDocDao uploadDocDao;

	/* Save & Update Upload Document */
	@Override
	public UploadDocument saveUploadDoc(UploadDocument uploadDoc) throws Exception {
		try {
			uploadDocDao.saveUploadDoc(uploadDoc);
		} catch (Exception e) {
			LOGGER.error("------Error occur while save & update upload doc------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No document save & update......" + uploadDoc.getId());
		}
		return uploadDoc;

	}

	/* Display Upload Document List */
	@Override
	public List<UploadDocument> findAllDoc() throws Exception {
		List<UploadDocument> uploadDocList = uploadDocDao.findAllDoc();
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
			return uploadDocDao.findDocById(id);
		} catch (Exception e) {
			LOGGER.error("------Error occur while find document by id------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist......");
		}
	}

	/* For deactivate document */
	@Override
	public UploadDocument deactiveDoc(UploadDocument uploadDocument) throws Exception {
		try {
			return uploadDocDao.deactiveDoc(uploadDocument);
		} catch (Exception e) {
			LOGGER.error("------Error occur while deactive upload documnet-----" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("No Document deactivate" + uploadDocument.getId());
		}
	}

	/* List Of IsActive Document */
	@Override
	public List<UploadDocument> getIsActive() throws Exception {
		List<UploadDocument> uploadDocument = new ArrayList<>();
		try {
			uploadDocument = uploadDocDao.getIsActive();
		} catch (Exception e) {
			LOGGER.error("------Error occur while display isactive upload documnet list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No active upload documnet record exist......");
		}
		return uploadDocument;
	}

	/* List Of Deactivate Document */
	@Override
	public List<UploadDocument> getDeactive() throws Exception {
		List<UploadDocument> uploadDocument = new ArrayList<>();
		try {
			uploadDocument = uploadDocDao.getDeactive();
		} catch (Exception e) {
			LOGGER.error("------Error occur while display deactivate upload documnet list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No inactive upload documnet record exist......");
		}
		return uploadDocument;
	}

	/* List Of Activate Document */
	@Override
	public UploadDocument activeDoc(UploadDocument uploadDocument) throws Exception {
		try {
			return uploadDocDao.activeDoc(uploadDocument);
		} catch (Exception e) {
			LOGGER.error("------Error occur while active upload documnet-----" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("No Document activate" + uploadDocument.getId());
		}
	}

	/* List Of Employee Department Document */
	@Override
	public List<UploadDocument> getEmpDepartment() throws Exception {
		List<UploadDocument> uploadDocList = uploadDocDao.getEmpDepartment();
		try {
			if (!uploadDocList.isEmpty()) {
				return uploadDocList;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while display upload employee department documnet list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist......");
		}
		return new ArrayList<>();
	}

	/* List Of Organization Department Document */
	@Override
	public List<UploadDocument> getOrganizationDepart() throws Exception {
		List<UploadDocument> uploadDocList = uploadDocDao.getOrganizationDepart();
		try {
			if (!uploadDocList.isEmpty()) {
				return uploadDocList;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while display upload organization department documnet list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist......");
		}
		return new ArrayList<>();
	}
	
	
	
	
}
