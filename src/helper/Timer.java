package helper;

/**
 * @author afeilke1
 *
 */
public class Timer {

	public long startTime; // of Timer.class
	public long ltime; // time from when was last measured
	public long dtime; // delta-time - time spent between now and last call
	public long time;

	/**
	 * Constructor sets starttime to now, sets ltime to now (now = System.nanoTime()
	 * )
	 */
	public Timer() {
		startTime = ltime = System.nanoTime();
	}

	/**
	 * get time difference in seconds
	 * 
	 * @return time difference
	 */
	public double d_sec() {
		return dtime / 1e9;
	}

	/**
	 * Get elapsed time since initialization in seconds
	 * 
	 * @return seconds
	 */
	public double gameTime() {
		return (System.nanoTime() - startTime) / 1e9;
	}

	/**
	 * get time in seconds
	 * 
	 * @return seconds
	 */
	public double sec() {
		return time / 1e9;
	}

	/**
	 * do a toc() and tic() call at once
	 */
	public void step() {
		toc();
		tic();
	}

	/**
	 * save current timestamp to take diff at toc() from
	 */
	public void tic() {
		time = System.nanoTime();
	}

	/**
	 * save time difference from last tic call to dtime
	 */
	public void toc() {
		dtime = System.nanoTime() - ltime;
		ltime = time;
	}
}
