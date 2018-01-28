package modul_1;
public class Person {
	public String name, adress, phone, eMail;
	
	Person(String name, String adress, String phone, String eMail) {
		this.name = name;
		this.adress = adress;
		this.phone = phone;
		this.eMail = eMail;
	}
	
	public String toString() {
		return String.format("Name: %s%nAdress: %s%nPhone: %s%nE-mail: %s%n", name, adress, phone, eMail);
	}

}
