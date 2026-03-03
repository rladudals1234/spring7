package com.java.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.java.dto.CommentDto;

public interface CommentService {
	public CommentDto findById(CommentDto mdto);
	
	public CommentDto save(Long bno, String id, CommentDto mdto);

	public Page<CommentDto> findAll(int page, int size, Pageable pageable);

	public void deleteById(CommentDto cdto);

}
