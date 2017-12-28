package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import pac.Board;

public class HeuristicTest {

	@Test
	public void testGetHeuristic() {
		int[][] arr = {
				{1, 2, 3},
				{4, 5, 6},
				{7, 8, 0}};
		Board b = new Board(arr, 3, null);
		assertTrue(b.getHeuristicValue() == 0);
		
		arr = new int[][] {{1, 2, 3}, 
							{4, 5, 6}, 
							{7, 8, 0}}; 
		b = new Board(arr, 0, null); 
		assertTrue(b.getHeuristicValue() == 0);
		
		arr = new int[][] {{1, 2, 3}, 
			{4, 5, 6}, 
			{7, 8, 0}}; 
		b = new Board(arr, 1, null); 
		assertTrue(b.getHeuristicValue() == 0);

		arr = new int[][] {{1, 2, 3}, 
			{4, 5, 6}, 
			{7, 8, 0}}; 
		b = new Board(arr, 2, null); 
		assertTrue(b.getHeuristicValue() == 0);
		
		arr = new int[][] {
			{1, 7, 2, 3}, 
			{5, 6, 4, 8}, 
			{9, 10, 11, 12},
			{13, 14, 15, 0}
			}; 
		b = new Board(arr, 2, null); 
		assertTrue(b.getHeuristicValue() == 6);
		
		arr = new int[][] { 
			{1, 4, 3, 2}, 
			{5, 6, 12, 8}, 
			{9, 10, 11, 7}, 
			{13, 14, 15, 0} 
			}; 
		b = new Board(arr, 3, null);
		
		assertTrue(b.getHeuristicValue() == 14);
		arr = new int[][] { 
			{1, 4, 3, 2}, 
			{5, 6, 12, 8}, 
			{9, 10, 11, 7}, 
			{13, 14, 15, 0} 
			}; 
		b = new Board(arr, 3, null); 
		assertFalse(b.getHeuristicValue() == 11);
		
		arr = new int[][] { 
			{1, 2, 3, 4}, 
			{5, 6, 8, 7}, 
			{9, 10, 11, 12}, 
			{15, 14, 13, 0} 
			}; 
		b = new Board(arr, 0, null); 
		assertFalse(b.getHeuristicValue() == 5);
		
		arr = new int[][] { {1, 5, 9, 13},
							{2, 6, 10, 14}, 
							{3, 7, 11, 15},
							{4, 8, 12, 0}	
		};
		b = new Board(arr, 3, null);
		assertTrue(b.getHeuristicValue() == 40);
		
		arr = new int[][] { {1, 5, 9, 13},
			{2, 6, 10, 14}, 
			{3, 7, 11, 15},
			{4, 8, 12, 0}	
		};
		b = new Board(arr, 2, null);
		assertTrue(b.getHeuristicValue() == 40);
		
		arr = new int[][] { 
			{1, 5, 9, 13}, 
			{2, 6, 10, 14}, 
			{3, 7, 11, 15}, 
			{4, 8, 12, 0} 
			}; 
		b = new Board(arr, 0, null); 
		assertTrue(b.getHeuristicValue() == 12);
		
		arr = new int[][] { 
			{1, 2, 3, 4}, 
			{5, 6, 8, 7}, 
			{9, 10, 11, 12}, 
			{15, 14, 13, 0} 
			}; 
		b = new Board(arr, 1, null); 
		assertFalse(b.getHeuristicValue() == 5);
	}

}
