Feature: Verify User Can Edit Display Name

  Scenario: Verify User Can Edit Display Name
    Given user clicks on "Allow" button
    Given user load test data from "editProfile.json"
    And user enters value in "Email_1" text field
    And user enters value in "Password" text field
    And user clicks on "Sign In" button
            And User clicks on "Skip" button as optional

    And user clicks on "Profile" icon
    And user clicks on "Edit profile" icon
    And User generate a unique value for "Display Name_Updated" with prefix "DisplayName"
    And user enters value in "Display Name_Updated" text field
    Then user validates "Display Name" field value is "Display Name_Updated"
    And user clicks on "Save" button
    Then user validates that text "Display Name_Updated" is "Displayed"
    And user clicks on "Back" button
    And user clicks on "Profile" icon
    Then user validates that text "Display Name_Updated" is "Displayed"
    And user clicks on "Back" button
    # Verify Create Listing - 1
    Given user clicks on "Create" icon
    Then user validates that text "Create Listing" is "Displayed"
    And user pushes the image "Product1.jpg" to the device
    And user clicks on "Upload Photos" button
  # Upload photo from phone gallery - Start
    And user clicks on "Photo taken on" content description
    And user clicks on "Done" button
    And wait "5" seconds
  # Upload photo from phone gallery - End
    And user clicks on "Identify Product" button
    And user waits till "Progress bar" element is disappeared
    And user gets "Product Name" text field value
    And user get the existing value for "Description" from "Product Name"
    And user enters value in "Description" text field
    And user gets "Listing Title" text field value
    And User generate a unique value for "Listing Title_1" with prefix "Listing Title"
    And user enters value in "Listing Title_1" text field
    And user gets "Listing Title_1" text field value
    And user clicks on "Estimate Price" button
    And user waits till "Progress bar" element is disappeared
    And user clicks on "Any Product Value" button
    And user clicks on "Publish Now" button
    Then user validates that text "My Listings" is "Displayed"
    And user clicks on "Active" button
    Then user validates that content description "Listing Title_1" is "Displayed"
    And user clicks on "Profile" icon
    And user clicks on "Log Out" button
    And user enters value in "Email_2" text field
    And user enters value in "Password" text field
    And user clicks on "Sign In" button
            And User clicks on "Skip" button as optional

    And user clicks on "My Listings" button
    # Verify Create Listing - 2
    Given user clicks on "Create" icon
    Then user validates that text "Create Listing" is "Displayed"
    And user pushes the image "Product2.jpg" to the device
    And user clicks on "Upload Photos" button
  # Upload photo from phone gallery - Start
    And user clicks on "Photo taken on" content description
    And user clicks on "Done" button
    And wait "5" seconds
  # Upload photo from phone gallery - End
    And user clicks on "Identify Product" button
    And user waits till "Progress bar" element is disappeared
    And user gets "Product Name" text field value
    And user get the existing value for "Description" from "Product Name"
    And user enters value in "Description" text field
    And user gets "Listing Title" text field value
    And User generate a unique value for "Listing Title_2" with prefix "Listing Title"
    And user enters value in "Listing Title_2" text field
    And user gets "Listing Title_2" text field value
    And user gets "Listing Title" text field value
    And user clicks on "Estimate Price" button
    And user waits till "Progress bar" element is disappeared
    And user clicks on "Any Product Value" button
    And user clicks on "Publish Now" button
    Then user validates that text "My Listings" is "Displayed"
    And user clicks on "Active" button
    Then user validates that content description "Listing Title_2" is "Displayed"
  # Verify full name in Swap - Sent Proposals
    And user clicks on "Profile" icon
    And user clicks on "Log Out" button
    And user enters value in "Email_1" text field
    And user enters value in "Password" text field
    And user clicks on "Sign In" button
            And User clicks on "Skip" button as optional

    And user clicks on "Home" button
    Then user validates that text "Items Feed" is "Displayed"
    And user clicks on "Listing Title_2" content description
    Then user validates that text "Match Your Products" is "Displayed"
    Then user validates that state of button "Initiate Swap" is "Disabled"
    And user clicks on "Listing Title_1" content description
    Then user validates that state of button "Initiate Swap" is "Enabled"
    And user clicks on "Initiate Swap" button
    Then user validates that text "Submit Swap Proposal" is "Displayed"
    Then user validates that content description "Listing Title_1" is "Displayed"
    Then user validates that content description "Listing Title_2" is "Displayed"
    And user clicks on "Submit" button
    And user clicks on "My Revaliyo" button
    And user clicks on "Sent Proposals" button
    And user clicks on "Listing Title_2" content description
    Then user validates that text "Display Name_Updated" is "Displayed"
    And user clicks on "Home" button
    And user clicks on "Profile" icon
    And user clicks on "Log Out" button
    # Verify full name in Swap - Received Proposals
    And user enters value in "Email_2" text field
    And user enters value in "Password" text field
    And user clicks on "Sign In" button
            And User clicks on "Skip" button as optional

    And user clicks on "My Revaliyo" button
    And user clicks on "Received Proposals" button
    And user clicks on "Listing Title_2" content description
    And User merge the "Display Name_Updated?'s? ?Requests" as "Received Swap Proposal Request"
    Then user validates that text "Received Swap Proposal Request" is "Displayed"
    And user clicks on "Accept" button
    And user clicks on "Accept" button
    And User merge the "Swap with? ?Display Name_Updated" as "Received Swap Proposal Chat"
    Then user validates that text "Received Swap Proposal Chat" is "Displayed"
    And user clicks on "Back" button
    And user clicks on "Back" button
    # Verify full name in Swaps In Progress
    And user clicks on "Swaps In Progress" button
    And User merge the "Display Name_Updated?'s " as "In Progress Swaps"
    Then user validates that content description "In Progress Swaps" is "Displayed"
    And user clicks on "Home" button
    And user clicks on "Profile" icon
    And user clicks on "Log Out" button
    # Verify full name in Swaps Completed
    And user enters value in "Email_1" text field
    And user enters value in "Password" text field
    And user clicks on "Sign In" button
            And User clicks on "Skip" button as optional

    And user clicks on "My Revaliyo" button
    And user clicks on "Swaps In Progress" button
    And user clicks on "Listing Title_1" content description
    And user clicks on "Collected / Picked up" button
    And user clicks on "Back" button
    And user clicks on "Home" button
    And user clicks on "Profile" icon
    And user clicks on "Log Out" button
    And user enters value in "Email_2" text field
    And user enters value in "Password" text field
    And user clicks on "Sign In" button
            And User clicks on "Skip" button as optional

    And user clicks on "My Revaliyo" button
    And user clicks on "Swaps In Progress" button
    Then user validates that content description "In Progress Swaps" is "Displayed"
    And user clicks on "Listing Title_1" content description
    And User merge the "Swap with? ?Display Name_Updated" as "Received Swap Proposal Chat"
    And user clicks on "Collected / Picked up" button
    And user clicks on "My Revaliyo" button
    Then user validates that text "Leave a review" is "Displayed"
    And user selects "4" stars for question "Rate how was your overall experience with this person?"
    And user selects "5" stars for question "Rate how clearly and timely did the user communicate?"
    And user selects "3" stars for question "Rate how smoothly did the pickup / handoff go?"
        And user clicks on "👍 Yes" button
    And user clicks on "Submit Review" button
        And user clicks on "My Revaliyo" button


    And user clicks on "Swaps Completed" button
    Then user validates that text "Display Name_Updated" is "Displayed"
