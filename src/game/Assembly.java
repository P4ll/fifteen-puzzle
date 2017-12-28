package game;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import pac.Board;

public class Assembly extends JPanel {
	public static Board board;
	public static int n;
	private static int w = 25, h = 25;

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.orange);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (board.blocks[i][j] == 0) {
					continue;
				}
				g.fillRect(5 + j * 26, 5 + i * 26, w, h);
				g.setColor(Color.black);
				if (board.blocks[i][j] != 0) {
					g.drawString(Integer.toString(board.blocks[i][j]), 5 + j * 26 + w / 2 - 5, 5 + i * 26 + h / 2 + 5);
				}

				g.setColor(Color.orange);
			}
		}
	}

	public static void moveLeft() {
		if (board.zeroJ + 1 >= 0 && board.zeroJ + 1 < board.dimension()) {
			board.blocks[board.zeroI][board.zeroJ] = board.blocks[board.zeroI][board.zeroJ + 1];
			board.blocks[board.zeroI][board.zeroJ + 1] = 0;
			board.zeroJ += 1;
		}
	}

	public static void moveRight() {
		if (board.zeroJ - 1 >= 0 && board.zeroJ - 1 < board.dimension()) {
			board.blocks[board.zeroI][board.zeroJ] = board.blocks[board.zeroI][board.zeroJ - 1];
			board.blocks[board.zeroI][board.zeroJ - 1] = 0;
			board.zeroJ -= 1;
		}
	}

	public static void moveUp() {
		if (board.zeroI + 1 >= 0 && board.zeroI + 1 < board.dimension()) {
			board.blocks[board.zeroI][board.zeroJ] = board.blocks[board.zeroI + 1][board.zeroJ];
			board.blocks[board.zeroI + 1][board.zeroJ] = 0;
			board.zeroI += 1;
		}
	}

	public static void moveDown() {
		if (board.zeroI - 1 >= 0 && board.zeroI - 1 < board.dimension()) {
			board.blocks[board.zeroI][board.zeroJ] = board.blocks[board.zeroI - 1][board.zeroJ];
			board.blocks[board.zeroI - 1][board.zeroJ] = 0;
			board.zeroI -= 1;
		}
	}
}
