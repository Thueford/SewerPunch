package application;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import darstellung.Loader;
import entities.Player;
import sounds.Sound;
import javafx.application.Platform;
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
	public Loader loader;
	private final List<Entity> entities = new ArrayList<Entity>();
	private static final Rectangle[] border = { new Rectangle(0, 0, 50, 720), new Rectangle(670, 0, 60, 720) };
	public final int resource = 50;
	public Gameloop loop;
	public Random ran = new Random();
	public double bots = 50;
	
	private Sound atmosphere;
	private Sound soundtrack;
	private Sound emp;
	
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

	public void MainThreadFunctions(double time, double dtime) {
		Main.game.renderer.render(time, dtime);
		// SoundHandler.play(Soundlist);
		
		if(!atmosphere.isPlaying()) {
			atmosphere.startSound();
		}
		if(!soundtrack.isPlaying()) {
			soundtrack.startSound();
		}
	}

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
					// AlertSound();
					wavestate++;
				}
				case 11: {
					System.out.println("Ult wave");
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
			}

			// in a 0.7 Chance spawns 2^wave*wavestate single enemys, otherwise spawns a
			// wave
			if ((int) (Math.random() * 10) <= 7) {
				System.out.println("single spawns");

				spawner.spawnNotWave((wave + (int) (wavestate * Math.random())) % (wave * 5 - 1));
			} else {
				System.out.println("wave");

				spawner.spawnWave(1 + (int) (wave * 0.25));
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

		for (Entity a : Main.game.getEntities()) {
			for (Entity b : Main.game.getEntities()) {
				if (a != b && a.getCollidable() && b.getCollidable() && a.collides(b)) {
					a.onCollide(b);
					b.onCollide(a);
				}
			}

			for (Rectangle b : border) {
				if (a.collides(b) && a instanceof entities.Haribo) {
					a.dead = true;
					a.die();
				}
			}
		}
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

	public void init() {
		// test
		Player p = new Player(4, 7);
		Main.game.addEntity(p);
		Main.game.addEntity(new darstellung.Background(0, 0));
		Main.game.addEntity(new darstellung.Background(0, -11));
		
		atmosphere = loader.LoadSound("atmosphere.wav");
		atmosphere.setVolume(0.3);
		atmosphere.setPriority(100);
		soundtrack = loader.LoadSound("soundtrack.wav");
		soundtrack.setVolume(0.3);
		soundtrack.setPriority(95);
		emp = loader.LoadSound("emp.wav");
		// renderer.render();
		// entities.get(0).sndSpawn.startSound();
	}

	// biochemischer emp
	public void bcemp() {
		emp.startSound();
		this.killAllEntities();
	}

	public void killAllEntities() {
		List<Entity> tmp = getEntities();
		for (int i = 0; i < tmp.size(); i++) {
			tmp.get(i).dead = true;
			tmp.get(i).die();
		}
	}
}
