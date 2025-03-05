package Nagusia;

import Bista.Eszenarioa;
import Eredua.EszenarioKudeatzailea;
import Eredua.Teklatua;

public class Exekutagarria {

	public static void main(String[] args) {
		EszenarioKudeatzailea eK= EszenarioKudeatzailea.getNireEszenarioKudeatzailea();
		Eszenarioa es= Eszenarioa.getEszenarioa();
		eK.sortuEszenarioClassic();
		
		Teklatua teklatua = Teklatua.getTeklatua();
		while (true) {
			String tekla = teklatua.detectWASD();
			if(!tekla.isEmpty()) {
				eK.bomberManMugitu();
				try {
                    Thread.sleep(100); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
			}
		}
	}
}
