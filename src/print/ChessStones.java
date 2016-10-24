package print;

import com.Board;
import com.Piece;

import java.awt.*;
import java.util.ArrayList;

public class ChessStones {
	private final String figuren = "PRKBQk";
	final String ROW = "abcdefgh";
	private Graphics2D gBuffer;
	private int width = Main.width;

	ChessColor cc = new ChessColor();
	public ChessStones(Board brd, Graphics2D gBuffer) {
		this.gBuffer = gBuffer;
		for (ArrayList<Piece> tempStones: brd.getStones()) {
			for (Piece pc: tempStones) {
				if(pc.isWhite()) gBuffer.setColor(cc.getPlayer1());
				else gBuffer.setColor(cc.getPlayer2());
//				gBuffer.fillOval(i*width + width, j*width + width, width, width);
//				gBuffer.setColor(Color.green);
//				gBuffer.setFont(new Font("Arial", Font.PLAIN, 80*width/100));
//				gBuffer.drawString("" + board[i][j].charAt(0), i*width+26 + width, j*width+76 + width);
//				gBuffer.setFont(new Font("Arial", Font.PLAIN, 20*width/100));
				int x2 = pc.getX() * width;
				int y2 = pc.getY() * width;
				switch(pc.getID()){
					case 0 : pawn(x2, y2); break;
					case 1 : rook(x2, y2); break;
					case 2 : knight(x2, y2); break;
					case 3 : bishop(x2, y2); break;
					case 4 : queen(x2, y2); break;
					case 5 : king(x2, y2); break;
					default: System.out.println("not listed"); break;
				}
			}
		}
//		if(pressed) {
//			PointerInfo a = MouseInfo.getPointerInfo();
//			Point b = a.getLocation();
//			int x = (int) b.getX() - width;
//			int y = (int) b.getY() - width - 25;
////			pawn(x-f.getX(), y-f.getY()); // TODO Pointer
//		}
//		if(game && player > 1) {
//			LastMove(lastMove.p1);
//			LastMove(lastMove.p2);
//			LastMove(letzterZugX2, letzterZugY2);
//		}
	}

	public void LastMove(Point p){
		int x = p.x * width;
		int y = p.y * width;
		int secColor = 0;
		while(cc.checkForSameColorSelection(secColor)){ secColor++; }  //TODO: integrieren
		gBuffer.setColor(cc.getColor(secColor % cc.getLength()));
		gBuffer.fillRect(x + width, y + width, scale(30), 10*width/100);
		gBuffer.fillRect(x + width, y + width, scale(10), 30*width/100);
		
		gBuffer.fillRect(x + width + scale(70), y + width, scale(30), 10*width/100);
		gBuffer.fillRect(x + width + scale(90), y + width, scale(10), 30*width/100);
		
		gBuffer.fillRect(x + width, y + width + scale(90), scale(30), scale(10));
		gBuffer.fillRect(x + width, y + width + scale(70), scale(10), scale(30));
		
		gBuffer.fillRect(x + width + scale(70), y + width + scale(90), scale(30), scale(10));
		gBuffer.fillRect(x + width + scale(90), y + width + scale(70), scale(10), scale(30));
	}

	public void ChoseStone(int x, int y){
		for(int i = 0; i < 4; i++){
			gBuffer.setColor(Color.darkGray);
			gBuffer.fillRect((i+x) * width + width, y * width + width - width, width, width);
			gBuffer.setColor(Color.lightGray);
			gBuffer.fillRect((i+x) * width + width + scale(5), y * width + width - width + scale(5), width - scale(10), width - scale(10));
			Point temp_point = new Point(x,y);
//			if (brd.getField(x,y).isPlayer1()) {
//				gBuffer.setColor(cc.getPlayer1());
//			} else if (!brd.getField(x,y).isPlayer1()) {
//				gBuffer.setColor(cc.getPlayer2());
//			}
			rook  ((0 + x) * width, (-1 + y) * width);
			knight((1 + x) * width, (-1 + y) * width);
			bishop((2 + x) * width, (-1 + y) * width);
			queen ((3 + x) * width, (-1 + y) * width);
		}
		y *= width;
		x *= width;
		gBuffer.setColor(Color.darkGray);
		gBuffer.fillRect(x + width, y + width, scale(30), scale( 6));
		gBuffer.fillRect(x + width, y + width, scale( 6), scale(30));
		
		gBuffer.fillRect(x + width + scale(70), y + width, scale(30), scale( 6));
		gBuffer.fillRect(x + width + scale(95), y + width, scale( 6), scale(30));
		
		gBuffer.fillRect(x + width, y + width + scale(95), scale(30), scale( 6));
		gBuffer.fillRect(x + width, y + width + scale(70), scale( 6), scale(30));
		
		gBuffer.fillRect(x + width + scale(70), y + width + scale(95), scale(30), scale( 6));
		gBuffer.fillRect(x + width + scale(95), y + width + scale(70), scale( 6), scale(30));
	}
	
