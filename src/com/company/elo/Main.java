package com.company.elo;

/**
 * Created by pdk on 09.10.16.
 */
public class Main {
    public static void main(String args[]){
        PvP player = new PvP(2806, 2577);
        Elo elo = new Elo();
        elo.setElo(player, 0.0f);
        System.out.println(player.getP1());
        System.out.println(player.getP2());
    }
}
