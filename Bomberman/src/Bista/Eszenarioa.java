package Bista;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;

import Eredua.Biguna;
import Eredua.Blokea;
import Eredua.BomberZuria;
import Eredua.Bonba;
import Eredua.Gelaxka;
import Eredua.Gogorra; 
import Eredua.Jokalaria;  
import Eredua.Pertsonaia; 

public class Eszenarioa extends JFrame implements Observer {
	
	private Gelaxka[][] gelaxkaMatrix;
	private static Eszenarioa nEszenarioa = null;
	
	private Eszenarioa() {
		this.gelaxkaMatrix = new Gelaxka[10][16];
		
	}
	public static Eszenarioa getEszenarioa() {
		if (nEszenarioa==null) {
			nEszenarioa= new Eszenarioa();
		}
		return nEszenarioa;
	}
	
	
	
	
	
	@Override
	public void update (Observable o, Object arg) {
		
	}
}
