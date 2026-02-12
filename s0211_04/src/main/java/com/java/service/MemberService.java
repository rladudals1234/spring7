package com.java.service;

import java.util.List;

import com.java.dto.Member;

public interface MemberService {
	public List<Member> selectAll();

	public Member selectIdAndPw(Member member);
}
