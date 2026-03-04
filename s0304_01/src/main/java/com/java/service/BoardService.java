package com.java.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.java.dto.BoardDto;

public interface BoardService {
	public BoardDto findById(BoardDto mdto);
	
	public BoardDto save(BoardDto mdto);

	public Page<BoardDto> findAll(int page, int size, Pageable pageable);
	
	public Map<String, Object> findAll(int page, int size, String category, String search);
	
	public Map<String, Object> findAll(int page, int size);
	
	//public List<BoardDto> findAll(Pageable pageable);

	public void deleteById(BoardDto mdto);

	public BoardDto update(BoardDto mdto);

	public BoardDto findById(Long bno);

	public BoardDto reply(BoardDto bdto);
	
	public BoardDto findByPre(Long bno);
	
	public BoardDto findByNext(Long bno);

}
