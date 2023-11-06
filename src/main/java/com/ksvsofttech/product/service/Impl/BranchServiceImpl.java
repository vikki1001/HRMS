package com.ksvsofttech.product.service.Impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksvsofttech.product.dao.BranchDao;
import com.ksvsofttech.product.entities.BranchMst;
import com.ksvsofttech.product.service.BranchService;

@Service
public class BranchServiceImpl implements BranchService {
	private static final Logger LOGGER = LogManager.getLogger(BranchServiceImpl.class);

	@Autowired
	private BranchDao branchDao;

	/* List Of IsActive Branch */
	public List<BranchMst> getIsActiveBranchs() throws Exception {
		List<BranchMst> branchList;
		try {
			branchList = branchDao.getIsActiveBranchs();
		} catch (Exception e) {
			LOGGER.error(
					"------Error occur while display isactive branch list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No isactive branch record exist......");
		}
		return branchList;
	}

	/* List Of InActive Branch */
	public List<BranchMst> getInActiveBranchs() throws Exception {
		List<BranchMst> branchList;
		try {
			branchList = branchDao.getInActiveBranchs();
		} catch (Exception e) {
			LOGGER.error(
					"------Error occur while display inactive branch list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No inactive branch record exist......");
		}
		return branchList;
	}

	/* Save & Update Branch */
	public void save(BranchMst branch) throws Exception {
		try {
			this.branchDao.save(branch);
		} catch (Exception e) {
			LOGGER.error("------Error occur while save & update branch------" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("Branch not save :: " + branch);
		}
	}

	/* Find Branch By Id */
	public BranchMst getBranchById(long branchId) throws Exception {
		BranchMst branchMst = new BranchMst();
		try {
			branchMst = branchDao.getBranchById(branchId);
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
			branchDao.deactiveBranch(branchMst);
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
			branchDao.activateBranch(branchMst);
		} catch (Exception e) {
			LOGGER.error("------Error occur while deactive branch------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No branch activate......");
		}
		return branchMst;
	}

	/* For Duplicate Branch Code Validation */
	@Override
	public Optional<BranchMst> findBranchByBranchCode(String branchCode) throws Exception {
		try {
			return branchDao.findBranchByBranchCode(branchCode);
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
