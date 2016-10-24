package com;

import java.awt.*;
import java.util.ArrayList;

public class Smart extends Move{
	private double[] values_stones = new double[6];
	private double[] values_style = new double[3];
	private DoublePoint prefBoardMiddle;

	public Smart() {
		int scale = 10;
		fillRandom(values_stones, scale);
		fillRandom(values_style, scale);
		this.prefBoardMiddle = new DoublePoint(Math.random() * 8, Math.random() * 8);
	}

	public Smart clone() {
		Smart clone = new Smart();
		for (int i = 0; i < getValuesStones().length; i++)
			clone.values_stones[i] = getValuesStones()[i];
		for (int i = 0; i < getValuesStyle().length; i++)
			clone.values_style[i] = getValuesStyle()[i];
		clone.prefBoardMiddle = getPrefBoardMiddle().clone();
		return clone;
	}

	public boolean SmartMove(Board brd) {
//		System.out.println((brd.getPlayerStones().size() * 13) / 16.0);
		if (brd.getPlayerStones().size() <
				(brd.getStones().get(1-brd.getPlayer()).size() * 13) / 16.0) {
//			System.out.println("won");
			return false;
		}
//		if(brd.getMove_count() > 20 && brd.getMove_count() < 32) { // if loses one piece every turn
//			if (brd.getPlayerStones().size() < 16-(brd.getMove_count()/2)) return false;
//		}
		boolean moved = false;
		ArrayList<Board> possibleBoards = new ArrayList<>();
		for (Piece pc1: brd.getPlayerStones()) {
			for(int x2 = 0; x2 < 8; x2++) {
				for (int y2 = 0; y2 < 8; y2++) {
					if (new Logic(brd, pc1.getX(), pc1.getY(), x2, y2).canMove()) {
						moved = true;
						Board clone = brd.cloneBoard();
						new Move().Normal(clone, pc1.getX(), pc1.getY(), x2, y2);
						possibleBoards.add(clone);
					}
				}
			}
		}
		if (!moved) return false;
		brd.findBestMove(possibleBoards, values_style, values_stones, prefBoardMiddle);
		return true;
	}

	public void mutate() {
		mutateArray(values_stones, 10);
		mutateArray(values_style, 10);
		prefBoardMiddle.setX(prefBoardMiddle.getX() + (Math.random() * prefBoardMiddle.getX() / 5) - prefBoardMiddle.getX() / 10);
		prefBoardMiddle.setY(prefBoardMiddle.getY() + (Math.random() * prefBoardMiddle.getY() / 5) - prefBoardMiddle.getY() / 10);
	}

	public void fillRandom(double[] array, double scale) {
		for (int i = 0; i < array.length; i++) {
			array[i] = Math.random() * (scale - 1) + 1;
		}
	}

	public void mutateArray(double[] array, double scale) {
		for (int i = 0; i < array.length; i++) {
			array[i] += (Math.random() * array[i] / (scale / 2)) - (array[i] / scale);
		}
	}

	public double[] getValuesStones() { // return the values of the stones
		return values_stones;
	}

	public double[] getValuesStyle() {
		return values_style;
	}

	public DoublePoint getPrefBoardMiddle() {
		return prefBoardMiddle;
	}

	public double pointDelta(Point p1, Point p2) {
		return Math.sqrt(Math.pow(p2.getX()-p1.getX(), 2) + Math.pow(p2.getY()-p1.getY(), 2));
	}

	public void printStats() {
		System.out.println("Computer Stats");
		printArray(values_stones);
		printArray(values_style);
		prefBoardMiddle.print();
	}

	public void printArray(double[] array) {
		System.out.print("[");
		for (int i = 0; i < array.length; i++) {
			System.out.print((int)(array[i] * 100) / 100.0);
			if (i < array.length-1) System.out.print(" : ");
		} System.out.println("]");
	}
}