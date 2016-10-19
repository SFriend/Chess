package com;

import com.elo.Elo;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by pdk on 14.10.16.
 */
public class Evolution {
    static int number_of_computer = 10;
    static final float MAX_VALUES_DELTA = 10;
    static ArrayList<Player> players = new ArrayList<>();

    public static void main(String[] args){
//        System.out.println(randomGenerator(2));
//        System.out.println(randomGenerator(2));

//        for (int c = 0; c < number_of_computer; c++) {
//            smart.add(new Smart(c,MAX_VALUES_DELTA));
//        }

        for (int i = 0; i < number_of_computer; i++) {
            players.add(new Player());
        }
        Game game = new Game(players.get(0), players.get(1));

        purge(50);
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

    public int getNumber_of_computer() {
        return number_of_computer;
    }
}
