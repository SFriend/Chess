package com.company;

import java.awt.*;

public class Logic extends Piece {

	public boolean inTheWay(Board brd, Point p1, Point p2) {
		int x1 = (int) p1.getX(), x2 = (int) p2.getX();
		int y1 = (int) p1.getY(), y2 = (int) p2.getY();

		if(brd.getField(p1).isKnight()) return false;

		int vx = vec(x1,x2);
		int vy = vec(y1,y2);

		for (int i = 1; i <= 6 + 1; i++) {
			try {
				if ((x1 + (vx * i)) == x2 && (y1 + (vy * i)) == y2) return false;
				if (brd.getField(new Point(x1 + (vx * i), y1 + (vy * i))).isEmpty()) continue;
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
	 * @param p1 koodinaties
	 * @param p2 koodinaties
     */
	public boolean direction(Board brd, Point p1, Point p2){
		System.out.println(brd.getField(p1).getString() + " " + brd.getField(p1).isEmpty());
		if(brd.getField(p1).isEmpty()) return  false;
		if(brd.getField(p1).isColor(getPlayer())) return false; // checks if correct (color) piece is selected
		if(inTheWay(brd, p1, p2)) return false;
//		if(brd[x1][y1].charAt(0) == figuren.charAt(5) && brd[x2][y2].charAt(0) == figuren.charAt(1)); //Rochade
		return moveViable(brd, p1, p2);
	}

	public boolean moveViable(Board brd, Point p1, Point p2) {
		switch (brd.getField(p1).getPiece()){
			case 0 : return pawnMove(brd, p1, p2); // pawn //TODO en passant
			case 1 : return horizontal(p1, p2); // rook
			case 2 : return knightsMove(p1, p2); // knight
			case 3 : return diagonal(p1, p2); // bishop
			case 4 : return diagonal(p1, p2) || (horizontal(p1, p2)); // queen
			case 5 : return kingMove(p1, p2); // king
			default : { System.out.println("wrong input piece " + brd.getField(p1).getPiece()); } break;
		}
		return false;
	}

	public boolean horizontal(Point p1, Point p2){
		return (p1.getX() == p2.getX()) || (p1.getY() == p2.getY());
	}

	public boolean diagonal(Point p1, Point p2) {
		int x1 = (int) p1.getX();
		int x2 = (int) p2.getX();
		int y1 = (int) p1.getY();
		int y2 = (int) p2.getY();
		return Math.abs(x1 - x2) == Math.abs(y1 - y2);
	}

	public boolean oneStepHorizontal(Point p1, Point p2){
		int x1 = (int) p1.getX();
		int x2 = (int) p2.getX();
		int y1 = (int) p1.getY();
		int y2 = (int) p2.getY();
		return (Math.abs(x1 - x2) == 1) || (Math.abs(y1 - y2) == 1);
	}

	public boolean oneStepDiagonal(Point p1, Point p2){
		int x1 = (int) p1.getX();
		int x2 = (int) p2.getX();
		int y1 = (int) p1.getY();
		int y2 = (int) p2.getY();
		return (Math.abs(x1 - x2) == 1) && (Math.abs(y1 - y2) == 1);
	}

	public boolean knightsMove(Point p1, Point p2) {
		int x1 = (int) p1.getX();
		int x2 = (int) p2.getX();
		int y1 = (int) p1.getY();
		int y2 = (int) p2.getY();
		if(Math.abs(x1 - x2) == 1 && Math.abs(y1 - y2) == 2) return true;
		else if((Math.abs(x1 - x2) == 2 && Math.abs(y1 - y2) == 1)) return true;
		return false;
	}

	public boolean pawnMove(Board brd, Point p1, Point p2) {
		int x1 = (int) p1.getX();
		int x2 = (int) p2.getX();
		int y1 = (int) p1.getY();
		int y2 = (int) p2.getY();
		int temp = brd.getField(p1).isColor(0) ? 1 : -1; // black (1) or white (-1)
		if (brd.getField(p2).isEmpty()){ // empty target field
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
					return true;
				}
			}
		}
		return false;
	}

	public boolean kingMove(Point p1, Point p2){
//			if (moves[x1][y1] == 0 && moves[x2][y2] == 0 && brd.getField(p1).isPiece(1) && brd.isColorEqual(p1, p2)) { // Rochade
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
			return (oneStepDiagonal(p1, p2) || oneStepHorizontal(p1, p2));
	}

	public boolean stalemate(Board brd) {
		int pieces = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if(pieces > 2) return false;
				if (!brd.getField(new Point(i,j)).isEmpty()) pieces++;
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
				if(brd.getField(new Point(x1,y1)).isEmpty()) continue; // emtpy stone
				for (int x2 = 0; x2 < 8; x2++) {
					for (int y2 = 0; y2 < 8; y2++) {
						if(ableToMove(brd, new Point(x1, y1), new Point(x2, y2)))
							return false;
					}
				}
			}
		}
		return true;
	}

	public boolean ableToMove(Board brd, Point p1, Point p2){
		if(brd.isColorEqual(p1,p2)) return false;
		if (!direction(brd, p1, p2)) return false;
		Board temp_brd = new Board(8,8);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				temp_brd.setField(new Point(i,j), brd.getField(new Point(i,j)));
			}
		}
		brd.moveStone(p1,p2);

		return !isCheck(temp_brd, getPlayer());
	}

	/**
	 * return true if 0 (black) or 1 (white) is in check
	 * @param p 0 (black) 1 (white)
	 * @return true -> check
	 */
	public boolean isCheck(Board brd, int p){
		for (int x2 = 0; x2 < 8; x2++) {
			for (int y2 = 0; y2 < 8; y2++) {
				if(brd.getField(new Point(x2,y2)).isPiece(5) && brd.getField(new Point(x2,y2)).isColor(p)){
					for (int x1 = 0; x1 < 8; x1++) {
						for (int y1 = 0; y1 < 8; y1++) {
							if(brd.getField(new Point(x1,y1)).isEmpty()) continue;
							if(!brd.isColorEqual(new Point(x1,y1),new Point(x2,y2))) {
								if (!inTheWay(brd, new Point(x1,y1), new Point(x2,y2))) {
									if(direction(brd, new Point(x1,y1), new Point(x2,y2)))
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
