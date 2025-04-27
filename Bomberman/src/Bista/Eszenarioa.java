package Bista;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Eredua.BomberMan;
import Eredua.BomberZuria;
import Eredua.EszenarioKudeatzailea;
import Eredua.GelaxkaKudeatzailea;
import Eredua.Gogorra;
import Eredua.JokoKudeatzailea;

public class Eszenarioa extends JFrame implements Observer {
	
	private JPanel contentPane = new JPanel(new BorderLayout());;
	private JPanel esz;
	private Gelaxka[][] gelaxkaMatrix;
	private static Eszenarioa nEszenarioa = null;
	private JLabel atzekoa;
	private JLabel[][] jLMatrix= new JLabel[11][17];
	private BomberMan b;
	private static Controler nireControler = null;
	
	private Eszenarioa(Observable pEK) {
	    this.gelaxkaMatrix = new Gelaxka[11][17];
	    pEK.addObserver(this);
	    this.addKeyListener(getControler());
	    this.setFocusable(true);
	}

	public static Eszenarioa getEszenarioa() {
		if (nEszenarioa==null) {
			Observable nireObservable = JokoKudeatzailea.getNireJokoKudeatzailea();
			nEszenarioa= new Eszenarioa(nireObservable);
		}
		return nEszenarioa;
	}
	
	public void sortuLehioa() {
	    setSize(678, 440);
	    setTitle("BOMBERMAN");
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setResizable(true);
	    setVisible(true);
	   
	    contentPane.setPreferredSize(new Dimension(666, 404)); 
	    contentPane.setSize(666, 404);
	    contentPane.setBackground(Color.BLACK);
	    setContentPane(contentPane);
	    
	    contentPane.add(HasieraPantaila.getNireHasieraPantaila().getContentPane());
	    
        HasieraPantaila.getNireHasieraPantaila().setFocusable(true);

        this.requestFocusInWindow();
	}
	
	public void initialize(String mota) {
		HasieraPantaila.getNireHasieraPantaila().getContentPane().setVisible(false);
	    contentPane.setPreferredSize(new Dimension(666, 404)); 
	    contentPane.setSize(666, 404);
	    contentPane.setBackground(Color.BLACK);
	    setContentPane(contentPane);

	    ImageIcon backgroundIcon = new ImageIcon(getClass().getResource(mota));
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
        esz.setLocation(x-7, y-19);
        //esz.setLocation(x, y);
        esz.revalidate();
        atzekoa.revalidate();
    }

	
	@Override
	public void update(Observable o, Object arg) {	
		if(((String[])arg)[0]=="EszenarioClassic") {
		    initialize("stageBack1.png");
			eszenarioClassicSortu();
			erdianJarri();
		}
		else if(((String[])arg)[0]=="EszenarioSoft") { 
			initialize("stageBack2.png");
			eszenarioSoftSortu();
			erdianJarri();
		}
		else if(((String[])arg)[0]=="EszenarioEmpty") { 
			initialize("stageBack3.png");
			eszenarioEmptySortu();
			erdianJarri();
		}
		else if(((String[])arg)[0]=="Galduta") { 
			esz.removeAll();
		    ImageIcon icon = new ImageIcon(getClass().getResource("gameover.png"));
			atzekoa.setIcon(icon);
		    contentPane.add(atzekoa, BorderLayout.CENTER);
		    System.out.println("Galdu duzu!!!");
		}
		else if(((String[])arg)[0]=="Irabazi") { 
			esz.removeAll();
		    ImageIcon icon = new ImageIcon(getClass().getResource("youWin.png"));
			atzekoa.setIcon(icon);
		    contentPane.add(atzekoa, BorderLayout.CENTER);
		    System.out.println("Irabazi duzu!!!");
		}
	}
	
	private void eszenarioClassicSortu() {
		JokoKudeatzailea jK= JokoKudeatzailea.getNireJokoKudeatzailea();
		GelaxkaKudeatzailea[][] mat= jK.getGelaxkaMatrizea();
		for(int x=0; x<11; x++) {
			for(int y=0;y<17;y++) {
				JLabel bomber, zuria, bloke, etsaia;
				GelaxkaKudeatzailea g = mat[x][y];
				Gelaxka gel = new Gelaxka(g);
				gelaxkaMatrix[x][y]=gel;
				if(g.bomberDago()) {
					b= g.getBomberMan();
					ImageIcon icon;
					if (b instanceof BomberZuria) {
						icon = new ImageIcon(getClass().getResource("whitefront1.png"));
					}else {
						icon = new ImageIcon(getClass().getResource("blackfront1.png"));
					}
					bomber = new JLabel(icon);
					esz.add(bomber);
					jLMatrix[x][y]=bomber;
					gel.setIrudia(bomber);
				}else if(g.blokeaDago()) {
					if(g.getBlokea() instanceof Gogorra) {
						ImageIcon icon = new ImageIcon(getClass().getResource("hardClassic.png"));
						bloke = new JLabel(icon); 
						esz.add(bloke);
					}
					else {
						ImageIcon icon = new ImageIcon(getClass().getResource("softClassic1.png"));
						bloke = new JLabel(icon); 
						esz.add(bloke);
						jLMatrix[x][y]=bloke;
					}
					gel.setIrudia(bloke);
				}
				else if(g.etsaiaDago()) {
					ImageIcon icon = new ImageIcon(getClass().getResource("baloon1.png"));
					etsaia = new JLabel(icon);
					esz.add(etsaia);
					jLMatrix[x][y]=etsaia;
					gel.setIrudia(etsaia);
				}
				else {
					zuria = new JLabel("");
					esz.add(zuria);
					jLMatrix[x][y]=zuria;
					gel.setIrudia(zuria);
				}
			}
		}
		esz.revalidate();
	    esz.repaint();
	    this.revalidate();
	    this.repaint();
	}
	
