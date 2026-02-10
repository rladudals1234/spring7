package com.java.dto;

import java.sql.Timestamp;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data				//getter/setter
@AllArgsConstructor	//전체생성자
@NoArgsConstructor	//기본생성자
@Builder			//부분생성자
public class BoardDto {
	@Min(12)
	private int bno;
	@NotEmpty(message = "필수값")		//필수값이 아닌 경우 에러처리
	private String btitle;
	private String bcontent;
	private String id;
	private int bgroup;
	private int bstep;
	private int bindent;
	private int bhit;
	private String bfile;
	private Timestamp bdate;
	
	public BoardDto(int bno, String btitle, String bcontent, String id) {
		this.bno = bno;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.id = id;
	}
}
