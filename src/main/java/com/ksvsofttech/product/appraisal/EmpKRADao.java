package com.ksvsofttech.product.appraisal;

import java.util.List;
import java.util.Optional;

import com.ksvsofttech.product.entities.EmpWorkDetails;

public interface EmpKRADao {

	public List<EmpWorkDetails> getEmpWithManger(String empId) throws Exception;

	public List<EmpKRA> getCurrentEmpAppraisal(String empId) throws Exception;

	public List<EmpKRA> getAllEmpAppraisal() throws Exception;

	public Optional<EmpKRA> findByempId(String empId) throws Exception;

	public EmpKRA saveTagKra(EmpKRA empKRA) throws Exception;

	public EmpKRA updateTagKra(EmpKRA empKRA) throws Exception;

	public EmpKRA getById(Long id) throws Exception;

	public EmpKRA getKRAWithDepIdAndEmpId(String depId, String empId) throws Exception;

	public EmpKRA getManagerIdWithMangerIdWithDepId(String reportingManager, String depId, String empId)
			throws Exception;

	public EmpKRA saveDraftSelfAppraisal(EmpKRA empKRA) throws Exception;

	public EmpKRA saveSelfAppraisal(EmpKRA empKRA) throws Exception;

	public EmpKRA saveRatingByReportingManager(EmpKRA empKRA) throws Exception;

	public EmpKRA saveDraftRatingByReportingManager(EmpKRA empKRA) throws Exception;

	public EmpKRA saveRatingByReportingManager2(EmpKRA empKRA) throws Exception;

	public EmpKRA saveDraftRatingByReportingManager2(EmpKRA empKRA) throws Exception;

	public List<EmpKRA> getTeammetsTeamMangerId(String empId, String depId) throws Exception;

	public List<EmpKRA> getEmpWithMangerIdOrReportingManager(String empId, String depId, List<String> empIds)
			throws Exception;

	public List<String> getEmpWithManger3(String empId) throws Exception;

	public EmpWorkDetails getEmpWithManger1(String empId, List<String> empId2) throws Exception;

	public List<EmpKRA> findEmpByEmpId(String empId) throws Exception;

	public List<EmpKRA> findEmpByDepId(String depId) throws Exception;

	public List<EmpKRA> appraisalCycleList(String empId) throws Exception;

	public EmpKRA getKRAWithDepIdAndEmpIdAndDate(String depId, String empId, String date) throws Exception;

	public Optional<EmpKRA> duplicateEmpKRACheck(String kraI, String kraII, String kraIII, String kraIV, String kraV,
			String kraVI, String kraVII, String kraVIII, String kraIX, String kraX) throws Exception;

	public boolean empKRAExists(String kraI, String kraII, String kraIII, String kraIV, String kraV, String kraVI,
			String kraVII, String kraVIII, String kraIX, String kraX) throws Exception;
}