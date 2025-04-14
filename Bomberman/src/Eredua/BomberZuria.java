package Eredua;


public class BomberZuria extends BomberMan{
	
	public BomberZuria(int pPosX, int pPosY) {
		super (pPosX, pPosY, 10);
	}
	public Bonba bonbaSortu() {
		return BonbaFactory.getNireBonbaFactory().createBonba(1, getPosX(), getPosY());
		
	}

		
}


