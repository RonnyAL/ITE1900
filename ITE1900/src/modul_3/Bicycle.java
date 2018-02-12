package modul_3;
import java.util.Calendar;

public class Bicycle extends Vehicle {

	private int gears;
	private Calendar productionDate;
	
	Bicycle() {}
	
	Bicycle(String name, String colour, int price, int model, String serialNumber, int speed, int direction) {
		super(name, colour, price, model, serialNumber, speed, direction);
		productionDate = Calendar.getInstance();
	}
	
	@Override
	public void setAllFields() {
		super.setAllFields();
		System.out.print("Gears: ");
		this.gears = input.nextInt();
		productionDate = Calendar.getInstance();
	}
	
	public void turn(int degrees) {
		if (Math.abs(degrees) >= 0 && degrees <= 360) {
			System.out.printf("Bicycle \"%s\" turns %s by %d degree%s.%n", this.getName(), 
					degrees < 0 ? "left" : "right", Math.abs(degrees), degrees == 1 ? "" : "s");
		}
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
		return super.toString() + String.format("Gears: %s | Production date: %s", gears, df.format(productionDate.getTime()));
	}
	
}
