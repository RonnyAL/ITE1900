package modul_3;

public class TestGeometricObjects {

	public static void main(String[] args) {
		
		
		GeometricObject c1, c2, r1, r2;
		
		Triangle t1, t2;
		
		c1 = new Circle(randomNum());
		c2 = new Circle(randomNum());
		
		while (c1.equals(c2)) {
			c1 = new Circle(randomNum());
			c2 = new Circle(randomNum());
		}
		
		r1 = new Rectangle(randomNum(), randomNum());
		r2 = new Rectangle(randomNum(), randomNum());
		
		while (r1.getArea() == r2.getArea()) {
			r1 = new Rectangle(randomNum(), randomNum());
			r2 = new Rectangle(randomNum(), randomNum());
		}
		
		
		t1 = new Triangle(randomNum(), randomNum(), randomNum());
		t2 = new Triangle(randomNum(), randomNum(), randomNum());

		
		while (!legalTriangle(t1.getSide1(), t1.getSide2(), t1.getSide3())) {
			t1 = new Triangle(randomNum(), randomNum(), randomNum());
		}
		
		while (!legalTriangle(t2.getSide1(), t2.getSide2(), t2.getSide3()) || t1.equals(t2)) {
			t2 = new Triangle(randomNum(), randomNum(), randomNum());
		}
		
		
		
		
		GeometricObject maxCircle = GeometricObject.max(c1, c2);
		GeometricObject maxRect = GeometricObject.max(r1,  r2);
		GeometricObject maxTriangle = GeometricObject.max(t1, t2);
		
		GeometricObject tempMax = GeometricObject.max(maxCircle, maxRect);
		GeometricObject goMax = GeometricObject.max(tempMax, maxTriangle);
		
		System.out.println("The biggest circle:");
		System.out.println(maxCircle.toString());
		System.out.println();
		
		System.out.println("The biggest rectangle:");
		System.out.println(maxRect.toString());
		System.out.println();
		
		System.out.println("The biggest triangle:");
		System.out.println(maxTriangle.toString());
		System.out.println();
		
		System.out.println("The biggest geometric object is: " + goMax.getClass().getSimpleName());
		System.out.println(goMax.toString());
		
	}
	
	public static int randomNum() {
		return 1 + (int)(Math.random() * 9);
	}
	
	public static boolean legalTriangle(double a, double b, double c) {
		if (a <= 0 || b <= 0 || c <= 0 || a >= b+c || b >= a+c || c >= a+b) {
			return false;
		} else {
			return true;
		}
	}
	
}
