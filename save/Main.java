package com.company;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class Main extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;

	static boolean computer = true;
	static boolean computerVScomputer = computer;
	static boolean calculating = false;
//	static int timeLimit = 1800;
	static boolean game = false;
	static boolean uhrSek = false;

//	static String board[][] = new String[8][8]; // TODO multiverson
	static Board brd = new Board(8,8);

	static String color[] = { "Weiss", "Gelb", "Orange", "Rot", "Pink", "Magenta", "Cyan", "Blau", "Gruen", "Hellgrau", "Grau", "Dunkelgrau", "Schwarz" };
	static String log[] = new String[9999];
	static int moves[][] = new int[8][8];
	static int letzterZugX1, letzterZugX2, letzterZugY1, letzterZugY2;
	static final char[] charC = {'b', 'w'};
	static int width = 60, abstX = width, abstY = width;
	static int quaX;

	static int quaY = 100;
	static int mx1, my1, mx2, my2;
	static int minsek;
	static int recreateGame;
	static int countColor1 = 1000000, countColor2 = 1000011, countColor3 = 1000002, countColor4 = 1000006;
	static int choseStoneX, choseStoneY;
	static double sincl;
	static int player = 1;
	static boolean selection = false;
	static boolean quad = true;
	static boolean easterEgg[] = new boolean[10];
	static boolean moveP = false;
	static boolean lastMoveChess = true;
	static boolean pause = false;
	static boolean choseStone = false;
	static final String reihe = "ABCDEFGH";
	static final String ROW_K = "abcdefgh";
	/* P-0 R-1 L-2 B-3 Q-4 k-5 */
	static final String figuren = "PRKBQk";
	static String letzterZug = "";
	static String easterString = "";
	static Color[] col = { Color.white, Color.yellow, Color.orange, Color.red, Color.pink, Color.magenta, Color.cyan, Color.blue, Color.green, Color.lightGray, Color.gray, Color.DARK_GRAY, Color.black };
	static Graphics2D gBuffer;
	
	static CountDown countdown = new CountDown();
	static Timer timeP1 = new Timer(1800);
	static Timer timeP2 = new Timer(1800);
	
	static Logic logic = new Logic();

	static Image buffer;

	static boolean pressed = false;

	static boolean moveC = false;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Main());
	}

	@Override
	public void run() {
		JFrame f = new JFrame("Schach");
		java.net.URL dame = getClass().getResource("/images/dame.gif");
		f.setIconImage(new ImageIcon(dame).getImage());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(this);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.setResizable(true);
		f.setBackground(new Color(255, 255, 255));
		f.addKeyListener(keylistener);
		f.addMouseListener(new RepaintOnClick());
		f.setAlwaysOnTop(true);
		vorbereiten();
	}

	public Main() {
		this.setPreferredSize(new Dimension((int) (1400 * width / 100) + abstX, (int) (900 * width / 100) + abstY));
		setLayout(null);
	}

	public void paintComponent(Graphics g) {
//		int nbThreads =  Thread.getAllStackTraces().keySet().size();
//		System.out.println(nbThreads);
		super.paintComponent(g);
		gBuffer = (Graphics2D) g;
		gBuffer.clearRect(0, 0, this.getSize().width, this.getSize().height);
		gBuffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if ((double) this.getSize().width / this.getSize().height <= (double) 750 / 450)
			width = (this.getSize().width) / 14 - width / 14;
		else if ((double) this.getSize().width / this.getSize().height > (double) 750 / 450)
			width = (this.getSize().height) / 9 - width / 9;
		abstX = width;
		abstY = width;
//		System.out.println(calculating);
		if (game && !calculating) {
			game = !new Logic().finish2();
//			System.out.println(game);
			if (computer && getPlayer() == 1)
				new Random();
			else if (computerVScomputer && getPlayer() == 0)
				new Random();
		}
		new Menu();
		if (selection && game && easterEgg[4]) {
//			System.out.println(board[mx1][my1] + " " + board[mx2][my2]);
//			System.out.println(mx1 + " " + my1 + " " + mx2 + " " + my2);
			if (mx1 == mx2 && my1 == my2){
//				selection = false;
				moveP = false;
			} else if (mx2 >= 0 && my2 >= 0 && moveP) {
				new Move(new Point(mx2, my2), new Point(mx1, my1));
//				if (!computer) {
//					moveP = false;
//				} else if (!computerVScomputer) {
//					new Move(mx2, my2, mx1, my1);
//					moveP = false;
//				}
			}
			if (!isEmpty(brd.getFieldString(mx1,my1))) {
				mx2 = mx1;
				my2 = my1;
				moveP = true;
			} else {
				mx2 = 0;
				my2 = 0;
				moveP = false;
			}
		}
//		if (!easterEgg[4]) {
//			game = false;
//			gBuffer.setColor(Color.gray);
//			gBuffer.fillRect(width * 2, width * 2, 11 * width, 6 * width);
//			printSinus();
//			gBuffer.setColor(Color.black);
//			gBuffer.setFont(new Font("Arial", Font.BOLD, (int) (130 * width / 100)));
//			gBuffer.drawString("Herr Thomae hat", (int) (130 * width / 100) + abstX, (int) (300 * width / 100) + abstY);
//			gBuffer.drawString("gewonnen!", (int) (310 * width / 100) + abstX, (int) (550 * width / 100) + abstY);
//			gBuffer.setFont(new Font("Arial", Font.BOLD, (int) (80 * width / 100)));
//			gBuffer.drawString("Thomae > Carlsen", (int) (320 * width / 100) + abstX, (int) (670 * width / 100) + abstY);
//		}
		g.drawImage(buffer, 0, 0, this);
		repaint();
//		try {
//			Thread.sleep(10);                 //1000 milliseconds is one second.
//		} catch(InterruptedException ex) {
//			Thread.currentThread().interrupt();
//		}
	}

	public void switchStones(int x, int y) {
		brd.setString(x,y, figuren.charAt(mx1 - choseStoneX + 1) + "" + getColor(brd.getFieldString(x,y)));
//		player++;
	}

	public void printSinus() {
		for (double i = 0; i < 480; i++) {
			gBuffer.setStroke(new BasicStroke((int) (5)));
			int red = (int) (Math.sin(i / 10 + 0 + sincl * i / i) * 127 + 128);
			int green = (int) (Math.sin(i / 10 + 2 + sincl * i / i) * 127 + 128);
			int blue = (int) (Math.sin(i / 10 + 4 + sincl * i / i) * 127 + 128);
			gBuffer.setColor(new Color(red, green, blue));
			double mathsin = Math.sin(sincl / 5) * Math.sin(sincl / 5) * 100;
			double mathcos = Math.cos(sincl / 5) * Math.cos(sincl / 5) * 100;
			gBuffer.drawLine((int) (i + 100), (int) (Math.sin(i / mathsin) * mathsin + 250), (int) (i + 166), (int) (Math.sin(i / mathcos) * mathcos + 250));
			gBuffer.drawLine((int)(i), (int)(Math.tan(i/30)*30) + 100, (int)(i), (int)(Math.tan(i/30)*30) + 100);
		}
	}

	public void moveRe() {
		int x1 = ROW_K.indexOf(log[player - 2].charAt(1));
		int y1 = Integer.parseInt("" + log[player - 2].charAt(2));
		int x2 = ROW_K.indexOf(log[player - 2].charAt(5));
		int y2 = Integer.parseInt("" + log[player - 2].charAt(6));

		System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);

		char farbe = ' ';
		if (getPlayer() == 0)
			farbe = charC[0];
		else
			farbe = charC[1];
		brd.setString(x1,y1, log[player - 2].charAt(0) + "" + farbe);
		if (getPlayer() == 0)
			farbe = charC[1];
		else
			farbe = charC[0];
		brd.setString(x2,y2, log[player - 2].charAt(4) + "" + farbe);
		if (log[player - 2].charAt(4) == ' ')
			brd.setString(x2,y2, "  ");

		player--;

		// String boardTemp1 = board[x1][y1];
		// String boardTemp2 = board[x2][y2];
		//
		// board[x2][y2] = board[x1][y1];
		// //if(log[recreateGame].charAt(index))
		// board[x1][y1] = " ";
		//
		// moves[x2][y2] = moves[x1][y1] + 1;
		// moves[x1][y1] = 0;
	}

	public boolean checkForColor() {
		if (countColor1 % color.length != countColor2 % color.length && countColor1 % color.length != countColor3 % color.length && countColor1 % color.length != countColor4 % color.length)
			if (countColor2 % color.length != countColor3 % color.length && countColor2 % color.length != countColor4 % color.length)
				if (countColor3 % color.length != countColor4 % color.length)
					return false;
		return true;
	}

	public boolean checkForColor2(int n) {
		if (countColor1 % color.length != n && countColor2 % color.length != n && countColor1 % color.length != n && countColor1 % color.length != n)
			return false;
		return true;
	}

	public void vorbereiten() {
		game = true;
		calculating = false;
		mx1 = 0;
		my1 = 0;
		mx2 = 0;
		my2 = 0;
		player = 1;
		timeP1.reset();;
		timeP2.reset();;
		for (int i = 0; i < easterEgg.length; i++)
			easterEgg[i] = true;
		easterString = "";
		selection = false;
		choseStone = false;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				moves[i][j] = 0;
			}
			brd.setString(i,1, figuren.charAt(0) + "" + charC[0]);
			brd.setString(i,6, figuren.charAt(0) + "" + charC[1]);
		}

		letzterZugX1 = 0;
		letzterZugX2 = 0;
		letzterZugY1 = 0;
		letzterZugY2 = 0;

		for (int x = 0; x < 5; x++){
			for (int n = 0; n < 2 ; n++){
				brd.setString(x,7 * n, figuren.charAt(x + 1) + "" + charC[n]);
				if(x != 4) brd.setString(x + 4,7 * n, figuren.charAt(4 - x) + "" + charC[n]);
			}
		}
	}

	public void printLog() {
		int i = 0;
		while (log[i] != null && i <= log.length) {
			System.out.println(log[i]);
			i++;
		}
	}

	public void nullLog() {
		int i = 0;
		while (log[i] != null && i <= log.length) {
			log[i] = null;
			i++;
		}
	}

	public void reGame() {
		int x1 = ROW_K.indexOf(log[recreateGame].charAt(4));
		int x2 = ROW_K.indexOf(log[recreateGame].charAt(8));
		int y1 = Integer.parseInt("" + log[recreateGame].charAt(5));
		int y2 = Integer.parseInt("" + log[recreateGame].charAt(9));
		System.out.println(x1 + " " + x2 + " " + y1 + " " + y2);
		new Move(new Point(x1,y1), new Point(x2,y2));
		recreateGame++;
	}

	public boolean isEmpty(String str){
		return str.equals(setEmpty());
	}

	public String setEmpty(){
		return "  ";
	}

	public char getPiece(String str){
		return str.charAt(0);
	}

	public char getColor(String str){
		return str.charAt(1);
	}

	public int getPlayer(){
		return player % 2;
	}

	private class RepaintOnClick implements MouseListener {
		public void mousePressed(MouseEvent evt) {
//			if (mx1 > (int) (800 * width / 100) || my1 > (int) (800 * width / 100) || (int) mx1 < 0 || (int) my1 < 0) {
//				selection = false;
//			} else if (!choseStone) {
//				mx1 = (int) (mx1 / (100 * width / 100));
//				my1 = (int) (my1 / (100 * width / 100));
//				System.out.println("pressed");
//				pressed = true;
//			}
		}

		public void mouseClicked(MouseEvent evt) {
			mx1 = evt.getX() - abstX;
			my1 = evt.getY() - abstY - 25;
//			MouseX = evt.getX();
//			MouseY = evt.getY();

			gBuffer.fillRect(mx1,my1,100,100);
			if (mx1 > (int) (1105 * width / 100) && mx1 < (int) (1203 * width / 100) && my1 > (int) (740 * width / 100) && my1 < (int) (790 * width / 100))
				minsek = (minsek + 1) % 2;
			if(!game) {
				if (uhrSek) {
					if (my1 > (int) (700 * width / 100) && my1 < (int) (725 * width / 100)) {
						if (mx1 > (int) (1210 * width / 100) && mx1 < (int) (1245 * width / 100) && timeP1.getLimit() < 4999){
							timeP1.limitAd(1000);
							timeP2.limitAd(1000);
						}
						else if (mx1 > (int) (1250 * width / 100) && mx1 < (int) (1285 * width / 100) && timeP1.getLimit() < 5899){
							timeP1.limitAd(100);
							timeP2.limitAd(100);
						}
						else if (mx1 > (int) (1290 * width / 100) && mx1 < (int) (1325 * width / 100) && timeP1.getLimit() < 5989){
							timeP1.limitAd(10);
							timeP2.limitAd(10);
						}
						else if (mx1 > (int) (1330 * width / 100) && mx1 < (int) (1365 * width / 100) && timeP1.getLimit() < 5999){
							timeP1.limitAd(1);
							timeP2.limitAd(1);
						}
					} else if (my1 > (int) (805 * width / 100) && my1 < (int) (830 * width / 100)) {
						if (mx1 > (int) (1210 * width / 100) && mx1 < (int) (1245 * width / 100) && timeP1.getLimit() > 1000){
							timeP1.limitSub(1000);
							timeP1.limitSub(1000);
						}
						else if (mx1 > (int) (1250 * width / 100) && mx1 < (int) (1285 * width / 100) && timeP1.getLimit() > 100){
							timeP1.limitSub(100);
							timeP1.limitSub(100);
						}
						else if (mx1 > (int) (1290 * width / 100) && mx1 < (int) (1325 * width / 100) &&timeP1.getLimit() > 10){
							timeP1.limitSub(10);
							timeP1.limitSub(10);
						}
						else if (mx1 > (int) (1330 * width / 100) && mx1 < (int) (1365 * width / 100) && timeP1.getLimit() > 1){
							timeP1.limitSub(1);
							timeP1.limitSub(1);
						}
					}
				} else {
					if (my1 > (int) (700 * width / 100) && my1 < (int) (725 * width / 100)) {
						if (mx1 > (int) (1210 * width / 100) && mx1 < (int) (1245 * width / 100) && timeP1.getLimit() < 4999){
							timeP1.limitAd(600);
							timeP1.limitAd(600);
						}
						else if (mx1 > (int) (1250 * width / 100) && mx1 < (int) (1285 * width / 100) && timeP1.getLimit() < 5899){
							timeP1.limitAd(100);
							timeP1.limitAd(100);
						}
						else if (mx1 > (int) (1290 * width / 100) && mx1 < (int) (1325 * width / 100) && timeP1.getLimit() < 5989){
							timeP1.limitAd(10);
							timeP1.limitAd(10);
						}
						else if (mx1 > (int) (1330 * width / 100) && mx1 < (int) (1365 * width / 100) && timeP1.getLimit() < 5999){
							timeP1.limitAd(1);
							timeP1.limitAd(1);
						}
					} else if (my1 > (int) (805 * width / 100) && my1 < (int) (830 * width / 100)) {
						if (mx1 > (int) (1210 * width / 100) && mx1 < (int) (1245 * width / 100) && timeP1.getLimit() > 1000){
							timeP1.limitSub(600);
							timeP1.limitSub(600);
						}
						else if (mx1 > (int) (1250 * width / 100) && mx1 < (int) (1285 * width / 100) && timeP1.getLimit() > 100){
							timeP1.limitSub(100);
							timeP1.limitSub(100);
						}
						else if (mx1 > (int) (1290 * width / 100) && mx1 < (int) (1325 * width / 100) && timeP1.getLimit() > 10){
							timeP1.limitSub(10);
							timeP1.limitSub(10);
						}
						else if (mx1 > (int) (1330 * width / 100) && mx1 < (int) (1365 * width / 100) && timeP1.getLimit() > 1){
							timeP1.limitSub(1);
							timeP1.limitSub(1);
						}
					}
				}
			}

			if (mx1 > (int) (1140 * width / 100) && mx1 < (int) (1170 * width / 100)) {
				if (my1 > (int) (260 * width / 100) && my1 < (int) (320 * width / 100)) {
					countColor1--;
					while (checkForColor()) {
						countColor1--;
					}
				} else if (my1 > (int) (330 * width / 100) && my1 < (int) (390 * width / 100)) {
					countColor2--;
					while (checkForColor()) {
						countColor2--;
					}
				} else if (my1 > (int) (480 * width / 100) && my1 < (int) (540 * width / 100)) {
					countColor3--;
					while (checkForColor()) {
						countColor3--;
					}
				} else if (my1 > (int) (550 * width / 100) && my1 < (int) (610 * width / 100)) {
					countColor4--;
					while (checkForColor()) {
						countColor4--;
					}
				}
			} else if (mx1 > (int) (1345 * width / 100) && mx1 < (int) (1375 * width / 100)) {
				if (my1 > (int) (260 * width / 100) && my1 < (int) (320 * width / 100)) {
					countColor1++;
					while (checkForColor()) {
						countColor1++;
					}
					;
				} else if (my1 > (int) (330 * width / 100) && my1 < (int) (390 * width / 100)) {
					countColor2++;
					while (checkForColor()) {
						countColor2++;
					}
					;
				} else if (my1 > (int) (480 * width / 100) && my1 < (int) (540 * width / 100)) {
					countColor3++;
					while (checkForColor()) {
						countColor3++;
					}
					;
				} else if (my1 > (int) (550 * width / 100) && my1 < (int) (610 * width / 100)) {
					countColor4++;
					while (checkForColor()) {
						countColor4++;
					}
				}
			}

			if (my1 > (int) (150 * width / 100) && my1 < (int) (175 * width / 100)) {
				if (mx1 > (int) (1140 * width / 100) && mx1 < (int) (1165 * width / 100)) {
					computer = false;
					computerVScomputer = false;
					game = false;
				} else if (mx1 > (int) (1210 * width / 100) && mx1 < (int) (1235 * width / 100)) {
					computer = true;
					computerVScomputer = false;
					game = false;
				} else if (mx1 > (int) (1280 * width / 100) && mx1 < (int) (1305 * width / 100)) {
					computer = false;
					computerVScomputer = true;
					game = false;
				} else if (mx1 > (int) (1350 * width / 100) && mx1 < (int) (1375 * width / 100)) {
					computer = true;
					computerVScomputer = true;
					game = false;
				}
			}

			if (mx1 > (int) (1140 * width / 100) && mx1 < (int) (1262 * width / 100) && my1 > (int) (-40 * width / 100) && my1 < (int) (20 * width / 100) && game) {
				pause = true;
				game = false;
			} else if (mx1 > (int) (1265 * width / 100) && mx1 < (int) (1360 * width / 100) && my1 > (int) (-40 * width / 100) && my1 < (int) (20 * width / 100) && game)
				vorbereiten();
			else if (mx1 > (int) (1140 * width / 100) && mx1 < (int) (1360 * width / 100) && my1 > (int) (-40 * width / 100) && my1 < (int) (20 * width / 100) && !game) {
				pause = false;
				game = true;
			}

			if (mx1 > (int) (800 * width / 100) || my1 > (int) (800 * width / 100) || (int) mx1 < 0 || (int) my1 < 0) {
				selection = false;
			} else if (!choseStone) {
				mx1 = (int) (mx1 / (100 * width / 100));
				my1 = (int) (my1 / (100 * width / 100));
				selection = true;
			}
			if (choseStone) {
				mx1 = (int) (mx1 / (100 * width / 100));
				if (my1 < 0)
					my1 = -1;
				else{
					my1 = (int) (my1 / (100 * width / 100));
				}

				if (mx1 >= choseStoneX && mx1 <= choseStoneX + 3) {
					if(my1 == -1){
						switchStones(choseStoneX, choseStoneY);
						choseStone = false;
					} else if(my1 == 6){
						switchStones(choseStoneX, choseStoneY);
						choseStone = false;
					}
				}
			}
		}

		public void mouseReleased(MouseEvent evt) {
//			if (mx1 > (int) (800 * width / 100) || my1 > (int) (800 * width / 100) || (int) mx1 < 0 || (int) my1 < 0) {
//				selection = false;
//			} else if (!choseStone) {
////				mx1 = (int) (mx1 / (100 * width / 100));
////				my1 = (int) (my1 / (100 * width / 100));
//				pressed = false;
//				System.out.println("released");
//			}
		}

		public void mouseEntered(MouseEvent evt) {
		}

		public void mouseExited(MouseEvent evt) {
		}

		public void mouseMoved(MouseEvent evt){

		}
	}

	public KeyListener keylistener = new KeyListener() {
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			easterString += e.getKeyChar();
			if (key == KeyEvent.VK_DOWN) {
				width -= 5;
				abstX = width;
				abstY = width;
			}
			if (key == KeyEvent.VK_UP) {
				width += 5;
				abstX = width;
				abstY = width;
			}
			if (key == KeyEvent.VK_ESCAPE) {
				vorbereiten();
			}
			if (key == KeyEvent.VK_LEFT) {
				moveRe();
			} // timeP1+=10; timeP2+=10;}
			if (key == KeyEvent.VK_RIGHT) {
				timeP1.limitSub(10);
				timeP2.limitSub(10);
			}
			if (key == KeyEvent.VK_SPACE) {
				easterString = "";
			}
			if (key == KeyEvent.VK_C) {
//				smart.checkForBestMove();
			}
			if (key == KeyEvent.VK_V) {
//				smart.check();
			}
			if (key == KeyEvent.VK_B) {
//				smart.print();
			}
			if (key == KeyEvent.VK_G) {
				printLog();
			}
			if (key == KeyEvent.VK_H) {
				nullLog();
			}
			if (key == KeyEvent.VK_J) {
				reGame();
			}
			if (key == KeyEvent.VK_P) {
				// try {
				// Tone.sound(1000,100,0.5);
				// } catch (LineUnavailableException e1) {
				// e1.printStackTrace();
				// }
			}
			if (easterString.equalsIgnoreCase("bvb1909")) {
				easterEgg[0] = false;
				System.out.println("BVB09");
				easterString = "";
			} // easteregg
			if (easterString.equalsIgnoreCase("primzahlen")) {
				easterEgg[1] = false;
				System.out.println("prim");
				easterString = "";
			}
			if (easterString.equalsIgnoreCase("fibonacci")) {
				easterEgg[2] = false;
				System.out.println("0 1 1 2 3 5 8");
				easterString = "";
			}
			if (easterString.equalsIgnoreCase("pyhtagoras")) {
				easterEgg[3] = false;
				System.out.println("pyht");
				easterString = "";
			}
			if (easterString.equalsIgnoreCase("thomae")) {
				easterEgg[4] = false;
				System.out.println("Herr Thomae");
				easterString = "";
			}

			if (easterString.equalsIgnoreCase("3,14159")) {
				System.out.println("pi");
				easterString = "";
			}
			if (easterString.equalsIgnoreCase("quadrat")) {
				System.out.println("x^2");
				easterString = "";
			}
			System.out.println(easterString);
		}

		public void keyReleased(KeyEvent e) {
		}

		public void keyTyped(KeyEvent e) {
		}
	};
}