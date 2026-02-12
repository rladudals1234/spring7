package com.java.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.Board;

@Mapper	//mybatis지원
public interface BoardMapper {
	List<Board> selectAll();
}
