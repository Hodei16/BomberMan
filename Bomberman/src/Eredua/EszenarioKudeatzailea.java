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
	
	BomberMan b ; 
	
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
	public void setBomberMota(int pMota) { // 1 = zuri ; 2 = beltza
		b = BomberManFactory.getNireBomberManFactory().createBomberMan(pMota);
	}
	
	public void setEszenarioMota(String mota) {
	    if (mota.equals("EszenarioClassic")) {
	        sortuEszenarioClassic();
	    }
	    else if (mota.equals("EszenarioSoft")){
	    	sortuEszenarioSoft();
	        }
	    else if (mota.equals("EszenarioEmpty")){
	        sortuEszenarioEmpty();  
	    }
	}
	
	public void bomberManMugitu(char tekla) {
		if (!b.getSutanDago()) {
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
				
				b.bonbaJarri();
				
			}
			gelaxkaMatrizea[x][y].setBomberZuria(b);
			b.setPosX(x);
			b.setPosY(y);	
		}
	}
	
	public void bonbaJarri(int bonbX, int bonbY, Bonba jarritakoBonba) {
		
		gelaxkaMatrizea[bonbX][bonbY].setBonba(jarritakoBonba);
		b.bonbaJarri();
		jarritakoBonba.kontaketaHasi();
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
			
			
			boolean aurkitutaEsk = false;
			boolean aurkitutaEzk = false;
			boolean aurkitutaBehera = false;
			boolean aurkitutaGora = false;
			int suaEsk = bonbY;
			int suaEzk = bonbY;
			int suaBehera = bonbX;
			int suaGora = bonbX;
			gelaxkaMatrizea[bonbX][bonbY].kenduBonba();
			Sua s = new Sua(bonbX, bonbY);
			gelaxkaMatrizea[bonbX][bonbY].setSua(s);				

			while(!aurkitutaEsk || !aurkitutaEzk || !aurkitutaBehera || !aurkitutaGora) {
			    
			    if (!aurkitutaEsk) {
			        if (suaEsk < 16) {  
			            suaEsk++;
			            Sua sEsk = new Sua(bonbX, suaEsk);
			            if(gelaxkaMatrizea[bonbX][suaEsk].blokeaDago()){
			                if(gelaxkaMatrizea[bonbX][suaEsk].getBlokea() instanceof Biguna) {
			                    gelaxkaMatrizea[bonbX][suaEsk].kenduBlokea();
			                    gelaxkaMatrizea[bonbX][suaEsk].setSua(sEsk);
			                } else {
			                    aurkitutaEsk = true;
			                }
			            } else {
			                gelaxkaMatrizea[bonbX][suaEsk].setSua(sEsk);
			            }
			        } else {
			            aurkitutaEsk = true;
			        }
			    }
			    
			    
			    if (!aurkitutaEzk) {
			        if (suaEzk > 0) {
			            suaEzk--;
			            Sua sEzk = new Sua(bonbX, suaEzk);
			            if(gelaxkaMatrizea[bonbX][suaEzk].blokeaDago()){
			                if(gelaxkaMatrizea[bonbX][suaEzk].getBlokea() instanceof Biguna) {
			                    gelaxkaMatrizea[bonbX][suaEzk].kenduBlokea();
			                    gelaxkaMatrizea[bonbX][suaEzk].setSua(sEzk);
			                } else {
			                    aurkitutaEzk = true;
			                }
			            } else {
			                gelaxkaMatrizea[bonbX][suaEzk].setSua(sEzk);
			            }
			        } else {
			            aurkitutaEzk = true;
			        }
			    }
			    
			   
			    if (!aurkitutaBehera) {
			        if (suaBehera < 10) {  
			            suaBehera++;
			            Sua sBehera = new Sua(suaBehera, bonbY);
			            if(gelaxkaMatrizea[suaBehera][bonbY].blokeaDago()){
			                if(gelaxkaMatrizea[suaBehera][bonbY].getBlokea() instanceof Biguna) {
			                    gelaxkaMatrizea[suaBehera][bonbY].kenduBlokea();
			                    gelaxkaMatrizea[suaBehera][bonbY].setSua(sBehera);
			                } else {
			                    aurkitutaBehera = true;
			                }
			            } else {
			                gelaxkaMatrizea[suaBehera][bonbY].setSua(sBehera);
			            }
			        } else {
			            aurkitutaBehera = true;
			        }
			    }
			    
			    
			    if (!aurkitutaGora) {
			        if (suaGora > 0) {
			            suaGora--;
			            Sua sGora = new Sua(suaGora, bonbY);
			            if(gelaxkaMatrizea[suaGora][bonbY].blokeaDago()){
			                if(gelaxkaMatrizea[suaGora][bonbY].getBlokea() instanceof Biguna) {
			                    gelaxkaMatrizea[suaGora][bonbY].kenduBlokea();
			                    gelaxkaMatrizea[suaGora][bonbY].setSua(sGora);
			                } else {
			                    aurkitutaGora = true;
			                }
			            } else {
			                gelaxkaMatrizea[suaGora][bonbY].setSua(sGora);
			            }
			        } else {
			            aurkitutaGora = true;
			        }
			    }
			}

		}
	}
	
	
	public void kenduSua(int pPosX, int pPosY) {
		gelaxkaMatrizea[pPosX][pPosY].kenduSua();
	}
	
	
	public void etsaiaMugitu(int x, int y) {
		Random r= new Random();
		int ausazkoZenb= r.nextInt(101);
		GelaxkaKudeatzailea g = gelaxkaMatrizea[x][y];
		Etsaia e = g.getEtsaia();
		if(ausazkoZenb<25) {
			if(x<10 && !gelaxkaMatrizea[x+1][y].blokeaDago() && !gelaxkaMatrizea[x+1][y].bonbaDago() && !gelaxkaMatrizea[x+1][y].etsaiaDago()) {
				if(x<9 && gelaxkaMatrizea[x+2][y].etsaiaDago()) {}
				else if(x<10 && y<16 && gelaxkaMatrizea[x+1][y+1].etsaiaDago()) {}
				else if(x<10 && y>1 && gelaxkaMatrizea[x+1][y-1].etsaiaDago()) {}
				else {
					g.etsaiaKendu(x,y);
					x++;
					GelaxkaKudeatzailea gBerria = gelaxkaMatrizea[x][y];
					gBerria.setEtsaia(e);
				}
			}
		}
		else if(25<ausazkoZenb && ausazkoZenb<50) {
			if(y<16 && !gelaxkaMatrizea[x][y+1].blokeaDago() && !gelaxkaMatrizea[x][y+1].bonbaDago() && !gelaxkaMatrizea[x][y+1].etsaiaDago()) {
				if(y<15 && gelaxkaMatrizea[x][y+2].etsaiaDago()) {}
				else if(x<10 && y<16 && gelaxkaMatrizea[x+1][y+1].etsaiaDago()) {}
				else if(x>1 && y<16 && gelaxkaMatrizea[x-1][y+1].etsaiaDago()) {}
				else {
					g.etsaiaKendu(x,y);
					y++;
					GelaxkaKudeatzailea gBerria = gelaxkaMatrizea[x][y];
					gBerria.setEtsaia(e);
				}
			}
		}
		else if(50<ausazkoZenb && ausazkoZenb<75) {
			if(x>0 && !gelaxkaMatrizea[x-1][y].blokeaDago() && !gelaxkaMatrizea[x-1][y].bonbaDago() && !gelaxkaMatrizea[x-1][y].etsaiaDago()) {
				if(x>1 && gelaxkaMatrizea[x-2][y].etsaiaDago()) {}
				else if(x>1 && y<16 && gelaxkaMatrizea[x-1][y+1].etsaiaDago()) {}
				else if(x>1 && y>1 && gelaxkaMatrizea[x-1][y-1].etsaiaDago()) {}
				else {
					g.etsaiaKendu(x,y);
					x--;
					GelaxkaKudeatzailea gBerria = gelaxkaMatrizea[x][y];
					gBerria.setEtsaia(e);
				}
			}
		}
		else{
			if(y>0 && !gelaxkaMatrizea[x][y-1].blokeaDago() && !gelaxkaMatrizea[x][y-1].bonbaDago() && !gelaxkaMatrizea[x][y-1].etsaiaDago()) {
				if(y>1 && gelaxkaMatrizea[x][y-2].etsaiaDago()) {}
				else if(x<10 && y>1 && gelaxkaMatrizea[x+1][y-1].etsaiaDago()) {}
				else if(x>1 && y>1 && gelaxkaMatrizea[x-1][y-1].etsaiaDago()) {}
				else {
					g.etsaiaKendu(x,y);
					y--;
					GelaxkaKudeatzailea gBerria = gelaxkaMatrizea[x][y];
					gBerria.setEtsaia(e);
				}
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
	
		
	
	
	public void sortuEszenarioClassic() {
		BlokeFactory bk = BlokeFactory.getNireBlokeFactory();
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
					Blokea b= bk.createBlokea(2);
					gK.setBlokea(b);
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
						Blokea b= bk.createBlokea(1);
						gK.setBlokea(b);
					}
				}
				gelaxkaMatrizea[x][y] = gK;
			} 
		}
		setChanged();
		notifyObservers(new String[] {"EszenarioClassic"});
	}
	
	public void sortuEszenarioSoft() {
		BlokeFactory bk = BlokeFactory.getNireBlokeFactory();
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
				else {
					Random r= new Random();
					int ausazkoZenb1= r.nextInt(101);
					if(ausazkoZenb1<40) {
						int ausazkoZenb2= r.nextInt(101);
						if(ausazkoZenb2>90 && etsaiKop<8) {
							Etsaia e= new Etsaia(x,y);
							gK.setEtsaia(e);
							etsaiKop++;
						}
					}
					else {
						Blokea b= bk.createBlokea(1);
						gK.setBlokea(b);
					}
				}
				gelaxkaMatrizea[x][y] = gK;
			} 
		}
		setChanged();
		notifyObservers(new String[] {"EszenarioSoft"});
	}
	
	public void sortuEszenarioEmpty() {
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
				else{
					Random r= new Random();
					int ausazkoZenb= r.nextInt(101);
					if(ausazkoZenb>95 && etsaiKop<10) {
						Etsaia e= new Etsaia(x,y);
						gK.setEtsaia(e);
						etsaiKop++;
					}
				}
				gelaxkaMatrizea[x][y] = gK;
			} 
		}
		setChanged();
		notifyObservers(new String[] {"EszenarioEmpty"});
	}
	
	public void partidaAmaitu(String pMota) {
		setChanged();
		notifyObservers(new String[] {pMota});
	}
}
