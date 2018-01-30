package modul_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class RemoveStrings {

	public static void main(String[] args) {
		if (args.length < 2 || args[0] == null || args[1] == null) {
			System.out.println("Missing arguments! Program will terminate.%n%n");
			System.exit(1);
		}
		
		String stringToBeRemoved = args[0];
		File sourceFile = new File(args[1]);

		Scanner reader = null;
		String s1 ="";
		
		try {
			reader = new Scanner(sourceFile, "UTF-8");
			while (reader.hasNextLine()) {
				s1 += reader.nextLine() + "\r\n";
			}
		} catch (FileNotFoundException e) {
			System.out.printf("File \"%s\" not found! Program will terminate.%n%n", sourceFile);
			e.printStackTrace();
		}
		
		reader.close();
	
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(sourceFile, "UTF-8");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.out.printf("Error: %s encoding not supported!%n%n", e.getMessage());
			e.printStackTrace();
		}
		
		s1 = s1.replaceAll(stringToBeRemoved, "");
		writer.flush();
		writer.print(s1);
		writer.close();
		
		System.out.printf("Successfully removed all instances of string \"%s\" from file %s.", stringToBeRemoved, sourceFile);
		
	}

}