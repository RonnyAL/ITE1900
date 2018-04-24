package modul_6;

import java.util.concurrent.*;

public class ArraySearch {
	private final static int numToFind = 1000000;
	private final static int arraySize = 1000000;
	private final static int poolSize = 10;
	private final static ExecutorService pool = Executors.newFixedThreadPool(poolSize);
	private final static int chunkSize = (int) arraySize / poolSize;
	private static boolean found = false;
	private static int[] bigArray = new int[arraySize];

	public static synchronized void main(String[] args) {

		for (int i = 0; i < bigArray.length; i++) {
			bigArray[i] = i + 1;
		}

		for (int startIndex = 0; startIndex < arraySize; startIndex += chunkSize) {
			pool.execute(new SearchTask(startIndex, startIndex + chunkSize - 1, numToFind));
		}

		pool.shutdown();

		try {
			pool.awaitTermination(5, TimeUnit.SECONDS);
			if (!found) {
				System.out.printf("The number %s was not found in the array", numToFind);
			}
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	private static class SearchTask implements Runnable {
		private int startIndex;
		private int endIndex;
		private int x;

		SearchTask(int startIndex, int endIndex, int x) {
			this.startIndex = startIndex;
			this.endIndex = endIndex;
			this.x = x;
		}

		@Override
		public void run() {
			for (int i = startIndex; i <= endIndex; i++) {
				if (bigArray[i] == x && !found) {
					found = true;
					System.out.printf("Thread \"%s\" found the number %d at index %d.%s",
							Thread.currentThread().getName(), x, i, System.lineSeparator());
					pool.shutdown();
				}

			}

		}

	}
}