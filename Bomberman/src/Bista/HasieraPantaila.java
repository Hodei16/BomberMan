package Bista;

import Eredua.EszenarioKudeatzailea;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HasieraPantaila extends JFrame {
    private static HasieraPantaila nireHasieraPantaila = null;

    private HasieraPantaila() {
        setTitle("BOMBERMAN");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));
        
        add(botoiaSortu("CLASSIC", "EszenarioClassic"));
        add(botoiaSortu("SOFT", "EszenarioSoft"));
        add(botoiaSortu("EMPTY", "EszenarioEmpty"));
        
        setLocationRelativeTo(null);
    }

    public static synchronized HasieraPantaila getNireHasieraPantaila() {
        if (nireHasieraPantaila == null) {
            nireHasieraPantaila = new HasieraPantaila();
        }
        return nireHasieraPantaila;
    }

    private JButton botoiaSortu(String mota, String eszenarioa) {
        JButton btn = new JButton(mota);
        btn.addActionListener(e -> {
            dispose();
            EszenarioKudeatzailea.getNireEszenarioKudeatzailea().setEszenarioMota(eszenarioa);
            Eszenarioa.getEszenarioa();
        });
        return btn;
    }
}