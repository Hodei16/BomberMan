package Bista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import Eredua.EszenarioKudeatzailea;

public class HasieraPantaila extends JFrame {
    private static HasieraPantaila nireHasieraPantaila = null;
    private JPanel contentPane;
    private JPanel esz;
    private Integer bomber = null;
    private String eszenario = null;

    private HasieraPantaila() {}
    
    public static synchronized HasieraPantaila getNireHasieraPantaila() {
        if (nireHasieraPantaila == null) {
            nireHasieraPantaila = new HasieraPantaila();
        }
        return nireHasieraPantaila;
    }

    public void initialize() {
        contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);
        contentPane.setBackground(Color.BLACK);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(400, 400));

        ImageIcon imageIcon1 = new ImageIcon(getClass().getResource("back.png"));
        JLabel imageLabel1 = new JLabel(imageIcon1);
        imageLabel1.setBounds(0, 0, imageIcon1.getIconWidth(), imageIcon1.getIconHeight());

        ImageIcon imageIcon2 = new ImageIcon(getClass().getResource("title.png"));
        JLabel imageLabel2 = new JLabel(imageIcon2);
        imageLabel2.setBounds(0, 0, imageIcon2.getIconWidth(), imageIcon2.getIconHeight());
                
   
        ImageIcon bomber1Icon = new ImageIcon(getClass().getResource("bomber1.png"));
        JButton bomber1Button = botoiaBomberMan(1);
        bomber1Button.setIcon(bomber1Icon); 
        bomber1Button.setContentAreaFilled(false);
        bomber1Button.setBorderPainted(false); 
        bomber1Button.setBounds(80, 180, bomber1Icon.getIconWidth(), bomber1Icon.getIconHeight());

        
        ImageIcon bomber2Icon = new ImageIcon(getClass().getResource("bomber2.png"));
        JButton bomber2Button = botoiaBomberMan(2);
        bomber2Button.setIcon(bomber2Icon); 
        bomber2Button.setContentAreaFilled(false);
        bomber2Button.setBorderPainted(false); 
        bomber2Button.setBounds(150, 250, bomber2Icon.getIconWidth(), bomber2Icon.getIconHeight());

             
       
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(0, 0, imageIcon1.getIconWidth(), imageIcon1.getIconHeight());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 0);

      
        
        
        gbc.gridy = 1;
        buttonPanel.add(botoiaEszenarioa("CLASSIC", "EszenarioClassic"), gbc);
        gbc.gridy = 2;
        buttonPanel.add(botoiaEszenarioa("SOFT", "EszenarioSoft"), gbc);
        gbc.gridy = 3;
        buttonPanel.add(botoiaEszenarioa("EMPTY", "EszenarioEmpty"), gbc);

    
        layeredPane.add(imageLabel1, Integer.valueOf(0));
        layeredPane.add(imageLabel2, Integer.valueOf(1));
        layeredPane.add(bomber1Button, Integer.valueOf(1));
        layeredPane.add(bomber2Button, Integer.valueOf(2)); 
        layeredPane.add(buttonPanel, Integer.valueOf(2));

        contentPane.add(layeredPane, BorderLayout.CENTER);
    }

    private JButton botoiaBomberMan(int bomberMota) {
        JButton btn = new JButton();
        btn.addActionListener(e -> {
            bomber = bomberMota;
            EszenarioKudeatzailea.getNireEszenarioKudeatzailea().setBomberMota(bomberMota);
        });
        return btn;
    }

    private JButton botoiaEszenarioa(String mota, String eszenarioMota) {
        JButton btn = new JButton(mota);
        btn.addActionListener(e -> {
            if(bomber != null) {
                EszenarioKudeatzailea.getNireEszenarioKudeatzailea().setEszenarioMota(eszenarioMota);
            } else {
                System.out.println("LEHENENGO BOMBERMAN MOTA AUKERATU!");
            }
        });
        return btn;
    }
}