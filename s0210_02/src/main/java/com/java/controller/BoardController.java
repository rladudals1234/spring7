package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.dto.BoardDto;

@Controller
@RequestMapping("/board")
public class BoardController {
	@GetMapping("/board")
	public String board() {
		return "board/board";
	}
	
	@PostMapping("/doBoard")		//객체타입
	public String doBoard(BoardDto bdto, Model model) {
		int bno = bdto.getBno();
		String id = bdto.getId();
		String btitle = bdto.getBtitle();
		String bcontent = bdto.getBcontent();
		System.out.println("넘어온 데이터 : "+bno+","+id+","+btitle+","+bcontent);
		model.addAttribute("board", bdto);
		return "board/doBoard";
	}
}