	public void pawn(int x, int y) { // pawn
		printFeet(x,y); // feet
		printFillOval(x + scale(38), y + scale(38), 32, 32); // head
	}

	public void rook(int x, int y) { // rook
		printFillRect(x + scale(15), y + scale(90), 70, 10); // feet
		printFillRect(x + scale(30), y + scale(20), 40, 70); // body
		printFillRect(x + scale(15), y + scale(10), 20, 20); // krone 1
		printFillRect(x + scale(40), y + scale(10), 20, 20); // krone 2
		printFillRect(x + scale(65), y + scale(10), 20, 20); // krone 3
	}

	public void knight(int x, int y) { // kinight
		printFeet(x,y); // feet
		printFillOval(x + scale(40), y + scale(30), 25, 50); // body
		printFillOval(x + scale(25), y + scale(20), 30, 30); // head
		printFillOval(x + scale(10), y + scale(50), 15, 15); // mouth
		printFillOval(x + scale(40), y + scale(10),  8, 15); // ear l
		printFillOval(x + scale(47), y + scale(13), 10, 20); // ear r
		int[] x1 = {x + scale(15) + width, x + scale(25) + width, x + scale(50) + width, x + scale(30) + width };// Mundst�ck
		int[] y1 = {y + scale(53) + width, y + scale(60) + width, y + scale(25) + width, y + scale(25) + width };
		gBuffer.fillPolygon(x1, y1, 4);
	}

	public void bishop(int x, int y) { // bishop
		printFeet(x,y); // feet
		printFillOval(x + scale(42), y + scale( 5), 15, 15); // body top
		printFillOval(x + scale(35), y + scale(13), 30, 87); // body middle
	}

	public void queen(int x, int y) { // queen
		printFillRect(x + scale(25), y + scale(88), 50, 12); // feet
		printFillOval(x + scale(12), y + scale(42), 38, 38); // ball l
		printFillOval(x + scale(50), y + scale(42), 38, 38); // ball r
		int[] x1 = {x + scale(25) + width, x + scale(75) + width, x + scale(87) + width, x + scale(13) + width };// Mittelteil
		int[] y1 = {y + scale(88) + width, y + scale(88) + width, y + scale(54) + width, y + scale(54) + width };
		gBuffer.fillPolygon(x1, y1, 4);
		printFillOval(x + scale(15), y + scale(20), 10, 10); // ball 1
		printFillOval(x + scale(35), y + scale(15), 10, 10); // ball 2
		printFillOval(x + scale(55), y + scale(15), 10, 10); // ball 3
		printFillOval(x + scale(75), y + scale(20), 10, 10); // ball 4
		gBuffer.setStroke(new BasicStroke(scale( 4)));
		gBuffer.drawLine(x + scale(20) + width, y + scale(25) + width, x + scale(50) + width, y + scale(95) + width); // Stab-1
		gBuffer.drawLine(x + scale(40) + width, y + scale(20) + width, x + scale(50) + width, y + scale(95) + width); // Stab-2
		gBuffer.drawLine(x + scale(60) + width, y + scale(20) + width, x + scale(50) + width, y + scale(95) + width); // Stab-3
		gBuffer.drawLine(x + scale(80) + width, y + scale(25) + width, x + scale(50) + width, y + scale(95) + width); // Stab-4
		printFillRect(x + scale(40), y + scale(48), 20, 10); // gap closer
//		if(brd.plaplayer%2 == 0){ // TODO charC[0]
//			if(countColor[2] % color.length == 12) gBuffer.setColor(Color.white);
//			else if(countColor[2] % color.length != 12) gBuffer.setColor(Color.black);
//		} else {
//			if(countColor[3]%color.length == 12) gBuffer.setColor(Color.white);
//			else if(countColor[3]%color.length != 12) gBuffer.setColor(Color.black);
//		}
		printFillRect(x + scale(25), y + scale(90), 50, 4); // bar bottom
		int[] x2 = {x + scale(22) + width, x + scale(78) + width, x + scale(80) + width, x + scale(20) + width }; // Balcken-oben
		int[] y2 = {y + scale(80) + width, y + scale(80) + width, y + scale(76) + width, y + scale(76) + width };
		gBuffer.fillPolygon(x2, y2, 4);
	}

