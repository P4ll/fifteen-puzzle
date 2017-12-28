package pac;

import java.util.Comparator;

import edu.princeton.cs.algs4.Queue;
import pac.Board.Pair;

/**
 * Данный класс cлужит для создания состояний, которые могут использоваться
 * другими классами.
 */
public class Board implements Comparable<Board> {
	/** Поле последовательности костяшек */
	public int blocks[][];
	/** Свойство, указывающиее на возможность или невозможность решения при 1 и 0 соотвественно. -1 при ещё неопределённом результате.*/
	private int solvable = -1;
	/** Объект, дающий эвристичекую оценку узлу. */
	private Heuristic heuristic;
	/** Размерность доски */
	private int n;

	/** Поле номера строки пустой костяшки */
	public int zeroI;
	/** Поле номера столбца пустой костяшки */
	public int zeroJ;
	/** Поле счетчика ходов */
	public int moves = 0;
	/** Поле предыдущего состояния */
	public Board parent = null;
	/** Поле значения эвристики */
	private int heuristicValue = 0;
	/** Свойство - тип эвристики: 0 - хемминг, 1 - евклид, 2 - манхеттен, 3 - манхеттен с линейнм конфликтом. */
	private int heuristicType;

	/**
	 * 
	 * Принимает последовательность костяшек, передает ее полю blocks,
	 * определяет номера строки и столбца пустой костяшки, получает оцененное
	 * значение эвристики.
	 * 
	 * @param blocks
	 *            Последовательность костяшек.
	 * 
	 */
	public Board(int[][] blocks, int hType, Board parent) {
		n = blocks.length;
		heuristicType = hType;
		this.blocks = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				this.blocks[i][j] = blocks[i][j];
				if (this.blocks[i][j] == 0) {
					zeroI = i;
					zeroJ = j;
				}
			}
		}
		this.parent = parent;
		heuristic = new Heuristic(this, heuristicType);
		heuristicValue = heuristic.getHeuristic();
	}

	/**
	 * Принимает и передает последовательность костяшек полю blocks,
	 * родительское состояние, номер хода, размерность, номера строки и столбца
	 * пустой костяшки, а также значение эвристики.
	 * 
	 * @param b состояние.          
	 * @param p родительское состояние.
	 * @param m номер хода.
	 */
	public Board(Board b, Board p, int m) {
		this.blocks = b.blocks;
		parent = p;
		moves = m;
		n = b.blocks.length;
		zeroI = b.zeroI;
		zeroJ = b.zeroJ;
		heuristicType = b.heuristicType;
		heuristicValue = b.heuristicValue;
	}

	/**
	 * Возвращает размерность головоломки
	 * 
	 * @return размерность головоломки
	 */
	public int dimension() {
		return n;
	}

	/**
	 * Определяет, достигнута ли конечная позиция
	 * 
	 * @return true - если достигнута, false - если нет
	 */
	public boolean isGoal() {
		return (heuristicValue == 0);
	}

	/**
	 * Сравнивает два состояния доски.
	 * 
	 * @param y состояние, с которым сравнивается текущее состояние
	 *            Определяет, совпадают ли состояния.
	 * @return true - если состояния совпадают, false - если не совпадают.
	 */
	@Override
	public boolean equals(Object y) {
		if (y == null) {
			return false;
		}
		if (y.getClass() != this.getClass()) {
			return false;
		}
		Board temp = (Board) y;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (this.blocks[i][j] != temp.blocks[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Определяет больше или меньше последний неравный элемент, или же определяет, что все элементы равны.
	 * 
	 * @param b состояние доски, с которым сравнивают текующее состояние.
	 * @return возращает целое число, которые указывает на положение одного объекта относительно другого.
	 */
	@Override
	public int compareTo(Board b) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (this.blocks[i][j] < b.blocks[i][j]) {
					return -1;
				} else if (this.blocks[i][j] > b.blocks[i][j]) {
					return 1;
				}
			}
		}
		return 0;
	}

	/**
	 * Находит приоритет вершины, сумму эвристической функции и расстояния до текущей вершины.
	 * @return значение приоритета вершины.
	 */
	public int getPriority() {
		return heuristicValue + moves;
	}

	/**
	 * 
	 * Сдвигает пустую ячейку (нулевую) на указанную.
	 * 
	 * @param arr последовательность костяшек.
	 *            
	 * @param zI номер строки пустой костяшки.
	 *            
	 * @param zJ номер столбца пустой костяшки.
	 *            
	 * @param shift направления перемещения пустой костяшки.
	 * @return новую последовательность костяшек.         
	 * 
	 *            
	 * 
	 * 
	 */
	private int[][] shiftZero(int[][] arr, int zI, int zJ, Pair shift) {
		arr[zI][zJ] = arr[zI + shift.first][zJ + shift.second];
		arr[zI + shift.first][zJ + shift.second] = 0;
		return arr;
	}

	/**
	 * Реализует структуру данных - пара элементов.
	 * 
	 * @class Pair
	 * @author Круглов Андрей
	 */
	class Pair {
		/** Свойства, хранящие значение целочисленной пары элементов. */
		public int first, second;
		/**
		 * Конструктор, определяющий свойства экземпляра класса.
		 */
		Pair(int f, int s) {
			first = f;
			second = s;
		}
	}

	/**
	 * 
	 * Метод, который находит соседей (соседнюю вершин, которая связна с текущей).
	 * Соседние вершины - вершины, к которым можно добраться, сделав один разрешённый ход: влево, вправо, вверх, вниз, при том не выходящий за границы.
	 * 
	 * @return любой перечисляемый контейнер.
	 */
	public Iterable<Board> neighbors() {
		Queue<Board> q = new Queue<Board>();
		Pair[] possibleMoves = { new Pair(1, 0), new Pair(-1, 0), new Pair(0, 1), new Pair(0, -1) };
		for (Pair j : possibleMoves) {
			boolean isRangeI = (zeroI + j.first) >= 0 && (zeroI + j.first) < n;
			boolean isRangeJ = (zeroJ + j.second) >= 0 && (zeroJ + j.second) < n;
			if (isRangeI && isRangeJ) {
				int[][] temp = cloneBlocks();
				temp = shiftZero(temp, zeroI, zeroJ, j);

				q.enqueue(new Board(temp, heuristicType, this));
			}
		}
		return q;
	}

	/**
	 * Метод, создающий копию двумерного равностороннего массива.
	 * С его помощью создаём каждого соседа.
	 * 
	 * @return скопированную последовательность костяшек для состояния
	 */
	private int[][] cloneBlocks() {
		int[][] temp = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				temp[i][j] = blocks[i][j];
			}
		}
		return temp;
	}

	/**
	 * Переопределённый стандартный метод класса, возвращает строку с размерностью и расположением костяшек.
	 * 
	 * @return строка с размерностью и костяшками.
	 */
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(n + "\n");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				s.append(String.format("%2d ", blocks[i][j]));
			}
			s.append("\n");
		}
		return s.toString();
	}
	
	public int getHeuristicValue() {
		return heuristicValue;
	}

	/**
	 * Метод определяет, возможно ли вообще когда-нибудь прийти к конечной цели, делая правильные ходы и текущего состояния.
	 * 
	 * @return true - если можно собрать, false - если нельзя.
	 */
	public boolean isSolvable() {
		if (solvable == 1) {
			return true;
		}
		if (solvable == 0) {
			return false;
		}
		int[] a = new int[blocks.length * blocks.length];
		int n = blocks.length;
		int zeroI = 0;
		int inv = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				a[i * n + j] = blocks[i][j];
				if (blocks[i][j] == 0) {
					zeroI = i;
				}
			}
		}
		zeroI++;
		n *= n;
		for (int i = 0; i < n; i++) {
			if (a[i] != 0) {
				for (int j = 0; j < i; j++) {
					if (a[j] > a[i]) {
						inv++;
					}
				}
			}
		}
		if (blocks.length % 2 == 0) {
			inv += zeroI;
		}
		if ((inv % 2) == 0) {
			solvable = 1;
		} else {
			solvable = 0;
		}
		return solvable == 1;
	}
}