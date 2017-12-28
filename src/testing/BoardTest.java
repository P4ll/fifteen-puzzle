package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import pac.Board;

public class BoardTest {

	@Test
	public void testIsGoal() {
		int[][] arr = {{1, 2, 3},
						{4, 5, 6},
						{7, 8, 0}};
		Board b = new Board(arr, 3, null);
		assertTrue(b.isGoal());
		
		arr = new int[][] { {1, 2, 3, 4},
							{5, 6, 7, 8},
							{9, 10, 11, 12},
							{13, 14, 15, 0}
		};
		b = new Board(arr, 3, null);
		assertTrue(b.isGoal());
		
		arr = new int[][] {
			{1, 2, 3, 4},
			{5, 6, 7, 8},
			{9, 10, 11, 12},
			{13, 15, 14, 0}
			};
		b = new Board(arr, 3, null);
		assertFalse(b.isGoal());
		
		arr = new int[][] {
			{1, 2, 3, 4, 5, 6},
			{7, 8, 9, 10, 11, 12},
			{13, 14, 15, 16, 17, 18},
			{19, 20, 21, 22, 23, 24},
			{25, 26, 27, 28, 29, 30},
			{31, 32, 33, 34, 35, 0}
			};
		b = new Board(arr, 3, null);
		assertTrue(b.isGoal());
		
		arr = new int[][] { 
			{1, 2, 3, 4, 5, 6, 7, 8, 9}, 
			{10, 11, 12, 13, 14, 15, 16, 17, 18}, 
			{19, 20, 21, 22, 23, 24, 25, 26, 27}, 
			{28, 29, 30, 31, 32, 33, 34, 35, 36}, 
			{37, 38, 39, 40, 41, 42, 43, 44, 45}, 
			{46, 47, 48, 49, 50, 51, 52, 53, 54}, 
			{55, 56, 57, 58, 59, 60, 61, 62, 63}, 
			{64, 65, 66, 67, 68, 69, 70, 71, 72}, 
			{73, 74, 75, 76, 77, 78, 79, 80, 0} 
			}; 
			b = new Board(arr, 3, null); 
		assertTrue(b.isGoal());
	}

	@Test
	public void testEqualsObject() {
		int[][] arr = {{2, 1, 3},
						{4, 5, 6},
						{7, 8, 0}};
		Board b = new Board(arr, 3, null);
		int[][] arr2 = {{1, 2, 3},
						{4, 5, 6},
						{7, 8, 0}};
		Board b2 = new Board(arr2, 3, null);
		assertFalse(b.equals(b2));
		
		arr = new int[][] { 
			{1, 2, 3, 4, 5, 6, 7, 8, 9}, 
			{10, 11, 12, 13, 14, 15, 16, 17, 18}, 
			{19, 20, 21, 22, 23, 24, 25, 26, 27}, 
			{28, 29, 30, 31, 32, 33, 34, 35, 36}, 
			{37, 38, 39, 40, 41, 42, 43, 44, 45}, 
			{46, 47, 48, 49, 50, 51, 52, 53, 54}, 
			{55, 56, 57, 58, 59, 60, 61, 62, 63}, 
			{64, 65, 66, 67, 68, 69, 70, 71, 72}, 
			{73, 74, 75, 76, 77, 78, 79, 80, 0} 
			};
		b = new Board(arr, 3, null);
		arr2 = new int[][] { 
			{1, 2, 3, 4, 5, 6, 7, 8, 9}, 
			{10, 11, 12, 13, 14, 15, 16, 17, 18}, 
			{19, 20, 21, 22, 23, 24, 25, 26, 27}, 
			{28, 29, 30, 31, 32, 33, 34, 35, 36}, 
			{37, 38, 39, 40, 41, 42, 43, 44, 45}, 
			{46, 47, 48, 49, 50, 51, 52, 53, 54}, 
			{55, 56, 57, 58, 59, 60, 61, 62, 63}, 
			{64, 65, 66, 67, 68, 69, 70, 71, 72}, 
			{73, 74, 75, 76, 77, 78, 79, 0, 80} 
			}; 
		b2 = new Board(arr2, 3, null);
		assertFalse(b.equals(b2));
		
		arr = new int[][]{ 
			{0, 1, 2}, 
			{3, 4, 5}, 
			{7, 6, 8} 
			}; 
		b = new Board(arr, 3, null); 
		arr2 = new int[][] { 
			{0, 1, 2}, 
			{3, 4, 5},
			{7, 6, 8}
			}; 
		b2 = new Board(arr2, 3, null); 
		assertTrue(b.equals(b2));
	}

