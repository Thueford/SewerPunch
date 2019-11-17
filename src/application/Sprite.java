package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {

	private static BufferedImage spriteSheet;
	private static final int TILE_SIZE = 60;

	public static BufferedImage getSprite(int xGrid, int yGrid, String file) {

		if (spriteSheet == null) {
			spriteSheet = Main.game.loader.LoadBufferedImage(file);
		}

		return spriteSheet.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
	}

}
