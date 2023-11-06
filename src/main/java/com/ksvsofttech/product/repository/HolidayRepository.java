package com.ksvsofttech.product.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.entities.HolidayMaster;

@Repository
public interface HolidayRepository extends JpaRepository<HolidayMaster, Long>{

	@Query(value = "SELECT * FROM HolidayMaster WHERE holidayDate > CURDATE() ORDER BY holidayDate ASC",nativeQuery = true)
	public List<HolidayMaster> getHolidayUpcoming();
	
	@Query(value = "SELECT * FROM HolidayMaster WHERE holidayDate < :holidayDate",nativeQuery = true)
	public List<HolidayMaster> getHolidayHistory(@Param("holidayDate") Date holidayDate);

	@Query(value = "SELECT count(*) FROM HolidayMaster WHERE MONTH(holidayDate) = MONTH(CURRENT_DATE())", nativeQuery = true)
	public long getHolidayDays();
	
	@Query(value = "SELECT COUNT(*) FROM HolidayMaster WHERE holidayDate between DATE_FORMAT(:from, '%Y-%m-%d') AND DATE_FORMAT(:to, '%Y-%m-%d')", nativeQuery = true)
	public float getHolidayDaysForLeave(Date from, Date to);
}