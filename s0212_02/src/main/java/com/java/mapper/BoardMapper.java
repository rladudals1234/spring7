package com.java.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.BoardDto;

@Mapper		//@Component, @Controller, @Service, @Repository(jpa), @Configration -> 자동으로 넣어줌
public interface BoardMapper {
	List<BoardDto> selectAll();
}
