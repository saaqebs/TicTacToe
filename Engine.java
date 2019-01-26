package app;

public class Engine {

	/**
	 * Application driver
	 */
	public static void main(String[] args) {
		PennDraw.setCanvasSize(700, 700);	// making the canvas bigger
		PennDraw.setXscale(0, 11);			// setting the scale larger
		PennDraw.setYscale(0, 11);
		PennDraw.setPenColor(PennDraw.BLACK);
		PennDraw.text(5.5,5.5,"Press the space key to play TicTacToe!");
		while (!PennDraw.isKeyPressed(32)) { continue; }
		gameLoop();
		while (GameBoard.playAgain()) { gameLoop(); }
	}
	
	/**
	 * Player 1's turn
	 */
	public static void player1turn() {
		PennDraw.setPenColor(PennDraw.WHITE);
		PennDraw.filledRectangle(5.5, 10.5, 2, 0.3);
		PennDraw.setPenColor(PennDraw.BOOK_BLUE);
		PennDraw.text(5.5,10.5,"Player 1's turn!");
		GameBoard.turnLoop(1);
	}
	
	/**
	 * Player 2's turn
	 */
	public static void player2turn() {
		PennDraw.setPenColor(PennDraw.WHITE);
		PennDraw.filledRectangle(5.5, 10.5, 2, 0.3);
		PennDraw.setPenColor(PennDraw.BOOK_RED);
		PennDraw.text(5.5,10.5,"Player 2's turn!");
		GameBoard.turnLoop(2);
	}
	
	/**
	 * Draw's out the correct dimensions for a TicTacToe board
	 */
	public static void makeGameboard() {
		// for columns
        for (int i = 1; i <= 10; i = i + 3) {
        	PennDraw.setPenColor(PennDraw.BLACK);
            PennDraw.line(1, i, 10, i);
        }
        // for rows
        for (int i = 1; i <= 10; i = i + 3) {
    		PennDraw.setPenColor(PennDraw.BLACK);
    		PennDraw.line(i, 1, i, 10);
        }
	}
	
	/**
	 * Game loop that iterates through one game of Tic-Tac-Toe
	 */
	public static void gameLoop() {
		PennDraw.setFontSize(20);
		PennDraw.clear();
		makeGameboard();
		int winner = 0;
		PennDraw.setFontBold();
		while (true) {
			player1turn();
			winner = GameBoard.check4winner();
			if (winner != 0) {
				PennDraw.clear();
				PennDraw.setFontSize(45);
				PennDraw.text(5.5,5.5,"Player "+winner+" has won the game!");
				break;
			}
			if (GameBoard.isDraw()) { break; }
			player2turn();
			winner = GameBoard.check4winner();
			if (winner != 0) {
				PennDraw.clear();
				PennDraw.setFontSize(45);
				PennDraw.text(5.5,5.5,"Player "+winner+" has won the game!!");
				break;
			}
		}
		PennDraw.setFontSize(20);
	}
	
}
