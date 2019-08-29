import java.io.Serializable; 
import java.util.ArrayList; 
import javafx.scene.paint.Color;


/**
 * A cell contains a Lifeform or nothing. At this stage the
 * cell can contain either a Herbivore, a plant or nothing.
 * Cells get the color of their Lifeforms. If a cell contains 
 * nothing then its color is White
 * 
 * @author Amir Kbah
 *
 */
public class Cell implements Serializable {
	/**
	 * Serialized version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The lifeform variable that holds the cell's
	 * lifeform
	 */
	private Lifeform life;
	
	/**
	 * The variable that will hold the cell's color
	 */
	transient Color colors;
	
	transient Color white = Color.WHITE;
	
	/**
	 * The cells horizontal position
	 */
	int x;
	
	/**
	 * The cell's vertical position
	 */
	int y;
	
	/**
	 * The cell's constructor, takes in 3 parameters:
	 * a lifeform, x position and y position. the color of the
	 * cell is taken from the lifeform passed to the constructor.
	 * If no lifeform is passed (i.e null) the cell's coor becomes white
	 * to indicate that nothing is occupying this cell.
	 * @param life
	 * @param x
	 * @param y
	 */
	public Cell(Lifeform life, int x, int y) {
		this.life = life;
		if (life == null) {
			this.colors = Color.WHITE;
		} else {
			this.colors = life.getColor();
		}
		this.x = x;
		this.y = y;
	}
	
	/**
	 * returns the cell's color
	 * @return color
	 */
	public Color getCellColor() {
		return this.colors;
	}
	
	/**
	 * returns the lifeform occupying the cell
	 * @return life
	 */
	public Lifeform getSpecies() {
		return this.life;
	}
	
	/**
	 * Sets a new species to the cell and at the
	 * same time changes the color of the cell to that
	 * of a the new lifeform.
	 * @param life
	 */
	public void setSpecies(Lifeform life) {
		this.life = life;
		this.setCellColor();
	}
	
	/**
	 * Sets the cell color. 
	 * Used after deserialization as the .ser file as Colors
	 * cannot be serialized.
	 */
	public void setCellColor() {
		if (life == null) {
			this.colors = white;
		} else if (life instanceof Herbivore) {
			this.colors = Color.YELLOW;
			life.setColor(Color.YELLOW);
		} else if (life instanceof Omnivore) {
			this.colors = Color.BLUE;
			life.setColor(Color.BLUE);
		} else if (life instanceof Carnivore) {
			this.colors = Color.RED;
			life.setColor(Color.RED);
		} if (life instanceof Plant) {
			this.colors = Color.GREEN;
			life.setColor(Color.GREEN);
		}
	}

	/**
	 * returns the cell's X position
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * sets the cell's X position
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * returns the cells Y position
	 * @return
	 */
	public int getY() {
		return y;
	}

	/**
	 * sets the cell's Y position
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Returns an array list of all neighboring cells.
	 * Excludes self. So the returned Arraylist size can be either
	 * 3 if the cells i in one of the corners, 5 if the cell is on one
	 * of the sides, or 8 if the cell is not near the edge or the corner.
	 * The returned ArraList ensures that no lifeform can move our of 
	 * the original grid
	 * @param world
	 * @return neighbors
	 */
	public ArrayList<Cell> getNeighbors(World world){
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) { 
				if (this.getX() + i < 0 || this.getX() + i > 49
						|| this.getY() + j < 0 || this.getY() + j > 49
						||(i == 0 && j == 0)) {
				
				} else {
					neighbors.add(world.getCell(this.getX() + i, this.getY() + j));	
				}
			}
		}	
		return neighbors;
	}
	

}
