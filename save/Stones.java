package com.company;

import java.awt.*;

public class Stones extends Main {
	
	public Stones() {
		for (int x1 = 0; x1 < 8; x1++) {
			for (int y1 = 0; y1 < 8; y1++) {
				if (getColor(board[x1][y1]) == charC[0])
					gBuffer.setColor(col[countColor3 % col.length]);
				else if (getColor(board[x1][y1]) == charC[1])
					gBuffer.setColor(col[countColor4 % col.length]);
				else
					continue;
//				gBuffer.fillOval(i*width + abstX, j*width + abstY, width, width);
//				gBuffer.setColor(Color.green);
//				gBuffer.setFont(new Font("Arial", Font.PLAIN, 80*width/100));
//				gBuffer.drawString("" + board[i][j].charAt(0), i*width+26 + abstX, j*width+76 + abstY);
//				gBuffer.setFont(new Font("Arial", Font.PLAIN, 20*width/100));
				
				char temp = getPiece(board[x1][y1]);

				int x2 = x1*width;
				int y2 = y1*width;
				switch(figuren.indexOf(temp)){
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
		if(pressed){
			PointerInfo a = MouseInfo.getPointerInfo();

			Point b = a.getLocation();
			int x = (int) b.getX() - abstX;
			int y = (int) b.getY() - abstY - 25;
//			pawn(x-f.getX(), y-f.getY()); // TODO Pointer
		}
		if(game && player > 1){
			LastMove(letzterZugX1, letzterZugY1);
			LastMove(letzterZugX2, letzterZugY2);
		}
	}
	
	public void LastMove(int x, int y){
		x *= width;
		y *= width;
		int secColor = 0;
		while(checkForColor2(secColor)){ secColor++; }  //TODO: integrieren
		gBuffer.setColor(col[secColor%col.length]);
		gBuffer.fillRect(x + abstX, y + abstY, calcScale(30), 10*width/100);
		gBuffer.fillRect(x + abstX, y + abstY, calcScale(10), 30*width/100);
		
		gBuffer.fillRect(x + abstX + calcScale(70), y + abstY, calcScale(30), 10*width/100);
		gBuffer.fillRect(x + abstX + calcScale(90), y + abstY, calcScale(10), 30*width/100);
		
		gBuffer.fillRect(x + abstX, y + abstY + calcScale(90), calcScale(30), calcScale(10));
		gBuffer.fillRect(x + abstX, y + abstY + calcScale(70), calcScale(10), calcScale(30));
		
		gBuffer.fillRect(x + abstX + calcScale(70), y + abstY + calcScale(90), calcScale(30), calcScale(10));
		gBuffer.fillRect(x + abstX + calcScale(90), y + abstY + calcScale(70), calcScale(10), calcScale(30));
	}

	
	public void ChoseStone(int x, int y){
		for(int i = 0; i < 4; i++){
			gBuffer.setColor(Color.darkGray);
			gBuffer.fillRect((i+x) * width + abstX, y * width + abstY - width, width, width);
			gBuffer.setColor(Color.lightGray);
			gBuffer.fillRect((i+x) * width + abstX + calcScale(5), y * width + abstY - width + calcScale(5), width - calcScale(10), width - calcScale(10));
			if (getColor(board[x][y]) == charC[0]) {
				gBuffer.setColor(col[countColor3 % col.length]);
			}
			else if (getColor(board[x][y]) == charC[1]) {
				gBuffer.setColor(col[countColor4 % col.length]);
			}
			rook  ((0 + x) * width, (-1 + y) * width);
			knight((1 + x) * width, (-1 + y) * width);
			bishop((2 + x) * width, (-1 + y) * width);
			queen ((3 + x) * width, (-1 + y) * width);
		}
		y *= width;
		x *= width;
		gBuffer.setColor(Color.darkGray);
		gBuffer.fillRect(x + abstX, y + abstY, calcScale(30), calcScale( 6));
		gBuffer.fillRect(x + abstX, y + abstY, calcScale( 6), calcScale(30));
		
		gBuffer.fillRect(x + abstX + calcScale(70), y + abstY, calcScale(30), calcScale( 6));
		gBuffer.fillRect(x + abstX + calcScale(95), y + abstY, calcScale( 6), calcScale(30));
		
		gBuffer.fillRect(x + abstX, y + abstY + calcScale(95), calcScale(30), calcScale( 6));
		gBuffer.fillRect(x + abstX, y + abstY + calcScale(70), calcScale( 6), calcScale(30));
		
		gBuffer.fillRect(x + abstX + calcScale(70), y + abstY + calcScale(95), calcScale(30), calcScale( 6));
		gBuffer.fillRect(x + abstX + calcScale(95), y + abstY + calcScale(70), calcScale( 6), calcScale(30));
	}
	
	public void pawn(int x, int y) { // pawn
		gBuffer.fillArc (x + calcScale(19) + abstX, y + calcScale(80) + abstY, calcScale(65), calcScale(40), 0, 180); // Fu�1
		gBuffer.fillArc (x + calcScale(29) + abstX, y + calcScale(65) + abstY, calcScale(45), calcScale(40), 0, 180); // Fu�2
		gBuffer.fillOval(x + (int)(width / 2) - (int)(width / 6.2) + abstX, y + (int)(width / 1.8) - (int)(width / 6.6) + abstY, (int)(width / 3.3), (int)(width / 3.3)); // Kopf
	}

	public void rook(int x, int y) { // rook
		gBuffer.fillRect(x + calcScale(15) + abstX, y + calcScale(90) + abstY, calcScale(70), calcScale(10));// Fu�
		gBuffer.fillRect(x + calcScale(30) + abstX, y + calcScale(20) + abstY, calcScale(40), calcScale(70));// K�rper
		gBuffer.fillRect(x + calcScale(15) + abstX, y + calcScale(10) + abstY, calcScale(20), calcScale(20));// Ziegel-L
		gBuffer.fillRect(x + calcScale(40) + abstX, y + calcScale(10) + abstY, calcScale(20), calcScale(20));// Ziegel-M
		gBuffer.fillRect(x + calcScale(65) + abstX, y + calcScale(10) + abstY, calcScale(20), calcScale(20));// Ziegel-R
	}

	public void knight(int x, int y) { // kinight
		gBuffer.fillArc (x + calcScale(20) + abstX, y + calcScale(80) + abstY, calcScale(65), calcScale(40), 0, 180); // Fu�1
		gBuffer.fillArc (x + calcScale(30) + abstX, y + calcScale(65) + abstY, calcScale(45), calcScale(40), 0, 180); // Fu�2
		gBuffer.fillOval(x + calcScale(40) + abstX, y + calcScale(30) + abstY, calcScale(25), calcScale(50)); // K�rper
		gBuffer.fillOval(x + calcScale(25) + abstX, y + calcScale(20) + abstY, calcScale(30), calcScale(30)); // Kopf
		gBuffer.fillOval(x + calcScale(10) + abstX, y + calcScale(50) + abstY, calcScale(15), calcScale(15)); // Mund
		gBuffer.fillOval(x + calcScale(40) + abstX, y + calcScale(10) + abstY, calcScale( 8), calcScale(15)); // OhrL
		gBuffer.fillOval(x + calcScale(47) + abstX, y + calcScale(13) + abstY, calcScale(10), calcScale(20)); // OhrR
		int[] x1 = 		{x + calcScale(15) + abstX, x + calcScale(25) + abstX, x + calcScale(50) + abstX, x + calcScale(30) + abstX };// Mundst�ck
		int[] y1 = 		{y + calcScale(53) + abstY, y + calcScale(60) + abstY, y + calcScale(25) + abstY, y + calcScale(25) + abstY };
		gBuffer.fillPolygon(x1, y1, 4);
	}

	public void bishop(int x, int y) { // bishop
		gBuffer.fillArc (x + calcScale(19) + abstX, y + calcScale(85) + abstY, calcScale(65), calcScale(30), 0, 180); // Fu�1
		gBuffer.fillArc (x + calcScale(29) + abstX, y + calcScale(75) + abstY, calcScale(45), calcScale(30), 0, 180); // Fu�2
		gBuffer.fillOval(x + calcScale(35) + abstX, y + calcScale(13) + abstY, calcScale(30), calcScale(87)); // K�rper
		gBuffer.fillOval(x + calcScale(42) + abstX, y + calcScale( 5) + abstY, calcScale(15), calcScale(15)); // K�rper
	}
	
	public void queen(int x, int y) { // queen
		gBuffer.fillRect(x + calcScale(25) + abstX, y + calcScale(88) + abstY, calcScale(50), calcScale(12));// Fu�
		gBuffer.fillOval(x + calcScale(12) + abstX, y + calcScale(42) + abstY, calcScale(38), calcScale(38));// Kugel-L
		gBuffer.fillOval(x + calcScale(50) + abstX, y + calcScale(42) + abstY, calcScale(38), calcScale(38));// Kugel-R
		int[] x1 = 		{x + calcScale(25) + abstX, x + calcScale(75) + abstX, x + calcScale(87) + abstX, x + calcScale(13) + abstX };// Mittelteil
		int[] y1 = 		{y + calcScale(88) + abstY, y + calcScale(88) + abstY, y + calcScale(54) + abstY, y + calcScale(54) + abstY };
		gBuffer.fillPolygon(x1, y1, 4);
		gBuffer.fillOval(x + calcScale(15) + abstX, y + calcScale(20) + abstY, calcScale(10), calcScale(10));// Kugel-1
		gBuffer.fillOval(x + calcScale(35) + abstX, y + calcScale(15) + abstY, calcScale(10), calcScale(10));// Kugel-2
		gBuffer.fillOval(x + calcScale(55) + abstX, y + calcScale(15) + abstY, calcScale(10), calcScale(10));// Kugel-3
		gBuffer.fillOval(x + calcScale(75) + abstX, y + calcScale(20) + abstY, calcScale(10), calcScale(10));// Kugel-4
		gBuffer.setStroke(new BasicStroke(calcScale( 4)));
		gBuffer.drawLine(x + calcScale(20) + abstX, y + calcScale(25) + abstY, x + calcScale(50) + abstX, y + calcScale(95) + abstY); // Stab-1
		gBuffer.drawLine(x + calcScale(40) + abstX, y + calcScale(20) + abstY, x + calcScale(50) + abstX, y + calcScale(95) + abstY); // Stab-2
		gBuffer.drawLine(x + calcScale(60) + abstX, y + calcScale(20) + abstY, x + calcScale(50) + abstX, y + calcScale(95) + abstY); // Stab-3
		gBuffer.drawLine(x + calcScale(80) + abstX, y + calcScale(25) + abstY, x + calcScale(50) + abstX, y + calcScale(95) + abstY); // Stab-4
		gBuffer.fillRect(x + calcScale(40) + abstX, y + calcScale(48) + abstY, calcScale(20), calcScale(10));// L�ckenschlie�er

		if(getPlayer() == 0){ // TODO charC[0]
			if(countColor3 % color.length == 12) gBuffer.setColor(Color.white);
			else if(countColor3 % color.length != 12) gBuffer.setColor(Color.black);
		} else {
			if(countColor4%color.length == 12) gBuffer.setColor(Color.white);
			else if(countColor4%color.length != 12) gBuffer.setColor(Color.black);
		}
		gBuffer.fillRect(x + calcScale(25) + abstX, y + calcScale(90) + abstY, 0 + calcScale(50), calcScale( 4));// Balcken-unten
		int[] x2 = 		{x + calcScale(22) + abstX, x + calcScale(78) + abstX, x + calcScale(80) + abstX, x + calcScale(20) + abstX }; // Balcken-oben
		int[] y2 = 		{y + calcScale(80) + abstY, y + calcScale(80) + abstY, y + calcScale(76) + abstY, y + calcScale(76) + abstY };
		gBuffer.fillPolygon(x2, y2, 4);
	}
	
	public void king(int i, int j) { // king
		gBuffer.fillRect(i + calcScale(25) + abstX, j + calcScale(88) + abstY, calcScale(50), calcScale(12));// Fu�
		gBuffer.fillOval(i + calcScale(12) + abstX, j + calcScale(42) + abstY, calcScale(38), calcScale(38));// Kugel-L
		gBuffer.fillOval(i + calcScale(50) + abstX, j + calcScale(42) + abstY, calcScale(38), calcScale(38));// Kugel- R
		int[] x = 		{i + calcScale(25) + abstX, i + calcScale(75) + abstX, i + calcScale(87) + abstX, i + calcScale(13) + abstX }; // Mittelteil
		int[] y = 		{j + calcScale(88) + abstY, j + calcScale(88) + abstY, j + calcScale(54) + abstY, j + calcScale(54) + abstY };
		gBuffer.fillPolygon(x, y, 4);
		gBuffer.fillRect(i + calcScale(46) + abstX, j + calcScale(10) + abstY, calcScale( 8), calcScale(50));// Kreuzz-horzizontal
		gBuffer.fillRect(i + calcScale(38) + abstX, j + calcScale(18) + abstY, calcScale(24), calcScale( 8));// Kreuzz-vertikal

		if(getPlayer() == 0){
			if(countColor3%color.length == 12) gBuffer.setColor(Color.white);
			else if(countColor3%color.length != 12) gBuffer.setColor(Color.black);
		} else {
			if(countColor4%color.length == 12) gBuffer.setColor(Color.white);
			else if(countColor4%color.length != 12) gBuffer.setColor(Color.black);
		}
		gBuffer.fillRect(i + calcScale(25) + abstX, j + calcScale(90) + abstY, 0 + calcScale(50), calcScale( 4));// Balcken-unten
		int[] x2 = 		{i + calcScale(22) + abstX, i + calcScale(78) + abstX, i + calcScale(80) + abstX, i + calcScale(20) + abstX }; // Balcken-oben
		int[] y2 = 		{j + calcScale(80) + abstY, j + calcScale(80) + abstY, j + calcScale(76) + abstY, j + calcScale(76) + abstY };
		gBuffer.fillPolygon(x2, y2, 4);
	}

	public int calcScale(int a){
		return a * width / 100;
	}
}
