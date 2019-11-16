package application;

import java.util.ArrayList;
import java.util.List;

import darstellung.Loader;
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
	public final List<Entity> entities = new ArrayList<Entity>();
	public final int resource = 50;
	public Gameloop loop;
	
	public Game() {
		renderer = new Renderer();
		loader = new Loader(getClass().getResource("img"));
	}
	
	public void start() {
		
		Pane root = new Pane();
		
		final Canvas canvas = new Canvas(Main.HEIGHT, Main.WIDTH);
		final GraphicsContext ctx = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);
		
		renderer.setContext(ctx);
		
		Scene scene = new Scene(root,Main.HEIGHT, Main.WIDTH);
		new Keyboard(scene);
		Main.primaryStage.setScene(scene);
		Main.primaryStage.show();
		
		loop = new Gameloop();
		loop.start();
		
		// test
		new Spawner().spawn(0);
		//renderer.render();
		entities.get(0).sndSpawn.startSound();
	}

	public void MainThreadFunctions() {
		Main.game.renderer.render();
		//SoundHandler.play(Soundlist);
	}
	
	public class SpawnManagement {
		
		private int wave = 1, wavestate = 0; //wave increases
		Spawner spawner;
		
		public SpawnManagement() { spawner = new Spawner(); }
		
		public void spawn(){
			
			if(wavestate == 10) {
				spawner.spawnWave(wave+1);
				return;
			}
			if((int)(Math.random() * 10) >= 7) {
				spawner.spawnNotWave((int)Math.pow(2, wave*wavestate));
			}else {
				spawner.spawnWave((int) (1+wave*0.5));
			}
		}
		
	}
	
	public void move() {
		
	}
	
	public void collide() {
		
	}
}
