Feature: Verify New User Sign Up, Email OTP Verification, Profile Setup, and First Sign-In Flow

Scenario: Verify New User Sign Up, Email OTP Verification, Profile Setup, and First Sign-In Flow
Given user clicks on "Allow" button
Then user validates that text "Welcome back" is "Displayed"
And user clicks on "Sign up" button
Given user load test data from "accountCreation.json"
Then user validates that text field "Full Name" is "Displayed"
Then user validates that text field "Display Name (optional)" is "Displayed"
Then user validates that text field "Email" is "Displayed"
Then user validates that text field "Password" is "Displayed"
Then user validates that text field "Confirm Password" is "Displayed"
Then user validates that checkbox "I agree to the Terms & Conditions" is "Displayed"
Then user validates that checkbox "I agree to the Privacy Policy" is "Displayed"
Then user validates that checkbox "I'd like push notifications about my swaps and offers (optional)." is "Displayed"
Then user validates that button "Create Account" is "Displayed"
And user enters value in "Full Name" text field
And user enters value in "Display Name (optional)" text field
And user enters value in "Email" text field
And user enters value in "Password" text field
And user enters value in "Confirm Password" text field
And user sets the checkbox "I agree to the Terms & Conditions" to "checked"
And user sets the checkbox "I agree to the Privacy Policy" to "checked"
And user clicks on "Create Account" button
And user gets the "OTP" from the email
And user enters value in "OTP" text field
And user clicks on "Verify" button 
Then user validates "Full Name" field value
Then user validates "Display Name (optional)" field value
And user enters value in "Mobile Number" text field
And user get the existing value for "Email Address" from "Email"
Then user validates "Email Address" field value
And user clicks on "Home Address" lookup field
And user enters value in "Enter Postcode" lookup field
And user selects option in "Enter Postcode" lookup field

And user clicks on "Within 5 miles" button 

And user clicks on "Save" button 
