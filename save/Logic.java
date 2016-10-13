package com.company;

import java.awt.*;

public class Logic extends Main {

	public boolean inTheWay(Point p1, Point p2) {
		int x1 = (int) p1.getX();
		int x2 = (int) p2.getX();
		int y1 = (int) p1.getY();
		int y2 = (int) p2.getY();
		if(getPiece(board[x1][y1]) == figuren.charAt(2)) return false;
		int vx = 0, vy = 0;
		if(x1<x2) vx =1;
		else if(x1>x2) vx =-1;
		else if(x1==x2) vx = 0;
		
		if(y1 < y2) vy =1;
		else if(y1 > y2) vy =-1;
		else if(y1 == y2) vy = 0;
		for (int i = 1; i <= 6 + 1; i++) {
			try {
				if ((x1 + (vx*i)) == x2 && (y1 + (vy * i)) == y2) return false;
				if (isEmpty(board[x1 + (vx*i)][y1 + (vy * i)])) continue;
				else return true;
			} catch (Exception e) {
//				System.out.println("error in the way");
			}
		}
		return false;
	}
	
	/**
	 * Checks if the move is viable
	 * @param p1 koodinaties
	 * @param p2 koodinaties
     */
	public boolean direction(String[][] board, Point p1, Point p2){
		int x1 = (int) p1.getX();
		int x2 = (int) p2.getX();
		int y1 = (int) p1.getY();
		int y2 = (int) p2.getY();
		if(isEmpty(board[x1][y1])) return  false;
		if(getPlayer() == (getColor(board[x1][y1]) == charC[0] ? 1 : 0)) return false; // checks if right (color) piece is selected
		if(inTheWay(p1, p2)) return false;
//		if(board[x1][y1].charAt(0) == figuren.charAt(5) && board[x2][y2].charAt(0) == figuren.charAt(1)); //Rochade
		return moveViable(board, p1, p2);
	}

	public boolean moveViable(String[][] board, Point p1, Point p2) {
		int x1 = (int) p1.getX();
		int x2 = (int) p2.getX();
		int y1 = (int) p1.getY();
		int y2 = (int) p2.getY();
		switch (figuren.indexOf(getPiece(board[x1][y1]))){
			case 0 : if (pawnMove(p1, p2)) return true; break; // pawn //TODO en passant
			case 1 : if (horizontal(p1, p2)) return true; break; // rook
			case 2 : if (knightsMove(p1, p2)) return true; break; // knight
			case 3 : if (diagonal(p1, p2)) return true; break; // bishop
			case 4 : if (diagonal(p1, p2) || (horizontal(p1, p2))) return true; break; // queen
			case 5 : // king
				if (moves[x1][y1] == 0 && moves[x2][y2] == 0 && getPiece(board[x2][y2]) == figuren.charAt(1) && getColor(board[x1][y1]) == getColor(board[x2][y2])) { // Rochade
					if (x1 > x2) {
						if (isEmpty(board[x1 - 1][y1]) && isEmpty(board[x1 - 2][y1]) && isEmpty(board[x1 - 3][y1])) {
							System.out.println("O-O-O");
							new Castling(x1, y1, x2, y2, 1);
						}
					} else if (x1 < x2){
						if (isEmpty(board[x1 + 1][y1]) && isEmpty(board[x1 + 2][y1])) {
							System.out.println("O-O");
							new Castling(x1, y1, x2, y2, -1);
						}
					}
				} else if (oneStepDiagonal(p1, p2) || oneStepHorizontal(p1, p2)) return true;
				break;
			default : { System.out.println("wrong input piece " + figuren.indexOf(getPiece(board[x1][y1]))); } break;
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

	public boolean pawnMove(Point p1, Point p2) {
		int x1 = (int) p1.getX();
		int x2 = (int) p2.getX();
		int y1 = (int) p1.getY();
		int y2 = (int) p2.getY();
		int temp = getColor(board[x1][y1]) == charC[0] ? 1 : -1; // black (1) or white (-1)
		if (isEmpty(board[x2][y2])){ // empty target field
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

	public boolean finish2() {
		if (timeP1.finish() || timeP2.finish()) {
			player++;
			return false;
		}
		if(checkMate(board,0) || checkMate(board,1)) {
			System.out.println("mate");
			return true;
		}
		if(!isCheck(board, (player%2)) && checkMate(board,0))
			return true;
		int pieces = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (!isEmpty(board[i][j])) {
					pieces++;
				}
			}
		}
		if (pieces == 2) { // stalemate (draw)
			System.out.println("stalemate");
			return true;
		}
		return checkMate(board, 0) || checkMate(board, 1);
	}

	/**
	 * if can atleast move one piece return false
	 * else checkmate -> true
	 * @return
     */
	public boolean checkMate(String[][] board, int player){
		for (int x1 = 0; x1 < 8; x1++) {
			for (int y1 = 0; y1 < 8; y1++) {
				if(isEmpty(board[x1][y1])) continue; // emtpy stone
				for (int x2 = 0; x2 < 8; x2++) {
					for (int y2 = 0; y2 < 8; y2++) {
						if(ableToMove(board, new Point(x1, y1), new Point(x2, y2), player))
							return false;
					}
				}
			}
		}
		game = false;
		return true;
	}

	public boolean ableToMove(String[][] board, Point p1, Point p2, int player){
		int x1 = (int) p1.getX();
		int x2 = (int) p2.getX();
		int y1 = (int) p1.getY();
		int y2 = (int) p2.getY();

		if(getColor(board[x1][y1]) == getColor(board[x2][y2])) return false;

		if (!direction(board, p1, p2)) return false;

		String[][] temp_board = new String[8][8];
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				temp_board[i][j] = board[i][j];
			}
		}

		temp_board[x2][y2] = temp_board[x1][y1];
		temp_board[x1][y1] = setEmpty();

		return !isCheck(temp_board, getPlayer());
	}

	/**
	 * return true if 0 (black) or 1 (white) is in check
	 * @param p 0 (black) 1 (white)
	 * @return true -> check
	 */
	public boolean isCheck(String[][] board, int p){
		for (int x2 = 0; x2 < 8; x2++) {
			for (int y2 = 0; y2 < 8; y2++) {
				if(getPiece(board[x2][y2]) == figuren.charAt(5) && getColor(board[x2][y2]) == charC[p]){
					for (int x1 = 0; x1 < 8; x1++) {
						for (int y1 = 0; y1 < 8; y1++) {
							if(isEmpty(board[x1][y1])) continue;
							if(getColor(board[x1][y1]) != getColor(board[x2][y2])) {
								if (!inTheWay(new Point(x1,y1), new Point(x2,y2))) {
									if(direction(board, new Point(x1,y1), new Point(x2,y2)))
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
