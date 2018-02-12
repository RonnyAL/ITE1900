package modul_3;
import java.util.Calendar;

public class Car extends Vehicle {
	private int power;
	private Calendar productionDate;
	
	Car() {}
	
	Car(String colour, String name, String serialNumber, int model, int price, int direction) {
		super(colour, name, serialNumber, model, price, direction);
	}
	
	@Override
	public void setAllFields() {
		super.setAllFields();
		System.out.println("Power: ");
		this.power = input.nextInt();
//		System.out.println("Production date: ");
//		this.productionDate = ?
	}
	
	public void turnRight(int degrees) {
		if (degrees >= 0 && degrees <= 360) {
			int newDirection = Math.floorMod(this.getDirection()+degrees, 360);
			this.setDirection(newDirection);
		}
	}
	
	public void turnLeft(int degrees) {
		if (degrees >= 0 && degrees <= 360) {
			int newDirection = Math.floorMod(this.getDirection()-degrees, 360);
			this.setDirection(newDirection);
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
	public String toString() {
		return super.toString() + "Car [power=" + power + ", productionDate=" + productionDate + "]";
	}
	
}
