package Eredua;

public class BonbaTxikia extends Bonba{
	public BonbaTxikia(int pPosX, int pPosY) {
		super(pPosX, pPosY);
	}
	
	public void updateKont() {
		kont--;
		if(kont == 0) {
			kont = PERIODO;
			timer.cancel();
			eK.bonbaKendu(posX, posY, "BonbaTxikia");
		}
	}
}
