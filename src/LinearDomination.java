
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class LinearDomination {
	
	public LinearDomination() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {

			File inputFile;
			File outputFile;

			inputFile = new File("InputFiles\\input.txt");
			outputFile = new File("OutputFiles\\output.txt");
				
			Game linearDomination = new Game(inputFile);
			Cell[][] gameBoard = linearDomination.getGameBoard();
			String symbol = " ";
			boolean gameOn = true;

			while (gameOn) {

				for (int player = 1; player <= linearDomination.getTurns(); player++) {

					if (player % 2 == 0) {

						symbol = "O";

					} else {

						symbol = "X";
					}

					int[] startPoint = { linearDomination.getCoordinate(0), linearDomination.getCoordinate(1) };
					int[] endPoint = { linearDomination.getCoordinate(2), linearDomination.getCoordinate(3) };

					for (int j = 0; j < 4; j++) {
						
						linearDomination.removeCoordinate(0);
					}

					Line theLine = new Line(startPoint, endPoint);

					if (linearDomination.lastLinesEmpty() || (kConditionsMet(linearDomination, theLine)
							&& angleConditionMet(linearDomination, theLine))) {

						takeTurn(gameBoard, symbol, theLine);
						linearDomination.setLastTurnTrue();
						linearDomination.setLastLine(theLine);

					} else if (!linearDomination.getLastTurnValid()) {

						gameOn = false;
						break;

					} else {

						linearDomination.setLastLine(theLine);
						linearDomination.setLastTurnFalse();
					}
				}

				if (linearDomination.noMoreCoordinates()) {
					gameOn = false;
				}
			}

			createOutPutFile(linearDomination, outputFile);
	}

	public static void displayBoard(Game linearDomination) {

		Cell[][] gameBoard = linearDomination.getGameBoard();
		int rowSize = gameBoard.length;
		int colSize = gameBoard[0].length;
		int player1 = 0;
		int player2 = 0;

		System.out.println("\r\n" + "  " + "-".repeat(((colSize * 4) + 1)));

		for (int row = 0; row < rowSize; row++) {

			System.out.print("  ");

			for (int col = 0; col < colSize; col++) {

				Cell thisCell = gameBoard[row][col];
				System.out.print(String.format("%-2s%-2s", "|", thisCell.getText()));

				if (gameBoard[row][col].getText().equals("O")) {
					player2++;
				} else if (gameBoard[row][col].getText().equals("X")) {
					player1++;
				}

			}
			System.out.print("|");
			System.out.println();
			System.out.println("  " + "-".repeat((colSize * 4 + 1)));

		}

		System.out.println();

		System.out.println("  Player X: " + player1 + "\r\n  Player O: " + player2 + "\r\n");

	}

	public static void createOutPutFile(Game linearDomination, File outputFile) throws FileNotFoundException {

		PrintWriter printer = new PrintWriter(outputFile);

		Cell[][] gameBoard = linearDomination.getGameBoard();
		int rowSize = gameBoard.length;
		int colSize = gameBoard[0].length;
		int player1 = 0;
		int player2 = 0;

		printer.println("\r\n" + "  " + "-".repeat(((colSize * 4) + 1)));

		for (int row = 0; row < rowSize; row++) {

			printer.print("  ");

			for (int col = 0; col < colSize; col++) {

				Cell thisCell = gameBoard[row][col];
				printer.print(String.format("%-2s%-2s", "|", thisCell.getText()));

				if (gameBoard[row][col].getText().equals("O")) {
					player2++;
				} else if (gameBoard[row][col].getText().equals("X")) {
					player1++;
				}

			}
			printer.print("|");
			printer.println();
			printer.println("  " + "-".repeat((colSize * 4 + 1)));

		}

		printer.println();

		printer.println("  Player X: " + player1 + "\r\n  Player O: " + player2 + "\r\n");

		printer.close();
	}

	public static boolean angleConditionMet(Game linearDomination, Line theLine) {

		double thisAngle = theLine.getAngle();
		double lastAngle;
		for (int i = 0; i < linearDomination.getLastLinesSize(); i++) {

			lastAngle = linearDomination.getLastLine(i).getAngle();

			if (thisAngle == lastAngle) {

				return false;
			}

		}
		return true;

	}

	public static boolean kConditionsMet(Game linearDomination, Line theLine) {

		int[] startPoint = theLine.getStartPoint();
		int[] endPoint = theLine.getEndPoint();
		double[] midPoint = theLine.getMidPoint();
		int k = linearDomination.getK();
		int totalLastLines = linearDomination.getLastLinesSize();

		if (k == 0) {

			return true;

		} else if (k <= totalLastLines) {

			for (int i = totalLastLines - k; i < totalLastLines; i++) {

				int[] lastStartPoint = linearDomination.getLastLine(i).getStartPoint();
				int[] lastEndPoint = linearDomination.getLastLine(i).getEndPoint();
				double[] lastMidPoint = linearDomination.getLastLine(i).getMidPoint();

				if ((startPoint[0] == lastStartPoint[0] && startPoint[1] == lastStartPoint[1])
						|| (endPoint[0] == lastEndPoint[0] && endPoint[1] == lastEndPoint[1])) {

					return false;

				} else if (midPoint[0] == lastMidPoint[0] && midPoint[1] == lastMidPoint[1]) {

					return false;

				}

			}

			return true;

		} else {

			for (int i = 0; i < totalLastLines; i++) {

				int[] lastStartPoint = linearDomination.getLastLine(i).getStartPoint();
				int[] lastEndPoint = linearDomination.getLastLine(i).getEndPoint();
				double[] lastMidPoint = linearDomination.getLastLine(i).getMidPoint();

				if ((startPoint[0] == lastStartPoint[0] && startPoint[1] == lastStartPoint[1])
						|| (endPoint[0] == lastEndPoint[0] && endPoint[1] == lastEndPoint[1])) {

					return false;

				} else if (midPoint[0] == lastMidPoint[0] && midPoint[1] == lastMidPoint[1]) {

					return false;

				}

			}

			return true;

		}

	}

	public static void takeTurn(Cell[][] gameBoard, String symbol, Line theLine) {

		int[] startPoint = theLine.getStartPoint();
		int[] endPoint = theLine.getEndPoint();
		int[] vector = theLine.getDifference();
		int[] perpendicularVector = { vector[1] * -1, vector[0] };
		int c = ((-1 * perpendicularVector[0]) * startPoint[0]) - (perpendicularVector[1] * startPoint[1]);
		double perpendicularMagnitude = Math
				.sqrt((Math.pow(perpendicularVector[0], 2) + Math.pow(perpendicularVector[1], 2)));
		;
		double distance = 0;

		if ((vector[0] > 0) && (vector[1] > 0)) {

			for (int row = startPoint[0]; row <= endPoint[0]; row++) {

				for (int col = startPoint[1]; col <= endPoint[1]; col++) {

					double testLine = ((row) * perpendicularVector[0]) + ((col) * perpendicularVector[1]) + c;

					distance = testLine / perpendicularMagnitude;

					if ((distance < 0.5 && distance >= 0) || (distance > -0.5 && distance <= 0)) {

						gameBoard[(row - 1)][col - 1].setText(symbol);
					}
				}
			}

		} else if (vector[0] < 0 && vector[1] > 0) {

			for (int i = startPoint[0]; i >= endPoint[0]; i--) {

				for (int j = startPoint[1]; j <= endPoint[1]; j++) {

					int testLine = ((i) * perpendicularVector[0]) + ((j) * perpendicularVector[1]) + c;

					distance = testLine / perpendicularMagnitude;

					if ((distance < 0.5 && distance >= 0) || (distance > -0.5 && distance <= 0)) {

						gameBoard[i - 1][j - 1].setText(symbol);
					}
				}
			}

		} else if (vector[0] > 0 && vector[1] < 0) {

			for (int i = startPoint[0]; i <= endPoint[0]; i++) {

				for (int j = startPoint[1]; j >= endPoint[1]; j--) {

					int testLine = ((i) * perpendicularVector[0]) + ((j) * perpendicularVector[1]) + c;

					distance = testLine / perpendicularMagnitude;

					if ((distance < 0.5 && distance >= 0) || (distance > -0.5 && distance <= 0)) {

						gameBoard[i - 1][j - 1].setText(symbol);
					}
				}
			}

		} else if ((vector[0] < 0) && (vector[1] < 0)) {

			for (int i = startPoint[0]; i >= endPoint[0]; i--) {

				for (int j = startPoint[1]; j >= endPoint[1]; j--) {

					int testLine = ((i) * perpendicularVector[0]) + ((j) * perpendicularVector[1]) + c;

					distance = testLine / perpendicularMagnitude;

					if ((distance < 0.5 && distance >= 0) || (distance > -0.5 && distance <= 0)) {
						gameBoard[i - 1][j - 1].setText(symbol);
					}
				}
			}
		} else if (vector[0] > 0 && vector[1] == 0) {

			int j = endPoint[1];

			for (int i = startPoint[0]; i <= endPoint[0]; i++) {

				int testLine = ((i) * perpendicularVector[0]) + ((j) * perpendicularVector[1]) + c;

				distance = testLine / perpendicularMagnitude;

				if ((distance < 0.5 && distance >= 0) || (distance > -0.5 && distance <= 0)) {
					gameBoard[i - 1][j - 1].setText(symbol);
				}

			}

		} else if (vector[0] < 0 && vector[1] == 0) {

			int j = endPoint[1];

			for (int i = startPoint[0]; i >= endPoint[0]; i--) {

				int testLine = ((i) * perpendicularVector[0]) + ((j) * perpendicularVector[1]) + c;

				distance = testLine / perpendicularMagnitude;

				if ((distance < 0.5 && distance >= 0) || (distance > -0.5 && distance <= 0)) {
					gameBoard[i - 1][j - 1].setText(symbol);
				}
			}

		} else if (vector[0] == 0 && vector[1] > 0) {

			int i = endPoint[0];

			for (int j = startPoint[1]; j <= endPoint[1]; j++) {

				int testLine = ((i) * perpendicularVector[0]) + ((j) * perpendicularVector[1]) + c;

				distance = testLine / perpendicularMagnitude;

				if ((distance < 0.5 && distance >= 0) || (distance > -0.5 && distance <= 0)) {
					gameBoard[i - 1][j - 1].setText(symbol);
				}
			}

		} else if (vector[0] == 0 && vector[1] < 0) {

			int j = endPoint[0];

			for (int i = startPoint[1]; i >= endPoint[1]; i--) {

				int testLine = ((i) * perpendicularVector[0]) + ((j) * perpendicularVector[1]) + c;

				distance = testLine / perpendicularMagnitude;

				if ((distance < 0.5 && distance >= 0) || (distance > -0.5 && distance <= 0)) {
					gameBoard[i - 1][j - 1].setText(symbol);
				}

			}

		} else if (vector[0] == 0 && vector[1] == 0) {

		}
	}

}
