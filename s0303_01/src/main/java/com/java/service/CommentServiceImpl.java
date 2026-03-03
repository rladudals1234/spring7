package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.dto.BoardDto;
import com.java.dto.CommentDto;
import com.java.dto.MemberDto;
import com.java.repository.BoardRepository;
import com.java.repository.CommentRepository;
import com.java.repository.MemberRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	BoardRepository boardRepository;
	
	@Autowired
	MemberRepository memberRepository;

	@Transactional
	@Override
	public CommentDto save(Long bno, String id, CommentDto cdto) {
		BoardDto board = boardRepository.findById(bno).orElse(null);
		MemberDto member = memberRepository.findById(id).orElse(null);
		cdto.setBoard(board);
		cdto.setMember(member);
		CommentDto comment = commentRepository.save(cdto);
		return comment;		//Transactional 덕분에 save 호출 없이도 bgroup 업데이트 반영
	}

	@Override
	public void deleteById(CommentDto cdto) {
		commentRepository.deleteById(cdto.getCno());
	}

	@Transactional
	@Override
	public CommentDto findById(CommentDto cdto) {
		CommentDto CommentDto = commentRepository.findById(cdto.getCno()).orElseGet(()->{
			return null;		//없을때 null로 리턴
		});
		return CommentDto;
	}

	@Override
	public Page<CommentDto> findAll(int page, int size, Pageable pageable) {
		return null;
	}

}
