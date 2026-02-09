package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.java.dto.TvDto;

@Controller
public class FrontController {
	@GetMapping("/index")
	public String index() {
		TvDto t = new TvDto();
		t.setName("test");
		t.getName();
		return "index";
	}
}
