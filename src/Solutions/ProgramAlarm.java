package Solutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import utils.DataHandling;

public class ProgramAlarm {
	
	private static List<Integer> numberList = new ArrayList<>();
	private static List<Integer> originalList = new ArrayList<>();
	private static int indexTarget;
	private static String inputLine;
	
	public static void alarmAction(int actionIndex ) {
		if (numberList.get(indexTarget) != 99 
				&& numberList.get(indexTarget + 3) < numberList.size()
				&& indexTarget < numberList.size()				) {
			if (numberList.get(indexTarget) ==1) {
				numberList.set(numberList.get(indexTarget + 3), 
						numberList.get(numberList.get(indexTarget + 1) ) +
						numberList.get(numberList.get(indexTarget + 2)));
			}
			else if(numberList.get(indexTarget) == 2) {
				numberList.set(numberList.get(indexTarget + 3), 
						numberList.get(numberList.get(indexTarget + 1)) *
						numberList.get(numberList.get(indexTarget + 2)));
			}
			indexTarget += 4;
		}
		else {
			indexTarget += 100000000;
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		URL inputURL = RocketEquation.class.getClassLoader().getResource("inputFiles/inputday2.txt");
		BufferedReader inFile = new BufferedReader(
				new InputStreamReader(inputURL.openStream()));
		
		if((inputLine = inFile.readLine()) != null) {
			numberList = DataHandling.convertStringListToIntList( 
					Arrays.asList(inputLine.split(",")), Integer::parseInt );
		}
		// Create a copy of the list for part 2
		originalList = numberList.stream().collect(Collectors.toList());
		
		System.out.println("The original list of the code is:\n " + originalList);
		
		// The first file had some data saved!
		numberList.set(1, 12); numberList.set(2, 2);
		System.out.println("The list befor the crash of the code is: \n " + numberList);
				
		indexTarget = 0;
		while (indexTarget < numberList.size()) {
			alarmAction(indexTarget);
		}
		System.out.println("This is the list of the code:\n " + numberList);
		
		System.out.println("Now let's start with part 2!");
		boolean finished = false;
		int i = 0; int j =0;
		for (i = 0; i < 100; i++) {
			for (j = 0; j < 100; j++) {
				numberList = originalList.stream().collect(Collectors.toList());
				numberList.set(1, i); numberList.set(2, j);
				indexTarget = 0;
				while (indexTarget < numberList.size()) {
					alarmAction(indexTarget);
				}
				if (numberList.get(0) == 19690720) {
					finished = true;
					break;
				}
			}
			if (finished) {
				break;
			}
		}
		System.out.printf("The correct outputs are:\n%s at index 1 which is a noun\n%s at index 2  which is a verb\n"
				+ "The total aoutput is %s", i, j, (100*i + j));
		
	}

}
