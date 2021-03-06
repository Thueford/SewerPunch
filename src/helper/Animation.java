package helper;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gkruschw
 *
 */
public class Animation {

	private int frameCount; // Counts ticks for change
	private double frameDelay; // frame delay 1-12 (You will have to play around with this)
	private int currentFrame; // animations current frame
	private int animationDirection; // animation direction (i.e counting forward or backward)
	private int totalFrames; // total amount of frames for your animation
	private double lastFrameTime = 0;

	private boolean stopped; // has animations stopped

	private List<Frame> frames = new ArrayList<>(); // Arraylist of frames

	public Animation(BufferedImage[] frames, double frameDelay) {
		this.frameDelay = frameDelay;
		this.stopped = true;

		for (BufferedImage frame : frames)
			addFrame(frame, frameDelay);

		this.frameCount = 0;
		this.currentFrame = 0;
		this.animationDirection = 1;
		this.totalFrames = this.frames.size();

	}

	private void addFrame(BufferedImage frame, double duration) {
		if (duration <= 0) {
			System.err.println("Invalid duration: " + duration);
			throw new RuntimeException("Invalid duration: " + duration);
		}

		frames.add(new Frame(frame, duration));
		currentFrame = 0;
	}

	public BufferedImage getSprite() {
		return frames.get(currentFrame).getFrame();
	}

	public void reset() {
		this.stopped = true;
		this.frameCount = 0;
		this.currentFrame = 0;
	}

	public void restart() {
		if (frames.size() == 0)
			return;

		stopped = false;
		currentFrame = 0;
	}

	public void start() {
		if (!stopped)
			return;

		if (frames.size() == 0)
			return;

		stopped = false;
	}

	public void stop() {
		if (frames.size() == 0)
			return;

		stopped = true;
	}

	public void update(double time, double dtime) {

		if (!stopped) {
			frameCount++;

			if (time - lastFrameTime > frameDelay) {
				frameCount = 0;
				currentFrame += animationDirection;

				if (currentFrame > totalFrames - 1) {
					currentFrame = 0;
				} else if (currentFrame < 0) {
					currentFrame = totalFrames - 1;
				}
				lastFrameTime = time;
			}
			// */

		}

	}

}