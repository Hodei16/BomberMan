package Eredua;

import java.util.Timer;
import java.util.TimerTask;

public class BomberBeltza extends BomberMan{
	
	private Bonba bonba;
	
	public BomberBeltza(int pPosX, int pPosY) {
		super (pPosX, pPosY/*, 1*/);
		bonba = BonbaFactory.getNireBonbaFactory().createBonba(2, -1, -1); //CAMBIAR la POSICION
	}
	
	//public Bonba bonbaSortu() {
	//	return BonbaFactory.getNireBonbaFactory().createBonba(2/*, getPosX(), getPosY()*/);
	//}
	
	public boolean bonbaDauka() {
		return !(bonba == null);
	}
		
	
	@Override
	public void bonbaJarri() {
		EszenarioKudeatzailea.getNireEszenarioKudeatzailea().bonbaJarri(posX, posY, bonba);
		bonba = null;
		
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
	
	@Override
	protected void updateKont() {
		kont--;
		if(kont == 0) {
			timer.cancel();
			bonba = BonbaFactory.getNireBonbaFactory().createBonba(2, -1, -1); //CAMBIAR la POSICION
		}
	}
}


