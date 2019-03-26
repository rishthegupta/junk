package login;


import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;

import com.capgemini.driver.DriverClass;
import com.capgemini.pages.LoginPage;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginPageSteps {

	private static WebDriver driver;
	private static LoginPage loginPage;
	
	@Given("^User want to book room in hotel through user web portal$")
	public void user_want_to_book_room_in_hotel_through_user_web_portal() {
		System.out.println("reached given");
		driver=DriverClass.getDriver();
		loginPage=new LoginPage(driver);

	}

	@When("^User enters corrrect credentials$")
	public void user_enters_corrrect_credentials()  {
		
		System.out.println("Reached when");
	    loginPage.fillLoginDetails("capgemini", "capg1234");
	    
	   
	}

	@Then("^he should be redirected to details filling page$")
	public void he_should_be_redirected_to_details_filling_page()  {
	    loginPage.clickLogin();
	    assertEquals("Hotel Booking", driver.getTitle());
	    
	   
	}
	
}
