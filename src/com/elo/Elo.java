package com.elo;

/**
 * Created by pdk on 09.10.16.
 */
public class Elo {
    int elo = 1600;
    int win;
    int games;
    double EA;
    final int k = 10;

    public int elo(int RA, int RB, float o){
        double a = 1 + (Math.pow(10, (RB - RA) / 400d));
        return (int) (k * (o - (1 / a)) + 0.5d);
    }
    public void setElo(PvP player, float outcome){
        int p1 = player.getP1();
        int p2 = player.getP2();

        player.setP1(elo(p1, p2,   outcome));
        player.setP2(elo(p2, p1, 1-outcome));
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
