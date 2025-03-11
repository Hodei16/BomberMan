package Bista;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Eredua.BomberZuria;
import Eredua.EszenarioKudeatzailea;
import Eredua.GelaxkaKudeatzailea;
import Eredua.Gogorra;

public class Eszenarioa extends JFrame implements Observer {
	
	private static final EszenarioKudeatzailea EszenarioKudeatzeaile = null;
	private JPanel contentPane;
	private JPanel esz;
	private static Observable o;
	private Gelaxka[][] gelaxkaMatrix;
	private static Eszenarioa nEszenarioa = null;
	private static Observable nireObservable = EszenarioKudeatzailea.getNireEszenarioKudeatzailea();
	private JLabel atzekoa;
	private JLabel[][] jLMatrix= new JLabel[11][17];
	private BomberZuria bZ=null;
	
	private Eszenarioa(Observable pEK) {
	    this.gelaxkaMatrix = new Gelaxka[11][17];
	    pEK.addObserver(this);
	    initialize();

	    //Berria
	    Controler controler = new Controler();
	    this.addKeyListener(controler);
	    this.setFocusable(true);
	    this.requestFocusInWindow();

	}

	public static Eszenarioa getEszenarioa() {
		if (nEszenarioa==null) {
			nEszenarioa= new Eszenarioa(nireObservable);
		}
		return nEszenarioa;
	}
	
	public void initialize() {
	    setSize(678, 440);
	    setTitle("BOMBERMAN");
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setResizable(true);
	    setVisible(true);

	    contentPane = new JPanel(new BorderLayout());
	    contentPane.setPreferredSize(new Dimension(666, 404)); 
	    contentPane.setSize(666, 404);
	    contentPane.setBackground(Color.BLACK);
	    // contentPane.setBackground(new Color(248,171,68)); // Ladreilu kolorea
	    setContentPane(contentPane);

	    ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("stageBack1.png"));
	    Image backgroundImage = backgroundIcon.getImage().getScaledInstance(666, 404, Image.SCALE_SMOOTH);
	    atzekoa = new JLabel(new ImageIcon(backgroundImage));
	    contentPane.add(atzekoa, BorderLayout.CENTER);

	    esz = new JPanel(new GridLayout(11, 17, 0, 0));
	    esz.setOpaque(false);
	    esz.setSize(666, 404);
	    atzekoa.add(esz);

