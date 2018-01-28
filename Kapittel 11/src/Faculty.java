
public class Faculty extends Employee{

	private String kontorNr;
	private int rank;
	
	public static final int LECTURER = 1;
	public static final int PROFESSOR = 2;
	public static final int INTERN = 3;
	public static final int STUDENT_ASSISTANT = 4;
	
	public Faculty(String kontorNr, int rank, double salary, String name, String adress, String phone, String eMail) {
		super(salary, name, adress, phone, eMail);
		this.kontorNr = kontorNr;
		this.rank = rank;
	}
	
	String getRankAsString(int rank) {
		String rankString;
		switch(rank) {
			case 1:
				rankString = "Lecturer";
				break;
			case 2:
				rankString = "Professor";
				break;
			case 3:
				rankString = "Intern";
				break;
			case 4:
				rankString = "Student assistant";
				break;
			default: 
				rankString = "UNDEFINED!";
				break;
		}
			
		return rankString;
				
	}
	
	public String toString() {
		return String.format("%nFaculty:%nOffice: %s%nRank: %s%n%s", kontorNr, this.getRankAsString(rank), super.toString());
	}

}
