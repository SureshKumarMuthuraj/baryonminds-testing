Feature: Verify Create Listing Flow for a Signed-In User

Scenario: Verify Create Listing Flow for a Signed-In User
Given user clicks on "Allow" button
Then user validates that text "Welcome back" is "Displayed"
Given user load test data from "login.json"
And user enters value in "Email" text field
And user enters value in "Password" text field
And user clicks on "Sign In" button
Then user validates that text "Items Feed" is "Displayed"
Then user validates that icon "Profile" is "Displayed"
#Verify Create Listing
Given user clicks on "Create" icon
Then user validates that text "Account required" is "Not Displayed"
Then user validates that text "Create Listing" is "Displayed"
And user clicks on "Upload Photos" button
