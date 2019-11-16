package Sounds;

import javafx.scene.media.*;
import java.net.URL;

public class sound {
	private double volume;			//default: 0.3      possible: 0.0 to 1.0		0- mute, 1-extremely loud
	private double speed;			//default: 1		possible: 0.125 to 8		
	private int priority;			//default: 0		possible: any Integer
	private String src;
	private AudioClip audioClip;
	
	public sound(String src, double volume) {
		this.volume = volume;
		this.src = src;
		final URL resource = getClass().getResource(src);
		audioClip = new AudioClip(resource.toExternalForm());
		audioClip.setCycleCount(1);
		audioClip.setPriority(priority);
	}
	
	public double getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public String getSrc() {
		return src;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}

	//startet das spielen des sounds
	public void startSound() {
		audioClip.play(this.volume);
	}
	//stoppt das spielen des sounds
	public void stopSound() {
		audioClip.stop();
	}
	

}
