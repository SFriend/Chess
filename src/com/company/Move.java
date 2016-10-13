package com.company;

import java.awt.*;

public class Move extends Logic {
	static boolean moved = false;

	/**
	 * moved = true if stones got moved
	 * @param brd
	 * @param p1
     * @param p2
     */
	public Move(Board brd, Point p1, Point p2) {
		int x1 = (int) p1.getX();
		int x2 = (int) p2.getX();
		int y1 = (int) p1.getY();
		int y2 = (int) p2.getY();
		if(ableToMove(brd, p1, p2)) {
			Field fieldTemp = brd.getField(p2);
			brd.moveStone(p1,p2);

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

			if(fieldTemp.getPiece() == 'k') System.out.println("#######################################");
			moved();
		}
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
