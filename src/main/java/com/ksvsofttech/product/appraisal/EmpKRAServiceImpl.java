package com.ksvsofttech.product.appraisal;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksvsofttech.product.entities.EmpWorkDetails;

@Service
@Transactional
public class EmpKRAServiceImpl implements EmpKRAService {
	private static final Logger LOGGER = LogManager.getLogger(EmpKRAServiceImpl.class);

	@Autowired
	EmpKRADao empKRADao;

	@Override
	public List<EmpWorkDetails> getEmpWithManger(String empId) throws Exception {
		List<EmpWorkDetails> empKRAs = empKRADao.getEmpWithManger(empId);
		try {
			if (Objects.nonNull(empKRAs)) {
				return empKRAs;
			} else {
				System.out.println("emp kra object is null ::: ");
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while display list if emp with managerId " + ExceptionUtils.getStackTrace(e));
		}
		return new ArrayList<>();
	}

	@Override
	public List<EmpKRA> getCurrentEmpAppraisal(String empId) throws Exception {
		List<EmpKRA> empKRAs = empKRADao.getCurrentEmpAppraisal(empId);
		try {
			if (Objects.nonNull(empKRAs)) {
				return empKRAs;
			} else {
				System.out.println("emp kra object is null ::: ");
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while display appraisal list of current emp " + ExceptionUtils.getStackTrace(e));
		}
		return new ArrayList<>();
	}

	@Override
	public List<EmpKRA> getAllEmpAppraisal() throws Exception {
		List<EmpKRA> empKRAs = empKRADao.getAllEmpAppraisal();
		try {
			if (Objects.nonNull(empKRAs)) {
				return empKRAs;
			} else {
				System.out.println("emp kra object is null ::: ");
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while display all emp appraisal " + ExceptionUtils.getStackTrace(e));
		}
		return new ArrayList<>();
	}

	@Override
	public Optional<EmpKRA> findByempId(String empId) throws Exception {
		Optional<EmpKRA> optional = empKRADao.findByempId(empId);
		try {
			if (optional.isPresent()) {
				return optional;
			}
		} catch (Exception e) {
			LOGGER.error("Error occuring while get emp id ... " + ExceptionUtils.getStackTrace(e));
		}
		return Optional.empty();
	}

	@Override
	public EmpKRA saveTagKra(EmpKRA empKRA) throws Exception {
		try {
			if (Objects.nonNull(empKRA)) {
				return empKRADao.saveTagKra(empKRA);
			}
		} catch (Exception e) {
			LOGGER.error("Error occuring while save or update tag kra ... " + ExceptionUtils.getStackTrace(e));
		}
		return empKRA;
	}

	@Override
	public EmpKRA updateTagKra(EmpKRA empKRA) throws Exception {
		try {
			if (Objects.nonNull(empKRA)) {
				return empKRADao.updateTagKra(empKRA);
			}
		} catch (Exception e) {
			LOGGER.error("Error occuring while update tag kra ... " + ExceptionUtils.getStackTrace(e));
		}
		return empKRA;
	}

	@Override
	public EmpKRA getById(Long id) throws Exception {
		EmpKRA empKRA = empKRADao.getById(id);
		try {
			if (Objects.nonNull(empKRA)) {
				return empKRA;
			}
		} catch (Exception e) {
			LOGGER.error("Error occuring while get id ... " + ExceptionUtils.getStackTrace(e));
		}
		return new EmpKRA();
	}

	@Override
	public EmpKRA getKRAWithDepIdAndEmpId(String depId, String empId) throws Exception {
		EmpKRA empKRA = empKRADao.getKRAWithDepIdAndEmpId(depId, empId);
		try {
			if (Objects.nonNull(empKRA)) {
				return empKRA;
			}
		} catch (Exception e) {
			LOGGER.error("Error occuring while get manager id & emp id ... " + ExceptionUtils.getStackTrace(e));
		}
		return new EmpKRA();
	}

	@Override
	public EmpKRA getManagerIdWithMangerIdWithDepId(String reportingManager, String depId, String empId)
			throws Exception {
		EmpKRA empKRA = empKRADao.getManagerIdWithMangerIdWithDepId(reportingManager, depId, empId);
		try {
			if (Objects.nonNull(empKRA)) {
				return empKRA;
			}
		} catch (Exception e) {
			LOGGER.error("Error occuring while get reporting manager, depid id & emp id ... "
					+ ExceptionUtils.getStackTrace(e));
		}
		return new EmpKRA();
	}

	@Override
	public EmpKRA saveDraftSelfAppraisal(EmpKRA empKRA) throws Exception {
		try {
			if (Objects.nonNull(empKRA)) {
				return empKRADao.saveDraftSelfAppraisal(empKRA);
			}
		} catch (Exception e) {
			LOGGER.error("Error occuring while save draft appraisal ... " + ExceptionUtils.getStackTrace(e));
		}
		return empKRA;
	}

	@Override
	public EmpKRA saveSelfAppraisal(EmpKRA empKRA) throws Exception {
		try {
			if (Objects.nonNull(empKRA)) {
				return empKRADao.saveSelfAppraisal(empKRA);
			}
		} catch (Exception e) {
			LOGGER.error("Error occuring while save appraisal ... " + ExceptionUtils.getStackTrace(e));
		}
		return empKRA;
	}

	@Override
	public EmpKRA saveRatingByReportingManager(EmpKRA empKRA) throws Exception {
		try {
			if (Objects.nonNull(empKRA)) {
				return empKRADao.saveRatingByReportingManager(empKRA);
			}
		} catch (Exception e) {
			LOGGER.error(
					"Error occuring while save rating by reporting manager ... " + ExceptionUtils.getStackTrace(e));
		}
		return empKRA;
	}

	@Override
	public EmpKRA saveDraftRatingByReportingManager(EmpKRA empKRA) throws Exception {
		try {
			if (Objects.nonNull(empKRA)) {
				return empKRADao.saveDraftRatingByReportingManager(empKRA);
			}
		} catch (Exception e) {
			LOGGER.error("Error occuring while save draft rating by reporting manager ... "
					+ ExceptionUtils.getStackTrace(e));
		}
		return empKRA;
	}

	@Override
	public EmpKRA saveDraftRatingByReportingManager2(EmpKRA empKRA) throws Exception {
		try {
			if (Objects.nonNull(empKRA)) {
				return empKRADao.saveDraftRatingByReportingManager2(empKRA);
			}
		} catch (Exception e) {
			LOGGER.error("Error occuring while save draft rating by reporting manager ... "
					+ ExceptionUtils.getStackTrace(e));
		}
		return empKRA;
	}

	@Override
	public EmpKRA saveRatingByReportingManager2(EmpKRA empKRA) throws Exception {
		try {
			if (Objects.nonNull(empKRA)) {
				return empKRADao.saveRatingByReportingManager2(empKRA);
			}
		} catch (Exception e) {
			LOGGER.error(
					"Error occuring while save rating by reporting manager ... " + ExceptionUtils.getStackTrace(e));
		}
		return empKRA;
	}

	@Override
	public List<EmpKRA> getTeammetsTeamMangerId(String empId, String depId) {
		try {
			return empKRADao.getTeammetsTeamMangerId(empId, depId);
		} catch (Exception e) {
			LOGGER.error("Error occuring while get teammets team manager id  ... " + ExceptionUtils.getStackTrace(e));
		}
		return new ArrayList<>();
	}

	@Override
	public List<EmpKRA> getEmpWithMangerIdOrReportingManager(String empId, String depId, List<String> empIds) {
		try {
			return empKRADao.getEmpWithMangerIdOrReportingManager(empId, depId, empIds);
		} catch (Exception e) {
			LOGGER.error("Error occuring while get Emp With MangerId Or ReportingManager ... "
					+ ExceptionUtils.getStackTrace(e));
		}
		return new ArrayList<>();
	}

	@Override
	public List<String> getEmpWithManger3(String empId) {
		try {
			if (Objects.nonNull(empId)) {
				return empKRADao.getEmpWithManger3(empId);
			}
		} catch (Exception e) {
			LOGGER.error("Error occuring while to get Emp With Manger ... " + ExceptionUtils.getStackTrace(e));
		}
		return new ArrayList<>();
	}

	@Override
	public EmpWorkDetails getEmpWithManger1(String empId, List<String> empId2) {
		try {
			return empKRADao.getEmpWithManger1(empId, empId2);
		} catch (Exception e) {
			LOGGER.error("Error occuring while get Emp With MangerId Or ReportingManager ... "
					+ ExceptionUtils.getStackTrace(e));
		}
		return new EmpWorkDetails();
	}

	@Override
	public List<EmpKRA> findEmpByEmpId(String empId) throws Exception {
		try {
			return empKRADao.findEmpByEmpId(empId);
		} catch (Exception e) {
			LOGGER.error("Error occuring while get Emp by empId " + ExceptionUtils.getStackTrace(e));
		}
		return new ArrayList<>();
	}

	@Override
	public List<EmpKRA> findEmpByDepId(String depId) throws Exception {
		try {
			return empKRADao.findEmpByDepId(depId);
		} catch (Exception e) {
			LOGGER.error("Error occuring while get Emp With depId" + ExceptionUtils.getStackTrace(e));
		}
		return new ArrayList<>();
	}

	@Override
	public List<EmpKRA> appraisalCycleList(String empId) throws Exception {
		try {
			return empKRADao.appraisalCycleList(empId);
		} catch (Exception e) {
			LOGGER.error("Error occuring while get appraisal Cycle List" + ExceptionUtils.getStackTrace(e));
		}
		return new ArrayList<>();
	}

	@Override
	public EmpKRA getKRAWithDepIdAndEmpIdAndDate(String depId, String empId, String date) throws Exception {
		try {
			return empKRADao.getKRAWithDepIdAndEmpIdAndDate(depId, empId, date);
		} catch (Exception e) {
			LOGGER.error(
					"Error occuring while get KRA With DepId And EmpId And Date" + ExceptionUtils.getStackTrace(e));
		}
		return new EmpKRA();

	}

	@Override
	public Optional<EmpKRA> duplicateEmpKRACheck(String kraI, String kraII, String kraIII, String kraIV, String kraV,
			String kraVI, String kraVII, String kraVIII, String kraIX, String kraX) throws Exception {
		try {
			return empKRADao.duplicateEmpKRACheck(kraI, kraII, kraIII, kraIV, kraV, kraVI, kraVII, kraVIII, kraIX,
					kraX);
		} catch (Exception e) {
			LOGGER.error("------Error occur while find duplicate emp kra ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No kra exists......");
		}
	}

	@Override
	public boolean empKRAExists(String kraI, String kraII, String kraIII, String kraIV, String kraV, String kraVI,
			String kraVII, String kraVIII, String kraIX, String kraX) throws Exception {
		try {
			return duplicateEmpKRACheck(kraI, kraII, kraIII, kraIV, kraV, kraVI, kraVII, kraVIII, kraIX, kraX)
					.isPresent();
		} catch (Exception e) {
			LOGGER.error("------Error occur while find emp kra ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No kra code exists......");
		}

	}
}