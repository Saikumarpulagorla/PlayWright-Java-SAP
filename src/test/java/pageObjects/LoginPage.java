package pageObjects;

public class LoginPage{
 public static final String Title = "Title==Login - SAP SuccessFactors";
 public static final String Username = "LABEL==User Name";
 public static final String Password = "LABEL==Password";
 public static final String Login = "XPATH==//*[text()='Log in']";
 
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
