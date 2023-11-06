package com.ksvsofttech.product.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.entities.TrainingForm;

@Repository
@Transactional
public interface TrainingFormRepository extends JpaRepository<TrainingForm, Long> {

	@Query("SELECT e FROM TrainingForm e WHERE e.isActive = '1' AND e.userId = :userId ORDER BY date DESC")
	public List<TrainingForm> getActiveList(String userId);
	
	@Query("SELECT e FROM TrainingForm e WHERE e.isActive = '0' AND e.userId = :userId ORDER BY date DESC")
	public List<TrainingForm> getInactiveList(String userId);
	
	@Query(value = "SELECT * FROM TrainingForm WHERE createdBy = :userId AND date = CURDATE() AND isActive = '1' AND subject = :subject ", nativeQuery = true)
	public TrainingForm getTrainingReq(String userId, String subject);

	@Query("SELECT e FROM TrainingForm e WHERE e.isActive = '1' ORDER BY date DESC")
	public List<TrainingForm> getListForHr();

	@Query("SELECT e FROM TrainingForm e WHERE e.reportingManager = :userId AND e.isActive = '1' ORDER BY date DESC")
	public List<TrainingForm> getListForManger(String userId);

	/* DashBoard Count for HR */
	@Query(value = "SELECT count(*) FROM TrainingForm WHERE MONTH(date) = MONTH(CURRENT_DATE()) AND isActive = '1'", nativeQuery = true)
	public long getAllTraining();
	
	/* Link */
	@Query(value = "SELECT * FROM TrainingForm WHERE MONTH(date) = MONTH(CURRENT_DATE()) AND isActive = '1' ORDER BY date DESC", nativeQuery = true)
	public List<TrainingForm> getTotalTraining();

	
}
