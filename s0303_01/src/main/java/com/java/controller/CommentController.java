package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.dto.CommentDto;
import com.java.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/save")
	@ResponseBody
	public String commentSave(
			//BoardDto bdto, MemberDto mdto,
			@RequestParam(name="bno", required=false, defaultValue="") Long bno,
			@RequestParam(name="id", required=false, defaultValue="") String id,
			CommentDto cdto, 
			Model model) {
		commentService.save(bno, id, cdto);
		return "성공";
	}
}
