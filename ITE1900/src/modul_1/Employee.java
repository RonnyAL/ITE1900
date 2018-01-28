package modul_1;
import java.time.LocalDateTime;

public class Employee extends Person {
	
	public LocalDateTime hiredDate;
	public double salary;
	
	public Employee(double salary, String name, String adress, String phone, String eMail) {
		super(name, adress, phone, eMail);
		hiredDate = LocalDateTime.now();
		this.salary = salary;
	}
	
	public String toString() {
		return String.format("Salary: %.0f %nDate hired: %tF %n%s", salary, hiredDate, super.toString());
	}

}
