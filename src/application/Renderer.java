package application;

import entities.Player;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Renderer {

	public GraphicsContext ctx = null;

	public static final Vector 
		OFFSET_TL = new Vector(1, 0.5), 
		OFFSET_BR = new Vector(1, 0.5),
		SPRITE_SIZE = new Vector(60, 72), 
		GRID_SIZE = new Vector(10, 10);

	public Renderer() {

	}

	public void setContext(GraphicsContext ctx) {
		this.ctx = ctx;
	}

	public void render() {
		ctx.clearRect(0, 0, Main.WIDTH, Main.HEIGHT);
		for (Entity e : Main.game.getEntities()) {
			if (e.visible) {
				e.onAnimate();
				ctx.drawImage(e.img, xCoordToPixel(e.x), yCoordToPixel(e.y));
			}
		}
	}

	public static Vector toPixelCoord(Vector gridCoord) {
		return new Vector(xCoordToPixel(gridCoord.x), yCoordToPixel(gridCoord.y));
	}

	public static double xCoordToPixel(double x) {
		return Main.WIDTH * (x + OFFSET_TL.x) / (GRID_SIZE.x - OFFSET_BR.x);
	}

	public static double yCoordToPixel(double y) {
		return Main.HEIGHT * (y + OFFSET_TL.y) / (GRID_SIZE.y - OFFSET_BR.y);
	}
}
