package com.java.controller;

//@Service
public class TV implements Product {

	@Override
	public String getName() {
		String name = "삼성TV 버전1";
		return name;
	}

}
