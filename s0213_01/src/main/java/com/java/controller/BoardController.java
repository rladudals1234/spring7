package com.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.dto.BoardDto;
import com.java.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@GetMapping("/blist")
	public String blist(Model model) {
		List<BoardDto> list = boardService.selectAll();
		model.addAttribute("list",list);
		return "board/blist";
	}
	
	//게시글 쓰기 페이지
	@GetMapping("/bwrite")
	public String bwrite(Model model) {
		return "board/bwrite";
	}
	
	@PostMapping("/bwrite")
	public String bwrite(BoardDto bdto, Model model) {
		
		return "redirect:/board/blist?flag=1";
	}
}
