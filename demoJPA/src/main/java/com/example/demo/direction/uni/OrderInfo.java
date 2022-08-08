package com.example.demo.direction.uni;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="order_info_jpa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfo {
	@Id
	private Long orderNo;
	private Date orderDt;
	private String orderId;
	
	@OneToMany
//	@JoinColumn(name = "order_line")
	private List<OrderLine>lines;

}
