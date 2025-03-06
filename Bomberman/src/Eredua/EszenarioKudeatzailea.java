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
	Queue <Integer> suaX = new LinkedList<>();
	Queue <Integer> suaY = new LinkedList<>();
	public void bonbaKendu() {
		
		boolean aurkitutaGora = false;
		boolean aurkitutaBehera = false;
		boolean aurkitutaEsk = false;
		boolean aurkitutaEzk = false;
		int bonbX = pilaX.remove();
		int bonbY = pilaY.remove();
		int suaGora = bonbY;
		int suaBehera = bonbY;
		int suaEsk = bonbX;
		int suaEzk = bonbX;
		
		gelaxkaMatrizea[bonbX][bonbY].kenduBonba();
		
		while(!aurkitutaGora && !aurkitutaBehera && !aurkitutaEsk && !aurkitutaEzk) {
			if (suaGora < 16) {
				suaGora++;
			}
			if (suaBehera > 0) {
				suaBehera--;
			}
			if (suaEzk > 0) {
				suaEzk--;
			}
			if (suaEsk < 11) {
				suaEsk++;
			}
			
			if(!aurkitutaGora) {
				if(gelaxkaMatrizea[bonbX][suaGora].blokeaDago()){
					aurkitutaGora = true;
					if(gelaxkaMatrizea[bonbX][suaGora].getBlokea() instanceof Biguna) {
						gelaxkaMatrizea[bonbX][suaGora].kenduBlokea();
					}
				}else {
					Sua s = new Sua();
					gelaxkaMatrizea[bonbX][suaGora].setSua(s);
					suaX.add(suaGora);
					suaY.add(bonbX);
				}
			}
			
			if(!aurkitutaBehera) {
				if(gelaxkaMatrizea[bonbX][suaBehera].blokeaDago()){
					aurkitutaBehera = true;
					if(gelaxkaMatrizea[bonbX][suaBehera].getBlokea() instanceof Biguna) {
						gelaxkaMatrizea[bonbX][suaBehera].kenduBlokea();
					}
				}else {
					Sua s = new Sua();
					gelaxkaMatrizea[bonbX][suaBehera].setSua(s);
					suaX.add(bonbX);
					suaY.add(suaBehera);
				}
			}
			if(!aurkitutaEsk) {
				if(gelaxkaMatrizea[suaEsk][bonbY].blokeaDago()){
					aurkitutaEsk = true;
					if(gelaxkaMatrizea[suaEsk][bonbY].getBlokea() instanceof Biguna) {
						gelaxkaMatrizea[suaEsk][bonbY].kenduBlokea();
					}
				}else {
					Sua s = new Sua();
					gelaxkaMatrizea[suaEsk][bonbY].setSua(s);
					suaX.add(suaEsk);
					suaY.add(bonbY);
				}
			}
			if(!aurkitutaEzk) {
				if(gelaxkaMatrizea[suaEzk][bonbY].blokeaDago()){
					aurkitutaEzk = true;
					if(gelaxkaMatrizea[suaEzk][bonbY].getBlokea() instanceof Biguna) {
						gelaxkaMatrizea[suaEzk][bonbY].kenduBlokea();
					}
				}else {
					Sua s = new Sua();
					gelaxkaMatrizea[suaEzk][bonbY].setSua(s);
					suaX.add(suaEzk);
					suaY.add(bonbY);
				}
			}
			
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
