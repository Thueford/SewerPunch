package helper;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import javafx.application.Platform;
import javafx.scene.image.Image;

/**
 * @author afeilke1
 *
 */
public class Loader {

	/**
	 * stores loaded images for reusing
	 */
	private static Map<String, Image> images = new HashMap<>();
	private static Map<String, BufferedImage> bufferedImages = new HashMap<>();
	private static Map<String, Sound> sounds = new HashMap<>();

	/**
	 * base path for image resources
	 */
	private static URL res_img, res_snd;

	public Loader(URL res_img, URL res_snd) {
		Loader.res_img = res_img;
		Loader.res_snd = res_snd;
	}

	public BufferedImage LoadBufferedImage(String src) {
		BufferedImage img = bufferedImages.get(src);
		if (img == null) {
			try {
				img = ImageIO.read(new URL(res_img, src).openStream());
			} catch (IOException e) {
				e.printStackTrace();
				Platform.exit();
			}
			bufferedImages.put(src, img);
		}
		return img;
	}

	/**
	 * load an image
	 * 
	 * @param src image source path
	 * @return javafx Image
	 * @throws IOException
	 */
	public Image LoadImage(String src) {
		Image img = images.get(src);
		if (img == null) {
			try {
				img = new Image(new URL(res_img, src).openStream());
			} catch (IOException e) {
				e.printStackTrace();
				Platform.exit();
			}
			images.put(src, img);
		}
		return img;
	}

	public Sound LoadSound(String src) {
		Sound snd = sounds.get(src);
		if (snd == null) {
			try {
				snd = new Sound(new URL(res_snd, src).getPath());
			} catch (IOException e) {
				e.printStackTrace();
				Platform.exit();
			}
			sounds.put(src, snd);
		}
		return snd;
	}
}
