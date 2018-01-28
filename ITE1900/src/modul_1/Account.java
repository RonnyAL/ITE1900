package modul_1;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

class Account {
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
}