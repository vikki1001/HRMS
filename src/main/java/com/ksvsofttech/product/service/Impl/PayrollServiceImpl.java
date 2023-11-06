package com.ksvsofttech.product.service.Impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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

import com.ksvsofttech.product.dao.PayrollDao;
import com.ksvsofttech.product.entities.EmpBasicDetails;
import com.ksvsofttech.product.entities.PayrollMst;
import com.ksvsofttech.product.service.EmpRegistartionService;
import com.ksvsofttech.product.service.PayrollService;

@Service
public class PayrollServiceImpl implements PayrollService {
	private static final Logger LOGGER = LogManager.getLogger(PayrollServiceImpl.class);

	@Autowired
	private PayrollDao payrollDao;
	@Autowired
	private EmpRegistartionService empRegistartionService;

	
	/* For Save/Upload Payroll Excel File */
	@Override
	public void saveAll(MultipartFile files) throws Exception {
		String empId;
		List<PayrollMst> payrollMstlist = new ArrayList<>();
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			if (Objects.nonNull(empId)) {
				EmpBasicDetails basicDetails = empRegistartionService.getCurrentUser(empId);
			
			try (XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream())) {
				// Read data form excel file sheet.
				XSSFSheet worksheet = workbook.getSheetAt(0);	
				// looping through each row		
				for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
					if (index > 0) {
						XSSFRow row = worksheet.getRow(index);						
						PayrollMst payrollMst = new PayrollMst();
						payrollMst.getEmpBasicDetails().setEmpId(row.getCell(0).getStringCellValue());
						payrollMst.setNameOfEmp(row.getCell(1).getStringCellValue());
						payrollMst.setDesignation(row.getCell(2).getStringCellValue());
						payrollMst.setWorkingDays(row.getCell(3).getNumericCellValue());
						payrollMst.setBasicSalary(row.getCell(4).getNumericCellValue());
						payrollMst.setDearnessAllowances(row.getCell(5).getNumericCellValue());
						payrollMst.setConveyance(row.getCell(6).getNumericCellValue());
						payrollMst.setHouseRent(row.getCell(7).getNumericCellValue());
						payrollMst.setMedicalBenefit(row.getCell(8).getNumericCellValue());
						payrollMst.setOverTimeHours(row.getCell(9).getNumericCellValue());
						payrollMst.setOverTimeRate(row.getCell(10).getNumericCellValue());
						payrollMst.setVariablePay(row.getCell(11).getNumericCellValue());
						payrollMst.setGrossSalary(row.getCell(12).getNumericCellValue());
						payrollMst.setIncomeTax(row.getCell(13).getNumericCellValue());
						payrollMst.setEmpProvidentFundOrg(row.getCell(14).getNumericCellValue());
						payrollMst.setNoOfLeaves(row.getCell(15).getNumericCellValue());
						payrollMst.setLeavesRate(row.getCell(16).getNumericCellValue());
						payrollMst.setTotalDeduction(row.getCell(17).getNumericCellValue());
						payrollMst.setNetSalary(row.getCell(18).getNumericCellValue());	
						
						payrollMst.setDate(dateFormat.format(new Date()));
						payrollMst.setCreatedDate(new Date());
						payrollMst.setCreatedBy(empId);
						payrollMst.setIsActive("1");
						payrollMst.setFlag("Y");
						payrollMst.setTenantId(basicDetails.getTenantId());

						payrollMstlist.add(payrollMst);
					}
				}
			}
		}
			payrollDao.saveAll(payrollMstlist);
		} catch (Exception e) {
			LOGGER.error("Error Occuring While Save Data ::::::  " + ExceptionUtils.getStackTrace(e));			
		}
	}
	
