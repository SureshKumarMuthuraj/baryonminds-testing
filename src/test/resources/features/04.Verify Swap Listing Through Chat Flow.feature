Feature: Verify Swap Listing Through Chat Flow

  Scenario: Verify Swap Listing Through Chat Flow
    # Create user 2
         Given user load test data from "swapListing.json"

    Given user clicks on "Allow" button
    Then user validates that text "Welcome back" is "Displayed"
    And user enters value in "Email_1" text field
    And user enters value in "Password" text field
    And user clicks on "Sign In" button
        And User clicks on "Skip" button as optional

    Then user validates that text "Items Feed" is "Displayed"
    # Verify Create Listing - 1
    And user clicks on "My Listings" button
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
    Then user validates "Condition" field value is "not empty"
    And user gets "Product Name" text field value
    And user get the existing value for "Description" from "Product Name"
    And user enters value in "Description" text field
    And user gets "Listing Title" text field value
    And User generate a unique value for "Listing Title_1" with prefix "Listing Title"
    And user enters value in "Listing Title_1" text field
    And user gets "Listing Title_1" text field value
  # And user clicks on "Post in a new address" button
  # And user clicks on "Address" lookup field
  # And user clicks on "Add a location" lookup field
  # And user enters value in "Enter Postcode" lookup field
  # And user selects option in "Enter Postcode" lookup field
    #Then user validates "Home Location" field value contains "Enter Postcode"
    And user selects value in "Condition" dropdown field
    And user clicks on "Estimate Price" button
    And user waits till "Progress bar" element is disappeared
    Then user validates "User Expected Value" field value is "not empty"
    And user enters value in "User Expected Value" text field
    #And user clicks on "Selected Categories" button
    #And user sets the checkbox "Electronics & Tech" to "checked"
    And user clicks on "Any Product Value" button
    And user clicks on "Publish Now" button
    Then user validates that text "My Listings" is "Displayed"
    And user clicks on "Active" button
    Then user validates that content description "Listing Title_1" is "Displayed"
    And user clicks on "Listing Title_1" content description
    And user gets "Listing ID_1" field value
    And user gets "Brand_1" field value
    And user gets "Model / Series_1" field value
    And user gets "Color / Finish_1" field value
    And user gets "Condition_1" field value
    And user gets "Type_1" field value
    And user gets "Our Smart Value Range_2" field value
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
    Then user validates "Product Name" field value is "not empty"
    Then user validates "Brand" field value is "not empty"
    Then user validates "Model / Series" field value is "not empty"
    Then user validates "Category" field value is "not empty"
    Then user validates "Subcategory" field value is "not empty"
    Then user validates "Colour / Finish" field value is "not empty"
    Then user validates "Condition" field value is "not empty"
    And user gets "Product Name" text field value
    And user get the existing value for "Description" from "Product Name"
    And user enters value in "Description" text field
    And user gets "Listing Title" text field value
    And User generate a unique value for "Listing Title_2" with prefix "Listing Title"
    And user enters value in "Listing Title_2" text field
    And user gets "Listing Title_2" text field value
    And user gets "Listing Title" text field value
  # And user clicks on "Post in a new address" button
  # And user clicks on "Address" lookup field
  # And user clicks on "Add a location" lookup field
  # And user enters value in "Enter Postcode" lookup field
  # And user selects option in "Enter Postcode" lookup field
   # Then user validates "Home Location" field value contains "Enter Postcode"
    And user selects value in "Condition" dropdown field
    And user clicks on "Estimate Price" button
    And user waits till "Progress bar" element is disappeared
    Then user validates "User Expected Value" field value is "not empty"
    And user enters value in "User Expected Value" text field
    #And user clicks on "Selected Categories" button
    #And user sets the checkbox "Electronics & Tech" to "checked"
    And user clicks on "Any Product Value" button
    And user clicks on "Publish Now" button
    Then user validates that text "My Listings" is "Displayed"
    And user clicks on "Active" button
    Then user validates that content description "Listing Title_2" is "Displayed"
    And user clicks on "Listing Title_2" content description
    And user gets "Listing ID_2" field value
    And user gets "Brand_2" field value
    And user gets "Model / Series_2" field value
    And user gets "Color / Finish_2" field value
    And user gets "Condition_2" field value
    And user gets "Type_2" field value
    And user gets "Our Smart Value Range_2" field value

    # Verify Create Listing - 3
            And user clicks on "Home" button

        And user clicks on "My Listings" button

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
    Then user validates "Condition" field value is "not empty"
    And user gets "Product Name" text field value
    And user get the existing value for "Description" from "Product Name"
    And user enters value in "Description" text field
    And user gets "Listing Title" text field value
    And User generate a unique value for "Listing Title_3" with prefix "Listing Title"
    And user enters value in "Listing Title_3" text field
    And user gets "Listing Title_3" text field value
  # And user clicks on "Post in a new address" button
  # And user clicks on "Address" lookup field
  # And user clicks on "Add a location" lookup field
  # And user enters value in "Enter Postcode" lookup field
  # And user selects option in "Enter Postcode" lookup field
  #   Then user validates "Home Location" field value contains "Enter Postcode"
    And user selects value in "Condition" dropdown field
    And user clicks on "Estimate Price" button
    And user waits till "Progress bar" element is disappeared
    Then user validates "User Expected Value" field value is "not empty"
    And user enters value in "User Expected Value" text field
    #And user clicks on "Selected Categories" button
    #And user sets the checkbox "Electronics & Tech" to "checked"
    And user clicks on "Any Product Value" button
    And user clicks on "Publish Now" button
    Then user validates that text "My Listings" is "Displayed"
    And user clicks on "Active" button
    Then user validates that content description "Listing Title_3" is "Displayed"
    And user clicks on "Listing Title_3" content description
    And user gets "Listing ID_3" field value
    And user gets "Brand_3" field value
    And user gets "Model / Series_3" field value
    And user gets "Color / Finish_3" field value
    And user gets "Condition_3" field value
    And user gets "Type_3" field value
    And user gets "Our Smart Value Range_3" field value
    And user clicks on "Home" button
    Then user validates that text "Items Feed" is "Displayed"
    #And user clicks on "Electronics & Tech" button
    And user clicks on "Listing Title_1" content description
    Then user validates "Listing ID" field value is "Listing ID_1"
    Then user validates "Brand" field value is "Brand_1"
    Then user validates "Model / Series" field value is "Model / Series_1"
    Then user validates "Color / Finish" field value is "Color / Finish_1"
    #Then user validates "Category" field value is "Electronics & Tech_1"
    Then user validates that text "Match Your Products" is "Displayed"
    Then user validates that state of button "Initiate Swap" is "Disabled"
    And user clicks on "Listing Title_2" content description
    Then user validates that state of button "Initiate Swap" is "Enabled"
    And user clicks on "Listing Title_3" content description
    And user clicks on "Initiate Swap" button
    Then user validates that text "Submit Swap Proposal" is "Displayed"
    Then user validates that content description "Listing Title_1" is "Displayed"
    Then user validates that content description "Listing Title_2" is "Displayed"
    Then user validates that content description "Listing Title_3" is "Displayed"
    And user clicks on "Submit" button
    # Then user validates that text "Proposal sent" is "Displayed"
    And user clicks on "My Revaliyo" button
    And user clicks on "Sent Proposals" button
    And user clicks on "Listing Title_1" content description
    Then user validates that content description "Listing Title_2" is "Displayed"
    Then user validates that content description "Listing Title_3" is "Displayed"
    And user clicks on "Home" button
    And user clicks on "Profile" icon
    And user clicks on "Log Out" button
    And user enters value in "Email_1" text field
    And user enters value in "Password" text field
    And user clicks on "Sign In" button
        And User clicks on "Skip" button as optional

    And user clicks on "My Revaliyo" button
    And user clicks on "Received Proposals" button
    And user clicks on "Listing Title_1" content description
    Then user validates that content description "Listing Title_2" is "Displayed"
    Then user validates that content description "Listing Title_3" is "Displayed"
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
    And user clicks on "Swaps In Progress" button
    And user clicks on "Listing Title_2" content description
    Then user validates that text "In Progress Swaps" is "Displayed"
    And user clicks on "Listing Title_2" content description
    And user clicks on "Share your Location" button
    And user clicks on "Propose Date & Time" button
    And user clicks on "Next" button
    And user clicks on "Send Proposal" button
    And user clicks on "Collected / Picked up" button
    And user validates that button "Confirmed" is "Displayed"
    Then user clicks on "Home" button
    And user clicks on "Profile" icon
    And user clicks on "Log Out" button
    And user enters value in "Email_1" text field
    And user enters value in "Password" text field
    And user clicks on "Sign In" button
        And User clicks on "Skip" button as optional

    And user clicks on "My Revaliyo" button
    And user clicks on "Swaps In Progress" button
    And user clicks on "Listing Title_1" content description
    And user clicks on "Collected / Picked up" button
    Then user validates that text "Leave a review" is "Displayed"
    And user selects "4" stars for question "Rate how was your overall experience with this person?"
    And user selects "5" stars for question "Rate how clearly and timely did the user communicate?"
    And user selects "3" stars for question "Rate how smoothly did the pickup / handoff go?"
    And user clicks on "👍 Yes" button
    And user clicks on "Submit Review" button
    And user clicks on "My Revaliyo" button
    And user clicks on "Swaps Completed" button
    Then user validates that content description "Listing Title_1" is "Displayed"
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
    And user clicks on "Swaps Completed" button
    Then user validates that content description "Listing Title_1" is "Displayed"
