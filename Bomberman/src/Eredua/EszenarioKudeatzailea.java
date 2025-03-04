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
				if(x==0 && y==0) {
					BomberZuria b= new BomberZuria();
					Gelaxka gelaxka= new Gelaxka(b);
					
				}
				if(x==0 && y==1) {}
				if(x==1 && y==0) {}
				if(x%2==1 && y%2==1) {
					Gogorra g= new Gogorra();
					Gelaxka gelaxka= new Gelaxka(g);
				}
				else {
					Random r= new Random();
					int ausazkoZenb= r.nextInt(101);
					if(ausazkoZenb<40) {}
					else {
						Biguna big= new Biguna();
						Gelaxka gelaxka= new Gelaxka(big);
						
					}
				}
				gelaxkaMatrizea[x][y]= gelaxka;
			}
		}
		
	}
}
