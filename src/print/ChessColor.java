package print;

import java.awt.*;

/**
 * Created by pdk on 13.10.16.
 */
public class ChessColor {
    static Color[] colors = {Color.white, Color.yellow, Color.orange, Color.red, Color.pink, Color.magenta, Color.cyan, Color.blue, Color.green, Color.lightGray, Color.gray, Color.DARK_GRAY, Color.black};
    static String[] color = {"Weiss", "Gelb", "Orange", "Rot", "Pink", "Magenta", "Cyan", "Blau", "Gruen", "Hellgrau", "Grau", "Dunkelgrau", "Schwarz"};
    static int[] count = new int[4]; //= 1000000, countColor2 = 1000011, countColor3 = 1000002, countColor4 = 1000006;

    public ChessColor() {
        count = new int[]{9,10,3,8};
    }

    public boolean checkForSameColor() {
        for (int i = 0; i < count.length; i++) {
            for (int j = 0; j < count.length; j++) {
                if (i == j) continue;
                if (count[i] == count[j]) return true;
            }
        }
        return false;
    }

    public boolean checkForSameColorSelection(int n) {
        for (int i = 0; i < count.length; i++)
            if (count[i] == n) return true;
        return false;
    }

    public void decresse(int col) {
        do {
            col--;
        } while (checkForSameColor());
    }

    public void increase(int n) {
        do {
            count[n] = (count[n] + 1) % colors.length;
        } while (checkForSameColor());
    }

    public void change(int i, int j) {
        if (i == 0) decresse(count[j]);
        else decresse(count[j]);
    }

    public Color getPlayer1(){
        return colors[count[0]];
    }

    public String getStringPlayer1(){
        return color[count[0]];
    }

    public Color getPlayer2(){
        return colors[count[1]];
    }

    public String getStringPlayer2(){
        return color[count[1]];
    }

    public Color getField1(){
        return colors[count[2]];
    }

    public String getStringField1(){
        return color[count[2]];
    }

    public Color getField2(){
        return colors[count[3]];
    }

    public String getStringField2(){
        return color[count[3]];
    }

    public Color getColor(int n) {
        return colors[n];
    }

    public int getLength(){
        return colors.length;
    }
}
