package Eredua;

import java.util.Timer;
import java.util.TimerTask;

public class Bonba {
	EszenarioKudeatzailea eK = EszenarioKudeatzailea.getNireEszenarioKudeatzailea();
	private static final int PERIODO = 3;
	private Timer timer = null;
	private int kont;
	
	public Bonba() {
		kont = PERIODO;
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				updateKont();
			}
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 0, 3000);
	}
	
	private void updateKont() {
		kont--;
		if(kont == 0) {
			kont = PERIODO;
			eK.bonbaKendu();
			timer.cancel();
		}
	}
}
