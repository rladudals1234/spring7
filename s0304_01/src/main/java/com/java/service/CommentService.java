package com.java.service;

import java.util.Map;

import com.java.dto.CommentDto;

public interface CommentService {
	public CommentDto findById(CommentDto mdto);
	
	public CommentDto save(Long bno, String id, CommentDto mdto);

	public Map<String, Object> findAll(int size, Long bno, Long lastCno);

	public void deleteById(CommentDto cdto);
}
