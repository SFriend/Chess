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
//        brd.print();
//        brd.move(0,1,0,3);
//        brd.randomMove();
//        brd.randomMove();
        while (isRunning()) {
//            if(!brd.Random(brd) && brd.getPieceNumber() > 2) finish();
            if (!player1.getBrain().SmartMove(brd)) {
                int elo_p1 = player1.getElo();
                int elo_p2 = player2.getElo();
                player1.setElo(elo_p2, 1);
                player2.setElo(elo_p1, 0);
                break;
            }
            if (!player2.getBrain().Random(brd)) {
                int elo_p1 = player1.getElo();
                int elo_p2 = player2.getElo();
                player1.setElo(elo_p2, 0);
                player2.setElo(elo_p1, 1);
                break;
            }
        }

        for (int i = 0; i < 2; i++) {
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