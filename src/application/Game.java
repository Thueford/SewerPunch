package application;

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
		
		Main.primaryStage.setScene(new Scene(root,Main.HEIGHT, Main.WIDTH));
		Main.primaryStage.show();
		
	}

}
