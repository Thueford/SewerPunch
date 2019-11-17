package application;

import entities.FistL;
import entities.FistR;

public class Fistmanagement {
	
	private static double resource = 50;
	private static boolean[] fistfromleft = new boolean[] {false, false, false, false}; //determines, if fist spawns from left or not
	private static boolean[] occupied = new boolean[] {false, false, false, false};
	
	public static void fistOut(int x, int y) { //x = line of fist, y = range of fist
		
		System.out.println("Out");
		
		if(occupied[x]) { return; }
		
		if(fistfromleft[x]) {
			FistL f = new FistL(x,0,y);
			Main.game.addEntity(f);
			occupied[x] = true; //set line occupied
		}else {
			FistR f = new FistR(x,11,y);
			Main.game.addEntity(f);
			occupied[x] = true; //set line occupied
		}
		
		
		
	}
	
	public static void fistBack(int x) {
		System.out.println("Back");
		if(!occupied[x]) { return;}
	}
	
	public static void update(){
		
	}

	/**
	 * Changes direction of spawn, line 1 is the highest one
	 * @param line
	 */
	public static void changeSide(int line) {
		fistfromleft[line+1] = fistfromleft[line+1]?false:true;
	}
}
