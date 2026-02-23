package com.java.service;

import com.java.dto.MemberDto;

public interface MemberService {
	public MemberDto findByIdAndPw(MemberDto mdto);

	public MemberDto selectLogin(MemberDto mdto);

	public MemberDto save(MemberDto mdto);
}
