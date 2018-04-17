package modul_6;
import java.util.concurrent.locks.*;

class Account {
	private int balance;
	private int accountNumber;
	private Lock lock;
	private Condition lockCondition;
	
	public Account(int accountNumber, int balance) {
		this.accountNumber = accountNumber;
		this.balance = balance;
		lock = new ReentrantLock();
		lockCondition = lock.newCondition();
	}

	
	public void withdraw(int amount) {
		
		lock.lock();

		try {
			while (balance < amount)
				lockCondition.await();
			balance -= amount;
		} catch (InterruptedException e) {
			//e.printStackTrace();
		} finally {
			lock.unlock();
		}
			
	}
	
	public void deposit(double amount) {
		lock.lock();
		
		try {
			balance += amount;
			lockCondition.signalAll();
		} finally {
			lock.unlock();
		}
	}
	//
	public int getAccountNumber() {
		return accountNumber;
	}
	
	public int getBalance() {
		return balance;
	}
	
	private Lock getLock() {
		return lock;
	}
	
}