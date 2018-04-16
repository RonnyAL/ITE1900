package modul_6;

import java.util.Random;

public class AccountThreads {
	
	private Bank bank;
	private boolean debug;
	private int accountIndex;
	private int maxTransferAmount;
	private Random random;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public AccountThreads(Bank bank, int accountIndex, int maxTransferAmount, boolean debug) {
		int ms;
		if (debug)
			ms = 2;
		else
			ms = 2 + random.nextInt(14);
		
		// ms = ventetid i millisekund.
		
	}
	
	public void run() {
		this.run();
	}

}