	@Test
	public void testCompareTo() {
		int[][] arr = {{1, 2, 3},
				{4, 5, 6},
				{7, 8, 0}};
		Board b = new Board(arr, 3, null);
		int[][] arr2 = {{1, 2, 3},
				{4, 5, 6},
				{7, 8, 0}};
		Board b2 = new Board(arr2, 3, null);
		assertTrue(b.compareTo(b2) == 0);
		
		arr = new int[][] { 
			{1, 2, 3, 4, 5, 6, 7, 8, 9}, 
			{10, 11, 12, 13, 14, 15, 16, 17, 18}, 
			{19, 20, 21, 22, 23, 24, 25, 26, 27}, 
			{28, 29, 30, 31, 32, 33, 34, 35, 36}, 
			{37, 38, 39, 40, 41, 42, 43, 44, 45}, 
			{46, 47, 48, 49, 50, 51, 52, 53, 54}, 
			{55, 56, 57, 58, 59, 60, 61, 62, 63}, 
			{64, 65, 66, 67, 68, 69, 70, 71, 72}, 
			{73, 74, 75, 76, 77, 78, 79, 80, 0} 
			};
		b = new Board(arr, 3, null);
		arr2 = new int[][] { 
			{1, 2, 3, 4, 5, 6, 7, 8, 9}, 
			{10, 11, 12, 13, 14, 15, 16, 17, 18}, 
			{19, 20, 21, 22, 23, 24, 25, 26, 27}, 
			{28, 29, 30, 31, 32, 33, 34, 35, 36}, 
			{37, 38, 39, 40, 41, 42, 43, 44, 45}, 
			{46, 47, 48, 49, 50, 51, 52, 53, 54}, 
			{55, 56, 57, 58, 59, 60, 61, 62, 63}, 
			{64, 65, 66, 67, 68, 69, 70, 71, 72}, 
			{73, 74, 75, 76, 77, 78, 79, 0, 80} 
			}; 
		b2 = new Board(arr2, 3, null);
		assertFalse(b.compareTo(b2) == -1);
		
		arr = new int[][]{ 
			{0, 1, 2}, 
			{3, 4, 5}, 
			{7, 6, 8} 
			}; 
		b = new Board(arr, 3, null); 
		arr2 = new int[][] { 
			{0, 1, 2}, 
			{3, 4, 5}, 
			{7, 6, 8} 
			}; 
		b2 = new Board(arr2, 3, null); 
		assertTrue(b.compareTo(b2) == 0);
			
		arr = new int[][]{ 
				{0, 1, 2}, 
				{3, 4, 5}, 
				{7, 6, 8} 
				}; 
		b = new Board(arr, 3, null); 
		arr2 = new int[][] { 
				{0, 1, 2}, 
				{3, 6, 5}, 
				{7, 4, 8} 
				}; 
		b2 = new Board(arr2, 3, null); 
		assertTrue(b.compareTo(b2) == -1);
		
		arr = new int[][]{ 
			{0, 1, 2}, 
			{3, 4, 5}, 
			{7, 6, 8} 
			}; 
		b = new Board(arr, 3, null); 
		arr2 = new int[][] { 
			{0, 1, 2}, 
			{3, 6, 5}, 
			{7, 4, 8} 
			}; 
		b2 = new Board(arr2, 3, null); 
		assertFalse(b.compareTo(b2) == 0);
		
		arr = new int[][] {
			{1, 2, 3, 4},
			{5, 6, 7, 8},
			{9, 10, 11, 12},
			{13, 15, 0, 14}
			};
		b = new Board(arr, 3, null);
		arr2 = new int[][] {
			{1, 2, 3, 4},
			{5, 6, 7, 8},
			{9, 10, 11, 12},
			{13, 15, 14, 0}
			};
		b2 = new Board(arr2, 3, null);
		assertTrue(b.compareTo(b2) == -1);
		
		arr = new int[][]{ 
			{1, 0, 2}, 
			{3, 4, 5}, 
			{7, 6, 8} 
			}; 
		b = new Board(arr, 3, null); 
		arr2 = new int[][] { 
			{0, 1, 2}, 
			{3, 6, 5}, 
			{7, 4, 8} 
			}; 
		b2 = new Board(arr2, 3, null); 
		assertTrue(b.compareTo(b2) == 1);
	}

	@Test
	public void testIsSolvable() {
		int[][] arr = {{1, 2, 3},
						{4, 5, 6},
						{7, 8, 0}};
		Board b = new Board(arr, 3, null);
		assertTrue(b.isSolvable());
		
		arr = new int[][] { { 1, 2, 3 },
							{ 4, 6, 5 },
							{ 7, 8, 0 } };
		b = new Board(arr, 3, null);
		assertFalse(b.isSolvable());
		
		arr = new int[][] {
			{1, 2, 3, 4},
			{5, 6, 7, 8},
			{9, 10, 11, 12},
			{13, 15, 14, 0}
			};
		b = new Board(arr, 3, null);
		assertFalse(b.isSolvable());
		
		arr = new int[][] { 
			{1, 2, 3, 4, 5, 6, 7, 8, 9}, 
			{10, 11, 12, 13, 14, 15, 16, 17, 18}, 
			{19, 20, 21, 22, 23, 24, 25, 26, 27}, 
			{28, 29, 30, 31, 32, 33, 34, 35, 36}, 
			{37, 38, 39, 40, 41, 42, 43, 44, 45}, 
			{46, 47, 48, 49, 50, 51, 52, 53, 54}, 
			{55, 56, 57, 58, 59, 60, 61, 62, 63}, 
			{64, 65, 66, 67, 68, 69, 70, 71, 72}, 
			{73, 74, 75, 76, 77, 78, 79, 80, 0} 
			}; 
			b = new Board(arr, 3, null); 
		assertTrue(b.isSolvable());
		
		arr = new int[][] { 
			{11, 4, 13, 5}, 
			{8, 6, 7, 14}, 
			{3, 2, 15, 9}, 
			{0, 10, 12, 1} 
			}; 
		b = new Board(arr, 3, null); 
		assertFalse(b.isSolvable());
		
		arr = new int[][] { 
			{11, 15, 14, 4}, 
			{13, 9, 7, 2}, 
			{12, 5, 1, 8}, 
			{3, 10, 6, 0} 
			}; 
		b = new Board(arr, 3, null); 
		assertTrue(b.isSolvable());
	}

}
