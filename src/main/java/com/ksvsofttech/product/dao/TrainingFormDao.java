package com.ksvsofttech.product.dao;

import java.util.List;

import com.ksvsofttech.product.entities.TrainingForm;

public interface TrainingFormDao {

	public List<TrainingForm> getActiveList(String userId) throws Exception;

	public List<TrainingForm> getInActiveList(String userId) throws Exception;

	public TrainingForm saveUpdate(TrainingForm trainingForm) throws Exception;

	public List<TrainingForm> getListForHr() throws Exception;

	public List<TrainingForm> getListForManger(String userId) throws Exception;

	public TrainingForm getTrainingReq(String userId, String subject) throws Exception;

	public TrainingForm getDeactiveTraining(TrainingForm trainingForm) throws Exception;

	public TrainingForm getActiveTraining(TrainingForm trainingForm) throws Exception;
	
	public TrainingForm getById(Long id) throws Exception;
	
	public List<TrainingForm> getTotalTraining() throws Exception;
	
	public long getAllTraining() throws Exception;
}
