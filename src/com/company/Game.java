package com.company;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by pdk on 12.10.16.
 */
public class Game {
    static boolean finish = false;
    static final float MAX_VALUES_DELTA = 10;
    static int computer = 10;

    public static void main(String[] args) {
        Smart[] smart = new Smart[computer];
//        System.out.println(randomGenerator(2));
//        System.out.println(randomGenerator(2));

        for (int cp = 0; cp < computer; cp++){
            double[] d_stones = new double[5];
            for (int i = 0; i < d_stones.length; i++){
                d_stones[i] = (i+1) + (Math.random() * (i + 1) * 2) / (MAX_VALUES_DELTA*2);
            }
            double[] d_style = new double[3];
            for (int i = 0; i < d_style.length; i++){
                d_style[i] = (Math.random() * 2) + 1;
            }
            DoublePoint prefBoardMiddle = new DoublePoint(Math.random()*8, Math.random()*8);
            smart[cp] = new Smart(d_stones, d_style, prefBoardMiddle);
        }
        for (int cp = 0; cp < computer; cp++){
            System.out.println("Stats of Computer Nr. " + (cp+1));
            for (int i = 0; i < smart[cp].getRandomStats().length-1; i++){
                System.out.println(Arrays.toString((double[]) smart[cp].getRandomStats()[i]));
            }
           ((DoublePoint) smart[cp].getRandomStats()[2]).print();
            System.out.println();
        }

        System.out.println(smart[0].pointDelta(new Point(1,1), new Point(2,1)));

        EventQueue.invokeLater(new Main());
    }

    static double randomGenerator(long seed) {
        Random generator = new Random(seed);
        double num = generator.nextDouble() * (1.0);

        return num;
    }

    public static boolean isFinish() {
        return finish;
    }
}
