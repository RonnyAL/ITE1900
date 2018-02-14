package modul_3;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Scanner;

public class Car extends Vehicle implements Cloneable {
	private int power;
	private Calendar productionDate;
	
	Car() {}
	
	Car(String name, String colour, int price, int model, String serialNumber, double speed, int direction) {
		super(name, colour, price, model, serialNumber, speed, direction);
		productionDate = Calendar.getInstance();
	}
	
	@Override
	public void setAllFields() {
		super.setAllFields();
		System.out.print("Power: ");
		this.power = input.nextInt();
		productionDate = Calendar.getInstance();
	}
	
	public void turn(int degrees) {
		if (Math.abs(degrees) >= 0 && degrees <= 360) {
			int newDirection = Math.floorMod(this.getDirection()+degrees, 360);
			setDirection(newDirection);
		}
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public Calendar getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Calendar productionDate) {
		this.productionDate = productionDate;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Car c = (Car) super.clone();
		c.productionDate = ( Calendar ) productionDate.clone();
		return c;
	}

	@Override
	public String toString() {
		return super.toString() + String.format("Power: %s | Production date: %s | Buying date: %s", 
				power, df.format(productionDate.getTime()), df.format(buyingDate.getTime()));
	}

	@Override
	public void accelerate(int speedFactor) {
		setSpeed(this.getSpeed() == 0 ? 0.5*speedFactor : Math.min(MAX_SPEED_CAR, this.getSpeed()*speedFactor));
		System.out.printf("%s \"%s\" accelerates to %s km/h%n", this.getClass().getSimpleName(), this.getName(), this.getSpeed());
	}

	@Override
	public void breaks(int speedFactor) {
		this.setSpeed(this.getSpeed()/speedFactor);
		System.out.printf("%s \"%s\" decelerates to %s km/h%n", this.getClass().getSimpleName(), this.getName(), this.getSpeed());
	}

	@Override
	public void writeData(PrintWriter p) throws FileNotFoundException {
		super.writeData(p);
		p.printf("%s,%s,%s%s", power, df.format(getProductionDate().getTime()), 
				df.format(super.getBuyingDate().getTime()), LINE_SEPARATOR);
	}

	@Override
	public void readData(Scanner in) {
		// TODO Auto-generated method stub
		
	}
	
}