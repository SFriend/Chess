package com;

import java.awt.*;
import java.util.ArrayList;

public class Logic {

	private int x1, y1, x2, y2;
	Board brd;

	public Logic(Board brd,  int x1, int y1, int x2, int y2){
		this.brd = brd;
		this. x1 = x1;
		this. y1 = y1;
		this. x2 = x2;
		this. y2 = y2;
	}

	public boolean inTheWay() {
		for (Piece pc1 : brd.player[brd.getPlayer()]) {
			if(pc1.getX() == x1 && pc1.getY() == y1) {
				if (pc1.getID() == 2) {
//					System.out.println(pc1.getID() + " " + x2 + " " + y2);
					return false;
				}
			}
		}
		int vx = vec(x1, x2); // direction of movement x
		int vy = vec(y1, y2); // direction of movement y
		for (int i = 1; i < 8; i++) {
			try {
				if ((x1 + (vx * i)) == x2 && (y1 + (vy * i)) == y2) return false;
				if (brd.isEmpty(x1 + (vx * i), y1 + (vy * i))) continue;
				else return true;
			} catch (Exception e) { System.out.println("error in the way"); }
		}
		return false;
	}

	/**
	 * return direction of two numbers
	 * @param a
	 * @param b
     * @return
     */
	public int vec(int a, int b){
		if(a < b) return 1;
		else if(a > b) return -1;
		else return 0;
	}
	
	/**
	 * Checks if the move is viable
     */
	public boolean direction() {
		boolean f = false;
		for (Piece pc1 : brd.player[brd.getPlayer()]) {
			if (pc1.getX() == x1 && pc1.getY() == y1) f = true;
		}
		if(!f) return false;
		if(inTheWay()) return false;
//		if(brd.getPlayer() != (brd.getField(x1, y1).isPlayer1() ? 0:1)) return false; // checks if correct (color) piece is selected //TODO palyer
//		if(brd[x1][y1].charAt(0) == figuren.charAt(5) && brd[x2][y2].charAt(0) == figuren.charAt(1)); //Rochade
		return moveViable();
	}

	public boolean moveViable() {
		for (int n = 0; n < 2; n++) {
			for (Piece pc1 : brd.player[n]) {
				if(pc1.getX() == x1 && pc1.getY() == y1) {
					switch (pc1.getID()){
						case 0 : return pawnMove(); // pawn //TODO en passant
						case 1 : return rookMove(); // rook
						case 2 : return knightsMove(); // knight
						case 3 : return bishopMove(); // bishop
						case 4 : return queenMove(); // queen
						case 5 : return kingMove(); // king
						default : { System.out.println("wrong input piece " + brd.getField(x1, y1).getPiece()); } break;
					}

//					if (pc1 instanceof Pawn) return pawnMove(brd, x1, y1, x2, y2);
//					else if (pc1 instanceof Rook) return rookMove(x1, y1, x2, y2);
//					else if (pc1 instanceof Knight) return knightsMove(x1, y1, x2, y2);
//					else if (pc1 instanceof Bishop) return bishopMove(x1, y1, x2, y2);
//					else if (pc1 instanceof Queen) return bishopMove(x1, y1, x2, y2) || (rookMove(x1, y1, x2, y2));
//					else if (pc1 instanceof King) return kingMove(x1, y1, x2, y2);
//					else System.out.println("wrong input piece "); //+ brd.getField(x1, y1).getPiece());
					return false;
				}
			}
		}
		return false;
	}

	public boolean pawnMove() {
		int temp = brd.getField(x1, y1).isPlayer1() ? 1 : -1; // black (1) or white (-1)
		if (brd.getField(x2, y2).isEmpty()){ // empty target field
			if((x1 - x2) == 0) { // step forward
				if((y2 - y1) * temp == 1) // one step
					return true;
				else if((y2 - y1) * temp == 2) // two steps
					if((y1 == 3.5 + (temp * -2.5))) // in beginning position white (6) black (1)
						return true;
			}
		} else { // target field
			if((Math.abs(x1 - x2) == 1)){ // bishopMove
				if((y2 - y1) * temp == 1){ // forward
					return true;
				}
			}
		}
		return false;
	}

