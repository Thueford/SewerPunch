package application;

import darstellung.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	public static final int WIDTH = 720, HEIGHT = 720;
	public static Stage primaryStage;
	public static Game game;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Main.primaryStage = primaryStage;
			
			final BorderPane root = new MainMenu();
			final Scene scene = new Scene(root, WIDTH, HEIGHT);
			primaryStage.setScene(scene);
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
