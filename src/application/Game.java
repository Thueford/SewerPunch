package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import darstellung.Loader;
import entities.Player;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

/**
 * Builds a new Scene, starts the Gameloop, maybe stores some variables, idk
 *
 */
public class Game {

	public final Renderer renderer;
	public final Loader loader;
	private final List<Entity> entities = new ArrayList<Entity>();
	public final int resource = 50;
	public Gameloop loop;
	public Random ran = new Random();
	public double bots = 50;

	/**
	 * Create renderer and loader instances
	 */
	public Game() {
		renderer = new Renderer();
		loader = new Loader(getClass().getResource("img"));
	}

	/**
	 * Show and start game loop
	 */
	public void start() {

		Pane root = new Pane();

		final Canvas canvas = new Canvas(Main.HEIGHT, Main.WIDTH);
		final GraphicsContext ctx = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);

		renderer.setContext(ctx);

		Scene scene = new Scene(root, Main.HEIGHT, Main.WIDTH);
		new Keyboard(scene);
		Main.primaryStage.setScene(scene);
		Main.primaryStage.show();

		loop = new Gameloop();
		loop.start();

		// test
		Main.game.addEntity(new Player(1, 1));
		// renderer.render();
		// entities.get(0).sndSpawn.startSound();
	}

	public void MainThreadFunctions() {
		Main.game.renderer.render();
		// SoundHandler.play(Soundlist);
	}

	public class SpawnManagement {

		private int wave = 1, wavestate = 0; // wave increases
		Spawner spawner;

		public SpawnManagement() {
			spawner = new Spawner();
		}

		public void spawn() {

			if (wavestate == 10) { // 10 means last Wave of Stage - spawns big Wave, resets wavestate, increments
									// wave

				System.out.println("Ult wave");
				spawner.spawnWave(wave + 1);
				wave++;
				wavestate = 0;

				return;
			}
			// in a 0.7 Chance spawns 2^wave*wavestate single enemys, otherwise spawns a
			// wave
			if ((int) (Math.random() * 10) <= 7) {
				System.out.println("single spawns");

				spawner.spawnNotWave((int) Math.pow(2, wave * wavestate));
			} else {
				System.out.println("wave");

				spawner.spawnWave((int) (1 + wave * 0.5));
			}
			wavestate++;
		}

	}

	/**
	 * move all entities
	 */
	public void move(long dtime) {
		for (Entity obj : Main.game.getEntities()) {
			obj.move(dtime);
		}
	}

	/**
	 * collision detection between all entities
	 */
	public void collide() {

	}

	public Entity addEntity(Entity e) {
		synchronized (entities) {
			entities.add(e);
		}
		return e;
	}

	public void Over() {
		// TODO show game over screen
	}

	public List<Entity> getEntities() {

		List<Entity> tmp = new ArrayList<Entity>();

		synchronized (entities) {
			for (Entity e : entities)
				tmp.add(e);
		}
		return tmp;
	}
}
