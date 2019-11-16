package application;

public class Timer {
	
	public long starttime; //of Timer.class
	public long ltime; //time from when was last measured
	public long dtime; //delta-time - time spent between now and last call
	public long time;
	
	/**
	 * Constructor sets starttime to now, sets ltime to now (now = System.nanoTime() )
	 */
	public Timer() {
		starttime = System.nanoTime();
		ltime = starttime;
	}
	
	/**
	 * returns System.nanotime() and saves the value in the variable time
	 * @return
	 */
	public long newtime(){
		return time = System.nanoTime();
	}
	
	/**
	 * sets the value of ltime to now (System.nanoTime() ), sets dtime to 0
	 * @return
	 */
	public void newltime() {
		ltime = System.nanoTime();
		dtime = 0;
	}
	
	/**
	 * calculates the value of dtime (time passed since timestamp of ltime), writes over the 
	 * value of dtime and returns new dtime
	 */
	public long newdtime() {
		long time = System.nanoTime();
		dtime = time - ltime;
		return dtime;
	}
	

}
