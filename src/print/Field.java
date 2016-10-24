package print;

import java.awt.*;

/**
 * Created by peter on 24.10.16.
 */
public class Field {
    private int x;
    private int y;
    private int width;
    private int height;

    public Field(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean isPressed(Point p1){
        return isBetween(p1.getX(),x,width) && isBetween(p1.getY(),y,height);
    }

    private int scale(double n){
        return (int) n; //(int) ((n * width) / 100);
    }

    private boolean isBetween(double n, double d1, double d2){
        return scale(n) >= scale(d1) && scale(n) < scale(d1+d2);
    }
}
