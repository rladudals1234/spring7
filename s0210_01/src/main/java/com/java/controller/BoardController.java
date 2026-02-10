package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.dto.BoardDto;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/board")
public class BoardController {
	@GetMapping("/board")
	public String board() {
		return "board/board";
	}
	
	//pathVariable방법
	@GetMapping("/boardView/{bno}")
	public ModelAndView boardView(@PathVariable Integer bno, Model model) {
	//public ModelAndView boardView(@PathVariable(value="bno",required = false) Integer bno, Model model) {
		
		//빌더는 생성자와 다르게 순서상관없음
		BoardDto b = BoardDto.builder()
				.bno(bno).btitle("제목입니다.")
				.bcontent("내용입니다.")
				.build();
		
		System.out.println("bno : "+bno);
		ModelAndView mv = new ModelAndView();
		mv.addObject("bno",bno);
		mv.setViewName("board/boardView");
		return mv;
	}
	
	/*@GetMapping("/boardView")
	public ModelAndView boardView(Integer bno, Model model) {
		System.out.println("bno : "+bno);
		ModelAndView mv = new ModelAndView();
		mv.addObject("bno",bno);
		mv.setViewName("board/boardView");
		return mv;
		//return "board/boardView";
	}*/
	
	@PostMapping("/doBoard")		//객체타입
	public String doBoard(@Valid BoardDto bdto, Model model) {
	//public String doBoard(BoardDto bdto, Model model) {
		int bno = bdto.getBno();
		String id = bdto.getId();
		String btitle = bdto.getBtitle();
		String bcontent = bdto.getBcontent();
		System.out.println("넘어온 데이터 : "+bno+","+id+","+btitle+","+bcontent);
		model.addAttribute("board", bdto);
		return "board/doBoard";
	}
	
	/*@PostMapping("/doBoard")
	public String doBoard(@RequestParam("bno") int bno,
			@RequestParam("id") String id,
			@RequestParam("btitle") String btitle,
			@RequestParam("bcontent") String bcontent,
			Model model) {
		System.out.println("넘어온 데이터 : "+bno+","+id+","+btitle+","+bcontent);
		model.addAttribute("bno", bno);
		model.addAttribute("id", id);
		model.addAttribute("btitle", btitle);
		model.addAttribute("bcontent", bcontent);
		return "board/doBoard";
	}*/
	
	/*@PostMapping("/doBoard")		//@RequestParam 데이터 이름이 같으면 생략
	public String doBoard(@RequestParam int bno,
			@RequestParam String id,
			@RequestParam String btitle,
			@RequestParam String bcontent,
			Model model) {
		System.out.println("넘어온 데이터 : "+bno+","+id+","+btitle+","+bcontent);
		model.addAttribute("bno", bno);
		model.addAttribute("id", id);
		model.addAttribute("btitle", btitle);
		model.addAttribute("bcontent", bcontent);
		return "board/doBoard";
	}*/
	
	/*@PostMapping("/doBoard")		//데이터 이름이 같으면 @RequestParam생략 가능	//자동으로 형변환 가능 - 타입이 다른경우는 null값일때 에러발생
	public String doBoard( int bno, String id, String btitle,String bcontent, Model model) {
		System.out.println("넘어온 데이터 : "+bno+","+id+","+btitle+","+bcontent);
		model.addAttribute("bno", bno);
		model.addAttribute("id", id);
		model.addAttribute("btitle", btitle);
		model.addAttribute("bcontent", bcontent);
		return "board/doBoard";
	}*/
	
	/*@PostMapping("/doBoard")		//@RequestParam 값이 없으면 디폴트 값 넣기 가능
	public String doBoard(@RequestParam(name = "bno", defaultValue = "0") int bno,
			@RequestParam(name = "id", defaultValue = "aaa") String id,
			@RequestParam(name = "btitle", defaultValue = "test") String btitle,
			@RequestParam(name = "bcontent", defaultValue = "test12345") String bcontent,
			Model model) {
		System.out.println("넘어온 데이터 : "+bno+","+id+","+btitle+","+bcontent);
		model.addAttribute("bno", bno);
		model.addAttribute("id", id);
		model.addAttribute("btitle", btitle);
		model.addAttribute("bcontent", bcontent);
		return "board/doBoard";
	}*/
}
