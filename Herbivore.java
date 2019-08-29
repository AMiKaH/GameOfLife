import java.io.Serializable;
import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 * Extends Animal >> Lifeform. Herbivores eat plants,
 * if they do not eat after 5 turns then they will die.
 * If they move into a cell that contains a plant then they eat
 * their Fed counter resets so they have 5 turns again.
 * @author Amir Kbah
 *
 */
public class Herbivore extends Animal implements CarnivoreEdible, OmnivoreEdible, Serializable {	
	/**
	 * Serialized version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the Herbivore. Sets the color to Yellow
	 * and starts the food counter at 4, giving the Herbivore 5 turns
	 * before dying.
	 */
	public Herbivore() {
		color = Color.YELLOW;
	}
	
	
	/**
	 * Validates if the neighboring cells can be moved into.
	 * based on whether a cell is empty or contains food
	 * @param neighbors
	 * @return
	 */
	public boolean validate(ArrayList<Cell> neighbors) {
		int size = neighbors.size();
		int validSpots = 0;
		for (int i = 0; i < size; i++) {
			if (neighbors.get(i).getSpecies() instanceof HerbivoreEdible ||
					neighbors.get(i).getSpecies() == null) {
				validSpots++;
			}
		}
		return validSpots >= 1 ? true : false;
	}
	
	/**
	 * Checks if the cell contains food.
	 * @see Animal#edible(Cell)
	 */
	public boolean edible(Cell cell) {
		if (cell.getSpecies() instanceof HerbivoreEdible) {
			return true;
		} 
		return false;
	}
	
	/**
	 * Method to give birth, the method takes the number of
	 * neighboring mates, empty cells, and food to give birth
	 * to a new animal. If minimum requirements to give birth are met,
	 * a new Animal spawns
	 */
	public void giveBirth(int nMate, int nEmpty, int nFood, ArrayList<Cell> neighbors) {
		int choose = 0;
		int size = neighbors.size();
		if (nMate >= 1 && nEmpty >= 2 && nFood >= 2) {
			do  {
				choose = RandomGenerator.nextNumber(size);
				
			} while (neighbors.get(choose).getSpecies() != null);

			neighbors.get(choose).setSpecies(new Herbivore());

			neighbors.get(choose).getSpecies().setCanMate(false);
			this.setCanMate(false);
		}	
	}
}
