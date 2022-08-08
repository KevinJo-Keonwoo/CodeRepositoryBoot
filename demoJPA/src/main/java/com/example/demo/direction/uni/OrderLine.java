package com.example.demo.direction.uni;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="order_line_jpa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderLine {
	@Id
	private Long orderNo;
	
	@ManyToOne
	@JoinColumn(name="order_prod_no", nullable = false)
	//orderLine과 관계를 맺는 orderP와 연결된 order_prod_no는 Null이 될 수 없다 
	//nullable에 false값을 주면 hibernate가 left outer join을 사용하지 않음
	private Product orderP;
	private int orderQuantity;
}
