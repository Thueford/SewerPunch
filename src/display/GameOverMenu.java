package display;

import application.Main;
import helper.Loader;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

/**
 * @author docheron
 */
public class GameOverMenu extends BorderPane {

    public Scene scene;

    /**
     * A Borderpane, designed to be the MainMenu, linked to a stylesheet
     */
	public GameOverMenu() {
        this.setId("root");
        this.setBottom(bottomButtons());
        this.setCenter(ButtonList());
        this.getStylesheets().add(getClass().getResource("mainmenu.css").toExternalForm());
        this.setBackground(new Background(new BackgroundImage(
        	Loader.LoadImage("Titelbildschirm_V1.png"), BackgroundRepeat.NO_REPEAT,
			BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));

        this.scene = new Scene(this, Main.WIDTH, Main.HEIGHT);
        Main.changeScene(this.scene);
    }

    public void show()
    {
        Main.changeScene(scene);
    }

    /**
     * Returns an HBox Object with two Buttons and a Region as Expander
     *
     * @return HBox
     */
	private HBox bottomButtons() {
        Button agb = new Button("AGB");
        Button impressum = new Button("Impressum");
        agb.getStyleClass().add("bottomButton");
        // agb.setOnAction(e -> agb());
        impressum.getStyleClass().add("bottomButton");
        // impressum.setOnAction(e -> impressum());

        Region expander = new Region(); // presses the button to the sides
        HBox.setHgrow(expander, Priority.ALWAYS);

        HBox unten = new HBox();
        unten.getChildren().addAll(agb, expander, impressum);
        return unten;

    }

    /**
     * Returns an VBox Object with all the buttons needed for main menu
     * functionality
     *
     * @return VBox
     */
	private VBox ButtonList() {
        VBox liste = new VBox();
        Button start = new Button("Play again");
        Button verlassen = new Button("Back to Main Menu");

        start.setOnAction(e -> Main.startGame());
        verlassen.setOnAction(e -> Main.mainMenu.show());

        liste.getChildren().addAll(start, verlassen);

        return liste;
    }

}
