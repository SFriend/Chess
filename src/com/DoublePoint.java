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

    public void divide(double n) {
        this.x /= n;
        this.y /= n;
    }

    public void addPoint(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public void print(){
        System.out.println("["+x+", "+y+"]");
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
