package Eredua;


public class BomberBeltza extends BomberMan{
	
	public BomberBeltza(int pPosX, int pPosY) {
		super (pPosX, pPosY, 1);
	}
	public Bonba bonbaSortu() {
		return BonbaFactory.getNireBonbaFactory().createBonba(2, getPosX(), getPosY());
	}

		
}


