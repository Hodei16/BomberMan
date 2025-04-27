package Eredua;

import java.util.Timer;
import java.util.TimerTask;

public class Sua {
	JokoKudeatzailea jK = JokoKudeatzailea.getNireJokoKudeatzailea();
	private static final int PERIODO = 4;
	private Timer timer = null;
	private int kont;
	private int posX;
	private int posY;
	
	public Sua(int pPosX, int pPosY) {
		this.posX = pPosX;
		this.posY = pPosY;
		kont = PERIODO;
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				updateKont();
			}
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 0, 1000);
	}
	
	private void updateKont() {
		kont--;
		if(kont == 0) {
			timer.cancel();
			jK.kenduSua(posX, posY);
		}
	}
}
