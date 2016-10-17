package com;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by pdk on 12.10.16.
 */
public class Game {
    private boolean running = true;
    private boolean finish = false;
    private boolean paused = false;

    private boolean computer = false;

    private boolean player1 = true;
    private boolean selected = false;

    private String lastMove;

    private boolean computerVScomputer = computer;
    private boolean chosen = false;

    public static Board brd = new Board(8);

    public Game() {

//        brd.print();
//        brd.move(0,1,0,3);
//        brd.randomMove();
//        brd.randomMove();
        while (isRunning()) {
            if(!brd.randomMove() && brd.getPieceNumber() > 2) finish();
        }
        for (int i = 0; i < 2; i++) {
        }
//        EventQueue.invokeLater(new Main());
    }

    public void reset(){
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

    public String getLastMove() {
        return lastMove;
    }
}