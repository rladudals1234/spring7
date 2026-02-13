package com.java.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.MemberDto;

@Mapper
public interface MemberMapper {
	public List<MemberDto> selectAll();

	public MemberDto selectLogin(MemberDto member);

	public int insertMember(MemberDto member);
}
