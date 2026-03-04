package com.java.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/list")
	@ResponseBody
	public Map<String, Object> commentList(
			@RequestParam(name="bno", required=false, defaultValue="") Long bno,
			//@RequestParam(name="page", required=false, defaultValue="1") int page,
			@RequestParam(name="size", required=false, defaultValue="10") int size,
			@RequestParam(name="lastCno", required=false, defaultValue="0") Long lastCno,
			Model model) {
		Map<String, Object> map = commentService.findAll(size, bno, lastCno);
		return map;
	}
	
	@PostMapping("/save")
	@ResponseBody
	public Map<String, Object> commentSave(
			//BoardDto bdto, MemberDto mdto,
			@RequestParam(name="bno", required=false, defaultValue="") Long bno,
			@RequestParam(name="id", required=false, defaultValue="") String id,
			CommentDto cdto, 
			Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("comment",commentService.save(bno, id, cdto));
		return map;
	}
	
	@PostMapping("/delete")
	@ResponseBody
	public String commentDelete(CommentDto cdto, Model model) {
		commentService.deleteById(cdto);
		return "성공";
	}
}
