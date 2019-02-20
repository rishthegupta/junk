package com.capgemini.repo;

import java.math.BigDecimal;

import java.sql.ResultSet;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.capgemini.beans.Customer;

import com.capgemini.exception.MobileNumberAlreadyExistsException;
import com.capgemini.exception.PhoneNumberDoesNotExistException;





public class WalletRepoImpl implements WalletRepo {
	
	
	static EntityManagerFactory factory;
	static EntityManager entityManager;
	static EntityTransaction entityTransaction;
	
	
	//Constructor 
	public WalletRepoImpl()
	{
	
		factory =Persistence.createEntityManagerFactory("wallet");
		entityManager = factory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
	}
	

	
	
	@Override
	public boolean save(Customer customer) throws MobileNumberAlreadyExistsException {
		// TODO Auto-generated method stub
		
		Customer cTemp=entityManager.find(Customer.class, customer.getMobileNumber());
		if(cTemp.getMobileNumber().equals(customer.getMobileNumber()))
		{
			throw new MobileNumberAlreadyExistsException();
		}
		
		entityManager.persist(customer);
		entityTransaction.commit();
		
		return true;
	}


	@Override
	public Customer findCustomer(String mobileNumber) throws PhoneNumberDoesNotExistException {
		// TODO Auto-generated method stub
		
		
		Customer customer=entityManager.find(Customer.class, mobileNumber);

			if(customer==null)
			{
				throw new PhoneNumberDoesNotExistException();
			}
		entityTransaction.commit();

		
		return customer;

}




	@Override
	public boolean updateBalance(String mobileNumber, BigDecimal balance) throws PhoneNumberDoesNotExistException {
		
	
		
		Customer customer=findCustomer(mobileNumber);
		customer.getWallet().setBalance(balance);
		entityTransaction.commit();
		return true;
	}




	@Override
	public boolean updateTransaction(int transactionID, String transactionType, BigDecimal amount, String mobileNumber)
			throws PhoneNumberDoesNotExistException {
		// TODO Auto-generated method stub
		return false;
	}




	@Override
	public ResultSet showTransactions(String mobileNumber) throws PhoneNumberDoesNotExistException {
		// TODO Auto-generated method stub
		return null;
	}




	/*@Override
	public boolean updateTransaction(int transactionID, String transactionType, BigDecimal amount, String mobileNumber) throws PhoneNumberDoesNotExistException {
		try {
			pSSelectAllData.setString(1,mobileNumber);
			if(pSSelectAllData.executeQuery()==null)
			{
				throw new PhoneNumberDoesNotExistException();
			}
			
			pSTransaction.setInt(1, transactionID);
			pSTransaction.setString(2, transactionType);
			pSTransaction.setInt(3, amount.intValue());
			pSTransaction.setString(4, mobileNumber);
			pSTransaction.executeQuery();
			
				return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return false;
	}




	@Override
	public ResultSet showTransactions(String mobileNumber) throws PhoneNumberDoesNotExistException {
		// TODO Auto-generated method stub
		
		try {
			pSSelectAllData.setString(1,mobileNumber);
			if(pSSelectAllData.executeQuery()==null)
			{
				throw new PhoneNumberDoesNotExistException();
			}
			
			pSShowTransaction.setString(1, mobileNumber);
			ResultSet resultSet=pSShowTransaction.executeQuery();
			return resultSet;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
		
	}
	*/
	
	
}
