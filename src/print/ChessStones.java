package print;

import com.Board;
import com.Piece;

import java.awt.*;

public class ChessStones {
	private final String figuren = "PRKBQk";
	final String ROW = "abcdefgh";
	public Graphics2D gBuffer; //Main.gBuffer;
	public int width = 100;

	ChessColor cc = new ChessColor();
	public ChessStones(Board brd) {
		for (int x1 = 0; x1 < 8; x1++) {
			for (int y1 = 0; y1 < 8; y1++) { //TODO color
//				if (brd.isWhiteTurn())
//					gBuffer.setColor(cc.getPlayer1());
//				else if (!brd.getField(x1, y1).isPlayer1())
//					gBuffer.setColor(cc.getPlayer2());
//				else
//					continue;
//				gBuffer.fillOval(i*width + width, j*width + width, width, width);
//				gBuffer.setColor(Color.green);
//				gBuffer.setFont(new Font("Arial", Font.PLAIN, 80*width/100));
//				gBuffer.drawString("" + board[i][j].charAt(0), i*width+26 + width, j*width+76 + width);
//				gBuffer.setFont(new Font("Arial", Font.PLAIN, 20*width/100));

				int x2 = x1 * width;
				int y2 = y1 * width;
				for (Piece pc: brd.getPlayerStones().get(brd.getPlayer())) {
					if(pc.getX() == x1 && pc.getY() == y1) {
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
			}
		}
//		if(pressed) {
//			PointerInfo a = MouseInfo.getPointerInfo();
//
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
		gBuffer.fillRect(x + width, y + width, calcScale(30), 10*width/100);
		gBuffer.fillRect(x + width, y + width, calcScale(10), 30*width/100);
		
		gBuffer.fillRect(x + width + calcScale(70), y + width, calcScale(30), 10*width/100);
		gBuffer.fillRect(x + width + calcScale(90), y + width, calcScale(10), 30*width/100);
		
		gBuffer.fillRect(x + width, y + width + calcScale(90), calcScale(30), calcScale(10));
		gBuffer.fillRect(x + width, y + width + calcScale(70), calcScale(10), calcScale(30));
		
		gBuffer.fillRect(x + width + calcScale(70), y + width + calcScale(90), calcScale(30), calcScale(10));
		gBuffer.fillRect(x + width + calcScale(90), y + width + calcScale(70), calcScale(10), calcScale(30));
	}

	
	public void ChoseStone(int x, int y){
		for(int i = 0; i < 4; i++){
			gBuffer.setColor(Color.darkGray);
			gBuffer.fillRect((i+x) * width + width, y * width + width - width, width, width);
			gBuffer.setColor(Color.lightGray);
			gBuffer.fillRect((i+x) * width + width + calcScale(5), y * width + width - width + calcScale(5), width - calcScale(10), width - calcScale(10));
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
		gBuffer.fillRect(x + width, y + width, calcScale(30), calcScale( 6));
		gBuffer.fillRect(x + width, y + width, calcScale( 6), calcScale(30));
		
		gBuffer.fillRect(x + width + calcScale(70), y + width, calcScale(30), calcScale( 6));
		gBuffer.fillRect(x + width + calcScale(95), y + width, calcScale( 6), calcScale(30));
		
		gBuffer.fillRect(x + width, y + width + calcScale(95), calcScale(30), calcScale( 6));
		gBuffer.fillRect(x + width, y + width + calcScale(70), calcScale( 6), calcScale(30));
		
		gBuffer.fillRect(x + width + calcScale(70), y + width + calcScale(95), calcScale(30), calcScale( 6));
		gBuffer.fillRect(x + width + calcScale(95), y + width + calcScale(70), calcScale( 6), calcScale(30));
	}
	
	public void pawn(int x, int y) { // pawn
		gBuffer.fillArc (x + calcScale(19) + width, y + calcScale(80) + width, calcScale(65), calcScale(40), 0, 180); // Fu�1
		gBuffer.fillArc (x + calcScale(29) + width, y + calcScale(65) + width, calcScale(45), calcScale(40), 0, 180); // Fu�2
		gBuffer.fillOval(x + (int)(width / 2) - (int)(width / 6.2) + width, y + (int)(width / 1.8) - (int)(width / 6.6) + width, (int)(width / 3.3), (int)(width / 3.3)); // Kopf
	}

	public void rook(int x, int y) { // rook
		gBuffer.fillRect(x + calcScale(15) + width, y + calcScale(90) + width, calcScale(70), calcScale(10));// Fu�
		gBuffer.fillRect(x + calcScale(30) + width, y + calcScale(20) + width, calcScale(40), calcScale(70));// K�rper
		gBuffer.fillRect(x + calcScale(15) + width, y + calcScale(10) + width, calcScale(20), calcScale(20));// Ziegel-L
		gBuffer.fillRect(x + calcScale(40) + width, y + calcScale(10) + width, calcScale(20), calcScale(20));// Ziegel-M
		gBuffer.fillRect(x + calcScale(65) + width, y + calcScale(10) + width, calcScale(20), calcScale(20));// Ziegel-R
	}

	public void knight(int x, int y) { // kinight
		gBuffer.fillArc (x + calcScale(20) + width, y + calcScale(80) + width, calcScale(65), calcScale(40), 0, 180); // Fu�1
		gBuffer.fillArc (x + calcScale(30) + width, y + calcScale(65) + width, calcScale(45), calcScale(40), 0, 180); // Fu�2
		gBuffer.fillOval(x + calcScale(40) + width, y + calcScale(30) + width, calcScale(25), calcScale(50)); // K�rper
		gBuffer.fillOval(x + calcScale(25) + width, y + calcScale(20) + width, calcScale(30), calcScale(30)); // Kopf
		gBuffer.fillOval(x + calcScale(10) + width, y + calcScale(50) + width, calcScale(15), calcScale(15)); // Mund
		gBuffer.fillOval(x + calcScale(40) + width, y + calcScale(10) + width, calcScale( 8), calcScale(15)); // OhrL
		gBuffer.fillOval(x + calcScale(47) + width, y + calcScale(13) + width, calcScale(10), calcScale(20)); // OhrR
		int[] x1 = 		{x + calcScale(15) + width, x + calcScale(25) + width, x + calcScale(50) + width, x + calcScale(30) + width };// Mundst�ck
		int[] y1 = 		{y + calcScale(53) + width, y + calcScale(60) + width, y + calcScale(25) + width, y + calcScale(25) + width };
		gBuffer.fillPolygon(x1, y1, 4);
	}

	public void bishop(int x, int y) { // bishop
		gBuffer.fillArc (x + calcScale(19) + width, y + calcScale(85) + width, calcScale(65), calcScale(30), 0, 180); // Fu�1
		gBuffer.fillArc (x + calcScale(29) + width, y + calcScale(75) + width, calcScale(45), calcScale(30), 0, 180); // Fu�2
		gBuffer.fillOval(x + calcScale(35) + width, y + calcScale(13) + width, calcScale(30), calcScale(87)); // K�rper
		gBuffer.fillOval(x + calcScale(42) + width, y + calcScale( 5) + width, calcScale(15), calcScale(15)); // K�rper
	}
	
	public void queen(int x, int y) { // queen
		gBuffer.fillRect(x + calcScale(25) + width, y + calcScale(88) + width, calcScale(50), calcScale(12));// Fu�
		gBuffer.fillOval(x + calcScale(12) + width, y + calcScale(42) + width, calcScale(38), calcScale(38));// Kugel-L
		gBuffer.fillOval(x + calcScale(50) + width, y + calcScale(42) + width, calcScale(38), calcScale(38));// Kugel-R
		int[] x1 = 		{x + calcScale(25) + width, x + calcScale(75) + width, x + calcScale(87) + width, x + calcScale(13) + width };// Mittelteil
		int[] y1 = 		{y + calcScale(88) + width, y + calcScale(88) + width, y + calcScale(54) + width, y + calcScale(54) + width };
		gBuffer.fillPolygon(x1, y1, 4);
		gBuffer.fillOval(x + calcScale(15) + width, y + calcScale(20) + width, calcScale(10), calcScale(10));// Kugel-1
		gBuffer.fillOval(x + calcScale(35) + width, y + calcScale(15) + width, calcScale(10), calcScale(10));// Kugel-2
		gBuffer.fillOval(x + calcScale(55) + width, y + calcScale(15) + width, calcScale(10), calcScale(10));// Kugel-3
		gBuffer.fillOval(x + calcScale(75) + width, y + calcScale(20) + width, calcScale(10), calcScale(10));// Kugel-4
		gBuffer.setStroke(new BasicStroke(calcScale( 4)));
		gBuffer.drawLine(x + calcScale(20) + width, y + calcScale(25) + width, x + calcScale(50) + width, y + calcScale(95) + width); // Stab-1
		gBuffer.drawLine(x + calcScale(40) + width, y + calcScale(20) + width, x + calcScale(50) + width, y + calcScale(95) + width); // Stab-2
		gBuffer.drawLine(x + calcScale(60) + width, y + calcScale(20) + width, x + calcScale(50) + width, y + calcScale(95) + width); // Stab-3
		gBuffer.drawLine(x + calcScale(80) + width, y + calcScale(25) + width, x + calcScale(50) + width, y + calcScale(95) + width); // Stab-4
		gBuffer.fillRect(x + calcScale(40) + width, y + calcScale(48) + width, calcScale(20), calcScale(10));// L�ckenschlie�er

//		if(brd.plaplayer%2 == 0){ // TODO charC[0]
//			if(countColor[2] % color.length == 12) gBuffer.setColor(Color.white);
//			else if(countColor[2] % color.length != 12) gBuffer.setColor(Color.black);
//		} else {
//			if(countColor[3]%color.length == 12) gBuffer.setColor(Color.white);
//			else if(countColor[3]%color.length != 12) gBuffer.setColor(Color.black);
//		}
		gBuffer.fillRect(x + calcScale(25) + width, y + calcScale(90) + width, 0 + calcScale(50), calcScale( 4));// Balcken-unten
		int[] x2 = 		{x + calcScale(22) + width, x + calcScale(78) + width, x + calcScale(80) + width, x + calcScale(20) + width }; // Balcken-oben
		int[] y2 = 		{y + calcScale(80) + width, y + calcScale(80) + width, y + calcScale(76) + width, y + calcScale(76) + width };
		gBuffer.fillPolygon(x2, y2, 4);
	}
	
	public void king(int i, int j) { // king
		gBuffer.fillRect(i + calcScale(25) + width, j + calcScale(88) + width, calcScale(50), calcScale(12));// Fu�
		gBuffer.fillOval(i + calcScale(12) + width, j + calcScale(42) + width, calcScale(38), calcScale(38));// Kugel-L
		gBuffer.fillOval(i + calcScale(50) + width, j + calcScale(42) + width, calcScale(38), calcScale(38));// Kugel- R
		int[] x = 		{i + calcScale(25) + width, i + calcScale(75) + width, i + calcScale(87) + width, i + calcScale(13) + width }; // Mittelteil
		int[] y = 		{j + calcScale(88) + width, j + calcScale(88) + width, j + calcScale(54) + width, j + calcScale(54) + width };
		gBuffer.fillPolygon(x, y, 4);
		gBuffer.fillRect(i + calcScale(46) + width, j + calcScale(10) + width, calcScale( 8), calcScale(50));// Kreuzz-horzizontal
		gBuffer.fillRect(i + calcScale(38) + width, j + calcScale(18) + width, calcScale(24), calcScale( 8));// Kreuzz-vertikal

//		if(player%2 == 0){
//			if(countColor[2]%color.length == 12) gBuffer.setColor(Color.white);
//			else if(countColor[2]%color.length != 12) gBuffer.setColor(Color.black);
//		} else {
//			if(countColor[3]%color.length == 12) gBuffer.setColor(Color.white);
//			else if(countColor[3]%color.length != 12) gBuffer.setColor(Color.black);
//		}
		gBuffer.fillRect(i + calcScale(25) + width, j + calcScale(90) + width, 0 + calcScale(50), calcScale( 4));// Balcken-unten
		int[] x2 = 		{i + calcScale(22) + width, i + calcScale(78) + width, i + calcScale(80) + width, i + calcScale(20) + width }; // Balcken-oben
		int[] y2 = 		{j + calcScale(80) + width, j + calcScale(80) + width, j + calcScale(76) + width, j + calcScale(76) + width };
		gBuffer.fillPolygon(x2, y2, 4);
	}

	public void easterEggBvB1(){
		gBuffer.setColor(Color.black);
		gBuffer.setFont(new Font("Arial Narrow", Font.BOLD, (int)(95*width/100)));
		gBuffer.drawString("B", width * 8 + width  * 2 + (int)(25*width/100) -width+width, width * 1 + (int)(35*width/100) + width);
		gBuffer.drawString("V", width * 8 + width  * 2 + (int)(75*width/100) -width+width, width * 1 + 0 + width);
		gBuffer.drawString("B", width * 8 + width  * 2 + (int)(125*width/100) -width+width, width * 1 + (int)(35*width/100) + width);
		gBuffer.setFont(new Font("Arial Narrow", Font.BOLD, (int)(50*width/100)));
		gBuffer.drawString("09", width * 8 + width  * 2 + (int)(80*width/100) -width+width, width * 1 + (int)(75*width/100) + width);
	}

	public void easterEggBvB2(){
		gBuffer.setColor(Color.black);
		gBuffer.setFont(new Font("Arial Narrow", Font.BOLD, (int)(95*width/100)));
		gBuffer.drawString("B", width * 8 + width  * 2 + (int)(25*width/100) -width+width, width * 7 + (int)(35*width/100) + width);
		gBuffer.drawString("V", width * 8 + width  * 2 + (int)(75*width/100) -width+width, width * 7 + 0 + width);
		gBuffer.drawString("B", width * 8 + width  * 2 + (int)(125*width/100) -width+width, width * 7 + (int)(35*width/100) + width);
		gBuffer.setFont(new Font("Arial Narrow", Font.BOLD, (int)(50*width/100)));
		gBuffer.drawString("09", width * 8 + width  * 2 + (int)(80*width/100) -width+width, width * 7 + (int)(75*width/100) + width);
	}

	public int calcScale(int a){
		return a * width / 100;
	}

	public String getFiguren() {
		return figuren;
	}

	public String getROW() {
		return ROW;
	}
}
