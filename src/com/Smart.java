package com;

import com.elo.Elo;

import java.awt.*;
import java.util.Arrays;

public class Smart extends Move{
	final int id;
	int generation;
	double[] values_stones;
	double[] values_style;
	DoublePoint prefBoardMiddle;
	boolean calculating;
	Board brd;

	int posibleMoves[][] = new int[8][8];
	int white, black;
	int amount;
	int whiteMax, whiteMaxAmount;
	int maxGood = 0;
	int max;

	public Smart(int id, final float MAX_VALUES_DELTA) {
		this.id = id;
		double[] values_stones = new double[5];
		for (int i = 0; i < values_stones.length; i++) {
			values_stones[i] = (i + 1) + (Math.random() * (i + 1) * 2) / (MAX_VALUES_DELTA * 2);
		}
		double[] values_style = new double[3];
		for (int i = 0; i < values_style.length; i++) {
			values_style[i] = (Math.random() * 2) + 1;
		}
		DoublePoint prefBoardMiddle = new DoublePoint(Math.random() * 8, Math.random() * 8);

		System.out.println("Stats of Computer Nr. " + id);
		for (int i = 0; i < getRandomStats().length; i++) {
			System.out.println(Arrays.toString((double[]) getRandomStats()[i]));
		}
		((DoublePoint) getRandomStats()[2]).print();
		System.out.println();

		this.values_stones = values_stones;
		this.values_style = values_style;
		this.prefBoardMiddle = prefBoardMiddle;
		if(values_stones.length != 5) System.out.print("not enought values");
	}

	public void mutate() {
		generation++;
	}

	public Object[] getRandomStats(){
		return new Object[]{values_stones, values_style, prefBoardMiddle};
	}

	public double[] getValuesStones() { // return the values of the stones
		return values_stones;
	}

	public double[] getValuesStyle() {
		return values_style;
	}

	public double pointDelta(Point p1, Point p2){
		return Math.sqrt( Math.pow(p2.getX()-p1.getX(), 2) + Math.pow(p2.getY()-p1.getY(), 2));
	}

	public void checkForBestMove(){
		for(int x1 = 0; x1 < 8; x1++){
			for(int y1 = 0; y1 < 8; y1++){
//				if(brd.isEmpty(x1, y1)) continue; // || brd[x1][y1].charAt(1) == charC[0]) continue;
				for(int x2 = 0; x2 < 8; x2++){
					for(int y2 = 0; y2 < 8; y2++){
//						if(brd.isColorEqual(x1, y1,x2, y2)) continue;

//						while(tCount.getCount() > 8){}
//						if(!imWeg(x1, y1, x2, y2) && ableToMove(x1, y1, x2, y2)) {
//							new Move(x1, y1, x2, y2);
//							return;
//						}

						if(!imWegSupport(x1, y1, x2, y2)){
							canMoveCheck(brd, x1, y1, x2 , y2);
						}
					}
				}
			}
		}
		searchForBestMove();
	}
	
