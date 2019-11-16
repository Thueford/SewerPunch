package application;

public class Gameloop {
	
	private boolean run = true, pause = true;
	private Timer globalT, paintT;
	private long wait = 0, framelength = 20000000;
	
	public Gameloop() {
		
		globalT = new Timer();
		paintT = new Timer();
				
		while(run) {
			
			if(pause) { continue; }
			
			globalT.newltime();
			long dtime = globalT.newdtime();
			
			System.out.println(dtime);
			
			//move Entities, move nanobots
			for(Entity obj : Main.game.entities) {
				//obj.move(dtime/);
			}
			
			//check for Collisions
						
			//Paint changes on Canvas -> Only run this once every frame
			if(System.nanoTime() >= paintT.time + wait) { //if wait + time exceeds nanoTime, the next Frame can be painted
				paintT.newltime();
				
				
				
				//calculates wait time, saves the timestamp of the time of calculation in 'time'
				wait = framelength -(paintT.newtime() - paintT.ltime);
			}
		}
		
	}
	
	public void start(){ pause = false; }
	public void stop(){ run = false; }
	public void pause(){ pause = pause?false:true;}

}
