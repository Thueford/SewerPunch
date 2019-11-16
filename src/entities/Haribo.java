package entities;

public class Haribo extends application.Entity{
	
	private static final String src_sound = "";
	private static final String src_img = "";
	
	
	public Haribo(String src, int x, int y, int width, int height, double speed, int HP) {
		super(src_img, src_sound, x, y, width, height, speed, HP);
	}
}
