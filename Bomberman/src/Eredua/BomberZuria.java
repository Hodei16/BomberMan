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
		
		for (int i = 0; i < 10 ; i++) {
			bonbaKola.add(BonbaFactory.getNireBonbaFactory().createBonba(1,-1,-1));
		}
	}
	
	
	@Override
	public void bonbaJarri() {
		if(!bonbaKola.isEmpty()) {
			Bonba jarritakoBonb = bonbaKola.remove();
			jarritakoBonb.setBonbaPos(posX, posY);
			EszenarioKudeatzailea.getNireEszenarioKudeatzailea().bonbaJarri(posX, posY, jarritakoBonb);
			
			if(bonbaKola.isEmpty()) {
				
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


