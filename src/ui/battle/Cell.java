package ui.battle;

public class Cell {

	private int x, y;
	private boolean isSolid, hidePlayer;
	private Character character;
	
	public Cell(int x, int y, boolean isSolid, boolean hidePlayer) {
		this.x = x;
		this.y = y;
		this.isSolid = isSolid;
		this.hidePlayer = hidePlayer;
		character = null;
	}

	// Implemented methods
	
	
	
	//Getters And setters
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Character getCharacter() {
		return character;
	}
	
	public void setCharacter(Character character) {
		this.character = character;
	}
	
	public boolean isSolid() {
		return isSolid;
	}
	
	public boolean hidePlayer() {
		return hidePlayer;
	}
	
}
