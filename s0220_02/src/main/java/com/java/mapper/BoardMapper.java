package com.java.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.java.dto.BoardDto;

@Mapper		//@Component, @Controller, @Service, @Repository(jpa), @Configration -> 자동으로 넣어줌
public interface BoardMapper {
	List<BoardDto> selectAll(@Param("startrow") int startrow, @Param("endrow") int endrow);
	
	int totalCnt();
	
	List<BoardDto> selectAll(@Param("startrow") int startrow, @Param("endrow") int endrow, 
			@Param("category") String category, @Param("searchWord") String searchWord);

	int insertBoard(BoardDto bdto);

	BoardDto selectOne(int bno);
	
	BoardDto selectOnePrev(BoardDto bno);
	
	BoardDto selectOneNext(BoardDto bno);
	
	int deleteBoard(int bno);

	int updateBoard(BoardDto bdto);

	int insertReply(BoardDto bdto);
	
	int updateReply(BoardDto bdto);
	
	int updatebhit(int bno);
}
