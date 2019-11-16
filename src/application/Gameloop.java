package application;

public class Gameloop {
	
	private boolean run = true;
	private Timer globalT, paintT;
	private long wait, framelength = 20000000;
	
	public Gameloop() {
		
		globalT = new Timer();
		paintT = new Timer();
				
		while(run) {
			globalT.newltime();
			long dtime = globalT.newdtime();
			
			
			//Only run this once every frame
			if(System.nanoTime() >= paintT.time + wait) { //if wait + time exceeds nanoTime, the next Frame can be painted
				paintT.newltime();
				
				//calculates wait time, saves the timestamp of the time of calculation in 'time'
				wait = framelength -(paintT.newtime() - paintT.ltime);
			}	
		}
		
	}

}
