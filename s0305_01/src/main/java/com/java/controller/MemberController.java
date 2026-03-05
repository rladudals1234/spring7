package com.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.dto.MemberDto;
import com.java.service.MemberService;

import jakarta.servlet.http.HttpSession;

//@RequiredArgsConstructor		//ioc센터에서 final매핑
@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	MemberService memberService;
	
	@Autowired
	HttpSession session;
	
	//private final MemberService memberService;
	
	@GetMapping("/login")		//로그인페이지
	public String login() {
		return "member/login";
	}
	
	@PostMapping("/login")
	public String login(MemberDto mdto, Model model) {
		System.out.println("id,pw : "+mdto.getId()+","+mdto.getPw());
		//MemberDto memberDto = memberService.findByIdAndPw(mdto);
		MemberDto memberDto = memberService.selectLogin(mdto);
		System.out.println(memberDto);
		model.addAttribute("login", memberDto);
		if(memberDto!=null) {
			System.out.println("로그인이 되었습니다.");
			session.setAttribute("session_id", memberDto.getId());
			return "redirect:/";
		} else {
			System.out.println("아이디 또는 패스워드가 일치하지 않습니다.");
			return "member/login";
		}
		//return "member/login";
	}
	
	@GetMapping("/membership")		//회원가입페이지
	public String membership() {
		return "member/membership";
	}
	
	@ResponseBody
	@PostMapping("/membership")
	public MemberDto membership(MemberDto mdto, 
			@RequestParam(name="phone1",required=false) String phone1,
			@RequestParam(name="phone2",required=false) String phone2,
			@RequestParam(name="phone3",required=false) String phone3,
			Model model) {
		mdto.setPhone(String.format("%s-%s-%s", phone1, phone2, phone3));
		System.out.println(mdto);
		//db저장 - service전달
		MemberDto member = memberService.save(mdto);
		return member;
	}
	
	@GetMapping("/mview")		//회원가입페이지
	public String mview(MemberDto mdto, Model model) {
		//MemberDto member = memberService.findById(mdto);
		model.addAttribute("member", memberService.findById(mdto));
		System.out.println(memberService.findById(mdto));
		return "member/mview";
	}
	
	@GetMapping("/mlist")
	public String mlist(Model model) {
		List<MemberDto> list = memberService.findAll();
		model.addAttribute("list", list);
		return "member/mlist";
	}
	
	
	@ResponseBody
	@DeleteMapping("/mdelete")
	public String mdelete(MemberDto mdto, Model model) {
		memberService.deleteById(mdto);
		//return "redirect:/";		//ajax 메세지 등 반환안할거면 @ResponseBody없어도 됨
		return "데이터삭제완료";
	}
	
	@GetMapping("/mupdate")
	public String mupdate(MemberDto mdto, Model model) {
		MemberDto member = memberService.findById(mdto);
		String[] phone = member.getPhone().split("-");
		model.addAttribute("member", member);
		model.addAttribute("phone", phone);
		return "member/mupdate";
	}
	
	@PostMapping("/mupdate")
	public String mupdate1(MemberDto mdto, 
			@RequestParam(name="phone1",required=false) String phone1,
			@RequestParam(name="phone2",required=false) String phone2,
			@RequestParam(name="phone3",required=false) String phone3,
			Model model) {
		mdto.setPhone(String.format("%s-%s-%s", phone1, phone2, phone3));
		String id=(String) session.getAttribute("session_id");
		mdto.setId(id);
		memberService.update(mdto);
		return "redirect:/member/mview?id="+mdto.getId();
	}
	
	@GetMapping("/logout")
	public String logout(Model model) {
		session.invalidate();
		return "redirect:/";
	}
}
