package application;

import entities.Player;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Renderer {
	
	public GraphicsContext ctx = null;
	
	public static final Point 
		OFFSET = new Point(1,0),
		SPRITE_SIZE = new Point(60, 72),
		GRID_SIZE = new Point(10, 10).add(OFFSET);
	
	public Renderer() {
		
	}

	public void setContext(GraphicsContext ctx) {
		this.ctx = ctx;
	}
	
	public void render() {
		for(Entity e : Main.game.entities) {
			if(e.visible)
				ctx.drawImage(e.img, xCoordToPixel(e.x), yCoordToPixel(e.y));
				//Image img = ((Player)e).animation.getSprite().getScaledInstance(-1, -1, Image);
				//ctx.drawImage(, 2, 2, null);
				Image image = SwingFXUtils.toFXImage(((Player)e).animation.getSprite(), null);
				ctx.drawImage(image, 2, 2);

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
