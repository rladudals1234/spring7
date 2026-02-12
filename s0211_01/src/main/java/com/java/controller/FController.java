package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FController {
	@GetMapping({"/","/index"})
	public String index(Integer flag, Model model) {
		model.addAttribute("flag", flag);
		return "index";
	}
	
	@GetMapping("/list")
	public String list() {
		return "notice_list";
	}
}
