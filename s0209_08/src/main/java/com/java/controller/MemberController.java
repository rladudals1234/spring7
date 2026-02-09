package com.java.controller;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/member")
public class MemberController {
	@GetMapping("/login")
	public String login() {
		return "member/login";
	}
	
	@PostMapping("/loginOk")
	public String doLogin(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		System.out.println("넘어온 데이터 : "+id+","+pw);
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		return "member/loginOk";
	}
	
	@GetMapping("/member")
	public String member() {
		return "member/member";
	}
	
	@PostMapping("/memberOk")
	public String member(HttpServletRequest request, Model model) {	//오버로딩:메소드명 같고,매개변수개수,타입이 다른것, url은 겹치지 않게
		model.addAttribute("id", request.getParameter("id"));
		model.addAttribute("pw", request.getParameter("pw"));
		return "member/memberOk";
	}
	
	@GetMapping("/membership")
	public String membership() {
		return "member/membership";
	}
	
	@PostMapping("/membershipOk")
	public String membershipOk(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("id", request.getParameter("id"));
		model.addAttribute("pw", request.getParameter("pw"));
		model.addAttribute("name", request.getParameter("name"));
		model.addAttribute("phone", request.getParameter("phone"));
		model.addAttribute("email", request.getParameter("email"));
		String gender = request.getParameter("gender");
		model.addAttribute("gender", gender);
		String[] hobbys = request.getParameterValues("hobby");
		model.addAttribute("hobby", Arrays.toString(hobbys));
		return "member/membershipOk";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "member/logout";
	}
}
