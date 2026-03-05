package com.java.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	public Map<String, Object> findAll(int size, Long bno, Long lastCno) {
		// 1. 서비스 단에서 정렬 기준 정의 (최신순)
	    Sort sort = Sort.by(Sort.Direction.DESC, "cno");
	    Pageable pageable = PageRequest.of(0, size, sort);
		List<CommentDto> list;
		// Service에서 정렬 및 조회 로직 분기 처리
        if (lastCno == null || lastCno == 0) {
            // [최초 조회] 가장 최신글
            list = commentRepository.findByBoardBno(bno, pageable);
        } else {
            // [스크롤 or 더보기 조회] 마지막으로 본 cno보다 작은(과거) 데이터들을 가져옴
            list = commentRepository.findByBoardBnoAndCnoLessThan(bno, lastCno, pageable);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("isLast", list.size() < size); // 더 가져올 데이터가 있는지 판별
        return map;
	}

}
