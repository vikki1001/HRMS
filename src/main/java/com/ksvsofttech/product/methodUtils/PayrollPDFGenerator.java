package com.ksvsofttech.product.methodUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.ksvsofttech.product.entities.EmpBasicDetails;
import com.ksvsofttech.product.entities.PayrollMst;
import com.ksvsofttech.product.entities.TenantMst;

public class PayrollPDFGenerator {
	private static final Logger LOGGER = LogManager.getLogger(PayrollPDFGenerator.class);

	DateFormat dateFormat = new SimpleDateFormat("MMMM-yy");

	private List<PayrollMst> payrollList;
	private EmpBasicDetails basicDetailsList;
	private TenantMst tenantMstList;

	public PayrollPDFGenerator(List<PayrollMst> payrollList, EmpBasicDetails basicDetailsList,
			TenantMst tenantMstList) {
		this.payrollList = payrollList;
		this.basicDetailsList = basicDetailsList;
		this.tenantMstList = tenantMstList;

	}

	/* Download Payroll Slip */
	public void export(HttpServletResponse response) throws Exception {
		String imageFile = "D:/companyLogo/" + tenantMstList.getTenantLogoPath();

		PdfWriter pdfWriter = new PdfWriter(response.getOutputStream());
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		pdfDocument.setDefaultPageSize(PageSize.A4);
		Document document = new Document(pdfDocument);
		try {
			float threecol = 190f;
			float twocol = 285f;
			float twocol150 = twocol - 150f;
			float[] twocolumnWidth = { twocol150, twocol };
			float[] fullWidth = { threecol * 3 };
			Paragraph onesp = new Paragraph("\n");

			float[] twocolumnWidth2 = { twocol, twocol, twocol, twocol };
			float[] twocolumnWidth3 = { twocol, twocol };

			float thirdcols150 = twocol + 150f;
			float[] twocolumnWidth4 = { threecol, thirdcols150 };

			Table table = new Table(twocolumnWidth);
			ImageData data = ImageDataFactory.create(imageFile);

			table.addCell(
					new Cell().add(new Image(data).setWidth(150f).setHeight(40f).setRelativePosition(180f, 15f, 0f, 0f))
							.setBorder(Border.NO_BORDER));
			table.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
			table.addCell(getHeaderTextCell(tenantMstList.getCompanyAddress()).setFontSize(9f).setRelativePosition(150f,
					20f, 0f, 0f));

			Border gb = new SolidBorder(Color.GRAY, 2f);
			Table divider = new Table(fullWidth);
			divider.setBorder(gb);
			document.add(table);
			document.add(onesp);
			document.add(divider);
			document.add(onesp);

			Table twoColtable6 = new Table(fullWidth);
			if (payrollList.isEmpty() || payrollList == null) {
				twoColtable6.addCell(new Cell().add("Employee Salary Slip for the Month of --")
						.setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
				document.add(twoColtable6);
				document.add(onesp);
			} else {
				for (PayrollMst payrollMst : payrollList) {
					String strDate = dateFormat.format(payrollMst.getCreatedDate());
					twoColtable6.addCell(new Cell().add("Employee Salary Slip for the Month of " + strDate)
							.setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
				}
				document.add(twoColtable6);
				document.add(onesp);
			}

			if (Objects.nonNull(basicDetailsList)) {
				Table twoColtable = new Table(twocolumnWidth2);
				twoColtable.addCell(getCell10Left("Employee ID", true));
				twoColtable.addCell(getCell10Left(basicDetailsList.getEmpId(), false));
				twoColtable.addCell(getCell10Left("Name", true));
				twoColtable.addCell(getCell10Left(basicDetailsList.getFullName(), false));
				twoColtable.addCell(getCell10Left("Department", true));
				twoColtable.addCell(getCell10Left(basicDetailsList.getDepartName(), false));
				twoColtable.addCell(getCell10Left("Designation", true));
				twoColtable.addCell(getCell10Left(basicDetailsList.getEmpWorkDetails().getDesignation(), false));
				twoColtable.addCell(getCell10Left("Pay Date", true));
				twoColtable.addCell(getCell10Left("-", false));
				twoColtable.addCell(getCell10Left("Days Working", true));
				twoColtable.addCell(getCell10Left("-", false));
				twoColtable.addCell(getCell10Left("PF No.", true));
				twoColtable.addCell(getCell10Left("-", false));
				twoColtable.addCell(getCell10Left("Bank A/c No.", true));
				twoColtable.addCell(getCell10Left(basicDetailsList.getEmpSalaryDetails().getSalaryAccNo(), false));
				twoColtable.addCell(getCell10Left("Gender", true));
				twoColtable.addCell(getCell10Left(basicDetailsList.getGender(), false));
				twoColtable.addCell(getCell10Left("PAN No.", true));
				twoColtable.addCell(getCell10Left(basicDetailsList.getEmpPersonalDetails().getPanNumber(), false));
				twoColtable.addCell(getCell10Left("DOB", true));
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				String strDate = dateFormat.format(basicDetailsList.getEmpPersonalDetails().getBirthDate());
				twoColtable.addCell(getCell10Left(strDate, false));
				document.add(twoColtable);
				document.add(onesp);
			} else {
				Table twoColtable = new Table(twocolumnWidth2);
				twoColtable.addCell(getCell10Left("Employee ID", true));
				twoColtable.addCell(getCell10Left("-", false));
				twoColtable.addCell(getCell10Left("Name", true));
				twoColtable.addCell(getCell10Left("-", false));
				twoColtable.addCell(getCell10Left("Department", true));
				twoColtable.addCell(getCell10Left("-", false));
				twoColtable.addCell(getCell10Left("Designation", true));
				twoColtable.addCell(getCell10Left("-", false));
				twoColtable.addCell(getCell10Left("Pay Date", true));
				twoColtable.addCell(getCell10Left("-", false));
				twoColtable.addCell(getCell10Left("Days Working", true));
				twoColtable.addCell(getCell10Left("-", false));
				twoColtable.addCell(getCell10Left("PF No.", true));
				twoColtable.addCell(getCell10Left("-", false));
				twoColtable.addCell(getCell10Left("Bank A/c No.", true));
				twoColtable.addCell(getCell10Left("-", false));
				twoColtable.addCell(getCell10Left("Gender", true));
				twoColtable.addCell(getCell10Left("-", false));
				twoColtable.addCell(getCell10Left("PAN No.", true));
				twoColtable.addCell(getCell10Left("-", false));
				twoColtable.addCell(getCell10Left("DOB", true));
				twoColtable.addCell(getCell10Left("-", false));
				document.add(twoColtable);
				document.add(onesp);
			}

			Table twoColtable3 = new Table(twocolumnWidth2);
			if (payrollList.isEmpty() || payrollList == null) {
				twoColtable3.addCell(new Cell().add("Earning").setBold());
				twoColtable3.addCell(new Cell().add("Amount").setBold());
				twoColtable3.addCell(new Cell().add("Deduction").setBold());
				twoColtable3.addCell(new Cell().add("Amount").setBold());
				twoColtable3.addCell(new Cell().add("Basic Salary"));
				twoColtable3.addCell(new Cell().add(String.valueOf("-")));
				twoColtable3.addCell(new Cell().add("PF"));
				twoColtable3.addCell(new Cell().add(String.valueOf("-")));
				twoColtable3.addCell(new Cell().add("HRA"));
				twoColtable3.addCell(new Cell().add("-"));
				twoColtable3.addCell(new Cell().add("Income Tax"));
				twoColtable3.addCell(new Cell().add(String.valueOf("-")));
				twoColtable3.addCell(new Cell().add("Darness Allowance"));
				twoColtable3.addCell(new Cell().add(String.valueOf("-")));
				twoColtable3.addCell(new Cell().add("Leave Deduction"));
				twoColtable3.addCell(new Cell().add(String.valueOf("-")));
				twoColtable3.addCell(new Cell().add("Conveyance"));
				twoColtable3.addCell(new Cell().add(String.valueOf("-")));
				twoColtable3.addCell(new Cell().add(""));
				twoColtable3.addCell(new Cell().add(""));
				twoColtable3.addCell(new Cell().add("Medical Benefit"));
				twoColtable3.addCell(new Cell().add(String.valueOf("-")));
				twoColtable3.addCell(new Cell().add(""));
				twoColtable3.addCell(new Cell().add(""));
				twoColtable3.addCell(new Cell().add("Overtime Rate"));
				twoColtable3.addCell(new Cell().add(String.valueOf("-")));
				twoColtable3.addCell(new Cell().add(""));
				twoColtable3.addCell(new Cell().add(""));
				twoColtable3.addCell(new Cell().add("Variable Pay"));
				twoColtable3.addCell(new Cell().add(String.valueOf("-")));
				twoColtable3.addCell(new Cell().add(""));
				twoColtable3.addCell(new Cell().add(""));
				twoColtable3.addCell(new Cell().add("Extra Allowance"));
				twoColtable3.addCell(new Cell().add("-"));
				twoColtable3.addCell(new Cell().add(""));
				twoColtable3.addCell(new Cell().add(""));
				twoColtable3.addCell(new Cell().add("Total Earning").setBold());
				twoColtable3.addCell(new Cell().add(String.valueOf("-")));
				twoColtable3.addCell(new Cell().add("Total Deduction").setBold());
				twoColtable3.addCell(new Cell().add(String.valueOf("-")));
				document.add(twoColtable3);
				document.add(onesp);
			} else {
				for (PayrollMst payrollMst : payrollList) {
					twoColtable3.addCell(new Cell().add("Earning").setBold());
					twoColtable3.addCell(new Cell().add("Amount").setBold());
					twoColtable3.addCell(new Cell().add("Deduction").setBold());
					twoColtable3.addCell(new Cell().add("Amount").setBold());
					twoColtable3.addCell(new Cell().add("Basic Salary"));
					twoColtable3.addCell(new Cell().add(String.valueOf(payrollMst.getBasicSalary())));
					twoColtable3.addCell(new Cell().add("PF"));
					twoColtable3.addCell(new Cell().add(String.valueOf(payrollMst.getEmpProvidentFundOrg())));
					twoColtable3.addCell(new Cell().add("HRA"));
					twoColtable3.addCell(new Cell().add("-"));
					twoColtable3.addCell(new Cell().add("Income Tax"));
					twoColtable3.addCell(new Cell().add(String.valueOf(payrollMst.getIncomeTax())));
					twoColtable3.addCell(new Cell().add("Darness Allowance"));
					twoColtable3.addCell(new Cell().add(String.valueOf(payrollMst.getDearnessAllowances())));
					twoColtable3.addCell(new Cell().add("Leave Deduction"));
					twoColtable3.addCell(new Cell().add(String.valueOf(payrollMst.getLeavesRate())));
					twoColtable3.addCell(new Cell().add("Conveyance"));
					twoColtable3.addCell(new Cell().add(String.valueOf(payrollMst.getConveyance())));
					twoColtable3.addCell(new Cell().add(""));
					twoColtable3.addCell(new Cell().add(""));
					twoColtable3.addCell(new Cell().add("Medical Benefit"));
					twoColtable3.addCell(
							new Cell().add(String.valueOf(String.format("%.2f", payrollMst.getMedicalBenefit()))));
					twoColtable3.addCell(new Cell().add(""));
					twoColtable3.addCell(new Cell().add(""));
					twoColtable3.addCell(new Cell().add("Overtime Rate"));
					twoColtable3.addCell(new Cell().add(String.valueOf(payrollMst.getOverTimeRate())));
					twoColtable3.addCell(new Cell().add(""));
					twoColtable3.addCell(new Cell().add(""));
					twoColtable3.addCell(new Cell().add("Variable Pay"));
					twoColtable3.addCell(new Cell().add(String.valueOf(payrollMst.getVariablePay())));
					twoColtable3.addCell(new Cell().add(""));
					twoColtable3.addCell(new Cell().add(""));
					twoColtable3.addCell(new Cell().add("Extra Allowance"));
					twoColtable3.addCell(new Cell().add("-"));
					twoColtable3.addCell(new Cell().add(""));
					twoColtable3.addCell(new Cell().add(""));
					twoColtable3.addCell(new Cell().add("Total Earning").setBold());
					twoColtable3.addCell(new Cell().add(String.valueOf(payrollMst.getGrossSalary())));
					twoColtable3.addCell(new Cell().add("Total Deduction").setBold());
					twoColtable3.addCell(new Cell().add(String.valueOf(payrollMst.getTotalDeduction())));
				}
				document.add(twoColtable3);
				document.add(onesp);
			}

			Table twoColtable4 = new Table(twocolumnWidth4);
			if (payrollList.isEmpty() || payrollList == null) {
				twoColtable4.addCell(new Cell().add("NET PAY : ").setBold());
				twoColtable4.addCell(new Cell().add("-"));
				twoColtable4.addCell(new Cell().add("Amount in Words : ").setBold());
				twoColtable4.addCell(new Cell().add("-"));
				document.add(twoColtable4);
				document.add(onesp);
				document.add(onesp);
				document.add(onesp);
			} else {
				for (PayrollMst payrollMst : payrollList) {
					twoColtable4.addCell(new Cell().add("NET PAY : 	").setBold());
					twoColtable4.addCell(new Cell().add(String.valueOf(payrollMst.getNetSalary())));
					twoColtable4.addCell(new Cell().add("Amount in Words : ").setBold());
					twoColtable4
							.addCell(new Cell().add(String.valueOf(convertNumber(payrollMst.getNetSalary())) + "Only"));
				}
				document.add(twoColtable4);
				document.add(onesp);
				document.add(onesp);
				document.add(onesp);
			}

			Table twoColtable5 = new Table(twocolumnWidth3);
			twoColtable5.addCell(new Cell().add("Employer Signature").setBold().setBorder(Border.NO_BORDER));
			twoColtable5.addCell(new Cell().add("Employee Signature").setBold().setBorder(Border.NO_BORDER)
					.setTextAlignment(TextAlignment.RIGHT));
			document.add(twoColtable5);

		} catch (Exception e) {
			LOGGER.error("Error occuring while download payroll Slip PDF ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			document.close();
		}
	}

	static Cell getHeaderTextCell(String textValue) {

		return new Cell().add(textValue).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER);
	}

	static Cell getHeaderTextCellValue(String textValue) {

		return new Cell().add(textValue).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER);
	}

	static Cell getCell10Left(String textValue, Boolean isBold) {
		Cell myCell = new Cell().add(textValue).setBorder(Border.NO_BORDER).setFontSize(10f)
				.setTextAlignment(TextAlignment.LEFT);
		return isBold ? myCell.setBold() : myCell;
	}

	private static final String[] units = { "", " One", " Two", " Three", " Four", " Five", " Six", " Seven", " Eight",
			" Nine" };
	private static final String[] twoDigits = { " Ten", " Eleven", " Twelve", " Thirteen", " Fourteen", " Fifteen",
			" Sixteen", " Seventeen", " Eighteen", " Nineteen" };
	private static final String[] tenMultiples = { "", "", " Twenty", " Thirty", " Forty", " Fifty", " Sixty",
			" Seventy", " Eighty", " Ninety" };
	private static final String[] placeValues = { " ", " Thousand", " Million", " Billion", " Trillion" };

	private static String convertNumber(double number) {
		String word = "";
		int index = 0;
		do {
			// take 3 digits in each iteration
			int num = (int) (number % 1000);
			if (num != 0) {
				String str = ConversionForUptoThreeDigits(num);
				word = str + placeValues[index] + word;
			}
			index++;
			// next 3 digits
			number = number / 1000;
		} while (number > 0);
		return word;
	}

	private static String ConversionForUptoThreeDigits(int number) {
		String word = "";
		int num = number % 100;
		if (num < 10) {
			word = word + units[num];
		} else if (num < 20) {
			word = word + twoDigits[num % 10];
		} else {
			word = tenMultiples[num / 10] + units[num % 10];
		}

		word = (number / 100 > 0) ? units[number / 100] + " Hundred" + word : word;
		return word;
	}
}