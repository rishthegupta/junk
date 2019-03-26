package com.capgemini.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	@FindBy(xpath="//*[@id=\"mainCnt\"]/div/div[1]/form/table/tbody/tr[2]/td[2]/input")
	WebElement userNameTextBox;
	
	@FindBy(xpath="//*[@id=\"mainCnt\"]/div/div[1]/form/table/tbody/tr[3]/td[2]/input")
	WebElement passwordTextBox;
	
	
	@FindBy(xpath="//*[@id=\"mainCnt\"]/div/div[1]/form/table/tbody/tr[4]/td[2]/input")
	WebElement loginButton;

	public LoginPage(WebDriver driver) {
		super();
		this.driver = driver;
		driver.get("C:\\Users\\RKUMA301\\Documents\\BDD\\HotelBookingBDD\\HTMLPages\\login.html");
		PageFactory.initElements(driver,this);
	}
	
	

	public void fillLoginDetails(String userName, String password)
	{
		userNameTextBox.sendKeys(userName);
		passwordTextBox.sendKeys(password);
	}
	
	
	public HotelBookingPage clickLogin()
	{
		loginButton.click();
		return new HotelBookingPage(driver);
	}

	
	
}
