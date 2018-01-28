package modul_2;

public class IllegalTriangleException extends Exception{
	
	private static final long serialVersionUID = 1L; //Eclipse gir advarsel hvis serialVersionUID mangler
	private double side1, side2, side3;

	public IllegalTriangleException(double side1, double side2, double side3, String errStr) {
	    super(errStr);

	} 

	public double getSide1() {
	    return side1;
	}
	
	public double getSide2() {
	    return side2;
	}
	
	public double getSide3() {
	    return side3;
	}

}
