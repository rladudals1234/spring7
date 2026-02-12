package com.java.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.Member;

@Mapper
public interface MemberMapper {
	public List<Member> selectAll();

	public Member selectIdAndPw(Member member);
}
