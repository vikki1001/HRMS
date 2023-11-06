package com.ksvsofttech.product.service;



import java.util.List;
import org.springframework.mobile.device.Device;
import com.ksvsofttech.product.entities.AuditRecord;
import com.ksvsofttech.product.entities.UserMst;

public interface AuditRecordService {

	 public AuditRecord save(AuditRecord auditRecord,Device device) throws Exception;
	
	 public List<UserMst>getAllUser()throws Exception;
	 
	 public List<AuditRecord> findByfromDateBetweenorloginId(String from, String to, String loginId)throws Exception;
	 
	 public List<AuditRecord> findByfromDateBetweenorbranchName(String from, String to, String branchName)throws Exception;

	 public List<AuditRecord> findByfromDateBetweenorroleCode(String from, String to, String roleCode)throws Exception;
	 
		public List<AuditRecord> findByfromDateBetweenorloginIdorbranchName(String from,
				String to, String loginId,String branchName)throws Exception;
	
	 
}
