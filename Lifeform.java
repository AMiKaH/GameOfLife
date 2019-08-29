import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Lifeform is the highest class in the hierarchy of this game.
 * All other creatures extend Lifeform, for this edition, there are 
 * Herbivore, Omnivore, Carnivore and Plants. 
 * and indirectly.
 * 
 * @author Amir Kbah
 */

public abstract class Lifeform implements Serializable {
	/**
	 * Serialized version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Holds the color of the lifeform
	 */
	static Color color;
	
	/**
	 * Holds whether a lfieform can move
	 */
	boolean canMove;
	
	/**
	 * Holds the lifeform's x Axis position
	 */
	int x;
	
	/**
	 * Holds the lifeform's y Axis position
	 */
	int y;
	
	/**
	 * Holds whether a lifeform can mate
	 */
	boolean canMate;
	
	/**
	 * Holds the lfieform's food counter
	 */
	int fed;

	/**
	 * Constructor of Lifeform, sets the food cunter to 5
	 * and the canMove and canMate to true
	 */
	public Lifeform() {
		canMove = true;
		canMate = true;
		fed = 5;
	}
	
	/**
	 * abstract methods to check if a cell is edible and if it can eat.
	 * implemented in each species below Animal
	 */
	public abstract boolean edible(Cell cell);
	public abstract void eat(Cell source, Cell dest);
	

	/**
	 * returns the food counter/status of the lifeform
	 * @return
	 */
	public int getFed() {
		return this.fed;
	}
	
	/**
	 * Sets the food counter of the lifeform
	 * @param x
	 */
	public void setFed(int x) {
		this.fed = x;
	}
	

	/**
	 * sets the canMate boolean
	 * @return
	 */
	public boolean getCanMate() {
		return this.canMate;
	}
	
	/**
	 * gets the canMate boolean
	 * @param x
	 */
	public void setCanMate(boolean x) {
		canMate = x;
	}
	
	/**
	 * resets the canMate variable to true after a move
	 */
	public void resetCanMate() {
		canMate = true;
	}
	
	/**
	 * sets the color
	 */
	public static void setColor(Color colors) {
		color = colors;
	}
	
	/**
	 * Returns the color of the Lifeform
	 * 
	 * @return color
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * For sub-lifeforms, returns whether or not 
	 * they can move
	 * @return
	 */
	public boolean getCanMove() {
		return this.canMove;
	}
	
	/**
	 * Sets whether or not a sub-lifeform can move.
	 * @param x
	 */
	public void setCanMove(boolean x) {
		this.canMove = x;
	}
	
	/**
	 * Resets the canMove boolean in sub-lifeforms
	 * so they can move in next turn.
	 */
	public void resetCanMove() {
		this.canMove = true;
	}
	
	/**
	 * Moves the inhabitant of a cell, each sub-lifeform
	 * uses the move method in their own particular way.
	 * The method takes an ArrayList of neighboring cells
	 * to determine a target position to move to.
	 * @param neighbors
	 * @param cell
	 */
	public void move(ArrayList<Cell> neighbors, Cell cell) {
		
		// Checks if the lifeform can move
		if (this.getCanMove() && this.validate(neighbors)) {
			// holds the random value to choose a destination cell
			int choose = 0;
			
			// size sets the range of the random generator
			int size = neighbors.size();
			
			/*
			 * This makes sure 2 animals of same type do not collide
			 * It also makes sure that animals move into cells that 
			 * contain food first			
			 */
			if (this.foodInNeighbors(neighbors, cell)) {
				do  {
					choose = RandomGenerator.nextNumber(size);
				} while (!this.edible(neighbors.get(choose)));
			} else {
				do  {
					choose = RandomGenerator.nextNumber(size);
				} while (neighbors.get(choose) == null);
			}
			this.eat(cell, neighbors.get(choose));
		}
	}
	
	
	
	//Edited here and below
	public void createLife(ArrayList<Cell> neighbors, Cell cell) {
		int food = 0;
		int mates = 0;
		int empty = 0;
		int size = neighbors.size();
		Lifeform life = cell.getSpecies();
		
		if (this.getCanMate()) {
			for (int i = 0; i < size; i++) {
				if (neighbors.get(i).getCellColor() == life.getColor()) {
					mates++;
				} else if (life.edible(neighbors.get(i))) {
					food++;
				} else if (neighbors.get(i).getSpecies() == null) {
					empty++;
				}
			}
			this.giveBirth(mates, empty, food, neighbors);
		}		
	}
	
	
	private boolean validate(ArrayList<Cell> neighbors) {
		int size = neighbors.size();
		int validSpots = 0;
		for (int i = 0; i < size; i++) {
			if (this.edible(neighbors.get(i)) ||
					neighbors.get(i).getSpecies() == null) {
				validSpots++;
			}
		}
		return validSpots >= 1 ? true : false;
	}
	
	public boolean movable(Cell cell) {
		if (this.edible(cell) || cell.getSpecies() == null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks if the neighboring cells contain food so that animals
	 * can move to the food first
	 * @param neighbors
	 * @param cell
	 * @return
	 */
	public boolean foodInNeighbors(ArrayList<Cell> neighbors, Cell cell) {
		int food = 0;
		for (int i = 0; i < neighbors.size(); i++) {
			if (this.edible(neighbors.get(i))) {
				food++;
			}
		}
		return food > 0 ? true : false;
	}
	/**
	 * Checls neighbors and gives birth if minimum requirements are met
	 * Implemented in sub classes
	 * @param nMate
	 * @param nEmpty
	 * @param nFood
	 * @param neighbors
	 */
	public void giveBirth(int nMate, int nEmpty, int nFood, ArrayList<Cell> neighbors) {
		
	}	
}
