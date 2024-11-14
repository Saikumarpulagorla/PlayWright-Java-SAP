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