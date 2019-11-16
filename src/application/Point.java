package application;


public class Point {
	public double x;
	public double y;
	
	//Konstruktor
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Point() {
		this.x = 0;
		this.y = 0;
	}
	
	//versetzt den Punkt an die gegebenen Koordinaten
	public Point set(double newx, double newy) {
		this.x = newx;
		this.y = newy;
		return this;
	}
	public Point set(Point p) {
		set(p.x, p.y);
		return this;
	}
	
	//verschiebt den Punkt um die gegebenen werte 
	public Point add(double addx, double addy) {
		this.x+=addx;
		this.y+=addy;
		return this;
	}

	public Point add(Point p) {
		return add(p.x, p.y);
	}
	
	//Rückgabe des Punktes als String
	public String toString() {
		return "Point(" + this.x + ',' + this.y + ')';
	}
	
	//bestimmt den abstand zum Koordinatenursprung
	public double length() {
		return Math.sqrt((x * x) + (y * y));
	}

	//Winkel für Polarkoordinaten
	public double angle() {
		return Math.asin(x / length());
	}
	
	//Winkel für Polarkoordinaten
	public double angleDeg(double x, double y) {
		return Math.toDegrees(angle());
	}
	
	//überprüft ob sich 2 punkte entsprechen
	public boolean equals(Point p) {
		return x == p.x && y == p.y;
	}
}
		
