package game;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.border.BevelBorder;

import pac.Board;
import pac.Generator;
import pac.Solver2;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;

public class TesterPerf {
	private JFrame frame;
	private JTextField dimField;
	private JTextField leftBoundField;
	private JTextField rightBoundtextField;
	private double[][] dataSet;
	private JRadioButton[] heuristicsCheck = new JRadioButton[4];
 
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TesterPerf window = new TesterPerf();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
 
    private double[][] getDataTest(int dim, int left, int right, boolean checks[]) {
    	Generator gen = new Generator();
    	Solver2 solver;
    	Board temp;
    	double[][] data = new double[right - left + 1][4];
    	double stTime, endTime, elapsedTime;
    	for (int i = 0; i < 4; i++) {
    		for (int j = left, number = 0; j <= right; j++, number++) {
    			if (!checks[i]) {
    				data[number][i] = 0;
    				continue;
    			}
    			temp = gen.getGen(dim, j, i);
        		stTime = System.currentTimeMillis();
        		solver = new Solver2(temp);
        		endTime = System.currentTimeMillis();
        		elapsedTime = endTime - stTime;
        		data[number][i] = elapsedTime;
        	}
    	}
		return data;
    }
    /**
     * Create the application.
     */
    public TesterPerf() {
        initialize();
    }
 
    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1168, 668);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        Gist panel = new Gist();
        panel.setBackground(Color.WHITE);
        panel.setBounds(10, 11, 1168, 466);
        panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        
        dimField = new JTextField();
        dimField.setText("6");
        dimField.setBounds(207, 485, 86, 20);
        frame.getContentPane().add(dimField);
        dimField.setColumns(10);
        
        leftBoundField = new JTextField();
        leftBoundField.setText("10");
        leftBoundField.setBounds(207, 547, 86, 20);
        frame.getContentPane().add(leftBoundField);
        leftBoundField.setColumns(10);
        
        rightBoundtextField = new JTextField();
        rightBoundtextField.setText("15");
        rightBoundtextField.setBounds(207, 575, 86, 20);
        frame.getContentPane().add(rightBoundtextField);
        rightBoundtextField.setColumns(10);
        
        heuristicsCheck[0] = new JRadioButton("");
        heuristicsCheck[0].setBounds(861, 484, 109, 23);
        frame.getContentPane().add(heuristicsCheck[0]);
        
        heuristicsCheck[1] = new JRadioButton("");
        heuristicsCheck[1].setBounds(861, 516, 109, 23);
        frame.getContentPane().add(heuristicsCheck[1]);
        
        heuristicsCheck[2] = new JRadioButton("");
        heuristicsCheck[2].setBounds(861, 547, 109, 23);
        frame.getContentPane().add(heuristicsCheck[2]);
        
        heuristicsCheck[3] = new JRadioButton("");
        heuristicsCheck[3].setBounds(861, 583, 109, 23);
        frame.getContentPane().add(heuristicsCheck[3]);
        
        for (int i = 0; i < heuristicsCheck.length; i++) {
        	heuristicsCheck[i].setSelected(true);
        } 
        
        JButton btnNewButton = new JButton("Тестировать");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int dim = Integer.parseInt(dimField.getText());
        		int l = Integer.parseInt(leftBoundField.getText());
        		int r = Integer.parseInt(rightBoundtextField.getText());
        		boolean[] checks = new boolean[heuristicsCheck.length];
        		for (int i = 0; i < heuristicsCheck.length; i++) {
        			if (heuristicsCheck[i].isSelected() == true) {
        				checks[i] = true;
        			}
        			else {
        				checks[i] = false;
        			}
        		}
        		dataSet = getDataTest(dim, l, r, checks);
        		panel.times = dataSet;
        		panel.leftBound = l;
        		panel.rightBound = r;
        		panel.repaint();
        	}
        });
        btnNewButton.setBounds(330, 484, 124, 23);
        frame.getContentPane().add(btnNewButton);
        
        JLabel label = new JLabel("Размерность");
        label.setBounds(77, 488, 80, 14);
        frame.getContentPane().add(label);
        
        JLabel label_1 = new JLabel("Количество ходов");
        label_1.setBounds(77, 519, 120, 14);
        frame.getContentPane().add(label_1);
        
        JLabel label_2 = new JLabel("Левая граница");
        label_2.setBounds(111, 550, 86, 14);
        frame.getContentPane().add(label_2);
        
        JLabel label_3 = new JLabel("Правая граница");
        label_3.setBounds(111, 578, 86, 14);
        frame.getContentPane().add(label_3);
        
        JTextPane textPane_1 = new JTextPane();
        textPane_1.setEnabled(false);
        textPane_1.setEditable(false);
        textPane_1.setBackground(Color.BLUE);
        textPane_1.setBounds(476, 484, 21, 20);
        frame.getContentPane().add(textPane_1);
        
        JLabel lblNewLabel = new JLabel("Функция хемминга");
        lblNewLabel.setBounds(520, 488, 140, 14);
        frame.getContentPane().add(lblNewLabel);
        
        JTextPane textPane_2 = new JTextPane();
        textPane_2.setEnabled(false);
        textPane_2.setEditable(false);
        textPane_2.setBackground(Color.CYAN);
        textPane_2.setBounds(476, 519, 21, 20);
        frame.getContentPane().add(textPane_2);
        
        JLabel label_4 = new JLabel("Расстояние евклида");
        label_4.setBounds(520, 519, 157, 14);
        frame.getContentPane().add(label_4);
        
        JTextPane textPane = new JTextPane();
        textPane.setEnabled(false);
        textPane.setEditable(false);
        textPane.setBackground(Color.BLACK);
        textPane.setBounds(476, 550, 21, 20);
        frame.getContentPane().add(textPane);
        
        JLabel label_5 = new JLabel("Манхеттенское расстояние");
        label_5.setBounds(520, 550, 170, 14);
        frame.getContentPane().add(label_5);
        
        JTextPane textPane_3 = new JTextPane();
        textPane_3.setEnabled(false);
        textPane_3.setEditable(false);
        textPane_3.setBackground(Color.GREEN);
        textPane_3.setBounds(476, 586, 21, 20);
        frame.getContentPane().add(textPane_3);
        
        JLabel label_6 = new JLabel("Манхеттенское расстояние с учётом линейных конфликтов");
        label_6.setBounds(520, 586, 350, 14);
        frame.getContentPane().add(label_6);
    }
}
