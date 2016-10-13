package com.company;

import java.awt.*;

/**
 * Created by pdk on 13.10.16.
 */
public class ChessColor {
    static String color[] = {"Weiss", "Gelb", "Orange", "Rot", "Pink", "Magenta", "Cyan", "Blau", "Gruen", "Hellgrau", "Grau", "Dunkelgrau", "Schwarz"};
    static Color[] col = {Color.white, Color.yellow, Color.orange, Color.red, Color.pink, Color.magenta, Color.cyan, Color.blue, Color.green, Color.lightGray, Color.gray, Color.DARK_GRAY, Color.black};
    static int[] countColor = new int[4]; //= 1000000, countColor2 = 1000011, countColor3 = 1000002, countColor4 = 1000006;

    public boolean checkForSameColor() {
        for (int i = 0; i < countColor.length; i++) {
            for (int j = 0; j < countColor.length; j++) {
                if (i == j) continue;
                if (countColor[i] == countColor[j]) return true;
            }
        }
        return false;
    }

    public boolean checkForSameColorSelection(int n) {
        for (int i = 0; i < countColor.length; i++)
            if (countColor[i] == n) return true;
        return false;
    }

    public void decresse(int col) {
        do {
            col--;
        } while (checkForSameColor());
    }

    public void increase(int n) {
        do {
            countColor[n] = (countColor[n] + 1) % col.length;
        } while (checkForSameColor());
    }

    public void change(int i, int j) {
        if (i == 0) decresse(countColor[j]);
        else decresse(countColor[j]);
    }
}
