package com;

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
        float points = 0f;
        while (isRunning()) {
            if (brd.getMove_count() > 100 || brd.getPieceNumber() <= 2) {
                points = 0.5f;
                break;
            }

            Board temp = brd.cloneBoard();
            if (player1.getBrain().SmartMove(brd)) {
                points = 1f;
                break;
            }

            Board temp2 = brd.cloneBoard();
            if (player2.getBrain().SmartMove(brd)) {
                points = 0f;
                break;
            }
        }
        int elo_p1 = player1.getElo();
        int elo_p2 = player2.getElo();
        player1.setElo(elo_p2, points);
        player2.setElo(elo_p1, 1-points);
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