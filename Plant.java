import java.io.Serializable; 
import java.util.ArrayList;   
import javafx.scene.paint.Color;

/**
 * Extends Lifeform. Plants seed if there are exactly 4 neighboring
 * plants and at least 3 empty adjacent cells.
 * Plant implements HerbivoreEdible interface which is used in the
 * Herbivore class to determine whether this is an edible item to reset the food counter
 * @author Amir Kbah
 *
 */
public class Plant extends Lifeform implements HerbivoreEdible, OmnivoreEdible, Serializable{
	/**
	 * Serialized version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Plant constructors sets the color of the plant to Green.
	 */
	public Plant() {
		color = Color.GREEN;
		canMate = true;
		canMove = false;
		
	}
			
	/**
	 * Since plants don't move, realistically it calls the
	 * pollinate helper method. We will keep the move function
	 * as it is inherited from Lifeform and being used for Herbivore and in 
	 * the future for other types of creature.
	 */
	public void move(ArrayList<Cell> neighbors, Cell cell) {
		pollinate(neighbors, cell);
	
	}
	
	/**
	 * Helper method that is equivalent to a move for the plants
	 * takes in the array of neighbors and seeds a new plant to a 
	 * destination cell if there are 4 other neighboring plants and at least 3 empty 
	 * destination cells, never seeds into an occupied cell.
	 * @param neighbors
	 * @param cell
	 */
	private void pollinate(ArrayList<Cell> neighbors, Cell cell) {
		int plants = 0;
		int empty = 0;
		int choose = 0;
		int food = 0;
		int size = neighbors.size();
		
		// Edited here
		for (int i = 0 ; i < size ; i++) {
			if (neighbors.get(i).getSpecies() instanceof Plant) {
				plants++;
			} else if (neighbors.get(i).getSpecies() == null) {
				empty++;
			} else if (neighbors.get(i).getSpecies() instanceof Animal) {
				food++;
			}
		}
		
		
		//Edited here
		if (empty >= 3 && plants >= 2 && food == 0) {
			do {
				choose = RandomGenerator.nextNumber(size);
			} while (neighbors.get(choose).getSpecies() != null);
			neighbors.get(choose).setSpecies(new Plant());
		}	
	}
	
	/**
	 * implementation of the Lifeform's abstract method.
	 */
	public void eat(Cell source, Cell dest) {
		
	}
	
	/**
	 * implementation of the Lifeforms edible abstract method
	 */
	public boolean edible(Cell cell) {
		return false;
	}
}
