package sounds;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SoundLoader {
	private static Map<String, Sound> assets = new HashMap<String, Sound>();

	private static URL res_sound;

	public SoundLoader(URL res_sound) {
		SoundLoader.res_sound = res_sound;
	}

	public static Sound LoadSound(String src) {
		Sound audioClip = assets.get(src);
		if (audioClip == null) {
			audioClip = new Sound(src);
			assets.put(src, audioClip);

		}
		return audioClip;
	}

}
