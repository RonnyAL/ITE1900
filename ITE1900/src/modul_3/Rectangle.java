package modul_3;

public class Rectangle extends GeometricObject {
	private double width;
	private double height;
	
	public Rectangle() {
		
	}
	
	public Rectangle(double sides) {
		this.width = sides;
		this.height = sides;
	}

	public Rectangle(double width, double height) {
		this.width = width;
		this.height = height;
	}

	  /** Return width */
	public double getWidth() {
		return width;
	}

	  /** Set a new width */
	public void setWidth(double width) {
		this.width = width;
	}

	  /** Return height */
	public double getHeight() {
		return height;
	}

	  /** Set a new height */
	public void setHeight(double height) {
		this.height = height;
	}
	
	@Override /** Return area */
	public double getArea() {
		return width * height;
	}

	@Override /** Return perimeter */
	public double getPerimeter() {
		return 2 * (width + height);
	}
	
	public boolean equals(Object other) {
		return other instanceof Circle && getArea() == ((Circle)other).getArea() ? true : false;
	}
	
	public String toString() {
		return String.format("Width: %s%nHeight: %s%n", width, height) + super.toString();
	}
}
