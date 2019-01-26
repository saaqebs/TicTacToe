package app;

import java.util.Arrays;

public class GameBoard {
	
	static boolean[][] gameboard1 = new boolean[3][3];		// false means its empty
	static boolean[][] gameboard2 = new boolean[3][3];
	
	/**
	 * The loop to iterate through one turn during the game
	 * 
	 * @param playerNumber The player's number who's turn it is
	 */
	protected static void turnLoop(int playerNumber) {
		double x = 0;
		double y = 0;
		while (true) {
			// waiting till the mouse is pressed
			if (PennDraw.mousePressed()) {
				
				// x and y coordinates for the mouse click
				x = PennDraw.mouseX();
				y = PennDraw.mouseY();
				
				// if the user clicked anything out of bounds
				if (x < 1 || y < 1 || x > 10 || y > 10) {
					PennDraw.setPenColor(PennDraw.RED);
					PennDraw.text(5.5,0.5,"Invalid press!");
					continue;					// repeat until user pressed valid area
				}
				PennDraw.setPenColor(PennDraw.WHITE);
				PennDraw.filledRectangle(5.5, 0.5, 2, 0.3);
				int X = 0, Y = 0;				// the index for the 2d boolean array game-board
				
				// figuring out which box the user clicked on
				if (x > 1 && x < 4) { X = 0; } 			// x axis
				else if (x > 4 && x < 7) { X = 1; } 
				else { X = 2;}
				
				if (y > 1 && y < 4) { Y = 0; }			// y axis
				else if (y > 4 && y < 7) { Y = 1; } 
				else { Y = 2; }
				
				// checking if this box is filled
				if (!gameboard1[X][Y] && !gameboard2[X][Y]) {
					 if (playerNumber == 1) {
						gameboard1[X][Y] = true;			
						placePiece(X,Y,playerNumber);		// printing the piece on the game-board
					} else {
						gameboard2[X][Y] = true;			
						placePiece(X,Y,playerNumber);		// printing the piece on the game-board
					} 
					return;
				} 
			}
		}
	}
	
	/**
	 * This method graphically places the correct piece in the desired location the player 
	 * wanted to place it. Blue means player 1's piece while red means player 2's piece.
	 * 
	 * @param x The index of the x position on the 2D boolean array
	 * @param y The index of the y position on the 2D boolean array
	 * @param playerNumber 1 means 'Player 1', 2 means 'Player 2'. 
	 */
	protected static void placePiece(int x, int y, int playerNumber) {
		// finding the final position of the x-axis
		double xfinal = 0;	
		if (x == 0) {
			xfinal = 2.5;
		} else if (x == 1) {
			xfinal = 5.5;
		} else {
			xfinal = 8.5;
		}
		
		// finding the final position of the y-axis
		double yfinal = 0;		
		if (y == 0) {
			yfinal = 2.5;
		} else if (y == 1) {
			yfinal = 5.5;
		} else {
			yfinal = 8.5;
		}
		
		// the circle color is blue if player 1
		if (playerNumber == 1) {					
			PennDraw.setPenColor(PennDraw.BOOK_BLUE);
			PennDraw.filledCircle(xfinal, yfinal, 1.2);
		} 
		// the circle color is red if player 2
		else {									
			PennDraw.setPenColor(PennDraw.BOOK_RED);
			PennDraw.filledCircle(xfinal, yfinal, 1.2);
		}
	}
	
	/**
	 * Checks for a winner in the current round of TicTacToe. Returns 0 
	 * if there is no winner.
	 * 
	 * @return The player number of the winner. 
	 */
	public static int check4winner() {
		for (int x = 0; x < 3; x++) {
			if (gameboard1[x][0] && gameboard1[x][1] && gameboard1[x][2]) {
				return 1;
			}
			if (gameboard2[x][0] && gameboard2[x][1] && gameboard2[x][2]) {
				return 2;
			}
		}
		for (int y = 0; y < 3; y++) {
			if (gameboard1[0][y] && gameboard1[1][y] && gameboard1[2][y]) {
				return 1;
			}
			if (gameboard2[0][y] && gameboard2[1][y] && gameboard2[2][y]) {
				return 2;
			}
		}
		if (gameboard1[0][0] && gameboard1[1][1] && gameboard1[2][2]) {
			return 1;
		}
		if (gameboard2[0][0] && gameboard2[1][1] && gameboard2[2][2]) {
			return 2;
		}
		if (gameboard1[0][2] && gameboard1[1][1] && gameboard1[2][0]) {
			return 1;
		}
		if (gameboard2[0][2] && gameboard2[1][1] && gameboard2[2][0]) {
			return 1;
		}
		return 0;
	}
	
	/**
	 * Checks to see whether or not the game ended on a draw.
	 * 
	 * @return true if the game ended in a draw
	 */
	public static boolean isDraw() {
		for (int i = 0; i <= 2; i++) {
			for (int j = 0; j <= 2; j++) {
				if (!gameboard1[i][j] && !gameboard2[i][j]) { return false; }
			}
		}
		PennDraw.clear();
		PennDraw.setFontSize(45);
		PennDraw.setPenColor(PennDraw.DARK_GRAY);
		PennDraw.text(5.5,5.5,"Draw game!");
		return true;
	}
	
	/**
	 * Checks to see if the user would like to play again. Clears the game-board.
	 * 
	 * @return true if the user would like to play again
	 */
	public static boolean playAgain() {
		PennDraw.setPenColor(PennDraw.BLACK);
		PennDraw.text(5.5,2.5,"Press the space key to play again!");
		while (!PennDraw.isKeyPressed(32)) { continue; }
		for (int i = 0; i < gameboard1.length; i++) {
			Arrays.fill( gameboard1[i], false);
		}
		for (int i = 0; i < gameboard2.length; i++) {
			Arrays.fill( gameboard2[i], false);
		}	   
		return true;
	}
	
}
