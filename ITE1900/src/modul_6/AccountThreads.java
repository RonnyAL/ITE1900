package modul_6;

import java.util.Random;

public class AccountThreads extends Thread {

	private Bank bank;
	private boolean debug;
	private int accountIndex;
	private int maxTransferAmount;
	private Random random = new Random();
	private static final int REPS = 1000;
	//

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public AccountThreads(Bank bank, int accountIndex, int maxTransferAmount, boolean debug) {
		this.bank = bank;
		this.accountIndex = accountIndex;
		this.maxTransferAmount = maxTransferAmount;
		this.debug = debug;
	}

	public void run() {
		int ms = debug ? 2 : 2 + random.nextInt(14);
		int amount;
		System.out.println("hei");
		
		while(true) {
			try {
				amount = random.nextInt(maxTransferAmount);
				bank.accounts.get(accountIndex).withdraw(amount);
				bank.accounts.get(random.nextInt(bank.accounts.size()+1)).deposit(amount);;
				System.out.println(bank.accounts.get(accountIndex));
				System.out.println(bank.accounts.get(accountIndex).getBalance());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}
}


