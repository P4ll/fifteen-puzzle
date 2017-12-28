package pac;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.Queue;
import pac.Solver.MyComp;

public class Generator2 {
	private static final Comparator<Board> comp = new Solver2.MyComp();
	private TreeSet<Board> used = new TreeSet<Board>(comp);
	
	private Board getBoard(int n, int hType) {
		int[][] a = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				a[i][j] = i * n + j + 1;
			}
		}
		a[n - 1][n - 1] = 0;
		return new Board(a, hType, null);
	}
	public Board getGen(int n, int countMoves, int hType) {
		Board initial = getBoard(n, hType);
		used.add(initial);
		while (initial.getHeuristicValue() != countMoves) {
			initial = used.last();
			used.remove(initial);
			for (Board i : initial.neighbors()) {
				if (!used.contains(i)) {
					used.add(new Board(i, initial, initial.moves + 1));
				}
				else if (i.moves > initial.moves + 1) {
					used.remove(i);
					used.add(new Board(i, initial, initial.moves + 1));
				}
			}
		}
		return initial;
	}
}