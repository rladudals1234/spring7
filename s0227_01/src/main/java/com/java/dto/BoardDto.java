package com.java.dto;

import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SequenceGenerator(
		name="boardDto_seq_generater"	//generator 이름
		,sequenceName = "boardDto_seq"	//오라클 테이블에서 시퀀스이름
		,initialValue = 101				//시작번호
		,allocationSize = 1				//메모리 할당범위
)
@Data				//getter/setter
@AllArgsConstructor	//전체생성자
@NoArgsConstructor	//기본생성자
@Builder			//부분생성자
@Entity
public class BoardDto {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="boardDto_seq_generater")	//oracle 시퀀스
	private Long bno;
	
	@Column(nullable=false, length=2000)
	private String btitle;
	
	@Lob			//대용량 문자열 - 오라클의 CLOB
	private String bcontent;
	
	//OneToMany(여러사람이 한 게시글 등록), ManyToMany(여러명회원이 여러개 글쓰기 가능), ManyToOne(한명의 회원은 여러개의 게시글 작성가능)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id")
	private MemberDto member;
	
	@ColumnDefault("0")
	private Long bgroup;
	
	@ColumnDefault("0")
	private Long bstep;
	
	@ColumnDefault("0")
	private Long bindent;
	
	@ColumnDefault("0")
	private Integer bhit;
	
	@Column(length=1000)
	private String bfile;
	
	@CreationTimestamp
	private Timestamp bdate;
}
