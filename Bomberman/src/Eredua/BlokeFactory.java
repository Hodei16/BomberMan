package Eredua;

public class BlokeFactory {
	private static BlokeFactory nireBlokeFactory;
	
	private BlokeFactory() {}
	
	public static synchronized BlokeFactory getNirenireBlokeFactory() {
		if(nireBlokeFactory == null) {
			nireBlokeFactory = new BlokeFactory();
		}
		return nireBlokeFactory;
	}
	
	public Blokea create(int pMota) {
		Blokea nireBlokea;
		if (pMota == 1) {nireBlokea = new Biguna();}
		else {nireBlokea = new Gogorra();}
		return nireBlokea;
	}
}
