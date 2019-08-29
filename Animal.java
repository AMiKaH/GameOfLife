 import java.io.*; 

/**
 * A subclass of Lifeform, this will be inherited by all 
 * Animals in future versions of the game and will contain
 * all shared methods among animals. 
 * 
 * @author Amir Kbah
 *
 */
public abstract class Animal extends Lifeform implements Serializable{	
		
	/**
	 * Serialized version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * After an animal move to a cell, this method checks for 
	 * food in the cell and adjusts the food counter as it 
	 * encounters food, no food, or animal's starvation
	 */
	public void eat(Cell source, Cell dest) {
		
		// If an animal starves, it dies here
		if (this.getFed() == 0) {
			source.setSpecies(null);
		
		// If an animal moves into a food cell, they get fed	
		} else if (this.edible(dest)) {
			dest.setSpecies(source.getSpecies());
			dest.getSpecies().setCanMove(false);
			this.setFed(5);
			
		// Otherwise they keep on moving and lose some energy i.e food counter	
		} else {
			dest.setSpecies(source.getSpecies());
			dest.getSpecies().setCanMove(false);
			this.fed--;
			
		}
		
		// Sets the eaten/moved into cell to null to avoid duplication
		source.setSpecies(null);

	}
}
