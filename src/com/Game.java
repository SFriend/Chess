package com;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by pdk on 12.10.16.
 */
public class Game {
    private boolean running = false;
    private boolean finish = true;
    private boolean paused = false;
    private int move_count = 0;

    private boolean computer = false;

    private boolean player1 = true;
    private boolean selected = false;

    private String lastMove;

    private boolean computerVScomputer = computer;
    private boolean chosen = false;

    public static void main(String[] args) {
        Board brd = new Board(8);

        brd.print();
        brd.move.random(0,1,0,3);
//        brd.move(0,1,0,3);
//        brd.print();
        System.out.print(brd.pieces1.remove(new Rook(0,0,true)));
//        EventQueue.invokeLater(new Main());
    }

    public Game(){

    }

    public void reset(){
        move_count = 0;
        finish = true;
        paused = false;
    }

    double randomGenerator(long seed) {
        Random generator = new Random(seed);
        double num = generator.nextDouble() * (1.0);

        return num;
    }

    public void start(){
        running = true;
    }

    public void pause(){
        paused = true;
    }

    public void conti(){
        paused = false;
    }

    public void finish() {
        running = false;
    }

    public boolean isSelected(){
        return selected;
    }

    public boolean isChosen() {
        return chosen;
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isPaused() {
        return paused;
    }

    public int getMove_count() {
        return move_count;
    }

    public String getLastMove() {
        return lastMove;
    }
}
