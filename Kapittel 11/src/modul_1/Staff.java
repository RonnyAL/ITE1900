package modul_1;

public class Staff extends Employee {

	private int title;
	public static final int JANITOR = 1;
	public static final int LIFT_ELECTRICIAN = 2;
	public static final int BARTENDER = 3;
	public static final int ASSISTANT = 4;
	public String titleString; 
	
	public Staff(int title, double salary, String name, String adress, String phone, String eMail) {
		super(salary, name, adress, phone, eMail);
		this.title = title;
	}
	
	String getTitleAsString(int title) {
		
		switch(title) {
			case 1:
				titleString = "Janitor";
				break;
			case 2:
				titleString ="Lift electrician";
				break;
			case 3:
				titleString = "Bartender";
				break;
			case 4:
				titleString = "Assistant";
				break;
			default:
				titleString = "UNDEFINED!";
				break;
		}
		return titleString;
	}
	
	 public String toString() {
			return String.format("%nStaff:%nTitle: %s%n%s", this.getTitleAsString(title), super.toString());
	 }

}
