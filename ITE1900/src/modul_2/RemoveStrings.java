package modul_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class RemoveStrings {

	public static void main(String[] args) {
		if (args.length != 2 || args[0] == null || args[1] == null) {
			System.out.println("Missing arguments! Program will terminate.");
			System.exit(1);
		}
		
		String stringToBeRemoved = args[0];
		File sourceFile = new File(args[1]);

		Scanner reader = null;
		StringBuilder sb = new StringBuilder();
		String lineBreak = System.getProperty("line.separator");
		
		try {
			reader = new Scanner(sourceFile, "UTF-8");
			while (reader.hasNextLine()) {
				sb.append(reader.nextLine().replaceAll(stringToBeRemoved, "") + lineBreak);
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
			System.out.printf("Error: File \"%s\" not found!%n%n", sourceFile);
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.out.printf("Error: %s encoding not supported!%n%n", e.getMessage());
			e.printStackTrace();
		}
		
		writer.flush();
		writer.print(sb.toString());
		writer.close();
		
		System.out.printf("Successfully removed all instances of string \"%s\" from file \"%s\".", stringToBeRemoved, sourceFile);
		
	}

}