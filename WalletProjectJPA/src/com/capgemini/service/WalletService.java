package com.capgemini.service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import com.capgemini.beans.Customer;

import com.capgemini.exception.FieldCannotBeNullException;
import com.capgemini.exception.InsufficientBalanceException;
import com.capgemini.exception.MobileNumberAlreadyExistsException;
import com.capgemini.exception.PhoneNumberDoesNotExistException;

public interface WalletService {
	
	public Customer createAccount(String name, String mobileNumber, BigDecimal amount) throws MobileNumberAlreadyExistsException, FieldCannotBeNullException;
	public Customer showBalance(String mobileNumber) throws PhoneNumberDoesNotExistException;
	public Customer depositAmount(String mobileNumber , BigDecimal amount) throws PhoneNumberDoesNotExistException;
	public Customer withdrawAmount(String mobileNumber , BigDecimal amount) throws InsufficientBalanceException, PhoneNumberDoesNotExistException;
	public Customer fundTransfer(String sourceMobileNumber ,String targetMobileNumber, BigDecimal amount) throws InsufficientBalanceException, PhoneNumberDoesNotExistException;
	public ResultSet showTransactions(String mobileNumber) throws PhoneNumberDoesNotExistException;
}
