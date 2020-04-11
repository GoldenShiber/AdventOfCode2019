package utils;
import utils.TwoPointer;

public class CoordinateLine {
	
	private TwoPointer coordinate1;
	private TwoPointer coordinate2;
	
	public CoordinateLine() {
		coordinate1 = new TwoPointer();
		coordinate2 = new TwoPointer();
	}
	
	public CoordinateLine(TwoPointer coord1, TwoPointer coord2) {
		coordinate1 = coord1;
		coordinate2 = coord2;
	}
	
	public TwoPointer getNewCoordinate() {
		return coordinate1;
	}
	
	public TwoPointer getOldCoordinate() {
		return coordinate2;
	}
	
	public boolean isHorisontal() {
		return (coordinate1.getX() - coordinate2.getX() != 0) ? true:false; 
	}
	
	public int getXMin() {
		return Math.min(coordinate1.getX(), coordinate2.getX());
	}
	
	public int getXMax() {
		return Math.max(coordinate1.getX(), coordinate2.getX());
	}
	
	public int getYMin() {
		return Math.min(coordinate1.getY(), coordinate2.getY());
	}
	
	public int getYMax() {
		return Math.max(coordinate1.getY(), coordinate2.getY());
	}
	
	public boolean isIntersection(TwoPointer coordinate) {
		if (isHorisontal()) {
			return (coordinate.getX() <= getXMax() && coordinate.getX()	>= getXMin() &&
					coordinate.getY() == getYMax()) ? true:false; 
		}
		else {
			return (coordinate.getY() <= getYMax() && coordinate.getY()	>= getYMin() &&
					coordinate.getX() == getXMax()) ? true:false;
		}	
		
	}
	
	public void print() {
		System.out.println("The current nodes parts are!");
		coordinate1.print();
		coordinate2.print();
	}
	
}
