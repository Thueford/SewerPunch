package application;

public class Vector {
	public double x;
	public double y;

	// Konstruktor
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Vector() {
		this.x = 0;
		this.y = 0;
	}

	// versetzt den Punkt an die gegebenen Koordinaten
	public Vector set(double newx, double newy) {
		this.x = newx;
		this.y = newy;
		return this;
	}

	public Vector set(Vector p) {
		set(p.x, p.y);
		return this;
	}

	// verschiebt den Punkt um die gegebenen werte
	public Vector add(double addx, double addy) {
		this.x += addx;
		this.y += addy;
		return this;
	}

	public Vector add(Vector p) {
		return add(p.x, p.y);
	}

	// Rückgabe des Punktes als String
	public String toString() {
		return "Point(" + this.x + ',' + this.y + ')';
	}

	// bestimmt den abstand zum Koordinatenursprung
	public double length() {
		return Math.sqrt((x * x) + (y * y));
	}

	// Winkel für Polarkoordinaten
	public double angle() {
		return Math.asin(x / length());
	}

	// Winkel für Polarkoordinaten
	public double angleDeg(double x, double y) {
		return Math.toDegrees(angle());
	}

	// überprüft ob sich 2 punkte entsprechen
	public boolean equals(Vector p) {
		return x == p.x && y == p.y;
	}

	public Vector copy() {
		return new Vector(this.x, this.y);
	}
}
