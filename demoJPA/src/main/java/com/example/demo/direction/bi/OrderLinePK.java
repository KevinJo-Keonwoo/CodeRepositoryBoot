package com.example.demo.direction.bi;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode //두 복합키가 결합한 것이 같은 가 확인해야함 
public class OrderLinePK implements Serializable {
	private static final long serialVersionUID = 1L;
	private OrderInfo orderInfo;
	private Product orderP;
}
