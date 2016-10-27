package com;

import java.util.ArrayList;

public class Logic {

	private int x2, y2;
	private Piece pc;
	private Board brd;

	public Logic(Board brd, Piece pc, int x2, int y2){
		this.brd = brd;
		this.x2 = x2;
		this.y2 = y2;
		this.pc = pc;
	}

	public boolean canMove() {
//		if (brd.isEmpty(x1, y1)) return false; // empty field picked
		if (pc.isWhite() != brd.getWhiteTurn()) return false;
		if (brd.isColorEqual(pc, x2, y2)) return false; // same team
		if (pc.getX() == x2 && pc.getY() == y2) return false; // move to same field
//		System.out.println(brd.getPiece(x1,y1).isWhite() != (brd.getPlayer()==0));
		return ableToMove();
	}

	public boolean ableToMove() {
		if (!pc.ableToMove(brd, x2, y2)) return false;
		Board temp_brd = brd.cloneBoard();
		temp_brd.movePiece(pc.getX(), pc.getY(), x2, y2); // .getX(), pc.getY()
		return !isCheck(temp_brd);
	}

	public boolean isCheck(Board brd) {
		for (Piece pc2 : brd.getPlayerStones()) {
			if (pc2.getID() == 5) { // Kings
				for (Piece pc1 : brd.getStones().get(1-brd.getPlayer())) {
					if(pc1.ableToMove(brd, pc2.getX(), pc2.getY())) return true;
				}
				break;
			}
		}
		return false;
	}
}
