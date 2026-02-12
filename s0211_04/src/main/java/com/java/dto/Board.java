package com.java.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data				//getter/setter
@AllArgsConstructor	//전체생성자
@NoArgsConstructor	//기본생성자
@Builder			//부분생성자
@Alias("board")
public class Board {
	private Integer bno;
	private String btitle;
	private StringBuffer bcontent;
	private String id;
	private Integer bgroup;
	private Integer bstep;
	private Integer bindent;
	private Integer bhit;
	private String bfile;
	private Timestamp bdate;
}
