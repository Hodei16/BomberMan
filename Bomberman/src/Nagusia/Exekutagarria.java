package Nagusia;

import Bista.Eszenarioa;
import Eredua.EszenarioKudeatzailea;

public class Exekutagarria {

	public static void main(String[] args) {
		EszenarioKudeatzailea eK= EszenarioKudeatzailea.getNireEszenarioKudeatzailea();
		Eszenarioa es= Eszenarioa.getEszenarioa();
		eK.sortuEszenarioClassic();
	}
}
