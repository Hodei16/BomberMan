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
	
	Queue <Integer> filaX = new LinkedList<>();
	Queue <Integer> filaY = new LinkedList<>();
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
			filaX.add(x);
			filaY.add(y);
			
		}
		gelaxkaMatrizea[x][y].setBomberZuria(b);
		b.setPosX(x);
		b.setPosY(y);
		
	
		
		setChanged();
		notifyObservers();
			           
	}
	Queue <Integer> suaX = new LinkedList<>();
	Queue <Integer> suaY = new LinkedList<>();
	public void bonbaKendu() {
		int bonbX = filaX.remove();
		int bonbY = filaY.remove();
		gelaxkaMatrizea[bonbX][bonbY].kenduBonba();
		Sua s = new Sua();
		gelaxkaMatrizea[bonbX][bonbY].setSua(s);
		if (bonbX < 11 && !(gelaxkaMatrizea[bonbX+1][bonbY].getBlokea() instanceof Gogorra)) {
			Sua sEsk = new Sua();
			if (gelaxkaMatrizea[bonbX+1][bonbY].blokeaDago()) {
				gelaxkaMatrizea[bonbX+1][bonbY].kenduBlokea();
			}
			gelaxkaMatrizea[bonbX+1][bonbY].setSua(sEsk);
			suaX.add(bonbX+1);
			suaY.add(bonbY);
		}
		
		if (bonbX > 0 && !(gelaxkaMatrizea[bonbX-1][bonbY].getBlokea() instanceof Gogorra)) {
			Sua sEzk = new Sua();
			if (gelaxkaMatrizea[bonbX-1][bonbY].blokeaDago()) {
				gelaxkaMatrizea[bonbX-1][bonbY].kenduBlokea();
			}
			gelaxkaMatrizea[bonbX-1][bonbY].setSua(sEzk);	
			suaX.add(bonbX-1);
			suaY.add(bonbY);	
			
		}
		
		
		
		if (bonbY < 16 && !(gelaxkaMatrizea[bonbX][bonbY+1].getBlokea() instanceof Gogorra)) {
			Sua sGora = new Sua();
			if (gelaxkaMatrizea[bonbX][bonbY+1].blokeaDago() ) {
				gelaxkaMatrizea[bonbX][bonbY+1].kenduBlokea();
			}
			gelaxkaMatrizea[bonbX][bonbY+1].setSua(sGora);
			suaX.add(bonbX-1);
			suaY.add(bonbY);	
			
			
			
		}
		if (bonbY > 0 && !(gelaxkaMatrizea[bonbX][bonbY-1].getBlokea() instanceof Gogorra)) {
			Sua sBehera = new Sua();
			if (gelaxkaMatrizea[bonbX][bonbY-1].blokeaDago() ) {
				gelaxkaMatrizea[bonbX][bonbY-1].kenduBlokea();
			}
			gelaxkaMatrizea[bonbX][bonbY-1].setSua(sBehera);
			suaX.add(bonbX);
			suaY.add(bonbY-1);
		}
		
		
		setChanged();
		notifyObservers();
		
	}
	
	public void kenduSua() {
		int x = suaX.remove();
		int y = suaY.remove();
		gelaxkaMatrizea[x][y].kenduSua();
		
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
