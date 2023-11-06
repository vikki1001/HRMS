package com.ksvsofttech.product.service;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ksvsofttech.product.entities.HolidayMaster;

public interface HolidayService {

	public List<HolidayMaster> getHolidayUpcoming() throws Exception;

	public List<HolidayMaster> getHolidayHistory(Date holidayDate) throws Exception;

	public void saveAll(MultipartFile files) throws Exception;
	
	public ByteArrayInputStream exportHoliday(List<HolidayMaster> holidayMasters);

	public long getHolidayDays() throws Exception;

	public List<HolidayMaster> findAll() throws Exception;

	public float getHolidayDaysForLeave(Date from, Date to) throws Exception;
}
