package com.ksvsofttech.product.controller;

import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ksvsofttech.product.entities.AuditRecord;
import com.ksvsofttech.product.entities.UploadDocument;
import com.ksvsofttech.product.service.AuditRecordService;
import com.ksvsofttech.product.service.UploadDocService;

@Controller
public class UploadDocController {
	private static final Logger LOGGER = LogManager.getLogger(UploadDocController.class);

	@Autowired
	private UploadDocService uploadDocService;

	@Autowired
	private AuditRecordService auditRecordService;

	/* Upload Document List in Service */
	@GetMapping("/documentList")
	public String documentList(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			List<UploadDocument> uploadDoc = uploadDocService.getIsActive();
			model.addAttribute("documentList", uploadDoc);
		} catch (Exception e) {
			LOGGER.error("Error occur to display upload active document list... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed upload document list"));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("All Service");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "document/empDocList";
	}

	/* Active Upload Document List */
	@GetMapping("/activeDocList")
	public String activeDocumentList(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			List<UploadDocument> uploadDoc = uploadDocService.getIsActive();
			model.addAttribute("docList", uploadDoc);
		} catch (Exception e) {
			LOGGER.error("Error occur to display upload active document list... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed active upload document list"));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("Upload Document");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "document/activeDocList";
	}

	/* Deactive Upload Document List */
	@GetMapping("/inActiveDocList")
	public String inactiveDocumentList(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			List<UploadDocument> uploadDoc = uploadDocService.getDeactive();
			model.addAttribute("docList", uploadDoc);
		} catch (Exception e) {
			LOGGER.error("Error occur to display upload deactive document list... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed deactive upload document list"));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("Upload Document");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "document/inActiveDocList";
	}

	/* Upload Registration Page */
	@GetMapping("/getUploadDoc")
	public String uploadDocPage(UploadDocument uploadDoc, Model model) {
		try {
			model.addAttribute("uploadDoc", uploadDoc);
		} catch (Exception e) {
			LOGGER.error("Error occur to display upload document registration page... " + ExceptionUtils.getStackTrace(e));
		}
		return "document/uploadDocument";
	}

	/* New Upload Doc Save */
	@PostMapping("/uploadDoc")
	public String uploadDocument(@ModelAttribute("uploadDoc") UploadDocument uploadDoc, BindingResult result,  Device device,
			@RequestParam("file") MultipartFile file, RedirectAttributes redirAttrs) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			if (uploadDoc != null) {
				String fileName = StringUtils.cleanPath(uploadDoc.getTemplateName());
				uploadDoc.setTemplateName(fileName);
				uploadDoc.setOriginalName(file.getOriginalFilename());
				uploadDoc.setFile(file.getBytes());
				uploadDoc.setFileType(file.getContentType());
				uploadDoc.setSize(file.getSize());
				String originalFileName = uploadDoc.getOriginalName();
				uploadDoc.setExtension(originalFileName.substring(originalFileName.lastIndexOf(".") + 1));				
				uploadDocService.saveUploadDoc(uploadDoc);
				redirAttrs.addFlashAttribute("success", "Registration Completed Successfully");
				return "redirect:/activeDocList";
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while upload document successfully... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - upload document succesfully"));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("Upload Document");
			auditRecord.setActivityCode("CREATE");
			auditRecordService.save(auditRecord, device);
		}
		return "document/uploadDocument";
	}

	/* Edit Upload Documents(Registration) */
	@GetMapping(value = "/uploadDocUpdate/{id}")
	public ModelAndView uploadDocUpdate(@PathVariable(name = "id") Long id, Device device) throws Exception {
		ModelAndView mav = new ModelAndView("document/updateDocument");
		AuditRecord auditRecord = new AuditRecord();
		try {
			UploadDocument uploadDocument = uploadDocService.findDocById(id);
			mav.addObject("uploadDoc", uploadDocument);
		} catch (Exception e) {
			LOGGER.error("Error occur while display edit registration page ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed by id - " + id));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("Upload Management");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return mav;
	}

	/* Edit Upload Doc Save */
	@PostMapping(value = "/updateDocument")
	public String updateUpdateDocument(@ModelAttribute("uploadDoc") UploadDocument uploadDocument, BindingResult result,
			Model model, Device device, RedirectAttributes redirAttrs, @RequestParam("file") MultipartFile file)
			throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			if(uploadDocument != null) {
				/* Save Upload Doc to Database */
				String fileName = StringUtils.cleanPath(uploadDocument.getTemplateName());
				uploadDocument.setTemplateName(fileName);
				uploadDocument.setOriginalName(file.getOriginalFilename());
				uploadDocument.setFile(file.getBytes());
				uploadDocument.setFileType(file.getContentType());
				uploadDocument.setSize(file.getSize());
				String originalFileName = uploadDocument.getOriginalName();
				uploadDocument.setExtension(originalFileName.substring(originalFileName.lastIndexOf(".") + 1));
				uploadDocService.saveUploadDoc(uploadDocument);
				redirAttrs.addFlashAttribute("success", "Update Registration Successfully");
				return "redirect:/activeDocList";
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while update Upload registration successfully... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - update upload document successfully"));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("Update Document");
			auditRecord.setActivityCode("UPDATE");
			auditRecordService.save(auditRecord, device);
		}
		return "document/updateDocument";
	}

	/* Documents Download */
	@GetMapping("/downloadfile")
	public void downloadFile(@Param("id") Long id, Model model, HttpServletResponse response) throws Exception {
		UploadDocument uploadDocument = uploadDocService.findDocById(id);
		if (uploadDocument != null) {
			String fileExtension = uploadDocument.getOriginalName();
			response.setContentType("application/octet-stream");
			String headerKey = "Content-Disposition";
			String headerValue = "attachment; filename = " + uploadDocument.getTemplateName() + "."
					+ fileExtension.substring(fileExtension.lastIndexOf(".") + 1);
			response.setHeader(headerKey, headerValue);
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(uploadDocument.getFile());
			outputStream.close();
		}
	}

	/* Documents Display */
	@GetMapping("/document")
	public void showDocument(@RequestParam("id") Long id, HttpServletResponse response) throws Exception {
		try {
			UploadDocument uploadDocument = uploadDocService.findDocById(id);
			if (uploadDocument != null) {
				response.setContentType(
						"document/jpeg, document/jpg, document/png, document/gif, document/pdf, document/txt, document/docx, document/xlsx");
				response.getOutputStream().write(uploadDocument.getFile());
				response.getOutputStream().flush();
				response.getOutputStream().close();
			} else {
				System.out.println("Document not get....");
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while display documents  " + ExceptionUtils.getStackTrace(e));
		}
	}

	/* Activate upload document */
	@GetMapping(value = "/activeDoc/{id}")
	public String activeUploadDoc(UploadDocument uploadDocument, @PathVariable(name = "id") Long id, Device device)
			throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			uploadDocService.activeDoc(uploadDocument);
		} catch (Exception e) {
			LOGGER.error("------Error occur while activate upload document-----" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("No document activate" + id);
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - activate document by id - " + id));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("Upload Document");
			auditRecord.setActivityCode("ACTIVATE");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/inActiveDocList";
	}

	/* Deactivate upload document */
	@GetMapping(value = "/deactiveDoc/{id}")
	public String deactiveUploadDoc(UploadDocument uploadDocument, @PathVariable(name = "id") Long id, Device device)
			throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			uploadDocService.deactiveDoc(uploadDocument);
		} catch (Exception e) {
			LOGGER.error("------Error occur while inactivate upload document-----" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("No document inactivate" + id);
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - deactivate document by id - " + id));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("Upload Document");
			auditRecord.setActivityCode("DEACTIVATE");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/activeDocList";
	}
}