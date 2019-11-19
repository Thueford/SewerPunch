package application;

import application.Game.SpawnManagement;
import helper.Timer;
import javafx.application.Platform;

public class Gameloop extends Thread {

	private boolean run = true;
	public boolean pause = false;
	private Timer globalT, paintT; // the two different timer-Objects
	private long fps = 60; // wait is temp-variable , framelenght determines length of one frame
	private double lastSpawnTime = 0, lastRenderTime = 0;

	SpawnManagement spmanager;

	/**
	 * The Gamecycle, operating in a different Thread then the JavaFX Main Thread.
	 * Calls Renderer on JavaFX Main Thread to invoke the painting of Ingame-Objects
	 */
	public Gameloop() {
		spmanager = Main.game.new SpawnManagement();
	}

	public void pause() {
		pause = !pause;
	}

	public void run() {

		System.out.println("Thread gestartet");
		globalT = new Timer();
		paintT = new Timer();

		globalT.step();
		paintT.step();
		globalT.step();
		paintT.step();

		while (run) {

			if (pause) {
				// Platform.runLater(() -> Main.game.soundtrack.stopSound() );
				continue;
			}

			globalT.step();

			// move Entities, move nanobots
			Main.game.move(globalT.d_sec());

			// every 2 seconds frames, a new Spawn is initialized
			if (globalT.sec() - lastSpawnTime >= 2) {
				spmanager.spawn();
				lastSpawnTime = globalT.sec();
			}

			// Collision detection
			Main.game.collide();

			globalT.step();
			paintT.step();
			// Paint changes on Canvas -> Only run this once every frame
			// apply framerate by time diff to lastRenderTime
			if (fps * (globalT.sec() - lastRenderTime) >= 1) {
				paintT.toc();
				Platform.runLater(() -> Main.game.MainThreadFunctions(paintT.sec(), paintT.d_sec()));

				paintT.tic();
				lastRenderTime = globalT.sec();
			}
		}
		System.out.println("Thread beendet");
	}

	public void terminate() {
		run = false;
	}

}
