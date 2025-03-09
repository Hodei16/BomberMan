package Eredua;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

import Bista.Eszenarioa;

public class Teklatua implements KeyListener{
	
	private static Teklatua nireTeklatua = null;
	private JFrame frame;
	
	private Teklatua() {
		frame = new JFrame();
	    frame.setUndecorated(true);
	    frame.setSize(1, 1);
	    frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.addKeyListener(this);
	    frame.setVisible(true);

	    frame.setFocusable(true);
	    frame.requestFocus();
	    frame.requestFocusInWindow();

	}
	public static Teklatua getTeklatua() {
		if(nireTeklatua == null) {
			nireTeklatua = new Teklatua();
		}
		return nireTeklatua;
	}
	
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
	    
	    es.erdiraMugitu();
	    eK.bomberManMugitu(c);
	    
	    
	}

	 @Override
	 public void keyReleased(KeyEvent e) {}

	 @Override
	 public void keyTyped(KeyEvent e) {}
	  
	 public void fokoEman() {
		 frame.requestFocusInWindow();
	 }
}
