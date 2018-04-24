package modul_6;

import java.util.concurrent.*;

public class PrimeChecker {

	// Variable used to define whether or not the number is a prime.
	private boolean isPrime;

	// Number which is suspected to be a prime number.
	private long number = 9223372036854775783L;

	// Number of threads to be created.
	private int numberOfThreads = 10;

	// Used to set up all threads and run them when needed ExecutorService
	private ExecutorService pool;

	// Array telling threads where to start checking if number is prime.
	private long[] startValues;

	// Array telling threads where to stop checking if number is prime.
	private long[] endValues;

	// Sets up and runs both types of prime tests and displays results.
	PrimeChecker() {

	}

	public static void main(String[] args) {
		PrimeChecker pc = new PrimeChecker();
		pc.init();

	}

	// Initializes the multiple threads and their division of work loads.
	public void init() {
		pool = Executors.newFixedThreadPool(numberOfThreads);
		runThreads();
		pool.shutdown();

		numberOfThreads = 1;
		pool = Executors.newSingleThreadExecutor();
		runThreads();
		pool.shutdown();
	}

	// Runs threads that check for primarity of specified number.
	public void runThreads() {
		int activeThreads = numberOfThreads;
		startValues = new long[numberOfThreads];
		endValues = new long[numberOfThreads];
		startValues[0] = 0;
		endValues[0] = number;
		
		if (activeThreads > 1) {
			for (int i = 1; i <= activeThreads; i++) {
				startValues[i-1] =  ((number / activeThreads) * (i-1));
				endValues[i-1] = (number / activeThreads * (long)i);
			}
		}
		
		for (int i = 0; i < startValues.length; i++) {
			System.out.println("Start: " + startValues[i] + " | End: " + endValues[i]);
		}
		
		System.exit(0);

		for (int i = 0; i < activeThreads; i++) {
			pool.execute(new PrimeTask(number, startValues[i], endValues[i]));
		}
	}

	// Called from threads to indicate that number is not a prime, stops exection of
	// all threads in pool
	public void notPrime() {
		pool.shutdown();
	}

	// Singlethraded prime checking algortihm, same as the one used in multithreaded
	// approach.
	public boolean singleThreadedPrimeCheck() {
		// Skisse
		Thread t = new Thread(new PrimeTask(0, 0, 0));
		t.start();
		return true;
	}

}

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