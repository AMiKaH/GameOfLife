Amir Kbah
A01041107
Set E

Game of Life

This is a simple game in which a world is populated with different life forms. 
For this version there are plants, Herbivores, Omnivores, and Carnivores. Once the game launches a grid is drawn
With cells that contain the different Life forms

Each click represents a turn. In each turn, Animals move to a new cell, eat if there's food near them, mate if they
meet the mating requirements, and Plants pollinate other plants. The game makes sure that animals go into cells
That have food first, if none available then they move to an empty cell. Animals never collide when they move.
No cannibalism.


Class Hierarchy:

Lifeform - is the top of the hierarchy contains method shred with other lifeforms.
Animal - extends Lifeform and contains method shared between animals 
Herbivore - extends Animal and contains methods relevant to a Herbivore and Animal only.
	Herbivores eat Plants only
Omnivore - extends Animal and contains methods relevant to an Omnivore and Animal only.
	Omnivores eat Herbivores, Carnivores, and Plants
Carnivore - extends Animal and contains methods relevant to a Carnivore and Animal only.
	Carnivores eat Herbivores and Omnivores
Plant - extends Lifeform and contains methods relevant to plant only.
Cell - contains a Lifeform, contains the lifeform's color, and has X and Y coordinates.
World - contains cells, a 2D array of cells that is used to represent the word and the grid on Javafx
Game - has the rules, the turn method which updates the world array with the new positions of the herbivore and if any plants seeded.
Main -  the driver of the program, draws a grid with cells in it an d on each click the grid is updated with the new loactions.


