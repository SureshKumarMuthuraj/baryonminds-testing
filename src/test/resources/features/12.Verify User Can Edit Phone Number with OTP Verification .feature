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
    And user enters value in "Mobile Number_1" text field
    Then user validates "Mobile Number" field value is "Mobile Number_1"
    And User merge the "+44?Mobile Number_1" as "Mobile Number_1"
    And user clicks on "Save" button
    And user clicks on "Reveal full value" icon of "Mobile Number" field
    Then user validates "Mobile Number" field value is "Mobile Number_1"
    And user clicks on "Edit profile" icon
    And user enters value in "Mobile Number_2" text field
    Then user validates "Mobile Number" field value is "Mobile Number_2"
    And User merge the "+44?Mobile Number_2" as "Mobile Number_2"
    And user clicks on "Save" button
    And user clicks on "Reveal full value" icon of "Mobile Number" field
    Then user validates "Mobile Number" field value is "Mobile Number_2"
