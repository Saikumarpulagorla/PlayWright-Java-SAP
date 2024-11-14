package pageObjects;

public class Homepage  {
	
	public static final String Searchbar = "LABEL==Auto-complete search results";
	public static final String Manage_Pending_Hires = "TEXT==Manage Pending Hires";
	public static final String Quick_Actions = "ID==#QUICK_ACTIONS-sectionLabel";
	
	public static String Quick_Actions() {
		return Quick_Actions;
	}
	
	public static String Searchbar() {
		return Searchbar;
	}
	
	public static String Manage_Pending_Hires() {
		return Manage_Pending_Hires;
	}

}
