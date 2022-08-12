package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Account;
import com.example.demo.exception.ModifyException;
import com.example.demo.repository.AccountRepository;

@Service
public class AccountService {
	@Autowired
	private AccountRepository repository;
	/**
	 * 계좌를 만든다
	 * @param a
	 */
	public void open(Account a) {
		repository.save(a);
	}
	/**
	 * 입금
	 * @param no 계좌번호
	 * @param amount 입금액
	 */
	public void deposit(String no, int amount) throws ModifyException{
		Optional<Account> optA = repository.findById(no);
//		if(!optA.isPresent()) {
//			new IllegalArgumentException(no + "계좌가 없습니다");   //이 3줄 if구문과 같은것이 orElseThrow()
//		}
//		optA.orElseThrow(()->new ModifyException(no+"계좌가 없습니다"));
		optA.orElseThrow(()->new IllegalArgumentException(no+"계좌가 없습니다"));
		//자동으로 메서드를 호출한곳(transfer)으로 떠넘겨줌 
		//없으면 예외를 던져라
		Account a = optA.get();
		int aBalance = a.getAccountBalance(); 
		a.setAccountBalance(aBalance + amount );
		repository.save(a); //트랜잭선 자동시작 -> 정상처리되면 commit()
	}
	/**
	 * 출금한다
	 * @param no 계좌번호
	 * @param amount 출금액
	 * @throws ModifyException 
	 */
	public void withdraw(String no, int amount) throws ModifyException {
		Optional<Account> optA = repository.findById(no);
		Account a = optA.get();
		int aBalance = a.getAccountBalance(); 
		if(amount > aBalance ) {  //잔액이 있어야 출금할 수 있음. 마이너스 통장아님 
			throw new ModifyException("잔액이 부족합니다");
//			throw new IllegalArgumentException("잔액이 부족합니다");
		}
		a.setAccountBalance(aBalance - amount );
		repository.save(a); //트랜잭션자동시작 -> 정상처리된 경우 commit() / 아닌경우 rollback()
	}
	/**
	 * 계좌이체 
	 * @param from 출금계좌번호
	 * @param to  입금계좌번호
	 * @param amount  이체금액
	 * @throws ModifyException
	 */
	@Transactional (rollbackFor = ModifyException.class) //특정예외가 발생하면 자동롤백하라
	//같은 트랜잭션 내에서 2가지 메서드가 동작하도록 
	public void transfer(String from, String to, int amount) throws ModifyException{
		withdraw(from, amount);
		deposit(to, amount); 
	}
}