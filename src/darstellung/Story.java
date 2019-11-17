package darstellung;

import application.Main;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;

public class Story {
	
	private Label textarea;
	private int index = 0, counter = 0;
	private String[] story = new String[] {
		"In einer dystopischen Zukunft haben die Konzerne gewonnen.    ",
		"Jedoch ist ihnen ein fehlgeschlagenes Experiment entkommen: - Nanobots.   ",
		"Sie haben ein Bewusstsein entwickelt.   ",
		"Zusammen mit ihrem menschlichen Wirt versuchen sie nun, in die Fabrik einzudringen, und die Müll-magnate zu stürzen.    "
		};
		
	
	public Story() {
		BorderPane root = new BorderPane();
		root.setMinHeight(720);
		root.setMinWidth(720);
		root.setStyle("-fx-background-color: black");
		textarea = new Label();
		textarea.setStyle(
				"	-fx-font-family: serif;" + 
				"	-fx-font-weight: bold;" + 
				"	-fx-font-size: 15px;" + 
				"   -fx-text-fill: white;" +
				"   -fx-text-alignment: center;"
			);
		
		textarea.setWrapText(true);
		root.setCenter(textarea);

		Scene scene = new Scene(root, 720, 720);
		scene.setOnKeyPressed(e -> {
			
			//KeyEvent:Strg + G -> skips Intro
			if(new KeyCodeCombination(KeyCode.G,KeyCombination.CONTROL_DOWN).match(e)) startGame();
			step();
		});
		Main.primaryStage.setScene(scene);
		Main.primaryStage.show();
	}
	
	private void step() {
		//adds 1 to 3 characters to the textarea from current story-String
		String tmp = "";
		for(int i = 0;i <= (Math.random()*10)%4 && counter < story[index].length(); i++,counter++) {
			tmp = tmp + story[index].charAt(counter);
		}
		textarea.setText(textarea.getText() + tmp);
		//if counter exceeds the length of the story, test if there are more strings
		if(counter >= story[index].length()) {
			if(++index > story.length-1) startGame(); //start new Game
			//or reset the textarea and counter
			textarea.setText("");
			counter = 0;
		}
	}
	
	private void startGame() {
		Main.game = new application.Game();
		Main.game.start();
	}

}
