@SAP
Feature: Test Patient Registration Functionality
 
	@SAP_Login
	Scenario: Login Test
		Given I navigate to web application
		Then I should see "Page_Title" as "Login - SAP SuccessFactors"
	 	When I enter "Username" as "blee" in "SAP_LoginPage"
	 	And I enter "Password" as "Sap@0101@" in "SAP_LoginPage"
	 	And I click on "Login" in "SAP_LoginPage"	
	 	And I wait for page to load
	 	Then I should see "Quick_Actions" in "SAP_Homepage"
	 	And I enter "Searchbar" as "Manage Pending Hires" in "SAP_Homepage"
	 	And I click on "Manage_Pending_Hires" in "SAP_Homepage"
	 	And I wait for page to load
	 	Then I should see "Manage_Pending_Hires" in "SAP_ManagePendingHiresPage"
	 	And I click on "Select_Pending_Hires_Downarrow" in "SAP_ManagePendingHiresPage"
	 	And I click on "Drafts" in "SAP_ManagePendingHiresPage"
	 	And I wait for page to load
	 	Then I should see "AamirKhan" in "SAP_ManagePendingHiresPage"
	 	And I click on "AamirKhan" in "SAP_ManagePendingHiresPage"
	 	And I wait for page to load
	 	Then I should see "Add_New_Employee" in "SAP_AddNewEmployeePage"
	 	And I click on "Salutation_Dropdown" in "SAP_AddNewEmployeePage"
	 	And I wait for 2000 milli Seconds
	 	And I click on "Mr_Salutation" in "SAP_AddNewEmployeePage"
	 	And I enter "Preferred_Name" as "MD" in "SAP_AddNewEmployeePage"
	 	And I click on "Birth_Country_dropdown" in "SAP_AddNewEmployeePage" 
	 	And I click on "India" in "SAP_AddNewEmployeePage"
	 	And I enter "National_ID" as "878767676565" in "SAP_AddNewEmployeePage"
	 	And I wait for 2000 milli Seconds 
	 	And I click on "Continue" in "SAP_AddNewEmployeePage"
	 	And I enter "Country" as "Inida" in "SAP_AddNewEmployeePage"
	 	And I click on "Calender_Icon_of_Certification" in "SAP_AddNewEmployeePage"
	 	And I click on "Certification_date" in "SAP_AddNewEmployeePage"
	 	And I click on "Show_more_fields" in "SAP_AddNewEmployeePage"
	 	And I enter "Full_Name" as "MD Aamir Khan" in "SAP_AddNewEmployeePage"
	 	And I enter "Precentage_of_graduation" as "91" in "SAP_AddNewEmployeePage"
	 	And I enter "Phone_number" as "8008789656" in "SAP_AddNewEmployeePage"
	 	And I enter "Email" as "aamirkhan23@gmail.com" in "SAP_AddNewEmployeePage"
	 	And I enter "Phone_Number" as "8008789656" in "SAP_AddNewEmployeePage"
	 	And I click on "Continue2" in "SAP_AddNewEmployeePage"
	 	And I click on "Add_Button" in "SAP_AddNewEmployeePage"
	 	And I enter "Relation_Name" as "Pra" in "SAP_AddNewEmployeePage"
    And I click on "Relation_Role_Name" in "SAP_AddNewEmployeePage" 
    And I click on "Relationship_Type_Downarrow" in "SAP_AddNewEmployeePage"
    And I click on "Apprentice_Supervisor" in "SAP_AddNewEmployeePage"
    #And I wait for 3000 milli Seconds 	
    And I click on "Continue3" in "SAP_AddNewEmployeePage"
    And I click on "Frequency_arrow" in "SAP_AddNewEmployeePage"
    And I click on "Frequency_Value" in "SAP_AddNewEmployeePage"
	 	And I wait for 5000 milli Seconds 
	 	
#	 	And I enter "Aadhar_Number" as "878767676565" in "SAP_AddNewEmployeePage"
#	 	And I enter "City_of_Hospitals" as "Hyderabad" in "SAP_AddNewEmployeePage"
	 	
	 	
	 	
	 	
	 	
	 	 