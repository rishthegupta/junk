package com.capgemini.test;



import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.sql.SQLException;

import org.junit.Test;

import com.capgemini.beans.Customer;
import com.capgemini.exception.FieldCannotBeNullException;
import com.capgemini.exception.InsufficientBalanceException;
import com.capgemini.exception.MobileNumberAlreadyExistsException;
import com.capgemini.exception.PhoneNumberDoesNotExistException;
import com.capgemini.repo.WalletRepo;
import com.capgemini.repo.WalletRepoImpl;

import com.capgemini.service.WalletServiceImpl;

public class WalletTest {

	WalletRepo walletRepo=new WalletRepoImpl();
	WalletServiceImpl walletService=new WalletServiceImpl(walletRepo);



	/*
	 * CREATE ACCOUNT
	 * 1. When correct detail is passed it should create user wallet account
	 * 2. When already existed mobile number is passed it should throw exception
	 * 3. When name passed is null, it should throw exception 
	 * 4. When Mobile Number passed is null, it should throw exception
	 * 5. When Amount passed is null, it should throw exception 
	 */
	
	@Test
	public void WhenAppropriateDataIsPassedAccountShouldBeCreated() throws MobileNumberAlreadyExistsException, FieldCannotBeNullException {
		walletService.createAccount("ABC", "9999999999", new BigDecimal(500));
	}
	
	@Test(expected=com.capgemini.exception.MobileNumberAlreadyExistsException.class)
	public void WhenDuplicateMobileNumberIsPassedItShouldThrowException() throws MobileNumberAlreadyExistsException, FieldCannotBeNullException
	{
		walletService.createAccount("ABC", "9999999999", new BigDecimal(500));
		walletService.createAccount("BCD", "9999999999", new BigDecimal(1000));
	}
	
	@Test(expected=com.capgemini.exception.FieldCannotBeNullException.class)
	public void WhenNamePassedIsNullThrowsException() throws FieldCannotBeNullException, MobileNumberAlreadyExistsException
	{
		walletService.createAccount(null, "9999999999", new BigDecimal(500));
	}
	
	@Test(expected=com.capgemini.exception.FieldCannotBeNullException.class)
	public void WhenMobileNumberPassedIsNullThrowsException() throws FieldCannotBeNullException, MobileNumberAlreadyExistsException
	{
		walletService.createAccount("ABC", null, new BigDecimal(500));
	}
	
	@Test(expected=com.capgemini.exception.FieldCannotBeNullException.class)
	public void WhenAmountPassedIsNullThrowsException() throws FieldCannotBeNullException, MobileNumberAlreadyExistsException
	{
		walletService.createAccount("ABC", "9999999999", null);
	}
	
	//***********************************************************************************************************
	
	/*
	 * Deposit Amount
	 * 1. When Mobile Number Entered is not found/ Account does not exists
	 * 2. When Deposit Amoount with Appropriate Details, deposit amount successfully
	 */
	
	@Test(expected=com.capgemini.exception.PhoneNumberDoesNotExistException.class)
	public void WhenPhoneNumberEnteredIsNotFound() throws PhoneNumberDoesNotExistException, FieldCannotBeNullException, MobileNumberAlreadyExistsException
	{
		walletService.createAccount("ABC", "9999999999", new BigDecimal(500));
		walletService.depositAmount("9912929292",new BigDecimal(1000));
	}
	
	@Test()
	public void WhenCorrectDetailsEnteredItShouldDepositAmountToWallet() throws FieldCannotBeNullException, MobileNumberAlreadyExistsException, PhoneNumberDoesNotExistException 
	{
		walletService.createAccount("ABC", "9999999999", new BigDecimal(500));
		walletService.depositAmount("9999999999",new BigDecimal(1000));
	}
	
	//***********************************************************************************************************
	
	/*
	 * Withdraw Amount
	 * 1. When WithdrawAmount With Appropriate Details, Withdraw amount Successfully
	 * 2. When Worng Mobile Number is Entered, it should throw exception
	 * 3. When balance is insufficient, it should throw Exception
	 */
	
	@Test()
	public void WhenCorrectDetailsEnteredItShouldWithdrawTheAmount() throws FieldCannotBeNullException, MobileNumberAlreadyExistsException, PhoneNumberDoesNotExistException, InsufficientBalanceException
	{
		walletService.createAccount("ABC", "9999999999", new BigDecimal(500));
		walletService.withdrawAmount("9999999999",new BigDecimal(100));
	}
	
