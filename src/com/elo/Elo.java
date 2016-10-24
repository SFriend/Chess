package com.elo;

/**
 * Created by pdk on 09.10.16.
 */
public class Elo {
    private int elo = 1600;
    private final int k = 40;

    public Elo clone() {
        Elo clone = new Elo();
        clone.setElo(elo);
        return clone;
    }

    private int calcPoints(int RB, float o){
        double a = 1 + (Math.pow(10, (RB - elo) / 400d));
        return (int) (k * (o - (1 / a)) + 0.5d);
    }

    public void changeElo(int p2, float outcome){
        this.elo += calcPoints(p2, outcome);
    }

    public int getElo() {
        return elo;
    }

    public void setElo(int elo) {
        this.elo = elo;
    }
}
