		package com.ksvsofttech.product.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Objects;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mobile.device.Device;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.ksvsofttech.product.entities.AuditRecord;
import com.ksvsofttech.product.entities.CheckInCheckOut;
import com.ksvsofttech.product.entities.EmpBasicDetails;
import com.ksvsofttech.product.methodUtils.MethodUtils;
import com.ksvsofttech.product.service.AuditRecordService;
import com.ksvsofttech.product.service.CheckInCheckOutService;
import com.ksvsofttech.product.service.DepartmentService;
import com.ksvsofttech.product.service.EmpRegistartionService;
import com.ksvsofttech.product.service.RestrictMailService;
import com.ksvsofttech.product.service.SequenceMstService;

@Controller
public class EmpRegistartionController {
	private static final Logger LOG = LogManager.getLogger(EmpRegistartionController.class);

	@Value("${qrCode.location}")
	private String qrCodeLocation;

	@Value("${sender.email.address}")
	private String sendFrom;
	@Value("${receiver.email.address}")
	private String sendTo;
	@Value("${receiver.email.subject}")
	private String subject;

	@Autowired
	private TemplateEngine templateEngine;

	public EmpRegistartionController(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}

	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private CheckInCheckOutService checkInCheckOutService;
	@Autowired
	private RestrictMailService restrictMailService ;
	
	@Autowired
	private EmpRegistartionService empRegistartionService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private AuditRecordService auditRecordService;
	@Autowired
	private SequenceMstService sequenceMstService;

	@Scheduled(cron = "${scheduling.job.cron}")
	public void tiggerMail() throws Exception {
		List<String> userId = checkInCheckOutService.getNineHourNotComplete();
		List<String> restrictEmployees = restrictMailService.getRestrictEmployees();
		try {
			if (Objects.nonNull(userId) && Objects.nonNull(restrictEmployees)) {
				userId.removeAll(restrictEmployees);
				List<CheckInCheckOut> checkInCheckOutList = checkInCheckOutService.getByUserIdAndCurentDate(userId);
				if (Objects.nonNull(checkInCheckOutList)) {
					Context context = new Context();
					context.setVariable("userList", checkInCheckOutList);
					String body = templateEngine.process("employee/emailTrigger", context);

					MimeMessage message = javaMailSender.createMimeMessage();
					MimeMessageHelper helper = new MimeMessageHelper(message, true);
					helper.setSubject(subject);
					helper.setText(body, true);
					helper.setFrom(sendFrom, "Mail Contant");
					helper.setTo(sendTo);

					javaMailSender.send(message);

					System.out.println("Email send Successfully :::::::::::::::::::::: ");
				} else {
					System.out.println("Error Occur to get CurrentDate & List of empId Data :::::::::::: ");
				}
			} else {
				System.out.println("UserId & RestrictEmployees not found ::::::::::: ");
			}
		} catch (Exception e) {
			LOG.error("When sending mail, an error occurs..... " + ExceptionUtils.getStackTrace(e));
		}
	}
	
	/* Display All Employee in Organization */
	@GetMapping(value = "/allEmployee")
	public String allEmployee(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			model.addAttribute("allEmployee", empRegistartionService.getIsActiveEmpRegList());
		} catch (Exception e) {
			LOG.error("Error occur while display all employee details  " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed all employee on dashboard"));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("All Service/Organization");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "employee/allEmployee";
	}

	/* Display Employee Profile in Organization */
	@GetMapping(value = "/profile/{empId}")
	public String getEmpProfileDashboard(@PathVariable(name = "empId") String empId, Model model, Device device)
			throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			model.addAttribute("empProfile", empRegistartionService.getCurrentUser(empId));
		} catch (Exception e) {
			LOG.error("Error occur while display all employee details  " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed all employee"));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("All Service/Organization");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "employee/empprofile2";
	}

	/* Display Image */
	@GetMapping(value = "/image")
	public void showImage(@RequestParam("empId") String empId, HttpServletResponse response) throws Exception {
		try {
			EmpBasicDetails empBasicDetails = empRegistartionService.getImage(empId);
			if (empBasicDetails != null) {
				response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
				response.getOutputStream().write(empBasicDetails.getFile());
				response.getOutputStream().flush();
				response.getOutputStream().close();
			} else {
				System.out.println("Image not get....");
			}
		} catch (Exception e) {
			LOG.error("Error occur while display image  " + ExceptionUtils.getStackTrace(e));
		}
	}

	/* QR Code Download */
	@GetMapping("/downloadQRCode")
	public void downloadFile(@Param("empId") String empId, Model model, HttpServletResponse response, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			EmpBasicDetails basicDetails = empRegistartionService.getEmpById(empId);
			File file = new File(qrCodeLocation + basicDetails.getEmpId() + ".png");

			response.setContentType("application/octet-stream");
			String headerKey = "Content-Disposition";
			String headerValue = "attachment; filename = " + basicDetails.getEmpId() + ".png";
			response.setHeader(headerKey, headerValue);
			ServletOutputStream outputStream = response.getOutputStream();
			BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));

