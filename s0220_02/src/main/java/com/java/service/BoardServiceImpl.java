package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.BoardDto;
import com.java.mapper.BoardMapper;

@Service		//객체선언없이 사용가능/IOC컨테이너에 등록
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardMapper boardMapper;
	
	@Override
	public List<BoardDto> selectAll(int startrow, int endrow, String category, String searchWord) {
		return boardMapper.selectAll(startrow, endrow, category, searchWord);
	}
	
	@Override
	public int totalCnt() {
		return boardMapper.totalCnt();
	}

	@Override
	public int insertBoard(BoardDto bdto) {
		return boardMapper.insertBoard(bdto);
	}

	@Override
	public BoardDto selectOne(int bno) {
		updatebhit(bno);
		return boardMapper.selectOne(bno);
	}

	@Override
	public int deleteBoard(int bno) {
		return boardMapper.deleteBoard(bno);
	}

	@Override
	public int updateBoard(BoardDto bdto) {
		return boardMapper.updateBoard(bdto);
	}

	@Override
	public int insertReply(BoardDto bdto) {
		boardMapper.updateReply(bdto);
		return boardMapper.insertReply(bdto);
	}

	@Override
	public int updateReply(BoardDto bdto) {
		return boardMapper.updateReply(bdto);
	}

	@Override
	public int updatebhit(int bno) {
		return boardMapper.updatebhit(bno);
	}

	@Override
	public BoardDto selectOnePrev(BoardDto bdto) {
		return boardMapper.selectOnePrev(bdto);
	}

	@Override
	public BoardDto selectOneNext(BoardDto bdto) {
		return boardMapper.selectOneNext(bdto);
	}

}
