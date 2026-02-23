package com.java.dto;

import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data				//getter/setter
@AllArgsConstructor	//전체생성자
@NoArgsConstructor	//기본생성자
@Builder			//부분생성자
@Entity
public class BoardDto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bno;
	
	@Column(nullable=false, length=2000)
	private String btitle;
	
	@Lob			//대용량 문자열 - 오라클의 CLOB
	private String bcontent;
	
	//OneToMany(여러사람이 한 게시글 등록), ManyToMany
	@ManyToOne			//한명의 user가 여러개의 게시글 등록가능
	@JoinColumn(name="id")
	private MemberDto member;
	
	@ColumnDefault("0")
	private Integer bgroup;
	
	@ColumnDefault("0")
	private Integer bstep;
	
	@ColumnDefault("0")
	private Integer bindent;
	
	@ColumnDefault("0")
	private Integer bhit;
	
	@Column(length=1000)
	private String bfile;
	
	@CreationTimestamp
	private Timestamp bdate;
}
