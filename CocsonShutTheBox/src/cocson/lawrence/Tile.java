package cocson.lawrence;

public class Tile {
	private int number;
	private boolean isUp;
	
	Tile(int n, boolean s){
		number = n;
		isUp = s;
	}
	
	Tile(){
		number = 0;
		isUp = true;
	}
	
	public int getValue() {
		return number;
	}
	
	public boolean isUp() {
		return isUp;
	}
	
	public void putUp() {
		isUp = true;
	}
	
	public void putDown() {
		isUp = false;
	}
	
	@Override
	public String toString() {
		return("||Value: " + number + " isUp: " + isUp + " ");
	}

}
