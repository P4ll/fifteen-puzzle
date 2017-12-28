package pac;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.Queue;

/**
 * 
 *  Данный класс генерирует только собираемые состояния для дальнейшего
 *         использования другими классами.
 */
public class Generator {
	/**
	 * Метод, генерирующий состояние, учитывая параметры.
	 * Переходим к первой вершине, которая не была использована до этого.
	 * 
	 * @param n 			размерность нужной доски.
	 * @param countMoves 	заданное количество легальных ходов.
	 * @param hType			тип эвристики.
	 * @return 				сгенерированное состояние.
	 */
	public Board getGen(int n, int countMoves, int hType) {
		int[][] arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				arr[i][j] = i * n + j + 1;
			}
		}
		arr[n - 1][n - 1] = 0;
		Board board = new Board(arr, hType, null);
		
		Queue<Board> used = new Queue<Board>();
		used.enqueue(board);
		for (int i = 0; i < countMoves; i++) {
			for (Board j : board.neighbors()) {
				int flag = 0;
				for (Board k : used) {
					if (k.equals(j)) {
						flag++;
					}
				}
				if (flag == 0) {
					// neig.insert(j);
					board = j;
					used.enqueue(j);
					break;
				}
			}
		}
		return board;
	}
}