package Bista;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Eredua.Biguna;
import Eredua.Blokea;
import Eredua.BomberZuria;
import Eredua.Bonba;
import Eredua.Gelaxka;
import Eredua.Gogorra; 
import Eredua.Jokalaria;  
import Eredua.Pertsonaia; 

public class Eszenarioa extends JFrame implements Observer {
	
	private JPanel esz;
	
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
	
	public void initialize() {
		this.setSize(getPreferredSize());
		
		getEsz().setIcon(new ImageIcon(this.getClass().getResource(stageBack1)));
	}
	private JPanel getEsz() {
		if(esz == null) {
			esz = new JPanel("");
		}
		return esz;
	}
	
	
	
	@Override
	public void update (Observable o, Object arg) {
		
	}
}
