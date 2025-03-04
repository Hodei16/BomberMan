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
import javax.swing.border.EmptyBorder;

import Eredua.Biguna;
import Eredua.Blokea;
import Eredua.BomberZuria;
import Eredua.Bonba;
import Eredua.EszenarioKudeatzailea;
import Eredua.Gelaxka;
import Eredua.Gogorra; 
import Eredua.Jokalaria;  
import Eredua.Pertsonaia; 



public class Eszenarioa extends JFrame implements Observer {
	
	private JPanel contentPane;
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
	

	private JPanel getEsz() {
		if (esz == null) {
			esz = new JPanel();
			esz.setBackground(new Color(255, 255, 255));
			esz.setLayout(new GridLayout(10, 10, 0, 0));
		}
		return esz;
	}
	
	@Override
	public void update (Observable o, Object arg) {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getEsz(), BorderLayout.CENTER);
		
		EszenarioKudeatzailea eK= EszenarioKudeatzailea.getNireEszenarioKudeatzailea();
		Gelaxka[][] mat= eK.getGelaxkaMatrizea();
		for(int x=0; x<17; x++) {
			for(int y=0;y<11;y++) {
				Gelaxka g= mat[x][y];
				gelaxkaMatrix[x][y]=g;
				if(g.bomberDago()) {
					JLabel Bomber = new JLabel("whitefront1.png");
				}
			}
		}
	}
}
