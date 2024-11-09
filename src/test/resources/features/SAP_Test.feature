
Feature: Test Patient Registration Functionality
 
  #@EmployeeOnboarding
  #Scenario: Validate Vida Login Test Functionality With Valid Test Data
	 # Given I navigate to web application
#	 	Then I should see page title as "Login - SAP SuccessFactors"
#	 	And I see "VT=SFCPART001458" web element
	 	#And I enter "LABEL=User Name" as "blee"
	 	#And I enter "LABEL=Password" as "Sap@0101@"
	 	#When I click on "text=Log in"
#	 	Then I see "VT=Welcome to  RMS" web element
	  #And I enter "LABEL=Auto-complete search results" as "Manage pending hires"
	  #And I click on "text2=Manage Pending Hires"
	  #And I click on "ID=selectPendingHires-arrow"
	  
	@SAP_Login
	Scenario: Login Test
		Given I navigate to web application
	 	#Then sap I should see "Title"
#	 	When sap I enter "Username" as "blee"
	 	When I enter "Username" as "blee" in "SAP_LoginPage"
	 	And I enter "Password" as "Sap@0101@" in "SAP_LoginPage"
	 	And I click on "Login" in "SAP_LoginPage"	 
	 	
	 	
	 	
	 	
	 	 