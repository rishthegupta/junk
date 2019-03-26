
@AppTest
Feature: Testing of Whole applicatiuon
 
  Scenario: user needs to book a hotel
			Given User is on login page, now he will login and enter details to book hotel
			When User will enter correct details
			Then He will book hotel and redirected to success page