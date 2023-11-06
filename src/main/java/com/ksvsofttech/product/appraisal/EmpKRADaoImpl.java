package com.ksvsofttech.product.appraisal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.entities.EmpBasicDetails;
import com.ksvsofttech.product.entities.EmpWorkDetails;
import com.ksvsofttech.product.repository.EmpWorkRepository;
import com.ksvsofttech.product.service.EmpRegistartionService;

@Repository
@Transactional
public class EmpKRADaoImpl implements EmpKRADao {
	private static final Logger LOGGER = LogManager.getLogger(EmpKRADaoImpl.class);

	@Autowired
	private EmpKRARepository empKRARepository;
	@Autowired
	private EmpRegistartionService empRegistartionService;
	@Autowired
	private EmpWorkRepository empWorkRepository;
	@Autowired
	private KRARepository kraRepository;

	@Override
	public List<EmpWorkDetails> getEmpWithManger(String empId) throws Exception {
		List<EmpWorkDetails> empKRAs = empWorkRepository.getEmpWithManger(empId);
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
		List<EmpKRA> empKRAs = empKRARepository.getCurrentEmpAppraisal(empId);
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
		List<EmpKRA> empKRAs = empKRARepository.getAllEmpAppraisal();
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
		Optional<EmpKRA> optional = empKRARepository.findByempId(empId);
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
			EmpBasicDetails basicDetails = empRegistartionService.getCurrentUser(empKRA.getUserId());
			KRA kra = kraRepository.getKRA(empKRA.getKraI());

			Optional<EmpKRA> optional = empKRARepository.findByempId(empKRA.getUserId());

			if (optional.isPresent()) {
				System.out.println("If Save2 Tag Kra :::::: ");
				EmpKRA newEmpKra = optional.get();
				if (newEmpKra.getKraI() == null) {
					newEmpKra.setKraI(empKRA.getKraI());
					newEmpKra.setWeightageI(empKRA.getWeightageI());
					newEmpKra.setDescriptionI(kra.getKraDescription().getDescription());
					empKRARepository.save(newEmpKra);
				} else if (newEmpKra.getKraII() == null) {
					newEmpKra.setKraII(empKRA.getKraI());
					newEmpKra.setWeightageII(empKRA.getWeightageI());
					newEmpKra.setDescriptionII(kra.getKraDescription().getDescription());
					empKRARepository.save(newEmpKra);
				} else if (newEmpKra.getKraIII() == null) {
					newEmpKra.setKraIII(empKRA.getKraI());
					newEmpKra.setWeightageIII(empKRA.getWeightageI());
					newEmpKra.setDescriptionIII(kra.getKraDescription().getDescription());
					empKRARepository.save(newEmpKra);
				} else if (newEmpKra.getKraIV() == null) {
					newEmpKra.setKraIV(empKRA.getKraI());
					newEmpKra.setWeightageIV(empKRA.getWeightageI());
					newEmpKra.setDescriptionIV(kra.getKraDescription().getDescription());
					empKRARepository.save(newEmpKra);
				} else if (newEmpKra.getKraV() == null) {
					newEmpKra.setKraV(empKRA.getKraI());
					newEmpKra.setWeightageV(empKRA.getWeightageI());
					newEmpKra.setDescriptionV(kra.getKraDescription().getDescription());
					empKRARepository.save(newEmpKra);
				} else if (newEmpKra.getKraVI() == null) {
					newEmpKra.setKraVI(empKRA.getKraI());
					newEmpKra.setWeightageVI(empKRA.getWeightageI());
					newEmpKra.setDescriptionVI(kra.getKraDescription().getDescription());
					empKRARepository.save(newEmpKra);
				} else if (newEmpKra.getKraVII() == null) {
					newEmpKra.setKraVII(empKRA.getKraI());
					newEmpKra.setWeightageVII(empKRA.getWeightageI());
					newEmpKra.setDescriptionVII(kra.getKraDescription().getDescription());
					empKRARepository.save(newEmpKra);
				} else if (newEmpKra.getKraVIII() == null) {
					newEmpKra.setKraVIII(empKRA.getKraI());
					newEmpKra.setWeightageVIII(empKRA.getWeightageI());
					newEmpKra.setDescriptionVIII(kra.getKraDescription().getDescription());
					empKRARepository.save(newEmpKra);
				} else if (newEmpKra.getKraIX() == null) {
					newEmpKra.setKraIX(empKRA.getKraI());
					newEmpKra.setWeightageIX(empKRA.getWeightageI());
					newEmpKra.setDescriptionIX(kra.getKraDescription().getDescription());
					empKRARepository.save(newEmpKra);
				} else if (newEmpKra.getKraX() == null) {
					newEmpKra.setKraX(empKRA.getKraI());
					newEmpKra.setWeightageX(empKRA.getWeightageI());
					newEmpKra.setDescriptionX(kra.getKraDescription().getDescription());
					empKRARepository.save(newEmpKra);
				}
			} else {
				System.out.println("If Save Tag Kra :::::: ");
				empKRA.setDescriptionI(kra.getKraDescription().getDescription());
				empKRA.setDepId(basicDetails.getDepartName());
				empKRA.setGrade(basicDetails.getGrade());
				empKRA.setManagerId(basicDetails.getEmpWorkDetails().getReportingManager());
				empKRA.setSelfAppStatus("Pending");
				empKRA.setIsActive("1");
				empKRA.setDate(new Date());
				empKRA.setEmpBasicDetails(basicDetails);
				empKRARepository.save(empKRA);
			}

		} catch (Exception e) {
			LOGGER.error("Error occuring while save tag kra ... " + ExceptionUtils.getStackTrace(e));
		}
		return empKRA;
	}

	@Override
	public EmpKRA updateTagKra(EmpKRA empKRA) throws Exception {
		try {
			KRA kra = kraRepository.getKRA(empKRA.getKraI());

			Optional<EmpKRA> optional = empKRARepository.findByempId(empKRA.getUserId());

			if (optional.isPresent()) {
				EmpKRA newEmpKra = optional.get();
				if (newEmpKra.getKraI() == null) {
					newEmpKra.setKraI(empKRA.getKraI());
					newEmpKra.setWeightageI(empKRA.getWeightageI());
					newEmpKra.setDescriptionI(kra.getKraDescription().getDescription());
					empKRARepository.save(newEmpKra);
				} else if (newEmpKra.getKraII() == null) {
					newEmpKra.setKraII(empKRA.getKraI());
					newEmpKra.setWeightageII(empKRA.getWeightageI());
					newEmpKra.setDescriptionII(kra.getKraDescription().getDescription());
					empKRARepository.save(newEmpKra);
				} else if (newEmpKra.getKraIII() == null) {
					newEmpKra.setKraIII(empKRA.getKraI());
					newEmpKra.setWeightageIII(empKRA.getWeightageI());
					newEmpKra.setDescriptionIII(kra.getKraDescription().getDescription());
					empKRARepository.save(newEmpKra);
				} else if (newEmpKra.getKraIV() == null) {
					newEmpKra.setKraIV(empKRA.getKraI());
					newEmpKra.setWeightageIV(empKRA.getWeightageI());
					newEmpKra.setDescriptionIV(kra.getKraDescription().getDescription());
					empKRARepository.save(newEmpKra);
				} else if (newEmpKra.getKraV() == null) {
					newEmpKra.setKraV(empKRA.getKraI());
					newEmpKra.setWeightageV(empKRA.getWeightageI());
					newEmpKra.setDescriptionV(kra.getKraDescription().getDescription());
					empKRARepository.save(newEmpKra);
				} else if (newEmpKra.getKraVI() == null) {
					newEmpKra.setKraVI(empKRA.getKraI());
					newEmpKra.setWeightageVI(empKRA.getWeightageI());
					newEmpKra.setDescriptionVI(kra.getKraDescription().getDescription());
					empKRARepository.save(newEmpKra);
				} else if (newEmpKra.getKraVII() == null) {
					newEmpKra.setKraVII(empKRA.getKraI());
					newEmpKra.setWeightageVII(empKRA.getWeightageI());
					newEmpKra.setDescriptionVII(kra.getKraDescription().getDescription());
					empKRARepository.save(newEmpKra);
				} else if (newEmpKra.getKraVIII() == null) {
					newEmpKra.setKraVIII(empKRA.getKraI());
					newEmpKra.setWeightageVIII(empKRA.getWeightageI());
					newEmpKra.setDescriptionVIII(kra.getKraDescription().getDescription());
					empKRARepository.save(newEmpKra);
				} else if (newEmpKra.getKraIX() == null) {
					newEmpKra.setKraIX(empKRA.getKraI());
					newEmpKra.setWeightageIX(empKRA.getWeightageI());
					newEmpKra.setDescriptionIX(kra.getKraDescription().getDescription());
					empKRARepository.save(newEmpKra);
				} else if (newEmpKra.getKraX() == null) {
					newEmpKra.setKraX(empKRA.getKraI());
					newEmpKra.setWeightageX(empKRA.getWeightageI());
					newEmpKra.setDescriptionX(kra.getKraDescription().getDescription());
					empKRARepository.save(newEmpKra);
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error occuring while update tag kra ... " + ExceptionUtils.getStackTrace(e));
		}
		return empKRA;
	}

	@Override
	public EmpKRA getById(Long id) throws Exception {
		EmpKRA empKRA = empKRARepository.getById(id);
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
		EmpKRA empKRA = empKRARepository.getKRAWithDepIdAndEmpId(depId, empId);
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
		EmpKRA empKRA = empKRARepository.getManagerIdWithMangerIdWithDepId(reportingManager, depId, empId);
		try {
			if (Objects.nonNull(empKRA)) {
				return empKRA;
			}
		} catch (Exception e) {
			LOGGER.error("Error occuring while get reportingManager, dep id & emp id ... "
					+ ExceptionUtils.getStackTrace(e));
		}
		return new EmpKRA();
	}

	@Override
	public EmpKRA saveDraftSelfAppraisal(EmpKRA empKRA) throws Exception {
		String empId = null;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();

			System.out.println("EMPLOYEE ID ----------- " + empKRA.getUserId());

			Optional<EmpKRA> optional = empKRARepository.findByempId(empKRA.getUserId());
			if (optional.isPresent()) {
				System.out.println("IF :::::::::::");
				EmpKRA newEmpKRA = optional.get();
				newEmpKRA.setKraId(empKRA.getKraId());
				newEmpKRA.setSelfRatingI(empKRA.getSelfRatingI());
				newEmpKRA.setSelfRatingII(empKRA.getSelfRatingII());
				newEmpKRA.setSelfRatingIII(empKRA.getSelfRatingIII());
				newEmpKRA.setSelfRatingIV(empKRA.getSelfRatingIV());
				newEmpKRA.setSelfRatingV(empKRA.getSelfRatingV());
				newEmpKRA.setSelfRatingVI(empKRA.getSelfRatingVI());
				newEmpKRA.setSelfRatingVII(empKRA.getSelfRatingVII());
				newEmpKRA.setSelfRatingVIII(empKRA.getSelfRatingVIII());
				newEmpKRA.setSelfRatingIX(empKRA.getSelfRatingIX());
				newEmpKRA.setSelfRatingX(empKRA.getSelfRatingX());
				newEmpKRA.setTotalSelf(empKRA.getTotalSelf());
				newEmpKRA.setSelfAppStatus("Draft");
				newEmpKRA.setLevelIAppStatus("To be Provided");
				newEmpKRA.setCreatedDate(new Date());
				newEmpKRA.setDate(new Date());
				newEmpKRA.setCreatedBy(empId);
				newEmpKRA.setSelfRating(empKRA.getSelfRating());

				empKRARepository.save(newEmpKRA);
			} else {
				System.out.println("ELSE :::::::::::");
				System.out.println("EmpKRA object is null");
			}
		} catch (Exception e) {
			LOGGER.error("Error occuring while save draft appraisal ... " + ExceptionUtils.getStackTrace(e));
		}
		return empKRA;
	}

	@Override
	public EmpKRA saveSelfAppraisal(EmpKRA empKRA) throws Exception {
		String empId = null;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();

			Optional<EmpKRA> optional = empKRARepository.findByempId(empKRA.getUserId());
			if (optional.isPresent()) {
				System.out.println("IF :::::::::::");
				EmpKRA newEmpKRA = optional.get();
				newEmpKRA.setKraId(empKRA.getKraId());
				newEmpKRA.setSelfRatingI(empKRA.getSelfRatingI());
				newEmpKRA.setSelfRatingII(empKRA.getSelfRatingII());
				newEmpKRA.setSelfRatingIII(empKRA.getSelfRatingIII());
				newEmpKRA.setSelfRatingIV(empKRA.getSelfRatingIV());
				newEmpKRA.setSelfRatingV(empKRA.getSelfRatingV());
				newEmpKRA.setSelfRatingVI(empKRA.getSelfRatingVI());
				newEmpKRA.setSelfRatingVII(empKRA.getSelfRatingVII());
				newEmpKRA.setSelfRatingVIII(empKRA.getSelfRatingVIII());
				newEmpKRA.setSelfRatingIX(empKRA.getSelfRatingIX());
				newEmpKRA.setSelfRatingX(empKRA.getSelfRatingX());
				newEmpKRA.setTotalSelf(empKRA.getTotalSelf());
				newEmpKRA.setSelfAppStatus("Completed");
				newEmpKRA.setLevelIAppStatus("To be Provided");
				newEmpKRA.setCreatedDate(new Date());
				newEmpKRA.setDate(new Date());
				newEmpKRA.setCreatedBy(empId);
				newEmpKRA.setSelfRating(empKRA.getSelfRating());
				newEmpKRA.setSubmittedOn(new Date());

				empKRARepository.save(newEmpKRA);
			} else {
				System.out.println("ELSE :::::::::::");
				System.out.println("EmpKRA object is null");
			}
		} catch (Exception e) {
			LOGGER.error("Error occuring while save appraisal ... " + ExceptionUtils.getStackTrace(e));
		}
		return empKRA;
	}

	@Override
	public EmpKRA saveDraftRatingByReportingManager(EmpKRA empKRA) throws Exception {
		try {
			Optional<EmpKRA> optional = empKRARepository.findByempId(empKRA.getUserId());
			if (optional.isPresent()) {
				System.out.println("IF ::::::::::: " + empKRA.getUserId());
				EmpKRA newEmpKRA = optional.get();
				newEmpKRA.setLevel1ApproverRatingI(empKRA.getLevel1ApproverRatingI());
				newEmpKRA.setLevel1ApproverRatingII(empKRA.getLevel1ApproverRatingII());
				newEmpKRA.setLevel1ApproverRatingIII(empKRA.getLevel1ApproverRatingIII());
				newEmpKRA.setLevel1ApproverRatingIV(empKRA.getLevel1ApproverRatingIV());
				newEmpKRA.setLevel1ApproverRatingV(empKRA.getLevel1ApproverRatingV());
				newEmpKRA.setLevel1ApproverRatingVI(empKRA.getLevel1ApproverRatingVI());
				newEmpKRA.setLevel1ApproverRatingVII(empKRA.getLevel1ApproverRatingVII());
				newEmpKRA.setLevel1ApproverRatingVIII(empKRA.getLevel1ApproverRatingVIII());
				newEmpKRA.setLevel1ApproverRatingIX(empKRA.getLevel1ApproverRatingIX());
				newEmpKRA.setLevel1ApproverRatingX(empKRA.getLevel1ApproverRatingX());
				newEmpKRA.setTotalLevelI(empKRA.getTotalLevelI());
				newEmpKRA.setLevelIAppStatus("Draft");
				newEmpKRA.setLevelIIAppStatus("To be Provided");
				newEmpKRA.setLevelIRating(empKRA.getLevelIRating());
				newEmpKRA.setTrainingReco(empKRA.getTrainingReco());
				newEmpKRA.setTrainingRecoComment(empKRA.getTrainingRecoComment());
				empKRARepository.save(newEmpKRA);
			} else {
				System.out.println("ELSE :::::::::::");
				System.out.println("EmpKRA object is null");
			}
		} catch (Exception e) {
			LOGGER.error("Error occuring while save draft rating by reporting manager ... "
					+ ExceptionUtils.getStackTrace(e));
		}
		return empKRA;
	}

	@Override
	public EmpKRA saveRatingByReportingManager(EmpKRA empKRA) throws Exception {
		try {
			Optional<EmpKRA> optional = empKRARepository.findByempId(empKRA.getUserId());
			if (optional.isPresent()) {
				System.out.println("IF ::::::::::: " + empKRA.getUserId());
				EmpKRA newEmpKRA = optional.get();
				newEmpKRA.setLevel1ApproverRatingI(empKRA.getLevel1ApproverRatingI());
				newEmpKRA.setLevel1ApproverRatingII(empKRA.getLevel1ApproverRatingII());
				newEmpKRA.setLevel1ApproverRatingIII(empKRA.getLevel1ApproverRatingIII());
				newEmpKRA.setLevel1ApproverRatingIV(empKRA.getLevel1ApproverRatingIV());
				newEmpKRA.setLevel1ApproverRatingV(empKRA.getLevel1ApproverRatingV());
				newEmpKRA.setLevel1ApproverRatingVI(empKRA.getLevel1ApproverRatingVI());
				newEmpKRA.setLevel1ApproverRatingVII(empKRA.getLevel1ApproverRatingVII());
				newEmpKRA.setLevel1ApproverRatingVIII(empKRA.getLevel1ApproverRatingVIII());
				newEmpKRA.setLevel1ApproverRatingIX(empKRA.getLevel1ApproverRatingIX());
				newEmpKRA.setLevel1ApproverRatingX(empKRA.getLevel1ApproverRatingX());
				newEmpKRA.setTotalLevelI(empKRA.getTotalLevelI());
				newEmpKRA.setLevelIAppStatus("Completed");
				newEmpKRA.setLevelIIAppStatus("To be Provided");
				newEmpKRA.setLevelIRating(empKRA.getLevelIRating());
				newEmpKRA.setTrainingReco(empKRA.getTrainingReco());
				newEmpKRA.setTrainingRecoComment(empKRA.getTrainingRecoComment());
				empKRARepository.save(newEmpKRA);
			} else {
				System.out.println("ELSE :::::::::::");
				System.out.println("EmpKRA object is null");
			}
		} catch (Exception e) {
			LOGGER.error(
					"Error occuring while save rating by reporting manager ... " + ExceptionUtils.getStackTrace(e));
		}
		return empKRA;
	}

	@Override
	public EmpKRA saveDraftRatingByReportingManager2(EmpKRA empKRA) throws Exception {
		try {
			Optional<EmpKRA> optional = empKRARepository.findByempId(empKRA.getUserId());
			if (optional.isPresent()) {
				System.out.println("IF ::::::::::: " + empKRA.getUserId());
				EmpKRA newEmpKRA = optional.get();
				newEmpKRA.setLevel2ApproverRatingI(empKRA.getLevel2ApproverRatingI());
				newEmpKRA.setLevel2ApproverRatingII(empKRA.getLevel2ApproverRatingII());
				newEmpKRA.setLevel2ApproverRatingIII(empKRA.getLevel2ApproverRatingIII());
				newEmpKRA.setLevel2ApproverRatingIV(empKRA.getLevel2ApproverRatingIV());
				newEmpKRA.setLevel2ApproverRatingV(empKRA.getLevel2ApproverRatingV());
				newEmpKRA.setLevel2ApproverRatingVI(empKRA.getLevel2ApproverRatingVI());
				newEmpKRA.setLevel2ApproverRatingVII(empKRA.getLevel2ApproverRatingVII());
				newEmpKRA.setLevel2ApproverRatingVIII(empKRA.getLevel2ApproverRatingVIII());
				newEmpKRA.setLevel2ApproverRatingIX(empKRA.getLevel2ApproverRatingIX());
				newEmpKRA.setLevel2ApproverRatingX(empKRA.getLevel2ApproverRatingX());
				newEmpKRA.setTotalLevelII(empKRA.getTotalLevelII());
				newEmpKRA.setLevelIIAppStatus("Draft");
				newEmpKRA.setLevelIIRating(empKRA.getLevelIIRating());
				empKRARepository.save(newEmpKRA);
			} else {
				System.out.println("ELSE :::::::::::");
				System.out.println("EmpKRA object is null");
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
			Optional<EmpKRA> optional = empKRARepository.findByempId(empKRA.getUserId());
			if (optional.isPresent()) {
				System.out.println("IF ::::::::::: " + empKRA.getUserId());
				EmpKRA newEmpKRA = optional.get();
				newEmpKRA.setLevel2ApproverRatingI(empKRA.getLevel2ApproverRatingI());
				newEmpKRA.setLevel2ApproverRatingII(empKRA.getLevel2ApproverRatingII());
				newEmpKRA.setLevel2ApproverRatingIII(empKRA.getLevel2ApproverRatingIII());
				newEmpKRA.setLevel2ApproverRatingIV(empKRA.getLevel2ApproverRatingIV());
				newEmpKRA.setLevel2ApproverRatingV(empKRA.getLevel2ApproverRatingV());
				newEmpKRA.setLevel2ApproverRatingVI(empKRA.getLevel2ApproverRatingVI());
				newEmpKRA.setLevel2ApproverRatingVII(empKRA.getLevel2ApproverRatingVII());
				newEmpKRA.setLevel2ApproverRatingVIII(empKRA.getLevel2ApproverRatingVIII());
				newEmpKRA.setLevel2ApproverRatingIX(empKRA.getLevel2ApproverRatingIX());
				newEmpKRA.setLevel2ApproverRatingX(empKRA.getLevel2ApproverRatingX());
				newEmpKRA.setTotalLevelII(empKRA.getTotalLevelII());
				newEmpKRA.setLevelIIAppStatus("Completed");
				newEmpKRA.setLevelIIRating(empKRA.getLevelIIRating());
				empKRARepository.save(newEmpKRA);
			} else {
				System.out.println("ELSE :::::::::::");
				System.out.println("EmpKRA object is null");
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
			return empKRARepository.getTeammetsTeamMangerId(empId, depId);
		} catch (Exception e) {
			LOGGER.error("Error occuring while get teammets team manager id  ... " + ExceptionUtils.getStackTrace(e));
		}
		return new ArrayList<>();
	}

	@Override
	public List<EmpKRA> getEmpWithMangerIdOrReportingManager(String empId, String depId, List<String> empIds) {
		try {
			return empKRARepository.getEmpWithMangerIdOrReportingManager(empId, depId, empIds);
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
				return empWorkRepository.getEmpWithManger3(empId);
			}
		} catch (Exception e) {
			LOGGER.error("Error occuring while to get Emp With Manger ... " + ExceptionUtils.getStackTrace(e));
		}
		return new ArrayList<>();
	}

	@Override
	public EmpWorkDetails getEmpWithManger1(String empId, List<String> empId2) {
		try {
			return empWorkRepository.getEmpWithManger1(empId, empId2);
		} catch (Exception e) {
			LOGGER.error("Error occuring while get Emp With MangerId Or ReportingManager ... "
					+ ExceptionUtils.getStackTrace(e));
		}
		return new EmpWorkDetails();
	}

	@Override
	public List<EmpKRA> findEmpByEmpId(String empId) throws Exception {
		try {
			return empKRARepository.findEmpByEmpId(empId);
		} catch (Exception e) {
			LOGGER.error("Error occuring while get Emp by empId " + ExceptionUtils.getStackTrace(e));
		}
		return new ArrayList<>();
	}

	@Override
	public List<EmpKRA> findEmpByDepId(String depId) throws Exception {
		try {
			return empKRARepository.findEmpByDepId(depId);
		} catch (Exception e) {
			LOGGER.error("Error occuring while get Emp With depId" + ExceptionUtils.getStackTrace(e));
		}
		return new ArrayList<>();
	}

	@Override
	public List<EmpKRA> appraisalCycleList(String empId) throws Exception {
		try {
			return empKRARepository.appraisalCycleList(empId);
		} catch (Exception e) {
			LOGGER.error("Error occuring while get appraisal Cycle List" + ExceptionUtils.getStackTrace(e));
		}
		return new ArrayList<>();
	}

	@Override
	public EmpKRA getKRAWithDepIdAndEmpIdAndDate(String depId, String empId, String date) throws Exception {
		try {
			return empKRARepository.getKRAWithDepIdAndEmpIdAndDate(depId, empId, date);
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
			return empKRARepository.duplicateEmpKRACheck(kraI, kraII, kraIII, kraIV, kraV, kraVI, kraVII, kraVIII,
					kraIX, kraX);
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