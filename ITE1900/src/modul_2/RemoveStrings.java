package modul_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class RemoveStrings {

	public static void main(String[] args) {
		if (args.length < 2 || args[0] == null || args[1] == null) {
			System.out.println("Missing arguments! Program will terminate.");
			System.exit(1);
		}
		
		String stringToBeRemoved = args[0];
		File sourceFile = new File(args[1]);

		Scanner reader = null;
		StringBuilder sb = new StringBuilder();
		
		try {
			reader = new Scanner(sourceFile);
			while (reader.hasNext()) {
				sb.append(reader.nextLine());
				sb.replace(sb.indexOf(stringToBeRemoved), stringToBeRemoved.length()-1, "ok");
			}
		} catch (FileNotFoundException e) {
			System.out.printf("File \"%s\" not found. Program will terminate.", sourceFile);
			e.printStackTrace();
		}
		
		System.out.println(sb.toString());
		
		reader.close();
	
		try {
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(sourceFile), "UTF-8"));
			writer.print(sb.toString());
			writer.close();
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		System.out.println("Ferdig.");

		
	}

}