//	private Object getValue(Cell cell) {
//		switch (cell.getCellType()) {
//		case STRING:
//			return cell.getStringCellValue();
//		case NUMERIC:
//			return String.valueOf((int) cell.getNumericCellValue());
//		case BOOLEAN:
//			return cell.getBooleanCellValue();
//		case ERROR:
//			return cell.getErrorCellValue();
//		case FORMULA:
//			return cell.getCellFormula();
//		case BLANK:
//			return null;
//		case _NONE:
//			return null;
//		default:
//			break;
//		}
//		return null;
//	}

	
	/* Current Month Payroll Dispaly for Employee*/
	@Override
	public List<PayrollMst> getPayrollSlipByMonthAndYear(String month, String year, String empId) throws Exception {
		List<PayrollMst> payrollMstList = payrollDao.getPayrollSlipByMonthAndYear(month, year, empId);
		try {
			if (Objects.nonNull(payrollMstList)) {
				return payrollMstList;
			} else {
				System.out.println("List is empty ::::::");
			}
		} catch (Exception e) {
			LOGGER.error("Error Occuring While Display Payroll List ::::::  " + ExceptionUtils.getStackTrace(e));
		}
		return new ArrayList<>();
	}

	/* Get all Records into DB */
	@Override
	public List<PayrollMst> findAll() throws Exception {
		List<PayrollMst> payrollMsts = payrollDao.findAll();
		try {
			if (Objects.nonNull(payrollMsts)) {
				return payrollMsts;
			} else {

			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while display all holiday ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No holiday record exist......");
		}
		return new ArrayList<>();
	}

	/* Download Payroll Excel Formate */
	@Override
	public ByteArrayInputStream exportPayroll(List<PayrollMst> payrollMst) throws Exception {
		try(Workbook workbook = new XSSFWorkbook()){
			Sheet sheet = workbook.createSheet("Payroll");
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
	        cell.setCellValue("EmpId");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(1);
	        cell.setCellValue("NameOfEmp");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(2);
	        cell.setCellValue("Designation");
	        cell.setCellStyle(headerCellStyle);
	
	        cell = row.createCell(3);
	        cell.setCellValue("WorkingDays");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(4);
	        cell.setCellValue("BasicSalary");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(5);
	        cell.setCellValue("DA");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(6);
	        cell.setCellValue("Conveyance");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(7);
	        cell.setCellValue("HouseRent");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(8);
	        cell.setCellValue("MedicalBenefit");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(9);
	        cell.setCellValue("OverTimeHours");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(10);
	        cell.setCellValue("OverTimeRate");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(11);
	        cell.setCellValue("VariablePay");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(12);
	        cell.setCellValue("GrossSalary");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(13);
	        cell.setCellValue("IncomeTax");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(14);
	        cell.setCellValue("EPF");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(15);
	        cell.setCellValue("NoOfLeaves");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(16);
	        cell.setCellValue("LeavesRate");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(17);
	        cell.setCellValue("TotalDeduction");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(18);
	        cell.setCellValue("NetSalary");
	        cell.setCellStyle(headerCellStyle);
		        
	        // Creating data rows for each contact
	        for(int i = 0; i < payrollMst.size(); i++) {
	        	Row dataRow = sheet.createRow(i + 1);
	        	dataRow.createCell(0).setCellValue(payrollMst.get(i).getEmpBasicDetails().getEmpId());
	        	dataRow.createCell(1).setCellValue(payrollMst.get(i).getNameOfEmp());
	        	dataRow.createCell(2).setCellValue(payrollMst.get(i).getDesignation());
	        	dataRow.createCell(3).setCellValue(payrollMst.get(i).getWorkingDays());
	        	dataRow.createCell(4).setCellValue(payrollMst.get(i).getBasicSalary());
	        	dataRow.createCell(5).setCellValue(payrollMst.get(i).getDearnessAllowances());
	        	dataRow.createCell(6).setCellValue(payrollMst.get(i).getConveyance());
	        	dataRow.createCell(7).setCellValue(payrollMst.get(i).getHouseRent());
	        	dataRow.createCell(8).setCellValue(payrollMst.get(i).getMedicalBenefit());
	        	dataRow.createCell(9).setCellValue(payrollMst.get(i).getOverTimeHours());
	        	dataRow.createCell(10).setCellValue(payrollMst.get(i).getOverTimeRate());
	        	dataRow.createCell(11).setCellValue(payrollMst.get(i).getVariablePay());
	        	dataRow.createCell(12).setCellValue(payrollMst.get(i).getGrossSalary());
	        	dataRow.createCell(13).setCellValue(payrollMst.get(i).getIncomeTax());
	        	dataRow.createCell(14).setCellValue(payrollMst.get(i).getEmpProvidentFundOrg());
	        	dataRow.createCell(15).setCellValue(payrollMst.get(i).getNoOfLeaves());
	        	dataRow.createCell(16).setCellValue(payrollMst.get(i).getLeavesRate());
	        	dataRow.createCell(17).setCellValue(payrollMst.get(i).getTotalDeduction());
	        	dataRow.createCell(18).setCellValue(payrollMst.get(i).getNetSalary());
	        }
	
	        // Making size of column auto resize to fit with data
	        sheet.autoSizeColumn(0);
	        sheet.autoSizeColumn(1);
	        sheet.autoSizeColumn(2);
	        sheet.autoSizeColumn(3);
	        sheet.autoSizeColumn(4);
	        sheet.autoSizeColumn(5);
	        sheet.autoSizeColumn(6);
	        sheet.autoSizeColumn(7);
	        sheet.autoSizeColumn(8);
	        sheet.autoSizeColumn(9);
	        sheet.autoSizeColumn(10);
	        sheet.autoSizeColumn(11);
	        sheet.autoSizeColumn(12);
	        sheet.autoSizeColumn(13);
	        sheet.autoSizeColumn(14);
	        sheet.autoSizeColumn(15);
	        sheet.autoSizeColumn(16);
	        sheet.autoSizeColumn(17);
	        sheet.autoSizeColumn(18);
	        
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        workbook.write(outputStream);
	        return new ByteArrayInputStream(outputStream.toByteArray());
		} catch (Exception e) {
			LOGGER.error("Error while download holiday excel format" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("Error Occur");
		}
	}
}