package modul_6;

import java.util.concurrent.locks.Lock;

public class Bank {
	
	final static int TO_STRING_FREQUENCY = 0;
	final static int TEST_FREQUENCY = 10000;
	private Lock lock;
	private long transactionCount;
	private int deviationCount;
	private int initialBalance;
	private Account[] accounts;
	private boolean debug;
	private int testCount;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void transfer(int from, int to, int amount) {
		
	}
	
	public void test() {
		
	}
	
	@Override
	public String toString() {
		if (debug) {
			return "x";
		}
		return "y";
	}
	
	public int numberOfAccounts() {
		return 0;
	}
	
	public long getTransactionCount() {
		return 0L;
	}
	
	public int getDeviationCount() {
		return 0;
	}
	
	public double getErrorPercentage() {
		return 0;
	}

}
