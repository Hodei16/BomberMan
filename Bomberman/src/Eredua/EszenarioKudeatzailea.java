package Eredua;

import Bista.HasieraPantaila;
import java.util.Random;
import java.util.Observable;
import java.util.Observer;

public class EszenarioKudeatzailea extends Observable{

	private static EszenarioKudeatzailea nireEszenarioKudeatzailea;
	private Gelaxka[][] gelaxkaMatrizea= new Gelaxka[11][17];
	
	private EszenarioKudeatzailea() {}
	
	public static synchronized EszenarioKudeatzailea getNireEszenarioKudeatzailea() {
		if(nireEszenarioKudeatzailea == null) {
			nireEszenarioKudeatzailea = new EszenarioKudeatzailea();
		}
		return nireEszenarioKudeatzailea;
	}
	
	public Gelaxka[][] getGelaxkaMatrizea(){
		return gelaxkaMatrizea;
	}
	
	public void sortuEszenarioClassic() {
		for(int x=0 ;x<11;x++) {
			for(int y=0; y<17; y++) {
				Gelaxka gelaxka;
				gelaxka= new Gelaxka();
				if(x==0 && y==0) {
					BomberZuria b= new BomberZuria();
					gelaxka.setBomberZuria(b);
				}
				if(x==0 && y==1) {
					
				}
				if(x==1 && y==0) {
					
					}
				if(x%2==1 && y%2==1) {
					Gogorra g= new Gogorra();
					gelaxka.setBlokea(g);
				}
				else {
					Random r= new Random();
					int ausazkoZenb= r.nextInt(101);
					if(ausazkoZenb<40) {
						
					}
					else {
						Biguna big= new Biguna();
						gelaxka.setBlokea(big);
					}
				}
				gelaxkaMatrizea[x][y]= gelaxka;
			} 
		}
		setChanged();
		notifyObservers();
	}
}
