package de.htwg.se.tpn.model;

public abstract class Direction {
	private int columnStep, rowStep;
	private int columnStart, rowStart;

	public Direction() {
		rowStep = 0;
		columnStep = 0;
	}

	public static class Left extends Direction {
		public Left() {
 			super.columnStep = 1;
		}
		public void setStarts(int index, int height) {
 			super.columnStart = 0;
 			super.rowStart = index;
		}
	}
	
	public static class Right extends Direction {
		public Right() {
 			super.columnStep = -1;
		}
		public void setStarts(int index, int height) {
 			super.columnStart = height - 1;
 			super.rowStart = index;
		}
	}
	
	public static class Down extends Direction {
		public Down() {
 			super.rowStep = -1;
		}
		public void setStarts(int index, int height) {
			super.columnStart = index;
			super.rowStart = height - 1;
		}
	}
	
	public static class Up extends Direction {
		public Up() {
 			super.rowStep = 1;
		}
		public void setStarts(int index, int height) {
			super.columnStart = index;
			super.rowStart = 0;
		}
	}
	
	public abstract void setStarts(int index, int height);
	
	public int getcStep() {
		return columnStep;
	}
	
	public int getrStep() {
		return rowStep;
	}
	
	public int getcStart() {
		return columnStart;
	}
	
	public int getrStart() {
		return rowStart;
	}
}
