package com;

import print.ChessStones;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by pdk on 12.10.16.
 */
public class Board {
    private final int width;
    private Field[][] field;

    DoublePoint[] balance;
    ChessStones chessStones;

    Move move = new Move();

//    List<Piece> pieceList = new ArrayList<>();
//    ArrayList pieces = new ArrayList<Piece>();
    ArrayList<Piece> pieces1 = new ArrayList<Piece>();
    ArrayList<Piece> pieces2 = new ArrayList<Piece>();

    public Board(int width) {
        this.width = width;
        field = new Field[width][width];
        reset();
    }

    public void reset() {
        for (int i = 0; i < this.width; i++){
            for (int j = 0; j < this.width; j++){
                field[i][j] = new Field(i,j);
            }
        }
        for (int i = 0; i < width; i++) {
            field[i][1].addPiece(new Pawn(i,1,true));
            field[i][6].addPiece(new Pawn(i,6,false));
            pieces1.add(new Pawn(i,1,true));
            pieces2.add(new Pawn(i,6,false));
        }


        field[0][0].addPiece(new Rook   (0, 0, true));
        field[1][0].addPiece(new Knight (1, 0, true));
        field[2][0].addPiece(new Bishop (2, 0, true));
        field[3][0].addPiece(new Queen  (3, 0, true));
        field[4][0].addPiece(new King   (4, 0, true));
        field[5][0].addPiece(new Bishop (5, 0, true));
        field[6][0].addPiece(new Knight (6, 0, true));
        field[7][0].addPiece(new Rook   (7, 0, true));
        pieces1.add(new Rook    (0,0,true));
        pieces1.add(new Knight  (1,0,true));
        pieces1.add(new Bishop  (2,0,true));
        pieces1.add(new Queen   (3,0,true));
        pieces1.add(new King    (4,0,true));
        pieces1.add(new Bishop  (5,0,true));
        pieces1.add(new Knight  (6,0,true));
        pieces1.add(new Rook    (7,0,true));

        field[0][7].addPiece(new Rook   (0, 7, false));
        field[1][7].addPiece(new Knight (1, 7, false));
        field[2][7].addPiece(new Bishop (2, 7, false));
        field[3][7].addPiece(new Queen  (3, 7, false));
        field[4][7].addPiece(new King   (4, 7, false));
        field[5][7].addPiece(new Bishop (5, 7, false));
        field[6][7].addPiece(new Knight (6, 7, false));
        field[7][7].addPiece(new Rook   (7, 7, false));
        pieces2.add(new Rook    (0,7,false));
        pieces2.add(new Knight  (1,7,false));
        pieces2.add(new Bishop  (2,7,false));
        pieces2.add(new Queen   (3,7,false));
        pieces2.add(new King    (4,7,false));
        pieces2.add(new Bishop  (5,7,false));
        pieces2.add(new Knight  (6,7,false));
        pieces2.add(new Rook    (7,7,false));

//        for (Piece pc: pieces1){
//            if(pc.getX() == 0 && pc.getY() == 1) {
//                pc.setX(0);
//                pc.setY(2);
//                break;
//            }
//        }

        balance = getBalance();
    }

    public void move(int x1, int y1, int x2, int y2){
        new Move(this, x1, y1, x2, y2);
    }

    public void moveStone(int x1, int y1, int x2, int y2){
        field[x2][y2].addPiece(field[x1][y1].getPiece());
        field[x1][y1].clearField();
    }

    public boolean isColorEqual(int x1, int y1, int x2, int y2){
        if(field[x2][y2].isEmpty()) return false;
        return field[x1][y1].isPlayer1() == field[x2][y2].isPlayer1();
    }

    public void setField(int x1, int y1, Field fld) {
        field[x1][y1] = fld;
    }

    public DoublePoint[] getBalance() {
        double gap = 0.5;
        DoublePoint pointSum[] = {new DoublePoint(0, 0), new DoublePoint(0, 0)};
        for (Piece pc : pieces1){
            pointSum[0].addPoint(pc.getX() + gap, pc.getY() + gap);
        }
        pointSum[0].divide(pieces1.size());

        for (Piece pc : pieces2){
            pointSum[1].addPoint(pc.getX() + gap, pc.getY() + gap);
        }
        pointSum[1].divide(pieces2.size());


//        for (int n = 0; n < 2; n++) {
//            int count = 0;
//            for (int x1 = 0; x1 < 8; x1++) {
//                for (int y1 = 0; y1 < 8; y1++) {
//                    if (!getField(x1, y1).isEmpty() && getField(x1, y1).isPlayer1()) {
//                        pointSum[n].addPoint(new DoublePoint(x1+0.5, y1+0.5));
//                        count++;
//                    }
//                }
//            }
//            pointSum[n].divide(count);
//        }
        return pointSum;
    }

    public Field getField(int x, int y) {
        return field[x][y];
    }

    public int getWidth() {
        return width;
    }

    public void print(){
        for (int x = 0; x < width; x++) {
            skip: for (int y = 0; y < width; y++) {
                for (Piece pc : pieces1) {
                    if(pc.getX() == x && pc.getY() == y) {
                        System.out.print(pc.getID() + " ");
                        continue skip;
                    }
                }

                for (Piece pc : pieces2) {
                    if(pc.getX() == x && pc.getY() == y) {
                        System.out.print(pc.getID() + " ");
                        continue skip;
                    }
                }
                System.out.print("  ");

//                if (!field[x][y].isEmpty()) System.out.print(field[x][y].getFieldID() + " ");
//                else System.out.print("  ");
            } System.out.println();
        }
        balance[0].print();
        balance[1].print();
    }

    public boolean isEmpty(int x, int y){
        for (Piece piece : pieces1) {
            if (piece.getX() == x && piece.getY() == y) return false;
        }
        for (Piece piece : pieces1) {
            if (piece.getX() == x && piece.getY() == y) return false;
        }
        return true;
    }

    public Piece getPiece(){
        return null;
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