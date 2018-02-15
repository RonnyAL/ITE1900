package modul_3;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;



public interface Fileable {
	String LINE_SEPARATOR = String.format("\r\n");
	
	void writeData(PrintWriter out) throws IOException;
	void readData(Scanner in) throws IOException;
}