

@login
Feature: Test for login page of Hotel Booking

  
  Scenario: When User will enter credentials
    Given User want to book room in hotel through user web portal 
    When User enters corrrect credentials
    Then he should be redirected to details filling page 

