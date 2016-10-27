package com;

/**
 * Created by pdk on 10.10.16.
 */
public abstract class Piece {
    private final String[] figures = {"Pawn", "Rook", "Knight", "Bishop", "Queen", "king"};
    protected int x1;
    protected int y1;
    private double value;
    private final boolean white;
    private String name;
    private int ID;

    public Piece(int x1, int y1, double value, boolean white) {
        this.x1 = x1;
        this.y1 = y1;
        this.value = value;
        this.white = white;
    }

    public int getX(){
        return x1;
    }

    public int getY() {
        return y1;
    }

    public int getID() {
        return ID;
    }

    public String getName(){
        return name;
    }

    public double getValue() {
        return value;
    }

    public void setX(int x1) {
        this.x1 = x1;
    }

    public void setY(int y1) {
        this.y1 = y1;
    }

    public void setXY(int x1, int y1) {
        this.x1 = x1;
        this.y1 = y1;
    }

    public void setName(int id){
        this.name = figures[id];
    }

    public double setValue(double value) {
        return this.value = value;
    }

    public void setID(int id){
        this.ID = id;
    }

    public boolean isWhite(){
        return white;
    }

    public int getDirection() {
        return 1 - (2 * (isWhite() ? 0:1));
    }

    public abstract boolean ableToMove(Board brd, int x2, int y2);

    public abstract Piece clonePiece();

    public boolean inTheWay(Board brd, int x2, int y2) {
//        if (brd.getPiece(x1, y1).getID() == 2) return false;
        int vx = vec(x1, x2); // direction of movement x
        int vy = vec(y1, y2); // direction of movement y
        for (int d = 1; d < 8; d++) {
            try {
                if ((x1 + (vx * d)) == x2 && (y1 + (vy * d)) == y2) return false;
                if (brd.isEmpty(x1 + (vx * d), y1 + (vy * d))) continue;
                else return true;
            } catch (Exception e) { System.out.println("out of array"); }
        }
        return false;
    }

    public int vec(int a, int b){
        if(a < b) return 1;
        else if(a > b) return -1;
        else return 0;
    }
}
class Pawn extends Piece {
    public Pawn(int x1, int y1, double value, boolean white) {
        super(x1, y1, value, white);
        setID(0);
        setName(getID());
    }
    public Piece clonePiece() {
        Piece clone = new Pawn(x1, y1, getValue(), isWhite());
        clone.setID(getID());
        clone.setName(getID());
        return clone;
    }
    public boolean ableToMove(Board brd, int x2, int y2) {
        if (inTheWay(brd, x2, y2)) return false;
        int direction = getDirection(); // white (-1) or white (1)
        if (brd.isEmpty(x2, y2)) { // empty target field
            if ((x1 - x2) == 0) { // step forward
                if ((y2 - y1) == direction) { // one step
                    return true;
                } else if ((y2 - y1) == 2 * direction) { // two steps
                    if ((y1 == 3.5 + (direction * -2.5))) { // in beginning position white (6) black (1)
                        return true;
                    }
                }
            }
        } else { // target field
            if ((Math.abs(x1 - x2) == 1)) { // bishopMove
                if ((y2 - y1) == direction) { // forward
                    return true;
                }
            }
        }
        return false;
    }
}
class Rook extends Piece {
    public Rook(int x1, int y1, double value, boolean white){
        super(x1, y1, value, white);
        setID(1);
        setName(getID());
    }
    public Piece clonePiece() {
        Piece clone = new Rook(x1, y1, getValue(), isWhite());
        clone.setID(getID());
        clone.setName(getID());
        return clone;
    }
    public boolean ableToMove(Board brd, int x2, int y2) {
        if (inTheWay(brd, x2, y2)) return false;
        return (x1 == x2) || (y1 == y2);
    }
}
class Knight extends Piece {
    public Knight(int x1, int y1, double value, boolean white){
        super(x1, y1, value, white);
        setID(2);
        setName(getID());
    }
    public Piece clonePiece() {
        Piece clone = new Knight(x1, y1, getValue(), isWhite());
        clone.setID(getID());
        clone.setName(getID());
        return clone;
    }
    public boolean ableToMove(Board brd, int x2, int y2) {
        if(Math.abs(x1 - x2) == 1 && Math.abs(y1 - y2) == 2) return true;
        else if((Math.abs(x1 - x2) == 2 && Math.abs(y1 - y2) == 1)) return true;
        return false;
    }
}
class Bishop extends Piece {
    public Bishop(int x1, int y1, double value, boolean white){
        super(x1, y1, value, white);
        setID(3);
        setName(getID());
    }
    public Piece clonePiece() {
        Piece clone = new Bishop(x1, y1, getValue(), isWhite());
        clone.setID(getID());
        clone.setName(getID());
        return clone;
    }
    public boolean ableToMove(Board brd, int x2, int y2) {
        if (inTheWay(brd, x2, y2)) return false;
        return Math.abs(x1 - x2) == Math.abs(y1 - y2);
    }
}
class Queen extends Piece {
    public Queen(int x1, int y1, double value, boolean white){
        super(x1, y1, value, white);
        setID(4);
        setName(getID());
    }
    public Piece clonePiece() {
        Piece clone = new Queen(x1, y1, getValue(), isWhite());
        clone.setID(getID());
        clone.setName(getID());
        return clone;
    }
    public boolean ableToMove(Board brd, int x2, int y2) {
        if (inTheWay(brd, x2, y2)) return false;
        return (Math.abs(x1 - x2) == Math.abs(y1 - y2)) || ((x1 == x2) || (y1 == y2));
    }
}
class King extends Piece {
    public King(int x1, int y1, double value, boolean white){
        super(x1, y1, value, white);
        setID(5);
        setName(getID());
    }
    public Piece clonePiece() {
        Piece clone = new King(x1, y1, getValue(), isWhite());
        clone.setID(getID());
        clone.setName(getID());
        return clone;
    }
    public boolean ableToMove(Board brd, int x2, int y2) {
        if (inTheWay(brd, x2, y2)) return false;
        return (Math.abs(x1 - x2) <= 1) && (Math.abs(y1 - y2) <= 1);
    }
}