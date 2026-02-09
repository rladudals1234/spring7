package com.java.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data				//getter,setter
@NoArgsConstructor	//기본생성자
@AllArgsConstructor	//전체생성자
public class Member {
	private String id;
	private String pw;
	private String name;
}
