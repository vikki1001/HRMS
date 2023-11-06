package com.ksvsofttech.product.dao;

import java.util.List;
import java.util.Optional;

import com.ksvsofttech.product.entities.BranchMst;

public interface BranchDao {

	public List<BranchMst> getIsActiveBranchs() throws Exception;

	public List<BranchMst> getInActiveBranchs() throws Exception;

	public BranchMst save(BranchMst branch) throws Exception;

	public BranchMst getBranchById(long branchId) throws Exception;

	public BranchMst deactiveBranch(BranchMst branchMst) throws Exception;

	public BranchMst activateBranch(BranchMst branchMst) throws Exception;
	
	public Optional<BranchMst> findBranchByBranchCode(String branchCode) throws Exception;

	/* For Duplicate Branch Code Validation */
	public boolean branchExists(String branchCode) throws Exception;
}
