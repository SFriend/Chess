package com.company;

import java.awt.*;

/**
 * Created by pdk on 10.10.16.
 */
public class Piece {
    Point koord;
    boolean player1;
    static int count;
    public Piece(Point koord, boolean player1){
        this.koord = koord;
        this.player1 = player1;
        count++;
    }
    public int getCount(){
        return count;
    }
}
class Pawn extends Piece{
    static int count;
    public Pawn(Point koord, boolean player1){
        super(koord, player1);
        count++;
    }
    public int getCount(){
        return count;
    }
}
class Rook extends Piece{
    static int count;
    public Rook(Point koord, boolean player1){
        super(koord, player1);
        count++;
    }
    public int getCount(){
        return count;
    }
}
class Knight extends Piece{
    static int count;
    public Knight(Point koord, boolean player1){
        super(koord, player1);
        count++;
    }
    public int getCount(){
        return count;
    }
}
class Bishop extends Piece{
    static int count;
    public Bishop(Point koord, boolean player1){
        super(koord, player1);
        count++;
    }
    public int getCount(){
        return count;
    }
}
class Queen extends Piece{
    static int count;
    public Queen(Point koord, boolean player1){
        super(koord, player1);
        count++;
    }
    public int getCount(){
        return count;
    }
}
class King extends Piece{
    static int count;
    public King(Point koord, boolean player1){
        super(koord, player1);
        count++;
    }
    public int getCount(){
        return count;
    }
}