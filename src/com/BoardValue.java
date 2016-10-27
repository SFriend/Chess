package com;

/**
 * Created by peter on 22.10.16.
 */
public class BoardValue {
    Board brd;
    public BoardValue(Board brd) {
        this.brd = brd;
    }

    public double[] allVals(double[] values_style, double[] values_stones) {
        int[] sup = new int[2];
        double[] enemy = new double[2];
        double[] enemyHit = new double[2];
        for (int n = 0; n < sup.length; n++) {
            for (Piece pc1 : brd.getStones().get(n)) {
                for (Piece pc2 : brd.getStones().get(n)) {
                    if (!pc1.equals(pc2)) {
//                        if (new Logic(brd, pc1.getX(), pc1.getY(), pc2.getX(), pc2.getY()).ableToMove()) System.out.println("true");pc1.ableToMove(brd, pc2.getX(), pc2.getY())
                        if (new Logic(brd, pc1, pc2.getX(), pc2.getY()).ableToMove()) {
                            sup[n]++; // values_stones[pc2.getID()];
                        }
                    }
                }
                for (Piece pc2 : brd.getStones().get(1 - n)) {
                    if (new Logic(brd, pc2, pc1.getX(), pc1.getY()).ableToMove()) {
                        enemy[n] -= values_stones[pc2.getID()];
                    }
                    if (new Logic(brd, pc1, pc2.getX(), pc2.getY()).ableToMove()) {
                        enemyHit[n] += values_stones[pc1.getID()];
                    }
                }
            }
        }
        sup[0] *= values_style[0];
        enemy[0] *= values_style[1];
        enemyHit[0] *= values_style[2];
        sup[1] *= values_style[0];
        enemy[1] *= values_style[1];
        enemyHit[1] *= values_style[2];
        return new double[]{ sup[0] + enemy[0] + enemyHit[0], sup[1] + enemy[1] + enemyHit[1] };
    }

    public int[] support(double[] values_stones) {
        int[] sup = new int[2];
        for (int n = 0; n < sup.length; n++) {
            for (Piece pc1 : brd.getStones().get(n)) {
                for (Piece pc2 : brd.getStones().get(n)) {
                    if (!pc1.equals(pc2)) {
                        if (new Logic(brd, pc1, pc2.getX(), pc2.getY()).ableToMove()) {
                            sup[n]++; // values_stones[pc2.getID()];
                        }
                    }
                }
            }
        }
        return sup;
    }

    public double[] enemy(double[] values_stones) {
        double[] enemy = new double[2];
        for (int n = 0; n < enemy.length; n++) {
            for (Piece pc1 : brd.getStones().get(n)) {
                for (Piece pc2 : brd.getStones().get(1 - n)) {
                    if (new Logic(brd, pc1, pc2.getX(), pc2.getY()).ableToMove()) {
                        enemy[n] -= values_stones[pc2.getID()];
                    }
                }
            }
        }
        return enemy;
    }

    public double[] enemyHit(double[] values_stones) {
        double[] enemyHit = new double[2];
        for (int n = 0; n < enemyHit.length; n++) {
            for (Piece pc1 : brd.getStones().get(n)) {
                for (Piece pc2 : brd.getStones().get(1 - n)) {
                    if (new Logic(brd, pc2, pc1.getX(), pc1.getY()).ableToMove()) {
                        enemyHit[n] += values_stones[pc1.getID()];
                    }
                }
            }
        }
        return enemyHit;
    }
}
