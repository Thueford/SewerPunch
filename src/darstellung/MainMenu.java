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
	 * BorderPane, fertig gestylt als Hauptmenu
	 */
	public MainMenu() {
		
		
		this.setBottom(bottomButtons());
		this.setCenter(ButtonList());
		this.getStylesheets().add(getClass().getResource("mainmenu.css").toExternalForm());
		
	}
	/**
	 * Gibt eine VBox mit allen benötigten Buttons fuer das Hauptmenu zurueck
	 * @return
	 */
	private VBox ButtonList() {
		VBox liste = new VBox();
		Button start = new Button("Start");
		Button verlassen = new Button("Ende");
		
		start.setOnAction(e -> new application.Game().start());
		verlassen.setOnAction(e -> Platform.exit());
		
		liste.getChildren().addAll(start,verlassen);
		
		return liste;
	}
	
	/**
	 * Gibt AGB + Impressum Button in einer HBox zurueck
	 */
	private HBox bottomButtons() {
		Button agb = new Button("AGB");
		Button impressum = new Button("Impressum");
		agb.getStyleClass().add("bottomButton");
		//agb.setOnAction(e -> agb());
		impressum.getStyleClass().add("bottomButton");
		//impressum.setOnAction(e -> impressum());
		
		Region expander = new Region(); //soll die Buttons nach rechts und links schieben
		HBox.setHgrow(expander, Priority.ALWAYS);
		
		HBox unten = new HBox();
		unten.getChildren().addAll(agb,expander,impressum);
		return unten;

	}

}

