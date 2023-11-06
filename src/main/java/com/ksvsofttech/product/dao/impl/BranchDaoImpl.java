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

import com.ksvsofttech.product.dao.BranchDao;
import com.ksvsofttech.product.entities.BranchMst;
import com.ksvsofttech.product.repository.BranchRepository;

@Repository
public class BranchDaoImpl implements BranchDao {
	private static final Logger LOGGER = LogManager.getLogger(BranchDaoImpl.class);

	@Autowired
	BranchRepository branchRepository;

	/* List Of IsActive Branch */
	public List<BranchMst> getIsActiveBranchs() throws Exception {
		List<BranchMst> branchList = branchRepository.getIsActive();
		try {
			if (!branchList.isEmpty()) {
				return branchList;
			}
		} catch (Exception e) {
			LOGGER.error(
					"------Error occur while display isactive branch list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No isactive branch record exist......");
		}
		return new ArrayList<>();
	}

	/* List Of InActive Branch */
	public List<BranchMst> getInActiveBranchs() throws Exception {
		List<BranchMst> branchList = branchRepository.getInActive();
		try {
			if (!branchList.isEmpty()) {
				return branchList;
			}
		} catch (Exception e) {
			LOGGER.error(
					"------Error occur while display inactive branch list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No inactive branch record exist......");
		}
		return new ArrayList<>();
	}

	/* Save & Update Branch */
	public BranchMst save(BranchMst branchMst) throws Exception {
		String loginId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			loginId = authentication.getName();
			if (branchMst.getBranchId() == null) {
				branchMst.setBranchCode(branchMst.getBranchCode().toUpperCase());
				branchMst.setAuthStatus("0");
				branchMst.setBranchTypeCode("0");
				branchMst.setIsactive("1");
				branchMst.setTenantId("1");
				branchMst.setVersion(1);
				branchMst.setBranchCodeName(branchMst.getBranchCode().concat(" - ").concat(branchMst.getBranchName()));
				branchMst.setCreatedDate(new Date());
				branchMst.setCreatedBy(loginId);
				this.branchRepository.save(branchMst);
			} else {
				Optional<BranchMst> branchMst2 = branchRepository.findById(branchMst.getBranchId());
				if (branchMst2.isPresent()) {
					BranchMst newBranchMst = branchMst2.get();
					newBranchMst.setBranchRefCode(branchMst.getBranchRefCode());
					newBranchMst.setBranchName(branchMst.getBranchName());
					newBranchMst.setBranchType(branchMst.getBranchType());
					newBranchMst.setPostalCode(branchMst.getPostalCode());
					newBranchMst.setAddress1(branchMst.getAddress1());
					newBranchMst.setAddress2(branchMst.getAddress2());
					newBranchMst.setAddress3(branchMst.getAddress3());
					newBranchMst.setCountry(branchMst.getCountry());
					newBranchMst.setState(branchMst.getState());
					newBranchMst.setCity(branchMst.getCity());
					newBranchMst.setContactPerson(branchMst.getContactPerson());
					newBranchMst.setEmail(branchMst.getEmail());
					newBranchMst.setTelephoneno(branchMst.getTelephoneno());
					newBranchMst.setFax(branchMst.getFax());
					newBranchMst.setTenantId("1");
					newBranchMst.setVersion(1);
					newBranchMst.setBranchCodeName(
							branchMst.getBranchCode().concat(" - ").concat(branchMst.getBranchName()));
					newBranchMst.setLastModifiedDate(new Date());
					newBranchMst.setLastModifiedBy(loginId);
					branchRepository.save(newBranchMst);
				} else {
					return branchMst;
				}
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while save & update branch------" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("Branch not save :: " + branchMst);
		}
		return branchMst;
	}

	/* Find Branch By Id */
	public BranchMst getBranchById(long branchId) throws Exception {
		Optional<BranchMst> optional = branchRepository.findById(branchId);
		BranchMst branchMst = null;
		try {
			if (optional.isPresent()) {
				branchMst = optional.get();
			}
		} catch (Exception e) {
			LOGGER.error("------Error while Branch not found for id------" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("Branch not found for id :: " + branchId);
		}
		return branchMst;
	}

	/* For Deactivate Branch */
	@Override
	public BranchMst deactiveBranch(BranchMst branchMst) throws Exception {
		try {
			Optional<BranchMst> branchMst2 = branchRepository.findById(branchMst.getBranchId());
			if (branchMst2.isPresent()) {
				BranchMst newBranch = branchMst2.get();
				newBranch.setIsActive(branchMst.getIsActive());
				newBranch.setIsActive("0");

				branchRepository.save(newBranch);
			} else {
				return branchMst;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while deactive branch------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No branch deactivate......");
		}
		return branchMst;
	}

	/* For Activate Branch */
	@Override
	public BranchMst activateBranch(BranchMst branchMst) throws Exception {
		try {
			Optional<BranchMst> branchMst2 = branchRepository.findById(branchMst.getBranchId());
			if (branchMst2.isPresent()) {
				BranchMst newBranch = branchMst2.get();
				newBranch.setIsActive(branchMst.getIsActive());
				newBranch.setIsActive("1");

				branchRepository.save(newBranch);
			} else {
				return branchMst;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while active branch------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No branch activate......");
		}
		return branchMst;
	}

	/* For Duplicate Branch Code Validation */
	@Override
	public Optional<BranchMst> findBranchByBranchCode(String branchCode) throws Exception {
		try {
			return branchRepository.findBranchByBranchCode(branchCode);
		} catch (Exception e) {
			LOGGER.error("------Error occur while find duplicate branch ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No branch exists......");
		}
	}

	@Override
	public boolean branchExists(String branchCode) throws Exception {
		try {
			return findBranchByBranchCode(branchCode).isPresent();
		} catch (Exception e) {
			LOGGER.error("------Error occur while find branch code ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No branch code exists......");
		}

	}
}