	private void eszenarioSoftSortu() {
		JokoKudeatzailea jK= JokoKudeatzailea.getNireJokoKudeatzailea();
		GelaxkaKudeatzailea[][] mat= jK.getGelaxkaMatrizea();
		for(int x=0; x<11; x++) {
			for(int y=0;y<17;y++) {
				JLabel bomber, zuria, bloke, etsaia;
				GelaxkaKudeatzailea g= mat[x][y];
				Gelaxka gel = new Gelaxka(g);
				gelaxkaMatrix[x][y]=gel;
				if(g.bomberDago()) {
					b= g.getBomberMan();
					ImageIcon icon;
					if (b instanceof BomberZuria) {
						icon = new ImageIcon(getClass().getResource("whitefront1.png"));
					}else {
						icon = new ImageIcon(getClass().getResource("blackfront1.png"));
					}
					bomber = new JLabel(icon);
					esz.add(bomber);
					jLMatrix[x][y]=bomber;
					gel.setIrudia(bomber);
				}else if(g.blokeaDago()) {
					ImageIcon icon = new ImageIcon(getClass().getResource("softClassic1.png"));
					bloke = new JLabel(icon); 
					esz.add(bloke);
					jLMatrix[x][y]=bloke;
					gel.setIrudia(bloke);
				}
				else if(g.etsaiaDago()) {
					ImageIcon icon = new ImageIcon(getClass().getResource("baloon1.png"));
					etsaia = new JLabel(icon);
					esz.add(etsaia);
					jLMatrix[x][y]=etsaia;
					gel.setIrudia(etsaia);
				}
				else {
					zuria = new JLabel("");
					esz.add(zuria);
					jLMatrix[x][y]=zuria;
					gel.setIrudia(zuria);
				}
			}
		}
		esz.revalidate();
	    esz.repaint();
	    this.revalidate();
	    this.repaint();
	}
	
	private void eszenarioEmptySortu() {
		JokoKudeatzailea jK= JokoKudeatzailea.getNireJokoKudeatzailea();
		GelaxkaKudeatzailea[][] mat= jK.getGelaxkaMatrizea();
		for(int x=0; x<11; x++) {
			for(int y=0;y<17;y++) {
				JLabel bomber, zuria, etsaia;
				GelaxkaKudeatzailea g= mat[x][y];
				Gelaxka gel = new Gelaxka(g);
				gelaxkaMatrix[x][y]=gel;
				if(g.bomberDago()) {
					b= g.getBomberMan();
					ImageIcon icon;
					if (b instanceof BomberZuria) {
						icon = new ImageIcon(getClass().getResource("whitefront1.png"));
					}else {
						icon = new ImageIcon(getClass().getResource("blackfront1.png"));
					}
					bomber = new JLabel(icon);
					esz.add(bomber);
					jLMatrix[x][y]=bomber;
					gel.setIrudia(bomber);
				}
				else if(g.etsaiaDago()) {
					ImageIcon icon = new ImageIcon(getClass().getResource("baloon1.png"));
					etsaia = new JLabel(icon);
					esz.add(etsaia);
					jLMatrix[x][y]=etsaia;
					gel.setIrudia(etsaia);
				}
				else {
					zuria = new JLabel("");
					esz.add(zuria);
					jLMatrix[x][y]=zuria;
					gel.setIrudia(zuria);
				}
			}
		}
		esz.revalidate();
	    esz.repaint();
	    this.revalidate();
	    this.repaint();
	}
	
	private static Controler getControler() {
		if(nireControler == null) {
			nireControler = new Controler();
		}
		return nireControler;
	}

	private static class Controler implements KeyListener {

	    private boolean hasiDaPartida = false;

	    private Controler() {}

	    @Override
	    public void keyPressed(KeyEvent e) {
	        JokoKudeatzailea jK = JokoKudeatzailea.getNireJokoKudeatzailea();
	        HasieraPantaila hp = HasieraPantaila.getNireHasieraPantaila();
	        
	        if (jK == null) return; 

	        int keyCode = e.getKeyCode();
	        char c = ' ';
	        
	        if (!hasiDaPartida) {
	            if (keyCode == KeyEvent.VK_SPACE) {
	            	if (hp.getBomber() == null && hp.getEszenario() == null) {
	                    System.out.println("Bomberman eta Eszenario mota aukeratu behar da!");
	            	} else if (hp.getBomber() == null) {
	                    System.out.println("Bomberman mota aukeratu behar da!");
	                } else if (hp.getEszenario() == null) {
	                    System.out.println("Eszenario mota aukeratu behar da!!");
	                } else {
	                    hasiDaPartida = true;
	                    jK.setBomberMota(hp.getBomber());
	                    jK.setEszenarioMota(hp.getEszenario());
	                    System.out.println("PARTIDA HASTEN...");
	                }
	            }
	        } else {
	            if (keyCode == KeyEvent.VK_W) c = 'w';
	            else if (keyCode == KeyEvent.VK_A) c = 'a';
	            else if (keyCode == KeyEvent.VK_S) c = 's';
	            else if (keyCode == KeyEvent.VK_D) c = 'd';
	            else if (keyCode == KeyEvent.VK_X) c = 'x';
	            jK.bomberManMugitu(c);
	        }
	    }

	    @Override
	    public void keyReleased(KeyEvent e) {}

	    @Override
	    public void keyTyped(KeyEvent e) {}
	}

}