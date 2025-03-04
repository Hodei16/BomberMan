package Bista;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue; 
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


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
		this.gelaxkaMatrix = new Gelaxka[11][17];
		
		initialize();
		
	}
	public static Eszenarioa getEszenarioa() {
		if (nEszenarioa==null) {
			nEszenarioa= new Eszenarioa();
		}
		return nEszenarioa;
	}
	
	public void initialize() {
		setSize(150, 555);
		this.setContentPane(getContentPane());
		setTitle("Classic");
		setLocationRelativeTo(null);
		setVisible(true);
		
		

	}
	
	public JPanel getContentPane() {
		if (esz == null) {
			esz = new JPanel();
			esz.setLayout(new BorderLayout());
			
		}
		return esz;
	}
	
	
	
	
	@Override
	public void update (Observable o, Object arg) {
		
	}
}
