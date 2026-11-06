Feature: Verify User Can Edit Phone Number with OTP Verification

  Scenario: Verify User Can Edit Phone Number with OTP Verification
    Given user clicks on "Allow" button
    Given user load test data from "editProfile.json"
    And user enters value in "Email_1" text field
    And user enters value in "Password" text field
    And user clicks on "Sign In" button
            And User clicks on "Skip" button as optional

    And user clicks on "Profile" icon
    And user clicks on "Edit profile" icon
    And user clicks on "Home Address" lookup field
    And user enters value in "Enter Postcode_1" lookup field
    And user selects option in "Enter Postcode_1" lookup field
    And user clicks on "Save" button
    And user clicks on "Reveal full value" icon of "Home Address" field
    Then user validates "Home Address" field value contains "Enter Postcode_1"
    And user clicks on "Edit profile" icon
    And user clicks on "Home Address" lookup field
    And user enters value in "Enter Postcode_2" lookup field
    And user selects option in "Enter Postcode_2" lookup field
    And user clicks on "Save" button
    And user clicks on "Reveal full value" icon of "Home Address" field
    Then user validates "Home Address" field value contains "Enter Postcode_2"
