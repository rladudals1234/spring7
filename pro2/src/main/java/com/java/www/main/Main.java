package com.java.www.main;

import org.springframework.beans.factory.annotation.Autowired;

public class Main {

	@Autowired
	Product product;

	public static void main(String[] args) {
		//1. 각각의 클래스를 객체선언해서 사용
		TV tv = new TV();
		tv.name = "삼성TV";
		System.out.println(tv.name);

		//2. 부모의 참조변수로 객체선언해서 사용
		//Product product = new TV();
//		Product product = new TV2();
//		product.name = "삼성TV";
//		System.out.println(product.name);

		//3. 스프링 사용
		//@Autowired
		//Product product;
		//product.name = "삼성TV";
		//System.out.println(product.name);
	}

}
