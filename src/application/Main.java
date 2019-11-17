package application;

import darstellung.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static final int WIDTH = 720, HEIGHT = 720;
	public static Stage primaryStage;
	public static Game game;

	@Override
	public void start(Stage primaryStage) {

			Main.primaryStage = primaryStage;
			Main.game = new Game();
			
			new MainMenu();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
