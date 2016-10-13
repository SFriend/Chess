package com.company;

import java.awt.*;

public class Smart extends Computer {
	private static int posibleMoves[][] = new int[8][8];
	private static int white, black;
	static int amount;
	private static int whiteMax, whiteMaxAmount;
	private static final int maxGood = 0;
	static int max;
	
	void checkForBestMove(){
		for(int x1 = 0; x1 < 8; x1++){
			for(int y1 = 0; y1 < 8; y1++){
				if(isEmpty(board[x1][y1])) continue; // || board[x1][y1].charAt(1) == charC[0]) continue;
				for(int x2 = 0; x2 < 8; x2++){
					for(int y2 = 0; y2 < 8; y2++){
						if(x1 == x2 && y1 == y2) continue;
						if(board[x1][y1].charAt(1) == board[x2][y2].charAt(1)) continue;

//						while(tCount.getCount() > 8){}
//						if(!imWeg(x1, y1, x2, y2) && ableToMove(x1, y1, x2, y2)) {
//							new Move(x1, y1, x2, y2);
//							return;
//						}

//						System.out.println(x1 + " " + y1 + " " + x2 + " " + y2 + " " + ableToMove(x1, y1, x2, y2) + " " + !imWeg(x1, y1, x2, y2));
						if(!imWegSupport(x1, y1, x2, y2)){
							canMoveCheck(board, x1, y1, x2 , y2);
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
		
		final Count tCount = new Count();
		final Count count = new Count();
		for(int x1 = 0; x1 < 8; x1++){
			for(int y1 = 0; y1 < 8; y1++){
				if(isEmpty(board[x1][y1])) continue;
				for(int x2 = 0; x2 < 8; x2++){
					for(int y2 = 0; y2 < 8; y2++){
						if(x1 == x2 && y1 == y2) continue;
						if(board[x1][y1].charAt(1) == board[x2][y2].charAt(1)) continue;

						if(!imWegSupport(x1, y1, x2, y2)){
							if(canMoveCheck(board, x1, y1, x2, y2)){
								if(white == whiteMax){
									if(randCount == random){
										if(new Move(new Point(x1,y1), new Point(x2,y2)).isMoved()) {
											System.out.println("F 1");
											print();
											moveC = true;
											return;
										}
									}
									randCount++;
								} else
								if(white >= maxGood) {
									if(randCount == random){
										if(new Move(new Point(x1,y1), new Point(x2,y2)).isMoved()){
											System.out.println("F 2");
											print();
											moveC = true;
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
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(board[i][j].charAt(1)==charC[1]) {
					white += posibleMoves[i][j];
				}
				if(board[i][j].charAt(1)==charC[0]) {
					black += posibleMoves[i][j];
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
				if(isEmpty(board[i][j])) continue;
				posibleMoves[i][j] = support(i, j) + enemy(i, j) + enemyHit(i, j);
			}
		}
		searchForMax();
	}
	
	boolean imWegSupport(int x1, int y1, int x2, int y2) {
		if(board[x1][y1].charAt(0) == figuren.charAt(2)) return false;
		
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
			}
		}
		return false;
	}
	
	public int support(int x2, int y2){
		int sup = 0;
		for(int x1 = 0; x1 < 8; x1++){
			for(int y1 = 0; y1 < 8; y1++){
				if(x1==x2 && y1 == y2) continue;
				if(isEmpty(board[x1][y1])) continue;
				if(board[x1][y1].charAt(1) != board[x2][y2].charAt(1)) continue;
				if(canMoveSupport(board, x1, y1, x2, y2) && !imWegSupport(x1, y1, x2, y2) && board[x1][y1].charAt(1) == board[x2][y2].charAt(1))
					sup++;
			}
		}
		return sup;
	}
	
	public int enemy(int x2, int y2){
		int enemy = 0;
		for(int x1 = 0; x1 < 8; x1++){
			for(int y1 = 0; y1 < 8; y1++){
				if(x1==x2 && y1 == y2) continue;
				if(isEmpty(board[x1][y1])) continue;
				if(board[x1][y1].charAt(1) == board[x2][y2].charAt(1)) continue;
				if(canMoveSupport(board, x1, y1, x2, y2) && !imWegSupport(x1, y1, x2, y2) && board[x1][y1].charAt(1) != board[x2][y2].charAt(1)) {
					char ch = board[x1][y1].charAt(0);
					if(ch == figuren.charAt(0)) enemy-=1;
					if(ch == figuren.charAt(1)) enemy-=2;
					if(ch == figuren.charAt(2)) enemy-=3;
					if(ch == figuren.charAt(3)) enemy-=4;
					if(ch == figuren.charAt(4)) enemy-=5;
					if(ch == figuren.charAt(5)) enemy-=6;
				}
			}
		}
		return enemy;
	}
	
	public int enemyHit(int x1, int y1){
		int enemyHit = 0;
		for(int x2 = 0; x2 < 8; x2++){
			for(int y2 = 0; y2 < 8; y2++){
				if(x1==x2 && y1 == y2) continue;
				if(isEmpty(board[x2][y2])) continue;
				if(board[x1][y1].charAt(1) == board[x2][y2].charAt(1)) continue;
				if(canMoveSupport(board, x1, y1, x2, y2) && !imWegSupport(x1, y1, x2, y2) && board[x1][y1].charAt(1) != board[x2][y2].charAt(1)) {
					char ch = board[x1][y1].charAt(0);
					if(ch == figuren.charAt(0)) enemyHit+=6;
					if(ch == figuren.charAt(1)) enemyHit+=5;
					if(ch == figuren.charAt(2)) enemyHit+=4;
					if(ch == figuren.charAt(3)) enemyHit+=3;
					if(ch == figuren.charAt(4)) enemyHit+=2;
					if(ch == figuren.charAt(5)) enemyHit+=1;
				}
			}
		}
		return enemyHit;
	}
	
	public boolean canMoveSupport(String[][] board, int x1, int y1, int x2, int y2){
		if (!richtungSupport(board, x1, y1, x2, y2)) return false;
		
		if(board[x1][y1].charAt(0) == figuren.charAt(5)) return true; //mal schauen ob das geht--!!

		if(ableToMove(board, new Point(x1,y1), new Point(x2,y2), (getPlayer()))){
			check();
			amount++;
			return true;
		}
		return false;
	}
	
	public boolean canMoveCheck(String[][] board, int x1, int y1, int x2, int y2){
		if (!richtungSupport(board, x1, y1, x2, y2)) return false;

		return (ableToMove(board, new Point(x1,y1), new Point(x2,y2), (getPlayer())));
	}
	
	public boolean richtungSupport(String[][] board, int x1, int y1, int x2, int y2){
		char ch = board[x1][y1].charAt(0);

		return moveViable(board, new Point(x1,y1), new Point(x2,y2));
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

class Dim4 {
	int x1, y1, x2, y2, randCount, random;
	public Dim4(int i1, int j1, int i2, int j2, int randCount, int random){
		this.x1 = i1;
		this.y1 = j1;
		this.x2 = i2;
		this.x2 = j2;
	}
	public void x1(){
		this.x1--;
	}
	public void y1(){
		this.y1--;
	}
	public void x2(){
		this.x2--;
	}
	public void y2(){
		this.y2--;
	}
	public void randCount(){
		this.randCount--;
	}
	public void random(){
		this.random--;
	}
}
