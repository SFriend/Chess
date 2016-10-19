package print;

import com.Game;

import java.util.Timer;
import java.util.TimerTask;

public class CountDown {

	private static final long serialVersionUID = 2L;
	private Game game;

	public CountDown(Game game) {
		this.game = game;
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				if (game.isRunning()) {
//					if (Main.timeP2.finish() || Main.timeP2.finish()) {
//						game.finish();
//					} else {
////						if (game.)
////							Main.timeP2.decrement();
////						else
////							Main.timeP1.decrement();
//					}
				}
			}
		};
		timer.schedule(task, 0, 1000);
	}
}