package com;

/**
 * Created by pdk on 10.10.16.
 */
public class Piece {
    private static int count;
    private int x;
    private int y;
    private int value;
    final boolean player1;
    private String name;
    private int ID;

    public Piece(int x, int y, boolean player1){
        this.x = x;
        this.y = y;
        this.player1 = player1;
        countInrement();
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

    public void countInrement(){
        count++;
    }

    public static int getCount(){
        return count;
    }

    public boolean getPlayer(){
        return player1;
    }

    public int getValue() {
        return value;
    }

    public void setName(String str){
        this.name = str;
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
        super(x,y, player1);
        setName("Pawn");
        setID(0);
    }
}
class Rook extends Piece{
    static int count;
    public Rook(int x, int y, boolean player1){
        super(x,y, player1);
        setName("Rook");
        setID(1);
    }
}
class Knight extends Piece{
    static int count;
    public Knight(int x, int y, boolean player1){
        super(x,y, player1);
        setName("Knight");
        setID(2);
    }
}
class Bishop extends Piece{
    static int count;
    public Bishop(int x, int y, boolean player1){
        super(x,y, player1);
        setName("Bishop");
        setID(3);
    }
}
class Queen extends Piece{
    static int count;
    public Queen(int x, int y, boolean player1){
        super(x,y, player1);
        setName("Queen");
        setID(4);
    }
}
class King extends Piece{
    static int count;
    public King(int x, int y, boolean player1){
        super(x,y, player1);
        setName("King");
        setID(5);
    }
}