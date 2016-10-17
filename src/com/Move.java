package com;

import java.awt.*;
import java.util.ArrayList;

public class Move extends Logic {
	boolean calculating = false;

	String noation = "abcdefgh";
	/**
	 * moved = true if stones got moved
	 * @param brd
     */
	public boolean Normal (Board brd, int x1, int y1, int x2, int y2) {
		if(x1 == x2 && y1 == y2) return false; // move to same field
		if(ableToMove(brd, x1, y1, x2, y2)) {
			String temp = "" + brd.getField(x1, y1).getPiece().getName().charAt(0); // piece p1
			temp += noation.charAt(x1) + "" + (y1 + 1); // move 1
			temp += brd.getField(x2, y2).isEmpty() ? "- " : "x"+brd.getField(x2, y2).getPiece().getName().charAt(0); // piece p2
			temp += noation.charAt(x2) + "" + (y1 + 1); // move 2
			brd.movePiece(x1, y1, x2, y2); // moves
			brd.print();
			if(isCheck(brd)) temp += "+";
			System.out.println(temp);

			if(brd.getField(x2, y2).isPawn()){ // pawn to special piece
				if(y2 == 0 || y2 == 7){
					int randomStone = (int)(Math.random() * (5 - 1) + 1);
//					brd.setField(x2, y2, new Field(x2, y2).addPiece(null)));
//					brd.getField(p2).setString(figuren.charAt(randomStone)+ "" + brd.getField(p2).getColor());
				}
			}
			brd.incrementMoveCount();
			return true;
		}
		return false;
	}

	public boolean Random(Board brd){
		Point[][][] p = generatePointArray();
		randomizePointArray(p);
		calculating = true;
		for (int i1 = 0; i1 < 8; i1++) {
			for (int j1 = 0; j1 < 8; j1++) {
				int x1 = (int) p[0][i1][j1].getX();
				int y1 = (int) p[0][i1][j1].getY();
				for (Piece pc1 : (ArrayList<Piece>) brd.player[brd.getPlayer()]) {
					if (pc1.getX() == x1 && pc1.getY() == y1) {
						for (int i2 = 0; i2 < 8; i2++) {
							for (int j2 = 0; j2 < 8; j2++) {
//								System.out.print("found 1");

								int x2 = (int) p[1][i2][j2].getX();
								int y2 = (int) p[1][i2][j2].getY();
								if (Normal(brd, x1, y1, x2, y2)) {
									calculating = false;
									System.out.println("found " + x1 + " " + y1 + " " + x2 + " " + y2);
									return true;
								}
							}
						}
					}
				}
			}
		}
		System.out.print("no move found");
		calculating = false;
		return false;
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

	public String[][] move(String[][] brd){
		return brd;
	}
}
