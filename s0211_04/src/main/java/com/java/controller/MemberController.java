package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.dto.Member;
import com.java.service.MemberService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired		//IOC컨테이너에서 시작할때 객체를 주입
	HttpSession session;
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/mlist")
	public String mlist(Model model) {
		model.addAttribute("list", memberService.selectAll());
		return "member/mlist";
	}
	
	@GetMapping("/login")
	public String login(Integer flag, Model model) {
		model.addAttribute("flag", flag);
		return "member/login";
	}
	
	@PostMapping("/login")
	public String login(Member mdto, Model model) {
		Member member = memberService.selectIdAndPw(mdto);
		
		if(member != null) {
			session.setAttribute("session_id", member.getId());
			session.setAttribute("session_name", member.getName());
			return "redirect:/?flag=1";
		} else {
			return "redirect:/member/login?flag=2";
		}
		//return "member/doLogin";
	}
	
	@GetMapping("/join")
	public String join() {
		return "member/join";
	}
	
	@PostMapping("/doJoin")
	public String doJoin(Member mdto, HttpServletResponse response, Model model) {
		model.addAttribute("member", mdto);
		return "member/doJoin";
	}
	
	@GetMapping("/logout")
	public String logout(Model model) {
		session.invalidate();
		return "redirect:/?flag=0";
	}
}
