package Eredua;

public class BonbaFactory {
	private static BonbaFactory nireBonbaFactory;
	
	private BonbaFactory() {}
	
	public static BonbaFactory getNireBonbaFactory() {
		if(nireBonbaFactory == null) {
			nireBonbaFactory = new BonbaFactory();
		}
		return nireBonbaFactory;
	}
	
	public Bonba createBonba(int pMota, int x, int y) {
		Bonba nireBonba = null;
		if (pMota == 1) {nireBonba = new BonbaTxikia(x,y);}
		else if (pMota == 2) {nireBonba = new BonbaHandia(x,y);}
		return nireBonba;
	}
}
