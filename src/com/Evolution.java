package com;

import java.util.ArrayList;

/**
 * Created by pdk on 14.10.16.
 */
public class Evolution {
    static int number_of_games = 100;
    static final int generations = 10;
    static ArrayList<Player> players = new ArrayList<>();

    public static void main(String[] args){
//        System.out.println(randomGenerator(2));
//        System.out.println(randomGenerator(2));
        for (int i = 0; i < number_of_games * 2; i++) {
            players.add(new Player(""+i));
        }
        for (int g = 0; g < generations; g++) {
            for (int i = 0; i < number_of_games; i++) {
                for (int j = 0; j < number_of_games; j++) {
                    if (i == j) continue;
                    new Game(players.get(i), players.get(j));
                    System.out.println("finish " + i + ":" + j );
                }
            }
            purge(25);
            System.out.println("###### finish generation " + g + " ######");
        }
        System.out.print("## Finish simulation ##");
    }

    public static void purge(double p) {
        for (int i = 0; i < players.size()-1; i++) {
            for (int j = 0; j < players.size()-1-i; j++) {
                if (players.get(j).elo.getElo() <
                        players.get(j+1).elo.getElo()) {
                    Player temp = players.get(j+1);
                    players.set(j+1, players.get(j));
                    players.set(j,temp);
                }
            }
        }
        ArrayList<Player> players2 = new ArrayList<>();
        for (int i = 0; i < players.size()*(1 - (p/100)); i++) {
            players2.add(players.get(i));
        }
        players = players2;
        for (int i = 0; players.size() < number_of_games*2; i++) {
            Player temp = players.get(i).clone();
            temp.mutate();
            players.add(temp);
        }

        for (int i = 0; i < players.size()-1; i++) {
            for (int j = 0; j < players.size()-1-i; j++) {
                if (players.get(j).elo.getElo() < players.get(j+1).elo.getElo()) {
                    Player temp = players.get(j+1);
                    players.set(j+1, players.get(j));
                    players.set(j,temp);
                }
            }
        }

        for (Player player: players) {
            System.out.println(player.elo.getElo() + " " + player.getId());
            player.getBrain().printStats();
        }
    }

    public int getNumber_of_games() {
        return number_of_games;
    }
}
