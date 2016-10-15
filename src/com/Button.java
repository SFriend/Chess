package com;

import java.awt.*;


public class Button {
	int x, y, width, height, n;
	
	public Button(int x, int y, int width, int height, int n){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.n = n;
	}

	public boolean isPressed(Point p1){
		return isBetween(p1.getX(),x,width) && isBetween(p1.getY(),y+25,height);
	}

	public int scale(double n){
		return (int) ((n * width) / 100);
	}

	public boolean isBetween(double n, double d1, double d2){
		return scale(n) >= scale(d1) && scale(n) < scale(d1+d2);
	}
	
	public Dim5 getDim(){
		Dim5 dim = new Dim5(x, y, width, height, n);
		return dim;
	}
}

class Dim5{
	int x, y, width, height, n;
	public Dim5(int x, int y, int width, int height, int n){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.n = n;
	}
}