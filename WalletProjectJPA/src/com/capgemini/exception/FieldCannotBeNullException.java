package com.capgemini.exception;

@SuppressWarnings("serial")
public class FieldCannotBeNullException extends Exception {

	public FieldCannotBeNullException()
	{
		System.out.println("Enter Some Data, this cannot be null");
	}
}
