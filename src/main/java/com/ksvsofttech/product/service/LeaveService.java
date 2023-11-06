package com.ksvsofttech.product.service;

import java.util.List;
import java.util.Optional;

import com.ksvsofttech.product.entities.LeaveMst;

public interface LeaveService {

	public LeaveMst saveOrUpdateLeave(LeaveMst leaveMst) throws Exception;

	public List<LeaveMst> getLeaveList() throws Exception;

	public List<LeaveMst> getActiveLeave(String empId) throws Exception;

	public List<LeaveMst> getInactiveLeave(String empId) throws Exception;

	public LeaveMst cancelLeave(LeaveMst leaveMst) throws Exception;

	public List<LeaveMst> getLeaveApply(String empId)  throws Exception;

	public List<LeaveMst> getRejectLeave(String empId) throws Exception;

	public void acceptStatus(String status, String flag, Long id) throws Exception;

	public List<LeaveMst> getAcceptLeaveById(Long id) throws Exception;

	public List<LeaveMst> getLeaveByLeaveType(String leaveType) throws Exception;

	public List<LeaveMst> getEmpWithManger(String empId) throws Exception;

	public List<LeaveMst> getLeavePending(String userId) throws Exception;

	public List<LeaveMst> onLeaveToday() throws Exception;

	public Optional<LeaveMst> findLeaveById(Long id) throws Exception;
	
	public List<LeaveMst> getTotalLeave() throws Exception;
	
	public List<LeaveMst> getTotalPendingLeave() throws Exception;

	public long getLeaveDays(String userId) throws Exception;

	public long getAllLeaves() throws Exception;

	public long getAllPendingLeaves() throws Exception;

	public LeaveMst notificationRead(LeaveMst leaveMst) throws Exception;
}
