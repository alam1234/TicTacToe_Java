package tic_tac_toe;

import java.util.ArrayList;
import java.util.List;
/**
 * Intelligence.class implements how to Computer turns in the best position.
 * * This class inherited the Board class.
 * Check in all possible combinations and choose the best position.
 * 
 * @author  Zahangir Alam
 * @version 1.0
 * @since   2019-11-10
 */
public class Intelligence extends Board {
	
	public static int currntRow, currentCol; // current seed's row and column
	
	/** This method search empty positions of the board and returns positions in a list */
	public List <Position> nextMoves() {
	List <Position> emptyPositions = new ArrayList <>();
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(board[i][j] == EMPTY) {
					emptyPositions.add(new Position(i, j));
				}
			}
		}
		return emptyPositions;
	}
	
    /* This method finds maximum value*/
	public static int max(int a, int b) {
		return a > b ? a : b;
	}

    /* This method finds minimum value*/
	public static int min(int a, int b) {
		return a < b ? a : b;
	}

	/** Return true if the player with "theSeed" has won after placing at
       (currentRow, currentCol) */
	public boolean hasWon(int theSeed, int currentRow, int currentCol) {
		return (board[currentRow][0] == theSeed         // 3-in-the-row
                   && board[currentRow][1] == theSeed
                   && board[currentRow][2] == theSeed
              || board[0][currentCol] == theSeed      // 3-in-the-column
                   && board[1][currentCol] == theSeed
                   && board[2][currentCol] == theSeed
              || currentRow == currentCol            // 3-in-the-diagonal
                   && board[0][0] == theSeed
                   && board[1][1] == theSeed
                   && board[2][2] == theSeed
              || currentRow + currentCol == 2  // 3-in-the-opposite-diagonal
                   && board[0][2] == theSeed
                   && board[1][1] == theSeed
                   && board[2][0] == theSeed);
	}
	
	/* This method find who win the game Human or Computer*/
	public int winLos(int theSeed, int row, int col) {
		boolean win = hasWon(theSeed, row, col);
		if(win == true) {
			if(theSeed == NOUGHT) {
				return 10;
			} else {
				return -10;
			}
		}
		return 0;
	}

	/**This method find the best position to turn Computer.
	 * It check the chance of win in the next turn.
	 * It find blocking position for opponent. 
	 * It find best position to win in the next*/
	public int miniMax(int depth, boolean isMax, int theSeed, int row, int col) {
		int score = winLos(theSeed, row, col);
		if (score == 10 || score == -10) {
			return score;
		}
		List <Position> pos = nextMoves();
		if (pos.size() == 0) {
			return 0;
		}
		if (isMax) {
			int best = -1000;
			pos = nextMoves();
			for(Position p : pos) {
				board[p.row][p.col] = NOUGHT;      // Make the move 
				// Call minimax recursively and choose the maximum value
				best = max( best, miniMax(depth+1, !isMax, NOUGHT, p.row, p.col) );
				board[p.row][p.col] = EMPTY;       // Undo the move
			}
			return best;
		} else {
			int best = 1000;
			pos = nextMoves();
			for(Position p : pos) {
				board[p.row][p.col] = CROSS;      // Make the move 
				// Call minimax recursively and choose the maximum value
				best = min( best, miniMax(depth+1, !isMax, CROSS, p.row, p.col) );
				board[p.row][p.col] = EMPTY;      // Undo the move 
			}
			return best;
		}
	}

	/**This will return the best possible move for the Computer */
	public Position findBestMove() {
		int bestVal = -1000; 
		Position bestMove = new Position(-1, -1); 
		List <Position> pos = nextMoves();  // Find all empty cells.
		for(Position p : pos) {
			board[p.row][p.col] = NOUGHT;    // Make the move 
			int moveVal = miniMax(0, false, NOUGHT, p.row, p.col); 
			board[p.row][p.col] = EMPTY;     // Undo the move 
			if (moveVal > bestVal) {
					bestMove.row = p.row;
					bestMove.col = p.col;
					bestVal = moveVal;
			}
		}
		return bestMove;
	}
}