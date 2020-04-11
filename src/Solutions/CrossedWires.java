package Solutions;

import java.io.BufferedReader;
import utils.TwoPointer;
import utils.CoordinateLine;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class CrossedWires {
	
	private static TwoPointer originPoint = new TwoPointer();
	private static ArrayList<ArrayList<ArrayList<String>>> designationsList =
			new ArrayList<ArrayList<ArrayList<String>>>();
	private static String[] designationOrders;
	private static ArrayList<TwoPointer> collisionList = new ArrayList<TwoPointer>();
	private static ArrayList<ArrayList<CoordinateLine>> wires = new ArrayList<ArrayList<CoordinateLine>>(2);
	
	private static ArrayList<String> getDestination(String nodeDestination){
		ArrayList<String> destinationList = new ArrayList<String>(2);
		destinationList.add(0, (nodeDestination.substring(0,1)));
		destinationList.add(nodeDestination.substring(1,nodeDestination.length()));
		return destinationList;
	}
	
	public static TwoPointer wireIntersection(CoordinateLine line1, CoordinateLine line2) {
		TwoPointer interSectionPointer = new TwoPointer();
		TwoPointer testPointer = new TwoPointer();
		if (line1.isHorisontal()) {
			for (int i = line1.getXMin(); i <= line1.getXMax(); i++) {
				testPointer = new TwoPointer(i, line1.getYMax());
				if (line2.isIntersection(testPointer) && (testPointer.getX() != 0 || testPointer.getY() != 0)) {
					interSectionPointer = testPointer;
					return interSectionPointer;
				}
			}
		}
		else {
			for (int i = line1.getYMin(); i < line1.getYMax(); i++) {
				testPointer = new TwoPointer(line1.getXMax(), i);
				if (line2.isIntersection(testPointer)) {
					interSectionPointer = testPointer;
					return interSectionPointer;
				}
			}
		}
		
		return new TwoPointer();
	}
	
	public static TwoPointer createDirectionPointer(String direction, int steps, TwoPointer currentNode) {
		if (direction.contentEquals("D")) {
			return new TwoPointer(currentNode.getX(), currentNode.getY() - steps);
		}
		else if (direction.equals("U")) {
			return new TwoPointer(currentNode.getX(), currentNode.getY() + steps);
		}
		else if (direction.equals("R")) {
			return new TwoPointer(currentNode.getX() + steps, currentNode.getY());
		}
		else {
			return new TwoPointer(currentNode.getX() - steps, currentNode.getY());
		}
	}
	
	
	public static double minManhattanDistance() {
		double value = 99999999;
		double newValue = 0;
		for (int i = 0; i< collisionList.size(); i++) {
			newValue = Math.abs(collisionList.get(i).getX()-originPoint.getX()) 
					+ Math.abs(collisionList.get(i).getY()-originPoint.getY());
			if (value > newValue) {
				value = newValue;
			}
			
		}
		return value;
	}
	
	public static double manhattanDistance(TwoPointer pointer1, TwoPointer pointer2) {
		return Math.abs(pointer1.getX()-pointer2.getX()) 
				+ Math.abs(pointer1.getY()-pointer2.getY());
	}
	
	public static int steps() {
		int currentBest = 99999999;
		int testValue = 0;
		TwoPointer prevNode = new TwoPointer();
		for (int i = 0; i < collisionList.size(); i++) {
			for (int j = 0; j < wires.size(); j++) {
				for (int k = 0; k < wires.get(j).size(); k++) {
				if (!wires.get(j).get(k).isIntersection(collisionList.get(i))) {
					testValue += Integer.parseInt(designationsList.get(j).get(k).get(1));
					prevNode = wires.get(j).get(k).getNewCoordinate(); }
				else {
					testValue += manhattanDistance(collisionList.get(i), prevNode); 
					prevNode = new TwoPointer();
					break;
					}
				}
			} 
			if (testValue < currentBest) {
				currentBest = testValue;
			}
			testValue = 0;
		}
		return currentBest;
	}

	private static ArrayList<ArrayList<String> > designationList =  
			new ArrayList<ArrayList<String> >(); 
	
	public static void main(String[] args) throws IOException {
		designationsList.add(new ArrayList<ArrayList<String>>());
		designationsList.add(new ArrayList<ArrayList<String>>());
		
		wires.add(new ArrayList<CoordinateLine>());
		wires.add(new ArrayList<CoordinateLine>());
		
		String inputLine = "";
		URL inputURL = CrossedWires.class.getClassLoader().getResource("inputFiles/inputday3.txt");
		BufferedReader inFile = new BufferedReader(
				new InputStreamReader(inputURL.openStream()));
		int index = 0;
		while((inputLine = inFile.readLine()) != null) {
			designationOrders = inputLine.split(",");
			for (int i = 0; i < designationOrders.length; i++) {
				designationList.add(getDestination(designationOrders[i]));
				designationsList.get(index).add(getDestination(designationOrders[i]));
			}
			index = index +1;
		}
		
		TwoPointer iterationPoint = new TwoPointer();
		TwoPointer previousPoint = originPoint;
		
		for (int i = 0; i< designationsList.size(); i++) {
			previousPoint = originPoint;
			iterationPoint = createDirectionPointer(designationsList.get(i).get(0).get(0)
					, Integer.parseInt(designationsList.get(i).get(0).get(1)),
					previousPoint);
			wires.get(i).add(new CoordinateLine(
					iterationPoint, previousPoint));
			previousPoint = iterationPoint;
			for (int j = 1; j < designationsList.get(i).size(); j++) {
				iterationPoint = createDirectionPointer(designationsList.get(i).get(j).get(0)
						, Integer.parseInt(designationsList.get(i).get(j).get(1)),
						previousPoint);
				wires.get(i).add(new CoordinateLine(
						iterationPoint, previousPoint));
				previousPoint = iterationPoint;	
			}
		}
		
		for (int i = 0; i < wires.get(0).size(); i++) {
			for (int j = 0; j < wires.get(1).size();j++) {
				TwoPointer collPointer = wireIntersection(wires.get(0).get(i), wires.get(1).get(j));
				if (collPointer.getX()!=0 || collPointer.getY()!=0) {
					collisionList.add(collPointer);
				}
			}
		}
		System.out.printf("The Min manhattan distance is:%s\n",minManhattanDistance());
		System.out.printf("The min amount of steps is: %s\n", steps());
        inFile.close();
		 
	}
}
