package pageObjects;

public class ManagePendingHiresPage {

	public static final String Select_Pending_Hires_Downarrow = "ID==#selectPendingHires-arrow";
	public static final String Drafts = "XPATH==(//*[contains(text(),'Drafts')])[3]";
	public static final String Caroline_Matthews = "TITLE==Caroline  Matthews";
	public static final String Manage_Pending_Hires = "text==Manage Pending Hires";
	public static final String Drafts_Recruting="XPATH==(//*[contains(text(),'Drafts (Recruiting)')])[1]";
	
	public static String Manage_Pending_Hires() {
		return Manage_Pending_Hires;
	}

	public static String Select_Pending_Hires_Downarrow() {
		return Select_Pending_Hires_Downarrow;
	}

	public static String Drafts() {
		return Drafts;
	}

	public static String Caroline_Matthews() {
		return Caroline_Matthews;
	}
	public static String Drafts_Recruting()
	{
		return Drafts_Recruting;
	}
	
}
