package application;

import java.rmi.registry.Registry;

public class Rectangle {
	// lass die getter so wie sie sind!
	public double x, y, width, height;

	// Konstruktor
	public Rectangle(Vector p, Vector size) {
		this.x = p.x;
		this.y = p.y;
		this.width = size.x;
		this.height = size.y;
	}

	public Rectangle(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	// rückgabe der eckpunkte
	public Vector[] getEcken() {
		Vector[] points = new Vector[2];
		points[0] = new Vector(x, y);
		points[1] = new Vector(x + width, y + height);
		return points;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public void set(Vector p) {
		set(p.x, p.y);
	}

	// versetzen des startpunktes
	public void set(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void add(Vector p) {
		add(p.x, p.y);
	}

	// versetzen des startpunktes
	public void add(double x, double y) {
		this.x += x;
		this.y += y;
	}

	// gibt eigenschaften des Rechteckes als String aus
	public String toString() {
		return "Rectangle(" + x + "," + y + "," + width + "," + height + ")";
	}

	// vergleicht ob sich 2 Rechtecke entsprechen
	public boolean equals(Rectangle r) {
		return x == r.x && y == r.y && width == r.width && height == r.height;
	}

	// prüft ob wenigstens ein eckpunkt von 2 Rechtecken sich entspricht
	public static boolean equal(Rectangle r1, Rectangle r2) {
		return r1.x == r2.x && r1.y == r2.y && r1.width == r2.width && r1.height == r2.height;
	}

//	public boolean collides(Rectangle r) {
//		return x < r.x + r.width && x + width > r.x && y < r.y + r.height && y + height > r.y;
//	}
	
	public boolean collides(Rectangle r) {
		return (x < r.x + r.width && x > r.x - width) && (y < r.y + r.height && y > r.y - height);
	}
}
