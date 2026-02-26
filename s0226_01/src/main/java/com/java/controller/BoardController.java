package com.java.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.java.dto.BoardDto;
import com.java.dto.MemberDto;
import com.java.service.BoardService;
import com.java.service.MemberService;

import jakarta.servlet.http.HttpSession;

//@RequiredArgsConstructor		//ioc센터에서 final매핑
@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService boardService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("/blist")
	public String blist(Model model) {
		List<BoardDto> list = boardService.findAll();	//service에서 정렬
		model.addAttribute("list", list);
		return "board/blist";
	}
	
	@GetMapping("/bview")
	public String bview(BoardDto bdto, Model model) {
		BoardDto board = boardService.findById(bdto);
		model.addAttribute("board", board);
		return "board/bview";
	}
	
	@GetMapping("/bwrite")
	public String bwrite(Model model) {
		return "board/bwrite";
	}
	
	@PostMapping("/bwrite")
	public String bwrite(BoardDto bdto,
			@RequestPart("file") MultipartFile file,
			Model model) {
		String id=(String) session.getAttribute("session_id");
		MemberDto member = memberService.findById(MemberDto.builder().id(id).build());
		System.out.println(member);
		if(!file.isEmpty()) {
			String fName = file.getOriginalFilename();
			long time = System.currentTimeMillis();
			String refName = String.format("%s_%s", time, fName);
			String fileUploadUrl = "c:/upload/";
			try {
				File f = new java.io.File(fileUploadUrl+refName);
				file.transferTo(f);
			} catch (Exception e) {
				e.printStackTrace();
			}
			bdto.setBfile(refName);
		}
		bdto.setMember(member);
		boardService.save(bdto);
		return "redirect:/board/blist";
	}
	
	@ResponseBody
	@DeleteMapping("/bdelete")
	public String bdelete(BoardDto bdto, Model model) {
		boardService.deleteById(bdto);
		return "데이터삭제완료";
	}
	
	@GetMapping("/bupdate")
	public String bupdate(BoardDto bdto, Model model) {
		BoardDto board = boardService.findById(bdto);
		model.addAttribute("board", board);
		return "board/bupdate";
	}
	
	@PostMapping("/bupdate")
	public String mupdate1(BoardDto bdto, Model model) {
		String id=(String) session.getAttribute("session_id");
		MemberDto member = memberService.findById(id);
		bdto.setMember(member);
		boardService.update(bdto);
		return "redirect:/board/bview?bno="+bdto.getBno();
	}
}
