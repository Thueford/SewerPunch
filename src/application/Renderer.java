package application;

import javafx.scene.canvas.GraphicsContext;

public class Renderer {
	
	public GraphicsContext ctx = null;
	
	public static final Point 
		OFFSET = new Point(1,0),
		SPRITE_SIZE = new Point(60, 72),
		GRID_SIZE = new Point(10, 10);
	
	public Renderer() {
		
	}

	public void setContext(GraphicsContext ctx) {
		this.ctx = ctx;
	}
	
	public void render() {
		for(Entity e : Main.game.entities) {
			ctx.drawImage(e.img, e.x, e.y);
		}
	}
	
	public static Point toPixelCoord(Point gridCoord) {
		return new Point();
	}
	
	public static int xCoordToPixel(int x) {
		return 0; //(x + OFFSET.x)
	}
}
