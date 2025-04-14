package Eredua;

import java.util.Timer;
import java.util.TimerTask;

public abstract class Bonba {
	EszenarioKudeatzailea eK = EszenarioKudeatzailea.getNireEszenarioKudeatzailea();
	protected static final int PERIODO = 3;
	protected Timer timer = null;
	protected int kont;
	protected int posX;
	protected int posY; 
	private boolean kontHasieratu = false;
	
	protected Bonba(int pPosX, int pPosY) {
		this.posX = pPosX;
		this.posY = pPosY;
		kont = PERIODO;
		
	}
	public void kontaketaHasi() {
		if(!kontHasieratu) {
			TimerTask timerTask = new TimerTask() {
				@Override
				public void run() {
					updateKont();
				}
			};
			timer = new Timer();
			timer.scheduleAtFixedRate(timerTask, 0, 1000);
			kontHasieratu = true;
		}
		
		
	}
	public abstract void updateKont();
		
	
}