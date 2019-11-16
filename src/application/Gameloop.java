package application;

import application.Game.SpawnManagement;
import javafx.application.Platform;

public class Gameloop extends Thread{
	
	private boolean run = true, pause = false;
	private Timer globalT, paintT; //the two different timer-Objects
	private long wait = 0, framelength = 20000000; //wait is temp-variable , framelenght determines length of one frame
	private int lastspawn = 0;
	
	SpawnManagement spmanager;
	
	/**
	 * The Gamecycle, operating in a different Thread then the JavaFX Main Thread. Calls Renderer on JavaFX Main
	 * Thread to invoke the painting of Ingame-Objects
	 */
	public Gameloop() {
		
		globalT = new Timer();
		paintT = new Timer();
		spmanager = Main.game.new SpawnManagement();
	}
	
	public void run() {
		
		System.out.println("Thread gestartet");
		
		while(run) {
						
			if(pause) { continue; }
			
			globalT.newltime();
			long dtime = globalT.newdtime();
			
			
			
			System.out.println(dtime);
			
			//move Entities, move nanobots
			for(Entity obj : Main.game.entities) {
				//obj.move(dtime/);
			}
			
			if(lastspawn >= 120) { //every 120 frames, a new Span is initialized
				spmanager.spawn();
				lastspawn = 0;
			}
			
			//check for Collisions
						
			//Paint changes on Canvas -> Only run this once every frame
			if(System.nanoTime() >= paintT.time + wait) { //if wait + time exceeds nanoTime, the next Frame can be painted
				paintT.newltime();
				
				Platform.runLater( () -> Main.game.MainThreadFunctions() );
				
				//calculates wait time, saves the timestamp of the time of calculation in 'time'
				wait = framelength -(paintT.newtime() - paintT.ltime);
				
				lastspawn++;
			}
		}
		System.out.println("Thread beendet");
		
	}
	
	public void terminate(){ run = false; }
	public void pause(){ pause = pause?false:true;}

}
