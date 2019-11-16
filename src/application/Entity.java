package application;

import javafx.scene.image.Image;

public class Entity extends Rectangle {
	
	public int x, y, width, height;
	
	public final String src;
	
	private final Image img;
	
	public Entity(String src, int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		
		this.width = width;
		this.height = height;
		
		this.src = src;
		this.img = Loader.LoadImage(src);
	}
}
