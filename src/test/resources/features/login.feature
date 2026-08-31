Feature: Login

Scenario: Successful login
Given user load test data from "login.json"
Given user clicks on "Allow" button
And user enters value "Email"
And user enters value "Password"
And user clicks on "Sign In" button
And user clicks on "Electronics & Tech" button
Then user validates that content description "Samsung Android tablet with stylus" is "Displayed"
And user clicks on "Home & Garden" button
Then user validates that content description "Logik white oscillating tower fan" is "Displayed"
Then user validates that content description "Samsung Android tablet with stylus" is "Not Displayed"


