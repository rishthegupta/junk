package com.capgemini.exception;

@SuppressWarnings("serial")
public class PhoneNumberDoesNotExistException extends Exception {
	
	
	public PhoneNumberDoesNotExistException()
	{
		System.out.println("This user does not exist !");
	}

}
