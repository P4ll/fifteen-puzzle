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

/**
 * Класс, реализующий алгоритм поиска A*.
 * Реализован для нахождения наименьшего количества ходов, необходимых для достижения целевого состояния в игре "Пятнашки", размерностью от 2 до 128.
 */
public class Solver2 {
	/** Объект, контроллирующйи порядок расположения объектов {@link#Board}. */
	private static final Comparator<Board> comp = new MyComp();
	/** Множество, реализованное на дереве, где элементы распологаются посредством сравнения. */
	private TreeSet<Board> used = new TreeSet<Board>(comp);
	/** Объект, хранящий текущее состояние {@link#Solver2} */
	private Board currentState;
	/** Свойство решаемости, хранит значение булевой переменной, указывающий на возможность разрешения данной доски. */
	private boolean maySolve;
	
	public int countCheckVertex = 0;
	public int countCreateVertex = 0;

	/**
	 * 
	 * Реализация алгоритма А*.
	 * Проверяем существует ли, решение.
	 * Создаём начальную вершину. Вынимаем вершину с наименьшим приоритетом, просматриваем её соседей, если они уже использовались,
	 * релаксируем расстояние до неё, иначе добавляем её. повторяем до тех пор, пока не придём к целевой вершине (собранная доска пятнашек).
	 * 
	 * Гарантируется, что алгоритм, при использовании допустимых эвристик, находит кротчайший путь до целевой вершины.
	 * 
	 * @param initial		принимает состоние доски.
	 */
	public Solver2(Board initial) {
		maySolve = initial.isSolvable();
		if (!maySolve) {
			return;
		}
		currentState = new Board(initial, null, 0);
		used.add(currentState);
		while (!currentState.isGoal()) {
			countCheckVertex++;
			currentState = used.first();
			used.remove(currentState);
			countCreateVertex--;
			for (Board i : currentState.neighbors()) {
				if (!used.contains(i)) {
					used.add(new Board(i, currentState, currentState.moves + 1));
					countCreateVertex++;
				}
			}
		}
	}

	/**
	 * Класс реализующий возможность сравнения двух объектов класса класса {@link#Board}.
	 * 
	 * @author Танькин Андрей
	 * @see Board#equals(Object)
	 */
	public static class MyComp implements Comparator<Board> {
		/*
		public int compare(Board o1, Board o2) {
			if (o1.getHeuristicValue() < o2.getHeuristicValue()) {
				return -1;
			}
			if (o1.getHeuristicValue() > o2.getHeuristicValue()) {
				return 1;
			}
			return o1.compareTo(o2);
		}
		*/
		public int compare(Board o1, Board o2) {
			if (o1.getPriority() < o2.getPriority()) {
				return -1;
			}
			if (o1.getPriority() > o2.getPriority()) {
				return 1;
			}
			return o1.compareTo(o2);
		}
		
	}

	/**
	 * Метод, который находит обратный путь: от целевой вершины до начальной.
	 * У каждого класса {@link#Board} есть предок, ссылка на который хранится как отдельное свойство.
	 * Двигаясь по цепочке, мы можеми прийти к вершине, у который свойство parent = null. Следовательно, мы пришли к начальой вершине.
	 * @return объект, реализующий возможность перечисления в for in.
	 */
	public Iterable<Board> solution() {
		if (!maySolve) {
			return null;
		}
		Stack<Board> t = new Stack<Board>();
		Board p = currentState;
		while (p.parent != null) {
			t.push(p);
			p = p.parent;
		}
		return t;
	}

	/**
	 * @return количество ходов, минимально необходимых чтобы прийти в целевую вершину из начальной, если в неё прийти невозможно, возвращает -1.
	 */
	public int moves() {
		if (maySolve) {
			return currentState.moves;
		} else {
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
    	System.out.println("Heuristic value: " + startBoard.getHeuristicValue());
    	if (!startBoard.isSolvable()) {
    		System.out.println("No solution!");
    	}
    	else {
    		Solver2 solve = new Solver2(startBoard);
    		//System.out.println("Checked vertex: " + solve.countCheckVertex);
    		//System.out.println("Created vertex: " + solve.countCreateVertex);
    		System.out.println("Minimum moves: " + solve.moves());
    		for (Board i: solve.solution()) {
    			System.out.println(i.toString());
    		}
    	}
	}
}