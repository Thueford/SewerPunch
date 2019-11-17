package application;

import entities.FistL;
import entities.FistR;

public class Fistmanagement {

	public static double resource = 50;

	private static boolean[] fistfromleft = new boolean[] {false, false, false, false}; //determines, if fist spawns from left or not
	private static boolean[] occupied = new boolean[] {false, false, false, false}; //blocks line for inputs
	private static Entity[] fists = new Entity[4];
	
	public static void fistOut(int y, int x) { //y = line of fist, x = range of fist
		
		if(occupied[y]) { return; }
		
		if(resource <= 10) {return; }
		
		resource -= 10;
		
		if(!fistfromleft[y]) {
			FistL f = new FistL(0,5,x);
			Main.game.addEntity(f);
			fists[y] = f;
			occupied[y] = true; //set line occupied
		}else {
			FistR f = new FistR(9,y,x);
			Main.game.addEntity(f);
			fists[y] = f;
			occupied[y] = true; //set line occupied
		}
	}

	public static void fistBack(int y) {
		if(!occupied[y]) { return;}
		
		if(!fistfromleft[y]) {
			fists[y].setSpeed(new Vector(-2,0));
			fists[y] = null;
			occupied[y] = false; //set line free
		}else {
			fists[y].setSpeed(new Vector(2,0));;
			fists[y] = null;
			occupied[y] = false; //set line free
		}
	}

	public static void update() {
		resource = resource < 0 ? 0 : resource;
		resource = resource > 50 ? 50 : resource;
	}

	/**
	 * Changes direction of spawn, line 0 is the one at the top
	 * @param line
	 */
	public static void changeSide(int line) {
		if(occupied[line]) { return; }
		fistfromleft[line] = fistfromleft[line]?false:true;
	}

	public static void showResource() {

		for (Entity obj : Main.game.getEntities()) {
			if (obj instanceof entities.Battery) {
				Main.game.removeEntity(obj);
			}
		}
		Main.game.addEntity(new entities.Battery(650, 400, resource));

	}
}