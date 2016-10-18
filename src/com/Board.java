package com;

import print.ChessStones;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by pdk on 12.10.16.
 */
public class Board implements Cloneable {
    private final int width;
    private Field[][] field;
    private int move_count = 0;
    private boolean whiteTurn = true;

    DoublePoint[] balance;

    ArrayList<Piece> player1 = new ArrayList<Piece>();
    ArrayList<Piece> player2 = new ArrayList<Piece>();
    ArrayList<Piece>[] player = (ArrayList<Piece>[]) new ArrayList[2];

    Move move = new Move();

    public Board(int width) {
        player[0] = player1;
        player[1] = player2;
        this.width = width;
        field = new Field[width][width];
        for (int i = 0; i < this.width; i++){
            for (int j = 0; j < this.width; j++){
                field[i][j] = new Field(i,j);
            }
        }
        for (int i = 0; i < width; i++) {
            field[i][1].addPiece(new Pawn(i,1,true));
            field[i][6].addPiece(new Pawn(i,6,false));
            player1.add(new Pawn(i,1,true));
            player2.add(new Pawn(i,6,false));
        }

        field[0][0].addPiece(new Rook   (0, 0, true));
        field[1][0].addPiece(new Knight (1, 0, true));
        field[2][0].addPiece(new Bishop (2, 0, true));
        field[3][0].addPiece(new Queen  (3, 0, true));
        field[4][0].addPiece(new King   (4, 0, true));
        field[5][0].addPiece(new Bishop (5, 0, true));
        field[6][0].addPiece(new Knight (6, 0, true));
        field[7][0].addPiece(new Rook   (7, 0, true));
        player1.add(new Rook    (0,0,true));
        player1.add(new Knight  (1,0,true));
        player1.add(new Bishop  (2,0,true));
        player1.add(new Queen   (3,0,true));
        player1.add(new King    (4,0,true));
        player1.add(new Bishop  (5,0,true));
        player1.add(new Knight  (6,0,true));
        player1.add(new Rook    (7,0,true));

        field[0][7].addPiece(new Rook   (0, 7, false));
        field[1][7].addPiece(new Knight (1, 7, false));
        field[2][7].addPiece(new Bishop (2, 7, false));
        field[3][7].addPiece(new Queen  (3, 7, false));
        field[4][7].addPiece(new King   (4, 7, false));
        field[5][7].addPiece(new Bishop (5, 7, false));
        field[6][7].addPiece(new Knight (6, 7, false));
        field[7][7].addPiece(new Rook   (7, 7, false));
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
        field[x2][y2].addPiece(field[x1][y1].takePiece());
        for (Piece pc1 : player[getPlayer()]) {
            if(pc1.getX() == x1 && pc1.getY() == y1) {
                pc1.setX(x2);
                pc1.setY(y2);
                for (Piece pc2 : player[1-getPlayer()]){
                    if (pc2.getX() == x2 && pc2.getY() == y2) {
                        player[1-getPlayer()].remove(pc2);
                        break;
                    }
                }
                break;
            }
        }
        calcBalance();
    }

    public boolean isColorEqual(int x1, int y1, int x2, int y2){
        for (Piece pc1 : player[getPlayer()]) {
            if(pc1.getX() == x1 && pc1.getY() == y1) {
                for (Piece pc2 : player[getPlayer()]){
                    if (pc2.getX() == x2 && pc2.getY() == y2) {
                        return true;
                    }
                }
                break;
            }
        }
        return false;
//        if(field[x2][y2].isEmpty()) return false;
//        return field[x1][y1].isPlayer1() == field[x2][y2].isPlayer1();
    }

    public void calcBalance() {
        double gap = 0.5;
        DoublePoint pointSum[] = {new DoublePoint(0, 0), new DoublePoint(0, 0)};
        for (int n = 0; n < 2; n++) {
            for (Piece pc : player[n]) {
                pointSum[n].addPoint(pc.getX() + gap, pc.getY() + gap);
            }
            pointSum[n].divide(player[n].size());
        }
        balance = pointSum;
    }

    public Field getField(int x, int y) {
        return field[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getMove_count() {
        return move_count;
    }

    public void incrementMoveCount(){
        move_count++;
        whiteTurn = !whiteTurn;
    }

    public boolean isWhiteTurn(){
        return whiteTurn;
    }

    public boolean isEmpty(int x, int y){
        for (int n = 0; n < 2; n++) {
            for (Piece piece : player[n]) {
                if (piece.getX() == x && piece.getY() == y) return false;
            }
        }
        return true;
    }

//    public boolean isEmpty2(int x, int y){
//        for (int n = 0; n < 2; n++) {
//            for (Piece pc: (ArrayList<Piece>) player[n]) {
//
//                System.out.print("");
//            }
//        }
//        for (int n = 0; n < 2; n++) {
//            for (Piece piece : (ArrayList<Piece>) player[n]) {
//                if (piece.getX() == x && piece.getY() == y) return false;
//            }
//        }
//        return true;
//    }

    public int getPlayer(){
        return isWhiteTurn() ? 0 : 1;
    }

    public void print(){
        for (int y = width-1; y >= 0; y--) {
            skip: for (int x = 0; x < width; x++) {
                for (int n = 0; n < 2; n++) {
                    for (Piece pc : player[n]) {
                        if(pc.getX() == x && pc.getY() == y) {
                            System.out.print(pc.getName().charAt(0) + "" + n + " ");
                            continue skip;
                        }
                    }
                } System.out.print("   ");
            } System.out.print(" | ");
            for (int i = 0; i < 8; i++) {
                if(!field[i][y].isEmpty())
                    System.out.print(field[i][y].getPiece().getName().charAt(0)+ "" + (field[i][y].getPiece().isWhite() ? 0: 1) + " ");
                else System.out.print("   ");
            } System.out.println();
        }
        balance[0].print();
        balance[1].print();
        System.out.println("Stones: " + (player[0].size() + player[1].size()));
        System.out.println("################# " + getMove_count());

    }

//    public Board clone(Board brd) {
//        Board temp_brd = new Board(width);
//        temp_brd.field = brd.field;
//        temp_brd.move_count = brd.move_count;
//        temp_brd.whiteTurn = brd.whiteTurn;
//        temp_brd.balance = brd.balance;
//        temp_brd.player = brd.player; //{player1, player2};
//        return temp_brd;
//    }

    public int getPieceNumber(){
        return player1.size()+player2.size();
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
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