package pac;
 
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeSet;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
 
public class Solver {
    private static final Comparator<Board> comp = new MyComp();
    private MinPQ<Board> q = new MinPQ<Board>(comp);
    private Board temp;
    private boolean maySolve;
    public Solver(Board initial) {
    	maySolve = initial.isSolvable();
        temp = new Board(initial, null, 0);
        q.insert(temp);
        while (!temp.isGoal()) {
            temp = q.delMin();
            for (Board i : temp.neighbors()) {
                if (!i.equals(temp.parent)) {
                	q.insert(new Board(i, temp, temp.moves + 1));
                }
            }
        }
    }
    public static class MyComp implements Comparator<Board> {
		public int compare(Board o1, Board o2) {
			if (o1.getPriority() < o2.getPriority()) {
				return -1;
			}
			if (o1.getPriority() > o2.getPriority()) {
				return 1;
			}
			return 0;
		}
    }
    public Iterable<Board> solution() {
        if (!maySolve) {
            return null;
        }
        Stack<Board> t = new Stack<Board>();
        Board p = temp;
        while (p.parent != null) {
            t.push(p);
            p = p.parent;
        }
        return t;
    }
    public int moves() {
        if (maySolve) {
            return temp.moves;
        }
        else {
            return -1;
        }
    }
 
    public static void main(String[] args) {
    	Scanner scan = new Scanner(System.in);
    	int n = scan.nextInt();
    	int[][] arr = new int[n][n];
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < n; j++) {
    			arr[i][j] = scan.nextInt();
    		}
    	}
    	Board startBoard = new Board(arr, 3, null);
    	if (!startBoard.isSolvable()) {
    		System.out.println("No solution!");
    	}
    	else {
    		Solver solve = new Solver(startBoard);
    		System.out.println("Minimum moves: " + solve.moves());
    		for (Board i: solve.solution()) {
    			System.out.println(i.toString());
    		}
    	}
    }
}