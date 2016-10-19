package com;

/**
 * Created by pdk on 10.10.16.
 */
public class Piece {
    private String[] figures = {"Pawn", "Rook", "Knight", "Bishop", "Queen", "#ing"};
    private int x;
    private int y;
    private double value;
    private final boolean white;
    private String name;
    private int ID;

    public Piece(int x, int y, double value, boolean white) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.white = white;
    }

    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setXY( int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double setValue(double value) {
        return this.value = value;
    }

    public void setName(int id){
        this.name = figures[id];
    }

    public String getName(){
        return name;
    }

    public void setID(int id){
        this.ID = id;
    }

    public int getID() {
        return ID;
    }

    public double getValue() {
        return value;
    }

    public boolean isWhite(){
        return white;
    }

    public Piece clonePiece() {
        Piece clone = new Piece(x,y,value,white);
        clone.setID(getID());
        clone.setName(getID());
        return clone;
    }

    public int getDirection() {
        return 1 - (2 * (isWhite() ? 0:1));
    }
}
class Pawn extends Piece {
    public Pawn(int x, int y, int value, boolean white){
        super(x, y, value, white);
        setID(0);
        setName(getID());
    }
}
class Rook extends Piece {
    public Rook(int x, int y, int value, boolean white){
        super(x, y, value, white);
        setID(1);
        setName(getID());
    }
}
class Knight extends Piece {
    public Knight(int x, int y, int value, boolean white){
        super(x, y, value, white);
        setID(2);
        setName(getID());
    }
}
class Bishop extends Piece {
    public Bishop(int x, int y, int value, boolean white){
        super(x, y, value, white);
        setID(3);
        setName(getID());
    }
}
class Queen extends Piece {
    public Queen(int x, int y, int value, boolean white){
        super(x, y, value, white);
        setID(4);
        setName(getID());
    }
}
class King extends Piece {
    public King(int x, int y, int value, boolean white){
        super(x, y, value, white);
        setID(5);
        setName(getID());
    }
}