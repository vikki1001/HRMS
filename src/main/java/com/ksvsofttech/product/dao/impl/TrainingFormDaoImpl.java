package com.ksvsofttech.product.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.dao.TrainingFormDao;
import com.ksvsofttech.product.entities.EmpBasicDetails;
import com.ksvsofttech.product.entities.TrainingForm;
import com.ksvsofttech.product.repository.EmpBasicRepository;
import com.ksvsofttech.product.repository.TrainingFormRepository;

@Repository
public class TrainingFormDaoImpl implements TrainingFormDao {
	private static final Logger LOGGER = LogManager.getLogger(TrainingFormDaoImpl.class);

	@Autowired
	private TrainingFormRepository trainingFormRepository;

	@Autowired
	private EmpBasicRepository empBasicRepository;

	@Override
	public List<TrainingForm> getActiveList(String userId) throws Exception {
		List<TrainingForm> trainingForm = trainingFormRepository.getActiveList(userId);
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
		List<TrainingForm> trainingForm = trainingFormRepository.getInactiveList(userId);
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
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String loginId = authentication.getName();
			if (Objects.nonNull(loginId)) {
				EmpBasicDetails userDetails = empBasicRepository.getCurrentUser(loginId);

				if (Objects.isNull(trainingForm.getId())) {
					trainingForm.setIsActive("1");
					trainingForm.setCreatedDate(new Date());
					trainingForm.setCreatedBy(loginId);
					trainingForm.setDate(new Date());
					trainingForm.setUserId(loginId);
					trainingForm.setReportingManager(userDetails.getEmpWorkDetails().getReportingManager());

					trainingFormRepository.save(trainingForm);
				} else {
					Optional<TrainingForm> trainingFormOptional = trainingFormRepository.findById(trainingForm.getId());
					if (trainingFormOptional.isPresent()) {
						TrainingForm newTrainingForm = trainingFormOptional.get();
						newTrainingForm.setSubject(trainingForm.getSubject());
						newTrainingForm.setTrainingPurpose(trainingForm.getTrainingPurpose());
						newTrainingForm.setLastModifiedBy(loginId);
						newTrainingForm.setLastModifiedDate(new Date());

						trainingFormRepository.save(newTrainingForm);
					} else {
						return trainingForm;
					}
				}
			} else {
				LOGGER.error("Error occur to get login id");
			}

		} catch (Exception e) {
			LOGGER.error("Error Occuring While Saving Data " + ExceptionUtils.getStackTrace(e));
		}
		return trainingForm;
	}

	@Override
	public List<TrainingForm> getListForHr() throws Exception {
		List<TrainingForm> trainingForm = trainingFormRepository.getListForHr();
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
		List<TrainingForm> trainingForm = trainingFormRepository.getListForManger(userId);
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
		TrainingForm trainingForm = trainingFormRepository.getTrainingReq(userId, subject);
		try {
			if (Objects.nonNull(trainingForm)) {
				return trainingForm;
			} else {
				throw new Exception("Error occur while display Training Data into mail");
			}
		} catch (Exception e) {
			LOGGER.error("Error Occuring While Display Training Training Data into Mail :::::: "
					+ ExceptionUtils.getStackTrace(e));
		}
		return new TrainingForm();
	}

	@Override
	public TrainingForm getDeactiveTraining(TrainingForm trainingForm) throws Exception {
		Optional<TrainingForm> trainingFormOptional = trainingFormRepository.findById(trainingForm.getId());
		try {
			if (trainingFormOptional.isPresent()) {
				TrainingForm newTrainingForm = trainingFormOptional.get();
				newTrainingForm.setIsActive("0");
				trainingFormRepository.save(newTrainingForm);
			} else {
				return trainingForm;
			}
		} catch (Exception e) {
			LOGGER.error("Error Occuring While Deactivate Training :::::: " + ExceptionUtils.getStackTrace(e));
		}
		return trainingForm;
	}

	@Override
	public TrainingForm getActiveTraining(TrainingForm trainingForm) throws Exception {
		Optional<TrainingForm> trainingFormOptional = trainingFormRepository.findById(trainingForm.getId());
		try {
			if (trainingFormOptional.isPresent()) {
				TrainingForm newTrainingForm = trainingFormOptional.get();
				newTrainingForm.setIsActive("1");
				trainingFormRepository.save(newTrainingForm);
			} else {
				return trainingForm;
			}
		} catch (Exception e) {
			LOGGER.error("Error Occuring While Activate Training :::::: " + ExceptionUtils.getStackTrace(e));
		}
		return trainingForm;
	}
	
	@Override
	public TrainingForm getById(Long id) throws Exception {
		TrainingForm trainingForm = null;
		Optional<TrainingForm> optional = trainingFormRepository.findById(id);
		try {
			if (optional.isPresent()) {
				trainingForm = optional.get();
			} else {
				System.out.println("Error occur while get training by ID");
			}
		} catch (Exception e) {
			LOGGER.error("Error Occuring While Get Training by ID :::::: " + ExceptionUtils.getStackTrace(e));
		}
		return trainingForm;
	}

	@Override
	public List<TrainingForm> getTotalTraining() throws Exception {
		List<TrainingForm> trainingForms = trainingFormRepository.getTotalTraining();
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
			return trainingFormRepository.getAllTraining();
		} catch (Exception e) {
			LOGGER.error("Error occuring while get all training of employee in current month " + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(" No record exist... " );
		}
	}
}