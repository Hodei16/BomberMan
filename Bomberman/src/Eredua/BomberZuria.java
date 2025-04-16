package Eredua;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Timer;
import java.util.TimerTask;

public class BomberZuria extends BomberMan{
	
	private Queue<Bonba> bonbaKola = new LinkedList<>();
	
	public BomberZuria(int pPosX, int pPosY) {
		super (pPosX, pPosY/*, 10*/);
		
		for (int i = 0; i<10 ; i++) {
			bonbaKola.add(BonbaFactory.getNireBonbaFactory().createBonba(1,-1,-1));
		}
	}
	
	//public Bonba bonbaSortu() {
	//	return BonbaFactory.getNireBonbaFactory().createBonba(1/*, getPosX(), getPosY()*/);
	// }
	
	public boolean bonbaDauka() {
		return !bonbaKola.isEmpty();
	}
	
	@Override
	public void bonbaJarri() {
		if(!bonbaKola.isEmpty()) {
			Bonba jarritakoBonb = bonbaKola.remove();
			if(bonbaKola.isEmpty()) {
				kont = PERIODO;
				TimerTask timerTask = new TimerTask() {
					@Override
					public void run() {
						updateKont();
					}
				};
				timer = new Timer();
				timer.scheduleAtFixedRate(timerTask, 0, 1000);}
			
			jarritakoBonb.setBonbaPos(posX, posY);
			EszenarioKudeatzailea.getNireEszenarioKudeatzailea().bonbaJarri(posX, posY, jarritakoBonb);
		}
	}
	
	@Override
	protected void updateKont() {
		kont--;
		if(kont == 0) {
			timer.cancel();
			bonbaKola.add(BonbaFactory.getNireBonbaFactory().createBonba(1, -1,-1));
		}
	}
}


