package de.htwg.se.tpn.model;

public class Direction {
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int DOWN = 2;
	public static final int UP = 3;
	
	private int direction;
	private int collumnStep, rowStep;
	private int collumnStart, rowStart;
	
	public Direction(int direction) {
		assert(direction == LEFT || direction == RIGHT || direction == DOWN || direction == UP);
		
		this.direction = direction;

		switch (direction) {
 		case LEFT:
 			collumnStep = 1;
 			break;
 		case RIGHT:
 			collumnStep = -1;
 			break;
 		case UP:
 			rowStep = 1;
 			break;
 		case DOWN:
 			this.rowStep = -1;
 		}
	}
	
	public void setStarts(int index, int height) {
		switch (direction) {
 		case LEFT:
 			collumnStart = 0;
 			rowStart = index;
 			break;
 		case RIGHT:
 			collumnStart = height - 1;
 			rowStart = index;
 			break;
 		case UP:
 			collumnStart = index;
 			rowStart = 0;
 			break;
 		case DOWN:
 			collumnStart = index;
 			rowStart = height - 1;
 		}
	}
	
//	class Left extends Direction {
//		public void setStarts() {
//			collumStart = 0;
//			rowStart= index;
//					
//		}
//	}
	
	public int getcStep() {
		return collumnStep;
	}
	
	public int getrStep() {
		return rowStep;
	}
	
	public int getcStart() {
		return collumnStart;
	}
	
	public int getrStart() {
		return rowStart;
	}
}
