package print;

import com.Game;
import com.Player;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class Main extends JPanel implements Runnable {
    private static final long serialVersionUID = 1L;

    public static int minsek;
    public static int width = 60;
//    public static ChessColor color = new ChessColor();

    static boolean computer = false;
    static boolean computerVScomputer = computer;
    static boolean calculating = false;
    //	static int timeLimit = 1800;
    static boolean uhrSek = false;

    //	static String board[][] = new String[8][8]; // TODO multiverson

    static print.Button[][] btnsTime = new print.Button[2][4];
    static print.Button btnClock;
    static Field[][] fields = new Field[8][8];
    static String log[] = new String[9999];
    static Mouse m = new Mouse();
    static Mouse lastMove = new Mouse();

    static int quaX;
    static int quaY = 100;
    static boolean easterEgg[] = new boolean[10];
    /* P-0 R-1 L-2 B-3 Q-4 k-5 */
//	static final String figuren = "PRKBQk";

    static Timer timeP1 = new Timer(1800);
    static Timer timeP2 = new Timer(1800);
    static Image buffer;

    public static Game game = new Game(new Player(""+0), new Player(""+1));
    public static Graphics2D gBuffer;

    ChessPrint menu = new ChessPrint();
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
        reset();
    }

    public Main() {
        this.setPreferredSize(new Dimension(scale(900) + width, scale(900) + width)); // 1400
        setLayout(null);
    }

    public static void main(String args[]) {
        EventQueue.invokeLater(new Main());
    }

    public int scale(int n) {
        return (n * width) / 100;
    }

    public void paintComponent(Graphics g) {
		super.paintComponent(g);
        gBuffer = (Graphics2D) g;
        gBuffer.clearRect(0, 0, this.getSize().width, this.getSize().height);
        gBuffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if ((double) this.getSize().width / this.getSize().height <= (double) 750 / 450)
			width = (this.getSize().width) / 9 - width / 9;
		else if ((double) this.getSize().width / this.getSize().height > (double) 750 / 450)
			width = (this.getSize().height) / 9 - width / 9;
//		if (game && !calculating) {
//			if (computer && player == 1)
//				new Random(brd);
//			else if (computerVScomputer && player == 0)
//				new Random(brd);
//		}

        menu.print(game, width);//lastMove.getX1(), lastMove.getY1(), lastMove.getX2(), lastMove.getY2());
        g.drawImage(buffer, 0, 0, this);
        repaint();
    }

    public void stuffNeedToDo() {
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

    public void reset() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                fields[x][y] = new Field(width + width*x, width+width*y, width, width);
            }
        }
        ChessPrint.choseStone = false;
        ChessPrint.selection = false;

        game = new Game(new Player(""+0), new Player(""+1));

        calculating = false;
        m = new Mouse();
        lastMove = new Mouse();
        timeP1.reset();
        timeP2.reset();
        for (int i = 0; i < easterEgg.length; i++)
            easterEgg[i] = true;

        int l = 1310;
        int o = (800);
        int u = (905);
        int w = 35;
        int h = (25);
        int ab = (40);
        for (int i = 0; i < 4; i++) {
            btnsTime[0][3 - i] = new print.Button(l + ab * i, o, w, h, 3);
            btnsTime[1][3 - i] = new print.Button(l + ab * i, u, w, h, 3);
        }
        btnClock = new print.Button((1105 * width / 100), (740 * width / 100), scale(98), scale(50), 0);
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

    private class RepaintOnClick implements MouseListener {
        public int scale(int n) {
            return (n * width) / 100;
        }

        public boolean isBetween(int n, int d1, int d2) {
            return n >= d1 && n < d2;
        }

        public void timerAd(double n) {
//            Timer.limit += n;
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
//            m.getP2().setLocation(evt.getX(),evt.getY());// - width, evt.getY() - width - 25);
            int mx1 = m.getX2();// - width;
            int my1 = m.getY2();// - width - 25;
            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    if(fields[x][y].isPressed(evt.getPoint())) {
                        System.out.println(x + " " + y + " " +m.getX1()/width + " " + m.getY2()/width);
                        if (!menu.selection && !menu.choseStone && !game.brd.isEmpty(x, y)) {
                            m.p1.setLocation(x,y);
                            menu.selection = true;
                        } else if (menu.selection) {
                            if (x == m.p1.getX() && y == m.p1.getY()) {
                                menu.selection = false;
                                menu.choseStone = false;
                            } else if (!game.brd.isColorEqual(m.getX1()/width,m.getY2()/width,x,y)) {
                                m.p2.setLocation(x,y);
                                menu.choseStone = true;
                            }
                        }
                    }
                }
            }
            if (isBetween(mx1, 0, width * 8)) { // board
                if (isBetween(my1, 0, width * 8)) {
//                    if (!menu.selection && !menu.choseStone && !game.brd.isEmpty(mx1/width, my1/width)) {
//                        m.p1.setLocation(mx1 / width, my1 / width);
//                        menu.selection = true;
//                    } else if (menu.selection) {
//                        if ((evt.getX()-width) / width == m.p1.getX() && (evt.getY() - width - 25) / width == m.p1.getY()) {
//                            menu.selection = false;
//                            menu.choseStone = false;
//                        } else { //if (!game.brd.isColorEqual(mx1/width,my1/width,m.getX1()/width,m.getY1()/width)) {
//                            m.p2.setLocation(mx1 / width, my1 / width);
//                            menu.choseStone = true;
//                        }
//                    }
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
            if (mx1 >= width * 9) { // settings
                System.out.print("true");
                if (btnClock.isPressed(evt.getPoint())) {
                    if (isBetween(mx1, 1140, 1165)) {
                            computer = false;
                            computerVScomputer = false;
                            game.finish();
                        } else if (isBetween(mx1, 1210, 1235)) {
                            computer = true;
                            computerVScomputer = false;
                            game.finish();
                        } else if (isBetween(mx1, 1280, 1305)) {
                            computer = false;
                            computerVScomputer = true;
                            game.finish();
                        } else if (isBetween(mx1, 1350, 1375)) {
                            computer = true;
                            computerVScomputer = true;
                            game.finish();
                        }
                }
                if (isBetween(my1, 150, 175)) { // mode
                }

                for (int i = 0; i < 2; i++) { // time
                    for (int j = 0; j < 4; j++) {
                        if (btnsTime[i][j].isPressed(evt.getPoint())) {
//                            color.change(i, j);
                        }
                    }
                }

                if (isBetween(mx1, 1105, 1203)) {
                    if (isBetween(my1, 740, 790)) { // minsek
                        minsek = (minsek + 1) % 2;
                    }
                } else if (!game.isRunning()) {
                    for (int i = 0; i < 2; i++) { // time
                        for (int j = 0; j < 4; j++) {
                            if (btnsTime[i][j].isPressed(evt.getPoint())) {
                                if (uhrSek) {
                                    if (i == 0) {
                                        if (timeP1.getLimit() < 5999) timerAd(Math.pow(10, j));
                                    } else if (timeP1.getLimit() > Math.pow(10, j)) timerAd(-Math.pow(10, j));
                                } else {
                                    if (Math.pow(10, j) > 60) {
                                        if (i == 0) {
                                            if (timeP1.getLimit() < 5999) timerAd(6 * Math.pow(10, j - 1));
                                        } else if (timeP1.getLimit() > 6 * Math.pow(10, j - 1))
                                            timerAd(-6 * Math.pow(10, j - 1));
                                    } else {
                                        if (i == 0) {
                                            if (timeP1.getLimit() < 5999) timerAd(Math.pow(10, j));
                                        } else if (timeP1.getLimit() > Math.pow(10, j)) timerAd(-Math.pow(10, j));
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

        public void mouseMoved(MouseEvent evt) {
        }
    }

    public KeyListener keylistener = new KeyListener() {
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_DOWN: width -= 5; return;
                case KeyEvent.VK_UP: width += 5; return;
//                case KeyEvent.VK_RIGHT: Timer.limit -= 10; return;
                case KeyEvent.VK_ESCAPE: reset(); return;
                case KeyEvent.VK_G: printLog(); return;
                case KeyEvent.VK_H: nullLog(); return;
                case KeyEvent.VK_S: game.start(); return;
                case KeyEvent.VK_N: game.nextPlayer(); return;
                default: System.out.println("Keyinput wrong");
            }
        }
        public void keyReleased(KeyEvent e) {
        }

        public void keyTyped(KeyEvent e) {
        }
    };
}

class Mouse {
    Point p1, p2;

    public Mouse() {
        p1 = new Point(0, 0);
        p2 = new Point(0, 0);
    }

    public void setP1(int x, int y) {
        this.p1.setLocation(x, y);
    }

    public void setP2(int x, int y) {
        this.p2.setLocation(x, y);
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    public int getX1() {
        return (int) p1.getX();
    }

    public int getY1() {
        return (int) p1.getY();
    }

    public int getX2() {
        return (int) p2.getX();
    }

    public int getY2() {
        return (int) p2.getY();
    }
}