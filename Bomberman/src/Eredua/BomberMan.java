package Eredua;
import java.util.Timer;
import java.util.TimerTask;

public abstract class BomberMan {
	protected int posX;
	protected int posY;
	private boolean sutanDago = false;
    
	protected static final int PERIODO = 4;
	protected Timer timer = null;
	protected int kont;
	
	protected BomberMan (int pPosX, int pPosY) {
		this.posX = pPosX;
		this.posY = pPosY;
	}

	
	public abstract void bonbaJarri();
	protected abstract void updateKont();
	
			
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
	
	
	public void setSutanDago() {
		sutanDago = true;
	}
	public boolean getSutanDago() {
		return sutanDago;
	}
	
}


