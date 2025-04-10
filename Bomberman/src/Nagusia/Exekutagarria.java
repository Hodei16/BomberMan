package Nagusia;

import Bista.HasieraPantaila;
import Bista.Eszenarioa;
import Eredua.EszenarioKudeatzailea;

public class Exekutagarria {
    public static void main(String[] args) {
        EszenarioKudeatzailea eszenarioKudeatzailea = EszenarioKudeatzailea.getNireEszenarioKudeatzailea();
        Eszenarioa eszenarioa = Eszenarioa.getEszenarioa();
        HasieraPantaila hp = HasieraPantaila.getNireHasieraPantaila();
        hp.setVisible(true);
    }
}
