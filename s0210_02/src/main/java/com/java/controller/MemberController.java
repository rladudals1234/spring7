package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.dto.MemberDto;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class MemberController {
	@GetMapping("/login")
	public String login() {
		return "member/login";
	}
	
	@PostMapping("/doLogin")
	public String doLogin(MemberDto mdto, Model model) {
		model.addAttribute("member", mdto);
		return "member/doLogin";
	}
	
	@GetMapping("/member")
	public String member() {
		return "member/member";
	}
	
	@PostMapping("/memberOk")
	public String memberOk(MemberDto mdto) {	//오버로딩:메소드명 같고,매개변수개수,타입이 다른것, url은 겹치지 않게(Get,Post구분되면 상관없음)
		ModelAndView model = new ModelAndView();
		model.addObject("id",mdto.getId());
		model.addObject("pw", mdto.getPw());
		//model.addAttribute("id", request.getParameter("id"));
		//model.addAttribute("pw", request.getParameter("pw"));
		return "member/memberOk";
	}
	
	@GetMapping("/join")
	public String join() {
		return "member/join";
	}
	
	@PostMapping("/doJoin")
	public String doJoin(MemberDto mdto, HttpServletResponse response, Model model) {
		model.addAttribute("member", mdto);
		return "member/doJoin";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		return "member/logout";
	}
}
