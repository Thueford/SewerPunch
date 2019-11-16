package sounds;

import javafx.scene.media.*;

import java.io.File;

public class Sound {
	private double volume; // default: 0.3 possible: 0.0 to 1.0 0- mute, 1-extremely loud
	private double speed; // default: 1 possible: 0.125 to 8
	private int priority; // default: 0 possible: any Integer Background gets highest
	private String src;
	private AudioClip audioClip;

	public Sound(String src) {

		this.src = src;
		audioClip = new AudioClip(new File(src).toURI().toString());
		audioClip.setCycleCount(1);
		this.setPriority(0);
		this.setVolume(0.3);
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
		audioClip.setVolume(this.volume);
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
		audioClip.setRate(this.speed);
	}

	public String getSrc() {
		return src;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
		audioClip.setPriority(this.priority);
	}

	/**
	 * s tartet das spielen des sounds
	 */
	public void startSound() {
		audioClip.play();
	}

	/**
	 * stoppt das spielen des sounds
	 */
	public void stopSound() {
		audioClip.stop();
	}

}
