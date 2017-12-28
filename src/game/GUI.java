package game;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import pac.Board;
import pac.Generator;
import pac.Solver2;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GUI {

	private JFrame frame;
	private JTextField textFieldCountN;
	private JTextField textFieldCountMoves;
	private Generator gen;
	// private JPanel panel;
	private Board paintedBoard;
	public Timer timer;
	public JPanel panel;
	private int showedMoves;
	private Board nextPerm;
	private Iterator<Board> nextP;
	
	private static final File pathToSample = new File("samples");
	private File[] files;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		getFileNames();
		int[][] a = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				a[i][j] = i * 4 + j + 1;
			}
		}
		showedMoves = 0;
		paintedBoard = new Board(a, 3, null);
		nextPerm = null;
		nextP = null;
		initialize();
	}
	
	private void getFileNames() {
		files = pathToSample.listFiles();
	}
	private Board getDataFromFile(int fileNum, int hType) {
		try {
			byte[] bytes = Files.readAllBytes(Paths.get(files[fileNum].getPath()));
			String content = new String(bytes, Charset.defaultCharset());
			StringTokenizer strTok = new StringTokenizer(content, " \n\r\t");
			int n = Integer.parseInt(strTok.nextToken());
			int[][] arr = new int[n][n];
			for (int i = 0, z = 0; i < n; i++) {
				for (int j = 0; j < n; j++, z++) {
					arr[i][j] = Integer.parseInt(strTok.nextToken());
				}
			}
			return new Board(arr, hType, null);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	void resetGame(JLabel label) {
		Assembly.board = paintedBoard;
		Assembly.n = paintedBoard.dimension();
		panel.requestFocus();
		showedMoves = 0;
		label.setText(Integer.toString(showedMoves));
		panel.repaint();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 245, 238));
		frame.setBounds(100, 100, 1200, 425);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JLabel shCountMoves = new JLabel(Integer.toString(showedMoves));
		shCountMoves.setBounds(249, 44, 46, 14);
		frame.getContentPane().add(shCountMoves);
		
		panel = new Assembly();
		panel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					shCountMoves.setText(Integer.toString(++showedMoves));
					Assembly.moveRight();
					panel.repaint();
					break;
				case KeyEvent.VK_LEFT:
					shCountMoves.setText(Integer.toString(++showedMoves));
					Assembly.moveLeft();
					panel.repaint();
					break;
				case KeyEvent.VK_UP:
					shCountMoves.setText(Integer.toString(++showedMoves));
					Assembly.moveUp();
					panel.repaint();
					break;
				case KeyEvent.VK_DOWN:
					shCountMoves.setText(Integer.toString(++showedMoves));
					Assembly.moveDown();
					panel.repaint();
					break;
				case KeyEvent.VK_ENTER:
					panel.repaint();
					break;
				default:
					break;
				}
			}
		});
		panel.setBorder(null);
		panel.setBounds(10, 69, 714, 304);
		frame.getContentPane().add(panel);

		textFieldCountN = new JTextField();
		textFieldCountN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
				case KeyEvent.VK_ENTER:
					panel.requestFocus();
					break;
				}
			}
		});
		textFieldCountN.setText("4");
		textFieldCountN.setBounds(32, 12, 56, 20);
		frame.getContentPane().add(textFieldCountN);
		textFieldCountN.setColumns(10);

		JLabel labelCountN = new JLabel("N:");
		labelCountN.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		labelCountN.setBounds(10, 14, 46, 14);
		frame.getContentPane().add(labelCountN);

		JLabel labelCountMoves = new JLabel("Количество ходов: ");
		labelCountMoves.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		labelCountMoves.setBounds(111, 14, 128, 14);
		frame.getContentPane().add(labelCountMoves);

		textFieldCountMoves = new JTextField();
		textFieldCountMoves.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
				case KeyEvent.VK_ENTER:
					panel.requestFocus();
					break;
				}
			}
		});
		textFieldCountMoves.setText("6");
		textFieldCountMoves.setBounds(249, 12, 56, 20);
		frame.getContentPane().add(textFieldCountMoves);
		textFieldCountMoves.setColumns(10);

		JComboBox comboBoxHeuristic = new JComboBox();
		comboBoxHeuristic.setForeground(new Color(0, 0, 0));
		comboBoxHeuristic.setBackground(new Color(240, 255, 240));
		comboBoxHeuristic.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBoxHeuristic.setBounds(538, 11, 186, 20);
		frame.getContentPane().add(comboBoxHeuristic);
		comboBoxHeuristic.addItem("Хэмминг");
		comboBoxHeuristic.addItem("Эвклид");
		comboBoxHeuristic.addItem("Манхеттен без лин.конф.");
		comboBoxHeuristic.addItem("Манхеттен с лин.конф.");

		JLabel labelHeuristic = new JLabel("Эвристика:");
		labelHeuristic.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		labelHeuristic.setBounds(453, 14, 75, 14);
		// labelHeuristic.setEditable(true);

		frame.getContentPane().add(labelHeuristic);

		JButton buttonAssembly = new JButton("Собрать");
		buttonAssembly.addActionListener(new ActionListener() {
			// timer = new Timer();
			
			public void actionPerformed(ActionEvent e) {
				new Thread() {
					@Override
					public void run() {
						Solver2 solve = new Solver2(paintedBoard);
						shCountMoves.setText(Integer.toString(showedMoves = 0));
						
						for (Board i: solve.solution()) {
							Assembly.board = i;
							shCountMoves.setText(Integer.toString(++showedMoves));
							panel.repaint();
							try {
								Thread.sleep(200);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}.start();
			}
			
		});
		buttonAssembly.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		buttonAssembly.setBounds(596, 42, 128, 23);
		frame.getContentPane().add(buttonAssembly);

		JButton buttonGenerate = new JButton("Сгенерировать");
		buttonGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cN = 2;
				int cM = 2;
				if (Integer.parseInt(textFieldCountN.getText()) >= 2
						&& Integer.parseInt(textFieldCountMoves.getText()) >= 2) {
					cN = Integer.parseInt(textFieldCountN.getText());
					cM = Integer.parseInt(textFieldCountMoves.getText());
				}

				gen = new Generator();
				Board genBoard = gen.getGen(cN, cM, comboBoxHeuristic.getSelectedIndex());
				paintedBoard = genBoard;
				Assembly.board = paintedBoard;
				Assembly.n = cN;
				panel.requestFocus();
				showedMoves = 0;
				shCountMoves.setText(Integer.toString(showedMoves));
				panel.repaint();
			}
		});
		buttonGenerate.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		buttonGenerate.setBounds(453, 42, 128, 23);
		frame.getContentPane().add(buttonGenerate);
		
		JLabel label = new JLabel("Ходов сделано:");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		label.setBounds(111, 44, 128, 14);
		frame.getContentPane().add(label);
		
		JButton button = new JButton("Сл. позиция");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nextP == null) {
					Solver2 solve = new Solver2(paintedBoard);
					nextP = solve.solution().iterator();
				}
				if (nextP.hasNext()) {
					Assembly.board = nextP.next();
					shCountMoves.setText(Integer.toString(++showedMoves));
					panel.repaint();
				}
				else {
					Solver2 solve = new Solver2(paintedBoard);
					showedMoves = 0;
					shCountMoves.setText("0");
					nextP = solve.solution().iterator();
				}
			}
		});
		button.setBounds(727, 69, 112, 23);
		frame.getContentPane().add(button);
		
		JComboBox filesCombo = new JComboBox();
		filesCombo.setBounds(734, 12, 186, 20);
		frame.getContentPane().add(filesCombo);
		for (int i = 0; i < files.length; i++) {
			filesCombo.addItem(files[i].getName().substring(0, files[i].getName().length() - 4));
		}
		
		JButton addFromFile = new JButton("Добавить из файла");
		addFromFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paintedBoard = getDataFromFile(filesCombo.getSelectedIndex(), comboBoxHeuristic.getSelectedIndex());
				resetGame(shCountMoves);
			}
		});
		addFromFile.setBounds(849, 69, 160, 23);
		frame.getContentPane().add(addFromFile);
	}
}