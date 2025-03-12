package Eredua;

import java.util.Observable;

public class GelaxkaKudeatzailea extends Observable{
	private static final String Time = null;
	private BomberZuria bz=null;
	private Blokea b=null;
	private Bonba bonb=null;
	private Sua sua=null;
	
	public GelaxkaKudeatzailea() {}
	
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
	public void setBonba(Bonba pBonb) {
		this.bonb = pBonb;
		setChanged();
		notifyObservers(new String[] {"BonbaJarriBomber"});
	}
	public void kenduBonba() {
		this.bonb = null;
		setChanged();
		notifyObservers(new String[] {"KenduBonba"});
	}
	public boolean bonbaDago() {
		if (bonb != null) {
			return true;
		}else return false;
	}
	
	public void setBomberZuria(BomberZuria pBz) {
		this.bz = pBz;
	}
	
	public void kenduBomberZuria() {
		this.bz = null;
		if(!bonbaDago()) {
			setChanged();
			notifyObservers(new String[] {"KenduBomber"});
		}
		else {
			setChanged();
			notifyObservers(new String[] {"BonbaJarri"});
		}
	}
	
	public void setBlokea(Blokea pB) {
		this.b=pB;
	}
	public void kenduBlokea() {
		this.b = null;
	}
	public boolean bomberDago() {
		if(this.bz == null) {
			return false;
		}else {
			return true;
		}
	}
	public Sua getSua() {
		return this.sua;
	}
	public void setSua(Sua pSua) {
		this.sua = pSua;
		setChanged();
		notifyObservers(new String[] {"SuaJarri"});
		if(bomberDago()) {
			amaitu();
		}
	}
	public boolean suaDago() {
		if (this.sua != null) return true;
		else return false;
	}
	public void kenduSua() {
		this.sua = null;
		setChanged();
		notifyObservers(new String[] {"SuaKendu"});
	}

	public void bomberHeldu(BomberZuria bZ) {
		setBomberZuria(bZ);
		setChanged();
		notifyObservers(new String[] {"BomberHeldu"});
		if(suaDago()) {
			setChanged();
			notifyObservers(new String[] {"Galdu"});
		}
	}
	public void amaitu() {
		setChanged();
		notifyObservers(new String[] {"Galduta"});
	}
	
	public void irabazi() {
		setChanged();
		notifyObservers(new String[] {"Irabazi"});
	}
}
