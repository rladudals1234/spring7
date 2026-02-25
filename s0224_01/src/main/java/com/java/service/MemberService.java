package com.java.service;

import java.util.List;

import com.java.dto.MemberDto;

public interface MemberService {
	public MemberDto findByIdAndPw(MemberDto mdto);

	public MemberDto selectLogin(MemberDto mdto);

	public MemberDto save(MemberDto mdto);

	public List<MemberDto> findAll();

	public void deleteById(MemberDto mdto);
}
