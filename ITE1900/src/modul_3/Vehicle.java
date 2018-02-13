package modul_3;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class Vehicle implements Comparable<Vehicle>, Cloneable{
	private String colour, name, serialNumber;
	private int model, price, direction;
	private double speed;
	protected java.util.Scanner input = new java.util.Scanner(System.in);
	public SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private Calendar buyingDate;
	
	Vehicle() {}
	
	Vehicle(String name, String colour, int price, int model, String serialNumber, int speed, int direction) {
		this.name = name;
		this.colour = colour;
		this.serialNumber = serialNumber;
		this.model = model;
		this.price = price;
		this.direction = direction;
		this.speed = 0;
	}
	
	public void setAllFields() {
		System.out.print("Name: ");
		this.name = input.nextLine();
		System.out.printf("Colour: ");
		this.colour = input.nextLine();
		System.out.printf("Price: ");
		this.price = input.nextInt();
		System.out.printf("Model: ");
		this.model = input.nextInt();
		System.out.printf("Serial number: ");
		this.serialNumber = input.nextLine();
		input.next();
	}
	
	
//	public abstract void turnLeft(int degrees);
//	public abstract void turnRight(int degrees);
	public abstract void turn(int degrees);

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public int getModel() {
		return model;
	}

	public void setModel(int model) {
		this.model = model;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	@Override
	public String toString() {
		return String.format("Name: %s | Colour: %s | Price: %s | Model: "
				+ "%s | Serial number: %s | Direction: %s Speed: %s | ", 
				name, colour, price, model, serialNumber, direction, speed);
	}
	
	@Override
	public int compareTo(Vehicle v) {
		if (this.price > v.price)
			return 1;
		else
			return -1;
	}

	
}
