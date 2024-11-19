package pageObjects;

public class AddNewEmployeePage {
	 public static final String Add_New_Employee="text==Add New Employee";
	 public static final String First_Name="XPATH==(//*[text()='First Name'])[1]/../../../..//input";
	 public static final String Last_Name="XPATH==(//*[@title='Last Name']/../..//input)[1]";
	 public static final String DateOfBirth_Calender="XPATH==//*[contains(text(),'Date Of Birth')]/../../../..//input[@placeholder='MMM dd, yyyy']/..//span";
	 public static final String DateOfBirth="XPATH==//*[@class='sapUiCalItemText' and text()='20']";
	 public static final String CountryOfBirth="XPATH==//*[text()='Country Of Birth']/../../../..//div//input";
	 public static final String CountryOfBirth_Dropdown="LABEL==Person Info";
	 public static final String DownArrow="LABEL==Select Options";
	 public static final String Australia = "text==Australia";
	 public static final String NationalIDCardCountryArrow="XPATH==(//*[@title='Country']/../../../../..//*[@role='button'])[1]";
	 public static final String Country_Australia="text==Australia";
	 public static final String TFN_Arrow="XPATH==(//*[@title='Country']/../../../../..//*[@role='button'])[2]";
     public static final String TFN_Value="XPATH==//*[text()='TFN - Tax File Number']";
     public static final String NationalId_Value="XPATH==(//*[@title='National Id']/../../../../..//input)[3]";
	 public static final String IsPrimary_Arrow="XPATH==(//*[@title='Country']/../../../../..//*[@role='button'])[3]";
	 public static final String IsPrimary_Value="XPATH==//*[text()='Yes']";
	 public static final String Continue="XPATH==//*[text()='Continue']";
	 
	
	 public static final String Show_more_fields="ID==#detailsBtn_0-BDI-content";
	 public static final String NationalityDropDown="XPATH==(//*[@class='sapMInputBaseIconContainer'])[16]//span";
	 public static final String India_Nationality="XPATH==(//*[text()='Australia'])[2]";
	 public static final String Native_Preferred_Language_Dropdown="XPATH==(//*[@class='sapMInputBaseIconContainer'])[17]//span";
	 public static final String Native_Preferred_Laguage="XPATH==//*[text()='American Sign Language']";
	 public static final String Personal_Country_Dropdown="XPATH==(//*[@class='sapMInputBaseIconContainer'])[21]//span";
	 public static final String Personal_Country_Value="text==Australia";
	 public static final String EmailTypeDropdown="XPATH==(//*[text()='Email Type']/ancestor::table//span[@role='button'])[1]";
	 public static final String PersonalTypeEmail="text==Personal";
	 public static final String EmailAddress="XPATH==(//*[text()='Email Address']/ancestor::table//*[@type='text'])[2]";
	 public static final String Email_IsPrimaryArrow="XPATH==(//*[text()='Email Type']/ancestor::table//span[@role='button'])[2]";
	 public static final String Email_IsPrimaryValue="text==Yes";
	 public static final String PhoneType_HomeArrow="XPATH==(//*[text()='Phone Type']/ancestor::table//span[@role='button'])[1]";
	 public static final String PhoneType_HomeValue="XPATH==(//*[text()='Home'])[1]";
	 public static final String PhoneNumber="XPATH==(//table//td[5]//div/input)[2]";
	 public static final String ExtensionIsPrimary_Arrow="XPATH==(//*[text()='Phone Type']/ancestor::table//span[@role='button'])[2]";
	 public static final String ExtensionValueYes="XPATH==(//*[text()='Yes'])[2]";
	 public static final String AddPersonalContacts="XPATH==//*[text()='Add Personal Contacts']";
	 public static final String PrimaryArrow="XPATH==((//*[text()='Personal Contacts'])[1]/../../../../../..//*[@role='button'])[3]";
	 public static final String PrimaryYes="text==Yes";
	 public static final String ContactName="ID==#__input72-inner";
	 public static final String ExtensionIsPrimary_2Arrow="XPATH==(//*[text()='Phone Type']/ancestor::table//span[@role='button'])[3]";
	 public static final String ExtensionValueNo="XPATH==(//*[text()='No'])[2]";
	 public static final String InstaMessagingID="XPATH==(//table//td[4]/div//input)[4]";
	 public static final String Home_AddressType_Arrow="XPATH==//*[text()='Address Type']/../../../..//span[@role='button']";
	 public static final String Home_AddressValue="text==Vacation";
	 public static final String Region_Arrow="XPATH==(//*[text()='Address Type']/../../../../../..//*[@role='button'])[2]";
	 public static final String Region_Value="text==Australia";
	 public static final String Dependents_FirstName="XPATH==(//*[text()='First Name'])[5]/../../../..//div//input[@type='text']";
	 public static final String Dependents_LastName="XPATH==(//*[text()='Last Name'])[5]/../../../..//div//input[@type='text']";
	 public static final String RelationShipArrow="XPATH==(//*[text()='Last Name'])[5]/../../../../../../..//div[4]//span[@role='button']";
	 public static final String RelationShipValue="text==Domestic Partner";
	 public static final String Continue2="text==Continue";
	 
	 
	 public static final String Company_Arrow="XPATH==(//label[@title='Company'])[2]/../../..//*[@role='button']";
	 public static final String Company = "LABEL==Available Values";
	 public static final String Ace_Australia="text==Ace Australia (ACE_AUS)";
	 public static final String Yes="ID==#__mbox-btn-5-BDI-content";
	 public static final String JobClassification_Arrow="XPATH==(//label[@title='Company'])[2]/../../..//*[@role='button']";
	 public static final String Analyst="text==Analyst (ANALYST-IT)";
	 public static final String ShowMoreFields="XPATH==//*[text()='Show 20 more fields']";
	 //public static final String JobClassification="XPATH==//*[text()='Job Classification']/../../../..//input[@type='text']";
	 public static final String HireDate_Calender="XPATH==((//label[@title='Hire Date'])[2]/../..//span)[3]";
	 public static final String Date="text==20";
	 public static final String HireDate="XPATH==//*[@data-sap-day='20130601']";
	 public static final String Job_Add="XPATH==(//*[text()='Add'])[5]";	
	 public static final String RelationTypeArrow="XPATH==(//table//td[2]//div/div/span)[5]";
	 public static final String RelationType_Value="XPATH==//*[text()='HR Manager']";
	 public static final String Name="XPATH==(//input[@type='text' and @class='sapMInputBaseInner' and @role='combobox'])[3]";
	 public static final String JobRelationName="XPATH==//*[@class='surjGacePhotoFrame']";
	 public static final String IssueDate_Calender="XPATH==(//label[@title='Issue Date']/../../..//span)[3]";
	 public static final String IssueDateValue="XPATH==//*[@data-sap-day='20241113']";
	 public static final String Job_Continue="XPATH==//*[text()='Add Work Permit Info']/../../../../../../../../..//*[text()='Continue']";
	 
	 
	 public static final String SpotBonusCalender="XPATH==((//*[@title='Issue Date'])[2]/../../../../..//span[@role=\"presentation\"])[1]";
	 public static final String SpotBonusDate="XPATH==//*[@id='__picker18-cal--Month0-20241114']";
	 public static final String SpotBonusType_Arrow="XPATH==//*[@title='Type']/../../../../..//tbody//tr//td[3]//span[@role='button']";
	 public static final String SpotBonusValue="XPATH==//*[text()='Spot Bonus (SPOT)']";
	 public static final String CurrencyCode_Arrow="XPATH==//*[@title='Currency Code']/../../../../..//tbody//tr//td[4]//span[@role='button']";
	 public static final String CurrencyCodeValue="text==AUD";
	 
	 
	 public static String Add_New_Employee()
	 {
		 return Add_New_Employee;
	 }
	 public static String First_Name()
	 {
		 return First_Name;
	 }
	 public static String Last_Name()
	 {
		 return Last_Name;
	 }
	 public static String Continue()
	 {
		 return Continue;
	 }
	 public static String Show_more_fields()
	 {
		 return Show_more_fields;
	 }
	 public static String NationalityDropDown()
	 {
		 return NationalityDropDown;
	 }
	 public static final String India_Nationality()
	 {
		 return India_Nationality;
	 }
	 public static String Native_Preferred_Language_Dropdown()
	 {
		 return Native_Preferred_Language_Dropdown;
	 }
	 public static String Native_Preferred_Laguage()
	 {
		 return Native_Preferred_Laguage;
	 }
	 public static String DateOfBirth_Calender()
	 {
		 return DateOfBirth_Calender;
	 }
	 public static String DateOfBirth()
	 {
		 return DateOfBirth;
	 }
	 public static String CountryOfBirth_Dropdown()
	 {
		 return CountryOfBirth_Dropdown;
	 }
	 public static String CountryOfBirth()
	 {
		 return CountryOfBirth;
	 }
	 public static String DownArrow()
	 {
		 return DownArrow;
	 }
	 
