package com.java.service;

import java.util.List;

import com.java.dto.MemberDto;

public interface MemberService {
	public List<MemberDto> selectAll();

	public MemberDto selectLogin(MemberDto member);

	public int insertMember(MemberDto mdto);

	public int updateMember(MemberDto mdto);

	public MemberDto selectOne(String id);
}
