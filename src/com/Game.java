package com;

import java.util.Random;

/**
 * Created by pdk on 12.10.16.
 */
public class Game {
    private boolean running = true;
    public Board brd;
    public Player player1;
    public Player player2;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        brd = new Board();
    }

    public void start() {
        running = true;
        float points = 0.5f;
        while (brd.getMove_count() < 60) {
            nextPlayer();
//            if (!player1.getBrain().SmartMove(brd)) {
//                points = 1f;
//                break;
//            }
//            if (!player2.getBrain().SmartMove(brd)) {
//                points = 0f;
//                break;
//            }
        }
//        if (points == 1) {
//            System.out.println("player2 won");
//            player2.getBrain().printStats();
//            brd.print();
//        } else if (points == 0){
//            System.out.println("player1 won");
//            player1.getBrain().printStats();
//            brd.print();
//        }
        int elo_p1 = player1.elo.getElo();
        int elo_p2 = player2.elo.getElo();
        player1.elo.changeElo(elo_p2, points);
        player2.elo.changeElo(elo_p1, 1-points);
    }

    public void nextPlayer() {
        boolean moved = false;
        float points = 0.5f;
        if (brd.getMove_count() < 60) {
            if (brd.getPlayer() == 0) {
                if (!player1.getBrain().SmartMove(brd)) {
                    moved = true;
                    points = 1f;
                }
            } else {
                if (!player2.getBrain().SmartMove(brd)) {
                    moved = true;
                    points = 0f;
                }
            }
        }
        if (!moved) {
            int elo_p1 = player1.elo.getElo();
            int elo_p2 = player2.elo.getElo();
            player1.elo.changeElo(elo_p2, points);
            player2.elo.changeElo(elo_p1, 1-points);
        }
    }

    double randomGenerator(long seed) {
        Random generator = new Random(seed);
        double num = generator.nextDouble() * (1.0);
        return num;
    }

    public void finish() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }
}