package Eredua;

import java.util.Observable;
import java.util.Random;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

public class EszenarioKudeatzailea extends Observable{

	private static EszenarioKudeatzailea nireEszenarioKudeatzailea;
	private GelaxkaKudeatzailea[][] gelaxkaMatrizea= new GelaxkaKudeatzailea[11][17];
	private int etsaiKop = 0;
	
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
			
			if (b instanceof BomberZuria) {
			
				if(b.bonbaDauka()) {
					Bonba bonb = new BonbaTxikia(x, y);
					gelaxkaMatrizea[x][y].setBonba(bonb);
					b.bonbaKendu();
				}
				
			}else {
				if(b.bonbaDauka()) {
					Bonba bonb = new BonbaHandia(x, y);
					gelaxkaMatrizea[x][y].setBonba(bonb);
					b.bonbaKendu();
				}
			}
		}
		gelaxkaMatrizea[x][y].setBomberZuria(b);
		b.setPosX(x);
		b.setPosY(y);	           
	}
	
	public void etsaiaMugitu(int x, int y) {
		Random r= new Random();
		int ausazkoZenb= r.nextInt(101);
		GelaxkaKudeatzailea g = gelaxkaMatrizea[x][y];
		Etsaia e = g.getEtsaia();
		if(ausazkoZenb<25) {
			if(x<10 && !gelaxkaMatrizea[x+1][y].blokeaDago() && !gelaxkaMatrizea[x+1][y].bonbaDago() && !gelaxkaMatrizea[x+1][y].etsaiaDago()) {
				g.etsaiaKendu(x,y);
				x++;
				GelaxkaKudeatzailea gBerria = gelaxkaMatrizea[x][y];
				gBerria.setEtsaia(e);
			}
		}
		else if(25<ausazkoZenb && ausazkoZenb<50) {
			if(y<16 && !gelaxkaMatrizea[x][y+1].blokeaDago() && !gelaxkaMatrizea[x][y+1].bonbaDago() && !gelaxkaMatrizea[x][y+1].etsaiaDago()) {
				g.etsaiaKendu(x,y);
				y++;
				GelaxkaKudeatzailea gBerria = gelaxkaMatrizea[x][y];
				gBerria.setEtsaia(e);
			}
		}
		else if(50<ausazkoZenb && ausazkoZenb<75) {
			if(x>0 && !gelaxkaMatrizea[x-1][y].blokeaDago() && !gelaxkaMatrizea[x-1][y].bonbaDago() && !gelaxkaMatrizea[x-1][y].etsaiaDago()) {
				g.etsaiaKendu(x,y);
				x--;
				GelaxkaKudeatzailea gBerria = gelaxkaMatrizea[x][y];
				gBerria.setEtsaia(e);
			}
		}
		else{
			if(y>0 && !gelaxkaMatrizea[x][y-1].blokeaDago() && !gelaxkaMatrizea[x][y-1].bonbaDago() && !gelaxkaMatrizea[x][y-1].etsaiaDago()) {
				g.etsaiaKendu(x,y);
				y--;
				GelaxkaKudeatzailea gBerria = gelaxkaMatrizea[x][y];
				gBerria.setEtsaia(e);
			}
		}
		e.koordenatuakAldatu(x, y);
	}
	
	public void etsaiaKendu(Etsaia e) {
		etsaiKop--;
		if(etsaiKop==0) {
			setChanged();
			notifyObservers(new String[] {"Irabazi"});
		}
	}
	
	public void bonbaKendu(int bonbX, int bonbY, String pMota){
		
		if(pMota == "BonbaTxikia") {
			
			gelaxkaMatrizea[bonbX][bonbY].kenduBonba();
			Sua s = new Sua(bonbX, bonbY);
			gelaxkaMatrizea[bonbX][bonbY].setSua(s);
			if (bonbX < 10) {
				if (gelaxkaMatrizea[bonbX+1][bonbY].blokeaDago()) {
					Blokea b = gelaxkaMatrizea[bonbX+1][bonbY].getBlokea();
					if(b instanceof Biguna) {
						gelaxkaMatrizea[bonbX+1][bonbY].kenduBlokea();
						Sua sBehera = new Sua(bonbX+1, bonbY);
						gelaxkaMatrizea[bonbX+1][bonbY].setSua(sBehera);
					}
				}
				else {
					Sua sBehera = new Sua(bonbX+1, bonbY);
					gelaxkaMatrizea[bonbX+1][bonbY].setSua(sBehera);
				}
			}
			
			if (bonbX > 0) {
				if (gelaxkaMatrizea[bonbX-1][bonbY].blokeaDago()) {
					Blokea b = gelaxkaMatrizea[bonbX-1][bonbY].getBlokea();
					if(b instanceof Biguna) {
						gelaxkaMatrizea[bonbX-1][bonbY].kenduBlokea();
						Sua sGora = new Sua(bonbX-1, bonbY);
						gelaxkaMatrizea[bonbX-1][bonbY].setSua(sGora);
					}
				}
				else {
					Sua sGora = new Sua(bonbX-1, bonbY);
					gelaxkaMatrizea[bonbX-1][bonbY].setSua(sGora);
				}
			}
			
			if (bonbY < 16) {
				if (gelaxkaMatrizea[bonbX][bonbY+1].blokeaDago()) {
					Blokea b = gelaxkaMatrizea[bonbX][bonbY+1].getBlokea();
					if(b instanceof Biguna) {
						gelaxkaMatrizea[bonbX][bonbY+1].kenduBlokea();
						Sua sEsk = new Sua(bonbX, bonbY+1);
						gelaxkaMatrizea[bonbX][bonbY+1].setSua(sEsk);
					}
				}
				else {
					Sua sEsk = new Sua(bonbX, bonbY+1);
					gelaxkaMatrizea[bonbX][bonbY+1].setSua(sEsk);
				}
			}
			
			if (bonbY > 0) {
				if (gelaxkaMatrizea[bonbX][bonbY-1].blokeaDago()) {
					Blokea b = gelaxkaMatrizea[bonbX][bonbY-1].getBlokea();
					if(b instanceof Biguna) {
						gelaxkaMatrizea[bonbX][bonbY-1].kenduBlokea();
						Sua sEzk = new Sua(bonbX, bonbY-1);
						gelaxkaMatrizea[bonbX][bonbY-1].setSua(sEzk);
					}
				}
				else {
					Sua sEzk = new Sua(bonbX, bonbY-1);
					gelaxkaMatrizea[bonbX][bonbY-1].setSua(sEzk);
				}
			}
		}else if (pMota == "BonbaHandia") {
			
			
			boolean aurkitutaGora = false;
			boolean aurkitutaBehera = false;
			boolean aurkitutaEsk = false;
			boolean aurkitutaEzk = false;
			int suaGora = bonbY;
			int suaBehera = bonbY;
			int suaEsk = bonbX;
			int suaEzk = bonbX;
			
			gelaxkaMatrizea[bonbX][bonbY].kenduBonba();
			Sua s = new Sua(bonbX, bonbY);
			gelaxkaMatrizea[bonbX][bonbY].setSua(s);				
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
							Sua sGora = new Sua(bonbX, suaGora);
							gelaxkaMatrizea[bonbX][suaGora].setSua(sGora);
						}
					}else {
						gelaxkaMatrizea[bonbX][suaGora].setSua(s);
					}
				}
				
				if(!aurkitutaBehera) {
					if(gelaxkaMatrizea[bonbX][suaBehera].blokeaDago()){
						aurkitutaBehera = true;
						if(gelaxkaMatrizea[bonbX][suaBehera].getBlokea() instanceof Biguna) {
							gelaxkaMatrizea[bonbX][suaBehera].kenduBlokea();
							Sua sBehera = new Sua(bonbX, suaBehera);
							gelaxkaMatrizea[bonbX][suaBehera].setSua(sBehera);
						}
					}else {
						gelaxkaMatrizea[bonbX][suaBehera].setSua(s);
					}
				}
				if(!aurkitutaEsk) {
					if(gelaxkaMatrizea[suaEsk][bonbY].blokeaDago()){
						aurkitutaEsk = true;
						if(gelaxkaMatrizea[suaEsk][bonbY].getBlokea() instanceof Biguna) {
							gelaxkaMatrizea[suaEsk][bonbY].kenduBlokea();
							Sua sEsk = new Sua(suaEsk, bonbY);
							gelaxkaMatrizea[suaEsk][bonbY].setSua(sEsk);
						}
					}else {
						gelaxkaMatrizea[suaEsk][bonbY].setSua(s);
						
					}
				}
				if(!aurkitutaEzk) {
					if(gelaxkaMatrizea[suaEzk][bonbY].blokeaDago()){
						aurkitutaEzk = true;
						if(gelaxkaMatrizea[suaEzk][bonbY].getBlokea() instanceof Biguna) {
							gelaxkaMatrizea[suaEzk][bonbY].kenduBlokea();
							Sua sEzk = new Sua(suaEzk, bonbY);
							gelaxkaMatrizea[suaEzk][bonbY].setSua(sEzk);
						}
					}else {
						gelaxkaMatrizea[suaEzk][bonbY].setSua(s);

					}
				}
				
			}
			setChanged();
			notifyObservers();
			
		}
	}
	
	public void kenduSua(int pPosX, int pPosY) {
		gelaxkaMatrizea[pPosX][pPosY].kenduSua();
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
					int ausazkoZenb1= r.nextInt(101);
					if(ausazkoZenb1<40) {
						int ausazkoZenb2= r.nextInt(101);
						if(ausazkoZenb2>90 && etsaiKop<6) {
							Etsaia e= new Etsaia(x,y);
							gK.setEtsaia(e);
							etsaiKop++;
						}
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
	
	public void partidaAmaitu(String pMota) {
		setChanged();
		notifyObservers(new String[] {pMota});
	}
}
