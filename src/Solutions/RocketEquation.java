package Solutions;

import java.io.*;
import java.net.URL;
import utils.DataHandling;

public class RocketEquation {
	private static int fuelSum;
	private static int realFuelSum;
	private static String inputLine;
	
	public static int rocketEquation(int mass) {
		return mass/3-2>0?(int) (Math.floor(mass/3) - 2):0 ;
	}
	
	
	public static int fuelCostEquation(int mass) {
		int fuelCost = 0;
		while (mass/3-2 > 0) {
			fuelCost += rocketEquation(mass);
			mass = rocketEquation(mass);
			}
		return fuelCost;
	}
	
	public static void main(String[] args) throws IOException {
		URL inputURL = RocketEquation.class.getClassLoader().getResource("inputFiles/inputday1.txt");
		BufferedReader inFile = new BufferedReader(
				new InputStreamReader(inputURL.openStream()));

        while ((inputLine = inFile.readLine()) != null) {
        	if (DataHandling.isNumeric(inputLine)){
            fuelSum += rocketEquation(Integer.parseInt(inputLine));
        	realFuelSum += fuelCostEquation(Integer.parseInt(inputLine));
        		}
        	}
        inFile.close();
        
        System.out.println("The total amount of fuel is: " + fuelSum);
        System.out.println("The real total amount of fuel is: " + realFuelSum);
	}
}

