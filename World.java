import java.io.Serializable;

/**
 * World contains a 2D array of cells. The cells contents
 * are determined by a random generator. The world contains 2500 cells
 * with a width of 50 cells and a height of 50 cells
 * @author Amir Kbah
 *
 */
public class World implements Serializable {
	/**
	 * Serialized version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The size of each dimension of the array
	 */
	private final int size = 50;
	
	/**
	 * The 2D array that holds all the cells.
	 */
	private Cell[][] world = new Cell[size][size];
	
	
	/**
	 * The world's constructor initializes the cells using the random
	 * generator as follows:
	 * 1- If a value is 85 or over, it fills the cell with a Herbivore
	 * 2- If a value is 65 to 84 inclusive, then it fills the cell with a plant
	 * 3- If a value is less than 65 then it is an empty cell
	 */
	public World() {
		// The variable that will hold the random value for each cell
		int comeAlive;
		RandomGenerator.reset();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				comeAlive = RandomGenerator.nextNumber(99);
				if(comeAlive >= 80) {
					world[i][j] = new Cell(new Herbivore(), i, j);
		
				} else if (comeAlive >= 60) {
					world[i][j] = new Cell(new Plant(), i, j);
					
				} else if (comeAlive >= 50) {
					world[i][j] = new Cell(new Carnivore(), i, j);
				} else if (comeAlive >= 45) {
					world[i][j] = new Cell(new Omnivore(), i, j);
				} else {
					world[i][j] = new Cell(null, i, j);
				}
			}
		}
	}
	
	/**
	 * Returns the world's 2D array
	 * @return world array
	 */
	public Cell[][] getWorldArr(){
		return this.world;
	}
	
	/**
	 * Returns a cell at a specified position
	 * @param row
	 * @param col
	 * @return cell
	 */
	public Cell getCell(int row, int col) {
		return this.world[row][col];
	}

}
