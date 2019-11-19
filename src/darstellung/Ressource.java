package darstellung;

import application.Entity;
import application.Fistmanagement;
import application.Main;

public class Ressource {
	private double res;
	
	public Ressource () {
		res = 50;
	}
	
	public double getRes () {
		return res;
	}
	
	public void setRes (double r) {
		res = r;
	}
	
	public void genRes (double add) {
		Main.game.fillsnd.startSound();
		res += add;
	}
	
	public void reduceRes (double red) {
		res -= red;
	}
	
	public void showRes () {
		
		res = res < 0 ? 0 : res > Fistmanagement.resource_max ? 50 : res;
		
		for (Entity obj : Main.game.getEntities()) {
			if (obj instanceof entities.Battery) {
				Main.game.removeEntity(obj);
			}
		}
		Main.game.addEntity(new entities.Battery(0, 0, res));

	}
}
