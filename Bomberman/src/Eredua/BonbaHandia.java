package Eredua;

public class BonbaHandia extends Bonba{
	public BonbaHandia(int pPosX, int pPosY) {
		super(pPosX, pPosY);
	}
		
	
	public void updateKont() {
		kont--;
		if(kont == 0) {
			kont = PERIODO;
			timer.cancel();
			eK.bonbaKendu(posX, posY, "BonbaHandia");
		}
	}
}
