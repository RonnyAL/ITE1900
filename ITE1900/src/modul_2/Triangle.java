package modul_2;
import java.util.Arrays;

import modul_1.GeometricObject;

public class Triangle extends GeometricObject {
	private double side1, side2, side3;
	
//	public static void main(String[] args) {
//		Triangle testTriangle = new Triangle();
//		System.out.println(testTriangle.toString());
//
//	}
//	
	public Triangle() throws IllegalTriangleException {
		this(1.0);
	}

	public Triangle(double side) throws IllegalTriangleException {
		this(side, side, side);
	}
	
	public Triangle(double side1, double side2, double side3) throws IllegalTriangleException {
		String errStr = "";
		if (side1 < 0 || side2 < 0 || side3 < 0) {
			errStr = "One side is negative!";
		} else if (side1 >= side2+side3 || side2 >= side1+side3 || side3 >= side1+side2) {
			errStr = "One side is larger than the other two combined!";
		}
		
		if (errStr != "") {
			throw new IllegalTriangleException(side1, side2, side3, String.format("%s%n", errStr));
		}
		
		this.side1 = side1;
		this.side2 = side2;
		this.side3 = side3;
	}
	
//	public Triangle(String color, boolean filled, double side1, double side2, double side3)  throws IllegalTriangleException {
//		super(color, filled);
//		this.side1 = side1;
//		this.side2 = side2;
//		this.side3 = side3;
//	}
	
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
	
	public boolean equals(Triangle t2) {
		double[] t1Sides = new double[3];
		double[] t2Sides = new double[3];
		
		t1Sides[0] = this.getSide1();
		t1Sides[1] = this.getSide2();
		t1Sides[2] = this.getSide3();
		
		t2Sides[0] = t2.getSide1();
		t2Sides[1] = t2.getSide2();
		t2Sides[2] = t2.getSide3();
		
		Arrays.sort(t1Sides);
		Arrays.sort(t2Sides);
		
		return Arrays.equals(t1Sides, t2Sides);
	}
}
