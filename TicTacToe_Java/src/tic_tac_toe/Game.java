package tic_tac_toe;

import java.util.Scanner;
/**
 * Game.class implements the 'main' method which control the game.
 * This class inherited the Player class.
 * The Game object is created here.
 *
 * @author  Zahangir Alam
 * @version 1.0
 * @since   2019-11-10 
 */
public class Game extends Player {
	// Name-constants to represent the various states of the game
	public static final int PLAYING = 0;
	public static final int DRAW = 1;
	public static final int CROSS_WON = 2;
	public static final int NOUGHT_WON = 3;

	public static int currentState;  // the current state of the game
	                                 // (PLAYING, DRAW, CROSS_WON, NOUGHT_WON)
	public int whoStart = CROSS;
	
	/** The entry main method (the program starts here) */
	public static void main(String[] args) {
		char nextRound;
		in = new Scanner(System.in); // the input Scanner
		Game game = new Game();
		System.out.println("=======================================\r\n" + 
	  		"| Tic-Tac-Toe 3x3 grid game.          |\r\n" + 
	  		"| Human(You) vs CPU(Computer) mode.   |\r\n" + 
	  		"| Human(You):     X                   |\r\n" + 
	  		"| CPU(Computer):  O                   |\r\n" + 
	  		"=======================================");
		game.positionMap();
		do {
			// Initialize the game-board and current status
			game.startGame();
			game.printBoard();
			do {
				game.playerMove(game.currentPlayer); // update currentRow and currentCol
				game.updateGame(game.currentPlayer, currntRow, currentCol); // update currentState
				game.printBoard();
				// Print message if game-over
				if (currentState == CROSS_WON) {
					System.out.println("'You' won!");
				} else if (currentState == NOUGHT_WON) {
					System.out.println("'Computer' won!");
				} else if (currentState == DRAW) {
					System.out.println("This game is Draw!");
				}
				// Switch player
				game.currentPlayer = game.switchPlayer(game.currentPlayer);
			} while (currentState == PLAYING); // repeat if not game-over
			System.out.println("\nDo you want to play again?"
				+ "\nYes or No?\nPlease press 'y' to continue or press 'n' to exit the game.");
			nextRound = in.next().charAt(0);
		} while (nextRound == 'y' || nextRound == 'Y');
		System.out.println("Thank you for enjoying the game.\nGood Bye!");
	}
	
	/** Initialize the game-board contents and the current states */
	public void startGame() {
		for (int row = 0; row < 3; ++row) {
			for (int col = 0; col < 3; ++col) {
				board[row][col] = EMPTY;  // all cells empty
			}
		}
		currentState = PLAYING; // ready to play
		currentPlayer = whoStart;  // cross plays first
		whoStart =  (whoStart == CROSS) ? NOUGHT : CROSS;
	}
	
	/** Update the "currentState" after the player with "theSeed" has placed on
	    (currentRow, currentCol). */
	public void updateGame(int theSeed, int currentRow, int currentCol) {
		if (hasWon(theSeed, currentRow, currentCol)) {  // check if winning move
			currentState = (theSeed == CROSS) ? CROSS_WON : NOUGHT_WON;
		} else if (isDraw()) {  // check for draw
			currentState = DRAW;
		}
	}
	
	/** Return true if it is a draw (no more empty cell) */
	public boolean isDraw() {
		for (int row = 0; row < 3; ++row) {
			for (int col = 0; col < 3; ++col) {
				if (board[row][col] == EMPTY) {
					return false;  // an empty cell found, not draw, exit
				}
			}
		}
		return true;  // no empty cell, it's a draw
	}
}
