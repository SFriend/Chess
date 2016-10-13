package com.company;

import javafx.print.PageOrientation;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class Main extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;

	static boolean computer = false;
	static boolean computerVScomputer = computer;
	static boolean calculating = false;
//	static int timeLimit = 1800;
	static boolean game = false;
	static boolean uhrSek = false;

//	static String board[][] = new String[8][8]; // TODO multiverson
	static Board brd = new Board(8,8);
	static Button[][] btnsTime = new Button[2][4];
	static Button btnClock;

	static String color[] = { "Weiss", "Gelb", "Orange", "Rot", "Pink", "Magenta", "Cyan", "Blau", "Gruen", "Hellgrau", "Grau", "Dunkelgrau", "Schwarz" };
	static String log[] = new String[9999];
	static Mouse lastMove;
	static Mouse m = new Mouse();
	static int letzterZugX1, letzterZugX2, letzterZugY1, letzterZugY2;
	static int width = 100, abstX = width, abstY = width;

	static int quaX;
	static int quaY = 100;
	static int minsek;
	static int recreateGame;
	static int[] countColor = new int[4]; //= 1000000, countColor2 = 1000011, countColor3 = 1000002, countColor4 = 1000006;
	static int choseStoneX, choseStoneY;
	static double sincl;
	static int player = 1;
	static boolean selection = false;
	static boolean choseStone = false;
	static boolean quad = true;
	static boolean easterEgg[] = new boolean[10];
	static boolean moveP = false;
	static boolean pause = false;
	static final String reihe = "ABCDEFGH";
	/* P-0 R-1 L-2 B-3 Q-4 k-5 */
