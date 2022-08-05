package com.my.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Component

@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
@EqualsAndHashCode(of = {"boardNo"})
@ToString
//@Data

@Entity //DTO와 ENTITY를 함께만든것임 
@Table(name = "board_jpa") //이 이름의 JPA 테이블을 사용하기 
@SequenceGenerator(name = "boardjpa_seq_generator", //밑의 GeneratedValue에서 찾아올 이름 
					sequenceName = "board_jpa_seq", //오라클에 생성될 시퀀스 이름 
					initialValue = 1, 
					allocationSize = 1)
@DynamicInsert
@DynamicUpdate
public class Board {
	@Transient //계층형쿼리를 만들때만 쓰이는 pseudo컬럼이기에 ORM에서 제외하기 
	private int level; //Startwith 같은 함수를 썼을때만 제공
	
	@Id //PK역할 담당 
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "boardjpa_seq_generator" )
	@Column(name = "board_no")
	private Long boardNo; //게시글번호 
	
	@Column(name="board_parent_no")
//	@ColumnDefault(value = "0")
	private Long boardParentNo;
	
	@Column(name="board_title")
	private String boardTitle;
	
	@Column(name="board_content")
	private String boardContent;
	
	@JsonFormat(pattern = "yy/MM/dd", timezone = "Asia/Seoul")
	@Column(name="board_dt")
	@ColumnDefault(value = "SYSDATE")
	private Date boardDt;
	
	@Column(name="board_id")
	private String boardId; 
	
	@Column(name="board_viewcount")
//	@ColumnDefault(value = "0")
	private Integer boardViewcount;
}
