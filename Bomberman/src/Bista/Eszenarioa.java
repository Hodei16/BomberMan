package Bista;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Eredua.EszenarioKudeatzailea;
import Eredua.Gelaxka; 

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.*;

public class Eszenarioa extends JFrame implements Observer {
    
    private JPanel contentPane;
    private JPanel esz;
    private Gelaxka[][] gelaxkaMatrix;
    private JLabel atzekoa; // Fondo de la ventana

    private static Eszenarioa nEszenarioa = null;
    private static Observable nireObservable = EszenarioKudeatzailea.getNireEszenarioKudeatzailea();
    
    private Eszenarioa(Observable pEguraldiEstazioa) {
        this.gelaxkaMatrix = new Gelaxka[11][17];
        pEguraldiEstazioa.addObserver(this);
        initialize();
    }

    public static Eszenarioa getEszenarioa() {
        if (nEszenarioa == null) {
            nEszenarioa = new Eszenarioa(nireObservable);
        }
        return nEszenarioa;
    }

    public void initialize() {
        setSize(800, 600);
        setTitle("Classic");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        atzekoa = new JLabel();
        atzekoa.setLayout(new BorderLayout());
        contentPane.add(atzekoa, BorderLayout.CENTER);
        
        esz = new JPanel();
        esz.setOpaque(false);
        esz.setLayout(new GridLayout(11, 17, 0, 0));
        atzekoa.add(esz, BorderLayout.CENTER);

        setVisible(true);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
            	atzekoAldeaEguneratu();
            }
        });

        atzekoAldeaEguneratu();
    }

    private void atzekoAldeaEguneratu() {
        int width = getWidth();
        int height = getHeight();
        ImageIcon imgIkonoa = new ImageIcon(getClass().getResource("stageBack1.png"));
        Image img = imgIkonoa.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        atzekoa.setIcon(new ImageIcon(img));
    }

    @Override
    public void update(Observable o, Object arg) {
        EszenarioKudeatzailea eK = EszenarioKudeatzailea.getNireEszenarioKudeatzailea();
        Gelaxka[][] mat = eK.getGelaxkaMatrizea();
        
        esz.removeAll();
        
        for (int x = 0; x < 11; x++) {
            for (int y = 0; y < 17; y++) {
                JLabel Bomber;
                Gelaxka g = mat[x][y];
                gelaxkaMatrix[x][y] = g;
                if (g.bomberDago()) {
                    ImageIcon icon = new ImageIcon(getClass().getResource("whitefront1.png"));
                    Image bomberImg = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                    Bomber = new JLabel(new ImageIcon(bomberImg));
                } else {
                    Bomber = new JLabel("");
                }
                esz.add(Bomber);
            }
        }
        esz.revalidate();
        esz.repaint();
    }
}
