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

	public static void changeScene(Scene scene) {
		Main.primaryStage.setScene(scene);
		Main.primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			Main.primaryStage = primaryStage;
			Main.primaryStage.setResizable(false);

			Main.primaryStage.setOnCloseRequest(e -> {
				Platform.exit();
				try {
					Main.game.loop.terminate();
				} catch (Exception err) {
				}
			});
			Main.game = new Game();

			new MainMenu();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
