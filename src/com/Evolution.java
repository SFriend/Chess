package com;

import com.elo.Elo;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by pdk on 14.10.16.
 */
public class Evolution {
    static int number_of_games = 10;
    static final float MAX_VALUES_DELTA = 10;
    static ArrayList<Player> players = new ArrayList<>();

    public static void main(String[] args){
//        System.out.println(randomGenerator(2));
//        System.out.println(randomGenerator(2));
        for (int i = 0; i < number_of_games*2; i++) {
            players.add(new Player(i,MAX_VALUES_DELTA));
        }
        for (int i = 0; i < number_of_games; i++) {
            Game game = new Game(players.get(i*2), players.get((i*2)+1));
            System.out.println("finish " + i);
        }
        for (int i = 0; i < players.size(); i++) {
            System.out.println(players.get(i).getElo());
        }
//        purge(50);
//        System.out.println(smart.get(0).pointDelta(new Point(1, 1), new Point(2, 1)));
    }

    public static void purge(double p) {
        for (Player player: players) {
            System.out.print(player.getElo() + " ");
        }

        for (int i = 0; i < players.size()-1; i++) {
            if (players.get(i).getElo() < players.get(i+1).getElo()) {
                Player temp = players.get(i+1);
                players.set(i+1, players.get(i+1));
                players.set(i,temp);
            }
        }

        for (Player player: players) {
            System.out.print(player.getElo() + " ");
        }
    }

    public int getNumber_of_games() {
        return number_of_games;
    }
}
