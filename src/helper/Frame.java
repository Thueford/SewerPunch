package helper;

import java.awt.image.BufferedImage;

/**
 * @author gkruschw
 *
 */
public class Frame {

	private BufferedImage frame;
	private double duration;

	public Frame(BufferedImage frame, double duration) {
		this.frame = frame;
		this.duration = duration;
	}

	public double getDuration() {
		return duration;
	}

	public BufferedImage getFrame() {
		return frame;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setFrame(BufferedImage frame) {
		this.frame = frame;
	}
}
