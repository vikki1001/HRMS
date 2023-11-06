package com.ksvsofttech.product.dao;

import java.util.Date;
import java.util.List;

import com.ksvsofttech.product.entities.HolidayMaster;

public interface HolidayDao {
	
	public List<HolidayMaster> getHolidayUpcoming() throws Exception;
	
	public List<HolidayMaster> getHolidayHistory(Date holidayDate) throws Exception;
	
	public void saveAll(List<HolidayMaster> holidayMasters) throws Exception;
		
	public long getHolidayDays() throws Exception;
	
	public List<HolidayMaster> findAll() throws Exception;
	
	public float getHolidayDaysForLeave(Date from, Date to) throws Exception;
}
