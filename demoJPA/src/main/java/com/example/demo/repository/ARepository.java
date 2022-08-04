package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.A;

public interface ARepository extends CrudRepository<A, String> {
				//a.java 의 @ID어노테이션 값을 두번째매개변수에 넣기
	List<A> findByA4(String a4); //a4멤버변수 기준으로 검색하겠다 
	//findABy  queryBy countBy
	
	List<A> findByA4Like(String word);

}
