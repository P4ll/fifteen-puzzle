package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import pac.Board;
import pac.Solver2;

public class SolverTest {

	@Test
	public void testMoves() {
		int[][] arr = { { 5, 8, 7 },
						{ 1, 4, 6 },
						{ 3, 0, 2 } };
		Board b = new Board(arr, 3, null);
		Solver2 solver = new Solver2(b);
		assertTrue(solver.moves() == 27);

		arr = new int[][] { { 1, 2, 3 },
							{ 4, 5, 6 },
							{ 7, 8, 0 } };
		b = new Board(arr, 3, null);
		solver = new Solver2(b);
		assertTrue(solver.moves() == 0);

		arr = new int[][] { { 1, 2, 3 },
							{ 4, 6, 5 },
							{ 7, 8, 0 } };
		b = new Board(arr, 3, null);
		solver = new Solver2(b);
		assertTrue(solver.moves() == -1);

		arr = new int[][] { { 1, 2, 3, 4, 5, 7, 14 },
							{ 8, 9, 10, 11, 12, 13, 6 },
							{ 15, 16, 17, 18, 19, 20, 21 },
							{ 22, 23, 24, 25, 26, 27, 28 },
							{ 29, 30, 31, 32, 0, 33, 34 },
							{ 36, 37, 38, 39, 40, 41, 35 },
							{ 43, 44, 45, 46, 47, 48, 42 } };
		b = new Board(arr, 3, null);
		solver = new Solver2(b);
		assertTrue(solver.moves() == 14);
		
		arr = new int[][] { {5, 6, 2},
							{1, 8, 4},
							{7, 3, 0}
		};
		b = new Board(arr, 3, null);
		solver = new Solver2(b);
		assertTrue(solver.moves() == 18);
	}

}
