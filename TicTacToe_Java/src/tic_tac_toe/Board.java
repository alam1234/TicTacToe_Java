package tic_tac_toe;
/**
 * Board.class creates a 3x3 tic tac toe board.
 * Shows position number of each cell.
 * Prints the board after turn a player.
 * 
 * @author  Zahangir Alam
 * @version 1.0
 * @since   2019-11-10
 */
public class Board {
	public final int EMPTY = 0;
	public final int CROSS = 1; // CROSS represents for Human
	public final int NOUGHT = 2; // NOUGHT represents for Computer
	
	public int[][] board = new int[3][3]; // game board in 2D array
    //  board containing (EMPTY, CROSS, NOUGHT)
	/* This method prints position numbers of the board */
	public void positionMap() {
		System.out.println("\nPlease check position numbers from below:\n");
		System.out.println(" 1 | 2 | 3\n-----------\n 4 | 5 | 6\n-----------\n 7 | 8 | 9 \n");
	}
	
	/** Print the game board */
	public void printBoard() {
		System.out.println();
		for (int row = 0; row < 3; ++row) {
			for (int col = 0; col < 3; ++col) {
				printCell(board[row][col]); // print each of the cells
				if (col != 3 - 1) {
					System.out.print("|");   // print vertical partition
				}
			}
			System.out.println();
			if (row != 3 - 1) {
				System.out.println("-----------"); // print horizontal partition
			}
		}
		System.out.println();
	}
	
	/** Print a cell with the specified "content" */
	public void printCell(int content) {
		switch (content) {
	      	case EMPTY:  System.out.print("   "); break;
	      	case NOUGHT: System.out.print(" O "); break;
	      	case CROSS:  System.out.print(" X "); break;
		}
	}
}
