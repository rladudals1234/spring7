package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/member")
public class MemberController {

    private final FController FController;

    MemberController(FController FController) {
        this.FController = FController;
    }
	@GetMapping("/login")
	public String login() {
		return "member/login";
	}
	
	@PostMapping("/doLogin")
	//@RequestMapping("/doLogin")
	public String doLogin(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		System.out.println("넘어온 데이터 : "+id+","+pw);
		//request.setAttribute("id", id);
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		return "member/doLogin";
	}
	
	@GetMapping("/member")
	public String member() {
		return "member/member";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "member/logout";
	}
}
