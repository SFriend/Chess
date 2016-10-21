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
    public Board brd;

    public Game(Player player1, Player player2) {
        brd = new Board();
        while (isRunning()) {
            if (brd.getMove_count() > 150) {
                int elo_p1 = player1.getElo();
                int elo_p2 = player2.getElo();
                player1.setElo(elo_p2, 0.5f);
                player2.setElo(elo_p1, 0.5f);
                break;
            }
            if(brd.getPieceNumber() <= 2) finish();
            brd = player1.getBrain().SmartMove(brd);
//            new Move().printStat(brd);
            if(brd.getPieceNumber() <= 2) finish();
            brd = player2.getBrain().SmartMove(brd);
//            new Move().printStat(brd);
        }
//        EventQueue.invokeLater(new Main());
    }

    double randomGenerator(long seed) {
        Random generator = new Random(seed);
        double num = generator.nextDouble() * (1.0);
        return num;
    }

    public void start(){
        running = true;
    }

    public void finish() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }
}