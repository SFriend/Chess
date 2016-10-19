package com;

import java.util.ArrayList;

public class Logic {

	private int x1, y1, x2, y2;
	private Board brd;

	public Logic(Board brd,  int x1, int y1, int x2, int y2){
		this.brd = brd;
		this. x1 = x1;
		this. y1 = y1;
		this. x2 = x2;
		this. y2 = y2;
	}

	private boolean inTheWay() {
		if (brd.getPiece(x1, y1).getID() == 2) return false;
		int vx = vec(x1, x2); // direction of movement x
		int vy = vec(y1, y2); // direction of movement y
		for (int d = 1; d < 8; d++) {
			try {
				if ((x1 + (vx * d)) == x2 && (y1 + (vy * d)) == y2) return false;
				if (brd.isEmpty(x1 + (vx * d), y1 + (vy * d))) continue;
				else return true;
			} catch (Exception e) { System.out.println("out of array"); }
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
	
	public boolean moveViable() {
		if (inTheWay()) return false;
//		if(brd[x1][y1].charAt(0) == figuren.charAt(5) && brd[x2][y2].charAt(0) == figuren.charAt(1)); // TODO Rochade
//		for (int n = 0; n < 2; n++) {
//			for (Piece pc1 : brd.playerStones[n]) {
//				if(pc1.getX() == x1 && pc1.getY() == y1) {
						switch (brd.getPiece(x1,y1).getID()){
							case 0 : return pawnMove(); // pawn //TODO en passant
							case 1 : return rookMove(); // rook
							case 2 : return knightsMove(); // knight
							case 3 : return bishopMove(); // bishop
							case 4 : return queenMove(); // queen
							case 5 : return kingMove(); // king
							default : { System.out.println("wrong input piece " + brd.getPiece(x1,y1).getID()); } break;
						}

//					if (pc1 instanceof Pawn) return pawnMove(brd, x1, y1, x2, y2);
//					else if (pc1 instanceof Rook) return rookMove(x1, y1, x2, y2);
//					else if (pc1 instanceof Knight) return knightsMove(x1, y1, x2, y2);
//					else if (pc1 instanceof Bishop) return bishopMove(x1, y1, x2, y2);
//					else if (pc1 instanceof Queen) return bishopMove(x1, y1, x2, y2) || (rookMove(x1, y1, x2, y2));
//					else if (pc1 instanceof King) return kingMove(x1, y1, x2, y2);
//					else System.out.println("wrong input piece "); //+ brd.getField(x1, y1).getPiece());
					return false;
//				}
//			}
//		}
//		return false;
	}

	private boolean pawnMove() {
		int direction = brd.getPiece(x1,y1).getDirection(); // white (-1) or white (1)
		if (brd.isEmpty(x2,y2)){ // empty target field
			if((x1 - x2) == 0) { // step forward
				if((y2 - y1) == direction) { // one step
					return true;
				} else if((y2 - y1) == 2 * direction) { // two steps
					if ((y1 == 3.5 + (direction * -2.5))) { // in beginning position white (6) black (1)
						return true;
					}
				}
			}
		} else { // target field
			if((Math.abs(x1 - x2) == 1)) { // bishopMove
				if((y2 - y1) == direction) { // forward
					return true;
				}
			}
		}
		return false;
	}

	private boolean rookMove(){
		return (x1 == x2) || (y1 == y2);
	}

	private boolean knightsMove() {
		if(Math.abs(x1 - x2) == 1 && Math.abs(y1 - y2) == 2) return true;
		else if((Math.abs(x1 - x2) == 2 && Math.abs(y1 - y2) == 1)) return true;
		return false;
	}

	private boolean bishopMove() {
		return Math.abs(x1 - x2) == Math.abs(y1 - y2);
	}

	private boolean queenMove() {
		return bishopMove() || (rookMove());
	}

	private boolean kingMove() {
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
		return (Math.abs(x1 - x2) <= 1) && (Math.abs(y1 - y2) <= 1);
	}

	public boolean ableToMove() {
		if (!moveViable()) return false; // move viable

		Board temp_brd = brd.cloneBoard();//new Board(8); //brd.getWidth(),brd.getMove_count(),brd.getWhiteTurn(),brd.getBalance(), brd.getPlayerStones());

		temp_brd.movePiece(x1, y1, x2, y2);
//        System.out.println("------------------- " + temp_brd);
//        temp_brd.print();
//        System.out.println("+++++++++++++++++++ " + brd);
//        brd.print();
		return temp_brd.isCheck(); // !isCheck(temp_brd);
	}

	public boolean stalemate() {
		return (brd.getPlayerStones().get(0).size() + brd.getPlayerStones().get(1).size()) <= 2;
	}
}
