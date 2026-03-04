package com.java.dto;

import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data				//getter/setter
@AllArgsConstructor	//전체생성자
@NoArgsConstructor	//기본생성자
@Builder			//부분생성자
@Entity
public class EventDto {
	@Id
	@Column(length = 25)
	private Long id;                // 이벤트 ID
    
	@Column(length = 100)
	private String title;           // 제목
    
    @Lob			//대용량 문자열 - 오라클의 CLOB
    private String content;         // 내용
	
	@Column(length = 100)
    private String writer;          // 작성자
    
	@Lob			//대용량 문자열 - 오라클의 CLOB
    private String thumbnailUrl;   // 썸네일 이미지
	
    @ColumnDefault("'진행중'")
	private String status;         // 진행중, 종료, 예정
    
    @ColumnDefault("0")
    private int viewCount;         // 조회수

    @CurrentTimestamp
    private Timestamp startDate; // 이벤트 시작일
    
    @CurrentTimestamp
    private Timestamp endDate;   // 이벤트 종료일
    
    @CreationTimestamp
    private Timestamp crdt; // 작성일
    
    @UpdateTimestamp
    private Timestamp updt; // 수정일
}
