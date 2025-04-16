package Eredua;
import java.util.Timer;
import java.util.TimerTask;

public abstract class BomberMan {
	//private int bonbaKop;
	protected int posX;
	protected int posY;
	private boolean sutanDago = false;
    
	protected static final int PERIODO = 4;
	protected Timer timer = null;
	protected int kont;
	
	protected BomberMan (int pPosX, int pPosY/*, int pBonbaKop*/) {
		this.posX = pPosX;
		this.posY = pPosY;
		//this.bonbaKop = pBonbaKop;
	}

	
	public abstract void bonbaJarri();
	protected abstract void updateKont();
	
			
			
			
	//protected abstract Bonba bonbaSortu();
	/*
	public Bonba getBonba() {
		return bonbaSortu();
	}
	*/
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
	
	public abstract boolean bonbaDauka();
	
	public void setSutanDago() {
		sutanDago = true;
	}
	public boolean getSutanDago() {
		return sutanDago;
	}
	/*
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
		*/
}


