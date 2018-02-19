package modul_2;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class WriteEmployeeList {

	public static void main(String[] args) {
		
		int salary = 0;
		String[] rank = new String[3];
		
		rank[0] = "Assistant";
		rank[1] = "Associate";
		rank[2] = "Manager";
		
		int i = 0;
		
		try (PrintWriter writer = new PrintWriter("EmployeeList.txt")) {
			writer.printf("First name:\tLast name:\tRank:\t\tSalary:%n");
			String rankString;
			while (i < 1000) {
				rankString = rank[(int)(Math.random()*3)];
				switch(rankString) {
				case "Assistant":
					salary = 50000 + (int)(Math.random() * 30000);
					break;
				case "Associate":
					salary = 60000 + (int)(Math.random() * 40000);
					break;
				case "Manager":
					salary = 75000 + (int)(Math.random() * 65000);
					break;
				}
				
				i++;
				writer.printf("Firstname%04d\tLastname%04d\t%-16s%d.00", i, i, rankString, salary);
				if (i < 1000)
					writer.println();
			}
		} catch (FileNotFoundException e) {
			System.out.printf("File not found!%n%n");
			e.printStackTrace();
		}
		
		System.out.println(i + " employees added successfully!");
		
	}

}