package com;

import java.awt.*;

public class Move {
	boolean calculating = false;
	private String nation = "abcdefgh";
	private String lastMove = "";
	public boolean Normal (Board brd, int x1, int y1, int x2, int y2) {
		if (new Logic(brd, x1, y1, x2, y2).canMove()) {
//			lastMove = "";
//			lastMove += brd.getPiece(x1,y1).getName().charAt(0);
//			lastMove += nation.charAt(x1) + "" + (y1 + 1); // move 1
//			lastMove += brd.isEmpty(x2, y2) ? "- " : "x";
//			for (Piece pc2 : brd.getPlayerStones().get(1 - brd.getPlayer())) { // piece p2
//				if (pc2.getX() == x2 && pc2.getY() == y2) {
//					lastMove += pc2.getName().charAt(0);
//				}
//			}
//			lastMove += nation.charAt(x2) + "" + (y2 + 1); // move 2
			brd.movePiece(x1, y1, x2, y2); // moves
			brd.incrementMoveCount();
//			if(isCheck(brd)) temp += "+";
//			brd.print();
//			System.out.println(temp);
			return true;
		}
		return false;
	}

	public boolean Random(Board brd) { // TODO random with arraylist -> picks one piece
		Point[][][] p = generatePointArray();
		randomizePointArray(p);
		calculating = true;
		for (int i1 = 0; i1 < 8; i1++) {
			for (int j1 = 0; j1 < 8; j1++) {
				int x1 = (int) p[0][i1][j1].getX();
				int y1 = (int) p[0][i1][j1].getY();
				for (Piece pc1 : brd.getPlayerStones()) {
					if (pc1.getX() == x1 && pc1.getY() == y1) {
						for (int i2 = 0; i2 < 8; i2++) {
							for (int j2 = 0; j2 < 8; j2++) {
								int x2 = (int) p[1][i2][j2].getX();
								int y2 = (int) p[1][i2][j2].getY();
								if (Normal(brd, x1, y1, x2, y2)) {
									calculating = false;
									return true;
								}
							}
						}
					}
				}
			}
		}
		System.out.print("checkmate");
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
}
