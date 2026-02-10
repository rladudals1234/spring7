package com.java.controller;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.dto.MemberDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class MemberController {
	@GetMapping("/login")
	public String login(Integer flag, Model model) {
		model.addAttribute("flag", flag);
		return "member/login";
	}
	
	@PostMapping("/doLogin")
	public String doLogin(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		System.out.println("넘어온 데이터 : "+id+","+pw);
		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		session.setAttribute("pw", pw);
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		if(id.equals("aaa") && pw.equals("1111")) {
			return "redirect:/?flag=1";
		}else {
			return "redirect:/member/login?flag=2";
		}
		
		//return "member/doLogin";
	}
	
	@GetMapping("/member")
	public String member() {
		return "member/member";
	}
	
	@PostMapping("/memberOk")
	public String member(HttpServletRequest request) {	//오버로딩:메소드명 같고,매개변수개수,타입이 다른것, url은 겹치지 않게
		ModelAndView model = new ModelAndView();
		model.addObject("id",request.getParameter("id"));
		model.addObject("pw", request.getParameter("pw"));
		//model.addAttribute("id", request.getParameter("id"));
		//model.addAttribute("pw", request.getParameter("pw"));
		return "member/memberOk";
	}
	
	@GetMapping("/join")
	public String join() {
		return "member/join";
	}
	
	@GetMapping("/mUpdate")
	public String mUpdate(Model model) {
		MemberDto mdto = MemberDto.builder()
				.id("aaa")
				.pw("1111")
				.name("이상호")
				.phone("010-1111-2222")
				.email("8OYgR@example.com")
				.gender("남자")
				.hobby("게임,골프,독서")
				.build();
		model.addAttribute("member", mdto);
		return "member/mUpdate";
	}
	
	@PostMapping("/doJoin")
	public String doJoin(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("id", request.getParameter("id"));
		model.addAttribute("pw", request.getParameter("pw"));
		model.addAttribute("name", request.getParameter("name"));
		model.addAttribute("phone", request.getParameter("phone"));
		model.addAttribute("email", request.getParameter("email"));
		String gender = request.getParameter("gender");
		model.addAttribute("gender", gender);
		String[] hobbys = request.getParameterValues("hobby");
		model.addAttribute("hobby", Arrays.toString(hobbys));
		return "member/doJoin";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		System.out.println(session.getAttribute("id"));
		System.out.println(session.getAttribute("pw"));
		session.invalidate();
		return "member/logout";
	}
}
