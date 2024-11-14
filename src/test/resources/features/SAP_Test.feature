@SAP
Feature: SAP Application Functionality
 
	@SAP_Add_New_Employee
	Scenario: Add New Employee
		Given I navigate to web application
		Then I should see "Page_Title" as "Login - SAP SuccessFactors"
	 	When I enter "Username" as "blee" in "LoginPage"
	 	And I enter "Password" as "Sap@0101@" in "LoginPage"
	 	And I click on "Login" in "LoginPage"	
	 	And I wait for page to load
	 	Then I should see "Quick_Actions" in "Homepage"
	 	And I enter "Searchbar" as "Manage Pending Hires" in "Homepage"
	 	And I click on "Manage_Pending_Hires" in "Homepage"
    And I wait for page to load
    And I wait for 3000 milli Seconds
	 	Then I should see "Manage_Pending_Hires" in "ManagePendingHiresPage"
	 	And I click on "Select_Pending_Hires_Downarrow" in "ManagePendingHiresPage"
	 	And I click on "Drafts" in "ManagePendingHiresPage"
	 	And I wait for page to load
	 	Then I should see "AamirKhan" in "ManagePendingHiresPage"
	 	And I click on "AamirKhan" in "ManagePendingHiresPage"
	 	And I wait for page to load
	 	Then I should see "Add_New_Employee" in "AddNewEmployeePage"
  	And I wait for 30000 milli Seconds
	 	And I click on "Salutation_Dropdown" in "AddNewEmployeePage"
	 	And I wait for 2000 milli Seconds
	 	And I click on "Mr_Salutation" in "AddNewEmployeePage"
	 	And I enter "Preferred_Name" as "MD" in "AddNewEmployeePage"
	 	And I click on "Birth_Country_dropdown" in "AddNewEmployeePage"
	 	And I click on "India" in "AddNewEmployeePage"
	 	And I enter "National_ID" as "878767676565" in "AddNewEmployeePage"
	 	And I wait for 2000 milli Seconds
	 	And I click on "Continue" in "AddNewEmployeePage"
	 	And I enter "Country" as "Inida" in "AddNewEmployeePage"
	 	And I click on "Calender_Icon_of_Certification" in "AddNewEmployeePage"
	 	And I click on "Certification_date" in "AddNewEmployeePage"
	 	And I click on "Show_more_fields" in "AddNewEmployeePage"
	 	And I enter "Full_Name" as "MD Aamir Khan" in "AddNewEmployeePage"
	 	And I enter "Precentage_of_graduation" as "91" in "AddNewEmployeePage"
	 	And I enter "Phone_number" as "8008789656" in "AddNewEmployeePage"
	 	And I click on "Accomplishment" in "AddNewEmployeePage"
	 	And I click on "Accomplishment_Date" in "AddNewEmployeePage"
	 	And I enter "Email" as "aamirkhan23@gmail.com" in "AddNewEmployeePage"
	 	And I enter "Phone_Number" as "8008789656" in "AddNewEmployeePage"
	 	And I click on "Continue2" in "AddNewEmployeePage"
	 	And I click on "Add_Button" in "AddNewEmployeePage"
	 	And I enter "Relation_Name" as "Pra" in "AddNewEmployeePage"
    And I click on "Relation_Role_Name" in "AddNewEmployeePage"
    And I click on "Relationship_Type_Downarrow" in "AddNewEmployeePage"
    And I click on "Apprentice_Supervisor" in "AddNewEmployeePage"
    #And I wait for 3000 milli Seconds 	
    And I click on "Continue3" in "AddNewEmployeePage"
    And I wait for 4000 milli Seconds 	
    And I click on "Add_Button2" in "AddNewEmployeePage"
    And I click on "Frequency_arrow" in "AddNewEmployeePage"
    And I click on "Frequency_Value" in "AddNewEmployeePage"
    And I enter "Compensation_Amount" as "25000" in "AddNewEmployeePage"
    And I click on "Compensation_Currency_Arrow" in "AddNewEmployeePage"
    And I click on "Compensation_Currency" in "AddNewEmployeePage"
    Then I click on "Submit_Button" in "AddNewEmployeePage"
	 	And I wait for 5000 milli Seconds
	 	
#	 	And I enter "Aadhar_Number" as "878767676565" in "AddNewEmployeePage"
#	 	And I enter "City_of_Hospitals" as "Hyderabad" in "AddNewEmployeePage"
	 	
	 	
	@SAP_Login
	Scenario: Loging with Invalid password
		Given I navigate to web application
		Then I should see "Page_Title" as "Login - SAP SuccessFactors"
	 	When I enter "Username" as "blee" in "LoginPage"
	 	And I enter "Password" as "ssSap@0101@" in "LoginPage"
	 	And I click on "Login" in "LoginPage"	
	 	And I wait for page to load
	 	Then I should see "Quick_Actions" in "Homepage"
	 	
	 	
 
 