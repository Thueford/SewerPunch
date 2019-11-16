package application;

import javafx.application.Platform;

public class Gameloop extends Thread {

	private boolean run = true, pause = false;
	private Timer globalT, paintT;
	private long wait = 0, framelength = 20000000;

	public Gameloop() {
		globalT = new Timer();
		paintT = new Timer();
	}

	/**
	 * 
	 */
	public void run() {

		System.out.println("Thread gestartet");

		while (run) {

			if (pause) {
				continue;
			}

			globalT.newltime();
			long dtime = globalT.newdtime();

			// System.out.println(dtime);

			// move Entities, move nanobots
			Main.game.move(dtime);

			// Collision detection
			Main.game.collide();

			// check for Collisions

			// Paint changes on Canvas -> Only run this once every frame
			if (System.nanoTime() >= paintT.time + wait) { // if wait + time exceeds nanoTime, the next Frame can be
															// painted
				paintT.newltime();

				Platform.runLater(() -> Main.game.renderer.render());

				// calculates wait time, saves the timestamp of the time of calculation in
				// 'time'
				wait = framelength - (paintT.newtime() - paintT.ltime);
			}
		}
		System.out.println("Thread beendet");
	}

	public void terminate() {
		run = false;
	}

	public void pause() {
		pause = pause ? false : true;
	}

}
