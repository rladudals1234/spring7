package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.Member;
import com.java.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberMapper memberMapper;
	
	@Override
	public List<Member> selectAll() {
		return memberMapper.selectAll();
	}

	@Override
	public Member selectIdAndPw(Member member) {
		return memberMapper.selectIdAndPw(member);
	}

}
