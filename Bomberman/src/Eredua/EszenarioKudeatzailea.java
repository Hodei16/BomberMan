package Eredua;

import java.util.Observable;
import java.util.Random;
import java.util.Queue;
import java.util.LinkedList;

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
	
	Queue <Integer> pilaX = new LinkedList<>();
	Queue <Integer> pilaY = new LinkedList<>();
	public void bomberManMugitu(char tekla) {
		Teklatua teklatua = Teklatua.getTeklatua();
		int x = b.getPosX();
		int y = b.getPosY();
		
		gelaxkaMatrizea[x][y].kenduBomberZuria();
							
		
		if (tekla == 'w' && x > 0) {
			if(!gelaxkaMatrizea[x-1][y].blokeaDago()) {
				x--;
			}
			
		}else if (tekla == 'a' && y > 0) {
			if(!gelaxkaMatrizea[x][y-1].blokeaDago()) {
				y--;
			}
		}else if (tekla == 's' && x < 11) {
			if(!gelaxkaMatrizea[x+1][y].blokeaDago()) {
				x++;
			}
		}else if (tekla == 'd' && y < 16) {
			if(!gelaxkaMatrizea[x][y+1].blokeaDago()) {
				y++;
			}
			
		}else if (tekla == 'x') {
			Bonba bonb = new Bonba();
			gelaxkaMatrizea[x][y].setBonba(bonb);
			pilaX.add(x);
			pilaY.add(y);
			
		}
		gelaxkaMatrizea[x][y].setBomberZuria(b);
		b.setPosX(x);
		b.setPosY(y);
		
	
		
		setChanged();
		notifyObservers();
			           
	}
	
	public void bonbaKendu() {
		int bonbX = pilaX.remove();
		int bonbY = pilaY.remove();
		gelaxkaMatrizea[bonbX][bonbY].kenduBonba();
		
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
				else if(x==0 && y==1) {
					
				}
				else if(x==1 && y==0) {
					
					}
				else if(x%2==1 && y%2==1) {
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
