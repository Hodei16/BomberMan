package Eredua;

import java.util.Observable;
import java.util.Random;
import java.util.Queue;
import java.util.LinkedList;

public class EszenarioKudeatzailea extends Observable{

	private static EszenarioKudeatzailea nireEszenarioKudeatzailea;
	private GelaxkaKudeatzailea[][] gelaxkaMatrizea= new GelaxkaKudeatzailea[11][17];
	
	private EszenarioKudeatzailea() {}
	
	public static synchronized EszenarioKudeatzailea getNireEszenarioKudeatzailea() {
		if(nireEszenarioKudeatzailea == null) {
			nireEszenarioKudeatzailea = new EszenarioKudeatzailea();
		}
		return nireEszenarioKudeatzailea;
	}
	
	public GelaxkaKudeatzailea[][] getGelaxkaMatrizea(){
		return gelaxkaMatrizea;
	}
	
	Queue <Integer> filaX = new LinkedList<>();
	Queue <Integer> filaY = new LinkedList<>();
	
	public void bomberManMugitu(char tekla) {
		int x = b.getPosX();
		int y = b.getPosY();
		GelaxkaKudeatzailea g = gelaxkaMatrizea[x][y];
		if (tekla == 'w' && x > 0) {
			if(!gelaxkaMatrizea[x-1][y].blokeaDago() && !gelaxkaMatrizea[x-1][y].bonbaDago()) {
				g.kenduBomberZuria();
				x--;
				GelaxkaKudeatzailea gBerria = gelaxkaMatrizea[x][y];
				gBerria.bomberHeldu(b);
			}
		}else if (tekla == 'a' && y > 0) {
			if(!gelaxkaMatrizea[x][y-1].blokeaDago() && !gelaxkaMatrizea[x][y-1].bonbaDago()) {
				g.kenduBomberZuria();
				y--;
				GelaxkaKudeatzailea gBerria = gelaxkaMatrizea[x][y];
				gBerria.bomberHeldu(b);
			}
		}else if (tekla == 's' && x < 10) {
			if(!gelaxkaMatrizea[x+1][y].blokeaDago() && !gelaxkaMatrizea[x+1][y].bonbaDago()) {
				g.kenduBomberZuria();
				x++;
				GelaxkaKudeatzailea gBerria = gelaxkaMatrizea[x][y];
				gBerria.bomberHeldu(b);
			}
		}else if (tekla == 'd' && y < 16) {
			if(!gelaxkaMatrizea[x][y+1].blokeaDago() && !gelaxkaMatrizea[x][y+1].bonbaDago()) {
				g.kenduBomberZuria();
				y++;
				GelaxkaKudeatzailea gBerria = gelaxkaMatrizea[x][y];
				gBerria.bomberHeldu(b);
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
	}
	
	Queue <Integer> suaX = new LinkedList<>();
	Queue <Integer> suaY = new LinkedList<>();
	
	public void bonbaKendu(){
		int bonbX = filaX.remove();
		int bonbY = filaY.remove();
		gelaxkaMatrizea[bonbX][bonbY].kenduBonba();
		Sua s = new Sua();
		gelaxkaMatrizea[bonbX][bonbY].setSua(s);
		suaX.add(bonbX);
		suaY.add(bonbY);
		if (bonbX < 10) {
			if (gelaxkaMatrizea[bonbX+1][bonbY].blokeaDago()) {
				Blokea b = gelaxkaMatrizea[bonbX+1][bonbY].getBlokea();
				if(b instanceof Biguna) {
					gelaxkaMatrizea[bonbX+1][bonbY].kenduBlokea();
					suaX.add(bonbX+1);
					suaY.add(bonbY);
					Sua sBehera = new Sua();
					gelaxkaMatrizea[bonbX+1][bonbY].setSua(sBehera);
				}
			}
			else {
				suaX.add(bonbX+1);
				suaY.add(bonbY);
				Sua sBehera = new Sua();
				gelaxkaMatrizea[bonbX+1][bonbY].setSua(sBehera);
			}
		}
		
		if (bonbX > 0) {
			if (gelaxkaMatrizea[bonbX-1][bonbY].blokeaDago()) {
				Blokea b = gelaxkaMatrizea[bonbX-1][bonbY].getBlokea();
				if(b instanceof Biguna) {
					gelaxkaMatrizea[bonbX-1][bonbY].kenduBlokea();
					suaX.add(bonbX-1);
					suaY.add(bonbY);
					Sua sGora = new Sua();
					gelaxkaMatrizea[bonbX-1][bonbY].setSua(sGora);
				}
			}
			else {
				suaX.add(bonbX-1);
				suaY.add(bonbY);
				Sua sGora = new Sua();
				gelaxkaMatrizea[bonbX-1][bonbY].setSua(sGora);
			}
		}
		
		if (bonbY < 16) {
			if (gelaxkaMatrizea[bonbX][bonbY+1].blokeaDago()) {
				Blokea b = gelaxkaMatrizea[bonbX][bonbY+1].getBlokea();
				if(b instanceof Biguna) {
					gelaxkaMatrizea[bonbX][bonbY+1].kenduBlokea();
					suaX.add(bonbX);
					suaY.add(bonbY+1);
					Sua sEsk = new Sua();
					gelaxkaMatrizea[bonbX][bonbY+1].setSua(sEsk);
				}
			}
			else {
				suaX.add(bonbX);
				suaY.add(bonbY+1);
				Sua sEsk = new Sua();
				gelaxkaMatrizea[bonbX][bonbY+1].setSua(sEsk);
			}
		}
		
		if (bonbY > 0) {
			if (gelaxkaMatrizea[bonbX][bonbY-1].blokeaDago()) {
				Blokea b = gelaxkaMatrizea[bonbX][bonbY-1].getBlokea();
				if(b instanceof Biguna) {
					gelaxkaMatrizea[bonbX][bonbY-1].kenduBlokea();
					suaX.add(bonbX);
					suaY.add(bonbY-1);
					Sua sEzk = new Sua();
					gelaxkaMatrizea[bonbX][bonbY-1].setSua(sEzk);
				}
			}
			else {
				suaX.add(bonbX);
				suaY.add(bonbY-1);
				Sua sEzk = new Sua();
				gelaxkaMatrizea[bonbX][bonbY-1].setSua(sEzk);
			}
		}
	}
	
	public void kenduSua() {
		int x = suaX.remove();
		int y = suaY.remove();
		gelaxkaMatrizea[x][y].kenduSua();
	}
	
	BomberZuria b= new BomberZuria(0,0);
	
	public void sortuEszenarioClassic() {
		for(int x=0 ;x<11;x++) {
			for(int y=0; y<17; y++) {
				GelaxkaKudeatzailea gK = new GelaxkaKudeatzailea();
				if(x==0 && y==0) {
					gK.setBomberZuria(b);
					b.setPosX(x);
					b.setPosY(y);
				}
				else if(x==0 && y==1) {
					
				}
				else if(x==1 && y==0) {
					
					}
				else if(x%2==1 && y%2==1) {
					Gogorra g= new Gogorra();
					gK.setBlokea(g);
				}
				else {
					Random r= new Random();
					int ausazkoZenb= r.nextInt(101);
					if(ausazkoZenb<40) {
						
					}
					else {
						Biguna big= new Biguna();
						gK.setBlokea(big);
					}
				}
				gelaxkaMatrizea[x][y] = gK;
			} 
		}
		setChanged();
		notifyObservers(new String[] {"EszenarioClassic"});
	}
}
