package com.capgemini.repo;

import java.math.BigDecimal;
import java.sql.ResultSet;

import com.capgemini.beans.Customer;
import com.capgemini.exception.MobileNumberAlreadyExistsException;
import com.capgemini.exception.PhoneNumberDoesNotExistException;

public interface WalletRepo {
	
	public boolean save(Customer customer) throws MobileNumberAlreadyExistsException;
	public Customer findCustomer(String mobileNumber) throws PhoneNumberDoesNotExistException;
	public boolean updateBalance(String mobileNumber, BigDecimal balance) throws PhoneNumberDoesNotExistException;
	public boolean updateTransaction(int transactionID, String transactionType, BigDecimal amount, String mobileNumber) throws PhoneNumberDoesNotExistException;
	public ResultSet showTransactions(String mobileNumber) throws PhoneNumberDoesNotExistException;
}
