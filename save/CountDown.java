package com.company;

import java.util.Timer;
import java.util.TimerTask;

public class CountDown {
	
	private static final long serialVersionUID = 2L;
	static boolean game = Main.game;
	int quaX = Main.quaX;
	int quaY = Main.quaY;
	boolean quad = Main.quad;
	double sincl = Main.sincl;

	public CountDown() {
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				if (Main.game) {
					if (Main.player % 2 == 0)
						Main.timeP2.decrement();
					else
						Main.timeP1.decrement();
				}
			}
		};
		timer.schedule(task, 0, 1000);

		TimerTask task2 = new TimerTask() {
			@Override
			public void run() {
				if (true) {// game){
					if (quaX > 0 && quad) {
						quaX--;
						quaY++;
					} else if (quaX < 100) {
						quad = false;
						quaX++;
						quaY--;
					} else
						quad = true;
				}
			}
		};
		timer.schedule(task2, 0, 10);

		TimerTask task3 = new TimerTask() {
			@Override
			public void run() {
				sincl += 0.3;
				if (sincl > 100000)
					sincl = 0;
			}
		};
		timer.schedule(task3, 0, 100);

		TimerTask task4 = new TimerTask() {
			@Override
			public void run() {
				// try {
				// Tone.sound(1000,100,0.5);
				// Tone.sound(1700,80,0.5);
				// } catch (LineUnavailableException e1) {
				// e1.printStackTrace();
				// }
			}
		};
		timer.schedule(task4, 0, 1800);

	}

//	public static void main(String[] args) {
//		new CountDown();
//	}
}