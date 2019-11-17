package application;

import entities.FistL;
import entities.FistR;

public class Fistmanagement {
	
	public static double resource = 50;
	private static boolean[] fistfromleft = new boolean[] {false, false, false, false}; //determines, if fist spawns from left or not
	private static boolean[] occupied = new boolean[] {false, false, false, false};
	
	public static void fistOut(int y, int x) { //y = line of fist, x = range of fist
		
		System.out.println("Out");
		
		if(occupied[y]) { return; }
		
		if(fistfromleft[y]) {
			FistL f = new FistL(y,0,x);
			Main.game.addEntity(f);
			occupied[y] = true; //set line occupied
		}else {
			FistR f = new FistR(y,11,x);
			Main.game.addEntity(f);
			occupied[y] = true; //set line occupied
		}
		
		
		
	}
	
	public static void fistBack(int y) {
		System.out.println("Back");
		if(!occupied[y]) { return;}
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
