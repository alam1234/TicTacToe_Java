package test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import tic_tac_toe.Intelligence;

public class IntelligenceTest {
	public static Intelligence intel;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		intel = new Intelligence();
	}

	// Verify hasWon() method when return 'true' value
	@Test
	public void testHasWonTrue() {
		int theSeed = intel.CROSS;
		int currentRow = 0;
		int currentCol = 1;
		int[][] b = {
						{1, 1, 1},
						{0, 1, 1},
						{1, 0, 1}
					};
		intel.board = b;
		
		boolean expected = true;
		boolean result = intel.hasWon(theSeed, currentRow, currentCol);
		
		assertEquals(expected, result);
	}
	
	// Verify hasWon() method when return 'false' value
	@Test
	public void testHasWonFalse() {
		int theSeed = intel.NOUGHT;
		int currentRow = 0;
		int currentCol = 1;
		int[][] b = {
						{1, 2, 1},
						{0, 1, 1},
						{1, 0, 1}
					};
		intel.board = b;
		
		boolean expected = false;
		boolean result = intel.hasWon(theSeed, currentRow, currentCol);
		
		assertEquals(expected, result);
	}

	// Verify max() method
	@Test
	public void testMax() {
		int a = 5;
		int b = 7;
		
		int expected = 7;
		int result = intel.max(a, b);
		
		assertEquals(expected, result);
	}
	
	// Verify min() method
	@Test
	public void testMin() {
		int a = 5;
		int b = 7;
		
		int expected = 5;
		int result = intel.min(a, b);
		
		assertEquals(expected, result);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		intel = null;
	}
}
