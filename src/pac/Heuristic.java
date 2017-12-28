package pac;

/**
 *   	Данный класс служит для описания эвристик, которые будут
 *      использовааться для нахождения кратчайшего пути.
 */
public class Heuristic {
	private Board board;
	/** Поле последовательности костяшек */
	private int[][] blocks;
	/** Поле размерности головоломки */
	private int n;
	/** Поле оцененного эвристического расстояния */
	private int heuristicValue;

	/**
	 * Принимает последовательность костяшек, передает ее полю
	 *            blocks, получает оцененное значение эвристики
	 *            
	 * @param arr		массив, хранящйи состояние доски.
	 * @param type		тип используемой эвристики.
	 */
	public Heuristic(Board board, int type) {
		this.board = board;
		this.blocks = board.blocks;
		n = blocks.length;
		switch (type) {
		case 0:
			heuristicValue = hamming();
			break;
		case 1:
			heuristicValue = euclid();
			break;
		case 2:
			heuristicValue = manhattan();
			break;
		case 3:
			heuristicValue = manPlusLin();
			break;
		case 4:
			heuristicValue = invertDistance();
		default:
			heuristicValue = 0;
			break;
		}
	}

	/**
	 * Возвращает значение выбранной эвристичекой функции.
	 * @return оцененное эвристикой расстояние до конечного состояния.
	 */
	public int getHeuristic() {
		return heuristicValue;
	}

	/**
	 * Приоритетая функция Хемминга.
	 * Считает количество плиток, которые стоят не на своём месте.
	 * 
	 * @return значение функции.
	 */
	private int hamming() {
		int hamm = 0;
		if (board.parent != null) {
			return lightHamming();
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if ((blocks[i][j] != 0) && ((n * i + j + 1) != blocks[i][j])) {
					hamm++;
				}
			}
		}
		return hamm;
	}
	private int lightHamming() {
		Board par = board.parent;
		int hamm = par.getHeuristicValue();
		int parI = board.zeroI;
		int parJ = board.zeroJ;
		if ((par.blocks[parI][parJ]) != parI * n + parJ + 1) {
			hamm--;
		}
		parI = par.zeroI;
		parJ = par.zeroJ;
		if ((blocks[parI][parJ]) != parI * n + parJ + 1) {
			hamm++;
		}
		return hamm;
	}
	/**
	 * Манхеттанское расстояние.
	 * Считает сумму расстояний между каждой плиткой и её целевой позицией.
	 * @return сумму расстояний манхеттана.
	 */
	private int manhattan() {
//		if (board.parent != null) {
//			return lightManh();
//		}
		int man = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (blocks[i][j] != 0) {
					man += Math.abs((blocks[i][j] - 1) / n - i) + Math.abs((blocks[i][j] - 1) % n - j);
				}
			}
		}
		return man;
	}
	private int lightManh() {
		Board par = board.parent;
		int man = par.getHeuristicValue();
		int parI = board.zeroI;
		int parJ = board.zeroJ;
		man -= Math.abs((par.blocks[parI][parJ] - 1) / n - parI) + Math.abs((par.blocks[parI][parJ] - 1) % n - parJ);
		parI = par.zeroI;
		parJ = par.zeroJ;
		man += Math.abs((blocks[parI][parJ] - 1) / n - parI) + Math.abs((blocks[parI][parJ] - 1) % n - parJ);
		return man;
	}
	/**
	 * Функция расстояния евклида.
	 * Определяет сумму расстояний евклида до целевой позиции плитки.
	 * @return сумму евклидовых расстояний.
	 */
	private int euclid() {
//		if (board.parent != null) {
//			return lightEuclid();
//		}
		double e = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (blocks[i][j] != 0) {
					int posI = (blocks[i][j] - 1) / n;
					int posJ = (blocks[i][j] - 1) % n;
					int dist = (posI - i) * (posI - i) + (posJ - j) * (posJ - j);
					e += Math.sqrt((double) dist);
				}
			}
		}
		return (int) e;
	}
	private int lightEuclid() {
		Board par = board.parent;
		int eucl = par.getHeuristicValue();
		int parI = board.zeroI;
		int parJ = board.zeroJ;
		int posI = (blocks[parI][parJ] - 1) / n;
		int posJ = (blocks[parI][parJ] - 1) % n;
		int dist = (posI - parI) * (posI - parI) + (posJ - parJ) * (posJ - parJ);
		eucl -= Math.sqrt((double) dist);
		parI = par.zeroI;
		parJ = par.zeroJ;
		posI = (blocks[parI][parJ] - 1) / n;
		posJ = (blocks[parI][parJ] - 1) % n;
		dist = (posI - parI) * (posI - parI) + (posJ - parJ) * (posJ - parJ);
		eucl += Math.sqrt((double) dist);
		return eucl;
	}
	
	private int invertDistance() {
		int horizontal = 0;
		int vertical = 0;
		int[] a = new int[n * n];
		int[] b = new int[n * n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				a[i * n + j] = blocks[i][j];
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				b[i * n + j] = blocks[j][i];
			}
		}

		int len = n * n;
		for (int i = 0; i < len; i++) {
			if (a[i] != 0) {
				for (int j = 0; j < i; j++) {
					if (a[j] > a[i]) {
						horizontal++;
					}
				}
			}
		}

		for (int i = 0; i < len; i++) {
			if (b[i] != 0) {
				for (int j = 0; j < i; j++) {
					if (b[j] > b[i]) {
						vertical++;
					}
				}
			}
		}

		return vertical / 3 + vertical % 3 + horizontal / 3 + horizontal % 3;
	}

	/**
	 * Определение линейных конфликтов.
	 * Линейным конфликтом называют положение, когда элементы и их целевые позиции находятся в одной строке, при том, бльший элемент слева от меньшего.
	 * 
	 * @return количество линейных конфликтов.
	 */
	private int linearConflict() {
		int lenConf = 0;
		int n = blocks.length;
		int[][] checkRows = new int[n][n];
		int[][] checkColumns = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (blocks[i][j] != 0) {
					checkRows[i][j] = (blocks[i][j] - 1) / n;
					checkColumns[i][j] = (blocks[i][j] - 1) % n;
				} else
					checkColumns[i][j] = checkRows[i][j] = -1;
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - 1; j++) {
				if (checkRows[i][j] == i) {
					for (int k = j + 1; k < n; k++) {
						if (blocks[i][j] > blocks[i][k] && checkRows[i][k] == i) {
							lenConf += 1;
						}
					}
				}
				if (checkColumns[i][j] == j) {
					for (int k = i + 1; k < n; k++) {
						if (blocks[i][j] > blocks[k][j] && checkColumns[k][j] == j) {
							lenConf += 1;
						}
					}
				}
			}
		}
		return lenConf;
	}

	/**
	 * 
	 * Вычисление комбинационной эвристической функции Манхеттана и линейных конфликтов.
	 * 
	 * @return сумму манхеттанского расстояния и удвоенного количество линейных конфликтов.
	 */
	private int manPlusLin() {
		return manhattan() + 2 * linearConflict();
	}
}