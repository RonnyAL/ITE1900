package modul_1;

public class TestPersons {

	public static void main(String[] args) {
		Student s = new Student(Student.COMPUTER_SCIENCE, 2, "Jens", "Oterveien 2", "45809966", "rlo@post.uit.no");
		Staff st = new Staff(Staff.BARTENDER, 150000, "Ottar", "Gåseveien 4", "112", "ottar@gmail.com");
		Faculty f = new Faculty("E3100", 3, 80000, "Sven-Åge", "Bruspulverveien 32", "113", "svenaage@yahoo.za");
		
		System.out.printf(s.toString());
		System.out.printf(st.toString());
		System.out.printf(f.toString());

	}

}
