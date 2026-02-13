package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.dto.MemberDto;
import com.java.service.MemberService;

import jakarta.servlet.http.Cookie;
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
	public String login(MemberDto mdto, String saveId, HttpServletResponse response) {
		MemberDto member = memberService.selectLogin(mdto);
		System.out.println("saveId : "+saveId);
		//아이디저장 - cookie
		Cookie cookie = new Cookie("cook_id", mdto.getId());
		if(saveId != null) {
			cookie.setMaxAge(60*60*24*30);	//60초X60분X24시간X30일
		} else {
			cookie.setMaxAge(0);
		}
		response.addCookie(cookie);
		if(member != null) {
			session.setAttribute("session_id", member.getId());
			session.setAttribute("session_name", member.getName());
			return "redirect:/?flag=1";
		} else {
			return "redirect:/member/login?flag=2";
		}
		//return "member/doLogin";
	}
	
	@GetMapping("/join01")
	public String join01() {
		return "member/join01";
	}
	
	@GetMapping("/join02")
	public String join02() {
		return "member/join02";
	}
	
	//회원가입03 확인 - phone1,2,3,email1,2
	@PostMapping("/join03")
	public String join03(MemberDto mdto, String phone1, String phone2, String phone3, String email1, String email2) {
		mdto.setPhone(phone1+"-"+phone2+"-"+phone3);
		mdto.setEmail(email1+"@"+email2);
		System.out.println("id,pw : "+mdto.getId()+","+mdto.getPw());
		System.out.println("phone : "+phone1+","+phone2+","+phone3);
		System.out.println("email : "+email1+","+email2);
		
		int result = memberService.insertMember(mdto);
		System.out.println("성공:"+result);
		return "member/join03";
	}
	
	@PostMapping("/doJoin")
	public String doJoin(MemberDto mdto, HttpServletResponse response, Model model) {
		model.addAttribute("member", mdto);
		return "member/doJoin";
	}
	
	@GetMapping("/logout")
	public String logout(Model model) {
		session.invalidate();
		return "redirect:/?flag=0";
	}
}
