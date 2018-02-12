package modul_3;

import java.util.Calendar;

public class Bicycle extends Vehicle {

	private int gears;
	private Calendar productionDate;
	
	Bicycle() {}
	
	Bicycle(String colour, String name, String serialNumber, int model, int price, int direction) {
		super(colour, name, serialNumber, model, price, direction);
	}
	
	@Override
	public void setAllFields() {
		super.setAllFields();
		System.out.println("Gears: ");
		this.gears = input.nextInt();
//		System.out.println("Production date: ");
//		this.productionDate = ?
	}
	
	public void turnLeft(int degrees) {
		
	}
	
	public void turnRight(int degrees) {
		
	}

	public int getGears() {
		return gears;
	}

	public void setGears(int gears) {
		this.gears = gears;
	}

	public Calendar getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Calendar productionDate) {
		this.productionDate = productionDate;
	}
	
	@Override
	public String toString() {
		return "Bicycle [gears=" + gears + ", productionDate=" + productionDate + "]";
	}
	
}