	@Test(expected=com.capgemini.exception.PhoneNumberDoesNotExistException.class)
	public void WhenMobileNumberIsIncorrectItShouldWithdrawThrowException() throws FieldCannotBeNullException, MobileNumberAlreadyExistsException, PhoneNumberDoesNotExistException, InsufficientBalanceException
	{
		walletService.createAccount("ABC", "9999999999", new BigDecimal(500));
		walletService.withdrawAmount("9999988888",new BigDecimal(100));
	}
	
	@Test(expected=com.capgemini.exception.InsufficientBalanceException.class)
	public void WhenBalanceIsInsufficientItShouldWithdrawThrowException() throws FieldCannotBeNullException, MobileNumberAlreadyExistsException, PhoneNumberDoesNotExistException, InsufficientBalanceException
	{
		walletService.createAccount("ABC", "9999999999", new BigDecimal(500));
		walletService.withdrawAmount("9999999999",new BigDecimal(1000));
	}
	
	//***********************************************************************************************************
	
	/*
	 * Fund Transfer
	 * 1. When Details are correct It Should transfer Fund.
	 * 2. When remitter's or reciever's mobile number is incorrect, it should through exception.
	 * 3. When remitter's doesn't have enough balance to transfer, it should through exception.
	 */
	
	@Test()
	public void WhenDetailsAreAppropriateFundShouldBeTransfered() throws FieldCannotBeNullException, MobileNumberAlreadyExistsException, InsufficientBalanceException, PhoneNumberDoesNotExistException
	{
		walletService.createAccount("ABC", "9999999991", new BigDecimal(5000));
		walletService.createAccount("BCD", "8888888888", new BigDecimal(1000));
		walletService.fundTransfer("9999999991", "8888888888", new BigDecimal(200));
	}
	
	@Test(expected= com.capgemini.exception.PhoneNumberDoesNotExistException.class)
	public void WhenEitherOfPhoneNumberIsIncorrectItShouldThrowException() throws FieldCannotBeNullException, MobileNumberAlreadyExistsException, InsufficientBalanceException, PhoneNumberDoesNotExistException
	{
		
		walletService.createAccount("ABC", "9999999999", new BigDecimal(5000));
		walletService.createAccount("BCD", "9876543210", new BigDecimal(1000));
		walletService.fundTransfer("9999999999", "9879879807", new BigDecimal(200));
	}
	
	@Test(expected=com.capgemini.exception.InsufficientBalanceException.class)
	public void WhenRemitterDoesntHaveEnoughBalanceToTransfer() throws FieldCannotBeNullException, MobileNumberAlreadyExistsException, InsufficientBalanceException, PhoneNumberDoesNotExistException 
	{
		walletService.createAccount("ABC", "9999999999", new BigDecimal(5000));
		walletService.createAccount("BCD", "9876543210", new BigDecimal(1000));
		walletService.fundTransfer("9999999999", "9876543210", new BigDecimal(20000));
		
	}

	//***********************************************************************************************************
	
	/*
	 * Show Balance
	 * 1. When mobile number is correct it should show the balance
	 * 2. when it is incorrect mobile number, it should through exception
	 */
	
	@Test()
	public void WhenMobileNumberIsCorrectItShouldShowUsersBalanceAndReturnCorrectObject() throws PhoneNumberDoesNotExistException, FieldCannotBeNullException, MobileNumberAlreadyExistsException
	{
		Customer c1=walletService.createAccount("ABC", "9999999999", new BigDecimal(5000));
		assertEquals(c1,walletService.showBalance("9999999999"));
	}
	
	@Test(expected=com.capgemini.exception.PhoneNumberDoesNotExistException.class)
	public void WhenMobileNumberIsIncorrectItShouldThrowException() throws FieldCannotBeNullException, MobileNumberAlreadyExistsException, PhoneNumberDoesNotExistException
	{
		walletService.createAccount("ABC", "9999999999", new BigDecimal(5000));
		walletService.showBalance("9999989999");
	}
	
	//*********************************************************************************************************
	
	
	/*
	 * Show Transactions
	 * 1. When it is called it should Show the Transaction history
	 */
	
	@Test()
	public void WhenShowTransactionCalledItShouldReturnListContainingTransaction() throws InsufficientBalanceException, PhoneNumberDoesNotExistException, FieldCannotBeNullException, MobileNumberAlreadyExistsException
	{
		walletService.createAccount("ABC", "9999999999", new BigDecimal(5000));
		walletService.depositAmount("9999999999", new BigDecimal(5000));
		walletService.withdrawAmount("9999999999", new BigDecimal(2000));
		walletService.showBalance("9999999999");
		walletService.depositAmount("9999999999",new BigDecimal(1000.00));
		try {
			assertEquals(4, ( walletService.showTransactions("9999999999")).getRow());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//***********************************************************************************************************
}