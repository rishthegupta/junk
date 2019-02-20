package com.capgemini.exception;

@SuppressWarnings("serial")
public class InsufficientBalanceException extends Exception {

	public InsufficientBalanceException() {
		System.out.println("Insufficient Balance");
	}
}
