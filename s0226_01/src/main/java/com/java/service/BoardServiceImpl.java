package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.dto.BoardDto;
import com.java.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardRepository boardRepository;

	@Transactional
	@Override
	public BoardDto save(BoardDto mdto) {
		return boardRepository.save(mdto);
	}
	
	@Transactional
	@Override
	public BoardDto update(BoardDto mdto) {
		//기존에 있던 데이터에도 null값으로 들어갈 수 있어서 기존에 있던 데이터를 찾아서 넣어주고 일부만 수정
		BoardDto boardDto = boardRepository.findById(mdto.getBno()).orElse(null);
		boardDto.setBno(mdto.getBno());
		return boardRepository.save(boardDto);
	}

	@Override
	public List<BoardDto> findAll() {
		Sort sort = Sort.by(Sort.Order.desc("bgroup"), Sort.Order.asc("bstep"));	//정렬
		return boardRepository.findAll(sort);
	}

	@Override
	public void deleteById(BoardDto bdto) {
		boardRepository.deleteById(bdto.getBno());;
	}

	@Override
	public BoardDto findById(BoardDto bdto) {
		return boardRepository.findById(bdto.getBno()).orElseGet(()->{
			return null;		//없을때 null로 리턴
		});
	}
	
	@Override
	public BoardDto findById(Long bno) {
		return boardRepository.findById(bno).orElseGet(()->{
			return null;		//없을때 null로 리턴
		});
	}

}
