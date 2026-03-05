package com.java.dto;

import java.sql.Timestamp;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class CommentDto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cno;
	
	@Lob
	private String ccontent;
	
	@ManyToOne
	@JoinColumn(name="bno")
	@JsonIgnore
	private BoardDto board;
	
	@ManyToOne
	@JoinColumn(name="id")
	private MemberDto member;
	
	//@CreationTimestamp
	@UpdateTimestamp
	private Timestamp cdate;
}
