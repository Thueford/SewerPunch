package application;

public class Spawner {
	//breite des spielfeldes
		private static final int breite = 600;
		//höhe einer reihe
		private static final int hoeheFeld = 60;
		
		//wahrscheinlichkeiten für spawn in feldern
		private double[] arr = new double[10];
		
		//konstruktor
		public Spawner() {
			this.resetSpawnStuff();
		}
		
		//spawnt numOfEnemy an gegnern, hoffentlich gleichmäßig verteilt
		public void spawnNotWave(int numOfEnemy) {
			for(int i = 0; i<numOfEnemy; i++) {
				double ran = (int)Math.round(Math.random()*10);
				int feld=0; double j=ran;
				while(feld<10 && j>0) {
					j-=arr[feld];
					feld++;
				}
				double h = arr[feld];
				arr[feld] = 0;
				for(int z = 0; z<10; z++) {
					arr[z]+=h/10;
				}
				this.spawn(feld);
			}
		}
		
		//spawnt eine welle, vermutlich gleichmäßig verteilt
		public void spawnWave(int numOfWave) {
			//ändere rechnung sonst laggs
			int numOfEnemy = 30*numOfWave;
			for(int i = 0; i<10; i++) {
				for(int j = 0; j<numOfEnemy/10;j++) {
					spawn(i);
				}
			}
		}
		
		//erzeugt instanzen der klasse enemy
		public void spawn(int feld) {
			Enemy e = new Enemy("res/img/enemy.png", 1, 1, 1, 1);
		}
		
		public void resetSpawnStuff() {
			for(int i = 0; i<10; i++) {
				arr[i]=i;
			}
		}
}
