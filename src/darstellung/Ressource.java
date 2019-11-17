package darstellung;

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
		res += add;
	}
	
	public void reduceRes (double red) {
		res -= red;
	}
	
	public void showRes () {
		
	}
}
