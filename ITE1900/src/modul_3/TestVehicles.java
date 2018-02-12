package modul_3;
import java.util.*;

/**
 * Class that tests functionality of Vehicle, Bicycle and Car classes.
 */

public class TestVehicles {
  ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

  public static void main(String[] args) {
	   
    TestVehicles vtest = new TestVehicles();
    try {
      vtest.menuLoop();
    } catch (InputMismatchException e) {
      System.out.println("InputMismatchException!");
      System.out.println(e.getMessage());
      System.exit(1);
    }
  }

  private void menuLoop() throws InputMismatchException {
    Scanner input = new Scanner(System.in);
    Vehicle vehicle;

    vehicles.add(new Car("Volvo 740", "blue", 85000, 1985, "1010-11", 0, 120));
    vehicles.add(new Car("Ferrari Testarossa", "red", 1200000, 1996, "A112", 0, 350));
    vehicles.add(new Bicycle("Monark 1", "yellow", 4000, 1993, "BC100", 0, 10));
    vehicles.add(new Bicycle("DBS 2", "pink", 5000, 1994, "42", 0, 10));

    while (true) {
      System.out.println("1...................................New car");
      System.out.println("2...............................New bicycle");
      System.out.println("3......................Find vehicle by name");
      System.out.println("4..............Show data about all vehicles");
      System.out.println("5.......Change direction of a given vehicle");
      System.out.println("6..............................Exit program");
      System.out.print(".............................Your choice? ");

      int choice = input.nextInt();
      String searchTerm;
      char newDirection;

      switch (choice) {

        case 1:
          System.out.println("Input car data:");
          vehicle = new Car();
          vehicle.setAllFields(); // TODO Override method
          vehicles.add(vehicle);
          break;
        case 2:
          System.out.println("Input bicycle data:");
          vehicle = new Bicycle();
          vehicle.setAllFields(); // TODO Override method
          vehicles.add(vehicle);
          break;
        case 3:
        	System.out.print("Name of vehicle: ");
        	searchTerm = input.nextLine();
        	for (Vehicle v : vehicles) {
        		if (v.getName() == searchTerm) {
        			v.toString();
        			break;
        		}
        	}
        	System.out.printf("No vehicle named \"%s\" was found!", searchTerm);
        	break;
        	
        case 4:
        	for (Vehicle v : vehicles)
        		v.toString();
        	break;
        	
        case 5:
        	System.out.print("Name of vehicle: ");
        	searchTerm = input.nextLine();
        	for (Vehicle v : vehicles) {
        		if (v.getName() == searchTerm) {
        			System.out.print("Direction [R/L]: ");
        			newDirection = input.nextLine().toCharArray()[0];
        			switch(newDirection) {
        			case 'R':
        				v.turnRight(45);
        				break;
        			case 'L':
        				v.turnLeft(45);
        				break;
        			default:
        				System.out.println("Invalid choice!");
        				break;
        			}
        			break;
        		}
        	}
        	System.out.printf("No vehicle named \"%s\" was found!", searchTerm);
        	break;
          
        case 6:
          input.close();
          System.exit(0);
        default:
          System.out.println("Invalid option!");
      }
    }
  }
}