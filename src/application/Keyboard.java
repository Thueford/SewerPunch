package application;

import entities.FistL;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.Scene;

public class Keyboard {
	// rows...
	int[] r1 = { 25, 26, 27, 28, 29, 30, 31, 32, 33, 24 };
	int[] r2 = { 52, 58, 40, 53, 55, 61, 56, 44, 50, 51 };
	int[] r3 = { 36, 54, 39, 41, 42, 43, 45, 46, 47, 187 };
	int[] r4 = { 60, 59, 38, 57, 37, 49, 48, 20, 22, 21 };
	final int cr1 = 143;
	final int cr2 = 2;
	final int cr3 = 9;
	final int cr4 = 5;
	final int enter = 0;
	final int pause = 10; // 10 for ESC and 11 for SPACE
	final int strg = 6;
	int[] defaultKeys = {};
	int[][] field = { r1, r2, r3, r4 };
	// int [] Keys = {cr1, cr2, cr3, cr4, enter, pause};

	public Keyboard(Scene scene) {
		// System.out.println(KeyCode.A.ordinal());

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				//System.out.println(event.getCode().ordinal());
				boolean checked = false;

				int[] point = { 99, 99 };
				for (int y = 0; y < 4; y++) {
					for (int x = 0; x < 10; x++) {
						
						if (field[y][x] == event.getCode().ordinal()) {
							point[0] = x;
							point[1] = y;
							checked = true;
						}
					}
				}

				if (checked == false) {
					switch (event.getCode()) {
					case CIRCUMFLEX:
						point[0] = 9;
						point[1] = 0;
						checked = true;
						Fistmanagement.changeSide(0);
						break;
						
					case TAB:
						point[0] = 9;
						point[1] = 1;
						checked = true;
						Fistmanagement.changeSide(1);
						break;
						
					case CAPS:
						point[0] = 9;
						point[1] = 2;
						checked = true;
						Fistmanagement.changeSide(2);
						break;
						
					case SHIFT:
						point[0] = 9;
						point[1] = 3;
						checked = true;
						Fistmanagement.changeSide(3);
						break;
						
					case ENTER:
						// fill ressources
						checked = true;
						break;
						
					case SPACE:
						// pause
						Main.game.loop.pause();
						checked = true;
						break;
						
					case CONTROL:
						// strg
						checked = true;
						break;
						

					default:
						// fill ressources idk do nothing
						//System.out.println("default");
						checked = true;
					}
					return; //when no fist-key was pressed, return
				}

				 
				// System.out.println(point[1]);
				Vector p = new Vector(point[0], point[1]);
				if (point[0] != 99 && point[1] != 99) {
					Fistmanagement.fistOut(point[1], point[0]);
					
					
				}
				
				scene.setOnKeyReleased(k ->{
					
					if(event.getCode() == k.getCode()) {
						Fistmanagement.fistBack(point[1]);
					}
					
				});
			}
		});
		
	}

}
