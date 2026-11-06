Feature: Verify Listing Owner Receives & Accepts a Giveaway Request

  Scenario: Verify Listing Owner Receives & Accepts a Giveaway Request
     Given user load test data from "giveawayRequest.json"
    Given user clicks on "Allow" button
    Then user validates that text "Welcome back" is "Displayed"
    And user enters value in "Email_1" text field
    And user enters value in "Password" text field
    And user clicks on "Sign In" button
        And User clicks on "Skip" button as optional

    And user clicks on "My Listings" button
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
    Then user validates "Product Name" field value is "not empty"
    Then user validates "Brand" field value is "not empty"
    Then user validates "Model / Series" field value is "not empty"
    Then user validates "Category" field value is "not empty"
    Then user validates "Subcategory" field value is "not empty"
    Then user validates "Colour / Finish" field value is "not empty"
    Then user validates "Condition" dropdown field value is "not empty"
    And user gets "Product Name" text field value
    And user get the existing value for "Description" from "Product Name"
    And user enters value in "Description" text field
    And user gets "Listing Title" text field value
    And User generate a unique value for "Listing Title" with prefix "Listing Title"
    And user enters value in "Listing Title" text field
    And user gets "Listing Title" text field value

    #Then user validates "Home Location" field value contains "Enter Postcode"
    And user selects value in "Condition" dropdown field
    And user clicks on "Estimate Price" button
    And user waits till "Progress bar" element is disappeared
    Then user validates "User Expected Value" field value is "not empty"
    And user enters value in "User Expected Value" text field
    And user clicks on "Giveaway" button
    And user clicks on "Swap" button
    And user clicks on "Publish Now" button
    Then user validates that text "My Listings" is "Displayed"
    And user clicks on "Active" button
    Then user validates that content description "Listing Title" is "Displayed"
    #And user gets listing "1" name as "Product"
    And user clicks on "Listing Title" content description
    And user gets "Listing ID" field value
    And user gets "Brand" field value
    And user gets "Model / Series" field value
    And user gets "Color / Finish" field value
    And user gets "Condition" field value
    And user gets "Type" field value
    And user gets "Our Smart Value Range" field value
    And user clicks on "Listing actions" icon
    And user clicks on "Edit" button
    And user gets "Category" field value
    And user gets "Subcategory" field value
    And user gets "Price (GBP)" text field value
    And user clicks on "Back" icon
    And user clicks on "Profile" icon
    And user clicks on "Log Out" button
    And user enters value in "Email_2" text field
    And user enters value in "Password" text field
    And user clicks on "Sign In" button
    And User clicks on "Skip" button as optional
    Then user validates that text "Items Feed" is "Displayed"
    And user clicks on "Giveaway" button
    #And user clicks on "Electronics & Tech" button
    And wait "3" seconds
    And user clicks on "Listing Title" content description
    Then user validates "Listing ID" field value is "Listing ID"
    Then user validates "Category" field value is "Category"
    Then user validates "Subcategory" field value is "Subcategory"
    Then user validates "Brand" field value is "Brand"
    Then user validates "Model / Series" field value is "Model / Series"
    Then user validates "Color / Finish" field value is "Color / Finish"
    Then user validates "Our Smart Value Range" field value is "Our Smart Value Range"
    And user clicks on "Request Giveaway" button
    And user clicks on "Send Request" button
    Then user validates that button "Request Giveaway" is "Not Displayed"
    Then user validates that button "Requested" is "Displayed"
    # Then user validates that text "Request sent" is "Displayed"
    And user clicks on "My Revaliyo" button
    And user clicks on "Giveaway" button
    And user clicks on "Sent Request" button
    And user clicks on "Listing Title" content description
    Then user validates that text "Awaiting response" is "Displayed"
    Then user validates that button "Withdraw" is "Displayed"
    And user clicks on "Home" button
    And user clicks on "Profile" icon
    And user clicks on "Log Out" button
    And user enters value in "Email_1" text field
    And user enters value in "Password" text field
    And user clicks on "Sign In" button
        And User clicks on "Skip" button as optional

    #---------------------------------------------------------------------------------------
    #Swap proposal notification is not received by the listing owner# Issue needs to be checked
    #-------------------------------------------------------------------------------------
    
    And user clicks on "My Revaliyo" button
    And user clicks on "Giveaway" button
    And user clicks on "Pick-up Request" button
    And user clicks on "Listing Title" content description
    And user clicks on "Accept" button
    And user clicks on "Accept" button
    And user clicks on "Share your Location" button
    And user clicks on "Propose Date & Time" button
    And user clicks on "Next" button
    And user clicks on "Send Proposal" button
    And user clicks on "Home" button
    And user clicks on "Profile" icon
    And user clicks on "Log Out" button
    And user enters value in "Email_2" text field
    And user enters value in "Password" text field
    And user clicks on "Sign In" button
    And User clicks on "Skip" button as optional

    And user clicks on "My Revaliyo" button
    And user clicks on "Giveaway" button
    And user clicks on "Sent Request" button
    And user clicks on "Listing Title" content description
    Then user validates that button "In Progress" is "Displayed"
    And user clicks on "In Progress" button
    And user clicks on "Share your Location" button
    And user clicks on "Propose Date & Time" button
    And user clicks on "Next" button
    And user clicks on "Send Proposal" button
    And user clicks on "Mark as Collected" button
    And user validates that button "Confirmed" is "Displayed"
    Then user clicks on "Home" button
    And user clicks on "Profile" icon
    And user clicks on "Log Out" button
    And user enters value in "Email_1" text field
    And user enters value in "Password" text field
    And user clicks on "Sign In" button
        And User clicks on "Skip" button as optional

    And user clicks on "My Revaliyo" button
    And user clicks on "Giveaway" button
    And user clicks on "Giveaway in Progress" button
    And user clicks on "Listing Title" content description
    And user clicks on "Mark as Given" button
    Then user validates that text "Leave a review" is "Displayed"
    And user selects "4" stars for question "Rate how was your overall experience with this person?"
    And user selects "5" stars for question "Rate how clearly and timely did the user communicate?"
    And user selects "3" stars for question "Rate how smoothly did the pickup / handoff go?"
    And user clicks on "👍 Yes" button
    And user clicks on "Submit Review" button
    And user clicks on "My Revaliyo" button
    And user clicks on "Giveaway" button
    And user clicks on "Giveaway Completed" button
    Then user validates that content description "Listing Title" is "Displayed"
    And user clicks on "Home" button
    And user clicks on "Profile" icon
    And user clicks on "Log Out" button
    And user enters value in "Email_2" text field
    And user enters value in "Password" text field
    And user clicks on "Sign In" button
    
    And user clicks on "My Revaliyo" button
    Then user validates that text "Leave a review" is "Displayed"
    And user selects "5" stars for question "Rate how was your overall experience with this person?"
    And user selects "4" stars for question "Rate how clearly and timely did the user communicate?"
    And user selects "3" stars for question "Rate how smoothly did the pickup / handoff go?"
    And user clicks on "👍 Yes" button
    And user clicks on "Submit Review" button
    And user clicks on "My Revaliyo" button
    And user clicks on "Giveaway" button
    And user clicks on "Giveaway Completed" button
    Then user validates that content description "Listing Title" is "Displayed"
