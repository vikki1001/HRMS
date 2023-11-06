package com.ksvsofttech.product.appraisal;

import java.util.List;

public interface KRAService {

	public List<KRA> getActiveKra() throws Exception;
		
	public KRA saveOrUpdate(KRA kra) throws Exception;
	
	public KRA getById(Long id) throws Exception;

	public List<KRA> getKRAWithDepIdAndEmpId(String depId) throws Exception;

	public void deleteById(Long id) throws Exception;

}
