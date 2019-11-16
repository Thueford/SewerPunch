package application;

public class Rectangle {
	//lass die getter so wie sie sind!
	private Point startPoint;
	private double hoehe;
	private double breite;
	
	//Konstruktor
	public Rectangle(Point p1, double h, double b) {
		this.breite = b;
		this.hoehe = h;
		this.startPoint = p1;
	}
	public Rectangle(double x, double y, double h, double b) {
		Point p1 = new Point(x, y);
		this.breite = b;
		this.hoehe = h;
		this.startPoint = p1;
	}
	
	//getter
	public Point getStartPoint() {
		return startPoint;
	}
	public double getHoehe() {
		return hoehe;
	}
	public double getBreite() {
		return breite;
	}
	
	//rückgabe der eckpunkte
	public Point[] getEcken() {
		Point[] Pointe = new Point[4];
		Pointe[0] = this.startPoint;
		this.startPoint.verschieben(this.breite, 0);
		Pointe[1] = this.startPoint;
		this.startPoint.verschieben(0, this.hoehe);
		Pointe[2] = this.startPoint;
		this.startPoint.verschieben(-this.breite, 0);
		Pointe[3] = this.startPoint;
		this.startPoint.verschieben(0, -this.hoehe);
		return Pointe;
	}
	
	//versetzen des startpunktes
	public void versetzen(Point p1) {
		this.startPoint = p1;
	}
	//versetzen des startpunktes
	public void versetzen(double x, double y) {
		Point p1 = new Point(x, y);
		this.startPoint = p1;
	}
	
	//verschieben des startpunktes
	public void verschieben(int x, int y) {
		startPoint.verschieben(x, y);
	}
	
	//spiegeln des Rechteckes an der x achse
	public void spiegelnX() {
		Point[] Pointe = getEcken();
		for(int i = 0; i<4; i++) {
			Pointe[i].spiegelnX();
		}
	}
	//spiegeln des Rechteckes an der y achse
	public void spiegelnY() {
		Point[] Pointe = getEcken();
		for(int i = 0; i<4; i++) {
			Pointe[i].spiegelnY();
		}
	}
	
	//gibt eigenschaften des Rechteckes als String aus
	public String toString() {
		return "StartPoint: " + this.startPoint.toString() + ", Hoehe: " + Double.toString(this.hoehe) + ", Breite: " + Double.toString(this.breite);
	}
	
	//vergleicht ob sich 2 Rechtecke entsprechen
	public static boolean equals(Rectangle r1, Rectangle r2) {
		return ((r1.startPoint.equals(r2.startPoint)) && (r1.hoehe == r2.hoehe) && (r1.breite == r2.breite));
	}
	
	//prüft ob wenigstens ein eckpunkt von 2 Rechtecken sich entspricht
	public static boolean arePointsEqual(Rectangle r1, Rectangle r2) {
		Point[] Pointe1 = r1.getEcken();
		Point[] Pointe2 = r2.getEcken();
		boolean wahr = false;
		for(int i = 0; i<4; i++) {
			if(Pointe1[i].equals(Pointe2[i])) {
				wahr = true;
			}
		}
		return wahr;
	}
	
}
