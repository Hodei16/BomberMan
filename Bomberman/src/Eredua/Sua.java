package Eredua;

import java.util.Timer;
import java.util.TimerTask;

public class Sua {
	EszenarioKudeatzailea eK = EszenarioKudeatzailea.getNireEszenarioKudeatzailea();
	private static final int PERIODO = 5;
	private Timer timer = null;
	private int kont;
	
	public Sua() {
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
			eK.kenduSua();
			timer.cancel();
		}
	}
}
