package com.example.demo.direction.uni;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="product_jpa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	@Id
	private String prodNo;
	private String prodName;
	private int prodPrice;  //카멜케이스로 작성하면 DB에 자동으로 언더스코어로 생성됨 
}
