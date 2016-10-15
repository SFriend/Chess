package com;

import print.ChessStones;

import java.awt.*;
import java.util.Arrays;

/**
 * Created by pdk on 12.10.16.
 */
public class Board {
    private String figuren;
    private final int width;
    private Field[][] field;

    DoublePoint[] balance;
    ChessStones chessStones = new ChessStones();

    public Board(int width) {
        this.width = width;
        field = new Field[width][width];
        this.figuren = chessStones.getFiguren();
        reset();
        balance[0].print();
        balance[1].print();
    }

    public void reset() {
        for (int i = 0; i < this.width; i++){
            for (int j = 0; j < this.width; j++){
                field[i][j] = new Field(i,j);
            }
        }
        for (int i = 0; i < width; i++) {
            field[i][1].addPiece(new Pawn(i,1,false));
            field[i][6].addPiece(new Pawn(i,1,true));
        }

        field[0][0].addPiece(new Rook   (0, 0, false));
        field[1][0].addPiece(new Knight (1, 0, false));
        field[2][0].addPiece(new Bishop (2, 0, false));
        field[3][0].addPiece(new Queen  (3, 0, false));
        field[4][0].addPiece(new King   (4, 0, false));
        field[5][0].addPiece(new Bishop (5, 0, false));
        field[6][0].addPiece(new Knight (6, 0, false));
        field[7][0].addPiece(new Rook   (7, 0, false));

        field[0][7].addPiece(new Rook   (0, 7, true));
        field[1][7].addPiece(new Knight (1, 7, true));
        field[2][7].addPiece(new Bishop (2, 7, true));
        field[3][7].addPiece(new Queen  (3, 7, true));
        field[4][7].addPiece(new King   (4, 7, true));
        field[5][7].addPiece(new Bishop (5, 7, true));
        field[6][7].addPiece(new Knight (6, 7, true));
        field[7][7].addPiece(new Rook   (7, 7, true));

        balance = getBalance();
    }

    public void moveStone(int x1, int y1, int x2, int y2){
        field[x2][y2] = field[x1][y1];
        field[x1][y1].clearField();
    }

    public boolean isColorEqual(int x1, int y1, int x2, int y2){
        return field[x1][y1].isPlayer1() == field[x2][y2].isPlayer1();
    }

    public void setField(int x1, int y1, Field fld) {
        field[x1][y1] = fld;
    }

    public DoublePoint[] getBalance() {
        DoublePoint pointSum[] = {new DoublePoint(0,0), new DoublePoint(0,0)};
        for (int n = 0; n < 2; n++) {
            int count = 0;
            for (int x1 = 0; x1 < 8; x1++) {
                for (int y1 = 0; y1 < 8; y1++) {
                    if (getField(x1,y1).isPlayer1()) {
                        pointSum[n].addPoint(new DoublePoint(x1+0.5,y1+0.5));
                        count++;
                    }
                }
            }
            pointSum[n].divide(count);
        }
        return pointSum;
    }

    public Field getField(int x, int y) {
        return field[x][y];
    }

    public int getWidth() {
        return width;
    }

    public void print(){
        System.out.println(Arrays.deepToString(field));
    }
}

class DoublePoint{
    private double x, y;

    public DoublePoint(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void divide(int n){
        x /= n;
        y /= n;
    }

    public void addPoint(DoublePoint p1) {
        x += p1.getX();
        y += p1.getY();
    }

    public void print(){
        System.out.println("["+x+","+y+"]");
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}