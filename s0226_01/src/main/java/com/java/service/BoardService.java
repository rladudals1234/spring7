package com.java.service;

import java.util.List;

import com.java.dto.BoardDto;

public interface BoardService {
	public BoardDto findById(BoardDto mdto);
	
	public BoardDto save(BoardDto mdto);

	public List<BoardDto> findAll();

	public void deleteById(BoardDto mdto);

	public BoardDto update(BoardDto mdto);

	public BoardDto findById(Long bno);
}