	    addComponentListener(new ComponentAdapter() {
	        @Override
	        public void componentResized(ComponentEvent e) {
	            erdianJarri();
	        }
	    });
	}

    public void erdianJarri() {
        int newWidth = getWidth();
        int newHeight = getHeight();

        int x = (newWidth - 666) / 2;
        int y = (newHeight - 404) / 2;

        atzekoa.setLocation(x, y);
        atzekoa.revalidate();
    }

	
	@Override
	public void update(Observable o, Object arg) {	
		if(arg instanceof String[])
		if(((String[])arg)[0]=="eszenarioaSortu") {
			eszenarioaSortu();
		}
		else if(((String[])arg)[0]=="goraMugitu") {
			goraMugitu();
		}
		else if(((String[])arg)[0]=="beheraMugitu") {
			beheraMugitu();
		}
		else if(((String[])arg)[0]=="ezkMugitu") {
			ezkMugitu();
		}
		else if(((String[])arg)[0]=="eskMugitu") {
			eskMugitu();
		}
		else if(((String[])arg)[0]=="bonbaJarri") {
			bonbaJarri();
		}
		else if(((String[])arg)[0]=="bonbaKendu") {
			//bonbaKendu();
		}
	}
	
	/*private void bonbaKendu() {
		int posXbZ= bZ.getPosX();
		int posYbZ= bZ.getPosY();
		JLabel jL= jLMatrix[posXbZ][posYbZ];
		ImageIcon icon = new ImageIcon(getClass().getResource("bomb1.png"));
		jL.setIcon(icon);
	}*/
	
	private void bonbaJarri() {
		int posXbZ= bZ.getPosX();
		int posYbZ= bZ.getPosY();
		Gelaxka g= gelaxkaMatrix[posXbZ][posYbZ];
		JLabel jL= jLMatrix[posXbZ][posYbZ];
		ImageIcon icon = new ImageIcon(getClass().getResource("bomb1.png"));
		jL.setIcon(icon);
	}
	
	private void ezkMugitu() {
		int posXbZ= bZ.getPosX();
		int posYbZ= bZ.getPosY()+1;
		Gelaxka g= gelaxkaMatrix[posXbZ][posYbZ];
		JLabel jL= jLMatrix[posXbZ][posYbZ];
		g.kenduBomberZuria();
		if(!g.bonbaDago()) {
			jL.setIcon(null);
		}
		Gelaxka gBerria= gelaxkaMatrix[posXbZ][posYbZ-1];
		JLabel jLBerria= jLMatrix[posXbZ][posYbZ-1];
		gBerria.setBomberZuria(bZ);
		ImageIcon icon = new ImageIcon(getClass().getResource("whitefront1.png"));
		jLBerria.setIcon(icon);
	}
	
	private void eskMugitu() {
		int posXbZ= bZ.getPosX();
		int posYbZ= bZ.getPosY()-1;
		Gelaxka g= gelaxkaMatrix[posXbZ][posYbZ];
		JLabel jL= jLMatrix[posXbZ][posYbZ];
		g.kenduBomberZuria();
		if(!g.bonbaDago()) {
			jL.setIcon(null);
		}
		Gelaxka gBerria= gelaxkaMatrix[posXbZ][posYbZ+1];
		JLabel jLBerria= jLMatrix[posXbZ][posYbZ+1];
		gBerria.setBomberZuria(bZ);
		ImageIcon icon = new ImageIcon(getClass().getResource("whitefront1.png"));
		jLBerria.setIcon(icon);
	}
	
	private void goraMugitu() {
		int posXbZ= bZ.getPosX()+1;
		int posYbZ= bZ.getPosY();
		Gelaxka g= gelaxkaMatrix[posXbZ][posYbZ];
		JLabel jL= jLMatrix[posXbZ][posYbZ];
		g.kenduBomberZuria();
		if(!g.bonbaDago()) {
			jL.setIcon(null);
		}
		Gelaxka gBerria= gelaxkaMatrix[posXbZ-1][posYbZ];
		JLabel jLBerria= jLMatrix[posXbZ-1][posYbZ];
		gBerria.setBomberZuria(bZ);
		ImageIcon icon = new ImageIcon(getClass().getResource("whitefront1.png"));
		jLBerria.setIcon(icon);
	}
	
	private void beheraMugitu() {
		int posXbZ= bZ.getPosX()-1;
		int posYbZ= bZ.getPosY();
		Gelaxka g= gelaxkaMatrix[posXbZ][posYbZ];
		JLabel jL= jLMatrix[posXbZ][posYbZ];
		g.kenduBomberZuria();
		if(!g.bonbaDago()) {
			jL.setIcon(null);
		}
		Gelaxka gBerria= gelaxkaMatrix[posXbZ+1][posYbZ];
		JLabel jLBerria= jLMatrix[posXbZ+1][posYbZ];
		gBerria.setBomberZuria(bZ);
		ImageIcon icon = new ImageIcon(getClass().getResource("whitefront1.png"));
		jLBerria.setIcon(icon);
	}
	
	private void eszenarioaSortu() {
		EszenarioKudeatzailea eK= EszenarioKudeatzailea.getNireEszenarioKudeatzailea();
		GelaxkaKudeatzailea[][] mat= eK.getGelaxkaMatrizea();
		
		esz.removeAll();
		
		for(int x=0; x<11; x++) {
			for(int y=0;y<17;y++) {
				JLabel Bomber, Zuria, Bloke;
				GelaxkaKudeatzailea g= mat[x][y];
				Gelaxka gel = new Gelaxka(g);
				gelaxkaMatrix[x][y]=gel;
				if(g.bomberDago()) {
					ImageIcon icon = new ImageIcon(getClass().getResource("whitefront1.png"));
					Bomber = new JLabel(icon);
					esz.add(Bomber);
					bZ= g.getBomberZuria();
					jLMatrix[x][y]=Bomber;
				}else if(g.blokeaDago()) {
					if(g.getBlokea() instanceof Gogorra) {
						ImageIcon icon = new ImageIcon(getClass().getResource("hardClassic.png"));
						Bloke = new JLabel(icon); 
						esz.add(Bloke);
					}
					else {
						ImageIcon icon = new ImageIcon(getClass().getResource("softClassic1.png"));
						Bloke = new JLabel(icon); 
						esz.add(Bloke);
						jLMatrix[x][y]=Bloke;
					}}
				
				else {
					Zuria = new JLabel("");
					esz.add(Zuria);
					jLMatrix[x][y]=Zuria;
				}
				
				
			}
		}
		esz.revalidate();
	    esz.repaint();
	    this.revalidate();
	    this.repaint();
	}

	private class Controler implements KeyListener {		
		private Controler() {}
		
		@Override
		public void keyPressed(KeyEvent e) {
		    EszenarioKudeatzailea eK = EszenarioKudeatzailea.getNireEszenarioKudeatzailea();
		    Eszenarioa es = Eszenarioa.getEszenarioa();
		    
		    if (eK == null) return; 
		    
		    int keyCode = e.getKeyCode();
		    char c = ' ';
		    
		    if (keyCode == KeyEvent.VK_W) c = 'w';
		    else if (keyCode == KeyEvent.VK_A) c = 'a';
		    else if (keyCode == KeyEvent.VK_S) c = 's';
		    else if (keyCode == KeyEvent.VK_D) c = 'd';
		    else if (keyCode == KeyEvent.VK_X) c = 'x';
		    
		    es.erdianJarri();
		    eK.bomberManMugitu(c);
		}
		
		@Override
		public void keyReleased(KeyEvent e) {}

		@Override
		public void keyTyped(KeyEvent e) {}
	}
}