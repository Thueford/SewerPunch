package application;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import display.Ressource;
import entities.Entity;
import entities.Player;
import helper.Keyboard;
import helper.Loader;
import helper.Sound;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

/**
 * @author docheron
 * Builds a new Scene, starts the Gameloop, maybe stores some variables, idk
 *
 */
public class Game {

	public class SpawnManagement {

		private int wave = 1, wavestate = 0; // wave increases
		Spawner spawner;

		public SpawnManagement() {
			spawner = new Spawner();
		}

		public void spawn() {

			System.out.println("\n");

			if (wavestate >= 10) {
				switch (wavestate) {

				case 10: {
					alert.startSound();
					wavestate++;
				}
				case 11: {
					if (alert.isPlaying())
						break;
					spawner.spawnWave(1 + (int) (wave * 0.5));
					wavestate++;
					return;
				}
				case 12: {
					for (Entity obj : getEntities()) {
						if (obj instanceof entities.Haribo)
							return;
						wave++;
						wavestate = 0;
						return;
					}
				}
				}
			} else {

				// in a 0.7 Chance spawns 2^wave*wavestate single enemys, otherwise spawns a
				// wave
				if ((int) (Math.random() * 10) <= 7) {

					spawner.spawnNotWave((wave + (int) (wavestate * Math.random())) % (wave * 5 - 1));
				} else {
					spawner.spawnWave(1 + (int) (wave * 0.25));
				}
				wavestate++;
			}
		}

	}

	public final Renderer renderer;

	public Loader loader;
	private final List<Entity> entities = new ArrayList<Entity>();
	// private static final Rectangle[] border = { new Rectangle(0, 0, 50, 720), new
	// Rectangle(670, 0, 60, 720) };
	public final int resource = 50;
	public Gameloop loop;
	public Random ran = new Random();

	public Ressource bots = new Ressource();

	private boolean empReady;
	private Sound atmosphere;
	public Sound soundtrack;
	private Sound emp;
	public Sound fillsnd;
	private Sound alert;

	/**
	 * Create renderer and loader instances
	 */
	public Game() {
		renderer = new Renderer();

		URL res = null;
		try {
			res = getClass().getResource("/");
			loader = new Loader(new URL(res, "../res/img/"), new URL(res, "../res/snd/"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			loader = null;
			Platform.exit();
		}
	}

	public Entity addEntity(Entity e) {
		synchronized (entities) {
			entities.add(e);
			// System.out.println("Entity added");
		}
		return e;
	}

	// biochemischer emp
	public void bcemp() {
		if (empReady) {
			emp.startSound();
			this.killAllEntities();
			// empReady = false;
		}
	}

	/**
	 * collision detection between all entities
	 */
	public void collide() {
		List<Entity> allEntities = Main.game.getEntities();
//		System.out.println(allEntities.toString());
		for (Entity a : allEntities) {
			if (a.dead == false) {
				boolean coll = false;
				for (Entity b : allEntities) {
					if (a != b && a.isCollidable() && b.isCollidable() && a.collides(b)) {

//					System.out.println(a.collides(b) + b.toString());
						a.collided = true;
						a.onCollide(b);
						coll = true;
					}
				}

				// for (Rectangle b : border) {
				if ((a.x > 10 || a.x < 0) && a instanceof entities.Haribo) {
					a.dead = true;
					a.die();
				}
				if ((a.y > 10) && (a instanceof entities.Haribo || a instanceof entities.Garbage)) {
					Main.game.removeEntity(a);
				}
				if ((a.y >= 9) && (a instanceof entities.Haribo || a instanceof entities.Garbage) && !a.isDead()) {
					// Platform.runLater( () -> new MainMenu() );
				}

				if (a.collided && !coll) {
					a.onUncollide();
					a.collided = false;
				}
			}
		}
	}

	public List<Entity> getEntities() {

		List<Entity> tmp = new ArrayList<Entity>();

		synchronized (entities) {
			for (Entity e : entities)
				tmp.add(e);
		}
		return tmp;
	}

	public void init() {
		// test
		for (int i = 0; i < 9; i++) {
			Main.game.addEntity(new Player(i, 9));
		}
		Main.game.addEntity(new display.Background(-1, 0));
		Main.game.addEntity(new display.Background(-1, -10));

		atmosphere = loader.LoadSound("atmosphere.wav");
		atmosphere.setVolume(0.3);
		atmosphere.setPriority(100);
		soundtrack = loader.LoadSound("soundtrack.wav");
		soundtrack.setVolume(0.7);
		soundtrack.setPriority(95);
		emp = loader.LoadSound("emp.wav");
		fillsnd = loader.LoadSound("refill.wav");
		alert = loader.LoadSound("alert.wav");
		// alert.setSpeed(8);
		// entities.get(0).sndSpawn.startSound();

		bots.setRes(50);
		empReady = true;

		Main.game.addEntity(new entities.Battery(-1.0, 4.5, resource));
	}

	public void killAllEntities() {

		List<Entity> tmp = this.getEntities();
		for (int i = 0; i < tmp.size(); i++) {
			if (tmp.get(i) instanceof entities.Haribo) {
				tmp.get(i).dead = true;
				tmp.get(i).die();

			}
		}
	}

	public void MainThreadFunctions(double time, double dtime) {
		Main.game.renderer.render(time, dtime);
		// SoundHandler.play(Soundlist);
		bots.showRes();

		if (!atmosphere.isPlaying()) {
			atmosphere.startSound();
		}
		if (!soundtrack.isPlaying()) {
			soundtrack.startSound();
		}
	}

	/**
	 * move all entities
	 */
	public void move(double dtime) {
		for (Entity obj : Main.game.getEntities()) {
			obj.move(dtime);

		}
	}

	public void Over() {
		// TODO show game over screen
	}

	public Entity removeEntity(Entity e) {
		synchronized (entities) {
			entities.remove(e);
		}
		return e;
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

		init();
		loop.start();
	}
}
