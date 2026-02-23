package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.MemberDto;
import com.java.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberRepository memberRepository;
	
	@Override
	public MemberDto findByIdAndPw(MemberDto mdto) {
		//검색해서 데이터가 있을 경우 : memberDto 객체를 가져옴.
		//검색해서 데이터가 없을 경우 : Optional타입을 넘겨받아서 빈 객체로 생성해서 리턴
		return memberRepository.findByIdAndPw(mdto.getId(), mdto.getPw())
				//.get();		//없을 경우 에러
				.orElseGet(()->{
					return new MemberDto();
				});
				/*.orElseThrow(()->{		//없을경우 예외처리를 리턴
					return new IllegalArgumentException();
				});*/
	}

	@Override
	public MemberDto selectLogin(MemberDto mdto) {
		return memberRepository.selectLogin(mdto.getId(), mdto.getPw()).orElseGet(()->{
			return new MemberDto();
		});
	}

	@Override
	public MemberDto save(MemberDto mdto) {
		return memberRepository.save(mdto);
	}

}
