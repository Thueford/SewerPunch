package helper;

import javafx.application.Platform;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

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
	private static Path res_img, res_snd;

	public static void init(Class main) {
		try {
			Path res = Paths.get(main.getResource("/").toURI());

			while(!Files.isDirectory(res.resolve("res")))
				res = res.getParent();

			res_img = res.resolve("res/img/");
			res_snd = res.resolve("res/snd/");
		}
		catch (URISyntaxException e) {
			e.printStackTrace();
			Platform.exit();
		}
	}

	public static BufferedImage LoadBufferedImage(String src) {
		BufferedImage img = bufferedImages.get(src);
		if (img == null) {
			try {
				img = ImageIO.read(res_img.resolve(src).toFile());
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
	 */
	public static Image LoadImage(String src) {
		Image img = images.get(src);
		if (img == null) {
			try {
				img = new Image(new FileInputStream(res_img.resolve(src).toFile()));
			} catch (IOException e) {
				e.printStackTrace();
				Platform.exit();
			}
			images.put(src, img);
		}
		return img;
	}

	public static Sound LoadSound(String src) {
		Sound snd = sounds.get(src);
		if (snd == null) {
			snd = new Sound(res_snd.resolve(src).toString());
			sounds.put(src, snd);
		}
		return snd;
	}

	public static Sound LoadSound(String src, double volume)
	{
		Sound snd = LoadSound(src);
		snd.setVolume(volume);
		return snd;
	}

	public static Sound LoadSound(String src, double volume, int priority)
	{
		Sound snd = LoadSound(src);
		snd.setVolume(volume);
		snd.setPriority(priority);
		return snd;
	}
}
