@SAP
Feature: Test Draft recruting functionality
 
	@SAP_Add_New_Employee
	Scenario: Add New Employee
		Given I navigate to web application
		Then I should see "Page_Title" as "Login - SAP SuccessFactors"
	 	When I enter "Username" as "Admin5" in "LoginPage"
	 	And I enter "Password" as "Sap@123#" in "LoginPage"
	 	And I click on "Login" in "LoginPage"	
	 	And I wait for page to load
	 	Then I should see "Quick_Actions_Text" in "Homepage"
	 	And I enter "Searchbar" as "Manage Pending Hires" in "Homepage"
	 	And I click on "Manage_Pending_Hires" in "Homepage"
    And I wait for page to load
    And I wait for 2000 milli Seconds
    Then I should see "Manage_Pending_Hires" in "ManagePendingHiresPage"
	 	And I click on "Select_Pending_Hires_Downarrow" in "ManagePendingHiresPage"
	 	And I click on "Drafts_Recruting" in "ManagePendingHiresPage"
	 	And I wait for page to load
	 	Then I should see "Caroline_Matthews" in "ManagePendingHiresPage"
	 	And I click on "Caroline_Matthews" in "ManagePendingHiresPage"
	 	And I wait for page to load
	 	Then I should see "Add_New_Employee" in "AddNewEmployeePage"
	 	And I enter "First_Name" as "Caroline" in "AddNewEmployeePage"
	 	And I enter "Last_Name" as "Matthews" in "AddNewEmployeePage"
	 	And I click on "DateOfBirth_Calender" in "AddNewEmployeePage"
	 	And I click on "DateOfBirth" in "AddNewEmployeePage"
	 	And I click on "DownArrow" of "CountryOfBirth_Dropdown" in "AddNewEmployeePage"
	 	And I click on "Australia" in "AddNewEmployeePage"
	 	And I click on "NationalIDCardCountryArrow" in "AddNewEmployeePage"
	 	And I click on "Country_Australia" in "AddNewEmployeePage"
	 	And I click on "TFN_Arrow" in "AddNewEmployeePage"
	 	And I click on "TFN_Value" in "AddNewEmployeePage"
	 	And I enter "NationalId_Value" as "999 888 777" in "AddNewEmployeePage" 
	 	And I click on "IsPrimary_Arrow" in "AddNewEmployeePage"
	 	And I click on "IsPrimary_Value" in "AddNewEmployeePage"
	 	And I click on "Continue" in "AddNewEmployeePage"
	 	And I wait for page to load
	 	And I click on "Show_more_fields" in "AddNewEmployeePage"
	  And I click on "Native_Preferred_Language_Dropdown" in "AddNewEmployeePage"
    And I wait for page to load
    And I click on "Native_Preferred_Laguage" in "AddNewEmployeePage"
    And I click on "Personal_Country_Dropdown" in "AddNewEmployeePage"
    And I click on "Personal_Country_Value" in "AddNewEmployeePage"
    And I click on "EmailTypeDropdown" in "AddNewEmployeePage"
    And I click on "PersonalTypeEmail" in "AddNewEmployeePage"
    And I enter "EmailAddress" as "cmatthews@email.com" in "AddNewEmployeePage"
    And I click on "Email_IsPrimaryArrow" in "AddNewEmployeePage" 
    And I click on "Email_IsPrimaryValue" in "AddNewEmployeePage" 
    And I click on "PhoneType_HomeArrow" in "AddNewEmployeePage"
    And I click on "PhoneType_HomeValue" in "AddNewEmployeePage"
    And I enter "PhoneNumber" as "(650) 222-1315" in "AddNewEmployeePage"	
    And I enter "InstaMessagingID" as "123456" in "AddNewEmployeePage"
    And I click on "AddPersonalContacts" in "AddNewEmployeePage"
    And I enter "ContactName" as "TONY" in "AddNewEmployeePage"
    And I click on "PrimaryArrow" in "AddNewEmployeePage"
    And I click on "PrimaryYes" in "AddNewEmployeePage"
    And I wait for 2000 milli Seconds
    And I click on "Home_AddressType_Arrow" in "AddNewEmployeePage"
    And I click on "Home_AddressValue" in "AddNewEmployeePage"
    And I click on "Region_Arrow" in "AddNewEmployeePage"
    And I click on "Region_Value" in "AddNewEmployeePage"
    And I enter "Dependents_FirstName" as "John" in "AddNewEmployeePage"
    And I enter "Dependents_LastName" as "Mark" in "AddNewEmployeePage"
    And I click on "RelationShipArrow" in "AddNewEmployeePage"
    And I click on "RelationShipValue" in "AddNewEmployeePage"
    And I click on "Continue2" in "AddNewEmployeePage"
    #And I click on "Company_Arrow" in "AddNewEmployeePage"
    #And I click on "Ace_Australia" in "Company" in "AddNewEmployeePage"
    #And I accept the alert in "AddNewEmployeePage"
    #And I click on "JobClassification_Arrow" in "AddNewEmployeePage"
    #And I click on "Analyst" in "AddNewEmployeePage"
    And I click on "ShowMoreFields" in "AddNewEmployeePage"
    And I click on "HireDate_Calender" in "AddNewEmployeePage"
    And I click on "HireDate" in "AddNewEmployeePage"
    #And I click on "Job_Add" in "AddNewEmployeePage"
    And I click on "RelationTypeArrow" in "AddNewEmployeePage"
    And I click on "RelationType_Value" in "AddNewEmployeePage"
    And I enter "Name" as "Phan" in "AddNewEmployeePage"
    And I click on "JobRelationName" in "AddNewEmployeePage"
    And I click on "IssueDate_Calender" in "AddNewEmployeePage"
    And I click on "IssueDateValue" in "AddNewEmployeePage"
    And I click on "Job_Continue" in "AddNewEmployeePage"
    And I click on "SpotBonusCalender" in "AddNewEmployeePage"
    And I click on "SpotBonusDate" in "AddNewEmployeePage"
    And I click on "SpotBonusType_Arrow" in "AddNewEmployeePage"
    And I click on "SpotBonusValue" in "AddNewEmployeePage"
    And I click on "CurrencyCode_Arrow" in "AddNewEmployeePage"
    And I click on "CurrencyCodeValue" in "AddNewEmployeePage"
    
   
   
   @SAP_Add_New_Employee1
		Scenario: Add New Employee with Invalid credentials
    Given I navigate to web application
		Then I should see "Page_Title" as "Login - SAP SuccessFactors"
	 	When I enter "Username" as "Admin5" in "LoginPage"
	 	And I enter "Password" as "Sap@12" in "LoginPage"
	 	And I click on "Login" in "LoginPage"	
	 	And I wait for page to load
	  Then I should see "Quick_Actions_Text" in "Homepage"
    
 
    
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	
	
	 			 		 