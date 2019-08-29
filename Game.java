import java.util.ArrayList;
import java.io.Serializable;

/**
 * The Game class initializes the world and the first 
 * rendering. It contains the turn method which calls each cell's 
 * move method (if any).
 * @author Amir Kbah
 *
 */
public class Game implements Serializable {
	/**
	 * Serialized version
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Creates a reference for a World object
	 */
	private World world;
	
	/**
	 * variable to hold the size of the world Array
	 */
	private int size;
	
	/**
	 * Instantiates a Cell array
	 */
	private Cell[][] worldArr;
	
	/**
	 * The game constructor, instantiates and initializes a new world
	 * object which contains all cells.
	 */
	public Game() {
		this.world = new World();
		this.size = world.getWorldArr().length;
		worldArr = this.world.getWorldArr();
	}
	
	public void setWorld(World world) {
		this.world = world;
		this.worldArr = this.world.getWorldArr();
	}
	/**
	 * Returns the world of this game object
	 * @return
	 */
	public World getWorld() {
		return this.world;
	}
	

	/**
	 * updates the world's array each turn. It calls the get neighbors method
	 * then it calls each cells move passing the ArrayList returned from the
	 * get neighbors method. 
	 */
	public void turn() {
		this.move();
		this.mate();
		this.resetMoves();
		this.resetMating();
	}
	
	/**
	 * This method calls the move method on all lifeForms
	 */
	public void move() {
		ArrayList<Cell> getNei;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (worldArr[i][j].getSpecies() != null) {
					getNei = worldArr[i][j].getNeighbors(world);
					worldArr[i][j].getSpecies().move(getNei, worldArr[i][j]);
				}					
			}
		}
	}
	/**
	 * Resets the cell's canMove boolean to prepare them for
	 * next turn.
	 */
	public void resetMoves() {
		for (int i = 0; i < size ; i++) {
			for (int j = 0; j < size; j++) {
				if (worldArr[i][j].getSpecies() != null) {
					worldArr[i][j].getSpecies().resetCanMove();
				}
			}
		}
	}
	
	/**
	 * Resets the canMate boolean variable inside Animals 
	 * so that they can mate in the next turn
	 */
	public void resetMating() {
		for (int i = 0; i < size ; i++) {
			for (int j = 0; j < size; j++) {
				if (worldArr[i][j].getSpecies() != null) {
					worldArr[i][j].getSpecies().resetCanMate();
				}
			}
		}
	}
	
	/**
	 * This method calls the createLife method in all lifeForms
	 * inside the cells of the World object.
	 */
	public void mate() {
		ArrayList<Cell> getNei;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (worldArr[i][j].getSpecies() != null) {
					getNei = worldArr[i][j].getNeighbors(world);
					worldArr[i][j].getSpecies().createLife(getNei, worldArr[i][j]);					
				}
			}
		}
	}

}
