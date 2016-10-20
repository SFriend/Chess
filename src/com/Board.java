package com;

import print.ChessStones;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by pdk on 12.10.16.
 */
public class Board extends Move {
    int move_count = 0;
    boolean whiteTurn = true;
    DoublePoint[] balance;
    ArrayList<Piece> player1 = new ArrayList<>();
    ArrayList<Piece> player2 = new ArrayList<>();
    ArrayList<ArrayList<Piece>> playerStones = new ArrayList<>();

    public Board() {
        playerStones.add(player1);
        playerStones.add(player2);
        for (int n = 0; n < 2; n++) {
            int v = 0;
            boolean w = ((n==0) ? true:false);
            for (int i = 0; i < 8; i++) {
                playerStones.get(n).add(new Pawn(i, 1 + (n * 5), v, w));
            }
            int y = n*7;
            playerStones.get(n).add(new Rook    (0,y,v,w));
            playerStones.get(n).add(new Knight  (1,y,v,w));
            playerStones.get(n).add(new Bishop  (2,y,v,w));
            playerStones.get(n).add(new Queen   (3,y,v,w));
            playerStones.get(n).add(new King    (4,y,v,w));
            playerStones.get(n).add(new Bishop  (5,y,v,w));
            playerStones.get(n).add(new Knight  (6,y,v,w));
            playerStones.get(n).add(new Rook    (7,y,v,w));
        }
        calcBalance();
    }

    public Board cloneBoard() {
        Board clone = new Board();

        clone.setMove_count(move_count);
        clone.setWhiteTurn(whiteTurn);
        clone.setBalance(balance);

        clone.player1.clear();
        clone.player2.clear();
        clone.playerStones.clear();
        for (int i = 0; i < player1.size(); i++) clone.player1.add(player1.get(i).clonePiece());
        for (int i = 0; i < player2.size(); i++) clone.player2.add(player2.get(i).clonePiece());
        clone.playerStones.add(clone.player1);
        clone.playerStones.add(clone.player2);
        return clone;
    }

    public void movePiece(int x1, int y1, int x2, int y2) {
        getPiece(x1,y1).setXY(x2,y2);
        if (!isEmpty(x2,y2)) {
            playerStones.get(1-getPlayer()).remove(getPiece(x2,y2));
        }
    }

    private void calcBalance() {
        double gap = 0.5;
        DoublePoint pointSum[] = {new DoublePoint(0, 0), new DoublePoint(0, 0)};
        for (int n = 0; n < 2; n++) {
            for (Piece pc : playerStones.get(n)) {
                pointSum[n].addPoint(pc.getX() + gap, pc.getY() + gap);
            }
            pointSum[n].divide(playerStones.get(0).size());
        }
        balance = pointSum;
    }

    public void print() {
        System.out.println("################# " + getMove_count());
        for (int y = 7; y >= 0; y--) {
            System.out.print(y+1 + " ");
            skip: for (int x = 0; x < 8; x++) {
                for (int n = 0; n < 2; n++) {
                    for (Piece pc : playerStones.get(n)) {
                        if(pc.getX() == x && pc.getY() == y) {
                            System.out.print(pc.getName().charAt(0) + "" + n + " ");
                            continue skip;
                        }
                    }
                } System.out.print("   ");
            } System.out.println("|");
        }
        String notation = "abcdefgh";
        System.out.print("  ");
        for (int i = 0; i < 8; i++) {
            System.out.print(notation.charAt(i) + "  ");
        } System.out.println();
//        balance[0].print();
//        balance[1].print();
        System.out.println("Stones: " + (playerStones.get(0).size() + playerStones.get(1).size()));

    }

    public void incrementMoveCount() {
        move_count++;
        whiteTurn = !whiteTurn;
    }

    public int getPieceNumber(){
        return player1.size()+player2.size();
    }

    public int getPlayer(){
        return isWhiteTurn() ? 0 : 1;
    }

    public int getMove_count() {
        return move_count;
    }

    private boolean isWhiteTurn(){
        return whiteTurn;
    }

    public boolean isEmpty(int x, int y) {
        for (int n = 0; n < 2; n++) {
            for (Piece piece : playerStones.get(n)) {
                if (piece.getX() == x && piece.getY() == y) return false;
            }
        }
        return true;
    }

    public boolean isColorEqual(int x1, int y1, int x2, int y2) {
        if (!isEmpty(x2,y2)) return getPiece(x1, y1).isWhite() == getPiece(x2,y2).isWhite();
        return false;
    }

    public Piece getPiece(int x1, int y1) {
        for (int n = 0; n  < 2; n++) {
            for (Piece pc: playerStones.get(n)) {
                if (pc.getX() == x1 && pc.getY() == y1)
                    return pc;
            }
        }
        return null;
    }

    private ArrayList<Piece> getPlayer1(){
        return player1;
    }

    private ArrayList<Piece> getPlayer2(){
        return player2;
    }

    public ArrayList<ArrayList<Piece>> getPlayerStones() {
        return playerStones;
    }

    public DoublePoint[] getBalance() {
        return balance;
    }

    public void setPlayerStones(ArrayList<ArrayList<Piece>> playerStone) {
        this.playerStones = playerStone;
    }

    public void setPlayer1(ArrayList<Piece> player1) {
        this.player1 = player1;
    }

    public void setPlayer2(ArrayList<Piece> player2) {
        this.player2 = player2;
    }

    public void setMove_count(int move_count) {
        this.move_count = move_count;
    }

    public void setWhiteTurn(boolean whiteTurn){
        this.whiteTurn = whiteTurn;
    }

    public void setBalance(DoublePoint[] balance) {
        this.balance = balance;
    }
}