package com.my.exception;

public class FindException extends Exception{
	public FindException() {
		super();
	}
	public FindException(String product) {
		super(product);
	}
}
