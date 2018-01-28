package modul_1;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM HH:MM:ss");
	private double amount;
	private double balance;
	private String description;
	private Date date;
	private char type;
	
	public Transaction(char type, double amount, double balance, String description) {
		this.type = type;
		this.amount = amount;
		this.balance = balance;
		this.description = description;
		date = new Date(System.currentTimeMillis());
	}
	
	public String getDescription() {
		return description;
	}
 
	public double getAmount() {
		return amount;
	}

	public double getBalance() {
		return balance;
	}

	public Date getDate() {
		return date;
	}

	public char getType() {
		return type;
	}
	
	public String toString() {
		return String.format("%s\t%s\t%.2f\t%.2f\t%s", sdf.format(date), type, amount, balance, description);
	}

}
