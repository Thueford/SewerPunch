package application;

import display.Ressource;
import entities.Entity;
import entities.FistL;
import entities.FistR;
import helper.Loader;
import helper.Sound;
import helper.Vector;

/**
 * @author docheron
 *
 */
public class Fistmanagement {

	public static Ressource resource = Main.game.bots;
	private static Sound imp_action;

	private static boolean[] fistfromleft = new boolean[] { true, true, true, true }; // determines, if fist spawns from
																						// left or not
	public static boolean[] occupied = new boolean[] { false, false, false, false }; // blocks line for inputs
	public static Entity[] fists = new Entity[4];
	public static final int resource_max = 50;

	/**
	 * Changes direction of spawn, line 0 is the one at the top
	 * 
	 * @param line
	 */
	public static void changeSide(int line) {
		if (occupied[line]) {
			return;
		}
		fistfromleft[line] = !fistfromleft[line];
	}

	public static void fistBack(int y) {
		if (!occupied[y]) {
			return;
		}

		if (fistfromleft[y]) {
			fists[y].speed.set(new Vector(-10, 0));
			resource.genRes(10);
		} else {
			fists[y].speed.set(new Vector(10, 0));
			resource.genRes(10);
		}
	}

	public static void fistOut(int y, int x) { // y = line of fist, x = range of fist

		if (occupied[y] || resource.getRes() <= 10) {
			if (imp_action == null) {
				imp_action = Loader.LoadSound("impossibleaction.wav");
			}
			imp_action.startSound();
			return;
		}

		resource.reduceRes(fistfromleft[y] ? x : 10 - x); // determines cost of action

		if (fistfromleft[y]) {
			FistL f = new FistL(-9, 5 + y, x - 9);
			Main.game.addEntity(f);
			fists[y] = f;
			occupied[y] = true; // set line occupied
		} else {
			FistR f = new FistR(9, 5 + y, x);
			Main.game.addEntity(f);
			fists[y] = f;
			occupied[y] = true; // set line occupied
		}
	}

	public static void occupied(int line) {
		occupied[line] = !occupied[line];
		System.out.println(occupied[line]);
	}
}
