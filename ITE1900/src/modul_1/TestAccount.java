package modul_1;
import java.util.ArrayList;

public class TestAccount {

	public static void main(String[] args) {
		Account myAccount = new Account("Gunnar Flesk", 1, 2000);
		myAccount.setAnnualInterestRate(5.5);
		
		myAccount.deposit(3000, "Savings");
		myAccount.deposit(500, "Allowance");
		myAccount.deposit(140, "Sold gravel");
		myAccount.withdraw(1000, "Traveling money");
		myAccount.withdraw(90, "Snus");
		myAccount.withdraw(350, "Birthday present");
		
		ArrayList<Transaction> myTransactions = myAccount.getTransactions();
		
		System.out.println("Name: " + myAccount.getName());
		System.out.println("Annual interest rate: " + myAccount.getAnnualInterestRate());
		System.out.printf("Balance: %.2f%n%n", myAccount.getBalance());
		System.out.printf("Date\t\t\tType\tAmount\tBalance\tDescription%n");
		
		for (Transaction t : myTransactions) {
			System.out.printf("%s%n", t.toString());
		}
	}

}
