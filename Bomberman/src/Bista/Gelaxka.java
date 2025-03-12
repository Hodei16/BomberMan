package Bista;

import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import Eredua.EszenarioKudeatzailea;

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
		else if(((String[])arg)[0]=="Galduta") {
			sutan();
			EszenarioKudeatzailea.getNireEszenarioKudeatzailea().partidaAmaitu("Galduta");
		}
		else if(((String[])arg)[0]=="Galdu") {
			EszenarioKudeatzailea.getNireEszenarioKudeatzailea().partidaAmaitu("Galduta");
		}
		else if(((String[])arg)[0]=="Irabazi") {
			EszenarioKudeatzailea.getNireEszenarioKudeatzailea().partidaAmaitu("Irabazi");
		}
	}
	
	public void setIrudia(JLabel pIrudi) {
		this.irudia = pIrudi;
	}
	
	public void sutan() {
		ImageIcon icon = new ImageIcon(getClass().getResource("onFire1.png"));
		irudia.setIcon(icon);
		try {
            Thread.sleep(300); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		ImageIcon icon2 = new ImageIcon(getClass().getResource("onFire2.png"));
		irudia.setIcon(icon2);
		try {
            Thread.sleep(300); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		ImageIcon icon3 = new ImageIcon(getClass().getResource("onFire3.png"));
		irudia.setIcon(icon3);
		try {
            Thread.sleep(300); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		ImageIcon icon4 = new ImageIcon(getClass().getResource("onFire4.png"));
		irudia.setIcon(icon4);
		try {
            Thread.sleep(300); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
}
