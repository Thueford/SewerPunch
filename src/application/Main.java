package application;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import darstellung.MainMenu;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {

	public static final int WIDTH = 720, HEIGHT = 720;
	public static Stage primaryStage;
	public static Game game;
	public static URI rootPath;

	@Override
	public void start(Stage primaryStage) {
		try {
			rootPath = getRoot();
			Main.primaryStage = primaryStage;

			final BorderPane root = new MainMenu();
			final Scene scene = new Scene(root, WIDTH, HEIGHT);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);

			primaryStage.setOnCloseRequest(e -> {
				Platform.exit();
				try {
					game.loop.terminate();
				} catch (Exception err) {
				}
			});
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	private URI getRoot() throws IOException, URISyntaxException {
	    URL u = getClass().getProtectionDomain().getCodeSource().getLocation();
	    return new File(u.toURI()).getParentFile().toURI();
	  }
}
