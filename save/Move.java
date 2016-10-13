package com.company;

import java.awt.*;

public class Move extends Logic{
	static boolean moved = false;
	public Move(Point p1, Point p2) {
		if(checkMate(brd, getPlayer())) return;
		int x1 = (int) p1.getX();
		int x2 = (int) p2.getX();
		int y1 = (int) p1.getY();
		int y2 = (int) p2.getY();
		if(ableToMove(brd, new Point(x1,y1), new Point(x2,y2), getPlayer())) {
			String fieldTemp = board[x2][y2];
			board[x2][y2] = board[x1][y1];
			board[x1][y1] = setEmpty();

			brd.moveStone(p1,p2);

			if(getPiece(board[x2][y2]) == figuren.charAt(0)){ // pawn to special piece
				if((computer || computerVScomputer)){ // computer
					if(y2 == 0 || y2 == 7){
						int randomStone = (int)(Math.random()*(4)+(1));
						board[x2][y2] = figuren.charAt(randomStone)+ "" + getColor(board[x2][y2]);
					}
				} else { // player
					if(y2 == 0) { choseStone = true; choseStoneX = x2; choseStoneY = y2; }
					if(y2 == 7) { choseStone = true; choseStoneX = x2; choseStoneY = y2; }
				}
			}
			moves[x2][y2] = moves[x1][y1] + 1;
			moves[x1][y1] = 0;

//			if(!choseStone)
			selection = false;

			char moveChar = '0';
			if (isEmpty(fieldTemp)) moveChar = '-';
			else moveChar = 'x';


			String checkChar = "";
			lastMoveChess = true;
			if(checkMate(board, (getPlayer()))) {
				game = false;
				checkChar = "++";
			}
			else if(isCheck(board, 0) || isCheck(board, 1)) checkChar = "+";
			else lastMoveChess = false;

			player++;

			letzterZugX1=x1;
			letzterZugX2=x2;
			letzterZugY1=y1;
			letzterZugY2=y2;

			if(isCheck(board, getPlayer())) System.out.println("++++++++true 1");
			if(isCheck(board, 1 - getPlayer()))System.out.println("++++++++true 2");
			System.out.println(getPiece(board[x2][y2]) + "" + ROW_K.charAt(x1) + "" + (8 - y1) + "" + moveChar + "" + getPiece(fieldTemp) + "" + ROW_K.charAt(x2) + "" + (8 - y2) + checkChar);

			letzterZug = getPiece(board[x2][y2]) + "" + ROW_K.charAt(x1) + "" + (8 - y1) + "" + moveChar + "" + getPiece(fieldTemp) + "" + ROW_K.charAt(x2) + "" + (8 - y2) + checkChar;
			if(getPiece(fieldTemp) == 'k') System.out.println("#######################################");

			log[player - 2] = letzterZug;
			mx1 = 0; my1 = 0; mx1 = 0; my1 = 0;

			moved = true;
		} else {
			moved = false;
		}
	}

	public String[][] move(String[][] brd){

		return brd;
	}
	public boolean isMoved(){
		return moved;
	}
}
