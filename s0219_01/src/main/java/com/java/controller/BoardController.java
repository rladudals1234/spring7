package com.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.dto.BoardDto;
import com.java.service.BoardService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("/blist")
	public String blist(Model model) {
		List<BoardDto> list = boardService.selectAll();
		model.addAttribute("list",list);
		return "board/blist";
	}
	
	@GetMapping("/bview")
	public String bview(@RequestParam(name="bno",required=false) int bno, Model model) {
		BoardDto board = boardService.selectOne(bno);
		model.addAttribute("board",board);
		return "board/bview";
	}
	
	//게시글 쓰기 페이지
	@GetMapping("/bwrite")
	public String bwrite(Model model) {
		return "board/bwrite";
	}
	
	@PostMapping("/bwrite")
	public String bwrite(@ModelAttribute BoardDto bdto, Model model, HttpSession session) {
		String id = (String) session.getAttribute("session_id");
		if(id == null) return "redirect:/member/login";
		bdto.setId(id);
		boardService.insertBoard(bdto);
		return "redirect:/board/blist?flag=1";
	}
	
	@GetMapping("/bdelete")
	public String bdelete(@RequestParam(name="bno",required=false) int bno, Model model) {
		String id = (String) session.getAttribute("session_id");
		if(id == null) return "redirect:/member/login";
		int flag = boardService.deleteBoard(bno);
		return "redirect:/board/blist?flag="+(flag+1);
	}
	
	@GetMapping("/bupdate")
	public String bupdate(@RequestParam(name="bno",required=false) int bno, Model model) {
		BoardDto board = boardService.selectOne(bno);
		model.addAttribute("board",board);
		return "board/bupdate";
	}
	
	@PostMapping("/bupdate")		//수정저장
	public String bupdate(@ModelAttribute BoardDto bdto, Model model) {
		String id = (String) session.getAttribute("session_id");
		if(id == null) return "redirect:/member/login";
		bdto.setId(id);
		int flag = boardService.updateBoard(bdto);
		return "redirect:/board/blist?flag="+flag;
	}
	
	@GetMapping("/breply")
	public String breply(@RequestParam(name="bno",required=false) int bno, Model model) {
		BoardDto board = boardService.selectOne(bno);
		model.addAttribute("board",board);
		return "board/breply";
	}
	
	@PostMapping("/breply")
	public String breply(@ModelAttribute BoardDto bdto, Model model) {
		String id = (String) session.getAttribute("session_id");
		if(id == null) return "redirect:/member/login";
		boardService.insertReply(bdto);
		return "redirect:/board/blist";
	}
}
