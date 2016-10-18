package com;

import print.ChessStones;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by pdk on 12.10.16.
 */
public class Board {
    final int width;
    int move_count = 0;
    boolean whiteTurn = true;

    DoublePoint[] balance;

    ArrayList<Piece> player1 = new ArrayList<Piece>();
    ArrayList<Piece> player2 = new ArrayList<Piece>();
    ArrayList<Piece>[] playerStones = (ArrayList<Piece>[]) new ArrayList[2];

    Move move = new Move();

    public Board(int width) {
        playerStones[0] = player1;
        playerStones[1] = player2;
        this.width = width;
        for (int i = 0; i < width; i++) {
            player1.add(new Pawn(i,1,true));
            player2.add(new Pawn(i,6,false));
        }

        player1.add(new Rook    (0,0,true));
        player1.add(new Knight  (1,0,true));
        player1.add(new Bishop  (2,0,true));
        player1.add(new Queen   (3,0,true));
        player1.add(new King    (4,0,true));
        player1.add(new Bishop  (5,0,true));
        player1.add(new Knight  (6,0,true));
        player1.add(new Rook    (7,0,true));

        player2.add(new Rook    (0,7,false));
        player2.add(new Knight  (1,7,false));
        player2.add(new Bishop  (2,7,false));
        player2.add(new Queen   (3,7,false));
        player2.add(new King    (4,7,false));
        player2.add(new Bishop  (5,7,false));
        player2.add(new Knight  (6,7,false));
        player2.add(new Rook    (7,7,false));
        calcBalance();
    }

    public boolean randomMove(){
        return move.Random(this);
    }

    public void movePiece(int x1, int y1, int x2, int y2){
        for (Piece pc1 : playerStones[getPlayer()]) {
            if(pc1.getX() == x1 && pc1.getY() == y1) {
                pc1.setX(x2);
                pc1.setY(y2);
                for (Piece pc2 : playerStones[1-getPlayer()]){
                    if (pc2.getX() == x2 && pc2.getY() == y2) {
                        playerStones[1-getPlayer()].remove(pc2);
                        break;
                    }
                }
                break;
            }
        }
        calcBalance();
    }

    public boolean isColorEqual(int x1, int y1, int x2, int y2){
        if (!isEmpty(x1, y1) && !isEmpty(x2,y2)) {
            return getPiece(x1, y1).isWhite() && getPiece(x2,y2).isWhite();
        }
//        for (Piece pc1 : playerStones[getPlayer()]) {
//            if(pc1.getX() == x1 && pc1.getY() == y1) {
//                for (Piece pc2 : playerStones[getPlayer()]){
//                    if (pc2.getX() == x2 && pc2.getY() == y2) {
//                        return true;
//                    }
//                }
//                break;
//            }
//        }
        return false;
    }

    public void calcBalance() {
        double gap = 0.5;
        DoublePoint pointSum[] = {new DoublePoint(0, 0), new DoublePoint(0, 0)};
        for (int n = 0; n < 2; n++) {
            for (Piece pc : playerStones[n]) {
                pointSum[n].addPoint(pc.getX() + gap, pc.getY() + gap);
            }
            pointSum[n].divide(playerStones[n].size());
        }
        balance = pointSum;
    }

    public void incrementMoveCount(){
        move_count++;
        whiteTurn = !whiteTurn;
    }

    public Piece getPiece(int x1, int y1) {
        for (int n = 0; n  < 2; n++) {
            for (Piece pc: playerStones[n]) {
                if (pc.getX() == x1 && pc.getY() == y1)
                    return pc;
            }
        }
        return null;
    }

    public boolean isWhiteTurn(){
        return whiteTurn;
    }

    public boolean isEmpty(int x, int y){
        for (int n = 0; n < 2; n++) {
            for (Piece piece : playerStones[n]) {
                if (piece.getX() == x && piece.getY() == y) return false;
            }
        }
        return true;
    }

    public void print(){
        for (int y = width-1; y >= 0; y--) {
            skip: for (int x = 0; x < width; x++) {
                for (int n = 0; n < 2; n++) {
                    for (Piece pc : playerStones[n]) {
                        if(pc.getX() == x && pc.getY() == y) {
                            System.out.print(pc.getName().charAt(0) + "" + n + " ");
                            continue skip;
                        }
                    }
                } System.out.print("   ");
            } System.out.println(" | ");
        }
        balance[0].print();
        balance[1].print();
        System.out.println("Stones: " + (playerStones[0].size() + playerStones[1].size()));
        System.out.println("################# " + getMove_count());

    }

    public Board clone(Board brd) {
        Board temp_brd = new Board(brd.width);
        temp_brd.move_count = brd.getMove_count();
        temp_brd.whiteTurn = brd.getWhiteTurn();
        temp_brd.balance = brd.getBalance();
        temp_brd.playerStones = brd.getPlayerStones();
        temp_brd.move = brd.getMove();
        return temp_brd;
    }

    public int getPieceNumber(){
        return player1.size()+player2.size();
    }

    public ArrayList<Piece>[] getPlayerStones() {
        return playerStones;
    }

    public int getWidth() {
        return width;
    }

    public int getMove_count() {
        return move_count;
    }

    public int getPlayer(){
        return isWhiteTurn() ? 0 : 1;
    }

    public Move getMove() {
        return move;
    }

    public boolean getWhiteTurn(){
        return whiteTurn;
    }

    public DoublePoint[] getBalance() {
        return balance;
    }
}

class DoublePoint{
    private double x, y;

    public DoublePoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void divide(double n) {
        this.x /= n;
        this.y /= n;
    }

    public void addPoint(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public void print(){
        System.out.println("["+x+", "+y+"]");
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}