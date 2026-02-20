package com.java.service;

import java.util.List;

import com.java.dto.BoardDto;

public interface BoardService {
	List<BoardDto> selectAll();

	int insertBoard(BoardDto bdto);
	
	BoardDto selectOne(int bno);
	
	int deleteBoard(int bno);

	int updateBoard(BoardDto bdto);

	int insertReply(BoardDto bdto);

	int updateReply(BoardDto bdto);
}
