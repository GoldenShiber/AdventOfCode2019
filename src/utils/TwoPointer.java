package utils;

import java.util.ArrayList;

public class TwoPointer {
	public ArrayList<Integer> coordinate = new ArrayList<Integer>(2);
	
	public TwoPointer() {
		coordinate.add(0);
		coordinate.add(0);
	}
	
	public TwoPointer(int x, int y) {
		coordinate.add(x);
		coordinate.add(y);
	}
		
	public int getX() {
		return coordinate.get(0);
	}
	
	public int getY() {
		return coordinate.get(1);
	}
	
	public void print() {
		System.out.printf("The x-coordinate is %s and the y-coordinate is %s\n", this.getX(), this.getY());
	}

}
