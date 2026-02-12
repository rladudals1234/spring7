package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.BoardDao;
import com.java.dto.BoardDto;

@Service		//객체선언없이 사용가능/IOC컨테이너에 등록
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDao boardMapper;
	
	@Override
	public List<BoardDto> selectAll() {
		return boardMapper.selectAll();
	}

}