	public void king(int x, int y) { // king
		printFillRect(x + scale(25), y + scale(88), 50, 12); // feet
		printFillOval(x + scale(12), y + scale(42), 38, 38); // ball l
		printFillOval(x + scale(50), y + scale(42), 38, 38); // ball r
		int[] x1 = {x + scale(25) + width, x + scale(75) + width, x + scale(87) + width, x + scale(13) + width }; // Mittelteil
		int[] y1 = {y + scale(88) + width, y + scale(88) + width, y + scale(54) + width, y + scale(54) + width };
		gBuffer.fillPolygon(x1, y1, 4);
		printFillRect(x + scale(46), y + scale(10),  8, 50); // cross horizontal
		printFillRect(x + scale(38), y + scale(18), 24,  8); // cross vertical
//		if(player%2 == 0){
//			if(countColor[2]%color.length == 12) gBuffer.setColor(Color.white);
//			else if(countColor[2]%color.length != 12) gBuffer.setColor(Color.black);
//		} else {
//			if(countColor[3]%color.length == 12) gBuffer.setColor(Color.white);
//			else if(countColor[3]%color.length != 12) gBuffer.setColor(Color.black);
//		}
		printFillRect(x + scale(25), y + scale(90), 50,  4); // bar bottom
		int[] x2 = {x + scale(22) + width, x + scale(78) + width, x + scale(80) + width, x + scale(20) + width }; // Balcken-oben
		int[] y2 = {y + scale(80) + width, y + scale(80) + width, y + scale(76) + width, y + scale(76) + width };
		gBuffer.fillPolygon(x2, y2, 4);
	}

	private void printFeet(int x, int y) {
		printFillArc(x + scale(20), y + scale(80), 65, 40); // feet 1
		printFillArc(x + scale(30), y + scale(65), 45, 40); // feet 2
	}
	private void printFillRect(int x, int y, int w, int h) {
		gBuffer.fillRect(x + width, y + width, scale(w), scale(h));
	}

	private void printFillOval(int x, int y, int w, int h) {
		gBuffer.fillOval(x + width, y + width, scale(w), scale(h));
	}

	private void printFillArc(int x, int y, int w, int h) {
		gBuffer.fillArc (x + width, y + width, scale(w), scale(h), 0, 180); // Fu�1
	}

	private int scale(int a){
		return a * width / 100;
	}

	public void easterEggBvB1(){
		gBuffer.setColor(Color.black);
		gBuffer.setFont(new Font("Arial Narrow", Font.BOLD, (95*width/100)));
		gBuffer.drawString("B", width * 8 + width * 2 + scale(25), width + scale(35) + width);
		gBuffer.drawString("V", width * 8 + width * 2 + scale(75), width + width);
		gBuffer.drawString("B", width * 8 + width * 2 + scale(125), width + scale(35) + width);
		gBuffer.setFont(new Font("Arial Narrow", Font.BOLD, scale(50)));
		gBuffer.drawString("09", width * 8 + width * 2 + scale(80), width + scale(75) + width);
	}

	public void easterEggBvB2(){
		gBuffer.setColor(Color.black);
		gBuffer.setFont(new Font("Arial Narrow", Font.BOLD, (95*width/100)));
		gBuffer.drawString("B", width * 8 + width  * 2 + scale(25), width * 7 + (35*width/100) + width);
		gBuffer.drawString("V", width * 8 + width  * 2 + scale(75), width * 7 + width);
		gBuffer.drawString("B", width * 8 + width  * 2 + scale(125), width * 7 + (35*width/100) + width);
		gBuffer.setFont(new Font("Arial Narrow", Font.BOLD, scale(50)));
		gBuffer.drawString("09", width * 8 + width  * 2 + scale(80), width * 7 + (75*width/100) + width);
	}

	public void printSinus() {
		int sincl = 0;
		for (double i = 0; i < 480; i++) {
			gBuffer.setStroke(new BasicStroke(5));
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
}
