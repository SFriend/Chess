package com.company;

import java.awt.*;
import java.util.EmptyStackException;

/**
 * Created by pdk on 12.10.16.
 */
public class Board extends Piece {
    String figuren = Stones.figuren;
    int width;
    int height;
    Field[][] fields;

    DoublePoint[] balance;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        fields = new Field[width][height];
        for (int i = 0; i < this.width; i++){
            for (int j = 0; j < this.height; j++){
                fields[i][j] = new Field();
            }
        }
        reset();
        balance[0].print();
        balance[1].print();
    }

    public void reset() {
        for (int i = 0; i < width; i++) {
            new Field().setString("");
            String temp1 = "" + figuren.charAt(0) + charC[0];
            setField(new Point(i, 1), new Field().setString(temp1));//new Field.setString(figuren.charAt(0) + "" + charC[0]));
            setField(new Point(i, 6), new Field(figuren.charAt(0) + "" + charC[1]));
        }
        for (int x = 0; x < 5; x++) {
            for (int n = 0; n < 2; n++) {
                setField(new Point(x, 7 * n), new Field(figuren.charAt(x + 1) + "" + charC[n]));
                if (x != 4) setField(new Point(x + 4, 7 * n), new Field(figuren.charAt(4 - x) + "" + charC[n]));
            }
        }
        balance = getBalance();
    }

    public void moveStone(Point p1, Point p2){
        int x1 = (int) p1.getX(), y1 = (int) p1.getY();
        int x2 = (int) p2.getX(), y2 = (int) p2.getY();
        fields[x2][y2] = fields[x1][y1];
        fields[x1][y1].clearField();
    }

    public boolean isColorEqual(Point p1, Point p2){
        int x1 = (int) p1.getX(), y1 = (int) p1.getY();
        int x2 = (int) p2.getX(), y2 = (int) p2.getY();
        return fields[x1][y1].getColor() == fields[x2][y2].getColor();
    }

    public void setField(Point p1, Field fld) {
        int x1 = (int) p1.getX(), y1 = (int) p1.getY();
        fields[x1][y1] = fld;
    }

    public DoublePoint[] getBalance(){
        DoublePoint pointSum[] = {new DoublePoint(0,0), new DoublePoint(0,0)};
        for (int n = 0; n < 2; n++) {
            int count = 0;
            for (int x1 = 0; x1 < 8; x1++) {
                for (int y1 = 0; y1 < 8; y1++) {
                    if (getField(new Point(x1, y1)).isColor(n)) {
                        pointSum[n].addPoint(new DoublePoint(x1+0.5,y1+0.5));
                        count++;
                    }
                }
            }
            pointSum[n].divide(count);
        }
        return pointSum;
    }

    public Field getField(Point p1) {
        return fields[(int) p1.getX()] [(int) p1.getY()];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

class DoublePoint{
    double x, y;

    public DoublePoint(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void divide(int n){
        x /= n;
        y /= n;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void print(){
        System.out.println("["+x+","+y+"]");
    }

    public void addPoint(DoublePoint p1) {
        x += p1.getX();
        y += p1.getY();
    }
}

class Field extends Piece{
    String field;
    final String empty = "  ";

    public Field() {
        field = empty;
    }

    public boolean isPiece(int n) {
        return getPiece() == n;
    }

    public boolean isKnight() {
        return getPiece() == 2;
    }

    public boolean isEmpty() {
        return field.equals(empty);
    }

    public boolean isColor(int n){
        return getColor() == charC[n];
    }

    public void clearField(){
        field = empty;
    }

    public void setString(String str) {
        field = str;
    }

    public String getString() {
        return field;
    }

    public int getPiece(){
        return Stones.figuren.indexOf(field.charAt(0));
    }

    public char getColor(){
        return field.charAt(1);
    }
}