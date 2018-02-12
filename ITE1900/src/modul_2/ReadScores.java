package modul_2;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadScores {
	
	public static void main(String[] args) throws Exception {
		
		Scanner txtIn = new Scanner(System.in);
		
		System.out.print("Enter file name: ");
		String fileName = txtIn.next();
		
		txtIn.close();
		
		File sourceFile = new File(fileName);
		
		if (!sourceFile.exists()) {
			System.out.println("File " + sourceFile + " not found!");
			System.exit(0);
		}
		
		ArrayList<Integer> fileScores = new ArrayList<>();
		
		try (
			Scanner input = new Scanner(sourceFile);
		) { while (input.hasNextInt()) {
			fileScores.add(input.nextInt());
		}
		}
		
		double count = 0, scoreTotal = 0, scoreAvg;
		
		for (double d : fileScores) {
			count += 1;
			scoreTotal += d;
		}
		
		scoreAvg = scoreTotal/count;
		
		System.out.printf("%nTotal is: %.2f%nAverage is: %.2f", scoreTotal, scoreAvg);
	

	}

}