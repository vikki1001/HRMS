package com.ksvsofttech.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerAdvice {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = Exception.class)
	public ModelAndView exception(Exception exc, WebRequest request) {
		ModelAndView model = new ModelAndView("error");
		model.addObject("error", Throwables.getRootCause());
		exc.printStackTrace();
		return model;
	}
}
