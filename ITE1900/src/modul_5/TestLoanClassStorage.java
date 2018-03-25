package modul_5;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class TestLoanClassStorage {

	private static ArrayList<Loan> loans = new ArrayList<Loan>();
	private final static String FILEPATH = "test.txt"; // Oppgi filnavn for lagring av låneobjekter
	private static Scanner input = new Scanner(System.in);
	private final static String LS = System.lineSeparator();

	/** Main method */
	public static void main(String[] args) {
		
		// Henter lån som evt. finnes i fil og viser info om hvert lån
		loadLoans(new File(FILEPATH));
		if (loans.size() > 0) {
			for (int i = 0; i < loans.size(); i++) {
				System.out.printf("Lån %s:%sLånebeløp: %.2f%sMånedlig betaling: %.2f%s%s", i + 1, LS,
						loans.get(i).getLoanAmount(), LS, loans.get(i).getMonthlyPayment(), LS, LS);
			}
		}
		
		// Be om input for å opprette nytt lån
		createNewLoan();
		
	}

	private static void createNewLoan() {
		double annualInterestRate = 0, loanAmount = 0;
		
		// Enter yearly interest rate
		System.out.print("Enter yearly interest rate (double): ");
		
		try {
			annualInterestRate = Double.parseDouble(input.next().replaceAll(",", "."));
		} catch (NumberFormatException e) {
			System.err.println("Ugyldig tallformat, applikasjonen avsluttes.");
			System.exit(0);
		}
		

		// Enter number of years
		System.out.print("Enter number of years (integer): ");
		int numberOfYears = input.nextInt();

		// Enter loan amount
		System.out.print("Enter loan amount (double): ");
		try {
			loanAmount = Double.parseDouble(input.next().replaceAll(",", "."));
		} catch (NumberFormatException e) {
			System.err.println("Ugyldig tallformat, applikasjonen avsluttes.");
			System.exit(0);
		}

		// Create Loan object
		Loan loan = new Loan(annualInterestRate, numberOfYears, loanAmount);

		// Display loan date, monthly payment, and total payment
		System.out.printf("The loan was created on %s\n" + "The monthly payment is %.2f\nThe total payment is %.2f\n",
				loan.getLoanDate().toString(), loan.getMonthlyPayment(), loan.getTotalPayment());

		if (ynMenu("Lagre lånet til fil?") == "J") {
			saveLoan(loan, new File(FILEPATH));
		}

		if (ynMenu("Opprett nytt lån?") == "J") {
			createNewLoan();
		} else {
			System.exit(0);
		}
	}

	private static String ynMenu(String question) {
		String userInput = null;
		System.out.println();
		while (userInput == null) {
			System.out.print(question + " [J/N] ");
			switch (input.next()) {
			case "J":
			case "j":
				userInput = "J";
				break;
			case "N":
			case "n":
				userInput = "N";
				break;
			default:
				break;
			}
		}
		return userInput;

	}

	private static void saveLoan(Loan loan, File file) {
		try {
			boolean exists = file.exists();
			exists = (file.length() > 0);
			FileOutputStream fout = new FileOutputStream(file, true);
			ObjectOutputStream oout = exists ? new ObjectOutputStream(fout) {
				protected void writeStreamHeader() throws IOException {
					reset();
				}
			} : new ObjectOutputStream(fout);
			oout.writeObject(loan);
			oout.close();
			fout.close();

			System.out.printf("Låneobjektet ble lagret i filen \"%s\".%s", file.getAbsolutePath(), LS);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void loadLoans(File file) {
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			while (fis.available() > 0) {
				try {
					loans.add((Loan) ois.readObject());
				} catch (Exception e) {
					ois.close();
					return; // Mangler skriverettigheter (?)
				}
			}
			ois.close();
			System.out.printf("Lastet %s låne-objekt%s fra \"%s\"%s%s%s", loans.size(), loans.size() == 1 ? "" : "er",
					file.getAbsolutePath(), loans.size() > 0 ? ":" : ".", LS, System.lineSeparator());
		} catch (EOFException eof) {
			System.err.print(String.format("Fant ingen låneobjekter i \"%s\".%s%s", file.getAbsolutePath(), LS, LS));
		} catch (Exception e) {
			System.out.printf("En feil oppstod: %s", e);
		}
	}
}