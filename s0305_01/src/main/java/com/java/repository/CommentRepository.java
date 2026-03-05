package com.java.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.java.dto.BoardDto;
import com.java.dto.CommentDto;

//@Repository			//JpaRepository<사용객체, primary key타입>
public interface CommentRepository extends JpaRepository<CommentDto, Long> {
	
	// 특정 게시글의 댓글을 최신순으로 페이징 (처음 로딩용)
	List<CommentDto> findByBoardBno(Long bno, Pageable pageable);
	
	// 마지막으로 본 cno보다 작은(이전) 데이터들을 가져옴 (최신순)
    List<CommentDto> findByBoardBnoAndCnoLessThan(Long bno, Long lastCno, Pageable pageable);
	
	public BoardDto save(BoardDto bdto);
	
	public void deleteById(Long bno);
}
