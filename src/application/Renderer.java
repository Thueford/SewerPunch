package application;

import entities.Player;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Renderer {
	
	public GraphicsContext ctx = null;
	
	public static final Point 
		OFFSET = new Point(10,0),
		SPRITE_SIZE = new Point(60, 72),
		GRID_SIZE = new Point(10, 10).add(OFFSET);
	
	public Renderer() {
		
	}

	public void setContext(GraphicsContext ctx) {
		this.ctx = ctx;
	}
	
	public void render() {
		ctx.clearRect(0, 0, Main.WIDTH, Main.HEIGHT);
		for(Entity e : Main.game.entities) {
			if(e.visible)
				ctx.drawImage(e.img, xCoordToPixel(e.x), yCoordToPixel(e.y));

		}
	}
	
	public static Point toPixelCoord(Point gridCoord) {
		return new Point(xCoordToPixel(gridCoord.x), yCoordToPixel(gridCoord.y));
	}
	
	public static double xCoordToPixel(double x) {
		return Main.WIDTH * (x + OFFSET.x) / GRID_SIZE.x;
	}
	
	public static double yCoordToPixel(double y) {
		return Main.HEIGHT * (y + OFFSET.y) / GRID_SIZE.y;
	}
}