	public boolean rookMove(){
		return (x1 == x2) || (y1 == y2);
	}

	public boolean knightsMove() {
		if(Math.abs(x1 - x2) == 1 && Math.abs(y1 - y2) == 2) return true;
		else if((Math.abs(x1 - x2) == 2 && Math.abs(y1 - y2) == 1)) return true;
		return false;
	}

	public boolean bishopMove() {
		return Math.abs(x1 - x2) == Math.abs(y1 - y2);
	}

	public boolean queenMove() {
		return bishopMove() || (rookMove());
	}

	public boolean kingMove() {
//			if (moves[x1][y1] == 0 && moves[x2][y2] == 0 && brd.getField(p1).isPiece(1) && brd.isColorEqual(x1, y1, x2, y2)) { // Rochade
//				if (x1 > x2) {
//					if (isEmpty(brd[x1 - 1][y1]) && isEmpty(brd[x1 - 2][y1]) && isEmpty(brd[x1 - 3][y1])) {
//						System.out.println("O-O-O");
//						new Castling(x1, y1, x2, y2, 1);
//					}
//				} else if (x1 < x2){
//					if (isEmpty(brd[x1 + 1][y1]) && isEmpty(brd[x1 + 2][y1])) {
//						System.out.println("O-O");
//						new Castling(x1, y1, x2, y2, -1);
//					}
//				}
//			} else
			return (oneStepbishopMove() || oneSteprookMove());
	}

	public boolean oneSteprookMove() {
		return (Math.abs(x1 - x2) == 1) || (Math.abs(y1 - y2) == 1);
	}

	public boolean oneStepbishopMove() {
		return (Math.abs(x1 - x2) == 1) && (Math.abs(y1 - y2) == 1);
	}

	public boolean ableToMove() {
//		if(!brd.isWhiteTurn()) System.out.println("123123123123123123 "+ brd.isEmpty(x1, y1) + " " + x1 + " " + y1);
		if (brd.isEmpty(x1, y1)) return false; // if empty field // correct
		for (Piece pc1: brd.player[brd.getPlayer()]) { // hit one pices?
			if (pc1.getX() == x2 && pc1.getY() == y2) return false;
		}
		if (!direction()) return false; // move viable

		Board temp_brd = new Board(8);
		try {
			temp_brd = (Board) brd.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
//		temp_brd.clone(brd); //new Board(8);
//		for (int i = 0; i < 8; i++) {
//			for (int j = 0; j < 8; j++) {
//				temp_brd.getField(i, j).placePiece(brd.getField(i, j).getPiece());
//			}
//		}
        temp_brd.movePiece(x1, y1, x2, y2);
        System.out.println("##############################################");
        temp_brd.print();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
        brd.print();
		return !isCheck(temp_brd);
	}

	/**
	 * return true if 0 (black) or 1 (white) is in check
	 * @return true -> check
	 */
	public boolean isCheck(Board brd) {
        for (Piece pc2 : brd.player[brd.getPlayer()]) {
            if (pc2.getID() == 5) {
                for (Piece pc1 : brd.player[1-brd.getPlayer()]) {
					setXY(pc1.getX(), pc1.getY(), pc2.getX(), pc2.getY());
                    if (!inTheWay()) {
                        if(direction())
                            return true;
                    }
                }
            }
        }
        return false;
	}

	public boolean stalemate() {
		return (brd.player[0].size() + brd.player[1].size()) <= 2;
	}

	public void setXY(int x1, int y1, int x2, int y2){
		this. x1 = x1;
		this. y1 = y1;
		this. x2 = x2;
		this. y2 = y2;
	}
}
