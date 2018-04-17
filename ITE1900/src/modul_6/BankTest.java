package modul_6;

import java.util.ArrayList;

public class BankTest {
	final static boolean DEBUG = false;
	final static int ACCOUNT_AMOUNT = 10;
	final static int INITIAL_BALANCE = 10000;

	public static void main(String[] args) {
		
		Bank b = new Bank(ACCOUNT_AMOUNT, INITIAL_BALANCE, DEBUG);
		
		for (int i = 0; i < ACCOUNT_AMOUNT; i++) {
			AccountThreads t = new AccountThreads(b, i, INITIAL_BALANCE, DEBUG);
			t.run();
		}
		
		

	}

}
//