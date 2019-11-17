package application;

import java.awt.image.BufferedImage;

public class Sprite {

	private static BufferedImage spriteSheet;
	private static Vector size = new Vector(60, 72);

	public static BufferedImage getSprite(int xGrid, int yGrid, String file) {

		if (spriteSheet == null)
			spriteSheet = Main.game.loader.LoadBufferedImage(file);
		
		int p = file.lastIndexOf("_");
		if(p != -1) {
			file = file.substring(p + 1);
			p = file.indexOf('x');
			size.set(spriteSheet.getWidth() / Integer.parseInt(file.substring(0, p)), 
					spriteSheet.getHeight() / Integer.parseInt(file.substring(p + 1, file.indexOf('.'))));
		}

		return spriteSheet.getSubimage(xGrid * (int)size.x, yGrid * (int)size.y, (int)size.x, (int)size.y);
	}

}
