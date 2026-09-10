Feature: Verify Guest Mode access and authentication restrictions

Scenario: Verify Guest Mode access and authentication restrictions
Given user clicks on "Allow" button
And user clicks on "Continue as Guest" button
Then user validates that text "Items Feed" is "Displayed"
Then user validates that button "Home" is "Displayed"
#Verify Swap Products
And user clicks on "Giveaway" button
And user clicks on "Swap" button
Then user validates that popup "Account required" is "Not Displayed"
Then user validates that icon "Open expanded item view" is "Displayed"
Then user validates that icon "Filters" is "Displayed"
#Verify Giveaway Products
And user clicks on "Giveaway" button
Then user validates that popup "Account required" is "Not Displayed"
Then user validates that icon "Open expanded item view" is "Displayed"
Then user validates that icon "Filters" is "Displayed"
#Navigate to default landing page - Swap
And user clicks on "Swap" button
#Verify My Listing
Given user clicks on "My Listings" button
Then user validates that text "Account required" is "Displayed"
Then user validates that text "You need an account to do this. Sign Up or Log In to continue." is "Displayed"
Then user validates that button "Log In" is "Displayed"
Then user validates that button "Sign Up" is "Displayed"
And user taps outside the "Account required" popup
Then user validates the app not navigated to a new page
#Verify Chat
Given user clicks on "Chat" button
Then user validates that text "Account required" is "Displayed"
Then user validates that text "You need an account to do this. Sign Up or Log In to continue." is "Displayed"
Then user validates that button "Log In" is "Displayed"
Then user validates that button "Sign Up" is "Displayed"
And user taps outside the "Account required" popup
Then user validates the app not navigated to a new page
#Verify My Revaliyo
Given user clicks on "My Revaliyo" button
Then user validates that text "Account required" is "Displayed"
Then user validates that text "You need an account to do this. Sign Up or Log In to continue." is "Displayed"
Then user validates that button "Log In" is "Displayed"
Then user validates that button "Sign Up" is "Displayed"
And user taps outside the "Account required" popup
Then user validates the app not navigated to a new page
#Verify Create Listing
Given user clicks on "Create" icon
Then user validates that text "Account required" is "Displayed"
Then user validates that text "You need an account to do this. Sign Up or Log In to continue." is "Displayed"
Then user validates that button "Log In" is "Displayed"
Then user validates that button "Sign Up" is "Displayed"
And user taps outside the "Account required" popup
Then user validates the app not navigated to a new page
#Verify Sign Up Navigation
Given user clicks on "Create" icon
And user clicks on "Sign Up" button
Then user validates that text "Sign up" is "Displayed"
Then user validates that text "Full Name" is "Displayed"
Then user validates that text field "Display Name (optional)" is "Displayed"
Then user validates that text field "Email" is "Displayed"
Then user validates that text field "Password" is "Displayed"
Then user validates that text field "Confirm Password" is "Displayed"
Then user validates that button "Create Account" is "Displayed"
Given user clicks on "Back" icon
#Verify Login Navigation 
Given user clicks on "Create" icon
And user clicks on "Log In" button
Then user validates that text "Welcome back" is "Displayed"
Then user validates that text field "Email" is "Displayed"
Then user validates that text field "Password" is "Displayed"
Then user validates that button "Sign In" is "Displayed"
Given user load test data from "login.json"
And user enters value in "Email" text field
And user enters value in "Password" text field
And user clicks on "Sign In" button
Then user validates that text "Items Feed" is "Displayed"
Then user validates that icon "Profile" is "Displayed"
