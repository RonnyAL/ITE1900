package modul_3;

import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;

public class TestAccount {

	public static void main(String[] args) {
		
		Date d1 = new GregorianCalendar(2016, 5, 14).getTime();
		Date d2 = new GregorianCalendar(2014, 3, 11).getTime();
		Date d3 = new GregorianCalendar(2011, 9, 7).getTime();

		Account a = new Account("Ronny Andr� L�kken", 1, 18000, d1);
		Account b = new Account("Wim Felix Mossige", 2, 53000, d1);
		Account c = new Account("Joakim Christopher Storfjell", 3, 11000, d2);
		Account d = new Account("Andreas Holm", 4, 19000, d3);
		
		Account[] accounts = new Account[]{a, b, c, d};
		Arrays.sort(accounts);
		
		for (Account acc : accounts) {
			System.out.println(acc.toString());
		}
		
	}
	
}
