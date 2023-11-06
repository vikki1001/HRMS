package com.ksvsofttech.product.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksvsofttech.product.dao.TrainingFormDao;
import com.ksvsofttech.product.entities.TrainingForm;
import com.ksvsofttech.product.service.TrainingFormService;

@Service
public class TrainingFormServiceImpl implements TrainingFormService {
	private static final Logger LOGGER = LogManager.getLogger(TrainingFormServiceImpl.class);

	@Autowired
	private TrainingFormDao trainingFormDao;

	@Override
	public List<TrainingForm> getActiveList(String userId) throws Exception {
		List<TrainingForm> trainingForm = trainingFormDao.getActiveList(userId);
		try {
			if (Objects.nonNull(trainingForm)) {
				return trainingForm;
			} else {
				throw new Exception("Error occur while display Active list");
			}
		} catch (Exception e) {
			LOGGER.error("Error Occuring While Display Active List :::::: " + ExceptionUtils.getStackTrace(e));
		}
		return new ArrayList<>();
	}

	@Override
	public List<TrainingForm> getInActiveList(String userId) throws Exception {
		List<TrainingForm> trainingForm = trainingFormDao.getInActiveList(userId);
		try {
			if (Objects.nonNull(trainingForm)) {
				return trainingForm;
			} else {
				throw new Exception("Error occur while display isactive list");
			}
		} catch (Exception e) {
			LOGGER.error("Error Occuring While Display Isactive List :::::: " + ExceptionUtils.getStackTrace(e));
		}
		return new ArrayList<>();
	}

	@Override
	public TrainingForm saveUpdate(TrainingForm trainingForm) throws Exception {
		try {
			if (Objects.nonNull(trainingForm)) {
				trainingFormDao.saveUpdate(trainingForm);
			} else {
				throw new Exception("Error occur while save training form data ");
			}

		} catch (Exception e) {
			LOGGER.error("Error Occuring While Saving Data :::: " + ExceptionUtils.getStackTrace(e));
		}
		return trainingForm;
	}

	@Override
	public List<TrainingForm> getListForHr() throws Exception {
		List<TrainingForm> trainingForm = trainingFormDao.getListForHr();
		try {
			if (Objects.nonNull(trainingForm)) {
				return trainingForm;
			} else {
				throw new Exception("Error occur while display Training list to HR ");
			}
		} catch (Exception e) {
			LOGGER.error("Error Occuring While Display Training List to HR :::::: " + ExceptionUtils.getStackTrace(e));
		}
		return new ArrayList<>();
	}

	@Override
	public List<TrainingForm> getListForManger(String userId) throws Exception {
		List<TrainingForm> trainingForm = trainingFormDao.getListForManger(userId);
		try {
			if (Objects.nonNull(trainingForm)) {
				return trainingForm;
			} else {
				throw new Exception("Error occur while display Training List to Manager");
			}
		} catch (Exception e) {
			LOGGER.error(
					"Error Occuring While Display Training List to Manager :::::: " + ExceptionUtils.getStackTrace(e));
		}
		return new ArrayList<>();
	}

	@Override
	public TrainingForm getTrainingReq(String userId, String subject) throws Exception {
		TrainingForm trainingForm = trainingFormDao.getTrainingReq(userId, subject);
		try {
			if (Objects.nonNull(trainingForm)) {
				return trainingForm;
			} else {
				throw new Exception("Error occur while display Training Data into mail");
			}
		} catch (Exception e) {
			LOGGER.error(
					"Error Occuring While Display Training Data into Mail :::::: " + ExceptionUtils.getStackTrace(e));
		}
		return new TrainingForm();
	}

	@Override
	public TrainingForm getDeactiveTraining(TrainingForm trainingForm) throws Exception {
		try {
			if (Objects.nonNull(trainingForm)) {
				trainingFormDao.getDeactiveTraining(trainingForm);
			} else {
				throw new Exception("Error occur while deactivate Training ");
			}
		} catch (Exception e) {
			LOGGER.error("Error Occuring While Deactivate Training :::::: " + ExceptionUtils.getStackTrace(e));
		}
		return trainingForm;
	}

	@Override
	public TrainingForm getActiveTraining(TrainingForm trainingForm) throws Exception {
		try {
			if (Objects.nonNull(trainingForm)) {
				trainingFormDao.getActiveTraining(trainingForm);
			} else {
				throw new Exception("Error occur while activate Training ");
			}
		} catch (Exception e) {
			LOGGER.error("Error Occuring While Aactivate Training :::::: " + ExceptionUtils.getStackTrace(e));
		}
		return trainingForm;
	}

	@Override
	public TrainingForm getById(Long id) throws Exception {
		TrainingForm trainingForm = new TrainingForm();
		try {
			trainingForm = trainingFormDao.getById(id);
		} catch (Exception e) {
			LOGGER.error("Error Occuring While Get Training by ID :::::: " + ExceptionUtils.getStackTrace(e));
		}
		return trainingForm;
	}
	
	@Override
	public List<TrainingForm> getTotalTraining() throws Exception {
		List<TrainingForm> trainingForms = trainingFormDao.getTotalTraining();
		try {
			if (Objects.nonNull(trainingForms)) {
				return trainingForms;
			} else {
				System.out.println("Null Data Get :::::::: ");
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while get list of total training form " + ExceptionUtils.getStackTrace(e));
		}
		return new ArrayList<>();
	}

	@Override
	public long getAllTraining() throws Exception {
		try {
			return trainingFormDao.getAllTraining();
		} catch (Exception e) {
			LOGGER.error("Error occuring while get all training of employee in current month " + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(" No record exist... " );
		}
	}
}