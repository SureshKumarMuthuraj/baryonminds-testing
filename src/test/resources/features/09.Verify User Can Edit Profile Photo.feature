Feature: Verify User Can Edit Profile Photo

  Scenario: Verify User Can Edit Profile Photo
      Given user load test data from "editProfile.json"

    Given user clicks on "Allow" button
    Then user validates that text "Welcome back" is "Displayed"
    Given user load test data from "editProfile.json"
    And user enters value in "Email_1" text field
    And user enters value in "Password" text field
    And user clicks on "Sign In" button
            And User clicks on "Skip" button as optional

    # Edit profile photo 1
    Given user clicks on "Profile" icon
    And wait "3" seconds
    #And user takes screenshot of the content description element "Profile photo" as "ProfilePhoto_Original.jpeg"
    And user pushes the image "ProfilePhoto_1.jpeg" to the device
    And user clicks on "Profile photo" icon
    And user clicks on "Choose from Library" button
    And user clicks on "Photo taken on" content description
    And user clicks on "Done" button
    And wait "5" seconds
    And user takes screenshot of the content description element "Profile photo" as "ProfilePhoto_Updated.jpeg"
    Then User validates that the images "ProfilePhoto_1.jpeg" and "ProfilePhoto_Updated.jpeg" are "same"
    # Edit profile photo 2
    Given user clicks on "Profile" icon
    #And user takes screenshot of the content description element "Profile photo" as "ProfilePhoto_Original.jpeg"
    And user pushes the image "ProfilePhoto_2.jpeg" to the device
    And user clicks on "Profile photo" icon
    And user clicks on "Choose from Library" button
    And user clicks on "Photo taken on" content description
    And user clicks on "Done" button
    And wait "5" seconds
    And user takes screenshot of the content description element "Profile photo" as "ProfilePhoto_Updated.jpeg"
    Then User validates that the images "ProfilePhoto_2.jpeg" and "ProfilePhoto_Updated.jpeg" are "same"