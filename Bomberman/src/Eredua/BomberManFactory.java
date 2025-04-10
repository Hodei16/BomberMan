package Eredua;

public class BomberManFactory {
	private static BomberManFactory nireBomberManFactory;
	
	private BomberManFactory() {}
	
	public static BomberManFactory getNireBomberManFactory() {
		if(nireBomberManFactory == null) {
			nireBomberManFactory = new BomberManFactory();
		}
		return nireBomberManFactory;
	}
	
	public BomberMan createBomberMan(int pMota) {
		BomberMan nireBomberMan = null;
		if (pMota == 1) {nireBomberMan = new BomberZuria(0,0);}
		else if (pMota == 2) {nireBomberMan = new BomberBeltza(0,0);}
		return nireBomberMan;
	}
}
