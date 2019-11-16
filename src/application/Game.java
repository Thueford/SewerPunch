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
		
	}
	
	public void move() {
		
	}
	
	public void collide() {
		
	}
}
