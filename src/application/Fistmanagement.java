package application;

import entities.FistL;
import entities.FistR;

public class Fistmanagement {
	
	public static double resource = 50;
	private static boolean[] fistfromleft = new boolean[] {false, false, false, false}; //determines, if fist spawns from left or not
	private static boolean[] occupied = new boolean[] {false, false, false, false};
	private static Entity[] fists = new Entity[4];
	
	public static void fistOut(int y, int x) { //y = line of fist, x = range of fist
		
		System.out.println("Out");
		
		if(occupied[y]) { return; }
		
		if(resource <= 10) {return; }
		
		resource -= 10;
		
		if(fistfromleft[y]) {
			FistL f = new FistL(y,0,x);
			Main.game.addEntity(f);
			fists[y] = f;
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
		
		resource = resource < 0?0:resource;
		resource = resource > 50?50:resource;
		
	}

	/**
	 * Changes direction of spawn, line 1 is the highest one
	 * @param line
	 */
	public static void changeSide(int line) {
		fistfromleft[line+1] = fistfromleft[line+1]?false:true;
	}
	
	public static void showResource() {
		
		for(Entity obj : Main.game.getEntities()) {
			if(obj instanceof entities.Battery) {
				Main.game.removeEntity(obj);
			}
		}
		Main.game.addEntity(new entities.Battery(650, 400, resource));
		
	}
}
