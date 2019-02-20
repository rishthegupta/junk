package com.capgemini.exception;

@SuppressWarnings("serial")
public class MobileNumberAlreadyExistsException extends Exception {

	public MobileNumberAlreadyExistsException()
	{
		System.out.println("This Mobile number already Exists, Please Enter some different number");
	}
}
