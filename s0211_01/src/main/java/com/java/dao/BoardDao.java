package com.java.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.BoardDto;

@Mapper	//mybatis지원
public interface BoardDao {
	List<BoardDto> selectAll();
}
