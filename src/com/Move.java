package com;

import java.awt.*;

public class Move extends Logic {
	static boolean moved = false;
	boolean calculating = false;

	/**
	 * moved = true if stones got moved
	 * @param brd
     */
	public Move(Board brd, int x1, int y1, int x2, int y2) {
		if(ableToMove(brd, x1, y1, x2, y2)) {
			Field fieldTemp = brd.getField(x2,y2);
			brd.moveStone(x1, y1, x2, y2);

//			if(brd.getField(p1).isPiece(0)){ // pawn to special piece
//				if((computer || computerVScomputer)){ // computer
//					if(y2 == 0 || y2 == 7){
//						int randomStone = (int)(Math.random()*(figuren.length() - 1) + 1);
//						brd.getField(p2).setString(figuren.charAt(randomStone)+ "" + brd.getField(p2).getColor());
//					}
//				} else { // player
//					if(y2 == 0) { choseStone = true; choseStoneX = x2; choseStoneY = y2; }
//					if(y2 == 7) { choseStone = true; choseStoneX = x2; choseStoneY = y2; }
//				}
//			}
			char moveChar = '0';
			if (fieldTemp.isEmpty()) moveChar = '-';
			else moveChar = 'x';


			String checkChar = "";
			if(isCheck(brd, 0) || isCheck(brd, 1)) checkChar = "+";

//			System.out.println(Stones.figuren.charAt(brd.getField(p2).getPiece()) + "" + Stones.ROW_K.charAt(x1) + "" + (8 - y1) + "" + moveChar + "" + fieldTemp.getPiece() + "" + Stones.ROW_K.charAt(x2) + "" + (8 - y2) + checkChar);

			if(fieldTemp.isKing()) System.out.println("#######################################");
			moved();
		}
	}

	public void Random(){
		Point[][][] p = generatePointArray();
		randomizePointArray(p);

		calculating = true;
		for (int i1 = 0; i1 < 8; i1++) {
			for (int j1 = 0; j1 < 8; j1++) {
				int x1 = (int) p[0][i1][j1].getX();
				int y1 = (int) p[0][i1][j1].getY();
				Point temp_point = new Point(x1,y1);
				if(brd.getField(x1, y1).isEmpty()) continue; // empty field
				for (int i2 = 0; i2 < 8; i2++) {
					for (int j2 = 0; j2 < 8; j2++) {
						int x2 = (int) p[1][i2][j2].getX();
						int y2 = (int) p[1][i2][j2].getY();
						Point temp_point2 = new Point(x1,y1);
						if(brd.isColorEqual(x1, y1, x2, y2)) continue; // equal color
						if(new Move(brd, x1, y1, x2, y2).isMoved()) {
							calculating = false;
							return;
						}
					}
				}
			}
		}
//		print(p);
		System.out.print("no move found");
		calculating = false;
	}

	public Point[][][] generatePointArray(){
		Point[][][] p = new Point[2][8][8];
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				p[0][i][j] = new Point(i,j);
				p[1][i][j] = new Point(i,j);
			}
		}
		return p;
	}

	public Point[][][] randomizePointArray(Point[][][] p){
		for (int i1 = 0; i1 < 8; i1++) {
			for (int j1 = 0; j1 < 8; j1++) {
				for (int i2 = 0; i2 < 8; i2++) {
					for (int j2 = 0; j2 < 8; j2++) {
						int n = 0;
						if(Math.random() >= 0.5){
							Point temp = p[n][i1][j1];
							p[n][i1][j1] = p[n][i2][j2];
							p[n][i2][j2] = temp;
						}
						n++;
						if(Math.random() >= 0.5){
							Point temp = p[n][i1][j1];
							p[n][i1][j1] = p[n][i2][j2];
							p[n][i2][j2] = temp;
						}
					}
				}
			}
		}
		return p;
	}

	public void moveRe() { // TODO undo move
//		int x1 = Stones.ROW_K.indexOf(log[player - 2].charAt(1));
//		int y1 = Integer.parseInt("" + log[player - 2].charAt(2));
//		int x2 = Stones.ROW_K.indexOf(log[player - 2].charAt(5));
//		int y2 = Integer.parseInt("" + log[player - 2].charAt(6));
//
//		char farbe = ' ';
////		if (player == 0)
////			farbe = charC[0];
////		else
////			farbe = charC[1];
////		brd.setField(new Point(x1,y1), new Field(log[player - 2].charAt(0) + "" + farbe));
////		if (getPlayer() == 0)
////			farbe = charC[1];
////		else
////			farbe = charC[0];
//		brd.setField(new Point(x2,y2), new Field(log[player - 2].charAt(4) + "" + farbe));
//		if (log[player - 2].charAt(4) == ' ')
//			brd.setField(new Point(x2,y2), new Field(null));
//		player--;

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

	public void moved(){
		moved = true;
	}
	public String[][] move(String[][] brd){
		return brd;
	}

	public boolean isMoved(){
		return moved;
	}
}
