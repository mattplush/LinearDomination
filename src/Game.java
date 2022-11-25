
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Game {
	
	private int N;
	private int K;
	private int turns;
	private Cell[][] gameBoard;
	private ArrayList<Line> lines  = new ArrayList<Line>();
	private ArrayList<Integer> coordinates = new ArrayList<Integer>();
	private boolean lastTurnValid;

	public Game(File inputFile) throws FileNotFoundException {


		Scanner readFile = new Scanner(inputFile);

		N = readFile.nextInt(); 
		K = readFile.nextInt(); 

		while (readFile.hasNextInt()) {
			coordinates.add(readFile.nextInt());
		}
		readFile.close();

		turns = coordinates.size() / 4;
		lastTurnValid = true;
		createGameBoard();
	}
	
	public void createGameBoard() {

		gameBoard = new Cell[N][N];
		
		for (int row = 0; row < gameBoard.length; row++) {
			for (int col = 0; col < gameBoard[0].length; col++) {
				gameBoard[row][col] = new Cell("-");
			}
		}

	}
	
	
	
	public Cell[][] getGameBoard() {
		
		return gameBoard;
	}
	
	public int getN() {
		return N;
	}

	public int getK() {
		return K;
	}

	public int getTurns() {
		
		return turns;
	}

	public void setLastLine(Line line) {
		
		lines.add(line);
	}

	public Line getLastLine(int index) {
		
		return lines.get(index);
	}
	public int getLastLinesSize() {
		
		return lines.size();
		
	}
	public boolean getLastTurnValid() {
		
		return lastTurnValid;
	}
	
	public void setLastTurnFalse() {
		
		lastTurnValid = false;
		
	}
	public void setLastTurnTrue() {
		
		lastTurnValid = true;

	}
	public boolean lastLinesEmpty() {
		
		return lines.isEmpty();
	}

	public int getCoordinate(int index) {
		return coordinates.get(index);
	}

	public void removeCoordinate(int index) {
		coordinates.remove(index);
	}

	public boolean noMoreCoordinates() {
		return coordinates.isEmpty();
	}
}