//	static final String figuren = "PRKBQk";
	static String letzterZug = "";
	static String easterString = "";
	static Color[] col = { Color.white, Color.yellow, Color.orange, Color.red, Color.pink, Color.magenta, Color.cyan, Color.blue, Color.green, Color.lightGray, Color.gray, Color.DARK_GRAY, Color.black };
	static Graphics2D gBuffer;
	
	static CountDown countdown = new CountDown();
	static Timer timeP1 = new Timer(1800);
	static Timer timeP2 = new Timer(1800);
	
	static Image buffer;

	static boolean pressed = false;

	static boolean moveC = false;

	@Override
	public void run() {
		JFrame f = new JFrame("Chess");
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

	public int scale(int n){
		return (n * width) / 100;
	}

	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
		gBuffer = (Graphics2D) g;
		gBuffer.clearRect(0, 0, this.getSize().width, this.getSize().height);

		gBuffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if ((double) this.getSize().width / this.getSize().height <= (double) 750 / 450)
			width = (this.getSize().width) / 14 - width / 14;
		else if ((double) this.getSize().width / this.getSize().height > (double) 750 / 450)
			width = (this.getSize().height) / 9 - width / 9;
		abstX = width;
		abstY = width;
		if (game && !calculating) {
			if (computer && player == 1)
				new Random(brd);
			else if (computerVScomputer && player == 0)
				new Random(brd);
		}
		colorChange(0,0);
		new Menu();
		if(selection && choseStone && game){
//			selection =
 			new Move(brd, m.p1, m.p2);
			choseStone = false;
		}
//		if (selection && game && easterEgg[4]) {
////			System.out.println("Test");
//			if (m.getP1().getX() == m.getP2().getX() && m.getP1().getY() == m.getP2().getY()){
////				selection = false;
//				moveP = false;
//			} else if (m.getP2().getX() >= 0 && m.getP2().getY() >= 0 && moveP) {
//				selection = !new Move(brd, m.getP1(), m.getP2()).isMoved();
////				if (!computer) {
////					moveP = false;
////				} else if (!computerVScomputer) {
////					new Move(mx2, my2, mx1, my1);
////					moveP = false;
////				}
//			}
//			if (!brd.getField(m.getP1()).isEmpty() && !moveP) {
//				m.setP2((int) m.getP1().getX(), (int) m.getP1().getY());
//				moveP = true;
//			} else {
//				m.setP1(0,0);
//				moveP = false;
//			}
//		}
		g.drawImage(buffer, 0, 0, this);
		repaint();
	}

	public void switchStones(int x, int y) {
		brd.setField(new Point(x,y), new Field(Stones.figuren.charAt((int) m.getP2().getX() - choseStoneX + 1) + "" + brd.getField(new Point(x,y)).getColor()));
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

	public void stuffNeedToDo(){
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

//		try {
//			Thread.sleep(10);                 //1000 milliseconds is one second.
//		} catch(InterruptedException ex) {
//			Thread.currentThread().interrupt();
//		}
	}

	public boolean checkForSameColor() {
		for (int i = 0; i < countColor.length; i++){
			for (int j = 0; j < countColor.length; j++){
				if(i == j) continue;
				if(countColor[i] == countColor[j]) return true;
			}
		}
		return false;
	}

	public boolean checkForSameColorSelection(int n) {
		for (int i = 0; i < countColor.length; i++)
			if(countColor[0] == n) return true;
		return false;
	}

	public void vorbereiten() {
		game = true;
		calculating = false;
		m = new Mouse();
		lastMove = new Mouse();
		player = 1;
		timeP1.reset();
		timeP2.reset();
		for (int i = 0; i < easterEgg.length; i++)
			easterEgg[i] = true;
		easterString = "";
		selection = false;
		choseStone = false;
		brd.reset();

		countColor[0] = 0;
		countColor[1] = 11;
		countColor[2] = 2;
		countColor[3] = 6;

		int l = 1310; int o = (800); int u = (905);
		int w = 35; int h = (25);
		int ab = (40);
		for (int i = 0; i < 4; i++){
			btnsTime[0][3-i] = new Button(l+ab*i, o, w, h, 3);
			btnsTime[1][3-i] = new Button(l+ab*i, u, w, h, 3);
		}
		btnClock = new Button((1105*width/100), (740*width/100), scale(98), scale(50), 0);
	}

	public void colorDecese(int col){
		do { col--; } while (checkForSameColor());
	}

	public void colorIncrese(int col){
		do { col++; } while (checkForSameColor());
	}

	public void colorChange(int i, int j){
		if(i == 0) colorDecese(countColor[j]);
		else colorDecese(countColor[j]);
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
		int x1 = Stones.ROW_K.indexOf(log[recreateGame].charAt(4));
		int x2 = Stones.ROW_K.indexOf(log[recreateGame].charAt(8));
		int y1 = Integer.parseInt("" + log[recreateGame].charAt(5));
		int y2 = Integer.parseInt("" + log[recreateGame].charAt(9));
		new Move(brd, new Point(x1,y1), new Point(x2,y2));
		recreateGame++;
	}

	private class RepaintOnClick implements MouseListener {
		public int scale(int n){
			return (n * width) / 100;
		}
		public boolean isBetween(int n, int d1, int d2){
			return n >= d1 && n < d2;
		}
		public void timerAd(double n){
			Timer.limit += n;
		}
		public void mousePressed(MouseEvent evt) {
//			if (mx1 > (int) (800 * width / 100) || my1 > scale(int) (800 * width / 100) || (int) mx1 < 0 || (int) my1 < 0) {
//				selection = false;
//			} else if (!choseStone) {
//				mx1 = (int) (mx1 / (100 * width / 100));
//				my1 = (int) (my1 / (100 * width / 100));
//				pressed = true;
//			}
		}

		public void mouseClicked(MouseEvent evt) {
			m.getP2().setLocation(evt.getX() - abstX, evt.getY() - abstY - 25);
			int mx1 = m.getX2();
			int my1 = m.getY2();
			System.out.println(mx1 + " " + width*8);
			if (isBetween(mx1, 0, width * 8)) { // board
				if(isBetween(my1, 0, width * 8)) {
					if (!selection && !choseStone) {
						m.p1.setLocation(mx1/width, my1/100);
						selection = true;
					} else if (selection){
						if(evt.getX()/width == m.p1.getX() && evt.getY()/width == m.p1.getY()) selection = false;
						else {
							m.p2.setLocation(mx1/width, my1/width);
							choseStone = true;
						}
					}
//					if (!choseStone) {
//						m.setP2(mx1 / width, my1 / width);
//					} else {
//						mx1 = mx1 / width;
//						m.getP2().setLocation(m.getY2(), (my1 < 0) ? (-1) :  (my1 / width));
//						if (isBetween(mx1, choseStoneX, choseStoneX + 3)) {
//							if (my1 == -1){
//								switchStones(choseStoneX, choseStoneY);
//								choseStone = false;
//							} else if (my1 == 6){
//								switchStones(choseStoneX, choseStoneY);
//								choseStone = false;
//							}
//						}
//					}
				}
			}
//			selection = isBetween(mx1, 0, width * 8) && isBetween(my1, 0, width * 8);
			if (mx1 >= width * 11) { // settings
				if (btnClock.isPressed(evt.getPoint()))
				if (isBetween(my1 , 150, 175)) { // mode
					if (isBetween(mx1, 1140, 1165)) {
						computer = false;
						computerVScomputer = false;
						game = false;
					} else if (isBetween(mx1, 1210, 1235)) {
						computer = true;
						computerVScomputer = false;
						game = false;
					} else if (isBetween(mx1,1280, 1305)) {
						computer = false;
						computerVScomputer = true;
						game = false;
					} else if (isBetween(mx1, 1350, 1375)) {
						computer = true;
						computerVScomputer = true;
						game = false;
					}
				}

				for (int i = 0; i < 2; i++) { // time
					for (int j = 0; j < 4; j++) {
						if(btnsTime[i][j].isPressed(evt.getPoint())){
							colorChange(i,j);
						}
					}
				}

				if (isBetween(mx1, 1105, 1203)){
					if (isBetween(my1, 740, 790)) { // minsek
						minsek = (minsek + 1) % 2;
					}
				} else if(!game) {
					for (int i = 0; i < 2; i++) { // time
						for (int j = 0; j < 4; j++) {
							if(btnsTime[i][j].isPressed(evt.getPoint())) {
								if(uhrSek){
									if (i == 0){
										if( timeP1.getLimit() < 5999) timerAd(Math.pow(10,j));
									} else if (timeP1.getLimit() > Math.pow(10,j)) timerAd(-Math.pow(10,j));
								} else {
									if(Math.pow(10,j) > 60) {
										if (i == 0) {
											if (timeP1.getLimit() < 5999) timerAd(6 * Math.pow(10, j - 1));
										} else if (timeP1.getLimit() > 6 * Math.pow(10,j-1)) timerAd(-6 * Math.pow(10,j-1));
									} else {
										if (i == 0) {
											if (timeP1.getLimit() < 5999) timerAd(Math.pow(10, j));
										} else if (timeP1.getLimit() > Math.pow(10,j)) timerAd(-Math.pow(10,j));
									}
								}
							}
						}
					}
				}
			}
		}

		public void mouseReleased(MouseEvent evt) {
//			if (mx1 > scale(800) || my1 > scale(800) || mx1 < 0 || my1 < 0) {
//				selection = false;
//			} else if (!choseStone) {
////				mx1 = (mx1 / (100));
////				my1 = (my1 / (100));
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

			switch (key){
				case KeyEvent.VK_DOWN : {
					width -= 5;
					abstX = width;
					abstY = width;
					return;
				}
				case KeyEvent.VK_UP : {
					width += 5;
					abstX = width;
					abstY = width;
					return;
				}
				case KeyEvent.VK_RIGHT :  {
					Timer.limit -= 10;
					return;
				}
				case KeyEvent.VK_ESCAPE : vorbereiten(); return;
				case KeyEvent.VK_SPACE : easterString = ""; return;
				case KeyEvent.VK_G : printLog(); return;
				case KeyEvent.VK_H : nullLog(); return;
				case KeyEvent.VK_J : reGame(); return;
//				case KeyEvent.VK_LEFT : moveRe(); return;
//				case KeyEvent.VK_C  : smart.checkForBestMove(); return;
//				case KeyEvent.VK_V : smart.check(); return;
//				case KeyEvent.VK_B : smart.print(); return;
//				case KeyEvent.VK_P :
//					 try {
//						 Tone.sound(1000, 100, 0.5);
//					 } catch (LineUnavailableException e1) {
//						 e1.printStackTrace();
//					 }
//					return;
				default: System.out.println("Keyinput wrong");
			}

			switch (easterString.toLowerCase()) {
				case "bvb1909": easterEgg[0] = false; return;
				case "primzahlen": easterEgg[1] = false; return;
				case "fibonacci": easterEgg[2] = false; return;
				case "pyhtagoras": easterEgg[3] = false; return;
				case "thomae": easterEgg[4] = false; return;
				case "3,14159": easterEgg[0] = false; return;
				case "quadrat": easterEgg[0] = false; return;
				default: System.out.println("Easteregg not found");
			}
			System.out.println(easterString);
		}

		public void keyReleased(KeyEvent e) {
		}

		public void keyTyped(KeyEvent e) {
		}
	};
}

class Mouse{
	Point p1, p2;

	public Mouse(){
		p1 = new Point(0,0);
		p2 = new Point(0,0);
	}
	public void setP1(int x, int y){
		this.p1.setLocation(x,y);
	}

	public void setP2(int x, int y) {
		this.p2.setLocation(x,y);
	}

	public Point getP1() {
		return p1;
	}

	public Point getP2() {
		return p2;
	}

	public int getX1(){
		return (int) p1.getX();
	}
	public int getY1(){
		return (int) p1.getY();
	}
	public int getX2(){
		return (int) p2.getX();
	}
	public int getY2(){
		return (int) p2.getY();
	}
}