package application;

import darstellung.Loader;
import javafx.scene.image.Image;

public class Entity extends Rectangle {
	
	public final String src;
	public final Image img;
	
	public Entity(String src, int x, int y, int width, int height) {
		super(x, y, width, height);
		
		this.x = x;
		this.y = y;
		
		this.width = width;
		this.height = height;
		
		this.src = src;
		this.img = Loader.LoadImage(src);
	}
	
	//Entitäten töten
	public void die() {
		
	}
}
