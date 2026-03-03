package com.java.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.java.dto.MemberDto;

//@Repository			//JpaRepository<사용객체, primary key타입>
public interface MemberRepository extends JpaRepository<MemberDto, String> {
	//1개일때만 null처리하기 위해 Optional로
	public Optional<MemberDto> findByIdAndPw(String id, String pw);		//id와 pw로 where조건으로 검색

	//@Query(value="select * from MemberDto where id=? and pw=?", nativeQuery = true)
	@Query(value="select m.* from MemberDto m where m.id=:id and m.pw=:pw", nativeQuery = true)	//MemberDto클래스 대소문자 잘 맞춰야 함.
	public Optional<MemberDto> selectLogin(@Param("id") String id, @Param("pw") String pw);
	
	public MemberDto save(MemberDto mdto);
	
	public void deleteById(String id);
}
