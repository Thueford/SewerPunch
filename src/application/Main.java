package application;

import darstellung.MainMenu;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static final int WIDTH = 720, HEIGHT = 720;
	public static Stage primaryStage;
	public static Game game;

	@Override
	public void start(Stage primaryStage) {

			Main.primaryStage = primaryStage;
			Main.primaryStage.setOnCloseRequest(e -> {
				Platform.exit();
				try {
					Main.game.loop.terminate();
				}catch (Exception err) {}		
			});
			Main.primaryStage.setResizable(false);
			Main.game = new Game();
			
			new MainMenu();
	}

	public static void main(String[] args) {
		launch(args);
	}
	public static void changeScene(Scene scene) {
		Main.primaryStage.setScene(scene);
		Main.primaryStage.show();
	}
}
