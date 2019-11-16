package application;


public class Point {
	private double xCoord;
	private double yCoord;
	
	//Konstruktor
	public Point(double xCoord, double yCoord) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}
	
	public Point() {
		this.xCoord = 0;
		this.yCoord = 0;
	}
	
	//getter
	public double getxCoord() {
		return xCoord;
	}

	public double getyCoord() {
		return yCoord;
	}
	
	//versetzt den Punkt an die gegebenen Koordinaten
	public void versetzen(double newx, double newy) {
		this.xCoord = newx;
		this.yCoord = newy;
	}
	//verschiebt den Punkt um die gegebenen werte 
	public void verschieben(double addx, double addy) {
		this.xCoord+=addx;
		this.yCoord+=addy;
	}
	
	//spiegelt den punkt an der x achse
	public void spiegelnX() {
		this.yCoord -= 2 * this.yCoord; 
	}
	//spiegelt den Punkt
	public void spiegelnY() {
		this.xCoord -= 2 * this.xCoord; 
	}
	
	public String toString() {
		return '(' + Double.toString(this.xCoord) + ';' + Double.toString(this.yCoord) + ')';
	}
	
	public static double abstandZumUrsprung(double x, double y) {
		return Math.sqrt((x * x) + (y * y));
	}
	
	public static double Winkel(double x, double y) {
		return Math.toDegrees(Math.asin(x / abstandZumUrsprung(x, y)));
	}
	
	public static double abstandZumUrsprung(Point p) {
		return Math.sqrt((p.xCoord * p.xCoord) + (p.yCoord * p.yCoord));
	}
	
	public static double Winkel(Point p) {
		return Math.toDegrees(Math.asin(p.xCoord / abstandZumUrsprung(p.xCoord, p.yCoord)));
	}
	
	public static boolean equals(Point p1, Point p2) {
		return ((p1.xCoord == p2.xCoord) && (p1.yCoord == p2.yCoord));
	}
	
}
		