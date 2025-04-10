package Eredua;
import java.util.Timer;
import java.util.TimerTask;

public abstract class BomberMan {
	private int bonbaKop;
	private int posX;
	private int posY;

	
	public BomberMan (int pPosX, int pPosY, int pBonbaKop) {
		this.posX = pPosX;
		this.posY = pPosY;
		this.bonbaKop = pBonbaKop;
	}
	
	public void setPosX(int pPosX) {
		this.posX = pPosX;
	}
	public void setPosY(int pPosY) {
		this.posY = pPosY;
	}
	public int getPosX() {
		return this.posX;
	}
	public int getPosY() {
		return this.posY;
	}
	public boolean bonbaDauka() {
		return bonbaKop!=0;
	}
	
	private static final int PERIODO = 4;
	private Timer timer = null;
	private int kont;
	
	public void bonbaKendu() {
		bonbaKop--;
		if(bonbaKop==0) {
			kont = PERIODO;
			TimerTask timerTask = new TimerTask() {
				@Override
				public void run() {
					updateKont();
				}
			};
			timer = new Timer();
			timer.scheduleAtFixedRate(timerTask, 0, 1000);}
		}
		
		private void updateKont() {
			kont--;
			if(kont == 0) {
				timer.cancel();
				bonbaKop++;
			}
		}
		
}


