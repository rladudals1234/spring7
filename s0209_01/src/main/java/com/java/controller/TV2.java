package com.java.controller;

import org.springframework.stereotype.Service;

@Service		//TV클래스에 있는 Service 지우고 TV2로
public class TV2 implements Product {
	public String getName() {
		String name = "삼성TV 버전2";
		return name;
	}
}
