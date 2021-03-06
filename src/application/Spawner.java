package application;

import entities.Garbage;
import entities.Haribo;

/**
 * @author lwutke
 *
 */
public class Spawner {
	// wahrscheinlichkeiten für spawn in feldern
	private double[] arr = new double[10];

	// konstruktor
	public Spawner() {
		this.resetSpawnStuff();
	}

	/**
	 * select a field, based on a random value and the probability of the field
	 * (probabilities are based on previous spawns)
	 * 
	 * @return
	 */
	private int getField() {

		double j = (int) (Math.random() * 100);
		int feld = 0;

		// determine spawn-field - feld
		while (feld < 10) {
			j -= arr[feld];
			if (j < 0) {
				break;
			}
			feld++;
		}

		// save field-value in tmp and clear field
		double tmp = arr[feld];
		arr[feld] = 0;

		// evenly distribute former value of field to all fields, including field
		while (tmp > 0) {
			for (int i = 0; i < 10 && tmp > 0; i++) {
				arr[i] += 1;
				tmp--;
			}
		}
		return feld;
	}

	/**
	 * Fills Array with 10 Percent values
	 */
	public void resetSpawnStuff() {
		for (int i = 0; i < 10; i++) {
			arr[i] = 10;
		}
	}

	// erzeugt instanzen der klasse Entity/Haribo
	// feld ist die x Koordinate
	public void spawn(int feld) {
		int r = (int) (Math.random() * 100);
		if (r > 10) {
			Main.game.addEntity(new Haribo(feld, -2));
		} else {
			Main.game.addEntity(new Garbage(feld, -2));
		}
		// Main.game.entities.get(Main.game.entities.size()-1).sndSpawn.startSound();
	}

	// spawnt numOfEnemy an gegnern, hoffentlich gleichmäßig verteilt
	public void spawnNotWave(int numOfEnemy) {
		for (int i = 0; i < numOfEnemy; i++) {

			spawn(getField());
		}
	}

	// spawnt eine welle, vermutlich gleichmäßig verteilt
	public void spawnWave(int numOfWave) {
		// ändere rechnung sonst laggs
		int numOfEnemy = 5 * numOfWave;
		for (int i = 0; i < numOfEnemy; i++) {
			spawn(getField());
		}
	}
}
