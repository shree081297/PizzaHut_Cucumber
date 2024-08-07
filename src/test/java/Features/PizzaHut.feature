Feature: This feature will be used to automate PizzaHut Website

  Scenario: This scenario will be used to Place the Order
    Given I have launched the application
    When I enter the location as "pune railway station, Agarkar Nagar, Pune, Maharashtra, India"
    And I select the very first suggestion from the list
       Then I should land on the Deals page
    And I select the tab as "Pizzas"
    And I add "Schezwan Margherita" to the basket
      And I note down the price displayed on the screen
  #   Then I should see the pizza "Personal Schezwan Margherita" is added to the cart
    And price is also displayed correctly
    And I click on the Checkout button
      Then I should be landed on the secured checkout page
    And I enter the personal details 
      | Name     | Mobile     | Email         |
      | Srikanth | 9999999999 | abc@gmail.com |
    And I enter the address details
      | 123 Main Street |  
      | Some Landmark   |  
