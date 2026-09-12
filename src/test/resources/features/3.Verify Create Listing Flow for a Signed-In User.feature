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
#Verify Create Listing - 1
Given user load test data from "createListing.json"
Given user clicks on "Create" icon
Then user validates that text "Account required" is "Not Displayed"
Then user validates that text "Create Listing" is "Displayed"
And user clicks on "Upload Photos" button
#Upload photo from phone gallery - Start
And user clicks on "Collections" button
And user clicks on "From this device" button
And user clicks on "Revaliyo" button
And user clicks on "Photo taken on 9 Sept 2026 13:11" button
And user clicks on "Done" button
#Upload photo from phone gallery - End
And user clicks on "Identify Product" button
And user waits till "Progress bar" element is disappeared
Then user validates "Product Name" field value is "not empty"
Then user validates "Brand" field value is "not empty"
Then user validates "Model / Series" field value is "not empty"
Then user validates "Category" field value is "not empty"
Then user validates "Subcategory" field value is "not empty"
Then user validates "Colour / Finish" field value is "not empty"
Then user validates "Condition" field value is "not empty"
And user gets "Product Name" field value
And user get the existing value for "Description" from "Product Name"
And user enters value in "Description" text field
And user gets "Listing Title" field value
#And user clicks on "Post in a new address" button
#And user clicks on "Address" lookup field
And user clicks on "Add a location" lookup field
And user enters value in "Enter Postcode" lookup field
And user selects option in "Enter Postcode" lookup field
And user selects value in "Condition" dropdown field
And user clicks on "Estimate Price" button
And user waits till "Progress bar" element is disappeared
Then user validates "User Expected Value" field value is "not empty"
And user enters value in "User Expected Value" text field
And user clicks on "Giveaway" button
And user clicks on "Selected Categories" button
And user sets the checkbox "Electronics & Tech" to "checked"
And user clicks on "Any Product Value" button
And user clicks on "Publish Now" button
Then user validates that text "My Listings" is "Displayed"
And user clicks on "Active" button
Then user validates that content description "Listing Title" is "Displayed"
#Verify Create Listing - 2
Given user clicks on "Create" icon
Then user validates that text "Account required" is "Not Displayed"
Then user validates that text "Create Listing" is "Displayed"
And user clicks on "Upload Photos" button
#Upload photo from phone gallery - Start
And user clicks on "Collections" button
And user clicks on "From this device" button
And user clicks on "Revaliyo" button
And user clicks on "Photo taken on 9 Sept 2026 13:11" button
And user clicks on "Done" button
#Upload photo from phone gallery - End
And user clicks on "Identify Product" button
And user waits till "Progress bar" element is disappeared
Then user validates "Product Name" field value is "not empty"
Then user validates "Brand" field value is "not empty"
Then user validates "Model / Series" field value is "not empty"
Then user validates "Category" field value is "not empty"
Then user validates "Subcategory" field value is "not empty"
Then user validates "Colour / Finish" field value is "not empty"
Then user validates "Condition" field value is "not empty"
And user gets "Product Name" field value
And user get the existing value for "Description" from "Product Name"
And user enters value in "Description" text field
And user gets "Listing Title" field value
And user selects value in "Condition" dropdown field
And user clicks on "Estimate Price" button
And user waits till "Progress bar" element is disappeared
Then user validates "User Expected Value" field value is "not empty"
And user enters value in "User Expected Value" text field
And user clicks on "Giveaway" button
And user clicks on "Selected Categories" button
And user sets the checkbox "Electronics & Tech" to "checked"
And user clicks on "Any Product Value" button
And user clicks on "Save as Draft" button
Then user validates that text "My Listings" is "Displayed"
And user clicks on "Draft" button
Then user validates that content description "Listing Title" is "Displayed"
And user clicks on "Profile" icon
And user clicks on "Delete Account" button
And user clicks on "Delete Account" button

