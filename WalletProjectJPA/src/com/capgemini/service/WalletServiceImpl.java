package com.capgemini.service;


import java.math.BigDecimal;
import java.sql.ResultSet;

import com.capgemini.beans.Customer;

import com.capgemini.beans.Wallet;
import com.capgemini.exception.FieldCannotBeNullException;
import com.capgemini.exception.InsufficientBalanceException;
import com.capgemini.exception.MobileNumberAlreadyExistsException;
import com.capgemini.exception.PhoneNumberDoesNotExistException;
import com.capgemini.repo.WalletRepo;





public class WalletServiceImpl implements WalletService  {
	
	static int transactionID=1;
	WalletRepo walletRepo;
	
	// Constructor
	public WalletServiceImpl(WalletRepo walletRepo) {
		super();
		this.walletRepo = walletRepo;
	}

	
	// ********** OVERRIDDEN METHODS *********
	//Method for creating and addinf account to hashmap
	@Override
	public Customer createAccount(String name, String mobileNumber, BigDecimal amount) throws FieldCannotBeNullException, MobileNumberAlreadyExistsException {
		
		
		if(name==null || mobileNumber==null ||amount==null )
		{
			throw new FieldCannotBeNullException();
		}
		Wallet wallet=new Wallet();
		wallet.setBalance(amount);
		Customer customer =new Customer();
		
		customer.setName(name);
		customer.setMobileNumber(mobileNumber);
		customer.setWallet(wallet);
		
		if(walletRepo.save(customer))
			return customer;
		
		return null;
	}

	//Method for showing balance
	@Override
	public Customer showBalance(String mobileNumber) throws PhoneNumberDoesNotExistException {
		
		Customer customer=walletRepo.findCustomer(mobileNumber);
	
		
		//UPDATE TRANSACTION TABLE
		walletRepo.updateTransaction(transactionID++, "SHOWN BALANCE", customer.getWallet().getBalance(), mobileNumber);
		return customer;
	}

	
	//Method for deposit amount
	@Override
	public Customer depositAmount(String mobileNumber, BigDecimal amount) throws PhoneNumberDoesNotExistException {
		
		//System.out.println(walletRepo.findCustomer("9999999999"));
		Customer customer=walletRepo.findCustomer(mobileNumber);
		if(customer==null)
		{
			//System.out.println(customer);
			throw new NullPointerException();
		}
		customer.getWallet().setBalance(customer.getWallet().getBalance().add(amount));
		
		//Update the Balance in DATABASE
		walletRepo.updateBalance(mobileNumber, customer.getWallet().getBalance());
		
		//UPDATE TRANSACTION TABLE
		walletRepo.updateTransaction(transactionID++, "Balance Deposited", customer.getWallet().getBalance(), mobileNumber);
		
		return customer;
	}

	//Method for amount Withdrawl
	@Override
	public Customer withdrawAmount(String mobileNumber, BigDecimal amount) throws InsufficientBalanceException, PhoneNumberDoesNotExistException {
		// TODO Auto-generated method stub
		Customer customer=walletRepo.findCustomer(mobileNumber);
		
		//Checking Exceptions
		if(customer.getWallet().getBalance().compareTo(amount)==-1)
		{
			throw new InsufficientBalanceException();
		}
		
		customer.getWallet().setBalance(customer.getWallet().getBalance().subtract(amount));
		
		//Update the Balance in DATABASE
		walletRepo.updateBalance(mobileNumber, customer.getWallet().getBalance());
		
		//UPDATE TRANSACTION TABLE
		walletRepo.updateTransaction(transactionID++, "Balance Withdrawl", customer.getWallet().getBalance(), mobileNumber);
		
		return customer;
	}

	
	//Method for fund Transfer
	@Override
	public Customer fundTransfer(String sourceMobileNumber, String targetMobileNumber, BigDecimal amount) throws InsufficientBalanceException, PhoneNumberDoesNotExistException {
		// TODO Auto-generated method stub
		Customer customer1=walletRepo.findCustomer(sourceMobileNumber);
		Customer customer2=walletRepo.findCustomer(targetMobileNumber);
		
		//Checking Exception
		if(customer1.getWallet().getBalance().compareTo(amount)==-1)
		{
			throw new InsufficientBalanceException();
		}
		
		customer1.getWallet().setBalance(customer1.getWallet().getBalance().subtract(amount));
		customer2.getWallet().setBalance(customer2.getWallet().getBalance().add(amount));
		
		//Update the Balance in DATABASE
		walletRepo.updateBalance(sourceMobileNumber, customer1.getWallet().getBalance());
		walletRepo.updateBalance(targetMobileNumber, customer2.getWallet().getBalance());
		
		
		
		//UPDATE TRANSACTION TABLE
		//Remitter's account
		walletRepo.updateTransaction(transactionID++, "Fund Transfered", customer1.getWallet().getBalance(), sourceMobileNumber);
		//Reciever's account
		walletRepo.updateTransaction(transactionID++, "Fund Recieved", customer2.getWallet().getBalance(), targetMobileNumber);
		
		return customer1;
	}
	
	//Method for Show Transaction
	@Override
	public ResultSet showTransactions(String mobileNumber) throws PhoneNumberDoesNotExistException {
		
		return walletRepo.showTransactions(mobileNumber);
	}
	
	

}
