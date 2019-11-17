package application;

import java.util.List;

import javafx.scene.canvas.GraphicsContext;

public class Renderer {

	public GraphicsContext ctx = null;

	public static final Vector 
		OFFSET_TL = new Vector(1, 0.5),
		OFFSET_BR = new Vector(1, 0.5),
		SPRITE_SIZE = new Vector(60, 72),
		GRID_SIZE = new Vector(10, 10);

	public void setContext(GraphicsContext ctx) {
		this.ctx = ctx;
		ctx.setLineWidth(1.0);
	}

	public void render(double time, double dtime) {
		ctx.clearRect(0, 0, Main.WIDTH, Main.HEIGHT);
		List<Entity> tmp = Main.game.getEntities();
		tmp.sort((Entity a, Entity b) -> {
			return a.getDrawingOrder() > b.getDrawingOrder() ? 1 : -1;
		});
		
		for(int x=0;x<10;x++) {
			ctx.strokeRect(xCoordToPixel(x), yCoordToPixel(0), 0, yCoordToPixel(10));
		}
		for(int x=0;x<10;x++) {
			ctx.strokeRect(xCoordToPixel(0), yCoordToPixel(x), xCoordToPixel(10), 0);
		}
        
		for (Entity e : tmp) {
			if (e.visible) {
				e.onAnimate(time, dtime);
				ctx.drawImage(e.img, xCoordToPixel(e.x), yCoordToPixel(e.y), xCoordToPixel(e.width), yCoordToPixel(e.height));
			}
		}
	}

	public static Vector toPixelCoord(Vector gridCoord) {
		return new Vector(xCoordToPixel(gridCoord.x), yCoordToPixel(gridCoord.y));
	}

	public static double xCoordToPixel(double x) {
		return Main.WIDTH * (x + OFFSET_TL.x) / (GRID_SIZE.x + OFFSET_BR.x + OFFSET_TL.x);
	}

	public static double yCoordToPixel(double y) {
		return Main.HEIGHT * (y + OFFSET_TL.y) / (GRID_SIZE.y + OFFSET_BR.y + OFFSET_TL.y);
	}
}
