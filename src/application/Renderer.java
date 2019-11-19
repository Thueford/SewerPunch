package application;

import java.util.List;

import entities.Entity;
import helper.Rectangle;
import helper.Vector;
import javafx.scene.canvas.GraphicsContext;

/**
 * @author afeilke1
 * 
 */
public class Renderer {

	/**
	 * rendering offset in grid coordinates
	 */
	public static final Rectangle OFFSET = new Rectangle(1, 0, 1, 0);
	
	/**
	 * default sizes
	 */
	public static final Vector 
		SPRITE_SIZE = new Vector(60, 72),
		GRID_SIZE = new Vector(10, 10);

	/**
	 * Converts a grid coordinate to pixel coordinate
	 * @param gridCoord grid coordinate
	 * @return pixel coordinate
	 */
	public static Vector toPixelCoord(Vector gridCoord) {
		return new Vector(xCoordToPixel(gridCoord.x), yCoordToPixel(gridCoord.y));
	}

	/**
	 * Converts a grid X-coordinate to pixel coordinate
	 * @param x grid coordinate
	 * @return pixel coordinate
	 */
	public static double xCoordToPixel(double x) {
		return Main.WIDTH * (x + OFFSET.x) / (GRID_SIZE.x + OFFSET.width + OFFSET.x);
	}

	/**
	 * Converts a grid Y-coordinate to pixel coordinate
	 * @param y grid coordinate
	 * @return y pixel coordinate
	 */
	public static double yCoordToPixel(double y) {
		return Main.HEIGHT * (y + OFFSET.y) / (GRID_SIZE.y + OFFSET.height + OFFSET.y);
	}

	public GraphicsContext ctx = null;

	/**
	 * Render all entities
	 * @param time current time received by Gameloop.GameT
	 * @param dtime current dtime to last frame received by Gameloop.GameT
	 */
	public void render(double time, double dtime) {
		ctx.clearRect(0, 0, Main.WIDTH, Main.HEIGHT);
		
		// get and sort entity list
		List<Entity> tmp = Main.game.getEntities();
		tmp.sort((Entity a, Entity b) -> {
			return a.getDrawingOrder() > b.getDrawingOrder() ? 1 : -1;
		});
		
		// draw entities
		for (Entity e : tmp) {
			if (e.visible) {
				e.onAnimate(time, dtime);
				ctx.drawImage(e.img, xCoordToPixel(e.x), yCoordToPixel(e.y), xCoordToPixel(e.width) - xCoordToPixel(0),
						yCoordToPixel(e.height) - yCoordToPixel(0));
			}
		}
		/*
		// draw reference grid on background
		for (int x = 0; x <= 10; x++) {
			ctx.strokeRect(xCoordToPixel(x), yCoordToPixel(0), 0, yCoordToPixel(10) - yCoordToPixel(0));
			ctx.strokeRect(xCoordToPixel(0), yCoordToPixel(x), xCoordToPixel(10) - xCoordToPixel(0), 0);
		}*/
	}

	/**
	 * set the context to reder to
	 * @param ctx rendering context
	 */
	public void setContext(GraphicsContext ctx) {
		this.ctx = ctx;
		ctx.setLineWidth(1.0);
	}
}
