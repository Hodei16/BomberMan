package Bista;

import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Eredua.Blokea;
import Eredua.BomberZuria;
import Eredua.Bonba;
import Eredua.Sua;

public class Gelaxka implements Observer {
	
	private JLabel irudia;
	
	public Gelaxka(Observable pGK) {
		pGK.addObserver(this);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(((String[])arg)[0]=="KenduBomber") {
			irudia.setIcon(null);
		}
		else if(((String[])arg)[0]=="BomberHeldu") {
			ImageIcon icon = new ImageIcon(getClass().getResource("whitefront1.png"));
			irudia.setIcon(icon);
		}
		else if(((String[])arg)[0]=="BonbaJarriBomber") {
			ImageIcon icon = new ImageIcon(getClass().getResource("whitewithbomb1.png"));
			irudia.setIcon(icon);
		}
		else if(((String[])arg)[0]=="BonbaJarri") {
			ImageIcon icon = new ImageIcon(getClass().getResource("bomb1.png"));
			irudia.setIcon(icon);
		}
		else if(((String[])arg)[0]=="KenduBonba") {
			irudia.setIcon(null);
		}
		else if(((String[])arg)[0]=="SuaJarri") {
			ImageIcon icon = new ImageIcon(getClass().getResource("kaBomb3.png"));
			irudia.setIcon(icon);
		}
		else if(((String[])arg)[0]=="SuaKendu") {
			irudia.setIcon(null);
		}
	}
	
	public void setIrudia(JLabel pIrudi) {
		this.irudia = pIrudi;
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
