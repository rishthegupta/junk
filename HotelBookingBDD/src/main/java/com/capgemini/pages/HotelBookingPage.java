package com.capgemini.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class HotelBookingPage {
	
	
	private static WebDriver driver;
	
	//Personal Details
	@FindBy(xpath="//*[@id=\"txtFirstName\"]")
	WebElement firstNameBox;
	@FindBy(xpath="//*[@id=\"txtLastName\"]")
	WebElement lastNameBox;
	@FindBy(xpath="//*[@id=\"txtEmail\"]")
	WebElement emailBox;
	@FindBy(xpath="//*[@id=\"txtPhone\"]")
	WebElement mobileNumberBox;
	@FindBy(xpath="/html/body/div/div/form/table/tbody/tr[6]/td[2]/textarea")
	WebElement addressBox;
	@FindBy(xpath="/html/body/div/div/form/table/tbody/tr[7]/td[2]/select")
	WebElement citySelect;
	@FindBy(xpath="/html/body/div/div/form/table/tbody/tr[8]/td[2]/select")
	WebElement stateSelect;
	@FindBy(xpath="/html/body/div/div/form/table/tbody/tr[10]/td[2]/select")
	WebElement noOfGuestSelect;
	
	//Payment Details
	@FindBy(xpath="//*[@id=\"txtCardholderName\"]")
	WebElement cardHoldername;
	@FindBy(xpath="//*[@id=\"txtDebit\"]")
	WebElement cardNumberBox;
	@FindBy(xpath="//*[@id=\"txtCvv\"]")
	WebElement cvvDetails;
	@FindBy(xpath="//*[@id=\"txtMonth\"]")
	WebElement expirationMonthBox;
	@FindBy(xpath="//*[@id=\"txtYear\"]")
	WebElement expirationYearBox;
	
	//Button
	@FindBy(xpath="//*[@id=\"btnPayment\"]")
	WebElement confirmButton;
	
	 public HotelBookingPage(WebDriver driver) {
		super();
		this.driver = driver;
		driver.get("C:\\Users\\RKUMA301\\Documents\\BDD\\HotelBookingBDD\\HTMLPages\\hotelbooking.html");
		PageFactory.initElements(driver, this);
	}
	 
	 public void fillPersonalDettails(String firstName, String lastName, String email, String mobileNumber, String address, String city, String state, String noOfGuests)
	 {
		 firstNameBox.sendKeys(firstName);
		 lastNameBox.sendKeys(lastName);
		 emailBox.sendKeys(email);
		 mobileNumberBox.sendKeys(mobileNumber);
		 addressBox.sendKeys(address);
		 new Select(citySelect).selectByVisibleText(city);
		 new Select(stateSelect).selectByVisibleText(state);
		 new Select(noOfGuestSelect).selectByVisibleText(noOfGuests);
	 }
	 
	 public void fillPaymentDetails(String cardHolderName, String cardNumber, String CVV, String expirationMonth, String ExpirationYear)
	 {
		 cardHoldername.sendKeys(cardHolderName);
		 cardNumberBox.sendKeys(cardNumber);
		 cvvDetails.sendKeys(CVV);
		 expirationMonthBox.sendKeys(expirationMonth);
		 expirationYearBox.sendKeys(ExpirationYear);
	 }
	 
	 public void confirmButton()
	 {
		 confirmButton.click();
	 }
	 

	 
	 

	

	

}
