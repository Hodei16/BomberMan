package Eredua;

import Bista.HasieraPantaila;
import java.util.Random;
import java.util.Observable;
import java.util.Observer;

public class EszenarioKudeatzailea extends Observable{

	private static EszenarioKudeatzailea nireEszenarioKudeatzailea;
	private Gelaxka[][] gelaxkaMatrizea;
	
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
		for(int x=0 ;x<17;x++) {
			for(int y=0; y<11; y++) {
				Gelaxka gelaxka;
				if(x==0 && y==0) {
					BomberZuria b= new BomberZuria();
					gelaxka= new Gelaxka(b, null);
				}
				if(x==0 && y==1) {
					gelaxka= new Gelaxka(null, null);
				}
				if(x==1 && y==0) {
					gelaxka= new Gelaxka(null, null);
					}
				if(x%2==1 && y%2==1) {
					Gogorra g= new Gogorra();
					gelaxka= new Gelaxka(null,g);
				}
				else {
					Random r= new Random();
					int ausazkoZenb= r.nextInt(101);
					if(ausazkoZenb<40) {
						gelaxka= new Gelaxka(null, null);
					}
					else {
						Biguna big= new Biguna();
						gelaxka= new Gelaxka(null, big);
					}
				}
				gelaxkaMatrizea[x][y]= gelaxka;
			} 
		}
		setChanged();
		notifyObservers();
	}
}
