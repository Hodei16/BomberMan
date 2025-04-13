package Eredua;

import java.util.Observable;

public class GelaxkaKudeatzailea extends Observable{
	private BomberMan b=null;
	private Blokea blok=null;
	private Bonba bonb=null;
	private Sua sua=null;
	private Etsaia e=null;
	
	public GelaxkaKudeatzailea() {}
	
	public BomberMan getBomberMan() {
		return this.b;
	}
	
	public Blokea getBlokea() {
		return this.blok;
	}
	public boolean blokeaDago() {
		if (blok != null) {
			return true;
		}
		else return false;
	}
	public void setBonba(Bonba pBonb) {
		this.bonb = pBonb;
		if (b instanceof BomberZuria) {
			setChanged();
			notifyObservers(new String[] {"BonbaJarriBomberZuria"});
		}else {
			setChanged();
			notifyObservers(new String[] {"BonbaJarriBomberBeltza"});
		}
	}
	public void kenduBonba() {
		this.bonb = null;
		setChanged();
		notifyObservers(new String[] {"KenduIrudia"});
	}
	public boolean bonbaDago() {
		if (bonb != null) {
			return true;
		}else return false;
	}
	
	public void setBomberZuria(BomberMan pBz) {
			this.b = pBz;
	}
	
	public void kenduBomberZuria() {
		int intBomberMan = 1;
		if (this.b instanceof BomberBeltza) { intBomberMan = 2;}
		this.b = null;
		if(!bonbaDago()) {
			setChanged();
			notifyObservers(new String[] {"KenduIrudia"});
		}else {
			if (intBomberMan == 1) {
				setChanged();
				notifyObservers(new String[] {"BonbaZuria"});
			}else if (intBomberMan == 2) {
				setChanged();
				notifyObservers(new String[] {"BonbaBeltza"});
			}
		}
	}
	
	public void setBlokea(Blokea pB) {
		this.blok=pB;
	}
	public void kenduBlokea() {
		this.blok = null;
	}
	public boolean bomberDago() {
		if(this.b == null) {
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
		else if(etsaiaDago()) {
			e.timerGelditu();
			e=null;
			EszenarioKudeatzailea.getNireEszenarioKudeatzailea().etsaiaKendu(e);
		} 
	}
	public boolean suaDago() {
		if (this.sua != null) return true;
		else return false;
	}
	public void kenduSua() {
		this.sua = null;
		setChanged();
		notifyObservers(new String[] {"KenduIrudia"});
	}

	public void bomberHeldu(BomberMan b) {
		setBomberZuria(b);
		if(b instanceof BomberZuria) {
			setChanged();
			notifyObservers(new String[] {"BomberZuriaHeldu"});
		}else {
			setChanged();
			notifyObservers(new String[] {"BomberBeltzaHeldu"});
		}
		if(suaDago() || etsaiaDago()) {
			EszenarioKudeatzailea.getNireEszenarioKudeatzailea().partidaAmaitu("Galduta");		
			b=null;
		}
	}
	public void amaitu() {
		b.setSutanDago();
		setChanged();
		notifyObservers(new String[] {"Galduta"});
		EszenarioKudeatzailea.getNireEszenarioKudeatzailea().partidaAmaitu("Galduta");
	}
	
	public void irabazi() {
		EszenarioKudeatzailea.getNireEszenarioKudeatzailea().partidaAmaitu("Irabazi");
	}
	
	public void setEtsaia(Etsaia pE) {
		e = pE;
		if(suaDago()) {
			setChanged();
			notifyObservers(new String[] {"SuaJarri"});
			e.timerGelditu();
			e=null;
			EszenarioKudeatzailea.getNireEszenarioKudeatzailea().etsaiaKendu(e);
		}
		else if(bomberDago()) {
			b=null;
			EszenarioKudeatzailea.getNireEszenarioKudeatzailea().partidaAmaitu("Galduta");
		}
		else {
			setChanged();
			notifyObservers(new String[] {"EtsaiaJarri"});
		}
	}
	
	public void etsaiaKendu(int x, int y) {
		e = null;
		setChanged();
		notifyObservers(new String[] {"KenduIrudia"});
	}
	
	public boolean etsaiaDago() {
		return (this.e!=null);
	}
	
	public Etsaia getEtsaia() {
		return this.e;
	}
}
