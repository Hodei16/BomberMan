package Eredua;

import java.util.Timer;
import java.util.TimerTask;

public class BomberBeltza extends BomberMan{
	
	private Bonba bonba;
	
	public BomberBeltza(int pPosX, int pPosY) {
		super (pPosX, pPosY/*, 1*/);
		this.bonba = BonbaFactory.getNireBonbaFactory().createBonba(2, -1, -1);
	}
		
	
	@Override
	public void bonbaJarri() {
		if (this.bonba != null) {
			this.bonba.setBonbaPos(posX, posY);
			EszenarioKudeatzailea.getNireEszenarioKudeatzailea().bonbaJarri(posX, posY, bonba);
			this.bonba = null;
			
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
	
	}
	
	@Override
	protected void updateKont() {
		kont--;
		if(kont == 0) {
			timer.cancel();
			this.bonba = BonbaFactory.getNireBonbaFactory().createBonba(2, -1, -1); 
		}
	}
}


