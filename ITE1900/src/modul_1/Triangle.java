
public class Triangle extends GeometricObject {
	private double side1, side2, side3;
	
	public static void main(String[] args) {
		Triangle testTriangle = new Triangle();
		System.out.println(testTriangle.toString());

	}
	
	public Triangle() {
		this(1.0);
	}

	public Triangle(double side) {
		this(side, side, side);
	}
	
	public Triangle(double side1, double side2, double side3) {
		this("White", false, side1, side2, side3);
	}
	
	public Triangle(String color, boolean filled, double side1, double side2, double side3) {
		super(color, filled);
		this.side1 = side1;
		this.side2 = side2;
		this.side3 = side3;
	}
	
	//<getSides>
	public double getSide1() {
		return side1;
	}
	
	public double getSide2() {
		return side2;
	}
	
	public double getSide3() {
		return side3;
	}
	
	
	//<setSides>
	public void setSide1(double newSide1) {
		side1 = newSide1;
	}
	
	public void setSide2(double newSide2) {
		side2 = newSide2;
	}
	
	public void setSide3(double newSide3) {
		side3 = newSide3;
	}
	//</setSides>
	
	public double getPerimeter() {
		return Math.round((side1+side2+side3)*100.0)/100.0;
	}
	
	
	public double getArea() {
		double s = (side1+side2+side3)/2;
		double area = Math.round(Math.sqrt(s*(s-side1)*(s-side2)*(s-side3))*100.0)/100.0;
		return area;
	}
	
	public String toString() {
		return "Sides: " + this.side1 + ", " + this.side2 + ", " + this.side3 + "\n" + super.toString();
	}
	
}
