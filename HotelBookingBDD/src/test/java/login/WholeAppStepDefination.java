package login;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;

import com.capgemini.driver.DriverClass;
import com.capgemini.pages.HotelBookingPage;
import com.capgemini.pages.LoginPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class WholeAppStepDefination {
	
	
	
	private static WebDriver driver;
	private static HotelBookingPage hotelBookingPage;
	private static LoginPage loginPage;
	
	
	
	@Given("^User is on login page, now he will login and enter details to book hotel$")
	public void user_is_on_login_page_now_he_will_login_and_enter_details_to_book_hotel() {
	   
		driver=DriverClass.getDriver();
		hotelBookingPage=new HotelBookingPage(driver);
		loginPage=new LoginPage(driver);
	}

	@When("^User will enter correct details$")
	public void user_will_enter_correct_details() {
	   
		loginPage.fillLoginDetails("capgemini", "capg1234");
		loginPage.clickLogin();
		hotelBookingPage.fillPersonalDettails("Rishabh", "Kumar", "abc@xyz.com", "9898589589", "A Block", "Pune","Maharashtra", "2");
		hotelBookingPage.fillPaymentDetails("Rishabh Kumar","23246555677665673", "645", "08", "23");
		    
		
	}

	@Then("^He will book hotel and redirected to success page$")
	public void he_will_book_hotel_and_redirected_to_success_page() {
	  
		hotelBookingPage.confirmButton();
		assertEquals("Payment Details", driver.getTitle());
	}


}
