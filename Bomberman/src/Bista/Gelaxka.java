package Bista;

import java.util.Observable;
import java.util.Observer;

import Eredua.Blokea;
import Eredua.BomberZuria;
import Eredua.Bonba;
import Eredua.Sua;

public class Gelaxka implements Observer {
	
	public Gelaxka(Observable pGK) {
		pGK.addObserver(this);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private BomberZuria bz;
	private Blokea b;
	private Bonba bonb;
	private Sua sua;
	
	
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
	}
	public void kenduBonba() {
		this.bonb = null;
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
	}
	public boolean suaDago() {
		if (this.sua != null) return true;
		else return false;
	}
	public void kenduSua() {
		this.sua = null;
	}
}
