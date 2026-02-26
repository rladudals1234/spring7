package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
				//.orElse(new MemberDto());		//없을경우 빈 객체로 리턴
				/*.orElseGet(()->{
					return new MemberDto();	//없어도 빈 객체로 리턴
				});*/
				/*.orElseGet(()->{
					return null;		//없을때 null로 리턴
				});*/
				.orElseThrow(()->{		//없을경우 예외처리를 리턴
					return new IllegalArgumentException("검색데이터가 없음.");
				});
	}

	@Override
	public MemberDto selectLogin(MemberDto mdto) {
		return memberRepository.selectLogin(mdto.getId(), mdto.getPw()).orElseGet(()->{
			return new MemberDto();
		});
	}

	@Transactional
	@Override
	public MemberDto save(MemberDto mdto) {
		return memberRepository.save(mdto);
	}
	
	@Transactional
	@Override
	public MemberDto update(MemberDto mdto) {
		//기존에 있던 데이터에도 null값으로 들어갈 수 있어서 기존에 있던 데이터를 찾아서 넣어주고 일부만 수정
		MemberDto memberDto = memberRepository.findById(mdto.getId()).orElse(null);
		memberDto.setId(mdto.getId());
		memberDto.setPw(mdto.getPw());
		memberDto.setName(mdto.getName());
		memberDto.setPhone(mdto.getPhone());
		memberDto.setEmail(mdto.getEmail());
		memberDto.setGender(mdto.getGender());
		memberDto.setHobby(mdto.getHobby());
		return memberRepository.save(memberDto);
	}

	@Override
	public List<MemberDto> findAll() {
		Sort sort = Sort.by(Sort.Order.desc("name"), Sort.Order.asc("id"));
		return memberRepository.findAll(sort);
		//return memberRepository.findAll(Sort.by(Sort.Order.desc("name"), Sort.Order.asc("id")));
	}

	@Override
	public void deleteById(MemberDto mdto) {
		memberRepository.deleteById(mdto.getId());;
	}

	@Override
	public MemberDto findById(MemberDto mdto) {
		return memberRepository.findById(mdto.getId()).orElseGet(()->{
			return null;		//없을때 null로 리턴
		});
	}
	
	@Override
	public MemberDto findById(String id) {
		return memberRepository.findById(id).orElseGet(()->{
			return null;		//없을때 null로 리턴
		});
	}

}
