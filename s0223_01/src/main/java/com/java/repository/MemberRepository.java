package com.java.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.java.dto.MemberDto;

//@Repository			//JpaRepository<사용객체, primary key타입>
public interface MemberRepository extends JpaRepository<MemberDto, String> {
	public Optional<MemberDto> findByIdAndPw(String id, String pw);		//id와 pw로 where조건으로 검색

	@Query(value="select * from MemberDto where id=? and pw=?", nativeQuery = true)
	public Optional<MemberDto> selectLogin(String id, String pw);
	
	public MemberDto save(MemberDto mdto);
}
