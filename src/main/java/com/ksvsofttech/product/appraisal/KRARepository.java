package com.ksvsofttech.product.appraisal;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface KRARepository extends JpaRepository<KRA, Long> {

	@Query(value="SELECT * FROM KRA WHERE isActive='1' ORDER BY createdDate DESC", nativeQuery=true)
	public List<KRA> getActiveKra();

	@Query(value = "SELECT * FROM KRA WHERE department = :depId AND isActive = '1' ORDER BY createdDate DESC", nativeQuery = true)
	public List<KRA> getKRAWithDepIdAndEmpId(String depId);
	
	@Query(value = "SELECT * FROM KRA where kra = :kra", nativeQuery = true)
	public KRA getKRA(String kra);
}
