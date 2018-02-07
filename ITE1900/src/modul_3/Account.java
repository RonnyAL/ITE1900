package modul_3;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

class Account implements Comparable<Account> {
	private String name;
	private ArrayList<Transaction> transactions = new ArrayList<>();
	private int id;
	private double balance;
	private double annualInterestRate;
	private Date dateCreated;
	
	public Account() {
		dateCreated = new Date(System.currentTimeMillis());
	}
	
	public Account(String name, int id, double balance) {
		this.name = name;
		this.id = id;
		this.balance = balance;
	}
	
	public Account(String name, int id, double balance, Date dateCreated) {
		this.name = name;
		this.id = id;
		this.balance = balance;
		this.dateCreated = dateCreated;
	}
	
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}
	
	public void setAnnualInterestRate(double annualInterestRate) {
		this.annualInterestRate = annualInterestRate;
	}
	
	public double getAnnualInterestRate() {
		return annualInterestRate; 
	}
	
	public String getDateCreated() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
		return sdf.format(this.dateCreated.getTime());
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public String getName() {
		return name;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public double getMonthlyInterest() {
		return balance*(annualInterestRate / 1200);
	}
	
	public void withdraw(double amount, String description) {
		if (amount > 0 && amount <= balance) {
			balance -= amount;
			transactions.add(new Transaction('W', amount, balance, description));
		}	
	}
	
	public void deposit(double amount, String description) {
		if (amount > 0) {
			balance += amount;
			transactions.add(new Transaction('D', amount, balance, description));
		}
	}
	
	@Override
	public int compareTo(Account a) {
		
		if (this.balance == a.balance) {
			if (this.dateCreated.after(a.dateCreated)) {
				return 1;
			} else return -1;
		}
		
		if(this.balance >= a.balance) {
			return 1;
		} else return -1;
		
	}
	
	@Override
	public String toString() {
		return String.format("Name: %s, Id: %s, Balance: %s, Date created: %s", name, id, balance, this.getDateCreated());
	}
	
}