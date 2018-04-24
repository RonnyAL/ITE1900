package modul_6;

import java.util.concurrent.ExecutorService;

public class PrimeChecker {
	
	private ExecutorService pool;
	private boolean isPrime;
	private long number;
	private int numberOfThreads;
	private long[] startValues;
	private long[] endValues;
	
	PrimeChecker() {}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void init() {
		
	}
	
	public void runThreads() {
		
	}
	
	public void notPrime() {
		
	}
	
	public boolean singleThreadedPrimeCheck() {
		return true;
	}

}

//
class PrimeTask implements Runnable {
	private long number;
	private long startValue;
	private long endValue;
	
	PrimeTask(long number, long startValue, long endValue) {
		this.number = number;
		this.startValue = startValue;
		this.endValue = endValue;
	}

	@Override
	public void run() {
		
	}
	
	
}