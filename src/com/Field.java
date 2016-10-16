package com;

/**
 * Created by pdk on 15.10.16.
 */
public class Field {
    private int x, y;
    private Piece piece;

    public Field(int x, int y) {
        this.x = x;
        this.y = y;
        this.piece = null;
    }

    public void placePiece(Piece piece) {
        this.piece = piece;
    }

    public boolean isEmpty() {

//        for (Piece piece : player1) {
//            if (piece.getX() == x && piece.getY() == y) return false;
//        }
//        for (Piece piece : player1) {
//            if (piece.getX() == x && piece.getY() == y) return false;
//        }
        return piece == null;
    }

    public Piece takePiece(){
        Piece temp = this.piece;
        this.piece = null;
        return temp;
    }

    public void clearField() {
        this.piece = null;
    }

    public void addPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean isPiece(Piece piece) {
        return this.piece == piece;
    }

    public boolean isKing() {
        return this.piece instanceof King;
    }

    public boolean isQueen() {
        return this.piece instanceof Queen;
    }

    public boolean isBishop() {
        return this.piece instanceof Bishop;
    }

    public boolean isKnight() {
        return this.piece instanceof Knight;
    }

    public boolean isRook() {
        return this.piece instanceof Rook;
    }

    public boolean isPawn() {
        return this.piece instanceof Pawn;
    }

    public boolean isPlayer1() {
        return this.piece.white;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public int getFieldID(){
        return piece.getID();
    }
}