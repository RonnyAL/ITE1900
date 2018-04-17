package modul_6;

import java.util.ArrayList;
import java.util.concurrent.locks.*;

public class Bank {
	
	final static int TO_STRING_FREQUENCY = 0;
	final static int TEST_FREQUENCY = 10000;
	private Lock lock;
	private long transactionCount;
	private int deviationCount;
	private int initialBalance;
	ArrayList<Account> accounts = new ArrayList<Account>();
	private boolean debug;
	private int testCount;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//
	}
	
	Bank(int accountAmount, int initialBalance, boolean debug) {
		testCount = 0;
		transactionCount = 0;
		lock = new ReentrantLock();
		for (int i = 0; i < accountAmount; i++) {
			accounts.add(new Account(i, initialBalance));
		}
	}
	
	public void transfer(int from, int to, int amount) {
		transactionCount += 1;
		lock.lock();
		if (transactionCount % TO_STRING_FREQUENCY == 0)
			toString();
		if (transactionCount % TEST_FREQUENCY == 0)
			test();
		lock.unlock();
		accounts.get(from).withdraw(amount);
		accounts.get(to).deposit(amount);
		
	}
	
	public void test() {
		testCount += 1;
		int actualAmount = 0;
		for (int i = 0; i < accounts.size(); i++) {
			actualAmount += accounts.get(i).getBalance();
		}
		boolean error = (actualAmount == (accounts.size() * initialBalance));
		// TODO: Hva skal denne egentlig gjøre??
		
	}
	
	@Override
	public String toString() {
		if (debug) {
			return "x";
		}
		return "y";
	}
	
	public int numberOfAccounts() {
		return accounts.size();
	}
	
	public long getTransactionCount() {
		return transactionCount;
	}
	
	public int getDeviationCount() {
		return deviationCount;
	}
	
	public double getErrorPercentage() {
		return 0;
	}

}