			byte[] buffer = new byte[102400];
			int byteReader = -1;

			while ((byteReader = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, byteReader);
			}
			inputStream.close();
			outputStream.close();
		} catch (Exception e) {
			LOG.error("------Error occur while download QR Code ------" + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - download QR code by employee - " + empId));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("Employee Management");
			auditRecord.setActivityCode("Download");
			auditRecordService.save(auditRecord, device);
		}
	}

	/* Display List of IsActive Employee Registration list */
	@GetMapping(value = "/activeEmpRegList")
	public String getActiveEmpRegList(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			model.addAttribute("empRegList", empRegistartionService.getIsActiveEmpRegList());
		} catch (Exception e) {
			LOG.error("Error occur while display active employee registration list " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed active employee list"));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("Employee Management");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "employee/activeEmpRegList";
	}

	/* Display List of InActive Employee Registration list */
	@GetMapping(value = "/inActiveEmpRegList")
	public String getInActiveEmpRegList(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			model.addAttribute("empRegList", empRegistartionService.getInActiveEmpRegList());
		} catch (Exception e) {
			LOG.error("Error occur while display inActive employee registration list " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed inActive employee list"));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("Employee Management");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "employee/inActiveEmpRegList";
	}

	@GetMapping(value = "/home")
	public String homePage(@ModelAttribute EmpBasicDetails empBasicDetails, Model model, Device device)
			throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			model.addAttribute("departmentList", departmentService.getIsActive());
			model.addAttribute("tenantId", sequenceMstService.getTenantId());
		} catch (Exception e) {
			LOG.error("Error occur to open employee registration page... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed employee management form"));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("Employee Management");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "employee/empRegistration";
	}

	// @Override
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
		// Convert multipart object to byte[]
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}

	@PostMapping(value = "/saveEmployee")
	public String saveEmployee(@ModelAttribute("empBasicDetails") EmpBasicDetails empBasicDetails, BindingResult result,
			Model model, RedirectAttributes redirAttrs, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empDetails = null;
		try {
			model.addAttribute("departmentList", departmentService.getIsActive());
			model.addAttribute("tenantId", sequenceMstService.getTenantId());
				
				/* QR Code Generate */
				final String QR_CODE_IMAGE_PATH = qrCodeLocation + empBasicDetails.getEmpId() + ".png";

				empDetails = "Name -- " + empBasicDetails.getFullName() + '\n' + "Blood Group -- "
						+ empBasicDetails.getEmpPersonalDetails().getBloodGroup() + '\n' + "Present Address -- "
						+ empBasicDetails.getEmpAddressDetails().getPresentAddress() + ","
						+ empBasicDetails.getEmpAddressDetails().getPresentCity() + ","
						+ empBasicDetails.getEmpAddressDetails().getPresentState() + ","
						+ empBasicDetails.getEmpAddressDetails().getPresentPinCode() + '\n' + "Permanent Address -- "
						+ empBasicDetails.getEmpAddressDetails().getPermanentAddress() + ","
						+ empBasicDetails.getEmpAddressDetails().getPermanentCity() + ","
						+ empBasicDetails.getEmpAddressDetails().getPermanentState() + ","
						+ empBasicDetails.getEmpAddressDetails().getPermanentPinCode() + '\n'
						+ "Emergency Contact No -- " + empBasicDetails.getEmpEmergContactDetails().getEmergContactNo1();

				// Generate and Save Qr Code Image in static/image folder
				MethodUtils.generateQRCodeImage(empDetails, 250, 250, QR_CODE_IMAGE_PATH);

				empRegistartionService.saveBasicDetails(empBasicDetails);
				redirAttrs.addFlashAttribute("success", "Registration Completed Successfully");
				return "redirect:/activeEmpRegList";
		} catch (Exception e) {
			LOG.error("Error occur while save employee records successfully " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - create employee successfully"));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("Employee Management");
			auditRecord.setActivityCode("CREATE");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/activeEmpRegList";
	}

	/* Update of Registered Employee */
	@GetMapping(value = "/empUpdate/{empId}")
	public ModelAndView empUpdate(@PathVariable(name = "empId") String empId, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		ModelAndView mav = new ModelAndView("employee/updateEmpReg");
		try {
			mav.addObject("departmentList", departmentService.getIsActive());
			EmpBasicDetails empBasicDetails = empRegistartionService.getEmpById(empId);
			mav.addObject("tenantId", sequenceMstService.getTenantId());
			mav.addObject("empBasicDetails", empBasicDetails);
		} catch (Exception e) {
			LOG.error("Error occur while edit registered employee... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed employee id - " + empId));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("Employee Management");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return mav;
	}

	@PostMapping(value = "/updateEmployee")
	public String updateEmployee(@ModelAttribute("empBasicDetails") EmpBasicDetails empBasicDetails, BindingResult result, 
			Model model, RedirectAttributes redirAttrs, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empDetails = null;
		try {
			model.addAttribute("departmentList", departmentService.getIsActive());
			model.addAttribute("tenantId", sequenceMstService.getTenantId());		

			final String QR_CODE_IMAGE_PATH = qrCodeLocation + empBasicDetails.getEmpId() + ".png";

				empDetails = "Name -- " + empBasicDetails.getFullName() + '\n' + "Blood Group -- "
						+ empBasicDetails.getEmpPersonalDetails().getBloodGroup() + '\n' + "Present Address -- "
						+ empBasicDetails.getEmpAddressDetails().getPresentAddress() + ","
						+ empBasicDetails.getEmpAddressDetails().getPresentCity() + ","
						+ empBasicDetails.getEmpAddressDetails().getPresentState() + ","
						+ empBasicDetails.getEmpAddressDetails().getPresentPinCode() + '\n' + "Permanent Address -- "
						+ empBasicDetails.getEmpAddressDetails().getPermanentAddress() + ","
						+ empBasicDetails.getEmpAddressDetails().getPermanentCity() + ","
						+ empBasicDetails.getEmpAddressDetails().getPermanentState() + ","
						+ empBasicDetails.getEmpAddressDetails().getPermanentPinCode() + '\n'
						+ "Emergency Contact No -- " + empBasicDetails.getEmpEmergContactDetails().getEmergContactNo1();

				// Generate and Save Qr Code Image in static/image folder
				MethodUtils.generateQRCodeImage(empDetails, 250, 250, QR_CODE_IMAGE_PATH);

				empRegistartionService.saveBasicDetails(empBasicDetails);
				redirAttrs.addFlashAttribute("success", "Update Registration Successfully");
				return "redirect:/activeEmpRegList";
		} catch (Exception e) {
			LOG.error("Error occur while update employee records successfully " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - update registered employee successfully"));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("Employee Management");
			auditRecord.setActivityCode("UPDATE");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/activeEmpRegList";
	}

	/* Deactivate of Registered Employee */
	@GetMapping(value = "/deactiveEmp/{empId}")
	public String deactiveEmp(EmpBasicDetails empBasicDetails, @PathVariable(name = "empId") String empId, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			empRegistartionService.deactiveEmp(empBasicDetails);
		} catch (Exception e) {
			LOG.error("------Error occur while deactivate employee ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No employee deactivate......" + empId);
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - deactivate employee by employee id - " + empId));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("Employee Management");
			auditRecord.setActivityCode("DEACTIVATE");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/activeEmpRegList";
	}

	/* Activate of Registered Employee */
	@GetMapping(value = "/activeEmp/{empId}")
	public String activeEmp(EmpBasicDetails empBasicDetails, @PathVariable(name = "empId") String empId, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			empRegistartionService.activateEmp(empBasicDetails);
		} catch (Exception e) {
			LOG.error("------Error occur while activate employee ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No employee activate......" + empId);
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - activate employee by employee id - " + empId));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("Employee Management");
			auditRecord.setActivityCode("ACTIVATE");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/inActiveEmpRegList";
	}

	/* Display Info into profile */
	@GetMapping(value = "/empProfile")
	public String getEmpProfile(@ModelAttribute EmpBasicDetails empBasicDetails, Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empId = null;
		try {
			/* Current user */
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			EmpBasicDetails userDetails = null;
			if (empId != null) {
				userDetails = empRegistartionService.getCurrentUser(empId);
				if (userDetails != null) {
					model.addAttribute("empProfile", userDetails);
				} else {
					// Do nothing
				}
			} else {
				// Do nothing
			}
		} catch (Exception e) {
			LOG.error("Error occur while display employee profile " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord
					.setRemarks(userDetails.getUsername().concat(" - viewed employee profile"));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("Employee Management");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "employee/empprofile";
	}
	
	@PostMapping(value = "/uploadImage")
	public @ResponseBody void uploadImage(@ModelAttribute EmpBasicDetails empBasicDetails,
			Model model, @RequestParam("file") MultipartFile file, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empId = null;
		try {
			/* Current user */
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
					
			byte[] imageData = file.getBytes();
			if (empBasicDetails != null) {
				
				System.out.println("UPLOAD IMAGE 2 :::::::: " + file.getOriginalFilename());
				empRegistartionService.uploadImage(imageData, empId);
			} else {
				System.out.println("UPLOAD IMAGE FAILED :::::::::::: ");
			}
		} catch (Exception e) {
			LOG.error("Error occur while upload employee image ::::: " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - upload employee image"));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("Employee Management");
			auditRecord.setActivityCode("UPLOAD");
			auditRecordService.save(auditRecord, device);
		}
	}
}
