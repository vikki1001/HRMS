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

import com.ksvsofttech.product.dao.EmpRegistartionDao;
import com.ksvsofttech.product.entities.EmpBasicDetails;
import com.ksvsofttech.product.entities.EmpWorkDetails;
import com.ksvsofttech.product.entities.SequenceMst;
import com.ksvsofttech.product.repository.EmpBasicRepository;
import com.ksvsofttech.product.repository.EmpWorkRepository;
import com.ksvsofttech.product.repository.SequenceMstRepository;

@Repository
public class EmpRegistartionDaoImpl implements EmpRegistartionDao {
	private static final Logger LOG = LogManager.getLogger(EmpRegistartionDaoImpl.class);

	@Autowired
	private EmpBasicRepository empBasicRepository;
	
	@Autowired
	private EmpWorkRepository empWorkRepository;

	@Autowired
	private SequenceMstRepository sequenceMstRepository;

	@Override
	public List<EmpBasicDetails> getFindAllEmpRegList() throws Exception {
		List<EmpBasicDetails> empBasicDetails = empBasicRepository.findAll();
		try {
			if (!empBasicDetails.isEmpty()) {
				return empBasicDetails;
			}
		} catch (Exception e) {
			LOG.error("------Error occur while display findAll employee registartion list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No findAll employee register record exist......");
		}
		return new ArrayList<>();
	}

	/* Display List of IsActive Employee Registration list */
	@Override
	public List<EmpBasicDetails> getIsActiveEmpRegList() throws Exception {
		List<EmpBasicDetails> empBasicDetails = empBasicRepository.getIsActive();
		try {
			if (!empBasicDetails.isEmpty()) {
				return empBasicDetails;
			}
		} catch (Exception e) {
			LOG.error("------Error occur while display isactive employee registartion list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No isactive employee register record exist......");
		}
		return new ArrayList<>();
	}

	/* Display List of InActive Employee Registration list */
	@Override
	public List<EmpBasicDetails> getInActiveEmpRegList() throws Exception {
		List<EmpBasicDetails> empBasicDetails = empBasicRepository.getInActive();
		try {
			if (!empBasicDetails.isEmpty()) {
				return empBasicDetails;
			}
		} catch (Exception e) {
			LOG.error("------Error occur while display inactive employee registartion list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No inactive employee register record exist......");
		}
		return new ArrayList<>();
	}

