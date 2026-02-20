package com.java.service;

import java.util.List;

import com.java.dto.BoardDto;

public interface BoardService {
	List<BoardDto> selectAll(int startrow, int endrow, String category, String searchWord);
	
	int totalCnt();

	int insertBoard(BoardDto bdto);
	
	BoardDto selectOne(int bno);
	
	BoardDto selectOnePrev(BoardDto bdto);
	
	BoardDto selectOneNext(BoardDto bdto);
	
	int deleteBoard(int bno);

	int updateBoard(BoardDto bdto);

	int insertReply(BoardDto bdto);

	int updateReply(BoardDto bdto);
	
	int updatebhit(int bno);
}
