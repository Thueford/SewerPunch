package application;

import javafx.scene.canvas.GraphicsContext;

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
