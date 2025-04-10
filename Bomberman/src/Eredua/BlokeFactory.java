package Eredua;

public class BlokeFactory {
	private static BlokeFactory nireBlokeFactory;
	
	private BlokeFactory() {}
	
	public static BlokeFactory getNireBlokeFactory() {
		if(nireBlokeFactory == null) {
			nireBlokeFactory = new BlokeFactory();
		}
		return nireBlokeFactory;
	}
	
	public Blokea createBlokea(int pMota) {
		Blokea nireBlokea = null;
		if (pMota == 1) {nireBlokea = new Biguna();}
		else if (pMota == 2) {nireBlokea = new Gogorra();}
		return nireBlokea;
	}
}
