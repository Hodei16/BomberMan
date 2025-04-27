package Eredua;

import java.util.Random;

public class EszenarioEmpty extends EszenarioKudeatzailea{
	
	public EszenarioEmpty() {super ();}
	
	public GelaxkaKudeatzailea[][] sortuEszenarioa(){
		GelaxkaKudeatzailea[][] gelaxkaMatrizea = new GelaxkaKudeatzailea[11][17];
		BomberMan b = JokoKudeatzailea.getNireJokoKudeatzailea().getBomberMan();
		int etsaiKop = 0;
		for(int x=0 ;x<11;x++) {
			for(int y=0; y<17; y++) {
				GelaxkaKudeatzailea gK = new GelaxkaKudeatzailea();
				if(x==0 && y==0) {
					gK.setBomberZuria(b);
					b.setPosX(x);
					b.setPosY(y);
				}
				else if(x==0 && y==1) {}
				else if(x==1 && y==0) {}
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
			JokoKudeatzailea.getNireJokoKudeatzailea().etsaiKopEguneratu(etsaiKop);
		}
		return gelaxkaMatrizea;
	}

}
