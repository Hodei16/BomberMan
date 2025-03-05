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
        teklatua.fokoEman();
        
        System.out.println("Teklatua hasieratuta");
	}
	

}