	/* Find Emp By Id */
	@Override
	public EmpBasicDetails getEmpById(String empId) throws Exception {
		Optional<EmpBasicDetails> optional = empBasicRepository.findById(empId);
		EmpBasicDetails empBasicDetails = null;
		try {
			if (optional.isPresent()) {
				empBasicDetails = optional.get();
			}
		} catch (Exception e) {
			LOG.error("Error while Emp not found for id " + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("Role not found for id :: " + empId);
		}
		return empBasicDetails;
	}

	/* Save & Update Employee */
	@Override
	public EmpBasicDetails saveBasicDetails(EmpBasicDetails empBasicDetails) throws Exception {
		String loginId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			loginId = authentication.getName();
			if (empBasicDetails.getEmpId() == null) {
				Optional<SequenceMst> sequenceMstOptional = sequenceMstRepository
						.getSequenceKey(empBasicDetails.getTenantId());
				if (sequenceMstOptional.isPresent()) {
					SequenceMst sequenceMst = sequenceMstOptional.get();
					if (Objects.equals(sequenceMst.getTenantId(), empBasicDetails.getTenantId())) {
						Optional<SequenceMst> optional = sequenceMstRepository.findById(sequenceMst.getId());
						if (optional.isPresent()) {
							SequenceMst sequenceMst2 = optional.get();

							String newEmpId = sequenceMst.getSequenceKey() + sequenceMst.getSequenceNo();
							empBasicDetails.setEmpId(newEmpId);
							sequenceMst2.setCreatedDate(new Date());

							int seqNo = Integer.parseInt(sequenceMst.getSequenceNo());
							int newSeqNo = seqNo + 1;
							String newSequenceNo = String.valueOf(newSeqNo);
							sequenceMst2.setSequenceNo(newSequenceNo);

							int lastSeqNo = Integer.parseInt(sequenceMst.getLastSequenceNo());
							int newLastSeqNo = lastSeqNo + 1;
							String newLastSequenceNo = String.valueOf(newLastSeqNo);
							sequenceMst2.setLastSequenceNo(newLastSequenceNo);

							Optional<EmpBasicDetails> findEmpId = empBasicRepository
									.findById(empBasicDetails.getEmpId());
							if (findEmpId.isPresent()) {
								EmpBasicDetails empIdAlready = findEmpId.get();

								if (Objects.equals(empBasicDetails.getEmpId(), empIdAlready.getEmpId())) {
									LOG.info("Duplicate Employee ID");
									EmpBasicDetails id = empBasicRepository
											.getEmployeeId(empBasicDetails.getTenantId());
									String lastEmpId = id.getEmpId();
								      String separator ="EP";
								      int sepPos = lastEmpId.indexOf(separator);
								      if (sepPos == -1) {
								      }
								      String stringEmpIdNo = lastEmpId.substring(sepPos +       separator.length());
								      int empIdNo = Integer.parseInt(stringEmpIdNo);

									for (int i = empIdNo; i <= empIdNo; i++) {
										int idPlus = i + 1;

										String duplicateEmpId = sequenceMst.getSequenceKey() + idPlus;

										empBasicDetails.setEmpId(duplicateEmpId);
										empBasicDetails.setIsActive("1");
										empBasicDetails.setCreatedDate(new Date());
										empBasicDetails.setCreatedBy(loginId);
										empBasicDetails.setCoEmailId(empBasicDetails.getCoEmailId().toLowerCase());

										empBasicDetails.getEmpAddressDetails().setEmpBasicDetails(empBasicDetails);
										empBasicDetails.getEmpAddressDetails().setCreatedDate(new Date());
										empBasicDetails.getEmpAddressDetails().setCreatedBy(loginId);

										empBasicDetails.getEmpWorkDetails().setEmpBasicDetails(empBasicDetails);
										empBasicDetails.getEmpWorkDetails().setCreatedDate(new Date());
										empBasicDetails.getEmpWorkDetails().setCreatedBy(loginId);

										empBasicDetails.getEmpSkillDetails().setEmpBasicDetails(empBasicDetails);
										empBasicDetails.getEmpSkillDetails().setCreatedDate(new Date());
										empBasicDetails.getEmpSkillDetails().setCreatedBy(loginId);

										empBasicDetails.getEmpCertificationDetails()
												.setEmpBasicDetails(empBasicDetails);
										empBasicDetails.getEmpCertificationDetails().setCreatedDate(new Date());
										empBasicDetails.getEmpCertificationDetails().setCreatedBy(loginId);

										empBasicDetails.getEmpSalaryDetails().setEmpBasicDetails(empBasicDetails);
										empBasicDetails.getEmpSalaryDetails().setCreatedDate(new Date());
										empBasicDetails.getEmpSalaryDetails().setCreatedBy(loginId);

										empBasicDetails.getEmpPassportDetails().setEmpBasicDetails(empBasicDetails);
										empBasicDetails.getEmpPassportDetails().setCreatedDate(new Date());
										empBasicDetails.getEmpPassportDetails().setCreatedBy(loginId);

										empBasicDetails.getEmpPersonalDetails().setEmpBasicDetails(empBasicDetails);
										empBasicDetails.getEmpPersonalDetails().setCreatedDate(new Date());
										empBasicDetails.getEmpPersonalDetails().setCreatedBy(loginId);

										empBasicDetails.getEmpEmergContactDetails().setEmpBasicDetails(empBasicDetails);
										empBasicDetails.getEmpEmergContactDetails().setCreatedDate(new Date());
										empBasicDetails.getEmpEmergContactDetails().setCreatedBy(loginId);

										this.empBasicRepository.save(empBasicDetails);

										String newSequenceNo2 = String.valueOf(idPlus);
										int newLastSeqNo2 = idPlus - 1;
										String newLastSequenceNo2 = String.valueOf(newLastSeqNo2);

										sequenceMst2.setLastSequenceNo(newLastSequenceNo2);
										sequenceMst2.setSequenceNo(newSequenceNo2);
										sequenceMstRepository.save(sequenceMst2);
									}
								} else {
									LOG.info("ERROR :::::::::");
								}
							} else {
								LOG.info("Unique Employee Id");
								empBasicDetails.setEmpId(newEmpId);
								empBasicDetails.setIsActive("1");
								empBasicDetails.setCreatedDate(new Date());
								empBasicDetails.setCreatedBy(loginId);
								empBasicDetails.setCoEmailId(empBasicDetails.getCoEmailId().toLowerCase());

								empBasicDetails.getEmpAddressDetails().setEmpBasicDetails(empBasicDetails);
								empBasicDetails.getEmpAddressDetails().setCreatedDate(new Date());
								empBasicDetails.getEmpAddressDetails().setCreatedBy(loginId);

								empBasicDetails.getEmpWorkDetails().setEmpBasicDetails(empBasicDetails);
								empBasicDetails.getEmpWorkDetails().setCreatedDate(new Date());
								empBasicDetails.getEmpWorkDetails().setCreatedBy(loginId);

								empBasicDetails.getEmpSkillDetails().setEmpBasicDetails(empBasicDetails);
								empBasicDetails.getEmpSkillDetails().setCreatedDate(new Date());
								empBasicDetails.getEmpSkillDetails().setCreatedBy(loginId);

								empBasicDetails.getEmpCertificationDetails().setEmpBasicDetails(empBasicDetails);
								empBasicDetails.getEmpCertificationDetails().setCreatedDate(new Date());
								empBasicDetails.getEmpCertificationDetails().setCreatedBy(loginId);

								empBasicDetails.getEmpSalaryDetails().setEmpBasicDetails(empBasicDetails);
								empBasicDetails.getEmpSalaryDetails().setCreatedDate(new Date());
								empBasicDetails.getEmpSalaryDetails().setCreatedBy(loginId);

								empBasicDetails.getEmpPassportDetails().setEmpBasicDetails(empBasicDetails);
								empBasicDetails.getEmpPassportDetails().setCreatedDate(new Date());
								empBasicDetails.getEmpPassportDetails().setCreatedBy(loginId);

								empBasicDetails.getEmpPersonalDetails().setEmpBasicDetails(empBasicDetails);
								empBasicDetails.getEmpPersonalDetails().setCreatedDate(new Date());
								empBasicDetails.getEmpPersonalDetails().setCreatedBy(loginId);

								empBasicDetails.getEmpEmergContactDetails().setEmpBasicDetails(empBasicDetails);
								empBasicDetails.getEmpEmergContactDetails().setCreatedDate(new Date());
								empBasicDetails.getEmpEmergContactDetails().setCreatedBy(loginId);

								this.empBasicRepository.save(empBasicDetails);
								sequenceMstRepository.save(sequenceMst2);
							}
						}
					}
				}
			} else {
				Optional<EmpBasicDetails> empBasicDetails2 = empBasicRepository.findById(empBasicDetails.getEmpId());
				if (empBasicDetails2.isPresent()) {
					EmpBasicDetails newEmpBasicDetails = empBasicDetails2.get();
					newEmpBasicDetails.setCoEmailId(empBasicDetails.getCoEmailId().toLowerCase());
					newEmpBasicDetails.setFirstName(empBasicDetails.getFirstName());
					newEmpBasicDetails.setMiddleName(empBasicDetails.getMiddleName());
					newEmpBasicDetails.setLastName(empBasicDetails.getLastName());
					newEmpBasicDetails.setFullName(empBasicDetails.getFullName());
					newEmpBasicDetails.setGrade(empBasicDetails.getGrade());
					newEmpBasicDetails.setGender(empBasicDetails.getGender());
					newEmpBasicDetails.setDepartName(empBasicDetails.getDepartName());
					newEmpBasicDetails.setIsActive("1");
					newEmpBasicDetails.setTenantId(empBasicDetails.getTenantId());
					newEmpBasicDetails.setLastModifiedDate(new Date());
					newEmpBasicDetails.setLastModifiedBy(loginId);

					newEmpBasicDetails.getEmpPersonalDetails()
							.setBirthDate(empBasicDetails.getEmpPersonalDetails().getBirthDate());
					newEmpBasicDetails.getEmpPersonalDetails().setAge(empBasicDetails.getEmpPersonalDetails().getAge());
					newEmpBasicDetails.getEmpPersonalDetails()
							.setPlaceOfBirth(empBasicDetails.getEmpPersonalDetails().getPlaceOfBirth());
					newEmpBasicDetails.getEmpPersonalDetails()
							.setBloodGroup(empBasicDetails.getEmpPersonalDetails().getBloodGroup());
					newEmpBasicDetails.getEmpPersonalDetails()
							.setContactNo(empBasicDetails.getEmpPersonalDetails().getContactNo());
					newEmpBasicDetails.getEmpPersonalDetails()
							.setAlternateContactNo(empBasicDetails.getEmpPersonalDetails().getAlternateContactNo());
					newEmpBasicDetails.getEmpPersonalDetails()
							.setMaritalStatus(empBasicDetails.getEmpPersonalDetails().getMaritalStatus());
					newEmpBasicDetails.getEmpPersonalDetails()
							.setReligion(empBasicDetails.getEmpPersonalDetails().getReligion());
					newEmpBasicDetails.getEmpPersonalDetails()
							.setAddharNumber(empBasicDetails.getEmpPersonalDetails().getAddharNumber());
					newEmpBasicDetails.getEmpPersonalDetails()
							.setPanNumber(empBasicDetails.getEmpPersonalDetails().getPanNumber());
					newEmpBasicDetails.getEmpPersonalDetails()
							.setPersonalEmailId1(empBasicDetails.getEmpPersonalDetails().getPersonalEmailId1().toLowerCase());
					newEmpBasicDetails.getEmpPersonalDetails()
							.setPersonalEmailId2(empBasicDetails.getEmpPersonalDetails().getPersonalEmailId2().toLowerCase());
					newEmpBasicDetails.getEmpPersonalDetails().setLastModifiedDate(new Date());
					newEmpBasicDetails.getEmpPersonalDetails().setLastModifiedBy(loginId);

					newEmpBasicDetails.getEmpWorkDetails()
							.setDateOfJoining(empBasicDetails.getEmpWorkDetails().getDateOfJoining());
					newEmpBasicDetails.getEmpWorkDetails()
							.setEmployeeType(empBasicDetails.getEmpWorkDetails().getEmployeeType());
					newEmpBasicDetails.getEmpWorkDetails()
							.setBusinessUnit(empBasicDetails.getEmpWorkDetails().getBusinessUnit());
					newEmpBasicDetails.getEmpWorkDetails()
							.setProjectId(empBasicDetails.getEmpWorkDetails().getProjectId());
					newEmpBasicDetails.getEmpWorkDetails()
							.setKraRole(empBasicDetails.getEmpWorkDetails().getProjectName());
					newEmpBasicDetails.getEmpWorkDetails()
							.setProjectName(empBasicDetails.getEmpWorkDetails().getProjectName());
					newEmpBasicDetails.getEmpWorkDetails()
							.setDesignation(empBasicDetails.getEmpWorkDetails().getDesignation());
					newEmpBasicDetails.getEmpWorkDetails()
							.setReportingManager(empBasicDetails.getEmpWorkDetails().getReportingManager());
					newEmpBasicDetails.getEmpWorkDetails()
							.setBaseLocation(empBasicDetails.getEmpWorkDetails().getBaseLocation());
					newEmpBasicDetails.getEmpWorkDetails()
							.setCurrentLocation(empBasicDetails.getEmpWorkDetails().getCurrentLocation());
					newEmpBasicDetails.getEmpWorkDetails().setLastModifiedDate(new Date());
					newEmpBasicDetails.getEmpWorkDetails().setLastModifiedBy(loginId);

					newEmpBasicDetails.getEmpAddressDetails()
							.setPresentAddress(empBasicDetails.getEmpAddressDetails().getPresentAddress());
					newEmpBasicDetails.getEmpAddressDetails()
							.setPermanentAddress(empBasicDetails.getEmpAddressDetails().getPermanentAddress());
					newEmpBasicDetails.getEmpAddressDetails()
							.setPresentCity(empBasicDetails.getEmpAddressDetails().getPresentCity());
					newEmpBasicDetails.getEmpAddressDetails()
							.setPermanentCity(empBasicDetails.getEmpAddressDetails().getPermanentCity());
					newEmpBasicDetails.getEmpAddressDetails()
							.setPresentState(empBasicDetails.getEmpAddressDetails().getPresentState());
					newEmpBasicDetails.getEmpAddressDetails()
							.setPermanentState(empBasicDetails.getEmpAddressDetails().getPermanentState());
					newEmpBasicDetails.getEmpAddressDetails()
							.setPresentPinCode(empBasicDetails.getEmpAddressDetails().getPresentPinCode());
					newEmpBasicDetails.getEmpAddressDetails()
							.setPermanentPinCode(empBasicDetails.getEmpAddressDetails().getPermanentPinCode());
					newEmpBasicDetails.getEmpAddressDetails()
							.setPresentCountry(empBasicDetails.getEmpAddressDetails().getPresentCountry());
					newEmpBasicDetails.getEmpAddressDetails()
							.setPermanentCountry(empBasicDetails.getEmpAddressDetails().getPermanentCountry());
					newEmpBasicDetails.getEmpAddressDetails().setLastModifiedDate(new Date());
					newEmpBasicDetails.getEmpAddressDetails().setLastModifiedBy(loginId);

					newEmpBasicDetails.getEmpSalaryDetails()
							.setSalaryAccBank(empBasicDetails.getEmpSalaryDetails().getSalaryAccBank());
					newEmpBasicDetails.getEmpSalaryDetails()
							.setSalaryAccNo(empBasicDetails.getEmpSalaryDetails().getSalaryAccNo());
					newEmpBasicDetails.getEmpSalaryDetails()
							.setAccIFSCode(empBasicDetails.getEmpSalaryDetails().getAccIFSCode());
					newEmpBasicDetails.getEmpSalaryDetails().setLastModifiedDate(new Date());
					newEmpBasicDetails.getEmpSalaryDetails().setLastModifiedBy(loginId);

					newEmpBasicDetails.getEmpCertificationDetails()
							.setCertificationName(empBasicDetails.getEmpCertificationDetails().getCertificationName());
					newEmpBasicDetails.getEmpCertificationDetails()
							.setCertVersion(empBasicDetails.getEmpCertificationDetails().getCertVersion());
					newEmpBasicDetails.getEmpCertificationDetails()
							.setCertCompleteDate(empBasicDetails.getEmpCertificationDetails().getCertCompleteDate());
					newEmpBasicDetails.getEmpCertificationDetails()
							.setCertValidTill(empBasicDetails.getEmpCertificationDetails().getCertValidTill());
					newEmpBasicDetails.getEmpCertificationDetails()
							.setCertCode(empBasicDetails.getEmpCertificationDetails().getCertScore());
					newEmpBasicDetails.getEmpCertificationDetails()
							.setCertCode(empBasicDetails.getEmpCertificationDetails().getCertCode());
					newEmpBasicDetails.getEmpCertificationDetails().setLastModifiedDate(new Date());
					newEmpBasicDetails.getEmpCertificationDetails().setLastModifiedBy(loginId);

					newEmpBasicDetails.getEmpSkillDetails().setSkill(empBasicDetails.getEmpSkillDetails().getSkill());
					newEmpBasicDetails.getEmpSkillDetails()
							.setSkillVersion(empBasicDetails.getEmpSkillDetails().getSkillVersion());
					newEmpBasicDetails.getEmpSkillDetails()
							.setProficiencyLevel(empBasicDetails.getEmpSkillDetails().getProficiencyLevel());
					newEmpBasicDetails.getEmpSkillDetails()
							.setExperienceSkill(empBasicDetails.getEmpSkillDetails().getExperienceSkill());
					newEmpBasicDetails.getEmpSkillDetails()
							.setSelfRating(empBasicDetails.getEmpSkillDetails().getSelfRating());
					newEmpBasicDetails.getEmpSkillDetails().setLastModifiedDate(new Date());
					newEmpBasicDetails.getEmpSkillDetails().setLastModifiedBy(loginId);

					newEmpBasicDetails.getEmpPassportDetails()
							.setPassportName(empBasicDetails.getEmpPassportDetails().getPassportName());
					newEmpBasicDetails.getEmpPassportDetails()
							.setPassportNo(empBasicDetails.getEmpPassportDetails().getPassportNo());
					newEmpBasicDetails.getEmpPassportDetails()
							.setPassportAddress(empBasicDetails.getEmpPassportDetails().getPassportAddress());
					newEmpBasicDetails.getEmpPassportDetails()
							.setPassportCountry(empBasicDetails.getEmpPassportDetails().getPassportCountry());
					newEmpBasicDetails.getEmpPassportDetails()
							.setPassportDateOfIssue(empBasicDetails.getEmpPassportDetails().getPassportDateOfIssue());
					newEmpBasicDetails.getEmpPassportDetails()
							.setPassportDateOfExpire(empBasicDetails.getEmpPassportDetails().getPassportDateOfExpire());
					newEmpBasicDetails.getEmpPassportDetails().setLastModifiedDate(new Date());
					newEmpBasicDetails.getEmpPassportDetails().setLastModifiedBy(loginId);

					newEmpBasicDetails.getEmpEmergContactDetails()
							.setEmergContactName1(empBasicDetails.getEmpEmergContactDetails().getEmergContactName1());
					newEmpBasicDetails.getEmpEmergContactDetails()
							.setEmergContactNo1(empBasicDetails.getEmpEmergContactDetails().getEmergContactNo1());
					newEmpBasicDetails.getEmpEmergContactDetails()
							.setEmergContactName2(empBasicDetails.getEmpEmergContactDetails().getEmergContactName2());
					newEmpBasicDetails.getEmpEmergContactDetails()
							.setEmergContactNo2(empBasicDetails.getEmpEmergContactDetails().getEmergContactNo2());
					newEmpBasicDetails.getEmpEmergContactDetails().setLastModifiedDate(new Date());
					newEmpBasicDetails.getEmpEmergContactDetails().setLastModifiedBy(loginId);

					empBasicRepository.save(newEmpBasicDetails);
				} else {
					return empBasicDetails;
				}
			}
		} catch (Exception e) {
			LOG.error("------Error occur while save record------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("Employee record not save ......");
		}
		return empBasicDetails;
	}

	@Override
	public Optional<EmpBasicDetails> findEmpByEmail(String coEmailId) throws Exception {
		return empBasicRepository.findEmpByEmail(coEmailId);
	}

	@Override
	public boolean userExists(String coEmailId) throws Exception {
		return findEmpByEmail(coEmailId).isPresent();
	}

	public Optional<EmpBasicDetails> getImageById(String empId) {
		return empBasicRepository.findById(empId);
	}

	/* For Deactivate Employee */
	@Override
	public EmpBasicDetails deactiveEmp(EmpBasicDetails empBasicDetails) throws Exception {
		try {
			Optional<EmpBasicDetails> empBasicDetails2 = empBasicRepository.findById(empBasicDetails.getEmpId());
			if (empBasicDetails2.isPresent()) {
				EmpBasicDetails newEmpBasicDetails = empBasicDetails2.get();
				newEmpBasicDetails.setIsActive(empBasicDetails.getIsActive());
				newEmpBasicDetails.setIsActive("0");

				empBasicRepository.save(newEmpBasicDetails);
			} else {
				return empBasicDetails;
			}
		} catch (Exception e) {
			LOG.error("------Error occur while deactive employee------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No employee deactivate......");
		}
		return empBasicDetails;
	}

	/* For Activate Employee */
	@Override
	public EmpBasicDetails activateEmp(EmpBasicDetails empBasicDetails) throws Exception {
		try {
			Optional<EmpBasicDetails> empBasicDetails2 = empBasicRepository.findById(empBasicDetails.getEmpId());
			if (empBasicDetails2.isPresent()) {
				EmpBasicDetails newEmpBasicDetails = empBasicDetails2.get();
				newEmpBasicDetails.setIsActive(empBasicDetails.getIsActive());
				newEmpBasicDetails.setIsActive("1");

				empBasicRepository.save(newEmpBasicDetails);
			} else {
				return empBasicDetails;
			}
		} catch (Exception e) {
			LOG.error("------Error occur while active employee------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No employee activate......");
		}
		return empBasicDetails;
	}

	/* Get Current Active Employee Data by empId */
	@Override
	public EmpBasicDetails getCurrentUser(String empId) throws Exception {
		try {
			return this.empBasicRepository.getCurrentUser(empId);
		} catch (Exception e) {
			LOG.error("------Error occur while disply employee data------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("Employee not found......");
		}
	}

	/* Get Employee Image by empId */
	@Override
	public EmpBasicDetails getImage(String empId) throws Exception {
		try {
			return this.empBasicRepository.getCurrentUser(empId);
		} catch (Exception e) {
			LOG.error("------Error occur while disply employee image------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("Employee not found......");
		}
	}

	@Override
	public List<EmpWorkDetails> getEmpWithManger(String userId) throws Exception {
		List<EmpWorkDetails> empWorkDetails = empWorkRepository.getEmpWithManger(userId);
		try {
			if (Objects.nonNull(empWorkDetails)) {
				return empWorkDetails;
			}
		} catch (Exception e) {
			LOG.error("------Error occur while disply records to manager------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No data found......");
		}
		return new ArrayList<>();
	}

	@Override
	public List<String> getEmpIdWithIsActive() throws Exception {
		List<String> listOfEmp = empBasicRepository.getEmpIdWithIsActive();
		try {
			if (Objects.nonNull(listOfEmp)) {
				return listOfEmp;
			}
		} catch (Exception e) {
			LOG.error("------Error occur while get list of employee ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No data found......");
		}
		return new ArrayList<>();
	}

	@Override
	public long getAllEmployees() throws Exception {
		try {
			return empBasicRepository.getAllEmployees();
		} catch (Exception e) {
			LOG.error("Error occuring while get all employee in current month " + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(" No record exist... " );
		}
	}
	
	@Override
	public List<String> getUniqueTenantId() throws Exception {
		List<String> listOfUniqueTenantId = empBasicRepository.getUniqueTenantId();
		try {
			if (Objects.nonNull(listOfUniqueTenantId)) {
				return listOfUniqueTenantId;
			} else {
				System.out.println("list Of Unique Tenant Id is null");
			} 
		} catch (Exception e) {
			LOG.error("Error occuring while ge Unique Tenant Id  " + ExceptionUtils.getStackTrace(e));
		}
		return new ArrayList<>();
	}

	@Override
	public List<EmpBasicDetails> listOfCurrentUser(String empId) throws Exception {
		List<EmpBasicDetails> empBasicDetails = empBasicRepository.listOfCurrentUser(empId);
		try {
			if (!empBasicDetails.isEmpty()) {
				return empBasicDetails;
			}
		} catch (Exception e) {
			LOG.error("------Error occur while display all data from list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist......");
		}
		return empBasicDetails;
	}

	@Override
	public void uploadImage(byte[] imageData, String empId) throws Exception {
		try {
			empBasicRepository.uploadImage(imageData, empId);
		} catch (Exception e) {
			LOG.error("------Error occur while upload image ------" + ExceptionUtils.getStackTrace(e));
		}
	}

	@Override
	public List<String> getUniqueDepId() throws Exception {
			List<String> empsList = empBasicRepository.getUniqueDepId();
			try {
				if (Objects.nonNull(empsList)) {
					return empsList;
				}
		} catch (Exception e) {
			LOG.error("------Error occur while get unique depId ------" + ExceptionUtils.getStackTrace(e));
		}
		return new ArrayList<>();
	}

	@Override
	public List<String> getAllEmpId(String empId) throws Exception {
		List<String> empsList = empWorkRepository.getAllEmpId(empId);
		try {
			if (Objects.nonNull(empsList)) {
				return empsList;
			}
		} catch (Exception e) {
			LOG.error("------Error occur while get All EmpId ------" + ExceptionUtils.getStackTrace(e));
		}
		return new ArrayList<>();
	}

	@Override
	public List<EmpWorkDetails> getAllEmpJoiningDate(List<String> empIds) throws Exception {
		List<EmpWorkDetails> empsList = empWorkRepository.getAllEmpJoiningDate(empIds);
		try {
			if (Objects.nonNull(empsList)) {
				return empsList;
			}
		} catch (Exception e) {
			LOG.error("------Error occur while get All Emp JoiningDate ------" + ExceptionUtils.getStackTrace(e));
		}
		return new ArrayList<>();
	}

	@Override
	public EmpBasicDetails getKRAWithDepIdAndEmpId(String depId, String reportingManager) throws Exception {
		EmpBasicDetails empBasicDetails = empBasicRepository.getKRAWithDepIdAndEmpId(depId, reportingManager);
		try {
			if (Objects.nonNull(empBasicDetails)) {
				return empBasicDetails;
			}
		} catch (Exception e) {
			LOG.error("------Error occur while get KRA With DepId And EmpId ------" + ExceptionUtils.getStackTrace(e));
		}
		return new EmpBasicDetails();
	}

	@Override
	public Optional<EmpBasicDetails> findById(String empId) throws Exception {
		Optional<EmpBasicDetails> optional = empBasicRepository.findById(empId);
		try {
			if (optional.isPresent()) {
				return optional;
			}
		} catch (Exception e) {
			LOG.error("------Error occur while get find by id ------" + ExceptionUtils.getStackTrace(e));
		}
		return Optional.empty();
	}
}