	 public static String Australia()
	 {
		 return Australia;
	 }

	 public static String NationalIDCardCountryArrow()
	 {
		 return NationalIDCardCountryArrow;
	 }
	 public static String TFN_Arrow()
	 {
		 return TFN_Arrow;
	 }
	 public static String Country_Australia()
	 {
		 return Country_Australia;
	 }
	 public static String TFN_Value()
	 {
		 return TFN_Value;
	 }
	 public static String NationalId_Value()
	 {
		 return NationalId_Value;
	 }
	 public static String ExtensionIsPrimary_Arrow()
	 {
		 return ExtensionIsPrimary_Arrow;
	 }
	 public static String IsPrimary_Value()
	 {
		 return IsPrimary_Value;
	 }
	 public static String Personal_Country_Dropdown() {
		 return Personal_Country_Dropdown;
		 
	 }
	 public static String IsPrimary_Arrow()
	 {
		 return IsPrimary_Arrow;
	 }
// 
	 
	 public static String Personal_Country_Value() {
		 return Personal_Country_Value;
		 
	 }
	 public static String EmailTypeDropdown()
	 {
		 return EmailTypeDropdown;
	 }
	 public static String PersonalTypeEmail()
	 {
		 return PersonalTypeEmail;
	 }
	 public static String EmailAddress()
	 {
		 return EmailAddress;
	 }
	 public static String Email_IsPrimaryArrow()
	 {
		 return Email_IsPrimaryArrow;
	 }
	 public static String Email_IsPrimaryValue()
	 {
		 return Email_IsPrimaryValue;
	 }
	 public static String PhoneType_HomeArrow()
	 {
		 return PhoneType_HomeArrow;
	 }
	 public static String PhoneType_HomeValue()
	 {
		 return PhoneType_HomeValue;
	 }
	 public static String PhoneNumber()
	 {
		 return PhoneNumber;
	 }
	 public static String InstaMessagingID()
	 {
		 return InstaMessagingID;
	 }
	 public static String Home_AddressType_Arrow()
	 {
		 return Home_AddressType_Arrow;
	 }
	 public static String Home_AddressValue()
	 {
		 return Home_AddressValue;
	 }
	 public static String Region_Arrow()
	 {
		 return Region_Arrow;
	 }
	 public static String Region_Value()
	 {
		 return Region_Value;
	 }
	 public static String Dependents_FirstName()
	 {
		 return Dependents_FirstName;
	 }
	 public static String Dependents_LastName()
	 {
		 return Dependents_LastName;
	 }
	 public static String RelationShipArrow()
	 {
		 return RelationShipArrow;
	 }
	 public static String RelationShipValue()
	 {
		 return RelationShipValue;
	 }
	 public static String Continue2()
	 {
		 return Continue2;
	 }
	 public static String Company_Arrow()
	 {
		 return Company_Arrow;
	 }
	 public static String Ace_Australia()
	 {
		 return Ace_Australia;
	 }
	 public static String JobClassification_Arrow()
	 {
		 return JobClassification_Arrow;
	 }
	 public static String Analyst()
	 {
		 return Analyst;
	 }
	 public static String ShowMoreFields()
	 {
		 return ShowMoreFields;
	 }
	 
