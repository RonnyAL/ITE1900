package modul_3;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestVehicles {
	public static  ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
	public SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	public static void main(String[] args) {
		TestVehicles vtest = new TestVehicles();
		try (Scanner s = new Scanner(new File("VehicleList.txt"));){
			new Car().readData(s);
		}
		catch (FileNotFoundException fnf) { System.out.println("No save as of yet!");
}
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
		if (vehicles.isEmpty()) {
		vehicles.add(new Car("Volvo 740", "blue", 85000, 1985, "1010-11", 0, 120));
		vehicles.add(new Car("Ferrari Testarossa", "red", 1200000, 1996, "A112", 0, 350));
		vehicles.add(new Bicycle("Monark 1", "yellow", 4000, 1993, "BC100", 0, 10));
		vehicles.add(new Bicycle("DBS 2", "pink", 5000, 1994, "42", 0, 10));}
		
		
		while (true) {
			System.out.println("1...................................New car");
			System.out.println("2...............................New bicycle");
			System.out.println("3......................Find vehicle by name");
			System.out.println("4..............Show data about all vehicles");
			System.out.println("5.......Change direction of a given vehicle");
			System.out.println("6.........................Test clone method");
			System.out.println("7..................Test driveable interface");
			System.out.println("8..............................Exit program");
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
          
        case 6: // Clone vehicle and test result
        	Car c1 = new Car("Nissan Skyline", "Blue", 220000, 2015, "ZF36132", 290, 0);
        	vehicles.add(c1);
        	
		Car c2 = null;
		
		try {
			c2 = (Car) c1.clone();
			Calendar tempCal = c2.getBuyingDate();
    		tempCal.set(Calendar.YEAR, tempCal.get(Calendar.YEAR)-5);
    		c2.setBuyingDate(tempCal);
    		vehicles.add(c2);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
    		
		if(!c1.getBuyingDate().equals(c2.getBuyingDate())) {
			System.out.printf("%nDate objects are separate, deep copy.%nc1: "
					+ "%s%nc2: %s%n%n", df.format(c1.getBuyingDate().getTime()), 
					df.format(c2.getBuyingDate().getTime()));
    	} else {
    		System.out.printf("%nShallow copy!%n%n");
    	}
		
		break;
        	
        case 7:
        	Car c3 = new Car("Nissan Micra", "Turquoise", 35000, 2001, "XD69420", 0, 0);
        	Bicycle b1 = new Bicycle("DBS Intruder FX6", "Black", 3999, 2018, "YMD161xx01", 0, 0);
        	
        	System.out.println();
        	
        	c3.accelerate(10);
        	c3.accelerate(1000);
        	c3.breaks(1000);
        	c3.stop();

        	System.out.println();
        	
        	b1.accelerate(10);
        	b1.accelerate(1000);
        	b1.breaks(1000);
        	b1.stop();
        	
        	System.out.println();
        	
        	break;
		
        case 8: // Exit program
        	input.close();
        	Collections.sort(vehicles);
        	try (PrintWriter writer = new PrintWriter("VehicleList.txt")) {
        		System.out.println();
        		for (Vehicle v : vehicles) {
            		v.writeData(writer);
            		System.out.printf("Vehicle written to file: %s%n", v.toString());
            	}
        	} catch (FileNotFoundException e) {
				System.out.println("Error writing to file. Check permissions!");
				e.printStackTrace();
			}
        	
        	System.exit(0);
        	
        	
        
        default: // Invalid input
        	System.out.println("Invalid option!");
      }
    }
  }
}