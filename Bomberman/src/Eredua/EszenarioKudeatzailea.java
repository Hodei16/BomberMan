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
	public void bomberManMugitu() {
		
		Teklatua teklatua = Teklatua.getTeklatua();
		int x = b.getPosX();
		int y = b.getPosY();
		
		gelaxkaMatrizea[x][y].kenduBomberZuria();
							
		
		if (teklatua.detectWASD() == "w" && x > 0) {
			if(!gelaxkaMatrizea[x][y].blokeaDago()) {
				y++;
			}
			
		}else if (teklatua.detectWASD() == "a" && y > 0) {
			if(!gelaxkaMatrizea[x][y].blokeaDago()) {
				x--;
			}
		}else if (teklatua.detectWASD() == "s" && x < 11) {
			if(!gelaxkaMatrizea[x][y].blokeaDago()) {
				y--;
			}
		}else if (teklatua.detectWASD() == "d" && y < 16) {
			if(!gelaxkaMatrizea[x][y].blokeaDago()) {
			x++;
			}
			
		}
		gelaxkaMatrizea[x][y].setBomberZuria(b);
		
		setChanged();
		notifyObservers();
			        
   

	    
	}
	BomberZuria b= new BomberZuria(0,0);
	public void sortuEszenarioClassic() {
		for(int x=0 ;x<11;x++) {
			for(int y=0; y<17; y++) {
				Gelaxka gelaxka;
				gelaxka= new Gelaxka();
				if(x==0 && y==0) {
					
					gelaxka.setBomberZuria(b);
					b.setPosX(x);
					b.setPosY(y);
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
