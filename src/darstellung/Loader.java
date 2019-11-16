package darstellung;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

/**
 * @author afeilke1
 *
 */
public class Loader {

	/**
	 * stores loaded images for reusing
	 */
	private static Map<String, Image> assets = new HashMap<String, Image>();

	/**
	 * base path for image resources
	 */
	private static URL res_img;

	public Loader(URL res_img) {
		Loader.res_img = res_img;
	}

	/**
	 * load an image
	 * 
	 * @param src image source path
	 * @return javafx Image
	 */
	public static Image LoadImage(String src) {
		Image img = assets.get(src);
		if (img == null) {
			img = new Image(new File(src).toURI().toString());
			assets.put(src, img);
		}
		return img;
	}
}
