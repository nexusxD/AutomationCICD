
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce Page
  

  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <username> and password <password>
    When I add the product <productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples:  
      | username 					   | password   | productName     |
      | poggers@gmail.com    |Poggers1    | ZARA COAT 3     |