	 public static String Company()
	 {
		 return Company;
	 }
	 
	 public static String Yes()
	 {
		 return Yes;
	 }
	 
 public static String HireDate_Calender ()
 {
	 return HireDate_Calender;
 }
//	 
	 public static String HireDate ()
	 {
		 return HireDate ;
	 }
	 public static String Job_Add()
	 {
		 return Job_Add;
	 }
	 public static String RelationTypeArrow ()
	 {
		 return RelationTypeArrow;
	 }
//		 
		 public static String RelationType_Value ()
		 {
			 return RelationType_Value ;
		 }
		 public static String Name()
		 {
			 return Name;
		 }
		 public static String ExtensionValueYes()
		 {
			 return ExtensionValueYes;
		 }
		 
		 public static String ExtensionValueNo()
		 {
			 return ExtensionValueNo;
		 }
		 public static String ExtensionIsPrimary_2Arrow()
		 {
			 return ExtensionIsPrimary_2Arrow;
		 }
		 
		 public static String AddPersonalContacts()
		 {
			 return AddPersonalContacts;
		 }
		 public static String PrimaryYes()
		 {
			 return PrimaryYes;
		 }
		 public static String PrimaryArrow()
		 {
			 return PrimaryArrow;
		 }
		 public static String ContactName()
		 {
			 return ContactName;
		 }
		 public static String JobRelationName()
		 {
			 return JobRelationName;
		 }
		 public static String IssueDate_Calender()
		 {
			 return IssueDate_Calender;
		 }
		 public static String IssueDateValue()
		 {
			 return IssueDateValue;
		 }
		 public static String Job_Continue()
		 {
			 return Job_Continue;
		 }
		 public static String SpotBonusCalender()
		 {
			 return SpotBonusCalender;
		 }
		 public static String SpotBonusType_Arrow()
		 {
			 return SpotBonusType_Arrow;
		 }
		 public static String SpotBonusDate()
		 {
			 return SpotBonusDate;
		 }
		 public static String SpotBonusValue()
		 {
			 return SpotBonusValue;
		 }
		 public static String CurrencyCode_Arrow()
		 {
			 return CurrencyCode_Arrow;
		 }
		 public static String CurrencyCodeValue()
		 {
			 return CurrencyCodeValue;
		 }
//	 
}
