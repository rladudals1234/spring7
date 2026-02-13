package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.MemberDto;
import com.java.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberMapper memberMapper;
	
	@Override
	public List<MemberDto> selectAll() {
		return memberMapper.selectAll();
	}

	@Override
	public MemberDto selectLogin(MemberDto member) {
		return memberMapper.selectLogin(member);
	}

	@Override
	public int insertMember(MemberDto member) {
		return memberMapper.insertMember(member);
	}

}
