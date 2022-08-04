package com.example.demo.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "a_tbl")  //테이블 이름 설정
@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
@ToString
public class A {
	@Id
	private String a1;
	
	@javax.persistence.Column(name = "a2_c", precision = 5, scale = 2, nullable = true )  //컬럼명, 자릿수, 소숫점 2자리까지
	private BigDecimal a2;
	
	@CreationTimestamp  //Insert시 시간
//	@UpdateTimestamp  //Update시 시간
	private Date a3;
	
	//기본값 
	@ColumnDefault(value = "'hello'")
	@Column(name="a4_c", length=10)
	private String a4;
	
	@Transient
	private String a5;
}
