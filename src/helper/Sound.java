package helper;

import java.io.File;

import javafx.scene.media.AudioClip;

public class Sound {
	private double volume = 1; // 0.0 .. 1.0
	private double speed = 1; // 0.125 .. 8
	private int priority = 0; // playin gorder (the lower the value, the earlier played)
	private String src;
	private AudioClip audioClip;

	public Sound(String src) {
		this.src = src;
		audioClip = new AudioClip(new File(src).toURI().toString());
		audioClip.setCycleCount(1);
		this.setPriority(this.priority);
		this.setVolume(this.volume);
	}

	public int getPriority() {
		return priority;
	}

	public double getSpeed() {
		return speed;
	}

	public String getSrc() {
		return src;
	}

	public double getVolume() {
		return volume;
	}

	public boolean isPlaying() {
		return audioClip.isPlaying();
	}

	public void setPriority(int priority) {
		this.priority = priority;
		audioClip.setPriority(this.priority);
	}

	public void setSpeed(int speed) {
		this.speed = speed;
		audioClip.setRate(this.speed);
	}

	public void setVolume(double volume) {
		this.volume = volume;
		audioClip.setVolume(this.volume);
	}

	/**
	 * startet das spielen des sounds
	 */
	public void startSound() {
		if (!audioClip.isPlaying())
			audioClip.play();
	}

	/**
	 * stoppt das spielen des sounds
	 */
	public void stopSound() {
		if (audioClip.isPlaying())
			audioClip.stop();
	}

}
