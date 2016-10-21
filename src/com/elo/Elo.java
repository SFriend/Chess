package com.elo;

/**
 * Created by pdk on 09.10.16.
 */
public class Elo {
    private int elo = 1600;
    private int win;
    private int games;
    private double EA;
    private final int k = 40;

    private int elo(int RA, int RB, float o){
        double a = 1 + (Math.pow(10, (RB - RA) / 400d));
        return (int) (k * (o - (1 / a)) + 0.5d);
    }

    public void changeElo(int p2, float outcome){
        int p1 = this.elo;
        this.elo += elo(p1, p2, outcome);
    }

    public int getElo() {
        return elo;
    }

    public int getWin() {
        return win;
    }

    public int getGames() {
        return games;
    }

    public int getLose(){
        return games-win;
    }
}
