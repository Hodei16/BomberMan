package Eredua;

public class Gelaxka {
	private BomberZuria bz;
	private Blokea b;
	
	public Gelaxka() {
		
	}
	
	public BomberZuria getBomberZuria() {
		return this.bz;
	}
	
	public Blokea getBlokea() {
		return this.b;
	}
	public boolean blokeaDago() {
		if (b != null) {
			return true;
		}
		else return false;
	}
	
	public void setBomberZuria(BomberZuria pBz) {
		this.bz = pBz;
	}
	public void kenduBomberZuria() {
		this.bz = null;
	}
	
	public void setBlokea(Blokea pB) {
		this.b=pB;
	}
	
	public boolean bomberDago() {
		if(this.bz == null) {
			return false;
		}else {
			return true;
		}
	}
	
}
