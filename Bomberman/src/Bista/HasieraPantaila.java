package Bista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
    private static Integer bomber = null;
    private static String eszenario = null;
    private static Controler nireControler = null;

    private JButton bomber1Button;
    private JButton bomber2Button;
    private final ImageIcon bomber1Color = new ImageIcon(getClass().getResource("bomber1.png"));
    private final ImageIcon bomber1BW = new ImageIcon(getClass().getResource("bomber1BlackAndWhite.png"));
    private final ImageIcon bomber2Color = new ImageIcon(getClass().getResource("bomber2.png"));
    private final ImageIcon bomber2BW = new ImageIcon(getClass().getResource("bomber2BlackAndWhite.png"));

    private HasieraPantaila() {
	    this.addKeyListener(getControler());
        this.setFocusable(true);
        this.requestFocusInWindow();
    }
    
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

        ImageIcon background = new ImageIcon(getClass().getResource("back.png"));
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());

        ImageIcon title = new ImageIcon(getClass().getResource("title.png"));
        JLabel titleLabel = new JLabel(title);
        titleLabel.setBounds(0, 0, title.getIconWidth(), title.getIconHeight());

        bomber1Button = botoiaBomberMan(1, bomber1BW, 80, 180);
        bomber2Button = botoiaBomberMan(2, bomber2BW, 150, 250);

        JPanel scenarioPanel = new JPanel(new GridBagLayout());
        scenarioPanel.setOpaque(false);
        scenarioPanel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;

        scenarioPanel.add(botoiaEszenarioa("CLASSIC", "EszenarioClassic"), gbc);
        gbc.gridy = 1;
        scenarioPanel.add(botoiaEszenarioa("SOFT", "EszenarioSoft"), gbc);
        gbc.gridy = 2;
        scenarioPanel.add(botoiaEszenarioa("EMPTY", "EszenarioEmpty"), gbc);

        layeredPane.add(backgroundLabel, Integer.valueOf(0));
        layeredPane.add(titleLabel, Integer.valueOf(1));
        layeredPane.add(bomber1Button, Integer.valueOf(2));
        layeredPane.add(bomber2Button, Integer.valueOf(2));
        layeredPane.add(scenarioPanel, Integer.valueOf(2));

        contentPane.add(layeredPane, BorderLayout.CENTER);
    }

    private JButton botoiaBomberMan(int bomberMota, ImageIcon iconoInicial, int x, int y) {
        JButton btn = new JButton(iconoInicial);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setBounds(x, y, iconoInicial.getIconWidth(), iconoInicial.getIconHeight());
        
        btn.addActionListener(e -> {
            bomber = bomberMota;
            if (bomberMota == 1) {
                bomber1Button.setIcon(bomber1Color);
                bomber2Button.setIcon(bomber2BW);
            } else {
                bomber1Button.setIcon(bomber1BW);
                bomber2Button.setIcon(bomber2Color);
            }
            this.requestFocusInWindow();
        });
        return btn;
    }

    private JButton botoiaEszenarioa(String mota, String eszenarioMota) {
        JButton btn = new JButton(mota);
        btn.addActionListener(e -> {
            eszenario = eszenarioMota;
            btn.setBackground(Color.RED);
            if (bomber != null && eszenario != null) {
                System.out.println(bomber + " " + eszenario);
            } else {
                System.out.println("LEHENENGO BOMBERMAN MOTA AUKERATU!");
            }
            this.requestFocusInWindow();
        });
        return btn;
    }
    
	private static Controler getControler() {
		if(nireControler == null) {
			nireControler = new Controler();
		}
		return nireControler;
	}
    
	private static class Controler implements KeyListener {
	    
	    private Controler() {}

	    @Override
	    public void keyPressed(KeyEvent e) {
	        int keyCode = e.getKeyCode();
	        
	        if (keyCode == KeyEvent.VK_ENTER) {
	            if (bomber == null || eszenario == null) {
	                System.out.println("¡Falta seleccionar Bomberman o Escenario!");
	            } else {
	                EszenarioKudeatzailea.getNireEszenarioKudeatzailea().setBomberMota(bomber);
	                EszenarioKudeatzailea.getNireEszenarioKudeatzailea().setEszenarioMota(eszenario);
	                System.out.println("INICIANDO PARTIDA...");
	            }
	        }
	    }


	    @Override
	    public void keyReleased(KeyEvent e) {}

	    @Override
	    public void keyTyped(KeyEvent e) {}
	}
}
