package helper;

import java.awt.image.BufferedImage;

import application.Main;

public class Sprite {

	public static BufferedImage getSprite(int xGrid, int yGrid, String file) {
		BufferedImage spriteSheet = Main.game.loader.LoadBufferedImage(file);
		Vector size = new Vector(60, 72);

		int p = file.lastIndexOf("_strip");
		if (p != -1) {
			file = file.substring(p + 6, file.indexOf('.'));
			try {
				p = file.indexOf('x');
				size.set(spriteSheet.getWidth() / Integer.parseInt(file.substring(0, p)),
						spriteSheet.getHeight() / Integer.parseInt(file.substring(p + 1)));
			} catch (StringIndexOutOfBoundsException e) {
				System.err.println(e.getMessage());
			}
		}

		return spriteSheet.getSubimage(xGrid * (int) size.x, yGrid * (int) size.y, (int) size.x, (int) size.y);
	}
}
