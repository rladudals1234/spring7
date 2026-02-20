package com.java.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

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
	public String blist(
			@RequestParam(name="page",defaultValue="1") int page,
			@RequestParam(name="category",required=false) String category,
			@RequestParam(name="searchWord",required=false) String searchWord,
			Model model) {
		int totalCnt = boardService.totalCnt();
		int rowPerPage = 10;
		int maxPage = (int)Math.ceil((double)totalCnt/rowPerPage);
		//int maxPage = ((double)totalCnt + rowPerPage - 1) / rowPerPage;
		int startPage = ((page-1)/10)*10+1;
		int endPage = startPage+9;
		if (endPage > maxPage) endPage = maxPage;
		int startrow = (page-1)*10+1;
		int endrow = startrow + rowPerPage - 1;
		List<BoardDto> list = boardService.selectAll(startrow,endrow,category,searchWord);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", page);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("maxPage", maxPage);
		map.put("totalCnt", totalCnt);
		map.put("startrow", startrow);
		map.put("endrow", endrow);
		map.put("list", list);
		model.addAttribute("map",map);
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
	
	@PostMapping("/bwrite") //글쓰기 페이지 열기
	public String bwrite(@ModelAttribute BoardDto bdto, 
			@RequestPart("file") MultipartFile files,
			Model model) {
		if(!files.isEmpty()) {
			//원본이름 500.jpg -> 500.jpg
			String originFileName = files.getOriginalFilename();
			long time = System.currentTimeMillis();
			String uploadFileName = String.format("%s_%s",time,originFileName);
			bdto.setBfile(uploadFileName);
			//파일위치
			String fileUrl = "c:/upload/";
			File f = new File(fileUrl+uploadFileName);
			try {
				files.transferTo(f);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		//String id = (String) session.getAttribute("session_id");
		//if(id == null) return "redirect:/member/login";
		//bdto.setId(id);
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
