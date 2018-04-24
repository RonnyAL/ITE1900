package modul_6;

import java.util.concurrent.ExecutorService;

public class PrimeChecker {

	// Used to set up all threads and run them when needed ExecutorService
	private ExecutorService pool;
	
	// Variable used to define whether or not the number is a prime.
	private boolean isPrime;
	
	// Number which is suspected to be a prime number.
	private long number;

	// Number of threads to be created.
	private int numberOfThreads;

	// Array telling threads where to start checking if number is prime.
	private long[] startValues;

	// Array telling threads where to stop checking if number is prime.
	private long[] endValues;

	// Sets up and runs both types of prime tests and displays results.
	PrimeChecker() {
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// Initializes the multiple threads and their division of work loads.
	public void init() {

	}

	// Runs threads that check for primarity of specified number.
	public void runThreads() {

	}

	// Called from threads to indicate that number is not a prime, stops exection of
	// all threads in pool
	public void notPrime() {
		pool.shutdown();
	}

	// Singlethraded prime checking algortihm, same as the one used in multithreaded
	// approach.
	public boolean singleThreadedPrimeCheck() {
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