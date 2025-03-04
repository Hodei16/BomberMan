package Eredua;

import Bista.HasieraPantaila;
import java.util.Random;

public class EszenarioKudeatzailea {

	private static EszenarioKudeatzailea nireEszenarioKudeatzailea;
	private Gelaxka[][] gelaxkaMatrizea;
	
	private EszenarioKudeatzailea() {}
	
	public static synchronized EszenarioKudeatzailea getNireEszenarioKudeatzailea() {
		if(nireEszenarioKudeatzailea == null) {
			nireEszenarioKudeatzailea = new EszenarioKudeatzailea();
		}
		return nireEszenarioKudeatzailea;
	}
	
	public void sortuEszenarioClassic() {
		for(int x=0 ;x<17;x++) {
			for(int y=0; y<11; y++) {
				Gelaxka gelaxka;
				if(x==0 && y==0) {
					BomberZuria b= new BomberZuria();
					gelaxka= new Gelaxka(b, null, null);
					
				}
				if(x==0 && y==1) {
					gelaxka= new Gelaxka(null, null, null);
				}
				if(x==1 && y==0) {
					gelaxka= new Gelaxka(null, null, null);
					}
				if(x%2==1 && y%2==1) {
					Gogorra g= new Gogorra();
					gelaxka= new Gelaxka(null, null, g);
				}
				else {
					Random r= new Random();
					int ausazkoZenb= r.nextInt(101);
					if(ausazkoZenb<40) {
						gelaxka= new Gelaxka(null, null, null);
					}
					else {
						Biguna big= new Biguna();
						gelaxka= new Gelaxka(null, big, null);
					}
				}
				gelaxkaMatrizea[x][y]= gelaxka;
			}
		}
		
	}
}
