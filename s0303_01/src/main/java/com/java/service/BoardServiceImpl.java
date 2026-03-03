package com.java.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.dto.BoardDto;
import com.java.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardRepository boardRepository;

	@Transactional
	@Override
	public BoardDto save(BoardDto bdto) {
		bdto.setBhit(0);
		BoardDto board = boardRepository.save(bdto);
		board.setBgroup(board.getBno());
		board.setBstep(0L);
		board.setBindent(0L);
		return board;		//Transactional 덕분에 save 호출 없이도 bgroup 업데이트 반영
	}
	
	@Transactional
	@Override
	public BoardDto update(BoardDto bdto) {
		//기존에 있던 데이터에도 null값으로 들어갈 수 있어서 기존에 있던 데이터를 찾아서 넣어주고 일부만 수정
		BoardDto boardDto = boardRepository.findById(bdto.getBno()).orElse(null);
		boardDto.setBno(bdto.getBno());
		boardDto.setBtitle(bdto.getBtitle());
		boardDto.setBcontent(bdto.getBcontent());
		boardDto.setMember(bdto.getMember());
		boardDto.setBfile(bdto.getBfile());
		boardDto.setBdate(new Timestamp(System.currentTimeMillis()));
		return boardRepository.save(boardDto);
	}

	@Override
	public Page<BoardDto> findAll(int page, int size, Pageable pageable) {
		//Sort sort = Sort.by(Sort.Order.desc("bgroup"), Sort.Order.asc("bstep"));	//정렬
		//Pageable pageable = PageRequest.of(page, size, sort);
		return boardRepository.findAll(pageable);
	}
	
	@Override
	public Map<String, Object> findAll(int page, int size) {
		Sort sort = Sort.by(Sort.Order.desc("bgroup"), Sort.Order.asc("bstep"));	//정렬
		Pageable pageable = PageRequest.of(page-1, size, sort);
		
		Page<BoardDto> pageList = boardRepository.findAll(pageable);
		List<BoardDto> list = pageList.getContent();
		int maxPage = pageList.getTotalPages();
		int startPage = ((page-1)/10)*10+1;
		int endPage = Math.min(startPage+9, maxPage);
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("page", page);
		map.put("maxPage", maxPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		return map;
	}
	
	@Override
	public Map<String, Object> findAll(int page, int size, String category, String search) {
		Sort sort = Sort.by(Sort.Order.desc("bgroup"), Sort.Order.asc("bstep"));	//정렬
		Pageable pageable = PageRequest.of(page-1, size, sort);
		
		Page<BoardDto> pageList;
		if (category.equals("btitle")) {
	        pageList = boardRepository.findByBtitleContaining(search, pageable);
	    } else if (category.equals("bcontent")) {
	    	pageList = boardRepository.findByBcontentContaining(search, pageable);
	    } else if (category.equals("")) {
	        pageList = boardRepository.findByBtitleContainingOrBcontentContaining(search, search, pageable);
	    } else {
	    	pageList = boardRepository.findAll(pageable);
	    }
		List<BoardDto> list = pageList.getContent();
		int maxPage = pageList.getTotalPages();
		int startPage = ((page-1)/10)*10+1;
		int endPage = Math.min(startPage+9, maxPage);
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("page", page);
		map.put("maxPage", maxPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("category", category);
		map.put("search", search);
		return map;
	}

	@Override
	public void deleteById(BoardDto bdto) {
		boardRepository.deleteById(bdto.getBno());;
	}

	@Transactional
	@Override
	public BoardDto findById(BoardDto bdto) {
		BoardDto boardDto = boardRepository.findById(bdto.getBno()).orElseGet(()->{
			return null;		//없을때 null로 리턴
		});
		boardDto.setBhit(boardDto.getBhit()+1);
		return boardDto;
	}
	
	@Override
	public BoardDto findById(Long bno) {
		//해당글
		return boardRepository.findById(bno).orElseGet(()->{
			return null;		//없을때 null로 리턴
		});
	}
	
	public BoardDto findByPre(Long bno) {
		//이전글
		return boardRepository.findByPre(bno).orElse(null);
	}
	
	public BoardDto findByNext(Long bno) {
		//다음글
		return boardRepository.findByNext(bno).orElse(null);
	}

	@Transactional
	@Override
	public BoardDto reply(BoardDto bdto) {
		boardRepository.replyBstepUp(bdto.getBgroup(), bdto.getBstep());
		bdto.setBhit(0);
		bdto.setBdate(new Timestamp(System.currentTimeMillis()));
		BoardDto board = boardRepository.save(bdto);
		board.setBgroup(board.getBgroup());
		board.setBstep(board.getBstep()+1);
		board.setBindent(board.getBindent()+1);
		return board;
	}

}
