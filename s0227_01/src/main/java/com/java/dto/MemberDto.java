package com.java.dto;

import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data				//getter/setter
@AllArgsConstructor	//전체생성자
@NoArgsConstructor	//기본생성자
@Builder			//부분생성자
@Entity				//jpa로 테이블자동생성
public class MemberDto {
	@Id		//primary key 등록
	@Column(nullable = false, length=50)
	private String id;
	
	//@Column(name = "password", nullable = false, length=200)
	@Column(nullable = false, length=200)
	private String pw;
	
	@Column(nullable = false, length=100)
	private String name;
	
	@Column(length=13)
	private String phone;
	
	@Column(length=30)
	private String email;
	
	@Column(length=6)
	@ColumnDefault("'M'")		//문자열일때 ''넣어줌, 숫자인 경우는 ''넣지않음(M->남자)
	private String gender;
	
	@Column(length=100)
	private String hobby;
	
	@CreationTimestamp
	private Timestamp mdate;
}
