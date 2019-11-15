package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;


public class Main extends Application {
	
	public final int WIDTH = 720, HEIGHT = 720;
	
	public final Renderer renderer;
	
	public Main() {
		renderer = new Renderer();
		loader = new Loader(getClass().getResource("img"));
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			final Pane root = new Pane();
			final Scene scene = new Scene(root, WIDTH, HEIGHT);
			primaryStage.setScene(scene);
			
			final Canvas canvas = new Canvas(WIDTH, WIDTH);
			final GraphicsContext ctx = canvas.getGraphicsContext2D();
			root.getChildren().add(canvas);
			
			renderer.setContext(ctx);
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
