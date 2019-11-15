package application;

import javafx.scene.canvas.GraphicsContext;

public class Renderer {
	
	public GraphicsContext ctx = null;
	
	private int OX = 1, OY = 0, X = 60, Y = 72, WIDTH = 10, HEIGHT = 10;
	
	public Renderer() {
		
	}

	public void setContext(GraphicsContext ctx) {
		this.ctx = ctx;
	}
	
	public void render() {
		
	}
}
