package darstellung;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class MainMenu extends BorderPane {

	/**
	 * A Borderpane, designed to be the MainMenu, linked to a stylesheet
	 */
	public MainMenu() {
		this.setId("root");
		this.setBottom(bottomButtons());
		this.setCenter(ButtonList());
		this.getStylesheets().add(getClass().getResource("mainmenu.css").toExternalForm());

	}

	/**
	 * Returns an VBox Object with all the buttons needed for main menu
	 * functionality
	 * 
	 * @return VBox
	 */
	private VBox ButtonList() {
		VBox liste = new VBox();
		Button start = new Button("Start");
		Button verlassen = new Button("Ende");

		
		start.setOnAction(e -> new Story());
		verlassen.setOnAction(e -> Platform.exit());

		liste.getChildren().addAll(start, verlassen);

		return liste;
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

}
