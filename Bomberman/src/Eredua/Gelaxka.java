package Eredua;

public class Gelaxka {
	private BomberZuria bz;
	private Blokea b;
	
	public Gelaxka(BomberZuria pBz, Blokea pB) {
		this.bz = pBz;
		this.b = pB;
	}
	
	public BomberZuria getBomberZuria() {
		return this.bz;
	}
	
	public Blokea getBlokea() {
		return this.b;
	}
	public boolean bomberDago() {
		if(this.bz == null) {
			return false;
		}else {
			return true;
		}
	}
	
}
