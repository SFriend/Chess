package com.company;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;


public class Button2 extends Main{
	int x, y, width, height, n;
	
	public Button2(int x, int y, int widt, int height, int n){
		this.x = x;
		this.y = y;
		this.width = widt;
		this.height = height;
		this.n = n;
//		addMouseListener(new RepaintOnClick());
		

//		addMouseListener(new MouseAdapter() {
//			public void mousePressed(MouseEvent e) {
//				System.out.println("test");
//			}
//		});
	}
	
	public Dim5 getDim(){
		Dim5 dim = new Dim5(x, y, width, height, n);
		return dim;
	}
	
	private class RepaintOnClick2 implements MouseListener {
		public void mousePressed(MouseEvent evt) {
			Component source = (Component) evt.getSource();
			source.repaint();
			System.out.println("1");
		}

		public void mouseClicked(MouseEvent evt) {
			System.out.println("cklick");
			int mx = evt.getX(); // - abstX;
			int my = evt.getY(); //- abstY - 25;
			if(evt.getX() > x && evt.getX() < x+width){
				if(evt.getX() > y && evt.getX() < y+height){
					System.out.println("you did it");
				}
			}
		}

		public void mouseReleased(MouseEvent evt) {
			System.out.println("1");
		}

		public void mouseEntered(MouseEvent evt) {
			System.out.println("1");
		}

		public void mouseExited(MouseEvent evt) {
			System.out.println("1");
		}
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