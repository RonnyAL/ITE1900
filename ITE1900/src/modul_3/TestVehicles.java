package modul_3;
import java.util.*;

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

    vehicles.clear(); //TODO Remove this line
    
    vehicles.add(new Car("Volvo 740", "blue", 85000, 1985, "1010-11", 0, 120));
    vehicles.add(new Car("Ferrari Testarossa", "red", 1200000, 1996, "A112", 0, 350));
    vehicles.add(new Bicycle("Monark 1", "yellow", 4000, 1993, "BC100", 0, 10));
    vehicles.add(new Bicycle("DBS 2", "pink", 5000, 1994, "42", 0, 10));

    while (true) {
      System.out.printf("1...................................New car%n");
      System.out.println("2...............................New bicycle");
      System.out.println("3......................Find vehicle by name");
      System.out.println("4..............Show data about all vehicles");
      System.out.println("5.......Change direction of a given vehicle");
      System.out.println("6.........................Test clone method");
      System.out.println("7..............................Exit program");
      System.out.print(".............................Your choice? ");

      int choice = input.nextInt();
      String searchTerm;
      char turnDir;
      boolean found = false;
      int turnDeg;

      switch (choice) {

        case 1: // New car
          System.out.printf("%nInput car data:%n");
          vehicle = new Car();
          vehicle.setAllFields();
          vehicles.add(vehicle);
          System.out.println();
          break;
        case 2: // New bicycle
          System.out.printf("%nInput bicycle data:%n");
          vehicle = new Bicycle();
          vehicle.setAllFields();
          vehicles.add(vehicle);
          System.out.println();
          break;
        case 3: // Find vehicle by name
        	System.out.printf("%nName of vehicle: ");
        	input.nextLine();
        	searchTerm = input.nextLine();
        	found = false;
        	
        	for (Vehicle v : vehicles) {
        		if (v.getName().equals(searchTerm)) {
        			System.out.println(v.toString());
        			found = true;
        			break;
        		}
        	}
        	
        	if (!found)
        		System.out.printf("No vehicle named \"%s\" was found!%n", searchTerm);
        	System.out.println();
        	break;
        	
        case 4: // Show data about all vehicles
        	System.out.println();
        	for (Vehicle v : vehicles)
        		System.out.println(v.toString());
        	System.out.println();
        	break;
        	
        case 5: // Change direction of a given vehicle
        	System.out.printf("%nName of vehicle: ");
        	input.nextLine();
        	searchTerm = input.nextLine();
        	found = false;
        	
        	for (Vehicle v : vehicles) {
        		if (v.getName().equals(searchTerm)) {
        			found = true;
        			System.out.print("Direction [R/L]: ");
        			turnDir = input.nextLine().toCharArray()[0];
        			if (turnDir != 'R' && turnDir != 'L') {
        				System.out.printf("Invalid direction!%n");
        				break;
        			}
        				
        			System.out.printf("Degrees: ");
        			turnDeg = input.nextInt();
        			
        			if (turnDir == 'R')
        				v.turn(turnDeg);
        			else if (turnDir == 'L')
        				v.turn(-turnDeg);
        			
        		}
        	}
        	
        	if (!found)
        		System.out.printf("No vehicle named \"%s\" was found!%n", searchTerm);
        	
        	System.out.println();
        	break;
          
        case 6:
        	//TODO Stuff
        	break;
        	
        case 7: // Exit program
          input.close();
          System.exit(0);
        default:
          System.out.println("Invalid option!");
      }
    }
  }
}