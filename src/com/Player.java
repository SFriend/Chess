package com;

import com.elo.Elo;

/**
 * Created by peter on 18.10.16.
 */
public class Player {
    private Elo elo = new Elo();
    private int wins;
    private int games;
    private int lose;
    private Smart brain;

    public Player() {

    }

    public Smart getBrain() {
        return brain;
    }

    public void setElo(int p2, float outcome) {
        this.elo.changeElo(p2, outcome);
    }

    public void loseGame() {
        lose++;
    }

    public void gameWon() {
        games++;
    }

    public int getElo() {
        return elo.getElo();
    }

    public int getWins() {
        return wins;
    }

    public int getLose() {
        return lose;
    }

    public int getGames() {
        return games;
    }
}
