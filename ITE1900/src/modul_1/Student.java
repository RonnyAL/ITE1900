package modul_1;

public class Student extends Person {
	private int year;
	private int course;
	public static final int COMPUTER_SCIENCE = 1;
	public static final int PHILOSOPHY = 2;
	public static final int PSYCHOLOGY = 3;
	public static final int ARCHEOLOGY = 4;
	
	Student(int year, int course, String name, String adress, String phone, String eMail) {
		super(name, adress, phone, eMail);
		this.year = year;
		this.course = course;
	}

	 private String getCourseAsString(int course) {
		 String courseString;
		 switch(course) {
		 case 1:
			 courseString = "Computer science";
			 break;
		 case 2:
			 courseString = "Philosophy";
			 break;
		 case 3:
			 courseString = "Psychology";
			 break;
		 case 4:
			 courseString = "Archeology";
			 break;
		 default:
			 courseString = "UNDEFINED!";
		 }
		 
		 return courseString;
	 }
	 
	 public String toString() {
			return String.format("Student:%nYear: %s%nCourse: %s%n%s", year, this.getCourseAsString(course), super.toString());
	 }
}
