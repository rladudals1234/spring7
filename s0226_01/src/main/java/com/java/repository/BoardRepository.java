package com.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.dto.BoardDto;

//@Repository			//JpaRepository<사용객체, primary key타입>
public interface BoardRepository extends JpaRepository<BoardDto, Long> {
	//1개일때만 null처리하기 위해 Optional로
	//public Optional<MemberDto> findBybno(Long bno);		//id와 pw로 where조건으로 검색
	
	public BoardDto save(BoardDto bdto);
	
	public void deleteById(Long bno);
}
