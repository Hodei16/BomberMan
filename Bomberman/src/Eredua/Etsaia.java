package Eredua;

import java.util.Timer;
import java.util.TimerTask;

public class Etsaia {
	EszenarioKudeatzailea eK = EszenarioKudeatzailea.getNireEszenarioKudeatzailea();
	private static final int PERIODO = 2;
	private Timer timer = null;
	private int kont;
	private int posX;
	private int posY;
	
	public Etsaia(int pPosX, int pPosY) {
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
			kont = PERIODO;
			eK.etsaiaMugitu(posX ,posY);
		}
	}
	
	public void timerGelditu() {
		timer.cancel();
	}
	
	public void koordenatuakAldatu(int pX, int pY) {
		this.posX = pX;
		this.posY = pY;
	}
}
