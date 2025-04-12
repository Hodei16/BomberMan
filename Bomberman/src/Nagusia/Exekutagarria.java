package Nagusia;

import Bista.Eszenarioa;
import Bista.HasieraPantaila;

public class Exekutagarria {
    public static void main(String[] args) {
        Eszenarioa eszenarioa = Eszenarioa.getEszenarioa();
        HasieraPantaila hP = HasieraPantaila.getNireHasieraPantaila();
        hP.initialize();
        eszenarioa.sortuLehioa();
        eszenarioa.setVisible(true);
    }
}
