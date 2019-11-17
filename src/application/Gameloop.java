package application;

import application.Game.SpawnManagement;
import javafx.application.Platform;
import entities.Player;

public class Gameloop extends Thread {

	private boolean run = true; public boolean pause = false;
	private Timer globalT, paintT; // the two different timer-Objects
	private long wait = 0, framelength = 20000000; // wait is temp-variable , framelenght determines length of one frame
	private int lastspawn = 120;

	SpawnManagement spmanager;

	/**
	 * The Gamecycle, operating in a different Thread then the JavaFX Main Thread.
	 * Calls Renderer on JavaFX Main Thread to invoke the painting of Ingame-Objects
	 */
	public Gameloop() {
		spmanager = Main.game.new SpawnManagement();
	}

	public void run() {

		System.out.println("Thread gestartet");
		globalT = new Timer();
		paintT = new Timer();

		while (run) {

			if (pause) {
				Platform.runLater(() -> Main.game.soundtrack.stopSound() );
				continue;
			}

			globalT.newltime();
			long dtime = globalT.newdtime();

			// move Entities, move nanobots
			Main.game.move(dtime);

			if (lastspawn >= 120) { // every 120 frames, a new Span is initialized
				spmanager.spawn();
				lastspawn = 0;
			}

			// Collision detection
			Main.game.collide();

			// Paint changes on Canvas -> Only run this once every frame
			// if wait + time exceeds nanoTime, the next Frame can be painted
			if (System.nanoTime() >= paintT.time + wait) {
				paintT.newltime();
				Platform.runLater(() -> Main.game.MainThreadFunctions(globalT.getTimeSeconds(), globalT.getDTimeSeconds()));

				// calculates wait time, saves the timestamp of the time of calculation in 'time'
				wait = framelength - (paintT.newtime() - paintT.ltime);

				lastspawn++;
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
