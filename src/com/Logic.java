package com;

import java.awt.*;
import java.util.ArrayList;

public class Logic  {

	public boolean inTheWay(Board brd, int x1, int y1, int x2, int y2) {
		if(brd.getField(x1, y1).isKnight()) return false;

		int vx = vec(x1, x2); // direction of movement x
		int vy = vec(y1, y2); // direction of movement y

		for (int i = 1; i <= 6 + 1; i++) {
			try {
				if ((x1 + (vx * i)) == x2 && (y1 + (vy * i)) == y2) return false;
//				if (brd.getField((x1 + (vx * i)),(y1 + (vy * i))).isEmpty()) continue;
				if(brd.isEmpty(x1 + (vx * i),y1 + (vy * i))) continue;
				else return true;
			} catch (Exception e) {
				System.out.println("error in the way");
			}
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
	 * @param x1 koodinaties
	 * @param x2 koodinaties
     */
	public boolean direction(Board brd, int x1, int y1, int x2, int y2){
//		if(!brd.getField(x1, y1).isPlayer1()) return false; // checks if correct (color) piece is selected //TODO palyer
		if(inTheWay(brd, x1, y1, x2, y2)) return false;
//		if(brd[x1][y1].charAt(0) == figuren.charAt(5) && brd[x2][y2].charAt(0) == figuren.charAt(1)); //Rochade
		return moveViable(brd, x1, y1, x2, y2);
	}

	public boolean moveViable(Board brd, int x1, int y1, int x2, int y2) {
		switch (brd.getField(x1, y1).getPiece().getID()){
			case 0 : return pawnMove(brd, x1, y1, x2, y2); // pawn //TODO en passant
			case 1 : return horizontal(x1, y1, x2, y2); // rook
			case 2 : return knightsMove(x1, y1, x2, y2); // knight
			case 3 : return diagonal(x1, y1, x2, y2); // bishop
			case 4 : return diagonal(x1, y1, x2, y2) || (horizontal(x1, y1, x2, y2)); // queen
			case 5 : return kingMove(x1, y1, x2, y2); // king
			default : { System.out.println("wrong input piece " + brd.getField(x1, y1).getPiece()); } break;
		}
		return false;
	}

	public boolean horizontal(int x1, int y1, int x2, int y2){
		return (x1 == x2) || (y1 == y2);
	}

	public boolean diagonal(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) == Math.abs(y1 - y2);
	}

	public boolean oneStepHorizontal(int x1, int y1, int x2, int y2){
		return (Math.abs(x1 - x2) == 1) || (Math.abs(y1 - y2) == 1);
	}

	public boolean oneStepDiagonal(int x1, int y1, int x2, int y2){
		return (Math.abs(x1 - x2) == 1) && (Math.abs(y1 - y2) == 1);
	}

	public boolean knightsMove(int x1, int y1, int x2, int y2) {
		if(Math.abs(x1 - x2) == 1 && Math.abs(y1 - y2) == 2) return true;
		else if((Math.abs(x1 - x2) == 2 && Math.abs(y1 - y2) == 1)) return true;
		return false;
	}

	public boolean pawnMove(Board brd, int x1, int y1, int x2, int y2) {
		int temp = brd.getField(x1, y1).isPlayer1() ? 1 : -1; // black (1) or white (-1)
		if (brd.getField(x2, y2).isEmpty()){ // empty target field
			if((x1 - x2) == 0) { // step forward
				if((y2 - y1) * temp == 1) // one step
					return true;
				else if((y2 - y1) * temp == 2) // two steps
					if((y1 == 3.5 + (temp * -2.5))) // in beginning posistion white (6) black (1)
						return true;
			}
		} else { // target field
			if((Math.abs(x1 - x2) == 1)){ // one step diagonal
				if((y2 - y1) * temp == 1){ // one step
					System.out.println("123");
					return true;
				}
			}
		}
		return false;
	}

	public boolean kingMove(int x1, int y1, int x2, int y2){
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
			return (oneStepDiagonal(x1, y1, x2, y2) || oneStepHorizontal(x1, y1, x2, y2));
	}

	public boolean stalemate(Board brd) {
		int pieces = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if(pieces > 2) return false;
				if (!brd.getField(i, j).isEmpty()) pieces++;
			}
		}
		return true;
	}

	/**
	 * if can atleast move one piece return false
	 * else checkmate -> true
	 * @return
     */
	public boolean checkMate(Board brd, int player){
		for (int x1 = 0; x1 < 8; x1++) {
			for (int y1 = 0; y1 < 8; y1++) {
				if(brd.getField(x1, y1).isEmpty()) continue; // emtpy stone
				for (int x2 = 0; x2 < 8; x2++) {
					for (int y2 = 0; y2 < 8; y2++) {
						if(ableToMove(brd, x1, y1, x2, y2))
							return false;
					}
				}
			}
		}
		return true;
	}

	public boolean ableToMove(Board brd, int x1, int y1, int x2, int y2){
//		if(!brd.isWhiteTurn()) System.out.println("123123123123123123 "+ brd.isEmpty(x1, y1) + " " + x1 + " " + y1);
		if (brd.isEmpty(x1, y1)) return false; // if empty field
		for (Piece pc1: (ArrayList<Piece>) brd.player[brd.getPlayer()]) { // hit one pices?
			if (pc1.getX() == x2 && pc1.getY() == y2) return false;
		}
		if (!direction(brd, x1, y1, x2, y2)) return false; // move viable

		Board temp_brd = brd;
//		temp_brd.player = brd.player;
//		temp_brd.b
//		for (int i = 0; i < 8; i++) {
//			for (int j = 0; j < 8; j++) {
//				temp_brd.getField(i, j).placePiece(brd.getField(i, j).getPiece());
//			}
//		}
//		System.out.print("test 123");
		System.out.println(temp_brd.getPlayer());
		temp_brd.movePiece(x1, y1, x2, y2);
		if(!temp_brd.isWhiteTurn()) {
			System.out.println("+++++++");
			temp_brd.print();
		}
		return !isCheck(temp_brd, 0);
	}

	/**
	 * return true if 0 (black) or 1 (white) is in check
	 * @param p 0 (black) 1 (white)
	 * @return true -> check
	 */
	public boolean isCheck(Board brd, int p){
		for (int x2 = 0; x2 < 8; x2++) {
			for (int y2 = 0; y2 < 8; y2++) {
				if(brd.getField(x2, y2).isKing()) { // && brd.getField(x2, y2).isPlayer1()){
					for (int x1 = 0; x1 < 8; x1++) {
						for (int y1 = 0; y1 < 8; y1++) {
							if(brd.getField(x1, y1).isEmpty()) continue;
							if(!brd.isColorEqual(x1, y1, x2, y2)) {
								if (!inTheWay(brd, x1, y1, x2, y2)) {
									if(direction(brd, x1, y1, x2, y2))
										return true;
								}
							}
						}
					}
				}
				return false;
			}
		}
		return false;
	}
}
