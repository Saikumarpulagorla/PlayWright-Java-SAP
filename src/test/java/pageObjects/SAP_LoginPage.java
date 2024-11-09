package pageObjects;

public class SAP_LoginPage{
 public String Title = "Title=Login - SAP SuccessFactors";
 public static final String Username = "Label=User Name";
 public static final String Password = "Label=Password";
 public static final String Login = "text=Log in";
 public static final String NewTest = "Text=Log in";
 
 public String Title() {
		return Title;
	}
 public static String Username() {
	 return Username;
 }
 public static String Password() {
	 return Password;
 }
 public static String Login() {
	 return Login;
 }
}
