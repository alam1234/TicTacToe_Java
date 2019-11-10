package tic_tac_toe;

import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Player.class is to implemented to turn players (human or computer).
 * This class inherited the Intelligence class.
 * Update turned value in the global variable.
 * 
 * @author  Zahangir Alam
 * @version 1.0
 * @since   2019-11-10
 */
public class Player extends Intelligence {
	
	public static Scanner in;
	public int currentPlayer; // the current player (CROSS or NOUGHT)
	public int switchPlayer( int currentPlayer) {
		return currentPlayer = (currentPlayer == CROSS) ? NOUGHT : CROSS;
	}
	
	/** Player Human or Computer with the "theSeed" makes one move.
    Update global variables "currentRow" and "currentCol". */
	public void playerMove(int theSeed) {
	   if (theSeed == CROSS) {
		   playerMoveHuman(theSeed);
	   } else {
		   System.out.print("Player 'Computer' is moved as below: \n");
		   Position pos = findBestMove();    // Computer searching for best move.
		   currntRow = pos.row;
		   currentCol = pos.col;
		   
		   board[currntRow][currentCol] = theSeed;  // update game-board content
	   }
	}

	/** Player Human with the "theSeed" makes one move, with input validation.
    Update global variables "currentRow" and "currentCol". */	
	public void playerMoveHuman(int theSeed) {
		boolean validInput = false;  // for input validation
		do {
			System.out.print(" Player 'You', Please enter your move from position (1-9): ");
			try {
				in = new Scanner(System.in); // the input Scanner
				int position = in.nextInt() - 1;  // array index starts at 0 instead of 1
				int row, col;
				if (position >= 0 && position < 9) {
					row = position / 3;
					col = position - (row * 3);
					if(board[row][col] == EMPTY) {
						currntRow = row;
						currentCol = col;
						board[currntRow][currentCol] = theSeed;  // update game-board content
						validInput = true;  // input okay, exit loop
					} else {
						System.out.println(" The position '" + (position + 1) + "' is not empty. Please try again...");
					}
				} else {
					System.out.println(" This move at '" + (position + 1) + "' is not valid. Please try again...");
				}
			} catch(InputMismatchException ex) {
				System.out.println(" Please enter Integer Number only.");
			}
		} while (!validInput);  // repeat until input is valid
	}
}