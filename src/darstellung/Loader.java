package darstellung;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

public class Loader {
	
	private static Map<String, Image> assets = new HashMap<String, Image>();
	private static URL res_img;
	
	public Loader(URL res_img) {
		Loader.res_img = res_img;
	}
	
	public static Image LoadImage(String src) {
		Image img = assets.get(src);
		if(img == null) {
			img = new Image(src);
			assets.put(src, img);
		}
		return img;
	}
}
