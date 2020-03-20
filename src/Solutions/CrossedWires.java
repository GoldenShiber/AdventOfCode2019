package Solutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.print.attribute.standard.Destination;

import utils.DataHandling;


public class CrossedWires {

	private static ArrayList<String> destination;
	private static String[] designationOrders;
	private static int[] collisionList;
	
	
	private class Order {
		private int steps = 0;
		private String direction = "";
		
		private Order(int stepsAmount, String stepDirection) {
			steps = stepsAmount;
			direction = stepDirection;
		}
		
		private int getSteps() {
			return steps;
		}
		
		private String getDirection() {
			return direction;
		}
	}
	
	private static ArrayList<String> getDestination(String nodeDestination){
		ArrayList<String> destinationList = new ArrayList<String>(2);
		destinationList.add(0, (nodeDestination.substring(0,1)));
		destinationList.add(nodeDestination.substring(1,nodeDestination.length()));
		return destinationList;
		
	}
	
//	public static Order getOrder(int steps, String directions) {
//		return Order(steps, directions);
//	}
	
	
//	Create function that updates map
	
//	Create function that measures the Manhattan distance
	
	private static ArrayList<ArrayList<String> > aList =  
             new ArrayList<ArrayList<String> >(20); 

	private static ArrayList<ArrayList<String> > designationList =  
			new ArrayList<ArrayList<String> >(); 
	
	public static void main(String[] args) throws IOException {
		String inputLine = "";
		URL inputURL = RocketEquation.class.getClassLoader().getResource("inputFiles/inputday3.txt");
		BufferedReader inFile = new BufferedReader(
				new InputStreamReader(inputURL.openStream()));
		if((inputLine = inFile.readLine()) != null) {
			designationOrders = inputLine.split(",");
		}
		for (int i = 0; i < designationOrders.length; i++) {
			designationList.add(getDestination(designationOrders[i]));
			System.out.println(designationList.get(i));

		}
		
		ArrayList<String> list = new ArrayList<String>(Collections.nCopies(2000, "."));

		for (int i = 0; i < 2000; i++) {
			aList.add(list);
		}
//		DataHandling.printArrayList(aList.get(2), 2, 10);
		
//		Order testOrder = getOrder(10, "D");
		destination = getDestination("D342");
//		System.out.println(destination);
		
		
}
}
