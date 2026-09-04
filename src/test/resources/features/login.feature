Feature: Login

Scenario: Successful login
Given user load test data from "login.json"
Given user clicks on "Allow" button
And user enters value "Email"
And user enters value "Password"
And user clicks on "Sign In" button
And user clicks on "Toys & Gaming" button
Then user validates that content description "Sony Wireless game controller" is "Displayed"
And user clicks on "Action Figures & Character Toys" button
Then user validates that content description "BRIO wooden toy train set with station and accessories" is "Displayed"
Then user validates that content description "Sony wireless game controller" is "Not Displayed"


