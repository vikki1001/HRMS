package com.ksvsofttech.product.service.Impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ksvsofttech.product.dao.HolidayDao;
import com.ksvsofttech.product.entities.HolidayMaster;
import com.ksvsofttech.product.service.HolidayService;

@Service
public class HolidayServiceImpl implements HolidayService {
	private static final Logger LOGGER = LogManager.getLogger(HolidayServiceImpl.class);

	@Autowired
	private HolidayDao holidayDao;

	/* For Save/Upload Holiday Excel File */
	public void saveAll(MultipartFile files) throws Exception {
		String empId;
		List<HolidayMaster> holidayMasterList = new ArrayList<>();
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			try (XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream())) {
				// Read data form excel file sheet.
				XSSFSheet worksheet = workbook.getSheetAt(0);
			
				for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
					if (index > 0) {
						XSSFRow row = worksheet.getRow(index);
						HolidayMaster holidayMaster = new HolidayMaster();
						holidayMaster.setHolidayCode(row.getCell(0).getStringCellValue());
						holidayMaster.setDescription(row.getCell(1).getStringCellValue());
						holidayMaster.setHolidayDate(row.getCell(2).getDateCellValue());
						holidayMaster.setCreatedDate(new Date());
						holidayMaster.setCreatedBy(empId);

						holidayMasterList.add(holidayMaster);
					}
				}
			}
			holidayDao.saveAll(holidayMasterList);
		} catch (Exception e) {
			LOGGER.error("Error while save holiday master" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("No save Holiday master");
		}
	}
	
	/* Download Holiday Excel Formate */
	@Override
	public ByteArrayInputStream exportHoliday(List<HolidayMaster> holidayMaster) {
		try(Workbook workbook = new XSSFWorkbook()){
			Sheet sheet = workbook.createSheet("Holiday");
			
			//Header Bold
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			
			Row row = sheet.createRow(0);
			
			// Date formatting
			CellStyle dataColumnDateFormatStyle = workbook.createCellStyle();
			CreationHelper createHelper = workbook.getCreationHelper();
			dataColumnDateFormatStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-mm-dd"));
			dataColumnDateFormatStyle.setFont(headerFont);
			
			// Define header cell style
	        CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFont(headerFont);

	        // Creating header cells 
	        Cell cell = row.createCell(0);
	        cell.setCellValue("HolidayCode");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(1);
	        cell.setCellValue("Description");
	        cell.setCellStyle(headerCellStyle);
	
	        cell = row.createCell(2);
	        cell.setCellValue("HolidayDate");
	        cell.setCellStyle(dataColumnDateFormatStyle);	        
		        
	        // Creating data rows for each contact
	        for(int i = 0; i < holidayMaster.size(); i++) {
	        	Row dataRow = sheet.createRow(i + 1);
	        	dataRow.createCell(0).setCellValue(holidayMaster.get(i).getHolidayCode());
	        	dataRow.createCell(1).setCellValue(holidayMaster.get(i).getDescription());
	        	dataRow.createCell(2).setCellValue(holidayMaster.get(i).getHolidayDate());
	        }
	
	        // Making size of column auto resize to fit with data
	        sheet.autoSizeColumn(0);
	        sheet.autoSizeColumn(1);
	        sheet.autoSizeColumn(2);
	        
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        workbook.write(outputStream);
	        return new ByteArrayInputStream(outputStream.toByteArray());
		} catch (Exception e) {
			LOGGER.error("Error while download holiday excel format" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("Error Occur");
		}
	}

	/* List of Upcoming Holidays */
	@Override
	public List<HolidayMaster> getHolidayUpcoming() throws Exception {
		List<HolidayMaster> holidayMaster;
		try {
			holidayMaster = holidayDao.getHolidayUpcoming();
		} catch (Exception e) {
			LOGGER.error("------Error occur while display upcoming holiday list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No holiday record exist......");
		}
		return holidayMaster;
	}

	/* List of History Holidays */
	@Override
	public List<HolidayMaster> getHolidayHistory(Date createdDate) throws Exception {
		List<HolidayMaster> holidayMaster;
		try {
			holidayMaster = holidayDao.getHolidayHistory(createdDate);
		} catch (Exception e) {
			LOGGER.error("------Error occur while display history holiday list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No holiday record exist......");
		}
		return holidayMaster;
	}

	/* Count No. of Holiday in Current Month */
	@Override
	public long getHolidayDays() throws Exception {
		try {
			return holidayDao.getHolidayDays();
		} catch (Exception e) {
			LOGGER.error(" Error while get total holiday in current month " + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(" No record exist ");
		}
	}

	/* Get all Records into DB */
	@Override
	public List<HolidayMaster> findAll() throws Exception {
		List<HolidayMaster> holidayMaster;
		try {
			holidayMaster = holidayDao.findAll();
		} catch (Exception e) {
			LOGGER.error("------Error occur while display all holiday ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No holiday record exist......");
		}
		return holidayMaster;
	}

	@Override
	public float getHolidayDaysForLeave(Date from, Date to) throws Exception {
		try {
			return holidayDao.getHolidayDaysForLeave(from, to);
		} catch (Exception e) {
			LOGGER.error("------Error occur while get no. of holidays ------" + ExceptionUtils.getStackTrace(e));
		}
		return 0;
	}
}