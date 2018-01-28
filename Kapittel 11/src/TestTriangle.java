import java.util.Scanner;

public class TestTriangle extends Triangle {

	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		System.out.print("Oppgi side 1: ");
		double side1 = sc.nextDouble();
		System.out.print("Oppgi side 2: ");
		double side2 = sc.nextDouble();
		System.out.print("Oppgi side 3: ");
		double side3 = sc.nextDouble();
		
		Triangle myTriangle = new Triangle("Pink", false, side1, side2, side3);
		
		System.out.println("\nArea: " + myTriangle.getArea());
		System.out.println("Perimeter: " + myTriangle.getPerimeter());
		System.out.println(myTriangle.toString());
		

	}

}
