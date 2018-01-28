package modul_2;
public class TestTriangle extends Triangle {

	public TestTriangle(double side1, double side2, double side3) throws IllegalTriangleException {
		super(side1, side2, side3);
	}
	
	public static void main(String[] args) {
		
		double side1, side2, side3;
		
		//Lager gyldig triangel
		side1 = 4;
        side2 = 4;
        side3 = 4;
        createTriangle(side1, side2, side3);
        
        //En side er for lang
        side1 = 7;
        side2 = 3;
        side3 = 2;
        createTriangle(side1, side2, side3);
        
      //En side er negativ
        side1 = 4;
        side2 = -1;
        side3 = 2;
        createTriangle(side1, side2, side3);
	}
	
	private static void createTriangle(double side1, double side2, double side3) {
        try {
        	System.out.printf("Creating triangle with sides: %.2f, %.2f and %.2f%n", side1, side2, side3);
            Triangle t = new Triangle(side1, side2, side3);
            System.out.printf("Area: %s%n", t.getArea());
            System.out.printf("Perimeter: %s%n%n", t.getPerimeter());
        } catch (IllegalTriangleException e) {
            System.out.println(e.getMessage());
        }
		
	}

}
