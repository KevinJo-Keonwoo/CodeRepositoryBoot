package com.example.demo.direction.bi;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="order_line_jpa")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@IdClass(OrderLinePK.class)
public class OrderLine {
	@Id
//	@Column(name="order_line_no ")
	@ManyToOne
	@JoinColumn(name ="order_line_no")
	private OrderInfo orderInfo;
//	private Long orderNo;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "order_prod_no", nullable = false)
	private Product orderP;
	
	private int orderQuantity;
}