	public void searchForBestMove(){
		int random = (int)(Math.random() * whiteMaxAmount) + 1;
//		int random = (int)(Math.random() * max) + 1;
		int randCount = 1;

		Move move = new Move();
		
		final Count tCount = new Count();
		final Count count = new Count();
		for(int x1 = 0; x1 < 8; x1++){
			for(int y1 = 0; y1 < 8; y1++){
//				if(brd.getField(x1, y1).isEmpty()) continue;
				for(int x2 = 0; x2 < 8; x2++){
					for(int y2 = 0; y2 < 8; y2++){
						if(x1 == x2 && y1 == y2) continue;
//						if(brd.isColorEqual(x1, y1, x2, y2)) continue;

						if(!imWegSupport(x1, y1, x2, y2)){
							if(canMoveCheck(brd, x1, y1, x2, y2)){
								if(white == whiteMax){
									if(randCount == random){
										if(move.Normal(brd, x1, y1,  x2, y2)) {
											System.out.println("F 1");
											print();
//											moveC = true;
											return;
										}
									}
									randCount++;
								} else
								if(white >= maxGood) {
									if(randCount == random){
										if(move.Normal(brd, x1, y1,  x2, y2)){
											System.out.println("F 2");
											print();
//											moveC = true;
											return;
										}
									}
									randCount++;
								}
							}
						}

//						while(tCount.getCount() > 8){}
//
//						final Dim4 tempDim = new Dim4(i,j,i2,j2, randCount, random);
//
//						Thread thread = new Thread() {
//							public void run() {
//								tCount.increment();
//								if(!imWegSupport(tempDim.x1, tempDim.y1, tempDim.x2, tempDim.y2)){
//									if(canMoveCheck(tempDim.x1, tempDim.y1, tempDim.x2, tempDim.y2)){
//										if(white == whiteMax){
//											if(tempDim.randCount == tempDim.random){
//												new Move(tempDim.x1, tempDim.y1, tempDim.x2, tempDim.y2);
//												print();
//											}
//											tempDim.randCount++;
//										}
////										if(white >= maxGood) {
////											if(randCount == random){
////												move(i, j, i2, j2);
////												print();
////											}
////											randCount++;
////										}
//									}
//								}
//								tCount.decrement();
//							}
//						};
//						thread.start();
					}
				}
			}
		}
		System.out.println("not found");
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
	
	boolean imWegSupport(int x1, int y1, int x2, int y2) {
//		if(brd.getField(x1, y2).isKnight()) return false;
		
		int vx = new Logic(brd,x1,y1,x2,y2).vec(x1,x2);
		int vy = new Logic(brd,x1,y1,x2,y2).vec(y1,y2);

		for (int i = 1; i <= 7; i++) {
			try {
				if ((x1 + (vx*i)) == x2 && (y1 + (vy * i)) == y2) return false;
//				if (brd.getField((x1 + (vx * i)), (y1 + (vy * i))).isEmpty()) continue;
				else return true;
			} catch (Exception e) { }
		}
		return false;
	}
	
	public int support(int x2, int y2){
		int sup = 0;
		for(int x1 = 0; x1 < 8; x1++){
			for(int y1 = 0; y1 < 8; y1++){
				if(x1 == x2 && y1 == y2) continue;
//				if(brd.getField(x1, y1).isEmpty()) continue;
//				if(!brd.isColorEqual(x1, y1,  x2, y2)) continue;
				if(canMoveSupport(brd, x1, y1, x2, y2) && !imWegSupport(x1, y1, x2, y2))
					sup++;
			}
		}
		return sup;
	}
	
	public int enemy(int x2, int y2){
		int enemy = 0;
		for(int x1 = 0; x1 < 8; x1++){
			for(int y1 = 0; y1 < 8; y1++){
				if(x1 == x2 && y1 == y2) continue;
//				if(brd.getField(x1, y1).isEmpty()) continue;
//				if(brd.isColorEqual(x1, y1, x2, y2)) continue;
				if(canMoveSupport(brd, x1, y1, x2, y2) && !imWegSupport(x1, y1, x2, y2)) {
//					enemy += values_stones[brd.getField(x1, y1).getPiece().getValue()];

//					char ch = figuren.charAt(brd.getField(x1, y1).getPiece());
//					if(ch == figuren.charAt(0)) enemy-=1;
//					if(ch == figuren.charAt(1)) enemy-=2;
//					if(ch == figuren.charAt(2)) enemy-=3;
//					if(ch == figuren.charAt(3)) enemy-=4;
//					if(ch == figuren.charAt(4)) enemy-=5;
//					if(ch == figuren.charAt(5)) enemy-=6;
				}
			}
		}
		return enemy;
	}

	/**
	 * if can hit enemy += value of the stone
	 * @param x1
	 * @param y1
     * @return
     */
	public double enemyHit(int x1, int y1){
		double enemyHit = 0;
		for(int x2 = 0; x2 < 8; x2++){
			for(int y2 = 0; y2 < 8; y2++){
				if(x1 == x2 && y1 == y2) continue;
//				if(brd.getField( x2, y2).isEmpty()) continue;
//				if(brd.isColorEqual(x1, y1, x2, y2)) continue;
				if(canMoveSupport(brd, x1, y1, x2, y2) && !imWegSupport(x1, y1, x2, y2)) {
//					enemyHit += values_stones[brd.getField(x1, y1).getPiece().getValue()];

//					char ch = figuren.charAt(brd.getField(x1, y1).getPiece());
//					if(ch == figuren.charAt(0)) enemyHit+=6;
//					if(ch == figuren.charAt(1)) enemyHit+=5;
//					if(ch == figuren.charAt(2)) enemyHit+=4;
//					if(ch == figuren.charAt(3)) enemyHit+=3;
//					if(ch == figuren.charAt(4)) enemyHit+=2;
//					if(ch == figuren.charAt(5)) enemyHit+=1;
				}
			}
		}
		return enemyHit;
	}
	
	public boolean canMoveSupport(Board brd, int x1, int y1, int x2, int y2){
		if (!richtungSupport(brd, x1, y1, x2, y2)) return false;
		
//		if(brd.getField(x1, y1).getPiece() == 5) return true; //mal schauen ob das geht--!!

		if(new Logic(brd,x1,y1,x2,y2).ableToMove()){
			check();
			amount++;
			return true;
		}
		return false;
	}
	
	public boolean canMoveCheck(Board brd, int x1, int y1, int x2, int y2){
		if (!richtungSupport(brd, x1, y1, x2, y2)) return false;

		return new Logic(brd,x1,y1,x2,y2).ableToMove();
	}
	
	public boolean richtungSupport(Board brd, int x1, int y1, int x2, int y2){
//		char ch = figuren.charAt(brd.getField(x1, y1).getPiece());

		return new Logic(brd,x1,y1,x2,y2).moveViable();
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