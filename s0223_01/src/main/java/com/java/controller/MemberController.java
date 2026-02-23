package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.dto.MemberDto;
import com.java.service.MemberService;

//@RequiredArgsConstructor		//ioc센터에서 final매핑
@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	MemberService memberService;
	
	//private final MemberService memberService;
	
	@GetMapping("/login")
	public String login() {
		return "member/login";
	}
	
	@PostMapping("/login")
	public String login(MemberDto mdto, Model model) {
		System.out.println("id,pw : "+mdto.getId()+","+mdto.getPw());
		//MemberDto login = memberService.findByIdAndPw(mdto);
		MemberDto login = memberService.selectLogin(mdto);
		System.out.println(login);
		model.addAttribute("login", login);
		return "member/login";
	}
	
	@GetMapping("/membership")
	public String membership() {
		return "member/membership";
	}
	
	@PostMapping("/membership")
	public String membership(MemberDto mdto, 
			@RequestParam(name="phone1",required=false) String phone1,
			@RequestParam(name="phone2",required=false) String phone2,
			@RequestParam(name="phone3",required=false) String phone3,
			Model model) {
		mdto.setPhone(phone1+"-"+phone2+"-"+phone3);
		System.out.println(mdto);
		
		memberService.save(mdto);
		return "redirect:/";
	}
}
