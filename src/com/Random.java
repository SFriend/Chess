package com;

import java.awt.*;

public class Random extends Logic {
	Logic logic = new Logic();
	final int maxThreads = 8;
	boolean calculating = false;

	public Random(Board brd) {
		Point[][][] p = generatePointArray();
		randomizePointArray(p);

		calculating = true;
		for (int i1 = 0; i1 < 8; i1++) {
			for (int j1 = 0; j1 < 8; j1++) {
				int x1 = (int) p[0][i1][j1].getX();
				int y1 = (int) p[0][i1][j1].getY();
				Point temp_point = new Point(x1,y1);
				if(brd.getField(x1, y1).isEmpty()) continue; // empty field
				for (int i2 = 0; i2 < 8; i2++) {
					for (int j2 = 0; j2 < 8; j2++) {
						int x2 = (int) p[1][i2][j2].getX();
						int y2 = (int) p[1][i2][j2].getY();
						Point temp_point2 = new Point(x1,y1);
						if(brd.isColorEqual(x1, y1, x2, y2)) continue; // equal color
//						if(new Move(brd, x1, y1, x2, y2).isMoved()) {
//							calculating = false;
//							return;
//						}
					}
				}
			}
		}
//		print(p);
		System.out.print("no move found");
		calculating = false;
	}

	public boolean isCalculating() {
		return calculating;
	}

	public Point[][][] generatePointArray(){
		Point[][][] p = new Point[2][8][8];
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				p[0][i][j] = new Point(i,j);
				p[1][i][j] = new Point(i,j);
			}
		}
		return p;
	}

	public Point[][][] randomizePointArray(Point[][][] p){
		for (int i1 = 0; i1 < 8; i1++) {
			for (int j1 = 0; j1 < 8; j1++) {
				for (int i2 = 0; i2 < 8; i2++) {
					for (int j2 = 0; j2 < 8; j2++) {
						int n = 0;
						if(Math.random() >= 0.5){
							Point temp = p[n][i1][j1];
							p[n][i1][j1] = p[n][i2][j2];
							p[n][i2][j2] = temp;
						}
						n++;
						if(Math.random() >= 0.5){
							Point temp = p[n][i1][j1];
							p[n][i1][j1] = p[n][i2][j2];
							p[n][i2][j2] = temp;
						}
					}
				}
			}
		}
		return p;
	}
	public void print(Point[][][] p){
		int count = 0;
		for (int n = 0; n < 2; n++){
			for (int i = 0; i < 8; i++){
				for (int j = 0; j < 8; j++){
					System.out.print(p[n][i][j].getLocation());
					if(p[0][i][j] == p[1][i][j]) count++;
				} System.out.println();
			} System.out.println();
		}  System.out.println("gleich " + count);
		count = 0;
		for (int i1 = 0; i1 < 8; i1++) {
			for (int j1 = 0; j1 < 8; j1++) {
				for (int i2 = 0; i2 < 8; i2++) {
					for (int j2 = 0; j2 < 8; j2++) {
						if(p[0][i1][j1].equals(p[1][i2][j2])) count++;
					}
				}
			}
		} System.out.println("== " + count);
	}
}

class Values{
	int max = 8;
	int min = 0;
	boolean found = false;
	
	int getMax(){
		return max;
	}
}