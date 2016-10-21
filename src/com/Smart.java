package com;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Smart extends Move{
	final int id;
	int generation;
	double[] values_stones;
	double[] values_style;
	DoublePoint prefBoardMiddle;
	Board brd;

	int posibleMoves[][] = new int[8][8];
	int white, black;
	int amount;
	int whiteMax, whiteMaxAmount;
	int maxGood = 0;
	int max;

	public Smart(int id, final float MAX_VALUES_DELTA) {
		this.id = id;
		double[] values_stones = new double[6];
		for (int i = 0; i < values_stones.length; i++) {
//			values_stones[i] = (i + 1) + (Math.random() * (i + 1) * 2) / (MAX_VALUES_DELTA * 2); // old 1 2 3 4 5
			values_stones[i] = (6) + (Math.random() * (6) * 2) / (MAX_VALUES_DELTA * 2);
		}
		double[] values_style = new double[3];
		for (int i = 0; i < values_style.length; i++) {
			values_style[i] = (Math.random() * 2) + 1;
		}
		DoublePoint prefBoardMiddle = new DoublePoint(Math.random() * 8, Math.random() * 8);

//		System.out.println("Stats of Computer Nr. " + id);
//		for (int i = 0; i < getRandomStats().length; i++) {
//			System.out.println(Arrays.toString((double[]) getRandomStats()[i]));
//		}
//		((DoublePoint) getRandomStats()[2]).print();
//		System.out.println();

		this.values_stones = values_stones;
		this.values_style = values_style;
		this.prefBoardMiddle = prefBoardMiddle;
		if(values_stones.length != 6) System.out.print("not enought values");
	}

	public void mutate() {
		generation++;
	}

	public Object[] getRandomStats(){
		return new Object[] {values_stones, values_style, prefBoardMiddle};
	}

	public double[] getValuesStones() { // return the values of the stones
		return values_stones;
	}

	public double[] getValuesStyle() {
		return values_style;
	}

	public double pointDelta(Point p1, Point p2) {
		return Math.sqrt(Math.pow(p2.getX()-p1.getX(), 2) + Math.pow(p2.getY()-p1.getY(), 2));
	}

	public Board SmartMove(Board brd) {
		ArrayList<Board> possibleBoards = new ArrayList<>();
		for (Piece pc1: brd.playerStones.get(brd.getPlayer())) {
			for(int x2 = 0; x2 < 8; x2++) {
				for (int y2 = 0; y2 < 8; y2++) {
					if (new Logic(brd, pc1.getX(), pc1.getY(), x2, y2).canMove()) {
						Board clone = brd.cloneBoard();
						new Move().Normal(clone, pc1.getX(), pc1.getY(), x2, y2);
						possibleBoards.add(clone);
					}
				}
			}
		}
		brd = findBestMove(possibleBoards);
//		brd.incrementMoveCount();
		return brd;
	}

	public Board findBestMove(ArrayList<Board> possibleBoards) {
		ArrayList<Board> bestValue = new ArrayList<>();
		bestValue.add(possibleBoards.get(0));
		for (Board equals: possibleBoards) {
			if (equals.boardValue(values_stones) > bestValue.get(0).boardValue(values_stones)) {
				bestValue.clear();
				bestValue.add(equals);
			} else if (equals.boardValue(values_stones) == bestValue.get(0).boardValue(values_stones)) {
				bestValue.add(equals);
			}
		}
		Board best = bestValue.get(0);
		for (Board equals: bestValue) {
			if (equals.getMiddleDelta(prefBoardMiddle) < best.getMiddleDelta(prefBoardMiddle)) {
				best = equals;
			}
		}
		return best;
	}


	public void print(){
		for(int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(posibleMoves[j][i] + " ");
			}
			System.out.println();
		}
		System.out.println(white);
	}
	
	public void searchForMax(){
		black = 0;
		white = 0;
		for(int x1 = 0; x1 < 8; x1++){
			for(int y1 = 0; y1 < 8; y1++){
				for (int n = 0; n < 2; n++) {
					for (Piece pc: brd.getPlayerStones().get(n)) {
						if(pc.getX() == x1 && pc.getY() == y1) {
							if (n == 0) white += posibleMoves[x1][y1];
							else black += posibleMoves[x1][y1];
						}
					}
				}
			}
		}
		if(white >= maxGood) max++;
		if(white > whiteMax) {whiteMax = white; whiteMaxAmount = 1;}
		else if(white == whiteMax) whiteMaxAmount++;
	}
	
	public void check(){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				posibleMoves[i][j] = 0;
			}
		}
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
//				if(brd.getField(i, j).isEmpty()) continue;
//				posibleMoves[i][j] = support(i, j) + enemy(i, j) + enemyHit(i, j);
			}
		}
		searchForMax();
	}
}

class Count {
	int c;
	public void increment(){
		this.c++;
	}
	public void decrement(){
		this.c--;
	}
	int getCount(){
		return this.c;
	}
}