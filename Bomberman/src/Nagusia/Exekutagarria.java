package Nagusia;

import Bista.Eszenarioa;
import Bista.HasieraPantaila;

public class Exekutagarria {
    public static void main(String[] args) {
        Eszenarioa eszenarioa = Eszenarioa.getEszenarioa();
        HasieraPantaila hp = HasieraPantaila.getNireHasieraPantaila();
        hp.initialize();
        eszenarioa.sortuLehioa();
        eszenarioa.setVisible(true);
    }
}
