
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public class Transportation {

	public enum MeansOfTransportation {
		CAR, TRUCK;
		
		public static boolean contains(String s) {

		    for (MeansOfTransportation c : MeansOfTransportation.values()) {
		        if (c.name().equals(s)) {
		            return true;
		        }
		    }
		    return false;
		}
	}

	private static final String DATA_SEPARATOR = ",";

	public static void main(String[] args) throws FileNotFoundException {
		 Map<String, Integer> vehicleCounts = new HashMap<String, Integer>();
		readCountersFromFile(vehicleCounts);
		
		System.out.println("Available means of transportation are:");
		System.out.println(java.util.Arrays.asList(MeansOfTransportation.values()));
		Scanner scan = new Scanner(System.in); 
		String selection = scan.nextLine();
		while(!MeansOfTransportation.contains(selection)) {
			System.out.println("Invalid choice! Please enter: ");
			System.out.println(java.util.Arrays.asList(MeansOfTransportation.values()));
			selection = scan.nextLine();
		}
		System.out.println("Great choice! We are now creating new: " + selection);
		
		if (selection.equals("CAR")) {				
			Car myCar = new Car();
			operateVehicle(myCar);			
		}
		else if (selection.equals("TRUCK")) {			
			Truck myTruck = new Truck();
			myTruck.loadCargo();
			operateVehicle(myTruck);				
		}
		vehicleCounts.put(selection, vehicleCounts.get(selection)+1);
	    writeCountersToFile(vehicleCounts);
	    System.out.println("Show vehicle counts? Y/n" );
		String displayChoice = scan.nextLine();
		if(displayChoice.equalsIgnoreCase("Y")) {
			for (Map.Entry<String, Integer> entry : vehicleCounts.entrySet()) {
				System.out.println(entry.getKey()+ ": " + entry.getValue());
			}
		}
	    scan.close();
		
	}
	//TESTING NEW GIT BRANCH

	private static void readCountersFromFile(Map<String, Integer> vehicleCounts) {
		try {
			File file = new File("C:\\Users\\Maja\\Desktop\\VehicleCount.csv");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				String[] splitLine = line.split(",");
				vehicleCounts.put(splitLine[0], Integer.parseInt(splitLine[1]));				
			}
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void writeCountersToFile(Map<String, Integer> vehicleCounts) throws FileNotFoundException {
		Writer writer = new BufferedWriter(new OutputStreamWriter(
	            new FileOutputStream(new File("C:\\Users\\Maja\\Desktop\\VehicleCount.csv")), StandardCharsets.UTF_8));
	    vehicleCounts.forEach((key, value) -> {
			try {
				writer.write(key + DATA_SEPARATOR + value + System.lineSeparator());
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	    try {
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void operateVehicle(Vehicle myVehicle) {
		myVehicle.describeMe();
		myVehicle.service();
		myVehicle.computeTaxes();
		for (int i=0; i<=10; i++) {
			myVehicle.drive(100);
		}
	}

}
