package modul_3;

public abstract class Vehicle {
	private String colour, name, serialNumber;
	private int model, price, direction;
	private double speed;
	protected java.util.Scanner input;
	
	Vehicle() {
		
	}
	
	Vehicle(String colour, String name, String serialNumber, int model, int price, int direction) {
		this.name = name;
		this.serialNumber = serialNumber;
		this.model = model;
		this.price = price;
		this.direction = direction;
	}
	
	public void setAllFields() {
		System.out.print("Name: ");
		this.name = input.nextLine();
		System.out.println("Colour: ");
		this.colour = input.nextLine();
		System.out.println("Price: ");
		this.price = input.nextInt();
		System.out.println("Model: ");
		this.model = input.nextInt();
		System.out.println("Serial number: ");
		this.serialNumber = input.nextLine();
	}
	
	
	public abstract void turnLeft(int degrees);
	public abstract void turnRight(int degrees);

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
		return "Vehicle [colour=" + colour + ", name=" + name + ", serialNumber=" + serialNumber + ", model=" + model
				+ ", price=" + price + ", direction=" + direction + ", speed=" + speed + ", input=" + input + "]";
	}

	
}
