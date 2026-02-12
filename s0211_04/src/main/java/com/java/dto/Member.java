package com.java.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data				//getter/setter
@AllArgsConstructor	//전체생성자
@NoArgsConstructor	//기본생성자
@Builder			//부분생성자
@Alias("member")
public class Member {
	private String id;
	private String pw;
	private String name;
	private String phone;
	private String email;
	private String gender;
	private String hobby;
}
