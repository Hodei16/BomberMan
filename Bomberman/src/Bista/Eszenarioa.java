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
import javax.swing.*;
import java.awt.*;

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
	
	private static final EszenarioKudeatzailea EszenarioKudeatzeaile = null;
	private JPanel contentPane;
	private JPanel esz;
	private static Observable o;
	private Gelaxka[][] gelaxkaMatrix;
	private static Eszenarioa nEszenarioa = null;
	private static Observable nireObservable = EszenarioKudeatzailea.getNireEszenarioKudeatzailea();
	
	private Eszenarioa(Observable pEguraldiEstazioa) {
		this.gelaxkaMatrix = new Gelaxka[11][17];
		pEguraldiEstazioa.addObserver(this);
		initialize();
		
	}
	public static Eszenarioa getEszenarioa() {
		if (nEszenarioa==null) {
			nEszenarioa= new Eszenarioa(nireObservable);
		}
		return nEszenarioa;
	}
	
	public void initialize() {
		setSize(800, 600);	//setSize(150, 555);
		this.setContentPane(getContentPane());
		setTitle("Classic");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		

	}
	

	private JPanel getEsz() {
		if (esz == null) {
			esz = new JPanel();
			esz.setOpaque(false);
			esz.setLayout(new GridLayout(11, 17, 0, 0));
		}
		return esz;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 472);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	
		ImageIcon atzekoArg = new ImageIcon(getClass().getResource("stageBack1.png"));

        JLabel atzekoa = new JLabel(atzekoArg);
        atzekoa.setLayout(new BorderLayout());
        contentPane.add(atzekoa, BorderLayout.CENTER);
        setVisible(true);
		atzekoa.add(getEsz(), BorderLayout.CENTER);
		
		EszenarioKudeatzailea eK= EszenarioKudeatzailea.getNireEszenarioKudeatzailea();
		Gelaxka[][] mat= eK.getGelaxkaMatrizea();
		for(int x=0; x<11; x++) {
			for(int y=0;y<17;y++) {
				JLabel Bomber;
				Gelaxka g= mat[x][y];
				gelaxkaMatrix[x][y]=g;
				if(g.bomberDago()) {
					ImageIcon icon = new ImageIcon(getClass().getResource("whitefront1.png"));
					Bomber = new JLabel(icon);
					esz.add(Bomber);
				}
				else {
					Bomber = new JLabel("");
					esz.add(Bomber);
				}
			}
		}
	}

}
