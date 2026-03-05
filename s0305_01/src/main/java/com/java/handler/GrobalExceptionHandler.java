package com.java.handler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
//@RestController
public class GrobalExceptionHandler {
	
	//가장 최상위 Exception이 발생했을때
	@ExceptionHandler(Exception.class)		//404 등 세세하게 설정가능
	public String exceptionMethod(Exception e) {
		//return e.getMessage();
		return "error/error";
	}
	
	@ExceptionHandler(IllegalArgumentException.class)		//404 등 세세하게 설정가능
	public String illegalArgument(IllegalArgumentException e, Model model) {
		//return e.getMessage();
		model.addAttribute("msg", e.getMessage());
		return "error/error";
	}

	@ExceptionHandler(NullPointerException.class)
	public String NullPointer(NullPointerException e) {
		return e.getMessage();
		//return "error/error";
	}
}
