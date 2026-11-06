Feature: Verify Listing Owner Receives & Accepts a Swap Proposal

  Scenario: Verify Listing Owner Receives & Accepts a Swap Proposal
      Given user load test data from "swapProposal.json"
    Given user clicks on "Allow" button
    Then user validates that text "Welcome back" is "Displayed"
    And user enters value in "Email_1" text field
    And user enters value in "Password" text field
    And user clicks on "Sign In" button
        And User clicks on "Skip" button as optional

    # Verify Create Listing
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
    And user gets "Listing Title_1" text field value
    And User generate a unique value for "Listing Title_1" with prefix "Listing Title_1"
    And user enters value in "Listing Title_1" text field
    And user gets "Listing Title_1" text field value

    #Then user validates "Home Location" field value contains "Enter Postcode"
    And user clicks on "Estimate Price" button
    And user waits till "Progress bar" element is disappeared
    Then user validates "User Expected Value" field value is "not empty"
    And user clicks on "Any Product Value" button
    And user clicks on "Publish Now" button
    Then user validates that text "My Listings" is "Displayed"
    And user clicks on "Active" button
    Then user validates that content description "Listing Title_1" is "Displayed"
    And user clicks on "Listing Title_1" content description
    And user gets "Listing ID" field value
    And user gets "Brand" field value
    And user gets "Model / Series" field value
    And user gets "Color / Finish" field value
    And user gets "Condition" field value
    And user gets "Type" field value
    And user gets "Our Smart Value Range" field value
    And user clicks on "Listing actions" icon
    And user clicks on "Edit" button
    And user gets "Category" text field value
    And user gets "Subcategory" text field value
    And user gets "Price (GBP)" text field value
    And user clicks on "Back" icon
    And user clicks on "Profile" icon
    And user clicks on "Log Out" button
    #User1 submits a swap proposal
    And user enters value in "Email_2" text field
    And user enters value in "Password" text field
    And user clicks on "Sign In" button
        And User clicks on "Skip" button as optional

    And user clicks on "My Listings" button
     # Verify Create Listing
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
    And user gets "Listing Title_2" text field value
    And User generate a unique value for "Listing Title_2" with prefix "Listing Title_2"
    And user enters value in "Listing Title_2" text field
    And user gets "Listing Title_2" text field value

    #Then user validates "Home Location" field value contains "Enter Postcode"
    And user clicks on "Estimate Price" button
    And user waits till "Progress bar" element is disappeared
    Then user validates "User Expected Value" field value is "not empty"
    And user clicks on "Any Product Value" button
    And user clicks on "Publish Now" button
    Then user validates that text "My Listings" is "Displayed"
    And user clicks on "Active" button
    Then user validates that content description "Listing Title_2" is "Displayed"
    And user clicks on "Listing Title_2" content description
    And user gets "Listing ID" field value
    And user gets "Brand" field value
    And user gets "Model / Series" field value
    And user gets "Color / Finish" field value
    And user gets "Condition" field value
    And user gets "Type" field value
    And user gets "Our Smart Value Range" field value
    And user clicks on "Listing actions" icon
    And user clicks on "Edit" button
    And user gets "Category" text field value
    And user gets "Subcategory" text field value
    And user gets "Price (GBP)" text field value
    And user clicks on "Back" icon
    And user clicks on "Home" button
        And user clicks on "Listing Title_1" content description

    
    Then user validates that text "Match Your Products" is "Displayed"
    Then user validates that state of button "Initiate Swap" is "Disabled"
    And user clicks on "Listing Title_2" content description
    Then user validates that state of button "Initiate Swap" is "Enabled"
    And user clicks on "Initiate Swap" button
    Then user validates that text "Submit Swap Proposal" is "Displayed"
    And user clicks on "Submit" button
    And wait "5" seconds

    And user clicks on "Profile" icon
    And user clicks on "Log Out" button
    
     #User2 submits a swap proposal
    And user enters value in "Email_3" text field
    And user enters value in "Password" text field
    And user clicks on "Sign In" button
        And User clicks on "Skip" button as optional

    And user clicks on "My Listings" button
     # Verify Create Listing
    Given user clicks on "Create" icon
    Then user validates that text "Create Listing" is "Displayed"
         And user pushes the image "Product3.jpg" to the device

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
    And user gets "Listing Title_3" text field value
    And User generate a unique value for "Listing Title_3" with prefix "Listing Title_3"
    And user enters value in "Listing Title_3" text field
    And user gets "Listing Title_3" text field value

    #Then user validates "Home Location" field value contains "Enter Postcode"
    And user clicks on "Estimate Price" button
    And user waits till "Progress bar" element is disappeared
    Then user validates "User Expected Value" field value is "not empty"
    And user clicks on "Any Product Value" button
    And user clicks on "Publish Now" button
    Then user validates that text "My Listings" is "Displayed"
    And user clicks on "Active" button
    Then user validates that content description "Listing Title_3" is "Displayed"
    And user clicks on "Listing Title_3" content description
    And user gets "Listing ID" field value
    And user gets "Brand" field value
    And user gets "Model / Series" field value
    And user gets "Color / Finish" field value
    And user gets "Condition" field value
    And user gets "Type" field value
    And user gets "Our Smart Value Range" field value
    And user clicks on "Listing actions" icon
    And user clicks on "Edit" button
    And user gets "Category" text field value
    And user gets "Subcategory" text field value
    And user gets "Price (GBP)" text field value
    And user clicks on "Back" icon
    And user clicks on "Home" button
        And user clicks on "Listing Title_1" content description

    
    Then user validates that text "Match Your Products" is "Displayed"
    Then user validates that state of button "Initiate Swap" is "Disabled"
    And user clicks on "Listing Title_3" content description
    Then user validates that state of button "Initiate Swap" is "Enabled"
    And user clicks on "Initiate Swap" button
    Then user validates that text "Submit Swap Proposal" is "Displayed"
    And user clicks on "Submit" button
    And wait "5" seconds

    And user clicks on "Profile" icon
    And user clicks on "Log Out" button
#Login to listing and check for swap proposal notification
  And user enters value in "Email_1" text field
    And user enters value in "Password" text field
    And user clicks on "Sign In" button
            And User clicks on "Skip" button as optional

    #---------------------------------------------------------------------------------------
    #Swap proposal notification is not received by the listing owner# Issue needs to be checked
    #-------------------------------------------------------------------------------------
    
    And user clicks on "My Revaliyo" button
    And user clicks on "Received Proposals" button
    And user clicks on "Listing Title_1" content description
    Then user validates that content description "Listing Title_2" is "Displayed"
     Then user validates that content description "Listing Title_3" is "Displayed"

    And user clicks on "Accept" button for product with "Listing Title_3"
    And user clicks on "Accept" button
        And user clicks on "Back" icon
    And user validates the status is "In Progress" for product with "Listing Title_3"
    And user validates the status is "On Hold" for product with "Listing Title_2"
    And user clicks on "Open Chat" button for product with "Listing Title_3"

    
    And user clicks on "Share your Location" button
    And user clicks on "Propose Date & Time" button
    And user clicks on "Next" button
    And user clicks on "Send Proposal" button
    And user clicks on "Home" button
    And user clicks on "Profile" icon
    And user clicks on "Log Out" button