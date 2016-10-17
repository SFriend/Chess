package com;

/**
 * Created by pdk on 10.10.16.
 */
public class Piece {
    private String[] figures = {"Pawn", "Rook", "Knight", "Bishop", "Queen", "King"};
    private int x;
    private int y;
    private int value;
    final boolean white;
    private String name;
    private int ID;

    public Piece(int x, int y, boolean white){
        this.x = x;
        this.y = y;
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

    public boolean isWhite(){
        return white;
    }

    public int getValue() {
        return value;
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
}
class Pawn extends Piece{
    static int count;
    public Pawn(int x, int y, boolean player1){
        super(x, y, player1);
        setID(0);
        setName(getID());
    }
}
class Rook extends Piece{
    public Rook(int x, int y, boolean player1){
        super(x, y, player1);
        setID(1);
        setName(getID());
    }
}
class Knight extends Piece{
    public Knight(int x, int y, boolean player1){
        super(x, y, player1);
        setID(2);
        setName(getID());
    }
}
class Bishop extends Piece{
    public Bishop(int x, int y, boolean player1){
        super(x, y, player1);
        setID(3);
        setName(getID());
    }
}
class Queen extends Piece{
    public Queen(int x, int y, boolean player1){
        super(x, y, player1);
        setID(4);
        setName(getID());
    }
}
class King extends Piece{
    public King(int x, int y, boolean player1){
        super(x, y, player1);
        setID(5);
        setName(getID());
    }
}