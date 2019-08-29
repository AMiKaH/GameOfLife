import javafx.application.Application;
import java.io.*;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;



/**
 * The driver of the program, initializes a game object and draws the
 * grid containing the cells and their colors. Calls the Game's turn method
 * and updates the grid after each turn.
 * @author Amir Kbah
 *
 */

public class Main extends Application implements Serializable {
	/**
	 * Serialized version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * the size of the array
	 */
	private final int size = 50;
	
	/**
	 * Initializes a new Game object
	 */
	private Game game = new Game();
	
	/**
	 * initializes a new Grid Pane to hold all nodes
	 */
	public transient GridPane grid = new GridPane();
	
	/**
	 * The node that will hold the cell's color on the grid.
	 */
	private transient Rectangle sqr;
	
	/**
	 * Holds a list of sqrs, used to update the grid after each click.
	 */
	private ArrayList<Rectangle> gridCells = new ArrayList<Rectangle>();

	/**
	 * GridPane to hold buttons GridPane and the game's GridPane
	 */
	GridPane home = new GridPane();
	
	/**
	 * Holds the buttons 
	 */
	GridPane menu = new GridPane();
	
	/**
	 * Save button to serialize the game
	 */
	Button save = new Button("Save");
	
	/**
	 * Load button to deserializae the game
	 */
	Button load = new Button("Load");

	/**
	 * The start method, displays the stage using Javafx.
	 * Displays the grid and all of its contents
	 */
	public void start(Stage stage) {
		
		home = new GridPane();
		menu = new GridPane();
		save = new Button("Save");
		save.setPrefWidth(275);
		save.setStyle("-fx-background-color: Cyan;");
		load = new Button("Load");
		load.setStyle("-fx-background-color: red;");
		load.setTextFill(Color.WHITE);
		load.setPrefWidth(275);
		menu.add(save, 0, 0);
		menu.add(load, 1, 0);
		
		this.draw();

		home.add(menu, 0, 0);
		home.add(grid, 0, 1);
		Scene scene = new Scene(home);
		scene.setOnMousePressed(this::GoLClick);
		save.setOnMousePressed(this::save);
		load.setOnMousePressed(this::load);
		stage.setTitle("Game of Life");
		stage.setScene(scene);
		stage.show();
	}
	
	
	/**
	 * Process the mouse clicks, calls the Game's turn method,
	 * reset method, and then updates the grid.
	 * positions.
	 * @param e
	 */
	public void GoLClick(MouseEvent e) {
		
		game.turn();
		this.update();

	}
	
	/**
	 * draws the grid when the game first launches,
	 * or when the game is loaded
	 */
	public void draw() {
		gridCells.clear();
		grid.getChildren().clear();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				game.getWorld().getCell(i, j).setCellColor();
				sqr = new Rectangle(10, 10, game.getWorld().getCell(i, j).getCellColor());
				sqr.setStroke(Color.BLACK);
				sqr.setStrokeWidth(0.25);
				gridCells.add(sqr);
				grid.add(sqr, i, j);
			}
		}	
		
	}
	
	/**
	 * Updates the grid with the new positions of the cells after a turn.
	 */
	public void update() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				gridCells.get(i * size + j).setFill(game.getWorld().getCell(i, j).getCellColor());
			}
		}	
	}
	
	/**
	 * Saves the current state of the game object,
	 * gives users the option to choose where to save the file
	 * and what name to give it
	 * @param e
	 */
	public void save(MouseEvent e) {
		try {
			
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Save File");
			fileChooser.setInitialFileName("GoL-1");
			File file = fileChooser.showSaveDialog(null);
			
	        FileOutputStream fSave = new FileOutputStream(file + ".ser");
	        ObjectOutputStream out = new ObjectOutputStream(fSave);
	        out.writeObject(game);	        
	        out.close();
	        out.flush();
	       
		} catch (NotSerializableException e1){
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (NullPointerException e3) {
			System.out.println("No File Selected");
		}
	}
	
	/**
	 * Loads a game of life saved file.
	 * Resets current grid and arrayList then redraws the grid
	 * @param e
	 */
	public void load(MouseEvent e) {

		try {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open File");
			File file = fileChooser.showOpenDialog(null);
			if (file != null) {
		        FileInputStream fLoad = new FileInputStream(file);
		        ObjectInput in = new ObjectInputStream(fLoad);
		        
		        this.game = (Game) in.readObject();	
				this.draw();
				in.close();
			} else {
				
			}
	        
		} catch (IOException e1){
			e1.printStackTrace();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);

	}
}
