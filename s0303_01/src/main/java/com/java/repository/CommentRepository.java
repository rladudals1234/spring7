package com.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.dto.BoardDto;
import com.java.dto.CommentDto;

//@Repository			//JpaRepository<사용객체, primary key타입>
public interface CommentRepository extends JpaRepository<CommentDto, Long> {
	
	public BoardDto save(BoardDto bdto);
	
	public void deleteById(Long bno);
}
