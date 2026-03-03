package com.java.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.data.web.SortDefault.SortDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public String blist(
			@RequestParam(name="page", required=false, defaultValue="1") int page,
			@RequestParam(name="size", required=false, defaultValue="10") int size,
			@RequestParam(name="category", required=false, defaultValue="") String category,
			@RequestParam(name="search", required=false, defaultValue="") String search,
			@SortDefaults({
				@SortDefault(sort="bgroup", direction=Sort.Direction.DESC),
				@SortDefault(sort="bstep", direction=Sort.Direction.ASC)
			}) Pageable pageable,
			Model model) {
		Map<String, Object> map = null;
//		if(search != null) {
			map = boardService.findAll(page, size, category, search);	//service에서 정렬
//		}else {
//			map = boardService.findAll(page, size);	//service에서 정렬
//		}
		//Page<BoardDto> list = boardService.findAll(page, size);	//service에서 정렬
		//model.addAttribute("list", list);
		model.addAttribute("map", map);
		return "board/blist";
	}

	@ResponseBody
	@GetMapping("/ajaxBlist")
	public Page<BoardDto> ajaxBlist(
			@RequestParam(name="page", required=false, defaultValue="1") int page,
			@RequestParam(name="size", required=false, defaultValue="10") int size,
			@SortDefaults({
				@SortDefault(sort="bgroup", direction=Sort.Direction.DESC),
				@SortDefault(sort="bstep", direction=Sort.Direction.ASC)
			}) Pageable pageable,
			Model model) {
		Page<BoardDto> list = boardService.findAll(page, size, pageable);	//service에서 정렬 (page,size대신 pageable로 넘길 수 있음)
		System.out.println(list.getTotalElements());	//총개수
		System.out.println(list.getTotalPages());	//총페이지수
		System.out.println(list.getNumber());
		System.out.println(list.getPageable());
		//model.addAttribute("list", list);
		return list;
		//return "board/blist";
	}
	
	@GetMapping("/bview/{bno}")
	public String bview(@PathVariable(name="bno", required=true) Long bno,
			@RequestParam(name="category", required=false, defaultValue="") String category,
			@RequestParam(name="search", required=false, defaultValue="") String search,
			Model model) {
		BoardDto bdto = BoardDto.builder().bno(bno).build();
		BoardDto boardDto = boardService.findById(bdto);
		BoardDto next = boardService.findByNext(bno);
		BoardDto pre = boardService.findByPre(bno);	//조회라서 트렌젝션 안 붙임
		model.addAttribute("nextBoard", next);
		model.addAttribute("preBoard", pre);
		model.addAttribute("board", boardDto);
		Map<String, Object> map = new HashMap<>();
		map.put("category", category);
		map.put("search", search);
		model.addAttribute("map", map);
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
		BoardDto board = boardService.findById(bdto);
		String fileName = board.getBfile();
		boardService.deleteById(bdto);		//
		if (board != null && board.getBfile() != null) {
	        String fileUploadUrl = "c:/upload/";
	        File file = new File(fileUploadUrl + fileName);
	        
	        // 2. 물리적 파일 존재 여부 확인 후 삭제
	        if (file.exists()) {
	            if (file.delete()) {
	                System.out.println("파일 삭제 성공: " + fileName);
	            } else {
	                System.out.println("파일 삭제 실패");
	            }
	        }
	    }
		return "데이터삭제완료";
	}
	
	@GetMapping("/bupdate")
	public String bupdate(BoardDto bdto, Model model) {
		BoardDto board = boardService.findById(bdto);
		model.addAttribute("board", board);
		return "board/bupdate";
	}
	
	@PostMapping("/bupdate")
	public String mupdate1(BoardDto bdto, 
			@RequestPart("file") MultipartFile file,
			Model model) {
		String id=(String) session.getAttribute("session_id");
		MemberDto member = memberService.findById(id);
		bdto.setMember(member);
		if(!file.isEmpty()) {
			String fName = file.getOriginalFilename();
			long time = System.currentTimeMillis();
			String refName = String.format("%s_%s", time, fName);
			String fileUploadUrl = "c:/upload/";
			try {
				File oldBfile = new File(fileUploadUrl + bdto.getBfile());	//새로운 파일있으면 기존은 삭제
				if (oldBfile.exists()) {
		            if (oldBfile.delete()) {
		                System.out.println("파일 삭제 성공: " + bdto.getBfile());
		            } else {
		                System.out.println("파일 삭제 실패");
		            }
		        }
				File f = new java.io.File(fileUploadUrl+refName);
				file.transferTo(f);
			} catch (Exception e) {
				e.printStackTrace();
			}
			bdto.setBfile(refName);
		}
		boardService.update(bdto);
		return "redirect:/board/bview/"+bdto.getBno();
	}
	
	@GetMapping("/breply")
	public String breply(BoardDto bdto, Model model) {
		BoardDto board = boardService.findById(bdto);
		model.addAttribute("board", board);
		return "board/breply";
	}
	
	@PostMapping("/breply")
	public String breply(BoardDto bdto, 
			@RequestPart("file") MultipartFile file,
			Model model) {
		String id=(String) session.getAttribute("session_id");
		MemberDto member = memberService.findById(id);
		bdto.setMember(member);
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
		boardService.reply(bdto);
		return "redirect:/board/blist";
	}
}
