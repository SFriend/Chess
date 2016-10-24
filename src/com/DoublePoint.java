package com;

/**
 * Created by peter on 18.10.16.
 */
public class DoublePoint{
    private double x, y;

    public DoublePoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public DoublePoint clone() {
        DoublePoint clone = new DoublePoint(x,y);
        return clone;
    }

    public void divide(double n) {
        this.x /= n;
        this.y /= n;
    }

    public void addPoint(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void print() {
        x = (int)(x*100)/100.0;
        y = (int)(y*100)/100.0;
        System.out.println("["+x+", "+y+"]");
    }
}
