package application;

import display.MainMenu;
import helper.Keyboard;
import helper.Loader;
import helper.Sound;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static final int WIDTH = 720, HEIGHT = 720;
	public static Stage primaryStage;
	public static Game game;
	public static MainMenu mainMenu;
	public static Sound atmosphere;
	public static Sound soundtrack;

	public static void changeScene(Scene scene) {
		Platform.runLater(()->
		{
			if(scene == mainMenu.scene) {
				if(!atmosphere.isPlaying()) atmosphere.startSound();
				if(soundtrack.isPlaying()) soundtrack.stopSound();
			}
			if(scene == game.scene) {
				if(!atmosphere.isPlaying()) atmosphere.startSound();
				if(!soundtrack.isPlaying()) soundtrack.stopSound();
			}
			primaryStage.setScene(scene);
			primaryStage.show();
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void startGame()
	{
		game = new Game();
		game.start();
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			Loader.init(this.getClass());

			Main.primaryStage = primaryStage;
			Main.primaryStage.setResizable(false);

			Main.primaryStage.setOnCloseRequest(e -> {
				Platform.exit();
				if (Main.game.loop != null)
						Main.game.loop.terminate();
			});

			Main.atmosphere = Loader.LoadSound("atmosphere.wav", 0.3, 100);
			Main.soundtrack = Loader.LoadSound("soundtrack.wav", 0.7, 95);

			Main.game = new Game();
			Main.mainMenu = new MainMenu();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
