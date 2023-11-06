package com.ksvsofttech.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AllServicesController {

	@GetMapping(value = "/allServices")
	public String allServices() {
		
		return "allServices";
	}

}
