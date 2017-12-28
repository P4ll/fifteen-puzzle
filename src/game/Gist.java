package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

public class Gist extends JPanel {
	public static double[][] times;
	public static int leftBound;
	public static int rightBound;
    private int n;
    private static final int X = 50;
    private static final int Y = 406;
    private int x = X;
    private int y = Y;
    private int scale = 1;
    private static final String[] names = new String[] {
    	"Функция хемминга", "Расстояние евклида", "Манхеттенское расстояние",
    	"Манхеттенское расстояние с учётом линейных конфликтов"
    };
    private static final Color[] colors = new Color[] {
    		Color.blue, Color.cyan, Color.black, Color.green
    };
    
    @Override
    public void paint(Graphics g) {
    	super.paint(g);
    	if (times == null) {
    		return;
    	}
    	g.drawLine(X, Y, X + times.length * 100, Y);
    	g.drawLine(X, Y, X, Y - 500);
    	for (int i = 0; i < 50; i++) {
    		g.drawLine(X, Y - i * 20, X + times.length * 100, Y - i * 20);
    		g.drawString(Integer.toString(i * 15), X - 30, Y - i * 20);
    	}
    	for (int i = leftBound, j = 0; i <= rightBound; i++, j++) {
    		g.drawString(Integer.toString(i), X + 25 + 100 * j, Y + 30);
    	}
    	g.drawString("Кол-во минимальных предполагаемых ходов до конечно расстояния", X + (rightBound - leftBound) * 25, Y + 50);
    	g.drawString("↑", X - 40, Y);
    	g.drawString("Время, мс", X - 40, Y + 20);
    	for (int i = 0; i < times.length; i++) {
        	paintOneCycle(g, i);
        }
    	x = X;
    }
    private void paintOneCycle(Graphics g, int testNum) {
    	for (int i = 0; i < times[testNum].length; i++) {
    		g.setColor(colors[i]);
    		g.fillRect(x + i * 17, y - (int)times[testNum][i] / scale * 15, 15, (int)times[testNum][i] / scale * 15);
    	}
    	x += 100;
    }
//    public void myPaint(Graphics g, int x, int y) {
//    	g.fillRect(x, y, 100, 100);
//    }
    private void drawAnotation(Graphics g) {
    	int x = 50;
    	int y = 500;
    	
    	for (int i = 0; i < names.length; i++) {
    		g.drawString(names[i], x + 20, y + 8 + i * 30);
    	}
    	
    	for (int i = 0; i < colors.length; i++) {
    		g.setColor(colors[i]);
        	g.fillRect(x, y + i * 30, 10, 10);
    	}
    }
}
