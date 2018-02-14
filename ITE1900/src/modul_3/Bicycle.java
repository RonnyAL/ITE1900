package modul_3;
import java.util.Calendar;

public class Bicycle extends Vehicle {

	private int gears;
	private Calendar productionDate;
	
	Bicycle() {}
	
	Bicycle(String name, String colour, int price, int model, String serialNumber, double speed, int direction) {
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
	public Object clone() throws CloneNotSupportedException {
		Bicycle c = (Bicycle) super.clone();
		c.productionDate = ( Calendar ) productionDate.clone();
		return c;
	}
	
	@Override
	public String toString() {
		return super.toString() + String.format("Gears: %s | Production date: %s | Buying date: %s", 
				gears, df.format(productionDate.getTime()), df.format(buyingDate.getTime()));
	}
	
	@Override
	public void accelerate(int speedFactor) {
		setSpeed(this.getSpeed() == 0 ? 0.3*speedFactor : Math.min(MAX_SPEED_BIKE, this.getSpeed()*speedFactor));
		System.out.printf("%s \"%s\" accelerates to %s km/h%n", this.getClass().getSimpleName(), this.getName(), this.getSpeed());
	}

	@Override
	public void breaks(int speedFactor) {
		this.setSpeed(this.getSpeed()/(speedFactor*0.5));
		System.out.printf("%s \"%s\" decelerates to %.1f km/h%n", this.getClass().getSimpleName(), this.getName(), this.getSpeed());
	}
	
}
