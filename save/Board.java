package com.company;

import java.awt.*;

/**
 * Created by pdk on 12.10.16.
 */
public class Board {
    int width;
    int height;
    Field[][] fields;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        fields = new Field[width][height];
    }

    public void moveStone(Point p1, Point p2){
        int x1 = (int) p1.getX(), x2 = (int) p2.getX();
        int y1 = (int) p1.getY(), y2 = (int) p2.getY();
        Field temp = fields[x2][y2];
        fields[x2][y2] = fields[x1][y1];
        fields[x2][y2].clearString();
    }

    public void setString(int x, int y, String str) {
        fields[x][y].setString(str);
    }

    public Field getField(int x, int y) {
        return fields[x][y];
    }

    public String getFieldString(int x, int y) {
        return fields[x][y].getString();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

class Field {
    String field;

    public Field(){
        field = "  ";
    }

    public void clearString(){
        field = "  ";
    }

    public void setString(String str) {
        field = str;
    }

    public String getString() {
        return field;
    }
}