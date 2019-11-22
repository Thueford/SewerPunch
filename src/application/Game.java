package application;

import display.GameOverMenu;
import display.Ressource;
import entities.Entity;
import entities.Player;
import helper.Keyboard;
import helper.Loader;
import helper.Sound;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author docheron
 * Builds a new Scene, starts the Gameloop, maybe stores some variables, idk
 *
 */
public class Game {

	public Scene scene;

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

	private final List<Entity> entities = new ArrayList<>();
	// private static final Rectangle[] border = { new Rectangle(0, 0, 50, 720), new
	// Rectangle(670, 0, 60, 720) };
	public final int resource = 50;
	public Gameloop loop;
	public Random ran = new Random();

	public Ressource bots = new Ressource();

	private boolean empReady;
	private Sound emp;
	public Sound fillsnd;
	private Sound alert;

	/**
	 * Create renderer and loader instances
	 */
	public Game() {
		renderer = new Renderer();
	}

	public void addEntity(Entity e)
	{
		synchronized (this.entities) {
			this.entities.add(e);
		}
	}

	public void addEntities(Entity ...entities)
	{
		synchronized (this.entities) {
			this.entities.addAll(Arrays.asList(entities));
		}
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

		for (Entity a : allEntities) {
			if (!a.dead) {
				boolean coll = false;
				for (Entity b : allEntities) {
					if (a != b && a.isCollidable() && b.isCollidable() && a.collides(b)) {
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
					removeEntity(a);
					// if(!a.isDead()) Platform.runLater( () -> new MainMenu() );
				}

				if (!coll && a.collided) {
					a.onUncollide();
					a.collided = false;
				}
			}
		}
	}

	public List<Entity> getEntities() {
		synchronized (entities) {
			return new ArrayList<>(entities);
		}
	}

	public void init() {
		// test
		addEntity(new Player(4, 9));

		addEntities(
			new entities.Background(-Renderer.OFFSET.x, 0),
			new entities.Background(-Renderer.OFFSET.x, -10));

		emp = Loader.LoadSound("emp.wav");
		fillsnd = Loader.LoadSound("refill.wav");
		alert = Loader.LoadSound("alert.wav");
		// alert.setSpeed(8);
		// entities.get(0).sndSpawn.startSound();

		bots.setRes(50);
		empReady = true;
	}

	public void killAllEntities() {

		List<Entity> tmp = this.getEntities();
		for (Entity entity : tmp)
		{
			if (entity instanceof entities.Haribo)
			{
				entity.dead = true;
				entity.die();

			}
		}
	}

	public void MainThreadFunctions(double time, double dtime) {
		Main.game.renderer.render(time, dtime);
		// SoundHandler.play(Soundlist);
		bots.showRes();

		if (!Main.atmosphere.isPlaying()) Main.atmosphere.startSound();
		if (!Main.soundtrack.isPlaying()) Main.soundtrack.startSound();
	}

	/**
	 * move all entities
	 */
	public void move(double dtime) {
		for (Entity obj : Main.game.getEntities()) {
			obj.move(dtime);
			obj.onMove();}
	}

	public void Over() {
		new GameOverMenu().show();
	}

	public void removeEntity(Entity e) {
		synchronized (this.entities) {
			this.entities.remove(e);
		}
	}

	/**
	 * Show and start game loop
	 */
	public void start() {

		Pane root = new Pane();

		final Canvas canvas = new Canvas(Main.WIDTH, Main.HEIGHT);
		final GraphicsContext ctx = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);

		renderer.setContext(ctx);

		scene = new Scene(root, Main.WIDTH, Main.HEIGHT);
		Main.changeScene(scene);
		Keyboard.initScene(scene);

		loop = new Gameloop();

		init();
		loop.start();
	}
}
