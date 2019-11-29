package display;

import application.Fistmanagement;
import application.Main;
import entities.Entity;

public class Ressource {
	private double res;

	public Ressource() {
		res = 50;
	}

	public void genRes(double add) {
		if (res + add > 50) {
			res = 50;
			return;
		} else {
			res += add;
//			Main.game.fillsnd.startSound();
		}		
	}

	public double getRes() {
		return res;
	}

	public void reduceRes(double red) {
		
		res -= red;
	}

	public void setRes(double r) {
		res = r;
	}

	public void showRes() {

		res = res < 0 ? 0 : res > Fistmanagement.resource_max ? 50 : res;

		for (Entity obj : Main.game.getEntities()) {
			if (obj instanceof entities.Battery) {
				Main.game.removeEntity(obj);
			}
		}
		Main.game.addEntity(new entities.Battery(0, 0, res));

	}
}
