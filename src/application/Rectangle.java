package application;

public class Rectangle {
	//lass die getter so wie sie sind!
	public double x, y, width, height;
	
	//Konstruktor
	public Rectangle(Point p, Point size) {
		this.x = p.x;
		this.y = p.y;
		this.width = size.x;
		this.height = size.y;
	}
	
	public Rectangle(double x, double y, double width, double height) {
		this.height = height;
		this.width = width;
		this.x = x;
		this.y = y;
	}
	
	//rückgabe der eckpunkte
	public Point[] getEcken() {
		Point[] points = new Point[2];
		points[0] = new Point(x, y);
		points[1] = new Point(x + width, y + height);
		return points;
	}
	
	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}
	
	//versetzen des startpunktes
	public void move(Point p) {
		move(p.x, p.y);
	}
	
	//versetzen des startpunktes
	public void move(double x, double y) {
		Point p1 = new Point(x, y);
	}
	
	//gibt eigenschaften des Rechteckes als String aus
	public String toString() {
		return "Rectangle(" + x+","+y+","+width+","+height+ ")";
	}
	
	//vergleicht ob sich 2 Rechtecke entsprechen
	public boolean equals(Rectangle r) {
		return x == r.x && y == r.y && width == r.width && height == r.height;
	}
	
	//prüft ob wenigstens ein eckpunkt von 2 Rechtecken sich entspricht
	public static boolean equal(Rectangle r1, Rectangle r2) {
		return r1.x == r2.x && r1.y == r2.y && r1.width == r2.width && r1.height == r2.height;
	}
	
	public boolean collides(Rectangle r) {
		return x < r.x + r.width && x + width < r.x &&
				y < r.y + r.height && y + width < r.y;
	}
}
