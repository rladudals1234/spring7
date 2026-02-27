package com.java.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.java.dto.BoardDto;

//@Repository			//JpaRepository<사용객체, primary key타입>
public interface BoardRepository extends JpaRepository<BoardDto, Long> {
	//1개일때만 null처리하기 위해 Optional로
	//public Optional<MemberDto> findBybno(Long bno);		//id와 pw로 where조건으로 검색
	
	public BoardDto save(BoardDto bdto);
	
	public void deleteById(Long bno);

	@Modifying
	@Transactional
	@Query(value = "update boardDto set bstep=bstep+1 where bgroup=:bgroup and bstep>:bstep" ,nativeQuery = true)
	public void replyBstepUp(@Param("bgroup") Long bgroup, @Param("bstep") Long bstep);

	@Query(value = "select * from boarddto where bno=( select pre_bno from\r\n"
			+ "(select bno,lag(bno,1,-1) over(order by bgroup desc,bstep asc) pre_bno from boarddto) where bno=:bno)" ,nativeQuery = true)
	public Optional<BoardDto> findByPre(@Param("bno") Long bno);
	
	@Query(value = "select * from boarddto where bno=( select pre_bno from\r\n"
			+ "(select bno,lead(bno,1,-1) over(order by bgroup desc,bstep asc) pre_bno from boarddto) where bno=:bno)" ,nativeQuery = true)
	public Optional<BoardDto> findByNext(@Param("bno") Long bno);
}
