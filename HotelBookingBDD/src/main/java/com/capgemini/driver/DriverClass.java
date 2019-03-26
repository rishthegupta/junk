package com.capgemini.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverClass {
	
	public static WebDriver getDriver()
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\RKUMA301\\Documents\\Selenium jar\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		System.out.println("Base Class "+driver);
		return driver;
	}

}
